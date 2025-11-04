package casino.modelo;

public class JugadorVIP extends Jugador {
    private boolean puedeRepetirTirada;

    public JugadorVIP(String nombre, String apodo, int dineroInicial) {
        super(nombre, apodo, dineroInicial);
        this.puedeRepetirTirada = true; 
    }
    @Override
    public int calcularApuesta() {
        int apuesta = (int)(getDinero() * 0.3);
        return Math.max(apuesta, 1);
    }
    @Override
    public String obtenerTipoJugador() {
        return "VIP";
    }
    public boolean puedeRepetir() {
        return puedeRepetirTirada;
    }
    public void usarRepeticion() {
        puedeRepetirTirada = false;
    }
    public void resetearRepeticion() {
        puedeRepetirTirada = true;
    }
}