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
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        private Ronda ronda;
        private static int rondas = 3;
        private static int intentos = 10;
        
        
	public IU() {
            initComponents();
            inicio();
            panel_Visible(jPanel_iniciar_sesion);
                //this.jPanel_list.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            this.jPanel_sombra.setBackground(new Color(0,0,0,75));
	}
        
        public void inicio(){
            panel_Visible(jPanel_Partida);
            panel_intento_color_1.setBackground(pintar_paneles(((String) combo_intento_color_1.getSelectedItem())));
            panel_intento_color_2.setBackground(pintar_paneles(((String) combo_intento_color_2.getSelectedItem())));
            panel_intento_color_3.setBackground(pintar_paneles(((String) combo_intento_color_3.getSelectedItem())));
            panel_intento_color_4.setBackground(pintar_paneles(((String) combo_intento_color_4.getSelectedItem())));
            
            this.jLabel_error_iniciar_sesion.setText("");

            jPanel_list.setLayout(new BoxLayout(jPanel_list, BoxLayout.Y_AXIS));
            jScroll_list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScroll_list.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            contenido_intento.setText("0");
            contenido_puntuacion.setText("0");
           
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
            Usuario usuario = Login.sign_in(this.jTextField_nombre_usuario.getText(), this.jTextField_contrasena.getText());
            if(usuario == null){
                this.jLabel_error_iniciar_sesion.setText("Usuario o contraseña incorrectos");
                return null;
            }else{
                label_bienvenido.setText("Bienvenido, "+jTextField_nombre_usuario.getText());
                panel_Visible(jPanel_menu);
                return usuario;
            }
        }
        
        public Usuario registro(){
            Usuario user = Login.registro(this.jTextField_nombre_usuario.getText(), this.jTextField_contrasena.getText());
            panel_Visible(jPanel_menu);
            label_bienvenido.setText("Bienvenido, "+jTextField_nombre_usuario.getText());
            return user;
        }
        
        
        
        
        
        
        /*
        *Hacer algo cuando ganas
        *Revisar el tema de posicionados y contenidos
        *Cambiar colores
        *
        */
        public void entrenamiento(){
            contenido_intento.setText("0");
            contenido_puntuacion.setText("0");
            panel_Visible(this.jPanel_Partida);
            clave = new Combinacion();
            jPanel_list.removeAll();
        }
       
      
        
        
        //Cambiar nombre de variable de botones menu
        //Estetica
        
        
        
        
        /**
         * Crear partida 
         *  Añadir el tema de los popups
         *  Ver como gestionar el tema de las rondas
         *  Tema puntuacion
         */
        public void crearPartida(){
            Usuario contrincante = sign_in();
            this.partida = new Partida(this.usuario_actual, contrincante);
            Combinacion clave_local = leerClave();
            Combinacion clave_visitante = leerClave();
            Ronda ronda = new Ronda(usuario_actual, contrincante, clave_local, clave_visitante);
            jugarRonda(ronda);
            
           
        }
        
        
        
        
        //Rellenar codigo
        public void jugarRonda(Ronda ronda){
            
        }
        
        
        
        
        //Rellenar codigo
        //panel_Intento
        public Combinacion leerClave(){
            throw new RuntimeException("No implementado");
        }
        
        
        
        
        //Rellenar ajustes
        public void ventana_ajustes(){
            throw new RuntimeException("No implementado");
        }
        
        
        
        
        
        
        
        
        
        //ADRIIIIIIIIIIIIIIIIII LAS CLASIFICACIOOOOOOOOOOOOONEEEEEEES!!!!!!!!!!!
        public void clasificaciones(){}
        
        
        
        
        
        
        
        
        
        
        
        
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
                
            }
            return false;
        }
        public Color pintar_paneles(String valor){
            switch (valor) {
                case "Blanco" : return Color.WHITE;
                case "Azul" : return Color.BLUE;
                case "Marron" : return new Color(141, 73, 37);
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
        
        
        panel_resultado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        panel_resultado.setMaximumSize(new Dimension(562,75));
        int intent = Integer.parseInt(contenido_intento.getText());
        contenido_intento.setText(Integer.toString(intent+1)); 
        Component componente = this.jPanel_list.add(panel_resultado, intent);
        LayoutManager f = jPanel_list.getLayout();
        f.addLayoutComponent("componente", componente);
        jPanel_list.setLayout(f);
        jPanel_list.revalidate();
        jPanel_list.repaint();
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
        panel_puntuaciones = new javax.swing.JPanel();
        label_intento = new javax.swing.JLabel();
        contenido_intento = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        contenido_puntuacion = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScroll_list = new javax.swing.JScrollPane();
        jPanel_list = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel_menu = new javax.swing.JPanel();
        jPanel_sombra = new javax.swing.JPanel();
        jPanel_menu_inicio = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        label_juego = new javax.swing.JLabel();
        label_bienvenido = new javax.swing.JLabel();
        jPanel_menu_lateral = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbutton_newgame = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel_iniciar_sesion = new javax.swing.JPanel();
        jPanel_campos = new javax.swing.JPanel();
        jLabel_nombre_usuario = new javax.swing.JLabel();
        jTextField_nombre_usuario = new javax.swing.JTextField();
        jLabel_contrasena = new javax.swing.JLabel();
        jTextField_contrasena = new javax.swing.JTextField();
        jButton_iniciar_sesion = new javax.swing.JButton();
        jLabel_error_iniciar_sesion = new javax.swing.JLabel();
        jButton_registro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

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
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_Partida.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel_Partida.setMinimumSize(new java.awt.Dimension(1280, 720));

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
                .addContainerGap(83, Short.MAX_VALUE))
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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        boton_intento_comprobar.setText("Comprobar");
        boton_intento_comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_intento_comprobarActionPerformed(evt);
            }
        });

        panel_puntuaciones.setBackground(new java.awt.Color(240, 240, 0));
        panel_puntuaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        label_intento.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        label_intento.setText("Intento:");

        contenido_intento.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_intento.setText("0");

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        jLabel1.setText("Puntuacion:");

        contenido_puntuacion.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_puntuacion.setText("0");

        javax.swing.GroupLayout panel_puntuacionesLayout = new javax.swing.GroupLayout(panel_puntuaciones);
        panel_puntuaciones.setLayout(panel_puntuacionesLayout);
        panel_puntuacionesLayout.setHorizontalGroup(
            panel_puntuacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_puntuacionesLayout.createSequentialGroup()
                .addGroup(panel_puntuacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_puntuacionesLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(label_intento))
                    .addGroup(panel_puntuacionesLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addGroup(panel_puntuacionesLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(contenido_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_puntuacionesLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(contenido_intento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        panel_puntuacionesLayout.setVerticalGroup(
            panel_puntuacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_puntuacionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_intento)
                .addGap(4, 4, 4)
                .addComponent(contenido_intento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(contenido_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("Reiniciar entrenamiento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel_list.setBackground(new java.awt.Color(240, 240, 240));
        jPanel_list.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel_listLayout = new javax.swing.GroupLayout(jPanel_list);
        jPanel_list.setLayout(jPanel_listLayout);
        jPanel_listLayout.setHorizontalGroup(
            jPanel_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_listLayout.setVerticalGroup(
            jPanel_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );

        jScroll_list.setViewportView(jPanel_list);

        jButton7.setText("Volver");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_PartidaLayout = new javax.swing.GroupLayout(jPanel_Partida);
        jPanel_Partida.setLayout(jPanel_PartidaLayout);
        jPanel_PartidaLayout.setHorizontalGroup(
            jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PartidaLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScroll_list))
                .addGap(112, 112, 112)
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel_puntuaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(boton_intento_comprobar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
        );
        jPanel_PartidaLayout.setVerticalGroup(
            jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScroll_list, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(panel_intento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(113, Short.MAX_VALUE))
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addComponent(panel_puntuaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(boton_intento_comprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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

        label_juego.setFont(new java.awt.Font("Showcard Gothic", 1, 48)); // NOI18N
        label_juego.setText("MasterMind");

        label_bienvenido.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        label_bienvenido.setText("Bienvenido,");

        javax.swing.GroupLayout jPanel_menu_inicioLayout = new javax.swing.GroupLayout(jPanel_menu_inicio);
        jPanel_menu_inicio.setLayout(jPanel_menu_inicioLayout);
        jPanel_menu_inicioLayout.setHorizontalGroup(
            jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400)
                .addComponent(label_juego)
                .addGap(0, 192, Short.MAX_VALUE))
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addGap(558, 558, 558)
                .addComponent(label_bienvenido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_menu_inicioLayout.setVerticalGroup(
            jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addGroup(jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_juego)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(label_bienvenido)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel_menu_lateral.setPreferredSize(new java.awt.Dimension(500, 720));

        jButton3.setText("Entrenamiento");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel2.setText("MENU");

        jbutton_newgame.setText("Nueva Partida");
        jbutton_newgame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbutton_newgame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton_newgameActionPerformed(evt);
            }
        });

        jButton5.setText("Clasificación");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton6.setText("Ajustes");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menu_lateralLayout = new javax.swing.GroupLayout(jPanel_menu_lateral);
        jPanel_menu_lateral.setLayout(jPanel_menu_lateralLayout);
        jPanel_menu_lateralLayout.setHorizontalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menu_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbutton_newgame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel_menu_lateralLayout.setVerticalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jbutton_newgame, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
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
            .addComponent(jPanel_menu_lateral, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
            .addComponent(jPanel_menu_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
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

        jButton_iniciar_sesion.setText("Iniciar sesión");
        jButton_iniciar_sesion.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_iniciar_sesion.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_iniciar_sesionActionPerformed(evt);
            }
        });

        jLabel_error_iniciar_sesion.setForeground(new java.awt.Color(255, 0, 0));

        jButton_registro.setText("Registrarse");
        jButton_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_camposLayout = new javax.swing.GroupLayout(jPanel_campos);
        jPanel_campos.setLayout(jPanel_camposLayout);
        jPanel_camposLayout.setHorizontalGroup(
            jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_camposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton_registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_error_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(jLabel_contrasena, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_contrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel_nombre_usuario)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_camposLayout.setVerticalGroup(
            jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_nombre_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_contrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_registro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel_error_iniciar_sesion))
        );

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 1, 48)); // NOI18N
        jLabel3.setText("MasterMind");

        javax.swing.GroupLayout jPanel_iniciar_sesionLayout = new javax.swing.GroupLayout(jPanel_iniciar_sesion);
        jPanel_iniciar_sesion.setLayout(jPanel_iniciar_sesionLayout);
        jPanel_iniciar_sesionLayout.setHorizontalGroup(
            jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_iniciar_sesionLayout.createSequentialGroup()
                .addContainerGap(490, Short.MAX_VALUE)
                .addGroup(jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_iniciar_sesionLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(470, 470, 470))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_iniciar_sesionLayout.createSequentialGroup()
                        .addComponent(jPanel_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(506, 506, 506))))
        );
        jPanel_iniciar_sesionLayout.setVerticalGroup(
            jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_iniciar_sesionLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel3)
                .addGap(96, 96, 96)
                .addComponent(jPanel_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jbutton_newgameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton_newgameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbutton_newgameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registroActionPerformed
        this.usuario_actual = registro();
    }//GEN-LAST:event_jButton_registroActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        panel_Visible(jPanel_menu);
    }//GEN-LAST:event_jButton7ActionPerformed

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
    private javax.swing.JLabel contenido_intento;
    private javax.swing.JLabel contenido_puntuacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton_iniciar_sesion;
    private javax.swing.JButton jButton_registro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScroll_list;
    private javax.swing.JTextField jTextField_contrasena;
    private javax.swing.JTextField jTextField_nombre_usuario;
    private javax.swing.JButton jbutton_newgame;
    private javax.swing.JLabel label_bienvenido;
    private javax.swing.JLabel label_intento;
    private javax.swing.JLabel label_juego;
    private javax.swing.JPanel panel_intento;
    private javax.swing.JPanel panel_intento_color_1;
    private javax.swing.JPanel panel_intento_color_2;
    private javax.swing.JPanel panel_intento_color_3;
    private javax.swing.JPanel panel_intento_color_4;
    private javax.swing.JPanel panel_puntuaciones;
    // End of variables declaration//GEN-END:variables
   
}
