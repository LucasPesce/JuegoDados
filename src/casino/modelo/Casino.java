package casino.modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Casino {
    
    private ArrayList<Jugador> jugadores;
    private int mayorApuesta = 0;
    private String nombreJugadorMayorApuesta = "Sin registro";
    private int mejorPuntajeDados = 0;
    private String nombreJugadorMejorPuntaje = "Sin registro";
    private int conteoDadosCargados = 0;
    private HashMap<String, Integer> victimasDeTrampas = new HashMap<>();
    public int cantPartidasTotal = 0;
    private static final String ARCHIVO_GUARDADO = "partida_guardada.txt";

    
    public Casino() {
        jugadores = new ArrayList<>();
    }
    
    public ArrayList<Jugador> getJugadores() { return jugadores; }
    public int getMayorApuesta() { return mayorApuesta; }
    public String getNombreJugadorMayorApuesta() { return nombreJugadorMayorApuesta; }
    public int getMejorPuntajeDados() { return mejorPuntajeDados; }
    public String getNombreJugadorMejorPuntaje() { return nombreJugadorMejorPuntaje; }
    public HashMap<String, Integer> getVictimasDeTrampas() { return victimasDeTrampas; }

     public void reiniciarEstadisticas() {
        this.mayorApuesta = 0;
        this.nombreJugadorMayorApuesta = "Sin registro";
        this.mejorPuntajeDados = 0;
        this.nombreJugadorMejorPuntaje = "Sin registro";
        this.conteoDadosCargados = 0;
        this.victimasDeTrampas.clear();
        this.cantPartidasTotal = 0;
     }
     
    public void reiniciarVictoriasJugadores() {
        for (Jugador j : jugadores) {
            j.resetearVictorias(); 
        }
    }
     
    public void guardarPartida(int totalPartidas, int totalRondas, int partidaActual, int rondaActual) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_GUARDADO))) {
            writer.write(String.format("config,%d,%d,%d,%d", totalPartidas, totalRondas, partidaActual, rondaActual));
            writer.newLine();
            
            for (Jugador j : this.jugadores) {
                String linea = String.join(",",
                        j.getNombre(),
                        j.getApodo(),
                        j.obtenerTipoJugador(),
                        String.valueOf(j.getDinero()),
                        String.valueOf(j.getPartidasGanadas()) 
                );
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
        }
    }   
     public casino.modelo.PartidaGuardadaDTO cargarPartida() throws IOException, NumberFormatException {
        this.jugadores.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_GUARDADO))) {
            this.jugadores.clear();            
            String lineaConfig = reader.readLine();
            if (lineaConfig == null || !lineaConfig.startsWith("config,")) {
            throw new IOException("Formato de archivo inválido: falta o es incorrecta la línea de configuración.");
            }
            
           String[] datosConfig = lineaConfig.split(",");
           
           if (datosConfig.length < 5) { // Ahora debe tener 5 campos (config + 4 valores)
                throw new IOException("Línea de configuración incompleta.");
            }
           
           int totalPartidas = Integer.parseInt(datosConfig[1]);
           int totalRondas = Integer.parseInt(datosConfig[2]);
           int partidaActual = Integer.parseInt(datosConfig[3]);           
           int rondaActual = Integer.parseInt(datosConfig[4]);

            String lineaJugador;
            while ((lineaJugador = reader.readLine()) != null) {
                String[] datos = lineaJugador.split(",");    
                if (datos.length == 5) { // <-- AHORA COMPRUEBA 5 CAMPOS
                    String nombre = datos[0];
                    String apodo = datos[1];
                    String tipo = datos[2];
                    int dinero = Integer.parseInt(datos[3]);
                    int victorias = Integer.parseInt(datos[4]); // <-- NUEVO


                    Jugador jugadorCargado = crearJugadorDesdeTipo(nombre, apodo, tipo);
                    if (jugadorCargado != null) {
                        jugadorCargado.setDinero(dinero);
                        for (int i = 0; i < victorias; i++) {
                            jugadorCargado.sumarVictoria();
                        }
                        this.jugadores.add(jugadorCargado);
                    }
                }
            }
            if (this.jugadores.isEmpty()) {
                throw new IOException("No se cargó ningún jugador. Verifique el archivo.");
            }
            return new PartidaGuardadaDTO(totalPartidas, totalRondas, partidaActual, rondaActual, this.jugadores);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontró el archivo de partida guardada.");
        }
        
        
    }    
     
    public String leerHistorial() throws java.io.IOException {
        String nombreArchivo = "historial_partidas.txt";
        if (Files.exists(Paths.get(nombreArchivo))) {
            return String.join("\n", Files.readAllLines(Paths.get(nombreArchivo)));
        } else {
            return ""; 
        }
    }
    
    private Jugador crearJugadorDesdeTipo(String nombre, String apodo, String tipo) {
        switch (tipo) {
            case "Novato": return new JugadorNovato(nombre, apodo, 0);
            case "Experto": return new JugadorExperto(nombre, apodo, 0);
            case "VIP": return new JugadorVIP(nombre, apodo, 0);
            case "El Casino": return new JugadorCasino(nombre, 0);
            default: return null;
        }
    }
    
    public void actualizarEstadisticas(int apuesta, int puntajeDados, Jugador jugador) {
        if (apuesta > this.mayorApuesta) {
            this.mayorApuesta = apuesta;
            this.nombreJugadorMayorApuesta = jugador.getNombreConTipo();
        }
        if (puntajeDados > this.mejorPuntajeDados) {
            this.mejorPuntajeDados = puntajeDados;
            this.nombreJugadorMejorPuntaje = jugador.getNombreConTipo();
        }
    }   
    public void registrarVictima(Jugador victima) {
    String nombreVictima = victima.getNombre();
    int conteoActual = victimasDeTrampas.getOrDefault(nombreVictima, 0);
    victimasDeTrampas.put(nombreVictima, conteoActual + 1);
}
    public Jugador crearJugador(String nombre, String apodo, int tipo) {
        int dineroInicial = 500; 
        switch (tipo) {
            case 1 -> {
                return new JugadorNovato(nombre, apodo, dineroInicial);
            }
            case 2 -> {
                return new JugadorExperto(nombre, apodo, dineroInicial);
            }
            case 3 -> {
                return new JugadorVIP(nombre, apodo, dineroInicial);
            }
            case 4 -> {
                return new JugadorCasino(nombre, dineroInicial); //Consigna 3
            }
            default -> {
                System.out.println("Tipo inválido, se asignará como Novato.");
                return new JugadorNovato(nombre, apodo, dineroInicial);
            }
        }
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
    
    public void registrarUsoDadosCargados() {
    conteoDadosCargados++;
    }
    
    public int getConteoDadosCargados() {
    return conteoDadosCargados;
    }
    
    public boolean eliminarJugador(String apodo) {
        Jugador aEliminar = null;
        for (Jugador j : jugadores) {
            if (j.getApodo().equalsIgnoreCase(apodo)) {
                aEliminar = j;
                break;
            }
        }

        if (aEliminar != null) {
            jugadores.remove(aEliminar);
            return true; // Éxito
        } else {
            return false; // Fracaso (no se encontró)
        }
    }
    
    public void guardarHistorial(List<String> historial) {
        String nombreArchivo = "historial_partidas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, false))) { // false para sobreescribir
            for (String linea : historial) {
                writer.write(linea);
                writer.newLine(); 
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el historial en el archivo: " + e.getMessage());
        }
    }      
}
