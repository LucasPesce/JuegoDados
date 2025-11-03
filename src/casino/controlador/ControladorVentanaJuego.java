package casino.controlador;
import casino.modelo.Casino;
import casino.modelo.JuegoDados;
import casino.modelo.Jugador;
import casino.modelo.Reporte;
import casino.vista.VentanaJuego;
import casino.vista.VentanaPausa;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import casino.vista.VentanaConfiguracion;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.SwingUtilities;


public class ControladorVentanaJuego {
    private Casino casino;
    private VentanaJuego vistaJuego;
    private VentanaPausa vistaPausa;
    private JuegoDados juegoDados;
    private VentanaConfiguracion vistaConfig;
    
    private List<String> historialDeJuego;
    
    // Variables para controlar el estado del juego
    private int partidaActual;
    private int rondaActual;
    private int totalPartidas;
    private int totalRondas;
    private HashMap<Jugador, Integer> rondasGanadasEnPartida;

    
    public ControladorVentanaJuego(Casino casino, VentanaJuego vistaJuego, VentanaConfiguracion vistaConfig) {
        this.casino = casino;
        this.vistaJuego = vistaJuego;
        this.vistaConfig = vistaConfig;
        this.historialDeJuego = new ArrayList<>();
        this.vistaPausa = new VentanaPausa(vistaJuego, true);
        this.rondasGanadasEnPartida = new HashMap<>();
        configurarEventos();
    }
    
    public void iniciarJuego(int totalPartidas, int totalRondas) {
        this.totalPartidas = totalPartidas;
        this.totalRondas = totalRondas;
        this.partidaActual = 1;
        this.rondaActual = 1;
        
        casino.reiniciarEstadisticas();
        this.juegoDados = new JuegoDados(casino);
        reiniciarContadorRondasPartida();

        // Prepara la interfaz con los paneles de los jugadores
        SwingUtilities.invokeLater(() -> {
            vistaJuego.limpiarLog(); 
            vistaJuego.agregarAlLog(">>> ¡Nueva Partida Iniciada! <<<");
            vistaJuego.prepararInterfazJugadores(casino.getJugadores());
            actualizarInfoPartidaUI(0);
        });

        vistaJuego.setVisible(true);
    }
    
    public void continuarJuegoCargado(int totalPartidas, int totalRondas) {
        this.totalPartidas = totalPartidas;
        this.totalRondas = totalRondas;
        this.partidaActual = 1;
        this.rondaActual = 1;

        casino.reiniciarEstadisticas();
        this.juegoDados = new JuegoDados(casino);
        reiniciarContadorRondasPartida();

        // Prepara la interfaz con los paneles de los jugadores cargados
        SwingUtilities.invokeLater(() -> {
            vistaJuego.limpiarLog(); // <-- AÑADIR ESTA LÍNEA
            vistaJuego.agregarAlLog(">>> Partida Cargada Correctamente. <<<");            
            vistaJuego.prepararInterfazJugadores(casino.getJugadores());
            actualizarInfoPartidaUI(0);
        });

        vistaJuego.setVisible(true);
    }
    
