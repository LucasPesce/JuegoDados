package casino.modelo;

public class JugadorNovato extends Jugador {
    public JugadorNovato(String nombre, String apodo, int dineroInicial) {
        super(nombre, apodo, dineroInicial); // super() es el constructor de Jugador
    }
    @Override
    public int calcularApuesta() {
        return 50;
    }
    @Override
    public String obtenerTipoJugador() {
        return "Novato";
    }
}
