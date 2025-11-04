package casino.vista;
import casino.modelo.JuegoDados; 
import casino.modelo.Jugador;    
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaJuego extends javax.swing.JFrame {
    
    private List<JPanel> panelesJugadores;
    private List<JLabel> lblsNombre;
    private List<JLabel> lblsDinero;
    private List<JLabel> lblsVictorias;
    private List<JLabel> lblsApuesta;
    private List<JLabel> lblsDados;
    private List<JLabel> lblsEstado;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaJuego.class.getName());


     public VentanaJuego() {
        initComponents(); 
        this.setLocationRelativeTo(null);
        this.setResizable(false); 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        panelesJugadores = new ArrayList<>(Arrays.asList(jPanel1, jPanel2, jPanel3, jPanel4));
        lblsNombre = new ArrayList<>(Arrays.asList(lblNombreJ1, lblNombreJ2, lblNombreJ3, lblNombreJ4));
        lblsDinero = new ArrayList<>(Arrays.asList(lblDineroJ1, lblDineroJ2, lblDineroJ3, lblDineroJ4));
        lblsVictorias = new ArrayList<>(Arrays.asList(lblVictoriasJ1, lblVictoriasJ2, lblVictoriasJ3, lblVictoriasJ4));
        lblsApuesta = new ArrayList<>(Arrays.asList(lblApuestaJ1, lblApuestaJ2, lblApuestaJ3, lblApuestaJ4));
        lblsDados = new ArrayList<>(Arrays.asList(lblDadosJ1, lblDadosJ2, lblDadosJ3, lblDadosJ4));
        lblsEstado = new ArrayList<>(Arrays.asList(lblEstadoJ1, lblEstadoJ2, lblEstadoJ3, lblEstadoJ4));
    }
    
    public void prepararInterfazJugadores(List<Jugador> jugadores) {
        // Oculta todos los paneles primero
        for (JPanel panel : panelesJugadores) {
            panel.setVisible(false);
        }
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            JPanel panel = panelesJugadores.get(i);

            panel.setVisible(true);
            panel.setBorder(BorderFactory.createTitledBorder(jugador.getNombreConTipo()));
            lblsNombre.get(i).setText("Nombre: " + jugador.getNombre());
            lblsDinero.get(i).setText("Dinero: $" + jugador.getDinero());
            lblsVictorias.get(i).setText("Victorias: " + jugador.getPartidasGanadas());
        }
    }

    public void actualizarPanelJugador(int indice, Jugador jugador, JuegoDados.InfoTiroDTO infoTiro, boolean esGanador) {
        if (indice >= panelesJugadores.size()) return; // Medida de seguridad

        JPanel panel = panelesJugadores.get(indice);
        panel.setBackground(panel.getParent().getBackground());

        lblsDinero.get(indice).setText("Dinero: $" + jugador.getDinero());
        lblsVictorias.get(indice).setText("Victorias: " + jugador.getPartidasGanadas());
        lblsApuesta.get(indice).setText("Apuesta: $" + infoTiro.apuesta);
        lblsDados.get(indice).setText("Dados: " + infoTiro.tiro1 + " + " + infoTiro.tiro2 + " = " + infoTiro.suma);

        JLabel estadoLabel = lblsEstado.get(indice);
        estadoLabel.setText(" "); 
        
        if (esGanador) {
            panel.setBackground(new java.awt.Color(144, 238, 144)); 
            estadoLabel.setText("¡GANADOR!");
        }

        if (infoTiro.fueConfundido) {
            if (!esGanador) {
                panel.setBackground(new java.awt.Color(255, 204, 204)); // Rojo claro
            }
            estadoLabel.setText("¡CONFUNDIDO!");
        }
    }
    
    public void agregarAlLog(String mensaje) {
        txtLogEventos.append(mensaje + "\n");
        txtLogEventos.setCaretPosition(txtLogEventos.getDocument().getLength());
    }

    
    public void limpiarLog() {
        txtLogEventos.setText("");
    }
    
    public JButton getBtnAvanzar() {
        return btnAvanzar;
    }

    public JMenuItem getMenuItemPausar() {
        return menuItemPausar;
    }

    public JMenuItem getMenuItemGuardar() {
        return menuItemGuardar;
    }
    
    public JMenuItem getMenuItemSalir() {
        return menuItemSalir;
    }

    public JMenuItem getMenuItemRanking() {
        return menuItemRanking;
    }
    
    public JMenuItem getMenuItemHistorial() {
        return menuItemHistorial;
    }

    public JMenuItem getMenuItemEstadisticas() {
        return menuItemEstadisticas;
    }
    
    public javax.swing.JLabel getLblPartidaActual() {
        return lblPartidaActual;
    }

    public javax.swing.JLabel getLblRondaActual() {
        return lblRondaActual;
    }

    public javax.swing.JLabel getLblPozoAcumulado() {
        return lblPozoAcumulado;
    }
    public JTextArea getTxtLogEventos() {
        return txtLogEventos;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInfoPartida = new javax.swing.JPanel();
        lblPozoAcumulado = new javax.swing.JLabel();
        lblPartidaActual = new javax.swing.JLabel();
        lblRondaActual = new javax.swing.JLabel();
        pnlJugadores = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNombreJ1 = new javax.swing.JLabel();
        lblDineroJ1 = new javax.swing.JLabel();
        lblVictoriasJ1 = new javax.swing.JLabel();
        lblApuestaJ1 = new javax.swing.JLabel();
        lblDadosJ1 = new javax.swing.JLabel();
        lblEstadoJ1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNombreJ2 = new javax.swing.JLabel();
        lblDineroJ2 = new javax.swing.JLabel();
        lblVictoriasJ2 = new javax.swing.JLabel();
        lblApuestaJ2 = new javax.swing.JLabel();
        lblDadosJ2 = new javax.swing.JLabel();
        lblEstadoJ2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblNombreJ3 = new javax.swing.JLabel();
        lblDineroJ3 = new javax.swing.JLabel();
        lblVictoriasJ3 = new javax.swing.JLabel();
        lblApuestaJ3 = new javax.swing.JLabel();
        lblDadosJ3 = new javax.swing.JLabel();
        lblEstadoJ3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblNombreJ4 = new javax.swing.JLabel();
        lblDineroJ4 = new javax.swing.JLabel();
        lblVictoriasJ4 = new javax.swing.JLabel();
        lblApuestaJ4 = new javax.swing.JLabel();
        lblDadosJ4 = new javax.swing.JLabel();
        lblEstadoJ4 = new javax.swing.JLabel();
        pnlLogEventos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLogEventos = new javax.swing.JTextArea();
        btnAvanzar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemPausar = new javax.swing.JMenuItem();
        menuItemGuardar = new javax.swing.JMenuItem();
        menuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemRanking = new javax.swing.JMenuItem();
        menuItemHistorial = new javax.swing.JMenuItem();
        menuItemEstadisticas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlInfoPartida.setBackground(java.awt.SystemColor.inactiveCaption);

        lblPozoAcumulado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPozoAcumulado.setText("Pozo: $0");

        lblPartidaActual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPartidaActual.setText("Partida: -/-");

        lblRondaActual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblRondaActual.setText("Ronda: -/-");

        javax.swing.GroupLayout pnlInfoPartidaLayout = new javax.swing.GroupLayout(pnlInfoPartida);
        pnlInfoPartida.setLayout(pnlInfoPartidaLayout);
        pnlInfoPartidaLayout.setHorizontalGroup(
            pnlInfoPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoPartidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPartidaActual)
                .addGap(47, 47, 47)
                .addComponent(lblRondaActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPozoAcumulado)
                .addGap(103, 103, 103))
        );
        pnlInfoPartidaLayout.setVerticalGroup(
            pnlInfoPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoPartidaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInfoPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPozoAcumulado)
                    .addComponent(lblPartidaActual)
                    .addComponent(lblRondaActual))
                .addContainerGap())
        );

        pnlJugadores.setBackground(java.awt.SystemColor.activeCaption);
        pnlJugadores.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblNombreJ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreJ1.setText("Nombre: -");

        lblDineroJ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDineroJ1.setText("Dinero: $-");

        lblVictoriasJ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVictoriasJ1.setText("Victorias: 0");

        lblApuestaJ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApuestaJ1.setText("Apuesta: $-");

        lblDadosJ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDadosJ1.setText("Dados: -");

        lblEstadoJ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEstadoJ1.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreJ1)
                    .addComponent(lblDineroJ1)
                    .addComponent(lblApuestaJ1)
                    .addComponent(lblDadosJ1)
                    .addComponent(lblVictoriasJ1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblEstadoJ1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNombreJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDineroJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVictoriasJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApuestaJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDadosJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstadoJ1))
        );

        pnlJugadores.add(jPanel1, new java.awt.GridBagConstraints());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblNombreJ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreJ2.setText("Nombre: -");

        lblDineroJ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDineroJ2.setText("Dinero: $-");

        lblVictoriasJ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVictoriasJ2.setText("Victorias: 0");

        lblApuestaJ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApuestaJ2.setText("Apuesta: $-");

        lblDadosJ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDadosJ2.setText("Dados: -");

        lblEstadoJ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEstadoJ2.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreJ2)
                    .addComponent(lblDineroJ2)
                    .addComponent(lblApuestaJ2)
                    .addComponent(lblDadosJ2)
                    .addComponent(lblVictoriasJ2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblEstadoJ2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblNombreJ2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDineroJ2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVictoriasJ2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApuestaJ2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDadosJ2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstadoJ2))
        );

        pnlJugadores.add(jPanel2, new java.awt.GridBagConstraints());

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblNombreJ3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreJ3.setText("Nombre: -");

        lblDineroJ3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDineroJ3.setText("Dinero: $-");

        lblVictoriasJ3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVictoriasJ3.setText("Victorias: 0");

        lblApuestaJ3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApuestaJ3.setText("Apuesta: $-");

        lblDadosJ3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDadosJ3.setText("Dados: -");

        lblEstadoJ3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEstadoJ3.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreJ3)
                    .addComponent(lblDineroJ3)
                    .addComponent(lblApuestaJ3)
                    .addComponent(lblDadosJ3)
                    .addComponent(lblVictoriasJ3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblEstadoJ3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblNombreJ3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDineroJ3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVictoriasJ3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApuestaJ3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDadosJ3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstadoJ3))
        );

        pnlJugadores.add(jPanel3, new java.awt.GridBagConstraints());

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblNombreJ4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreJ4.setText("Nombre: -");

        lblDineroJ4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDineroJ4.setText("Dinero: $-");

        lblVictoriasJ4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVictoriasJ4.setText("Victorias: 0");

        lblApuestaJ4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApuestaJ4.setText("Apuesta: $-");

        lblDadosJ4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDadosJ4.setText("Dados: -");

        lblEstadoJ4.setBackground(new java.awt.Color(255, 255, 204));
        lblEstadoJ4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEstadoJ4.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreJ4)
                    .addComponent(lblDineroJ4)
                    .addComponent(lblApuestaJ4)
                    .addComponent(lblDadosJ4)
                    .addComponent(lblVictoriasJ4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblEstadoJ4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblNombreJ4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDineroJ4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVictoriasJ4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApuestaJ4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDadosJ4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstadoJ4))
        );

        pnlJugadores.add(jPanel4, new java.awt.GridBagConstraints());

        pnlLogEventos.setBackground(java.awt.SystemColor.inactiveCaption);
        pnlLogEventos.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtLogEventos.setEditable(false);
        txtLogEventos.setColumns(20);
        txtLogEventos.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtLogEventos.setRows(5);
        jScrollPane1.setViewportView(txtLogEventos);

        pnlLogEventos.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnAvanzar.setBackground(new java.awt.Color(255, 51, 51));
        btnAvanzar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAvanzar.setForeground(new java.awt.Color(255, 255, 255));
        btnAvanzar.setText("Lanzar Dados / Siguiente Ronda");
        btnAvanzar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarActionPerformed(evt);
            }
        });

        jMenu1.setText("Partida");

        menuItemPausar.setText("Pausa");
        jMenu1.add(menuItemPausar);

        menuItemGuardar.setText("Guardar Partida");
        jMenu1.add(menuItemGuardar);

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ver");

        menuItemRanking.setText("Ranking Actual");
        jMenu2.add(menuItemRanking);

        menuItemHistorial.setText("Historial de Partidas");
        jMenu2.add(menuItemHistorial);

        menuItemEstadisticas.setText("Estadísticas Generales");
        jMenu2.add(menuItemEstadisticas);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInfoPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlLogEventos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAvanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlInfoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAvanzar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlLogEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvanzarActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
    }//GEN-LAST:event_menuItemSalirActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VentanaJuego().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApuestaJ1;
    private javax.swing.JLabel lblApuestaJ2;
    private javax.swing.JLabel lblApuestaJ3;
    private javax.swing.JLabel lblApuestaJ4;
    private javax.swing.JLabel lblDadosJ1;
    private javax.swing.JLabel lblDadosJ2;
    private javax.swing.JLabel lblDadosJ3;
    private javax.swing.JLabel lblDadosJ4;
    private javax.swing.JLabel lblDineroJ1;
    private javax.swing.JLabel lblDineroJ2;
    private javax.swing.JLabel lblDineroJ3;
    private javax.swing.JLabel lblDineroJ4;
    private javax.swing.JLabel lblEstadoJ1;
    private javax.swing.JLabel lblEstadoJ2;
    private javax.swing.JLabel lblEstadoJ3;
    private javax.swing.JLabel lblEstadoJ4;
    private javax.swing.JLabel lblNombreJ1;
    private javax.swing.JLabel lblNombreJ2;
    private javax.swing.JLabel lblNombreJ3;
    private javax.swing.JLabel lblNombreJ4;
    private javax.swing.JLabel lblPartidaActual;
    private javax.swing.JLabel lblPozoAcumulado;
    private javax.swing.JLabel lblRondaActual;
    private javax.swing.JLabel lblVictoriasJ1;
    private javax.swing.JLabel lblVictoriasJ2;
    private javax.swing.JLabel lblVictoriasJ3;
    private javax.swing.JLabel lblVictoriasJ4;
    private javax.swing.JMenuItem menuItemEstadisticas;
    private javax.swing.JMenuItem menuItemGuardar;
    private javax.swing.JMenuItem menuItemHistorial;
    private javax.swing.JMenuItem menuItemPausar;
    private javax.swing.JMenuItem menuItemRanking;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JPanel pnlInfoPartida;
    private javax.swing.JPanel pnlJugadores;
    private javax.swing.JPanel pnlLogEventos;
    private javax.swing.JTextArea txtLogEventos;
    // End of variables declaration//GEN-END:variables
}
