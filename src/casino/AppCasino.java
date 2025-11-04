package casino;

import casino.modelo.*;
import casino.vista.*;
import casino.controlador.*;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class AppCasino {

    public static void main(String[] args) {

        // Inicializar el modelo
        Casino casino = new Casino(); // clase que gestiona los jugadores y estadísticas

        // Inicializar la vista
        VentanaConfiguracion ventanaConfig = new VentanaConfiguracion();

        // Inicializar el controlador
        ControladorJuego controlador = new ControladorJuego(casino, ventanaConfig);

        // Mostrar la ventana de configuración
        ventanaConfig.setVisible(true);
    }
}
