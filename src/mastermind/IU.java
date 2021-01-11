/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;


import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author amora
 */
public class IU extends javax.swing.JFrame {

	/**
	 * Creates new form IU
	 */
	private Usuario usuario_actual;
        private Combinacion clave;
        private Partida partida;
        private boolean comprobar;
        
        
	public IU() {
            initComponents();
            inicio();
            
                //this.jPanel_list.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            this.jPanel_sombra.setBackground(new Color(0,0,0,75));
	}
        
        public void inicio(){
            panel_Visible(jPanel_Partida);
            panel_intento_color_1.setBackground(pintar_paneles(((String) combo_intento_color_1.getSelectedItem())));
            panel_intento_color_2.setBackground(pintar_paneles(((String) combo_intento_color_2.getSelectedItem())));
            panel_intento_color_3.setBackground(pintar_paneles(((String) combo_intento_color_3.getSelectedItem())));
            panel_intento_color_4.setBackground(pintar_paneles(((String) combo_intento_color_4.getSelectedItem())));
        }
        public void panel_Visible(javax.swing.JPanel jpanel){
            this.jPanel_menu.setVisible(false);
            this.jPanel_iniciar_sesion.setVisible(false);
            this.jPanel_menu_lateral.setVisible(false);
            this.jPanel_sombra.setVisible(false);
            this.jPanel_Partida.setVisible(false);
            jpanel.setVisible(true);
	}
        
        public Usuario sign_in(){
            panel_Visible(jPanel_iniciar_sesion);
            Usuario usuario = null;
            this.jLabel_error_iniciar_sesion.setText("");
            while (usuario == null)
            {
                usuario = Login.sign_in(this.jTextField_nombre_usuario.getText(), this.jTextField_contrasena.getText());
                if(usuario == null) this.jLabel_error_iniciar_sesion.setText("Usuario o contraseña incorrectos");
            }
            return usuario;
        }
        
        public void entrenamiento(){
            //iniciar_partida();
            System.out.println("Entro");
            panel_Visible(this.jPanel_Partida);
            System.out.println(jPanel_Partida.getVisibleRect());
            this.jPanel_menu.setVisible(false);
            this.jPanel_Partida.setVisible(true);
            System.out.println("Panel Visible");
            clave = new Combinacion();
            /*System.out.println("Combinacion Creada");
            boolean correcto = false;
            int intentos = 0;
            while (!correcto){
                if(comprobar){
                    System.out.println("Entra en el bucle");
                    intentos++;
                    comprobar = false;
                    correcto = intento(clave);
                }
            }*/
            
        }
       
