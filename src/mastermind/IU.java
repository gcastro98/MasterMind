/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
	public IU() {
		initComponents();
		panel_Visible(jPanel_menu);
		this.jPanel_sombra.setBackground(new Color(0,0,0,75));
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
		this.jLabel_error_iniciar_sesion.setText("Usuario o contraseña incorrectos");
            }
            return usuario;
        }
        
        public void entrenamiento(){
            panel_Visible(jPanel_Partida);
            clave = new Combinacion();
            boolean correcto = false;
            while (!correcto){
                
            }
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
        public void intento(Combinacion combinacion){
            Colour[] intento = new Colour[4];
            intento[0]= (Colour) combo_intento_color_1.getSelectedItem();
            intento[1]= (Colour) combo_intento_color_2.getSelectedItem();
            intento[2]= (Colour) combo_intento_color_3.getSelectedItem();
            intento[3]= (Colour) combo_intento_color_4.getSelectedItem();
            Combinacion combi = new Combinacion(intento);
            if (combinacion.equals(combi)) System.out.println("Has ganado");
            else{
                int posicionado = 0;
                int contenidos = 0;
                for (int i=0;i<4;i++){
                    if (combinacion.contains(intento[i])){
                        contenidos++;
                        if(combinacion.posicionado(intento[i], i)) 
                        posicionado++;
                    }
                }
                lista_intentos.add(resultados(posicionado, contenidos));
            }
        }
        public Color pintar_paneles(String valor){
            //switch 
            throw new RuntimeException("no implementado");
        }
        public Component resultados(int posicionado, int contenidos){
        
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
        resultado_color_1.setBackground(new java.awt.Color(0, 0, 0));
        resultado_color_2.setBackground(new java.awt.Color(0, 0, 0));
        resultado_color_4.setBackground(new java.awt.Color(0, 0, 0));
        resultado_color_3.setBackground(new java.awt.Color(0, 0, 0));

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
            return panel_resultado;
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
        jPanel_menu = new javax.swing.JPanel();
        jPanel_sombra = new javax.swing.JPanel();
        jPanel_Partida = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_intentos = new javax.swing.JList<>();
        panel_intento = new javax.swing.JPanel();
        combo_intento_color_1 = new javax.swing.JComboBox<>();
        combo_intento_color_2 = new javax.swing.JComboBox<>();
        combo_intento_color_3 = new javax.swing.JComboBox<>();
        combo_intento_color_4 = new javax.swing.JComboBox<>();
        panel_intento_color_1 = new javax.swing.JPanel();
        panel_intento_color_2 = new javax.swing.JPanel();
        panel_intento_color_3 = new javax.swing.JPanel();
        panel_intento_color_4 = new javax.swing.JPanel();
        panel_resultado = new javax.swing.JPanel();
        resultado_color_2 = new javax.swing.JPanel();
        resultado_color_3 = new javax.swing.JPanel();
        resultado_color_4 = new javax.swing.JPanel();
        label_resultados_contenido = new javax.swing.JLabel();
        label_resultado_posicionado = new javax.swing.JLabel();
        numero_resultados_contenido = new javax.swing.JLabel();
        numero_resultado_posicionado = new javax.swing.JLabel();
        resultado_color_1 = new javax.swing.JPanel();
        boton_intento_comprobar = new javax.swing.JButton();
        jPanel_menu_inicio = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel_menu_lateral = new javax.swing.JPanel();
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

        jPanel_menu.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_sombra.setPreferredSize(new java.awt.Dimension(1280, 720));

        lista_intentos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lista_intentos);

        panel_intento.setBackground(new java.awt.Color(125, 125, 125));

        combo_intento_color_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_intento_color_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_1ActionPerformed(evt);
            }
        });

        combo_intento_color_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_intento_color_3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_intento_color_4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        panel_resultado.setBackground(new java.awt.Color(125, 125, 0));

        resultado_color_2.setBackground(new java.awt.Color(0, 0, 0));

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

        resultado_color_3.setBackground(new java.awt.Color(0, 0, 0));

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

        resultado_color_4.setBackground(new java.awt.Color(0, 0, 0));

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

        numero_resultados_contenido.setText("jLabel2");

        numero_resultado_posicionado.setText("jLabel2");

        resultado_color_1.setBackground(new java.awt.Color(0, 0, 0));

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
                        .addGap(30, 30, 30)
                        .addComponent(label_resultado_posicionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        boton_intento_comprobar.setText("Comprobar");
        boton_intento_comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_intento_comprobarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_PartidaLayout = new javax.swing.GroupLayout(jPanel_Partida);
        jPanel_Partida.setLayout(jPanel_PartidaLayout);
        jPanel_PartidaLayout.setHorizontalGroup(
            jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PartidaLayout.createSequentialGroup()
                .addContainerGap(424, Short.MAX_VALUE)
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(panel_resultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addComponent(boton_intento_comprobar)
                .addGap(196, 196, 196))
        );
        jPanel_PartidaLayout.setVerticalGroup(
            jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PartidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_intento_comprobar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(panel_resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_sombraLayout = new javax.swing.GroupLayout(jPanel_sombra);
        jPanel_sombra.setLayout(jPanel_sombraLayout);
        jPanel_sombraLayout.setHorizontalGroup(
            jPanel_sombraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_sombraLayout.setVerticalGroup(
            jPanel_sombraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_Partida.getAccessibleContext().setAccessibleName("");

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

        javax.swing.GroupLayout jPanel_menu_lateralLayout = new javax.swing.GroupLayout(jPanel_menu_lateral);
        jPanel_menu_lateral.setLayout(jPanel_menu_lateralLayout);
        jPanel_menu_lateralLayout.setHorizontalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        jPanel_menu_lateralLayout.setVerticalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_intento_color_4ActionPerformed

    private void boton_intento_comprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_intento_comprobarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_intento_comprobarActionPerformed

    private void combo_intento_color_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_intento_color_1ActionPerformed

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
    private javax.swing.JButton jButton_iniciar_sesion;
    private javax.swing.JLabel jLabel_contrasena;
    private javax.swing.JLabel jLabel_error_iniciar_sesion;
    private javax.swing.JLabel jLabel_nombre_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Partida;
    private javax.swing.JPanel jPanel_campos;
    private javax.swing.JPanel jPanel_iniciar_sesion;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_menu_inicio;
    private javax.swing.JPanel jPanel_menu_lateral;
    private javax.swing.JPanel jPanel_sombra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_contrasena;
    private javax.swing.JTextField jTextField_nombre_usuario;
    private javax.swing.JLabel label_resultado_posicionado;
    private javax.swing.JLabel label_resultados_contenido;
    private javax.swing.JList<String> lista_intentos;
    private javax.swing.JLabel numero_resultado_posicionado;
    private javax.swing.JLabel numero_resultados_contenido;
    private javax.swing.JPanel panel_intento;
    private javax.swing.JPanel panel_intento_color_1;
    private javax.swing.JPanel panel_intento_color_2;
    private javax.swing.JPanel panel_intento_color_3;
    private javax.swing.JPanel panel_intento_color_4;
    private javax.swing.JPanel panel_resultado;
    private javax.swing.JPanel resultado_color_1;
    private javax.swing.JPanel resultado_color_2;
    private javax.swing.JPanel resultado_color_3;
    private javax.swing.JPanel resultado_color_4;
    // End of variables declaration//GEN-END:variables
}
