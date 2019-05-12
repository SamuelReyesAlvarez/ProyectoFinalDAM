/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Samuel Reyes
 *
 */
public class PanelJuego extends javax.swing.JPanel {

    /**
     * Creates new form PanelJuego
     */
    public PanelJuego() {
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

        separador = new javax.swing.JSplitPane();
        pIzquierda = new javax.swing.JPanel();
        piFoto = new javax.swing.JPanel();
        separadorFoto = new javax.swing.JSplitPane();
        etNombre = new javax.swing.JLabel();
        etFoto = new javax.swing.JLabel();
        piAtributos = new javax.swing.JPanel();
        separadorAtributos = new javax.swing.JSplitPane();
        pAtrSuperior = new javax.swing.JPanel();
        etNivel = new javax.swing.JLabel();
        etOroAcumulado = new javax.swing.JLabel();
        etTurnoActual = new javax.swing.JLabel();
        pAtrInferior = new javax.swing.JPanel();
        panelVida = new javax.swing.JPanel();
        pbVida = new javax.swing.JProgressBar();
        panelExperiencia = new javax.swing.JPanel();
        pbExperiencia = new javax.swing.JProgressBar();
        panelAtaque = new javax.swing.JPanel();
        etAtaque = new javax.swing.JLabel();
        ataqueMinMax = new javax.swing.JLabel();
        panelDefensa = new javax.swing.JPanel();
        etDefensa = new javax.swing.JLabel();
        defensaMinMax = new javax.swing.JLabel();
        pDerecha = new javax.swing.JPanel();
        pdCentro = new javax.swing.JPanel();
        pdSur = new javax.swing.JPanel();
        btnSiguienteTurno = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());

        separador.setDividerLocation(248);
        separador.setDividerSize(1);
        separador.setEnabled(false);

        pIzquierda.setPreferredSize(new java.awt.Dimension(249, 598));
        pIzquierda.setLayout(new java.awt.GridLayout(2, 1));

        piFoto.setPreferredSize(new java.awt.Dimension(248, 299));
        piFoto.setLayout(new java.awt.BorderLayout());

        separadorFoto.setDividerLocation(25);
        separadorFoto.setDividerSize(0);
        separadorFoto.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        separadorFoto.setEnabled(false);

        etNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        etNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etNombre.setText("Nombre de Jugador");
        separadorFoto.setTopComponent(etNombre);

        etFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etFoto.setText("Foto de Jugador");
        etFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        separadorFoto.setBottomComponent(etFoto);

        piFoto.add(separadorFoto, java.awt.BorderLayout.CENTER);

        pIzquierda.add(piFoto);

        piAtributos.setLayout(new java.awt.BorderLayout());

        separadorAtributos.setDividerLocation(60);
        separadorAtributos.setDividerSize(0);
        separadorAtributos.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        separadorAtributos.setEnabled(false);

        pAtrSuperior.setLayout(new java.awt.BorderLayout());

        etNivel.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        etNivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etNivel.setText("Nivel");
        etNivel.setToolTipText("Nivel");
        etNivel.setPreferredSize(null);
        pAtrSuperior.add(etNivel, java.awt.BorderLayout.WEST);

        etOroAcumulado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        etOroAcumulado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etOroAcumulado.setText("Oro");
        etOroAcumulado.setToolTipText("Oro acumulado");
        pAtrSuperior.add(etOroAcumulado, java.awt.BorderLayout.CENTER);

        etTurnoActual.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        etTurnoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etTurnoActual.setText("Turno");
        etTurnoActual.setToolTipText("Turno de partida");
        etTurnoActual.setPreferredSize(null);
        pAtrSuperior.add(etTurnoActual, java.awt.BorderLayout.LINE_END);

        separadorAtributos.setLeftComponent(pAtrSuperior);

        pAtrInferior.setLayout(new java.awt.GridLayout(4, 1));

        pbVida.setToolTipText("Vida actual");
        pbVida.setValue(100);
        pbVida.setString("Vida 100%");
        pbVida.setStringPainted(true);