        public void iniciar_partida(){
           
        }
        public void crearPartida(){
            Usuario contrincante = sign_in();
            this.partida = new Partida(this.usuario_actual, contrincante);
            Combinacion clave_local = leerClave();
            Combinacion clave_visitante = leerClave();
            Ronda ronda = new Ronda(usuario_actual, contrincante, clave_local, clave_visitante);
            jugarRonda(ronda);
            
           
        }
        public void jugarRonda(Ronda ronda){
            
        }
        public Combinacion leerClave(){
            throw new RuntimeException("No implementado");
        }
        public boolean intento(){
            Colour[] intento = new Colour[4];
            intento[0]= Colour.valueOf((String) combo_intento_color_1.getSelectedItem());
            intento[1]= Colour.valueOf((String) combo_intento_color_2.getSelectedItem());
            intento[2]= Colour.valueOf((String) combo_intento_color_3.getSelectedItem());
            intento[3]= Colour.valueOf((String) combo_intento_color_4.getSelectedItem());
            Combinacion combi = new Combinacion(intento);
            if (clave.equals(combi)) return true;
            else{
                int posicionados = 0;
                int contenidos = 0;
                for (int i=0;i<4;i++){
                    if (clave.contains(intento[i])){
                        contenidos++;
                        if(clave.posicionado(intento[i], i)) 
                        posicionados++;
                    }
                }
               
                resultados(posicionados, contenidos);
                /*javax.swing.GroupLayout jPanel_listLayou = jPanel_list.getLayout();
        jPanel_list.setLayout(jPanel_listLayou);
        jPanel_listLayou.setHorizontalGroup(
            jPanel_listLayou.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_dentro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_listLayou.setVerticalGroup(
            jPanel_listLayou.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_listLayou.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panel_dentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
                componente.setVisible(true);
                //jPanel_Partida.add(componente, 0);*/
                
                System.out.println(jPanel_list.getComponentCount());
                System.out.println(contenidos+","+posicionados);
                jPanel_list.revalidate();
                jPanel_list.repaint();
                
            }
            return false;
        }
        public Color pintar_paneles(String valor){
            switch (valor) {
                case "Blanco" : return Color.WHITE;
                case "Azul" : return Color.BLUE;
                case "Marron" : return new Color(141, 73, 37,100);
                case "Negro" : return Color.BLACK;
                case "Rojo" : return Color.RED;
                case "Verde" : return Color.GREEN;
                default : return Color.WHITE;
            } 
        }
        public void visible(javax.swing.JPanel panel){
            Component[] lista = panel.getComponents();
            for(int i=0;i<lista.length;i++) lista[i].setVisible(true);
        }
        public void resultados(int posicionado, int contenidos){
        
        javax.swing.JPanel panel_resultado = new javax.swing.JPanel();
        javax.swing.JPanel resultado_color_1 = new javax.swing.JPanel();
        javax.swing.JPanel resultado_color_2 = new javax.swing.JPanel();
        javax.swing.JPanel resultado_color_3 = new javax.swing.JPanel();
        javax.swing.JPanel resultado_color_4 = new javax.swing.JPanel();
        javax.swing.JLabel label_resultados_contenido = new javax.swing.JLabel();
        javax.swing.JLabel label_resultado_posicionado = new javax.swing.JLabel();
        javax.swing.JLabel numero_resultados_contenido = new javax.swing.JLabel();
        javax.swing.JLabel numero_resultado_posicionado = new javax.swing.JLabel();
  
        
        panel_resultado.setBackground(new java.awt.Color(125, 125, 0));
        resultado_color_1.setBackground(pintar_paneles(((String) combo_intento_color_1.getSelectedItem())));
        resultado_color_2.setBackground(pintar_paneles(((String) combo_intento_color_2.getSelectedItem())));
        resultado_color_3.setBackground(pintar_paneles(((String) combo_intento_color_3.getSelectedItem())));
        resultado_color_4.setBackground(pintar_paneles(((String) combo_intento_color_4.getSelectedItem())));

        javax.swing.GroupLayout resultado_color_2Layout = new javax.swing.GroupLayout(resultado_color_2);
        resultado_color_2.setLayout(resultado_color_2Layout);
        resultado_color_2Layout.setHorizontalGroup(
            resultado_color_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        resultado_color_2Layout.setVerticalGroup(
            resultado_color_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout resultado_color_3Layout = new javax.swing.GroupLayout(resultado_color_3);
        resultado_color_3.setLayout(resultado_color_3Layout);
        resultado_color_3Layout.setHorizontalGroup(
            resultado_color_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        resultado_color_3Layout.setVerticalGroup(
            resultado_color_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout resultado_color_4Layout = new javax.swing.GroupLayout(resultado_color_4);
        resultado_color_4.setLayout(resultado_color_4Layout);
        resultado_color_4Layout.setHorizontalGroup(
            resultado_color_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        resultado_color_4Layout.setVerticalGroup(
            resultado_color_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        label_resultados_contenido.setText("Contenidos :");

        label_resultado_posicionado.setText("Posicionados: ");

        numero_resultados_contenido.setText(Integer.toString(contenidos));

        numero_resultado_posicionado.setText(Integer.toString(posicionado));

        javax.swing.GroupLayout resultado_color_1Layout = new javax.swing.GroupLayout(resultado_color_1);
        resultado_color_1.setLayout(resultado_color_1Layout);
        resultado_color_1Layout.setHorizontalGroup(
            resultado_color_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        resultado_color_1Layout.setVerticalGroup(
            resultado_color_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_resultadoLayout = new javax.swing.GroupLayout(panel_resultado);
        panel_resultado.setLayout(panel_resultadoLayout);
        panel_resultadoLayout.setHorizontalGroup(
            panel_resultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_resultadoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(resultado_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultado_color_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultado_color_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultado_color_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel_resultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_resultadoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(label_resultados_contenido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numero_resultados_contenido))
                    .addGroup(panel_resultadoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(label_resultado_posicionado)
                        .addGap(18, 18, 18)
                        .addComponent(numero_resultado_posicionado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_resultadoLayout.setVerticalGroup(
            panel_resultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_resultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_resultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_resultadoLayout.createSequentialGroup()
                        .addGroup(panel_resultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_resultados_contenido)
                            .addComponent(numero_resultados_contenido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(panel_resultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_resultado_posicionado)
                            .addComponent(numero_resultado_posicionado)))
                    .addComponent(resultado_color_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultado_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultado_color_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultado_color_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        /*System.out.println(panel_resultado.getComponentCount());
        javax.swing.JPanel componente = (javax.swing.JPanel) jPanel_list.add(panel_resultado);
        componente.revalidate();
        componente.repaint();
        componente.setVisible(true);
        componente.setOpaque(true);
        System.out.println(componente.isVisible());
        jPanel_list.revalidate();
        jPanel_list.repaint();
        visible();
        
        panel_dentro.add(new JButton("Hola"));
        panel_dentro.repaint();
        panel_dentro.revalidate();
        panel_dentro.setVisible(true);
        jPanel_Partida.repaint();
        jPanel_Partida.revalidate();
        this.repaint();
        this.revalidate();
        this.pack();*/
        
        Component componente = this.jPanel_list.add(panel_resultado, 0);
        visible((JPanel) componente);
        visible(jPanel_list);
        visible(jPanel_Partida);
        this.jPanel_list.revalidate();
        this.jPanel_list.repaint();
        componente.setVisible(true);
        this.repaint();
        this.revalidate();
      
        
            //return panel_resultado;
        }
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel_Partida = new javax.swing.JPanel();
        panel_intento = new javax.swing.JPanel();
        combo_intento_color_1 = new javax.swing.JComboBox<>();
        combo_intento_color_2 = new javax.swing.JComboBox<>();
        combo_intento_color_3 = new javax.swing.JComboBox<>();
        combo_intento_color_4 = new javax.swing.JComboBox<>();
        panel_intento_color_1 = new javax.swing.JPanel();
        panel_intento_color_2 = new javax.swing.JPanel();
        panel_intento_color_3 = new javax.swing.JPanel();
        panel_intento_color_4 = new javax.swing.JPanel();
        boton_intento_comprobar = new javax.swing.JButton();
        panel_fuera = new javax.swing.JPanel();
        panel_dentro = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid = new GridLayout(0,1);
        jPanel_list = new javax.swing.JPanel(this.grid);
        jPanel_menu = new javax.swing.JPanel();
        jPanel_sombra = new javax.swing.JPanel();
        jPanel_menu_inicio = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel_menu_lateral = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel_iniciar_sesion = new javax.swing.JPanel();
        jPanel_campos = new javax.swing.JPanel();
        jLabel_nombre_usuario = new javax.swing.JLabel();
        jTextField_nombre_usuario = new javax.swing.JTextField();
        jLabel_contrasena = new javax.swing.JLabel();
        jTextField_contrasena = new javax.swing.JTextField();
        jButton_iniciar_sesion = new javax.swing.JButton();
        jLabel_error_iniciar_sesion = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_intento.setBackground(new java.awt.Color(125, 125, 125));

        combo_intento_color_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_1ActionPerformed(evt);
            }
        });

        combo_intento_color_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_2ActionPerformed(evt);
            }
        });

        combo_intento_color_3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_3ActionPerformed(evt);
            }
        });

        combo_intento_color_4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_4ActionPerformed(evt);
            }
        });

