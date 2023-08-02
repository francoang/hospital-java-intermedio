package gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import principal.Principal;

/**
 *
 * @author franc
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaListado = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPrincipal = new javax.swing.JMenu();
        menuSalir = new javax.swing.JMenuItem();
        menuPersona = new javax.swing.JMenu();
        menuCargar = new javax.swing.JMenuItem();
        menuModif = new javax.swing.JMenuItem();
        menuBaja = new javax.swing.JMenuItem();
        menuBuscar = new javax.swing.JMenuItem();
        menuCambiar = new javax.swing.JMenuItem();
        menuListados = new javax.swing.JMenu();
        menuTodos = new javax.swing.JMenuItem();
        menuPacientes = new javax.swing.JMenuItem();
        menuDoctores = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital de Córdoba");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("LISTADO");

        txtAreaListado.setEditable(false);
        txtAreaListado.setColumns(20);
        txtAreaListado.setRows(5);
        jScrollPane1.setViewportView(txtAreaListado);

        menuPrincipal.setText("Menu");

        menuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salir.png"))); // NOI18N
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuSalir);

        jMenuBar1.add(menuPrincipal);

        menuPersona.setText("Persona");

        menuCargar.setText("Cargar");
        menuCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCargarActionPerformed(evt);
            }
        });
        menuPersona.add(menuCargar);

        menuModif.setText("Modificar");
        menuModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModifActionPerformed(evt);
            }
        });
        menuPersona.add(menuModif);

        menuBaja.setText("Dar baja");
        menuBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBajaActionPerformed(evt);
            }
        });
        menuPersona.add(menuBaja);

        menuBuscar.setText("Buscar");
        menuBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBuscarActionPerformed(evt);
            }
        });
        menuPersona.add(menuBuscar);

        menuCambiar.setText("Cambiar");
        menuCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCambiarActionPerformed(evt);
            }
        });
        menuPersona.add(menuCambiar);

        jMenuBar1.add(menuPersona);

        menuListados.setText("Listado");

        menuTodos.setText("Listar todas las personas");
        menuTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTodosActionPerformed(evt);
            }
        });
        menuListados.add(menuTodos);

        menuPacientes.setText("Listar solo pacientes");
        menuPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPacientesActionPerformed(evt);
            }
        });
        menuListados.add(menuPacientes);

        menuDoctores.setText("Listar solo doctores");
        menuDoctores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDoctoresActionPerformed(evt);
            }
        });
        menuListados.add(menuDoctores);

        jMenuBar1.add(menuListados);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBajaActionPerformed
        new BorrarPersona().setVisible(true);
    }//GEN-LAST:event_menuBajaActionPerformed

    private void menuCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCargarActionPerformed
        new CargarPersona().setVisible(true);
    }//GEN-LAST:event_menuCargarActionPerformed

    private void menuTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTodosActionPerformed
        String listado = Principal.listarTodos();
        txtAreaListado.setText(listado);
    }//GEN-LAST:event_menuTodosActionPerformed

    private void menuModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModifActionPerformed
        new ModificarPersona().setVisible(true);
    }//GEN-LAST:event_menuModifActionPerformed

    private void menuBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBuscarActionPerformed
        JOptionPane.showMessageDialog(this, "Funcionalidad no implementada.", "¡Ups!", JOptionPane.WARNING_MESSAGE);        
    }//GEN-LAST:event_menuBuscarActionPerformed

    private void menuPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPacientesActionPerformed
        JOptionPane.showMessageDialog(this, "Funcionalidad no implementada.", "¡Ups!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_menuPacientesActionPerformed

    private void menuDoctoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDoctoresActionPerformed
        JOptionPane.showMessageDialog(this, "Funcionalidad no implementada.", "¡Ups!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_menuDoctoresActionPerformed

    private void menuCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCambiarActionPerformed
        new CambiarPersona().setVisible(true);
    }//GEN-LAST:event_menuCambiarActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuBaja;
    private javax.swing.JMenuItem menuBuscar;
    private javax.swing.JMenuItem menuCambiar;
    private javax.swing.JMenuItem menuCargar;
    private javax.swing.JMenuItem menuDoctores;
    private javax.swing.JMenu menuListados;
    private javax.swing.JMenuItem menuModif;
    private javax.swing.JMenuItem menuPacientes;
    private javax.swing.JMenu menuPersona;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuTodos;
    private javax.swing.JTextArea txtAreaListado;
    // End of variables declaration//GEN-END:variables
}
