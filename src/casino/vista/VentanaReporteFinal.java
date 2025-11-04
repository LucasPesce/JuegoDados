package casino.vista;
import casino.modelo.Casino;
import casino.modelo.Jugador;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VentanaReporteFinal extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaReporteFinal.class.getName());


    public VentanaReporteFinal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();       
        this.setResizable(false); 
        this.setLocationRelativeTo(parent);
        btnCerrar.addActionListener(e -> this.dispose());

    }
    
    public void mostrarReporte(Casino casino, List<String> historial) {
        llenarTablaRanking(casino.getJugadores());
        lblMayorApuesta.setText("$" + casino.getMayorApuesta() + " por " + casino.getNombreJugadorMayorApuesta());
        lblMejorPuntaje.setText(casino.getMejorPuntajeDados() + " por " + casino.getNombreJugadorMejorPuntaje());
        
        if (casino.getVictimasDeTrampas().isEmpty()) {
            lblJugadoresAfectados.setText("Ninguno");
        } else {
            StringBuilder afectados = new StringBuilder();
            casino.getVictimasDeTrampas().forEach((nombre, cantidad) -> {
                afectados.append(nombre).append(" (").append(cantidad).append(" veces), ");
            });
            lblJugadoresAfectados.setText(afectados.substring(0, afectados.length() - 2));
        }
        
        StringBuilder historialTexto = new StringBuilder();
        int inicio = Math.max(0, historial.size() - 3);
        for (int i = inicio; i < historial.size(); i++) {
            historialTexto.append(historial.get(i)).append("\n");
        }
        txtHistorial.setText(historialTexto.toString());        
        this.setVisible(true);
    }
    
    private void llenarTablaRanking(ArrayList<Jugador> jugadores) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Dinero");
        model.addColumn("Victorias");
        
        ArrayList<Jugador> jugadoresOrdenados = new ArrayList<>(jugadores);
        jugadoresOrdenados.sort(Comparator.comparingInt(Jugador::getDinero).reversed());

        for (Jugador j : jugadoresOrdenados) {
            Object[] row = new Object[4];
            row[0] = j.getNombre();
            row[1] = j.obtenerTipoJugador();
            row[2] = "$" + j.getDinero();
            row[3] = j.getPartidasGanadas();
            model.addRow(row);
        }
        
        tblRanking.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTituloRanking = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRanking = new javax.swing.JTable();
        lblTituloEstadisticas = new javax.swing.JLabel();
        JLbael = new javax.swing.JLabel();
        lblMayorApuesta = new javax.swing.JLabel();
        JLbael1 = new javax.swing.JLabel();
        lblMejorPuntaje = new javax.swing.JLabel();
        JLbael2 = new javax.swing.JLabel();
        lblJugadoresAfectados = new javax.swing.JLabel();
        lblTituloHistorial = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Rerporte Final del Juego");

        lblTituloRanking.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloRanking.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloRanking.setText("Ranking ");

        tblRanking.setBackground(new java.awt.Color(204, 204, 204));
        tblRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblRanking);

        lblTituloEstadisticas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloEstadisticas.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloEstadisticas.setText("Estadísticas Generales");

        JLbael.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        JLbael.setForeground(new java.awt.Color(255, 255, 255));
        JLbael.setText("Mayor apuesta realizada:");

        lblMayorApuesta.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMayorApuesta.setForeground(new java.awt.Color(255, 255, 255));
        lblMayorApuesta.setText("cargando...");

        JLbael1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        JLbael1.setForeground(new java.awt.Color(255, 255, 255));
        JLbael1.setText("Mejor puntaje de dados:");

        lblMejorPuntaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMejorPuntaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMejorPuntaje.setText("cargando...");

        JLbael2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        JLbael2.setForeground(new java.awt.Color(255, 255, 255));
        JLbael2.setText("Jugadores afectados por trampas:");

        lblJugadoresAfectados.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblJugadoresAfectados.setForeground(new java.awt.Color(255, 255, 255));
        lblJugadoresAfectados.setText("cargando...");

        lblTituloHistorial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloHistorial.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloHistorial.setText("Historial");

        txtHistorial.setEditable(false);
        txtHistorial.setBackground(new java.awt.Color(204, 204, 204));
        txtHistorial.setColumns(20);
        txtHistorial.setRows(5);
        jScrollPane2.setViewportView(txtHistorial);

        btnCerrar.setBackground(new java.awt.Color(255, 51, 51));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloHistorial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloEstadisticas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloRanking)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JLbael)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblMayorApuesta))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JLbael1)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblMejorPuntaje))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JLbael2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblJugadoresAfectados)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(184, 184, 184))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloRanking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblTituloEstadisticas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLbael)
                    .addComponent(lblMayorApuesta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLbael1)
                    .addComponent(lblMejorPuntaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLbael2)
                    .addComponent(lblJugadoresAfectados))
                .addGap(29, 29, 29)
                .addComponent(lblTituloHistorial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaReporteFinal dialog = new VentanaReporteFinal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLbael;
    private javax.swing.JLabel JLbael1;
    private javax.swing.JLabel JLbael2;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblJugadoresAfectados;
    private javax.swing.JLabel lblMayorApuesta;
    private javax.swing.JLabel lblMejorPuntaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloEstadisticas;
    private javax.swing.JLabel lblTituloHistorial;
    private javax.swing.JLabel lblTituloRanking;
    private javax.swing.JTable tblRanking;
    private javax.swing.JTextArea txtHistorial;
    // End of variables declaration//GEN-END:variables
}
