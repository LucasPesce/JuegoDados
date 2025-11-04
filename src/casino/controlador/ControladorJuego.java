package casino.controlador;
import casino.modelo.Casino;
import casino.modelo.Jugador;
import casino.modelo.JugadorCasino;
import casino.vista.VentanaConfiguracion;
import casino.vista.VentanaJuego; 
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.io.IOException;
import casino.modelo.PartidaGuardadaDTO; 

public class ControladorJuego {
    private Casino casino;
    private VentanaConfiguracion ventanaConfig;  
    private boolean partidaConfirmada = false;

    public ControladorJuego(Casino casino, VentanaConfiguracion ventanaConfig) {
        this.casino = casino;
        this.ventanaConfig = ventanaConfig;
        configurarEventos();
    }

    private void configurarEventos() {
        /* ============= AGREGAR JUGADOR  =============*/
        ventanaConfig.getBtnAgregarJugador().addActionListener(e -> {
            String nombre = ventanaConfig.getTxtNombreJugador().getText().trim();
            String apodo = ventanaConfig.getTxtApodo().getText().trim();
            int tipo = ventanaConfig.getCmbTipoJugador().getSelectedIndex() + 1; // Novato=1, Experto=2, VIP=3

            if (!validarApodo(apodo)) {
                JOptionPane.showMessageDialog(ventanaConfig, 
                        "Apodo inválido. Debe tener entre 3 y 10 caracteres y solo letras/espacios.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Jugador jugador = casino.crearJugador(nombre, apodo, tipo);
            casino.agregarJugador(jugador);

            boolean existeCasino = casino.getJugadores().stream().anyMatch(j -> j instanceof JugadorCasino);
            if (!existeCasino) {
                int dineroInicial = 500; 
                JugadorCasino jugadorCasino = new JugadorCasino("Casino", dineroInicial);
                casino.agregarJugador(jugadorCasino);
                System.out.println("Se agregó automáticamente el jugador 'Casino' (La Casa).");
            }        
            actualizarListaJugadores();
            limpiarCamposJugador();
        });

        
        /* ============= ELIMINAR JUGADOR  =============*/
        ventanaConfig.getBtnEliminarJugador().addActionListener(e -> {
            String apodo = "";
            JList<String> lista = ventanaConfig.getLstJUgadoresRegistrados();
            int indiceSeleccionado = lista.getSelectedIndex();

            if (indiceSeleccionado != -1) {
                String elemento = lista.getModel().getElementAt(indiceSeleccionado);
                apodo = elemento.substring(elemento.indexOf('(') + 1, elemento.indexOf(')'));
            } else {
                apodo = ventanaConfig.getTxtApodo().getText().trim();
            }

            if (apodo.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaConfig, 
                    "Debe ingresar el apodo o seleccionar un jugador de la lista haciendo click sobre él.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean fueEliminado = casino.eliminarJugador(apodo); 

            if (fueEliminado) {
                JOptionPane.showMessageDialog(ventanaConfig, 
                    "Jugador con apodo '" + apodo + "' ha sido eliminado.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                actualizarListaJugadores(); 
            } else {
                JOptionPane.showMessageDialog(ventanaConfig, 
                    "No se encontró ningún jugador con el apodo: " + apodo,
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
                
        /* ============= CONFIRMAR PARTIDA  =============*/
        ventanaConfig.getBtnConfirmarPart().addActionListener(e -> {
            String dineroStr = ventanaConfig.getTxtDineroInicial().getText().trim();
            String rondasStr = ventanaConfig.getTxtRondasPartidas().getText().trim();

            try {
                int dinero;
                if (dineroStr.isEmpty()) {
                    dinero = 500; 
                    ventanaConfig.getTxtDineroInicial().setText("500"); 
                } else {
                    dinero = Integer.parseInt(dineroStr);
                }
                int cantRondas;
                if (rondasStr.isEmpty()) {
                    cantRondas = 3; 
                    ventanaConfig.getTxtRondasPartidas().setText("3");
                } else {
                    cantRondas = Integer.parseInt(rondasStr);
                }
                if (dinero <= 0 || cantRondas <= 0) {
                    JOptionPane.showMessageDialog(ventanaConfig,
                        "Dinero inicial y cantidad de rondas deben ser mayores a cero.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    partidaConfirmada = false;
                    return;
                }
                partidaConfirmada = true;
                JOptionPane.showMessageDialog(ventanaConfig,
                    "Configuración de la partida confirmada correctamente.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventanaConfig,
                    "Cantidad de partidas inválida.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                partidaConfirmada = false;
            }
        });

                
        /* ============= JUGAR  =============*/
        ventanaConfig.getBtnJugar().addActionListener(e -> {
           int cantJugadores = casino.getJugadores().size();
            if (cantJugadores < 2 || cantJugadores > 4) {
                JOptionPane.showMessageDialog(ventanaConfig,
                    "Debe haber entre 2 y 4 jugadores registrados.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!partidaConfirmada) {
                JOptionPane.showMessageDialog(ventanaConfig,
                    "Debes confirmar la configuración de la partida primero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int cantPartidas = getCantidadPartidasSeleccionada();
            int dineroInicial = Integer.parseInt(ventanaConfig.getTxtDineroInicial().getText().trim());
            int cantRondas = Integer.parseInt(ventanaConfig.getTxtRondasPartidas().getText().trim());    
            
            for (Jugador j : casino.getJugadores()) {
                j.setDinero(dineroInicial);
            }

            boolean trampaActiva = ventanaConfig.getChkTrampa().isSelected();
            JugadorCasino jugadorCasino = null;
            for (Jugador j : casino.getJugadores()) {
                if (j instanceof JugadorCasino) {
                    jugadorCasino = (JugadorCasino) j;
                    break;
                }
            }
            if (jugadorCasino != null && trampaActiva) {
                JOptionPane.showMessageDialog(ventanaConfig,
                    "La trampa del Casino está activada.\nEl Casino tiene un 40% de probabilidad de sacar 6 en cada dado.",
                    "Trampa activada", JOptionPane.WARNING_MESSAGE);
                System.out.println(" La trampa del Casino está ACTIVADA (dados cargados). <<<");
            } else {
                System.out.println(" La trampa del Casino está DESACTIVADA. <<<");
            }
            ventanaConfig.setVisible(false);
            VentanaJuego ventanaJuego = new VentanaJuego();         
            ControladorVentanaJuego controladorVentanaJuego = new ControladorVentanaJuego(casino, ventanaJuego, ventanaConfig);
            controladorVentanaJuego.iniciarJuego(cantPartidas, cantRondas);
                                 
    });        
                
        /* ============= CARGAR PARTIDA  =============*/
        ventanaConfig.getBtnCargarPartida().addActionListener(e -> {
           try {
               PartidaGuardadaDTO partidaGuardada = casino.cargarPartida();

                JOptionPane.showMessageDialog(ventanaConfig, "Partida cargada exitosamente.");

                ventanaConfig.setVisible(false);
                VentanaJuego ventanaJuego = new VentanaJuego();
                ControladorVentanaJuego controladorVentanaJuego = new ControladorVentanaJuego(casino, ventanaJuego, ventanaConfig);

                controladorVentanaJuego.restaurarJuegoCargado(partidaGuardada);

                actualizarListaJugadores();

            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventanaConfig, "Error al cargar la partida: " + ex.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        /* ============= SALIR  =============*/
        ventanaConfig.getBtnSalir().addActionListener(e -> System.exit(0));
    }
    
    private int getCantidadPartidasSeleccionada() {
        if (ventanaConfig.getOp2().isSelected()) {
            return 3;
        }
        if (ventanaConfig.getOp3().isSelected()) {
            return 5;
        }
        return 2; 
    }

    private void actualizarListaJugadores() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Jugador j : casino.getJugadores()) {
            modelo.addElement(j.getNombre() + " (" + j.getApodo() + ") - " + j.obtenerTipoJugador());
        }
        ventanaConfig.getLstJUgadoresRegistrados().setModel(modelo);
    }

    private void limpiarCamposJugador() {
        ventanaConfig.getTxtNombreJugador().setText("");
        ventanaConfig.getTxtApodo().setText("");
        ventanaConfig.getCmbTipoJugador().setSelectedIndex(0);
    }

    private boolean validarApodo(String apodo) {
        if (apodo.length() < 3 || apodo.length() > 10) return false;
        return apodo.matches("[a-zA-Z ]+");
    }
    
}
