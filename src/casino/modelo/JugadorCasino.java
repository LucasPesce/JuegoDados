package casino.modelo;
import java.util.List;
import java.util.Random; 
import java.util.stream.Collectors;


    public class JugadorCasino extends Jugador{    
        private final Random random;
        private int trampasUsadas;
        
    public JugadorCasino(String nombre, int dineroInicial) {
        // Le pasamos "La Casa" como apodo por defecto.
        super("Casino", "La Casa", dineroInicial); 
        this.random = new Random();
    } 
    @Override
    public String obtenerTipoJugador() {
        return "El Casino";
    }
        @Override   
        public int calcularApuesta() {
        return 0;
    }
        
    public int tirarDadoCargado() {
        if (random.nextDouble() < 0.4) {  
            return 6; 
        } else {
            return random.nextInt(6) + 1; 
        }
    }

    public int lanzarDadosCargados() {
        int dado1 = tirarDadoCargado();
        int dado2 = tirarDadoCargado();
        System.out.println("Casino tiró dados: " + dado1 + " + " + dado2 + " = " + (dado1 + dado2));
        return (dado1 + dado2);        
    }
        
    public Jugador seleccionarJugadorAConfundir(List<Jugador> jugadores) {
        if (new Random().nextDouble() < 0.3) { // 30% de probabilidad de activar
            List<Jugador> jugadoresSinCasino = jugadores.stream()
                .filter(j -> !(j instanceof JugadorCasino))
                .collect(Collectors.toList());

            if (!jugadoresSinCasino.isEmpty()) {
                return jugadoresSinCasino.get(new Random().nextInt(jugadoresSinCasino.size()));
            }
        }
        return null;
    }
    
    public int getTrampasUsadas() {
        return trampasUsadas;
    }
}
        
 