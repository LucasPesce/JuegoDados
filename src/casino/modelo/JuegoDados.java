package casino.modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JuegoDados {   
    private Dado dado;
    private Casino casino;
    private boolean juegoTerminado; 
    private int ultimoPozo;

    
    public JuegoDados(Casino casino) {
        this.dado = new Dado();
        this.casino = casino;
        this.juegoTerminado = false;
        this.ultimoPozo = 0;
    }

    public ResultadoRondaDTO jugarRonda(){        
        if (juegoTerminado) {
            return null;
        }        
        
        ArrayList<Jugador> jugadores = casino.getJugadores();
        int pozo = 0;        
        HashMap<Jugador, InfoTiroDTO> resultadosIndividuales = new HashMap<>();
        HashMap<Jugador, Integer> resultados = new HashMap<>();

        Jugador jugadorConfundido = null;
        JugadorCasino jugadorCasino = null;
        for (Jugador j : jugadores) {
            if (j instanceof JugadorCasino) {
                jugadorCasino = (JugadorCasino) j;
                break;
            }
        }
        if (jugadorCasino != null) {
            jugadorConfundido = jugadorCasino.seleccionarJugadorAConfundir(jugadores);            
            if (jugadorConfundido != null) {
                casino.registrarVictima(jugadorConfundido); 
            }
        }
        for (Jugador jugador : jugadores) {
            int apuesta = jugador.calcularApuesta();
            if (apuesta > jugador.getDinero()) apuesta = jugador.getDinero();
            jugador.perder(apuesta);
            pozo += apuesta;

            int tiro1, tiro2, suma;
            boolean fueConfundido = jugador.equals(jugadorConfundido);

            if (jugador instanceof JugadorCasino) {
                  tiro1 = ((JugadorCasino) jugador).tirarDadoCargado();
                  tiro2 = ((JugadorCasino) jugador).tirarDadoCargado();
                  casino.registrarUsoDadosCargados(); 
            } else {
                  tiro1 = dado.tirar();
                  tiro2 = dado.tirar();
            }

            if (fueConfundido) {
                tiro1 = Math.max(1, tiro1 - 1);
                tiro2 = Math.max(1, tiro2 - 1);
            }
            suma = tiro1 + tiro2;
            if (jugador instanceof JugadorVIP vip) { 
                if (vip.puedeRepetir() && suma < 8) {
                    tiro1 = dado.tirar();
                    tiro2 = dado.tirar();
                    suma = tiro1 + tiro2; 
                    vip.usarRepeticion();
                }
            }
            casino.actualizarEstadisticas(apuesta, suma, jugador);
            resultados.put(jugador, suma);
            resultadosIndividuales.put(jugador, new InfoTiroDTO(apuesta, tiro1, tiro2, suma, fueConfundido));
     
        } 

        int maxPuntaje = resultados.values().stream().max(Integer::compare).orElse(0);
        ArrayList<Jugador> ganadores = new ArrayList<>();
        for (Jugador j : resultados.keySet()) {
            if (resultados.get(j) == maxPuntaje) ganadores.add(j);
        }

        if (ganadores.isEmpty()) {
        } else {
            int premioPorJugador = pozo / ganadores.size();
            for (Jugador ganador : ganadores) {
                ganador.ganar(premioPorJugador);
            }
        }
        for (Jugador j : jugadores) {           
            if (j.getDinero() == 0) {
                juegoTerminado = true;
                break;
            }
        }
        for (Jugador j : jugadores) if (j instanceof JugadorVIP) ((JugadorVIP) j).resetearRepeticion();
        
        if (juegoTerminado) {
            return null; //  el juego ya no sigue
        }
        this.ultimoPozo = pozo;
        
        if (juegoTerminado) {
            return null;
        }
        return new ResultadoRondaDTO(ganadores, resultadosIndividuales);
    }
    
    public int getUltimoPozo() {
        return ultimoPozo;
    }
    
    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
    public static class InfoTiroDTO {
    public final int apuesta;
    public final int tiro1;
    public final int tiro2;
    public final int suma;
    public final boolean fueConfundido;

    public InfoTiroDTO(int apuesta, int tiro1, int tiro2, int suma, boolean fueConfundido) {
        this.apuesta = apuesta;
        this.tiro1 = tiro1;
        this.tiro2 = tiro2;
        this.suma = suma;
        this.fueConfundido = fueConfundido;
    }
}
    public static class ResultadoRondaDTO {
    public final List<Jugador> ganadores;
    public final HashMap<Jugador, InfoTiroDTO> resultadosIndividuales;

    public ResultadoRondaDTO(List<Jugador> ganadores, HashMap<Jugador, InfoTiroDTO> resultados) {
        this.ganadores = ganadores;
        this.resultadosIndividuales = resultados;
    }
}   
}