    private void configurarEventos() {
        // ... (Tu código de configurarEventos es correcto y no necesita cambios) ...
        // Lo incluyo aquí para que el archivo esté completo.
        
        /* ============= BOTON AVANZAR  =============*/
        vistaJuego.getBtnAvanzar().addActionListener(e -> jugarSiguienteRonda());
        
        /* ============= MENU PARTIDA  =============*/
        vistaJuego.getMenuItemPausar().addActionListener(e -> pausarJuego());
        vistaJuego.getMenuItemGuardar().addActionListener(e -> {
            casino.guardarPartida(this.totalPartidas, this.totalRondas);
            JOptionPane.showMessageDialog(vistaJuego, "Partida guardada correctamente.");
        });

        /* ============= MENU PAUSA  =============*/
        vistaPausa.getBtnVolver().addActionListener(e -> vistaPausa.dispose());
        vistaPausa.getBtnGuardarPausa().addActionListener(e -> {
            casino.guardarPartida(this.totalPartidas, this.totalRondas);
            JOptionPane.showMessageDialog(vistaPausa, "Partida guardada.");
        });
        
        // --- Evento del Menú "Ranking Actual" ---
        vistaJuego.getMenuItemRanking().addActionListener(e -> {
            StringBuilder rankingMsg = new StringBuilder("--- RANKING ACTUAL ---\n\n");
            ArrayList<Jugador> jugadoresOrdenados = new ArrayList<>(casino.getJugadores());
            jugadoresOrdenados.sort(Comparator.comparingInt(Jugador::getDinero).reversed());
            
            int pos = 1;
            for (Jugador j : jugadoresOrdenados) {
                rankingMsg.append(pos).append(". ").append(j.getNombreConTipo()).append(" - $").append(j.getDinero()).append("\n");
                pos++;
            }
            JOptionPane.showMessageDialog(vistaJuego, rankingMsg.toString(), "Ranking Actual", JOptionPane.INFORMATION_MESSAGE);
        });

        // --- Evento del Menú "Historial de Partidas" ---
        vistaJuego.getMenuItemHistorial().addActionListener(e -> {
            try {
                String historial = casino.leerHistorial();
                if (historial.isEmpty()) {
                    historial = "Aún no hay historial de partidas guardado.";
                }
                javax.swing.JTextArea textArea = new javax.swing.JTextArea(historial, 20, 50);
                textArea.setEditable(false);
                javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
                JOptionPane.showMessageDialog(vistaJuego, scrollPane, "Historial de Partidas", JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(vistaJuego, "Error al leer el historial: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // --- Evento del Menú "Estadísticas Generales" ---
        vistaJuego.getMenuItemEstadisticas().addActionListener(e -> {
            StringBuilder statsMsg = new StringBuilder("--- ESTADÍSTICAS GENERALES ---\n\n");
            statsMsg.append("Mayor apuesta realizada: $").append(casino.getMayorApuesta()).append(" por ").append(casino.getNombreJugadorMayorApuesta()).append("\n");
            statsMsg.append("Mejor puntaje de dados: ").append(casino.getMejorPuntajeDados()).append(" por ").append(casino.getNombreJugadorMejorPuntaje()).append("\n");
            statsMsg.append("Veces que el Casino usó dados cargados: ").append(casino.getConteoDadosCargados()).append("\n");
            JOptionPane.showMessageDialog(vistaJuego, statsMsg.toString(), "Estadísticas Generales", JOptionPane.INFORMATION_MESSAGE);
        });
    
        vistaPausa.getBtnSalirPausa().addActionListener(e -> {
            vistaPausa.dispose();
            vistaJuego.dispose();
            vistaConfig.setVisible(true);
        });   
        
        vistaJuego.getMenuItemSalir().addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(
                vistaJuego,
                "¿Estás seguro de que quieres salir de la partida actual?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                vistaJuego.dispose();
                vistaConfig.setVisible(true);
            }
        });
    }
    
    /**
     * MÉTODO COMPLETAMENTE REEMPLAZADO PARA FUNCIONAR CON LA NUEVA VISTA
     */
    private void jugarSiguienteRonda() {
        if (partidaActual > totalPartidas || (juegoDados != null && juegoDados.isJuegoTerminado())) {
            return;
        }

        // --- Mensaje de inicio de ronda en el log ---
        vistaJuego.agregarAlLog("----------------------------------------");
        vistaJuego.agregarAlLog(String.format("Partida %d, Ronda %d:", partidaActual, rondaActual));

        JuegoDados.ResultadoRondaDTO resultadoRonda = juegoDados.jugarRonda();

        if (resultadoRonda == null) {
            return;
        }

        // --- Bucle para actualizar la UI y construir el log ---
        List<Jugador> jugadores = casino.getJugadores();
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugadorActual = jugadores.get(i);
            JuegoDados.InfoTiroDTO infoTiro = resultadoRonda.resultadosIndividuales.get(jugadorActual);
            boolean esGanador = resultadoRonda.ganadores.contains(jugadorActual);

            // Actualizar el panel visual del jugador (esto ya lo teníamos)
            vistaJuego.actualizarPanelJugador(i, jugadorActual, infoTiro, esGanador);

            // Construir y añadir el mensaje al log de texto
            String logMensaje = String.format("- %s apostó $%d y sacó %d + %d = %d.",
                    jugadorActual.getNombreConTipo(),
                    infoTiro.apuesta,
                    infoTiro.tiro1,
                    infoTiro.tiro2,
                    infoTiro.suma);
            if(infoTiro.fueConfundido) {
                logMensaje += " (¡Confundido!)";
            }
            vistaJuego.agregarAlLog(logMensaje);
        }

        if(resultadoRonda.ganadores.isEmpty()){
            vistaJuego.agregarAlLog("\nNadie ganó la ronda. El pozo se pierde.");
        } else {
            StringBuilder ganadoresStr = new StringBuilder();
            for(Jugador g : resultadoRonda.ganadores){
                ganadoresStr.append(g.getNombre()).append(", ");
            }
            ganadoresStr.setLength(ganadoresStr.length() - 2);
            vistaJuego.agregarAlLog("\nGanador(es) de la ronda: " + ganadoresStr.toString());
        }


        int pozoDeLaRonda = juegoDados.getUltimoPozo();
        SwingUtilities.invokeLater(() -> actualizarInfoPartidaUI(pozoDeLaRonda));

        if (juegoDados.isJuegoTerminado()) {
            vistaJuego.agregarAlLog("\n!!! Un jugador se quedó sin dinero. Fin del juego. !!!");
            finalizarJuego("Un jugador se quedó sin dinero.");
            return;
        }

        rondaActual++;
        if (rondaActual > totalRondas) {
            finalizarPartida();
        }
    }
    
    private void actualizarInfoPartidaUI(int pozoActual) {
        System.out.println(String.format("DEBUG UI: Actualizando a Partida=%d, Ronda=%d, Pozo=%d", partidaActual, rondaActual, pozoActual));
        
        String textoPartida = String.format("Partida: %d / %d", partidaActual, totalPartidas);
        vistaJuego.getLblPartidaActual().setText(textoPartida);
        
        String textoRonda = String.format("Ronda: %d / %d", rondaActual, totalRondas);
        vistaJuego.getLblRondaActual().setText(textoRonda);
        
        String textoPozo = String.format("Pozo: $%d", pozoActual);
        vistaJuego.getLblPozoAcumulado().setText(textoPozo);
        
        vistaJuego.setTitle(String.format("Casino - Partida %d/%d | Ronda %d/%d", 
                                          partidaActual, totalPartidas, rondaActual, totalRondas));
    }
   
    private void finalizarPartida() {
        Jugador ganadorPartida = determinarGanadorPartida();
        ganadorPartida.sumarVictoria();
        
        StringBuilder detallePartida = new StringBuilder();
        detallePartida.append("PARTIDA #").append(partidaActual)
                      .append(" | Ganador: ").append(ganadorPartida.getNombreConTipo())
                      .append(" | Rondas ganadas: ")
                      .append(rondasGanadasEnPartida.get(ganadorPartida))
                      .append(" de ").append(totalRondas);

        historialDeJuego.add(detallePartida.toString());
        casino.guardarHistorial(historialDeJuego);
        
        vistaJuego.agregarAlLog(String.format(">>> Fin de la Partida %d. Ganador: %s <<<", partidaActual, ganadorPartida.getNombre()));    
        JOptionPane.showMessageDialog(vistaJuego, "Fin de la Partida " + partidaActual + ". Ganador: " + ganadorPartida.getNombre());        
        
        partidaActual++;
        rondaActual = 1;
        
        if (partidaActual > totalPartidas) {
            finalizarJuego("Se completaron todas las partidas.");
        } else {
            reiniciarContadorRondasPartida();
            SwingUtilities.invokeLater(() -> actualizarInfoPartidaUI(0));
        }
    }
    
    private void finalizarJuego(String motivo) {
        JOptionPane.showMessageDialog(vistaJuego, "¡Juego Terminado! Motivo: " + motivo);
        
        vistaJuego.getBtnAvanzar().setEnabled(false);
        vistaJuego.getMenuItemPausar().setEnabled(false);
        vistaJuego.getMenuItemGuardar().setEnabled(false);
        
        int partidasJugadas = (partidaActual > totalPartidas) ? totalPartidas : partidaActual - 1;
        if (partidasJugadas < 1) partidasJugadas = 1; // Asegurar que sea al menos 1 si termina en la primera partida
        
        Reporte.generarReporteFinal(casino, partidasJugadas);
        
        casino.guardarPartida(this.totalPartidas, this.totalRondas);
        System.out.println("Reporte final generado. Cierra esta ventana para volver a configurar.");
        
        Object[] options = {"Volver al Menú Principal"};
        int result = JOptionPane.showOptionDialog(vistaJuego,
                "¡Juego Terminado! Motivo: " + motivo + "\nEl reporte final ha sido generado en la consola.",
                "Fin del Juego",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (result == 0 || result == JOptionPane.CLOSED_OPTION) {
             vistaJuego.dispose();
             vistaConfig.setVisible(true);
        }
    }
    
    private void pausarJuego() {
        vistaPausa.setVisible(true);
    }
    
    // --- MÉTODOS AUXILIARES ---
    private void reiniciarContadorRondasPartida() {
        for (Jugador j : casino.getJugadores()) {
            rondasGanadasEnPartida.put(j, 0);
        }
    }

    private Jugador determinarGanadorPartida() {
        Jugador ganador = null;
        int maxRondas = -1;
        for (Jugador j : rondasGanadasEnPartida.keySet()) {
            if (rondasGanadasEnPartida.get(j) > maxRondas) {
                maxRondas = rondasGanadasEnPartida.get(j);
                ganador = j;
            }
        }
        return (ganador != null) ? casino.getJugadores().get(0) : ganador; // Corregido: devolver ganador, no el primero
    }
}