        javax.swing.GroupLayout panelVidaLayout = new javax.swing.GroupLayout(panelVida);
        panelVida.setLayout(panelVidaLayout);
        panelVidaLayout.setHorizontalGroup(
            panelVidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
            .addGroup(panelVidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelVidaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pbVida, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelVidaLayout.setVerticalGroup(
            panelVidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
            .addGroup(panelVidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelVidaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pbVida, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pbVida.getAccessibleContext().setAccessibleParent(pAtrInferior);

        pAtrInferior.add(panelVida);

        pbExperiencia.setToolTipText("Experiencia acumulada");
        pbExperiencia.setString("Experiencia 0%");
        pbExperiencia.setStringPainted(true);

        javax.swing.GroupLayout panelExperienciaLayout = new javax.swing.GroupLayout(panelExperiencia);
        panelExperiencia.setLayout(panelExperienciaLayout);
        panelExperienciaLayout.setHorizontalGroup(
            panelExperienciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
            .addGroup(panelExperienciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelExperienciaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pbExperiencia, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelExperienciaLayout.setVerticalGroup(
            panelExperienciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
            .addGroup(panelExperienciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelExperienciaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pbExperiencia, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pAtrInferior.add(panelExperiencia);

        etAtaque.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        etAtaque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etAtaque.setText("Ataque min - max =");
        etAtaque.setPreferredSize(new java.awt.Dimension(145, 21));

        ataqueMinMax.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ataqueMinMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ataqueMinMax.setText("0 - 0");
        ataqueMinMax.setPreferredSize(new java.awt.Dimension(60, 21));

        javax.swing.GroupLayout panelAtaqueLayout = new javax.swing.GroupLayout(panelAtaque);
        panelAtaque.setLayout(panelAtaqueLayout);
        panelAtaqueLayout.setHorizontalGroup(
            panelAtaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtaqueLayout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(ataqueMinMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelAtaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAtaqueLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE)))
        );
        panelAtaqueLayout.setVerticalGroup(
            panelAtaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtaqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ataqueMinMax, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelAtaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAtaqueLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etAtaque, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pAtrInferior.add(panelAtaque);

        etDefensa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        etDefensa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etDefensa.setText("Defensa min - max =");
        etDefensa.setPreferredSize(new java.awt.Dimension(145, 21));

        defensaMinMax.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        defensaMinMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        defensaMinMax.setText("0 - 0");
        defensaMinMax.setPreferredSize(new java.awt.Dimension(60, 21));

        javax.swing.GroupLayout panelDefensaLayout = new javax.swing.GroupLayout(panelDefensa);
        panelDefensa.setLayout(panelDefensaLayout);
        panelDefensaLayout.setHorizontalGroup(
            panelDefensaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
            .addGroup(panelDefensaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDefensaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE)))
            .addGroup(panelDefensaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDefensaLayout.createSequentialGroup()
                    .addContainerGap(169, Short.MAX_VALUE)
                    .addComponent(defensaMinMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        panelDefensaLayout.setVerticalGroup(
            panelDefensaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
            .addGroup(panelDefensaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDefensaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etDefensa, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(panelDefensaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDefensaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(defensaMinMax, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pAtrInferior.add(panelDefensa);

        separadorAtributos.setRightComponent(pAtrInferior);

        piAtributos.add(separadorAtributos, java.awt.BorderLayout.CENTER);

        pIzquierda.add(piAtributos);

        separador.setLeftComponent(pIzquierda);

        pDerecha.setPreferredSize(new java.awt.Dimension(649, 598));
        pDerecha.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pdCentroLayout = new javax.swing.GroupLayout(pdCentro);
        pdCentro.setLayout(pdCentroLayout);
        pdCentroLayout.setHorizontalGroup(
            pdCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        pdCentroLayout.setVerticalGroup(
            pdCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        pDerecha.add(pdCentro, java.awt.BorderLayout.CENTER);

        pdSur.setPreferredSize(new java.awt.Dimension(648, 48));
        pdSur.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnSiguienteTurno.setText("Terminar Turno");
        pdSur.add(btnSiguienteTurno);

        pDerecha.add(pdSur, java.awt.BorderLayout.SOUTH);

        separador.setRightComponent(pDerecha);

        add(separador, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ataqueMinMax;
    private javax.swing.JButton btnSiguienteTurno;
    private javax.swing.JLabel defensaMinMax;
    private javax.swing.JLabel etAtaque;
    private javax.swing.JLabel etDefensa;
    private javax.swing.JLabel etFoto;
    private javax.swing.JLabel etNivel;
    private javax.swing.JLabel etNombre;
    private javax.swing.JLabel etOroAcumulado;
    private javax.swing.JLabel etTurnoActual;
    private javax.swing.JPanel pAtrInferior;
    private javax.swing.JPanel pAtrSuperior;
    private javax.swing.JPanel pDerecha;
    private javax.swing.JPanel pIzquierda;
    private javax.swing.JPanel panelAtaque;
    private javax.swing.JPanel panelDefensa;
    private javax.swing.JPanel panelExperiencia;
    private javax.swing.JPanel panelVida;
    private javax.swing.JProgressBar pbExperiencia;
    private javax.swing.JProgressBar pbVida;
    private javax.swing.JPanel pdCentro;
    private javax.swing.JPanel pdSur;
    private javax.swing.JPanel piAtributos;
    private javax.swing.JPanel piFoto;
    private javax.swing.JSplitPane separador;
    private javax.swing.JSplitPane separadorAtributos;
    private javax.swing.JSplitPane separadorFoto;
    // End of variables declaration//GEN-END:variables

    public void addControlador(ActionListener al) {
        btnSiguienteTurno.addActionListener(al);
        btnSiguienteTurno.setActionCommand("terminarTurno");
    }

    public void setAtaqueMinMax(String ataqueMinMax) {
        this.ataqueMinMax.setText(ataqueMinMax);
    }

    public void setDefensaMinMax(String defensaMinMax) {
        this.defensaMinMax.setText(defensaMinMax);
    }

    public void setEtFoto(String foto) {
        if (foto != null) {
            this.etFoto.setIcon(new ImageIcon(foto));
        } else {
            this.etFoto.setText("Imagen no disponible");
        }
    }

    public void setEtNivel(String etNivel) {
        this.etNivel.setText(etNivel);
    }

    public void setEtNombre(String etNombre) {
        this.etNombre.setText("Lord " + etNombre);
    }

    public void setEtOroAcumulado(String etOroAcumulado) {
        this.etOroAcumulado.setText(etOroAcumulado);
    }

    public void setEtTurnoActual(String etTurnoActual) {
        this.etTurnoActual.setText(etTurnoActual);
    }

    public void setPbExperiencia(int max, int valor) {
        this.pbExperiencia.setMaximum(max);
        this.pbExperiencia.setValue(valor);
        this.pbExperiencia.setString("Exp " + valor + " / " + max);
    }

    public void setPbVida(int max, int valor) {
        this.pbVida.setMaximum(max);
        this.pbVida.setValue(valor);
        this.pbVida.setString("Vida " + valor + " / " + max);
    }

    public void setPdCentro(JPanel nuevoPdCentro) {
        pDerecha.remove(pdCentro);
        pdCentro = nuevoPdCentro;
        pDerecha.add(pdCentro, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public JButton getBotonTerminar() {
        return btnSiguienteTurno;
    }
}