package casino.modelo;


public class JugadorExperto extends Jugador {

    public JugadorExperto(String nombre, String apodo, int dineroInicial) {
        super(nombre, apodo, dineroInicial);
    }
    @Override
    public int calcularApuesta() {
        int apuesta = (int)(getDinero() * 0.2);
        return Math.max(apuesta, 1); 
    }
    @Override
    public String obtenerTipoJugador() {
        return "Experto";
    }
}