        panel_intento_color_1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_1Layout = new javax.swing.GroupLayout(panel_intento_color_1);
        panel_intento_color_1.setLayout(panel_intento_color_1Layout);
        panel_intento_color_1Layout.setHorizontalGroup(
            panel_intento_color_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        panel_intento_color_1Layout.setVerticalGroup(
            panel_intento_color_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panel_intento_color_2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_2Layout = new javax.swing.GroupLayout(panel_intento_color_2);
        panel_intento_color_2.setLayout(panel_intento_color_2Layout);
        panel_intento_color_2Layout.setHorizontalGroup(
            panel_intento_color_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_intento_color_2Layout.setVerticalGroup(
            panel_intento_color_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panel_intento_color_3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_3Layout = new javax.swing.GroupLayout(panel_intento_color_3);
        panel_intento_color_3.setLayout(panel_intento_color_3Layout);
        panel_intento_color_3Layout.setHorizontalGroup(
            panel_intento_color_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_intento_color_3Layout.setVerticalGroup(
            panel_intento_color_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panel_intento_color_4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_4Layout = new javax.swing.GroupLayout(panel_intento_color_4);
        panel_intento_color_4.setLayout(panel_intento_color_4Layout);
        panel_intento_color_4Layout.setHorizontalGroup(
            panel_intento_color_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        panel_intento_color_4Layout.setVerticalGroup(
            panel_intento_color_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_intentoLayout = new javax.swing.GroupLayout(panel_intento);
        panel_intento.setLayout(panel_intentoLayout);
        panel_intentoLayout.setHorizontalGroup(
            panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intentoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_intento_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_color_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_intento_color_2, 0, 81, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_color_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_intento_color_3, 0, 81, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento_color_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_intento_color_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        panel_intentoLayout.setVerticalGroup(
            panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_intentoLayout.createSequentialGroup()
                        .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_intento_color_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_intentoLayout.createSequentialGroup()
                                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel_intentoLayout.createSequentialGroup()
                                        .addComponent(panel_intento_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))
                                    .addGroup(panel_intentoLayout.createSequentialGroup()
                                        .addComponent(panel_intento_color_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_intentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(combo_intento_color_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(combo_intento_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(combo_intento_color_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_intentoLayout.createSequentialGroup()
                        .addComponent(panel_intento_color_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_intento_color_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        boton_intento_comprobar.setText("Comprobar");
        boton_intento_comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_intento_comprobarActionPerformed(evt);
            }
        });

        panel_fuera.setBackground(new java.awt.Color(240, 240, 0));

        javax.swing.GroupLayout panel_dentroLayout = new javax.swing.GroupLayout(panel_dentro);
        panel_dentro.setLayout(panel_dentroLayout);
        panel_dentroLayout.setHorizontalGroup(
            panel_dentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        panel_dentroLayout.setVerticalGroup(
            panel_dentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_fueraLayout = new javax.swing.GroupLayout(panel_fuera);
        panel_fuera.setLayout(panel_fueraLayout);
        panel_fueraLayout.setHorizontalGroup(
            panel_fueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_dentro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_fueraLayout.setVerticalGroup(
            panel_fueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_fueraLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panel_dentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel_list.setBackground(new java.awt.Color(240, 0, 240));
        jPanel_list.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel_listLayout = new javax.swing.GroupLayout(jPanel_list);
        jPanel_list.setLayout(jPanel_listLayout);
        jPanel_listLayout.setHorizontalGroup(
            jPanel_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_listLayout.setVerticalGroup(
            jPanel_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel_list);

        javax.swing.GroupLayout jPanel_PartidaLayout = new javax.swing.GroupLayout(jPanel_Partida);
        jPanel_Partida.setLayout(jPanel_PartidaLayout);
        jPanel_PartidaLayout.setHorizontalGroup(
            jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PartidaLayout.createSequentialGroup()
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton2))
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(panel_fuera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(37, 37, 37)
                .addComponent(boton_intento_comprobar)
                .addGap(194, 194, 194))
        );
        jPanel_PartidaLayout.setVerticalGroup(
            jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jButton2)
                        .addGap(67, 67, 67)
                        .addComponent(panel_fuera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PartidaLayout.createSequentialGroup()
                        .addComponent(boton_intento_comprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(139, 139, 139))
        );

        jPanel_menu.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_sombra.setPreferredSize(new java.awt.Dimension(1280, 720));

        javax.swing.GroupLayout jPanel_sombraLayout = new javax.swing.GroupLayout(jPanel_sombra);
        jPanel_sombra.setLayout(jPanel_sombraLayout);
        jPanel_sombraLayout.setHorizontalGroup(
            jPanel_sombraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel_sombraLayout.setVerticalGroup(
            jPanel_sombraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jPanel_menu_inicio.setPreferredSize(new java.awt.Dimension(1280, 720));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icon/round_menu_black_18dp.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setMaximumSize(new java.awt.Dimension(77, 77));
        jButton1.setMinimumSize(new java.awt.Dimension(77, 77));
        jButton1.setPreferredSize(new java.awt.Dimension(77, 77));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menu_inicioLayout = new javax.swing.GroupLayout(jPanel_menu_inicio);
        jPanel_menu_inicio.setLayout(jPanel_menu_inicioLayout);
        jPanel_menu_inicioLayout.setHorizontalGroup(
            jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 932, Short.MAX_VALUE))
        );
        jPanel_menu_inicioLayout.setVerticalGroup(
            jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel_menu_lateral.setPreferredSize(new java.awt.Dimension(500, 720));

        jButton3.setText("Entrenamiento");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menu_lateralLayout = new javax.swing.GroupLayout(jPanel_menu_lateral);
        jPanel_menu_lateral.setLayout(jPanel_menu_lateralLayout);
        jPanel_menu_lateralLayout.setHorizontalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jButton3)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel_menu_lateralLayout.setVerticalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jButton3)
                .addContainerGap(448, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jPanel_menu_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_menu_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
            .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_menuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel_sombra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu_lateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_menu_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_menuLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel_sombra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_iniciar_sesion.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_nombre_usuario.setText("Nombre de usuario :");

        jTextField_nombre_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_usuarioActionPerformed(evt);
            }
        });

        jLabel_contrasena.setText("Contraseña :");

        jButton_iniciar_sesion.setText("Entrar");
        jButton_iniciar_sesion.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_iniciar_sesion.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_iniciar_sesionActionPerformed(evt);
            }
        });

        jLabel_error_iniciar_sesion.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel_camposLayout = new javax.swing.GroupLayout(jPanel_campos);
        jPanel_campos.setLayout(jPanel_camposLayout);
        jPanel_camposLayout.setHorizontalGroup(
            jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_camposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel_error_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addComponent(jLabel_contrasena, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField_contrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_nombre_usuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_camposLayout.setVerticalGroup(
            jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_nombre_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_contrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_error_iniciar_sesion))
        );

        javax.swing.GroupLayout jPanel_iniciar_sesionLayout = new javax.swing.GroupLayout(jPanel_iniciar_sesion);
        jPanel_iniciar_sesion.setLayout(jPanel_iniciar_sesionLayout);
        jPanel_iniciar_sesionLayout.setHorizontalGroup(
            jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_iniciar_sesionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_iniciar_sesionLayout.setVerticalGroup(
            jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_iniciar_sesionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel_Partida.getAccessibleContext().setAccessibleName("jPanel_Partida");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_iniciar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_iniciar_sesionActionPerformed
        this.usuario_actual = sign_in();
		
    }//GEN-LAST:event_jButton_iniciar_sesionActionPerformed

    private void jTextField_nombre_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre_usuarioActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombre_usuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		if (this.jPanel_menu_lateral.isVisible())
		{
			this.jPanel_menu_lateral.setVisible(false);
			this.jPanel_sombra.setVisible(false);
		}
		else
		{			   
			this.jPanel_menu_lateral.setVisible(true);
			this.jPanel_sombra.setVisible(true);
		}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void combo_intento_color_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_4ActionPerformed
        panel_intento_color_4.setBackground(pintar_paneles(((String) combo_intento_color_4.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_4ActionPerformed

    private void boton_intento_comprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_intento_comprobarActionPerformed
        intento();
        this.panel_dentro.add(new JButton("Hola"));
        panel_dentro.repaint();
        panel_dentro.revalidate();
    }//GEN-LAST:event_boton_intento_comprobarActionPerformed

    private void combo_intento_color_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_1ActionPerformed
        panel_intento_color_1.setBackground(pintar_paneles(((String) combo_intento_color_1.getSelectedItem())));        
    }//GEN-LAST:event_combo_intento_color_1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      entrenamiento();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void combo_intento_color_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_2ActionPerformed
        panel_intento_color_2.setBackground(pintar_paneles(((String) combo_intento_color_2.getSelectedItem())));    
    }//GEN-LAST:event_combo_intento_color_2ActionPerformed

    private void combo_intento_color_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_3ActionPerformed
        panel_intento_color_3.setBackground(pintar_paneles(((String) combo_intento_color_3.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        entrenamiento();
    }//GEN-LAST:event_jButton2ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IU().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_intento_comprobar;
    private javax.swing.JComboBox<String> combo_intento_color_1;
    private javax.swing.JComboBox<String> combo_intento_color_2;
    private javax.swing.JComboBox<String> combo_intento_color_3;
    private javax.swing.JComboBox<String> combo_intento_color_4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_iniciar_sesion;
    private javax.swing.JLabel jLabel_contrasena;
    private javax.swing.JLabel jLabel_error_iniciar_sesion;
    private javax.swing.JLabel jLabel_nombre_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Partida;
    private javax.swing.JPanel jPanel_campos;
    private javax.swing.JPanel jPanel_iniciar_sesion;
    private javax.swing.JPanel jPanel_list;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_menu_inicio;
    private javax.swing.JPanel jPanel_menu_lateral;
    private javax.swing.JPanel jPanel_sombra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_contrasena;
    private javax.swing.JTextField jTextField_nombre_usuario;
    private javax.swing.JPanel panel_dentro;
    private javax.swing.JPanel panel_fuera;
    private javax.swing.JPanel panel_intento;
    private javax.swing.JPanel panel_intento_color_1;
    private javax.swing.JPanel panel_intento_color_2;
    private javax.swing.JPanel panel_intento_color_3;
    private javax.swing.JPanel panel_intento_color_4;
    // End of variables declaration//GEN-END:variables
    private GridLayout grid;
}
