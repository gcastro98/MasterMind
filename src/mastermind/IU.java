/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 *
 * @author amora
 */
public class IU extends javax.swing.JFrame {

    /**
     * Creates new form IU
     */
    private Usuario usuario_actual;
    private Usuario contrincante;
    private Combinacion clave_usuario_actual;
    private Combinacion clave_contrincante;
    private Partida partida;
    private Ronda ronda;
    private static int rondas = 3;
    private static int intentos = 10;
    private boolean entrenamiento = false;
    private boolean correcto = false;

    public IU() {
        initComponents();
        inicio();
        panel_visible(jPanel_iniciar_sesion);
        
    }

    public void inicio() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					MasterMind.guardar_datos();
				} catch (IOException ex) {
					Logger.getLogger(IU.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
        this.setLocationRelativeTo(null); 
        usuario_actual = null;
        contrincante = null;
        clave_usuario_actual = null;
        clave_contrincante = null;
        partida = null;
        ronda = null;
        panel_visible(jPanel_partida_usuario);
        jPanel_rondas_usuario.setVisible(true);
        reiniciar_paneles_combos();
        boton_reiniciar_usuario.setVisible(false);
        this.jLabel_error_iniciar_sesion.setText("");
        this.jLabel_popup_error_iniciar_sesion.setText("");
		this.jFrame_popup.setSize(new Dimension(460, 360));
		this.jButton_menu_ajustes.setVisible(false);

        jPanel_list_usuario.setLayout(new BoxLayout(jPanel_list_usuario, BoxLayout.Y_AXIS));
        jScroll_list_usuario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_list_usuario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //contenido_intento_usuario.setText("0");
        jPanel_list_contrincante.setLayout(new BoxLayout(jPanel_list_contrincante, BoxLayout.Y_AXIS));
        jScroll_list_contrincante.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScroll_list_contrincante.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contenido_intento_contrincante.setText("0");
        //contenido_puntuacion_usuario.setText("0");
		this.jPanel_clasificacion.setLayout(new BoxLayout(jPanel_clasificacion, BoxLayout.Y_AXIS));
        this.jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        

    }

    public void reiniciar_paneles_combos() {
        jPanel_list_usuario.removeAll();
        jPanel_list_contrincante.removeAll();
        panel_intento_color_contrincante_1.setBackground(pintar_paneles("blanco"));
        panel_intento_color_contrincante_2.setBackground(pintar_paneles("blanco"));
        panel_intento_color_contrincante_3.setBackground(pintar_paneles("blanco"));
        panel_intento_color_contrincante_4.setBackground(pintar_paneles("blanco"));
        jPanel_popup_color_1.setBackground(pintar_paneles("blanco"));
        jPanel_popup_color_2.setBackground(pintar_paneles("blanco"));
        jPanel_popup_color_3.setBackground(pintar_paneles("blanco"));
        jPanel_popup_color_4.setBackground(pintar_paneles("blanco"));
        panel_intento_color_usuario_1.setBackground(pintar_paneles("blanco"));
        panel_intento_color_usuario_2.setBackground(pintar_paneles("blanco"));
        panel_intento_color_usuario_3.setBackground(pintar_paneles("blanco"));
        panel_intento_color_usuario_4.setBackground(pintar_paneles("blanco"));
        combo_intento_color_contrincante_1.setSelectedIndex(0);
        combo_intento_color_contrincante_2.setSelectedIndex(0);
        combo_intento_color_contrincante_3.setSelectedIndex(0);
        combo_intento_color_contrincante_4.setSelectedIndex(0);
        combo_popup_1.setSelectedIndex(0);
        combo_popup_2.setSelectedIndex(0);
        combo_popup_3.setSelectedIndex(0);
        combo_popup_4.setSelectedIndex(0);
        combo_intento_color_usuario_1.setSelectedIndex(0);
        combo_intento_color_usuario_2.setSelectedIndex(0);
        combo_intento_color_usuario_3.setSelectedIndex(0);
        combo_intento_color_usuario_4.setSelectedIndex(0);
        this.contenido_intento_usuario.setText("0");
        this.contenido_intento_contrincante.setText("0");
    }

    public void panel_visible(javax.swing.JPanel jpanel) {
		if (jpanel == this.jPanel_menu){
			clasificacion_por_victorias();
		}
		this.jPanel_menu.setVisible(false);
        this.jPanel_iniciar_sesion.setVisible(false);
        this.jPanel_menu_lateral.setVisible(false);
        this.jPanel_partida_usuario.setVisible(false);
        this.jPanel_partida_contrincante.setVisible(false);
        this.jFrame_popup.setVisible(false);
        this.jPanel_popup_key.setVisible(false);
        this.jPanel_readkey.setVisible(false);
        this.jPanel_border_lateral.setVisible(false);
        this.jPanel_rondas_usuario.setVisible(false);
        jpanel.setVisible(true);
    }

    public void sign_in() {
        this.jLabel_error_iniciar_sesion.setText("");
        this.jLabel_popup_error_iniciar_sesion.setText("");
        if (usuario_actual == null) {
            Usuario usuario = Login.sign_in(this.jTextField_nombre_usuario.getText(), this.jTextField_contrasena.getText());
            if (usuario == null) {
                this.jLabel_error_iniciar_sesion.setText("Usuario o contraseña incorrectos");
            } else {
                usuario_actual = usuario;
                label_bienvenido.setText("Bienvenido, " + jTextField_nombre_usuario.getText());
                label_nombre_usuario.setText(usuario_actual.getUsuario());
                panel_visible(jPanel_menu);
                if (usuario.isAdministrador()) {
                    jButton_menu_ajustes.setVisible(true);
                    jTextField_ajustes_rondas.setText(String.valueOf(rondas));
                    jTextField_ajustes_intentos.setText(String.valueOf(intentos));
                }
            }
        } else {
            if (contrincante == null) {
                Usuario adversario = Login.sign_in(this.jTextField_popup_nombre_usuario.getText(), this.jTextField_popup_contrasena.getText());
                if (adversario == null) {
                    jLabel_popup_error_iniciar_sesion.setText("Usuario o contraseña incorrectos");
                } else {
                    if (adversario.equals(usuario_actual)) {
                        jLabel_popup_error_iniciar_sesion.setText("Se necesitan dos usuarios diferentes");
                    } else {
                        contrincante = adversario;
                        jLabel_popup_user.setText(usuario_actual.getUsuario());
                        label_nombre_contrincante.setText(contrincante.getUsuario());
                        jFrame_popup.setVisible(true);
                        jPanel_login_contrincante.setVisible(false);
                        jPanel_readkey.setVisible(true);
                        jPanel_popup_key.setVisible(true);
                    }
                }
            }
        }
    }

    public void registro() {
        if (usuario_actual == null) {
            if (Usuario.usernameExiste(this.jTextField_nombre_usuario.getText())) {
                jLabel_error_iniciar_sesion.setText("Este nombre de usuario ya corresponde a un usuario");
            } else {
                Usuario user = Login.registro(this.jTextField_nombre_usuario.getText(), this.jTextField_contrasena.getText());
				usuario_actual = user;
                panel_visible(jPanel_menu);
                label_bienvenido.setText("Bienvenido, " + jTextField_nombre_usuario.getText());
                label_nombre_usuario.setText(usuario_actual.getUsuario());
                if (user.isAdministrador()) {
                    jButton_menu_ajustes.setVisible(true);
                    jTextField_ajustes_rondas.setText(String.valueOf(rondas));
                    jTextField_ajustes_intentos.setText(String.valueOf(intentos));
                }
            }
        }else
        if (contrincante == null) {
            if (Usuario.usernameExiste(this.jTextField_popup_nombre_usuario.getText())) {
                jLabel_popup_error_iniciar_sesion.setText("Este nombre de usuario ya corresponde a un usuario");
            } else {
                contrincante = Login.registro(this.jTextField_popup_nombre_usuario.getText(), this.jTextField_popup_contrasena.getText());
                label_nombre_contrincante.setText(contrincante.getUsuario());
                jLabel_popup_user.setText(contrincante.getUsuario());
                jFrame_popup.setVisible(true);
                jPanel_login_contrincante.setVisible(false);
                jPanel_readkey.setVisible(true);
                this.jPanel_popup_key.setVisible(true);
            }
        }

    }

    public void entrenamiento() {
        reiniciar_paneles_combos();
        contenido_intento_usuario.setText("0");
        panel_visible(this.jPanel_partida_usuario);
        clave_usuario_actual = new Combinacion();
        jPanel_list_usuario.removeAll();
        this.panel_puntuaciones_usuario.setVisible(false);
        entrenamiento = true;
        boton_reiniciar_usuario.setVisible(false);
        jFrame_partida.setVisible(false);
    }

    public void boton_newGame() {
        label_nombre_contrincante.setText("");
        reiniciar_paneles_combos();
        contrincante = null;
        clave_usuario_actual = null;
        clave_contrincante = null;
        entrenamiento = false;
        jFrame_popup.setVisible(true);
        this.jPanel_login_contrincante.setVisible(true);
        this.jPanel_popup_key.setVisible(false);
    }

    public void crear_partida() {
        entrenamiento = false;
        if (partida == null) {
            this.partida = new Partida(this.usuario_actual, contrincante);
        }
        this.ronda = new Ronda(usuario_actual, contrincante, clave_usuario_actual, clave_contrincante);
        this.label_puntuaciones_usuario.setText(usuario_actual.getUsuario());
        this.label_puntuaciones_contrincante.setText(contrincante.getUsuario());
        this.contenido_puntuaciones_usuario.setText("0");
        this.contenido_puntuaciones_usuario.setText("0");
        this.contenido_puntuaciones_nrondas.setText("/ "+String.valueOf(rondas));
        if (partida.getRondas().isEmpty()) {
            contenido_ronda_usuario.setText("1");
        } else {
            contenido_ronda_usuario.setText(String.valueOf(partida.getRondas().size() + 1));
        }
        panel_visible(jPanel_partida_usuario);
        this.jPanel_rondas_usuario.setVisible(true);

    }

    public void nueva_ronda() {
        int puntuacion_usuario = 0;
        int puntuacion_contrincante = 0;
        if (jLabel_contenido.getText().equals(contrincante.getUsuario())){
            puntuacion_contrincante = 10 - Integer.parseInt(this.contenido_intento_contrincante.getText());
        }else{
        puntuacion_usuario = 10 - Integer.parseInt(this.contenido_intento_usuario.getText());}
        /*
        if (puntuacion_usuario < 0) {
            puntuacion_usuario = 0;
        }
        if (puntuacion_contrincante < 0) {
            puntuacion_contrincante = 0;
        }*/
        ronda.setPuntuacion1(puntuacion_usuario);
        ronda.setPuntuacion2(puntuacion_contrincante);
        partida.addRonda(ronda);
        if (partida.getRondas().size() < rondas) {
            puntuacion_contrincante += Integer.parseInt(contenido_puntuaciones_contrincante.getText());
            puntuacion_usuario += Integer.parseInt(contenido_puntuaciones_usuario.getText());
            this.contenido_puntuaciones_contrincante.setText(String.valueOf(puntuacion_contrincante));
            this.contenido_puntuaciones_usuario.setText(String.valueOf(puntuacion_usuario));
            clave_usuario_actual = null;
            clave_contrincante = null;
            ronda = null;
            this.contenido_intento_usuario.setText("0");
            this.contenido_intento_contrincante.setText("0");
            reiniciar_paneles_combos();
            this.jFrame_popup.setVisible(true);
            this.jPanel_login_contrincante.setVisible(false);
            this.jPanel_readkey.setVisible(true);
            this.jPanel_popup_key.setVisible(true);

        } else {
            System.out.println("Partida terminada");
            Partida.addPartida(partida);
            partida = null;
            ronda = null;
            clave_usuario_actual = null;
            clave_contrincante = null;
            contrincante = null;
            reiniciar_paneles_combos();
            panel_visible(jPanel_menu);
        }
    }

    public Combinacion leer_clave() {
        Colour[] intento = new Colour[4];
        intento[0] = Colour.valueOf((String) combo_popup_1.getSelectedItem());
        intento[1] = Colour.valueOf((String) combo_popup_2.getSelectedItem());
        intento[2] = Colour.valueOf((String) combo_popup_3.getSelectedItem());
        intento[3] = Colour.valueOf((String) combo_popup_4.getSelectedItem());
        Combinacion combi = new Combinacion(intento);
        if (clave_contrincante == null) {
            clave_contrincante = combi;
            reiniciar_paneles_combos();
            jLabel_popup_user.setText(usuario_actual.getUsuario());
        } else if (clave_usuario_actual == null) {
            clave_usuario_actual = combi;
            jLabel_popup_user.setText(contrincante.getUsuario());
            crear_partida();
        }
        return combi;
    }

    public void ventana_ajustes() {
        throw new RuntimeException("No implementado");
    }

    public void clasificacion_por_victorias() {
        List<Usuario> lista_ordenada;
        int i;
		Component us;
		
        lista_ordenada = Clasificacion.calificacion_por_victorias();
		this.jPanel_clasificacion.removeAll();
		//System.out.println(lista_ordenada.toString());
        i = 0;
        while (i < lista_ordenada.size()) {
            us = crear_panel_clasificacion(lista_ordenada.get(i));
			this.jPanel_clasificacion.add(us,jPanel_clasificacion.getComponentCount());
            LayoutManager f = this.jPanel_clasificacion.getLayout();
            f.addLayoutComponent("us", us);
			jPanel_clasificacion.setLayout(f);
            i++;
        }
		jPanel_clasificacion.setPreferredSize(new Dimension(400, 72 * Usuario.getLista_usuarios().size()));
		jPanel_clasificacion.revalidate();
		jPanel_clasificacion.repaint();
    }
	
	public void clasificacion_por_porcentaje() {
        List<Usuario> lista_ordenada;
        int i;
		Component us;
		
        lista_ordenada = Clasificacion.calificacion_por_porcentaje();
		this.jPanel_clasificacion.removeAll();
		System.out.println(lista_ordenada.toString());
		System.out.println(Usuario.getLista_usuarios().size() + "<>>-----------");
        i = 0;
        while (i < lista_ordenada.size()) {
            us = crear_panel_clasificacion(lista_ordenada.get(i));
			this.jPanel_clasificacion.add(us,jPanel_clasificacion.getComponentCount());
            LayoutManager f = this.jPanel_clasificacion.getLayout();
            f.addLayoutComponent("us", us);
			jPanel_clasificacion.setLayout(f);
            i++;
        }
		jPanel_clasificacion.setPreferredSize(new Dimension(400, 72 * Usuario.getLista_usuarios().size()));
		jPanel_clasificacion.revalidate();
		jPanel_clasificacion.repaint();
    }

    public javax.swing.JPanel crear_panel_clasificacion(Usuario u) {
        javax.swing.JLabel jLabel1;
        javax.swing.JLabel jLabel2;
        javax.swing.JLabel jLabel3;
        javax.swing.JLabel jLabel4;
        javax.swing.JLabel jLabel5;
        javax.swing.JPanel jPanel1;
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(60,63,64));
		jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel1.setMaximumSize(new java.awt.Dimension(400, 72));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 72));jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt, jLabel1);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 246, 254));
        jLabel1.setText(u.getUsuario());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 246, 254));
        jLabel2.setText("Partidas ganadas :");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 246, 254));
        jLabel3.setText(((Integer)u.getPartidas_ganadas()).toString());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 246, 254));
        jLabel4.setText("% de victorias");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(155, 246, 254));
        jLabel5.setText(((Integer)u.getPorcentaje_victorias()).toString());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1.setMaximumSize(new java.awt.Dimension(400, 72));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 72));
        return (jPanel1);
    }

    public Colour[] combinacion_usuario() {
        Colour[] intento = new Colour[4];
        intento[0] = Colour.valueOf((String) combo_intento_color_usuario_1.getSelectedItem());
        intento[1] = Colour.valueOf((String) combo_intento_color_usuario_2.getSelectedItem());
        intento[2] = Colour.valueOf((String) combo_intento_color_usuario_3.getSelectedItem());
        intento[3] = Colour.valueOf((String) combo_intento_color_usuario_4.getSelectedItem());
        return intento;
    }

    public Colour[] combinacion_contrincante() {
        Colour[] intento = new Colour[4];
        intento[0] = Colour.valueOf((String) combo_intento_color_contrincante_1.getSelectedItem());
        intento[1] = Colour.valueOf((String) combo_intento_color_contrincante_2.getSelectedItem());
        intento[2] = Colour.valueOf((String) combo_intento_color_contrincante_3.getSelectedItem());
        intento[3] = Colour.valueOf((String) combo_intento_color_contrincante_4.getSelectedItem());
        return intento;
    }

    public boolean intento(Combinacion secreta, Colour[] intento, Usuario usuario) {
        javax.swing.JPanel jpanel = null;
        javax.swing.JLabel label = null;
        if (usuario == usuario_actual) {
            jpanel = this.jPanel_list_usuario;
            label = this.contenido_intento_usuario;
        } else if (usuario.equals(contrincante)) {
            jpanel = this.jPanel_list_contrincante;
            label = this.contenido_intento_contrincante;
        }
        Combinacion combinacion = new Combinacion(intento);
        if (secreta.equals(combinacion)) {
            return true;
        }
         else {
            int posicionados = 0;
            int contenidos = 0;
            for (int i = 0; i < 4; i++) {
                if (secreta.contains(intento[i])) {
                    contenidos++;
                    if (clave_usuario_actual.posicionado(intento[i], i)) {
                        posicionados++;
                    }
                }
            }
            javax.swing.JPanel resultado = resultados(posicionados, contenidos, intento);
            if (usuario.equals(contrincante)) {
                resultado.setBackground(new java.awt.Color(102, 255, 102));
            }
            int intent = Integer.parseInt(label.getText());
            label.setText(Integer.toString(intent + 1));
            Component componente = jpanel.add(resultado, intent);
            LayoutManager f = jpanel.getLayout();
            f.addLayoutComponent("componente", componente);
            jpanel.setLayout(f);
            jpanel.revalidate();
            jpanel.repaint();
            return false;
        }
    }

    public Color pintar_paneles(String valor) {
        switch (valor) {
            case "Blanco":
                return Color.WHITE;
            case "Azul":
                return Color.BLUE;
            case "Marron":
                return new Color(141, 73, 37);
            case "Negro":
                return Color.BLACK;
            case "Rojo":
                return Color.RED;
            case "Verde":
                return Color.GREEN;
            default:
                return Color.WHITE;
        }
    }

    public void visible(javax.swing.JPanel panel, boolean visible) {
        Component[] lista = panel.getComponents();
        for (int i = 0; i < lista.length; i++) {
            lista[i].setVisible(visible);
        }
        panel.setVisible(visible);
    }

    public javax.swing.JPanel resultados(int posicionado, int contenidos, Colour[] combinacion) {

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
        panel_resultado.setBackground(new java.awt.Color(155, 246, 254));
        resultado_color_1.setBackground(pintar_paneles(combinacion[0].toString()));
        resultado_color_2.setBackground(pintar_paneles(combinacion[1].toString()));
        resultado_color_3.setBackground(pintar_paneles(combinacion[2].toString()));
        resultado_color_4.setBackground(pintar_paneles(combinacion[3].toString()));
        System.out.println(Arrays.toString(combinacion));
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
        panel_resultado.setMaximumSize(new Dimension(562, 75));
        return panel_resultado;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame_popup = new javax.swing.JFrame();
        jPanel_login_contrincante = new javax.swing.JPanel();
        jPanel_campos1 = new javax.swing.JPanel();
        jLabel_popup_nombre_usuario = new javax.swing.JLabel();
        jTextField_popup_nombre_usuario = new javax.swing.JTextField();
        jLabel_popup_contrasena = new javax.swing.JLabel();
        jTextField_popup_contrasena = new javax.swing.JTextField();
        jButton_popup_iniciar_sesion = new javax.swing.JButton();
        jLabel_popup_error_iniciar_sesion = new javax.swing.JLabel();
        jButton_popup_registro = new javax.swing.JButton();
        jPanel_readkey = new javax.swing.JPanel();
        jButton_popup_savekey = new javax.swing.JButton();
        jLabel_popup_secretkey = new javax.swing.JLabel();
        jLabel_popup_user = new javax.swing.JLabel();
        jPanel_popup_key = new javax.swing.JPanel();
        combo_popup_1 = new javax.swing.JComboBox<>();
        combo_popup_2 = new javax.swing.JComboBox<>();
        combo_popup_3 = new javax.swing.JComboBox<>();
        combo_popup_4 = new javax.swing.JComboBox<>();
        jPanel_popup_color_1 = new javax.swing.JPanel();
        jPanel_popup_color_2 = new javax.swing.JPanel();
        jPanel_popup_color_3 = new javax.swing.JPanel();
        jPanel_popup_color_4 = new javax.swing.JPanel();
        jFrame_ajustes = new javax.swing.JFrame();
        jPanel_ajustes = new javax.swing.JPanel();
        jPanel_ajustes_campos = new javax.swing.JPanel();
        jLabel_ajustes_rondas = new javax.swing.JLabel();
        jTextField_ajustes_rondas = new javax.swing.JTextField();
        jLabel_ajustes_intentos = new javax.swing.JLabel();
        jTextField_ajustes_intentos = new javax.swing.JTextField();
        jButton_ajustes_guardar = new javax.swing.JButton();
        jButton_ajustes_cancelar = new javax.swing.JButton();
        jFrame_partida = new javax.swing.JFrame();
        jPanel_turn = new javax.swing.JPanel();
        jPanel_turn_contenido = new javax.swing.JPanel();
        jButton_ajustes_guardar1 = new javax.swing.JButton();
        jLabel_ajustes = new javax.swing.JLabel();
        jLabel_contenido = new javax.swing.JLabel();
        jFrame_head_to_head = new javax.swing.JFrame();
        jPanel_head_usuario = new javax.swing.JPanel();
        jTextField_usuario_estadisticas = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel_perdidas_usuario = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_puntos_favor_usuario = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel_porcentaje_usuario = new javax.swing.JLabel();
        jLabel_ganadas_usuario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_jugadas_usuario = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel_puntos_contra_usuario = new javax.swing.JLabel();
        jLabel_administrador_usuario = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel_head_rival = new javax.swing.JPanel();
        jLabel_administrador_rival = new javax.swing.JLabel();
        jLabel_jugadas_rival = new javax.swing.JLabel();
        jLabel_ganadas_rival = new javax.swing.JLabel();
        jLabel_perdidas_rival = new javax.swing.JLabel();
        jLabel_porcentaje_rival = new javax.swing.JLabel();
        jLabel_puntos_favor_rival = new javax.swing.JLabel();
        jLabel_puntos_contra_rival = new javax.swing.JLabel();
        jTextField_rival_estadisticas = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel_partidas = new javax.swing.JPanel();
        jPanel_rondas_usuario = new javax.swing.JPanel();
        label_ronda_usuario = new javax.swing.JLabel();
        contenido_ronda_usuario = new javax.swing.JLabel();
        panel_puntuaciones_usuario = new javax.swing.JPanel();
        label_puntuaciones_usuario = new javax.swing.JLabel();
        label_puntuaciones_contrincante = new javax.swing.JLabel();
        label_puntuaciones_username = new javax.swing.JLabel();
        contenido_puntuaciones_usuario = new javax.swing.JLabel();
        contenido_puntuaciones_contrincante = new javax.swing.JLabel();
        contenido_puntuaciones_nrondas = new javax.swing.JLabel();
        jPanel_menu = new javax.swing.JPanel();
        jPanel_menu_lateral = new javax.swing.JPanel();
        jLabel_menu_title = new javax.swing.JLabel();
        jButton_menu_entrenamiento = new javax.swing.JButton();
        jbutton_newgame = new javax.swing.JButton();
        jButton_menu_clasificacion = new javax.swing.JButton();
        jButton_menu_ajustes = new javax.swing.JButton();
        jButton_menu_logout = new javax.swing.JButton();
        jButton_menu_estadisticas_usuario = new javax.swing.JButton();
        jPanel_border_lateral = new javax.swing.JPanel();
        jPanel_menu_inicio = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        label_juego = new javax.swing.JLabel();
        label_bienvenido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel_clasificacion = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jPanel_partida_contrincante = new javax.swing.JPanel();
        panel_intento_contrincante = new javax.swing.JPanel();
        combo_intento_color_contrincante_1 = new javax.swing.JComboBox<>();
        combo_intento_color_contrincante_2 = new javax.swing.JComboBox<>();
        combo_intento_color_contrincante_3 = new javax.swing.JComboBox<>();
        combo_intento_color_contrincante_4 = new javax.swing.JComboBox<>();
        panel_intento_color_contrincante_1 = new javax.swing.JPanel();
        panel_intento_color_contrincante_2 = new javax.swing.JPanel();
        panel_intento_color_contrincante_3 = new javax.swing.JPanel();
        panel_intento_color_contrincante_4 = new javax.swing.JPanel();
        boton_intento_comprobar_contrincante = new javax.swing.JButton();
        panel_intentos_contrincante = new javax.swing.JPanel();
        label_intento_contrincante = new javax.swing.JLabel();
        contenido_intento_contrincante = new javax.swing.JLabel();
        jScroll_list_contrincante = new javax.swing.JScrollPane();
        jPanel_list_contrincante = new javax.swing.JPanel();
        boton_volver_contrincante = new javax.swing.JButton();
        label_nombre_contrincante = new javax.swing.JLabel();
        jPanel_partida_usuario = new javax.swing.JPanel();
        panel_intento_usuario = new javax.swing.JPanel();
        combo_intento_color_usuario_1 = new javax.swing.JComboBox<>();
        combo_intento_color_usuario_2 = new javax.swing.JComboBox<>();
        combo_intento_color_usuario_3 = new javax.swing.JComboBox<>();
        combo_intento_color_usuario_4 = new javax.swing.JComboBox<>();
        panel_intento_color_usuario_1 = new javax.swing.JPanel();
        panel_intento_color_usuario_2 = new javax.swing.JPanel();
        panel_intento_color_usuario_3 = new javax.swing.JPanel();
        panel_intento_color_usuario_4 = new javax.swing.JPanel();
        boton_intento_comprobar_usuario = new javax.swing.JButton();
        panel_intentos_usuario = new javax.swing.JPanel();
        label_intento_usuario = new javax.swing.JLabel();
        contenido_intento_usuario = new javax.swing.JLabel();
        jScroll_list_usuario = new javax.swing.JScrollPane();
        jPanel_list_usuario = new javax.swing.JPanel();
        boton_volver_usuario = new javax.swing.JButton();
        boton_reiniciar_usuario = new javax.swing.JButton();
        label_nombre_usuario = new javax.swing.JLabel();

        jFrame_popup.setTitle("Iniciar sesión");

        jPanel_login_contrincante.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_login_contrincante.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel_login_contrincante.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel_campos1.setBackground(new java.awt.Color(243, 230, 0));

        jLabel_popup_nombre_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_popup_nombre_usuario.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_popup_nombre_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_popup_nombre_usuario.setText("Nombre de usuario :");

        jTextField_popup_nombre_usuario.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_popup_nombre_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField_popup_nombre_usuario.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_popup_nombre_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_popup_nombre_usuario.setText("admin");
        jTextField_popup_nombre_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_popup_nombre_usuarioActionPerformed(evt);
            }
        });

        jLabel_popup_contrasena.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_popup_contrasena.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_popup_contrasena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_popup_contrasena.setText("Contraseña :");

        jTextField_popup_contrasena.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_popup_contrasena.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField_popup_contrasena.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_popup_contrasena.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_popup_contrasena.setText("admin");

        jButton_popup_iniciar_sesion.setBackground(new java.awt.Color(60, 63, 64));
        jButton_popup_iniciar_sesion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_popup_iniciar_sesion.setForeground(new java.awt.Color(155, 246, 254));
        jButton_popup_iniciar_sesion.setText("Iniciar sesión");
        jButton_popup_iniciar_sesion.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_popup_iniciar_sesion.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_popup_iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_popup_iniciar_sesionActionPerformed(evt);
            }
        });

        jLabel_popup_error_iniciar_sesion.setForeground(new java.awt.Color(255, 0, 0));

        jButton_popup_registro.setBackground(new java.awt.Color(60, 63, 64));
        jButton_popup_registro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_popup_registro.setForeground(new java.awt.Color(155, 246, 254));
        jButton_popup_registro.setText("Registrarse");
        jButton_popup_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_popup_registroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_campos1Layout = new javax.swing.GroupLayout(jPanel_campos1);
        jPanel_campos1.setLayout(jPanel_campos1Layout);
        jPanel_campos1Layout.setHorizontalGroup(
            jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_campos1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_popup_nombre_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_campos1Layout.createSequentialGroup()
                        .addGroup(jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_popup_registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_popup_error_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_popup_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel_popup_contrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_popup_contrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_campos1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_popup_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_campos1Layout.setVerticalGroup(
            jPanel_campos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_campos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_popup_nombre_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_popup_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_popup_contrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_popup_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_popup_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_popup_registro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_popup_error_iniciar_sesion))
        );

        javax.swing.GroupLayout jPanel_login_contrincanteLayout = new javax.swing.GroupLayout(jPanel_login_contrincante);
        jPanel_login_contrincante.setLayout(jPanel_login_contrincanteLayout);
        jPanel_login_contrincanteLayout.setHorizontalGroup(
            jPanel_login_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel_login_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_login_contrincanteLayout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(jPanel_campos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
        jPanel_login_contrincanteLayout.setVerticalGroup(
            jPanel_login_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jPanel_login_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_login_contrincanteLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jPanel_campos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE)))
        );

        jPanel_readkey.setBackground(new java.awt.Color(243, 230, 2));

        jButton_popup_savekey.setBackground(new java.awt.Color(60, 63, 64));
        jButton_popup_savekey.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_popup_savekey.setForeground(new java.awt.Color(155, 246, 254));
        jButton_popup_savekey.setText("Guardar clave");
        jButton_popup_savekey.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_popup_savekey.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_popup_savekey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_popup_savekeyActionPerformed(evt);
            }
        });

        jLabel_popup_secretkey.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_popup_secretkey.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_popup_secretkey.setText("Introduzca su clave secreta");

        jLabel_popup_user.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel_popup_user.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_popup_user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_popup_user.setText("jLabel4");

        jPanel_popup_key.setBackground(new java.awt.Color(243, 230, 0));

        combo_popup_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_popup_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_popup_1ActionPerformed(evt);
            }
        });

        combo_popup_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_popup_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_popup_2ActionPerformed(evt);
            }
        });

        combo_popup_3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_popup_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_popup_3ActionPerformed(evt);
            }
        });

        combo_popup_4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_popup_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_popup_4ActionPerformed(evt);
            }
        });

        jPanel_popup_color_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_popup_color_1Layout = new javax.swing.GroupLayout(jPanel_popup_color_1);
        jPanel_popup_color_1.setLayout(jPanel_popup_color_1Layout);
        jPanel_popup_color_1Layout.setHorizontalGroup(
            jPanel_popup_color_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_popup_color_1Layout.setVerticalGroup(
            jPanel_popup_color_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel_popup_color_2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_popup_color_2Layout = new javax.swing.GroupLayout(jPanel_popup_color_2);
        jPanel_popup_color_2.setLayout(jPanel_popup_color_2Layout);
        jPanel_popup_color_2Layout.setHorizontalGroup(
            jPanel_popup_color_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_popup_color_2Layout.setVerticalGroup(
            jPanel_popup_color_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel_popup_color_3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_popup_color_3Layout = new javax.swing.GroupLayout(jPanel_popup_color_3);
        jPanel_popup_color_3.setLayout(jPanel_popup_color_3Layout);
        jPanel_popup_color_3Layout.setHorizontalGroup(
            jPanel_popup_color_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_popup_color_3Layout.setVerticalGroup(
            jPanel_popup_color_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel_popup_color_4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_popup_color_4Layout = new javax.swing.GroupLayout(jPanel_popup_color_4);
        jPanel_popup_color_4.setLayout(jPanel_popup_color_4Layout);
        jPanel_popup_color_4Layout.setHorizontalGroup(
            jPanel_popup_color_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );
        jPanel_popup_color_4Layout.setVerticalGroup(
            jPanel_popup_color_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_popup_keyLayout = new javax.swing.GroupLayout(jPanel_popup_key);
        jPanel_popup_key.setLayout(jPanel_popup_keyLayout);
        jPanel_popup_keyLayout.setHorizontalGroup(
            jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_popup_keyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_popup_1, 0, 79, Short.MAX_VALUE)
                    .addComponent(jPanel_popup_color_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_popup_2, 0, 81, Short.MAX_VALUE)
                    .addComponent(jPanel_popup_color_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_popup_3, 0, 81, Short.MAX_VALUE)
                    .addComponent(jPanel_popup_color_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_popup_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_popup_color_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_popup_keyLayout.setVerticalGroup(
            jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_popup_keyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel_popup_color_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel_popup_color_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel_popup_color_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_popup_color_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_popup_keyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_popup_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_popup_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_popup_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_popup_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_readkeyLayout = new javax.swing.GroupLayout(jPanel_readkey);
        jPanel_readkey.setLayout(jPanel_readkeyLayout);
        jPanel_readkeyLayout.setHorizontalGroup(
            jPanel_readkeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_readkeyLayout.createSequentialGroup()
                .addGroup(jPanel_readkeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_readkeyLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton_popup_savekey, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_readkeyLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel_popup_key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_readkeyLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel_readkeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_popup_secretkey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_popup_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel_readkeyLayout.setVerticalGroup(
            jPanel_readkeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_readkeyLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel_popup_user, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_popup_secretkey)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_popup_key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jButton_popup_savekey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jFrame_popup.setLocationRelativeTo(null);

        javax.swing.GroupLayout jFrame_popupLayout = new javax.swing.GroupLayout(jFrame_popup.getContentPane());
        jFrame_popup.getContentPane().setLayout(jFrame_popupLayout);
        jFrame_popupLayout.setHorizontalGroup(
            jFrame_popupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_login_contrincante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jFrame_popupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_readkey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFrame_popupLayout.setVerticalGroup(
            jFrame_popupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_login_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jFrame_popupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_readkey, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFrame_ajustes.setTitle("Ajustes");
        jFrame_ajustes.setMinimumSize(new java.awt.Dimension(415, 320));

        jPanel_ajustes.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_ajustes.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel_ajustes.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel_ajustes_campos.setBackground(new java.awt.Color(243, 230, 0));

        jLabel_ajustes_rondas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_ajustes_rondas.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_ajustes_rondas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ajustes_rondas.setText("Número de rondas:");

        jTextField_ajustes_rondas.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_ajustes_rondas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField_ajustes_rondas.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_ajustes_rondas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_ajustes_rondas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ajustes_rondasActionPerformed(evt);
            }
        });

        jLabel_ajustes_intentos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_ajustes_intentos.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_ajustes_intentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ajustes_intentos.setText("Número de intentos:");

        jTextField_ajustes_intentos.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_ajustes_intentos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField_ajustes_intentos.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_ajustes_intentos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton_ajustes_guardar.setBackground(new java.awt.Color(60, 63, 64));
        jButton_ajustes_guardar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_ajustes_guardar.setForeground(new java.awt.Color(155, 246, 254));
        jButton_ajustes_guardar.setText("Guardar");
        jButton_ajustes_guardar.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_ajustes_guardar.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_ajustes_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ajustes_guardarActionPerformed(evt);
            }
        });

        jButton_ajustes_cancelar.setBackground(new java.awt.Color(60, 63, 64));
        jButton_ajustes_cancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_ajustes_cancelar.setForeground(new java.awt.Color(155, 246, 254));
        jButton_ajustes_cancelar.setText("Cancelar");
        jButton_ajustes_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ajustes_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ajustes_camposLayout = new javax.swing.GroupLayout(jPanel_ajustes_campos);
        jPanel_ajustes_campos.setLayout(jPanel_ajustes_camposLayout);
        jPanel_ajustes_camposLayout.setHorizontalGroup(
            jPanel_ajustes_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_ajustes_rondas, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
            .addGroup(jPanel_ajustes_camposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ajustes_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ajustes_camposLayout.createSequentialGroup()
                        .addGroup(jPanel_ajustes_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_ajustes_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ajustes_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_ajustes_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_ajustes_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_ajustes_intentos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ajustes_camposLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_ajustes_rondas, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel_ajustes_intentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_ajustes_camposLayout.setVerticalGroup(
            jPanel_ajustes_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ajustes_camposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_ajustes_rondas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_ajustes_rondas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_ajustes_intentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_ajustes_intentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_ajustes_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_ajustes_cancelar)
                .addContainerGap(6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_ajustesLayout = new javax.swing.GroupLayout(jPanel_ajustes);
        jPanel_ajustes.setLayout(jPanel_ajustesLayout);
        jPanel_ajustesLayout.setHorizontalGroup(
            jPanel_ajustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel_ajustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ajustesLayout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(jPanel_ajustes_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
        jPanel_ajustesLayout.setVerticalGroup(
            jPanel_ajustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jPanel_ajustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ajustesLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jPanel_ajustes_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE)))
        );

        jFrame_ajustes.setLocationRelativeTo(null);

        javax.swing.GroupLayout jFrame_ajustesLayout = new javax.swing.GroupLayout(jFrame_ajustes.getContentPane());
        jFrame_ajustes.getContentPane().setLayout(jFrame_ajustesLayout);
        jFrame_ajustesLayout.setHorizontalGroup(
            jFrame_ajustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_ajustes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jFrame_ajustesLayout.setVerticalGroup(
            jFrame_ajustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_ajustes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jFrame_partida.setTitle("Ajustes");
        jFrame_partida.setMinimumSize(new java.awt.Dimension(410, 305));
        jFrame_partida.setResizable(false);

        jPanel_turn.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_turn.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel_turn.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel_turn.setPreferredSize(new java.awt.Dimension(400, 300));

        jPanel_turn_contenido.setBackground(new java.awt.Color(243, 230, 0));

        jButton_ajustes_guardar1.setBackground(new java.awt.Color(60, 63, 64));
        jButton_ajustes_guardar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_ajustes_guardar1.setForeground(new java.awt.Color(155, 246, 254));
        jButton_ajustes_guardar1.setText("Aceptar");
        jButton_ajustes_guardar1.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_ajustes_guardar1.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_ajustes_guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ajustes_guardar1ActionPerformed(evt);
            }
        });

        jLabel_ajustes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel_ajustes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ajustes.setText("¡Felicidades, has acertado!");

        jLabel_contenido.setFont(new java.awt.Font("Showcard Gothic", 1, 34)); // NOI18N
        jLabel_contenido.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_contenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_contenido.setText("jLabel2");

        javax.swing.GroupLayout jPanel_turn_contenidoLayout = new javax.swing.GroupLayout(jPanel_turn_contenido);
        jPanel_turn_contenido.setLayout(jPanel_turn_contenidoLayout);
        jPanel_turn_contenidoLayout.setHorizontalGroup(
            jPanel_turn_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_turn_contenidoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel_turn_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_ajustes, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jLabel_contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_ajustes_guardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel_turn_contenidoLayout.setVerticalGroup(
            jPanel_turn_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_turn_contenidoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel_ajustes)
                .addGap(18, 18, 18)
                .addComponent(jLabel_contenido, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton_ajustes_guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_turnLayout = new javax.swing.GroupLayout(jPanel_turn);
        jPanel_turn.setLayout(jPanel_turnLayout);
        jPanel_turnLayout.setHorizontalGroup(
            jPanel_turnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_turnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_turn_contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_turnLayout.setVerticalGroup(
            jPanel_turnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_turnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_turn_contenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFrame_partida.setLocationRelativeTo(null);

        javax.swing.GroupLayout jFrame_partidaLayout = new javax.swing.GroupLayout(jFrame_partida.getContentPane());
        jFrame_partida.getContentPane().setLayout(jFrame_partidaLayout);
        jFrame_partidaLayout.setHorizontalGroup(
            jFrame_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_turn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jFrame_partidaLayout.setVerticalGroup(
            jFrame_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_turn, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        );

        jFrame_head_to_head.setTitle("Cara a cara");
        jFrame_head_to_head.setBackground(new java.awt.Color(243, 230, 0));

        jPanel_head_usuario.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_head_usuario.setMinimumSize(new java.awt.Dimension(357, 280));

        jTextField_usuario_estadisticas.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_usuario_estadisticas.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_usuario_estadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_usuario_estadisticasActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(60, 63, 64));
        jButton4.setForeground(new java.awt.Color(155, 246, 254));
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel_perdidas_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_perdidas_usuario.setText("8");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Partidas ganadas");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Partidas jugadas");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Porcentaje de victorias");

        jLabel_puntos_favor_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_puntos_favor_usuario.setText("100");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Puntos anotados");

        jLabel_porcentaje_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_porcentaje_usuario.setText("55,5 %");

        jLabel_ganadas_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_ganadas_usuario.setText("10");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Partidas perdidas");

        jLabel_jugadas_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_jugadas_usuario.setText("18");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("Puntos encajados");

        jLabel_puntos_contra_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_puntos_contra_usuario.setText("50");

        jLabel_administrador_usuario.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel_administrador_usuario.setText("administrador");

        jButton6.setBackground(new java.awt.Color(60, 63, 64));
        jButton6.setForeground(new java.awt.Color(155, 246, 254));
        jButton6.setText("VS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_head_usuarioLayout = new javax.swing.GroupLayout(jPanel_head_usuario);
        jPanel_head_usuario.setLayout(jPanel_head_usuarioLayout);
        jPanel_head_usuarioLayout.setHorizontalGroup(
            jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_jugadas_usuario))
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_ganadas_usuario))
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_perdidas_usuario))
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(jLabel_porcentaje_usuario))
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_puntos_favor_usuario))
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_puntos_contra_usuario))
                    .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                        .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                                .addComponent(jTextField_usuario_estadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(jLabel_administrador_usuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel_head_usuarioLayout.setVerticalGroup(
            jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_head_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField_usuario_estadisticas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_administrador_usuario)
                .addGap(18, 18, 18)
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel_jugadas_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel_ganadas_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel_perdidas_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel_porcentaje_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel_puntos_favor_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_head_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel_puntos_contra_usuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_head_rival.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_head_rival.setMaximumSize(new java.awt.Dimension(357, 280));
        jPanel_head_rival.setMinimumSize(new java.awt.Dimension(357, 280));
        jPanel_head_rival.setPreferredSize(new java.awt.Dimension(357, 280));

        jLabel_administrador_rival.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel_administrador_rival.setText("administrador");

        jLabel_jugadas_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_jugadas_rival.setText("18");

        jLabel_ganadas_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_ganadas_rival.setText("10");

        jLabel_perdidas_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_perdidas_rival.setText("8");

        jLabel_porcentaje_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_porcentaje_rival.setText("55,5 %");

        jLabel_puntos_favor_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_puntos_favor_rival.setText("100");

        jLabel_puntos_contra_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_puntos_contra_rival.setText("50");

        jTextField_rival_estadisticas.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_rival_estadisticas.setForeground(new java.awt.Color(155, 246, 254));

        jButton5.setBackground(new java.awt.Color(60, 63, 64));
        jButton5.setForeground(new java.awt.Color(155, 246, 254));
        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_head_rivalLayout = new javax.swing.GroupLayout(jPanel_head_rival);
        jPanel_head_rival.setLayout(jPanel_head_rivalLayout);
        jPanel_head_rivalLayout.setHorizontalGroup(
            jPanel_head_rivalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_head_rivalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_head_rivalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_perdidas_rival)
                    .addComponent(jLabel_porcentaje_rival)
                    .addComponent(jLabel_puntos_favor_rival)
                    .addComponent(jLabel_puntos_contra_rival)
                    .addComponent(jLabel_jugadas_rival)
                    .addComponent(jLabel_ganadas_rival)
                    .addComponent(jLabel_administrador_rival)
                    .addGroup(jPanel_head_rivalLayout.createSequentialGroup()
                        .addComponent(jTextField_rival_estadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_head_rivalLayout.setVerticalGroup(
            jPanel_head_rivalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_head_rivalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_head_rivalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField_rival_estadisticas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_administrador_rival)
                .addGap(18, 18, 18)
                .addComponent(jLabel_jugadas_rival)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_ganadas_rival)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_perdidas_rival)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_porcentaje_rival)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_puntos_favor_rival)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_puntos_contra_rival)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setPreferredSize(new java.awt.Dimension(431, 286));

        javax.swing.GroupLayout jPanel_partidasLayout = new javax.swing.GroupLayout(jPanel_partidas);
        jPanel_partidas.setLayout(jPanel_partidasLayout);
        jPanel_partidasLayout.setHorizontalGroup(
            jPanel_partidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );
        jPanel_partidasLayout.setVerticalGroup(
            jPanel_partidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel_partidas);

        jFrame_popup.setLocationRelativeTo(null);

        javax.swing.GroupLayout jFrame_head_to_headLayout = new javax.swing.GroupLayout(jFrame_head_to_head.getContentPane());
        jFrame_head_to_head.getContentPane().setLayout(jFrame_head_to_headLayout);
        jFrame_head_to_headLayout.setHorizontalGroup(
            jFrame_head_to_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame_head_to_headLayout.createSequentialGroup()
                .addGroup(jFrame_head_to_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame_head_to_headLayout.createSequentialGroup()
                        .addComponent(jPanel_head_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_head_rival, javax.swing.GroupLayout.PREFERRED_SIZE, 268, Short.MAX_VALUE))
                    .addGroup(jFrame_head_to_headLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jFrame_head_to_headLayout.setVerticalGroup(
            jFrame_head_to_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame_head_to_headLayout.createSequentialGroup()
                .addGroup(jFrame_head_to_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel_head_rival, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(jPanel_head_usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mastermind");
        setBackground(new java.awt.Color(243, 230, 0));
        setMaximumSize(new java.awt.Dimension(1330, 770));
        setMinimumSize(new java.awt.Dimension(1330, 770));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1330, 770));

        jPanel_rondas_usuario.setBackground(new java.awt.Color(184, 174, 0));
        jPanel_rondas_usuario.setMaximumSize(new java.awt.Dimension(200, 260));
        jPanel_rondas_usuario.setMinimumSize(new java.awt.Dimension(200, 260));
        jPanel_rondas_usuario.setPreferredSize(new java.awt.Dimension(200, 260));

        label_ronda_usuario.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        label_ronda_usuario.setText("Ronda:");

        contenido_ronda_usuario.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_ronda_usuario.setText("1");

        panel_puntuaciones_usuario.setBackground(new java.awt.Color(184, 174, 0));

        label_puntuaciones_usuario.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        label_puntuaciones_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_puntuaciones_usuario.setText("Puntuaciones:");

        label_puntuaciones_contrincante.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        label_puntuaciones_contrincante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_puntuaciones_contrincante.setText("Contrincante");

        label_puntuaciones_username.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        label_puntuaciones_username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_puntuaciones_username.setText("usuario");

        contenido_puntuaciones_usuario.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_puntuaciones_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contenido_puntuaciones_usuario.setText("1");

        contenido_puntuaciones_contrincante.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_puntuaciones_contrincante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contenido_puntuaciones_contrincante.setText("1");

        javax.swing.GroupLayout panel_puntuaciones_usuarioLayout = new javax.swing.GroupLayout(panel_puntuaciones_usuario);
        panel_puntuaciones_usuario.setLayout(panel_puntuaciones_usuarioLayout);
        panel_puntuaciones_usuarioLayout.setHorizontalGroup(
            panel_puntuaciones_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_puntuaciones_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_puntuaciones_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panel_puntuaciones_usuarioLayout.createSequentialGroup()
                        .addComponent(contenido_puntuaciones_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(label_puntuaciones_usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(contenido_puntuaciones_usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_puntuaciones_contrincante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(label_puntuaciones_username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_puntuaciones_usuarioLayout.setVerticalGroup(
            panel_puntuaciones_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_puntuaciones_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_puntuaciones_usuario)
                .addGap(13, 13, 13)
                .addComponent(label_puntuaciones_username)
                .addGap(12, 12, 12)
                .addComponent(contenido_puntuaciones_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_puntuaciones_contrincante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contenido_puntuaciones_contrincante)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        contenido_puntuaciones_nrondas.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_puntuaciones_nrondas.setText("1");

        javax.swing.GroupLayout jPanel_rondas_usuarioLayout = new javax.swing.GroupLayout(jPanel_rondas_usuario);
        jPanel_rondas_usuario.setLayout(jPanel_rondas_usuarioLayout);
        jPanel_rondas_usuarioLayout.setHorizontalGroup(
            jPanel_rondas_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rondas_usuarioLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(label_ronda_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenido_ronda_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenido_puntuaciones_nrondas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_rondas_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_puntuaciones_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_rondas_usuarioLayout.setVerticalGroup(
            jPanel_rondas_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rondas_usuarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_rondas_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ronda_usuario)
                    .addComponent(contenido_ronda_usuario)
                    .addComponent(contenido_puntuaciones_nrondas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_puntuaciones_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_menu.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_menu.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel_menu.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel_menu.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_menu_lateral.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_menu_lateral.setPreferredSize(new java.awt.Dimension(500, 720));

        jLabel_menu_title.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel_menu_title.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_menu_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menu_title.setText("MENU");

        jButton_menu_entrenamiento.setBackground(new java.awt.Color(60, 63, 64));
        jButton_menu_entrenamiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_menu_entrenamiento.setForeground(new java.awt.Color(155, 246, 254));
        jButton_menu_entrenamiento.setText("Entrenamiento");
        jButton_menu_entrenamiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_menu_entrenamiento.setMaximumSize(new java.awt.Dimension(70, 36));
        jButton_menu_entrenamiento.setMinimumSize(new java.awt.Dimension(70, 36));
        jButton_menu_entrenamiento.setPreferredSize(new java.awt.Dimension(70, 36));
        jButton_menu_entrenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_menu_entrenamientoActionPerformed(evt);
            }
        });

        jbutton_newgame.setBackground(new java.awt.Color(60, 63, 64));
        jbutton_newgame.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jbutton_newgame.setForeground(new java.awt.Color(155, 246, 254));
        jbutton_newgame.setText("Nueva Partida");
        jbutton_newgame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jbutton_newgame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton_newgameActionPerformed(evt);
            }
        });

        jButton_menu_clasificacion.setBackground(new java.awt.Color(60, 63, 64));
        jButton_menu_clasificacion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_menu_clasificacion.setForeground(new java.awt.Color(155, 246, 254));
        jButton_menu_clasificacion.setText("Clasificación");
        jButton_menu_clasificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_menu_clasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_menu_clasificacionActionPerformed(evt);
            }
        });

        jButton_menu_ajustes.setBackground(new java.awt.Color(60, 63, 64));
        jButton_menu_ajustes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_menu_ajustes.setForeground(new java.awt.Color(155, 246, 254));
        jButton_menu_ajustes.setText("Ajustes");
        jButton_menu_ajustes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_menu_ajustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_menu_ajustesActionPerformed(evt);
            }
        });

        jButton_menu_logout.setBackground(new java.awt.Color(60, 63, 64));
        jButton_menu_logout.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_menu_logout.setForeground(new java.awt.Color(155, 246, 254));
        jButton_menu_logout.setText("Cerrar sesión");
        jButton_menu_logout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_menu_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_menu_logoutActionPerformed(evt);
            }
        });

        jButton_menu_estadisticas_usuario.setBackground(new java.awt.Color(60, 63, 64));
        jButton_menu_estadisticas_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_menu_estadisticas_usuario.setForeground(new java.awt.Color(155, 246, 254));
        jButton_menu_estadisticas_usuario.setText("Estadísticas de usuario");
        jButton_menu_estadisticas_usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_menu_estadisticas_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_menu_estadisticas_usuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menu_lateralLayout = new javax.swing.GroupLayout(jPanel_menu_lateral);
        jPanel_menu_lateral.setLayout(jPanel_menu_lateralLayout);
        jPanel_menu_lateralLayout.setHorizontalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_menu_entrenamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbutton_newgame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jButton_menu_clasificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_menu_ajustes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_menu_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menu_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_menu_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_menu_estadisticas_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_menu_lateralLayout.setVerticalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel_menu_title)
                .addGap(49, 49, 49)
                .addComponent(jButton_menu_entrenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jbutton_newgame, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton_menu_clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton_menu_ajustes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton_menu_estadisticas_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_menu_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel_border_lateral.setBackground(new java.awt.Color(60, 63, 64));
        jPanel_border_lateral.setMaximumSize(new java.awt.Dimension(5, 720));
        jPanel_border_lateral.setMinimumSize(new java.awt.Dimension(5, 720));
        jPanel_border_lateral.setPreferredSize(new java.awt.Dimension(5, 720));

        javax.swing.GroupLayout jPanel_border_lateralLayout = new javax.swing.GroupLayout(jPanel_border_lateral);
        jPanel_border_lateral.setLayout(jPanel_border_lateralLayout);
        jPanel_border_lateralLayout.setHorizontalGroup(
            jPanel_border_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel_border_lateralLayout.setVerticalGroup(
            jPanel_border_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jPanel_menu_inicio.setBackground(new java.awt.Color(243, 230, 0));
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
        label_juego.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_juego.setText("MasterMind");

        label_bienvenido.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        label_bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_bienvenido.setText("Bienvenido,");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(416, 600));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(416, 600));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel_clasificacion.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_clasificacion.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel_clasificacion.setPreferredSize(new java.awt.Dimension(416, 2000));

        javax.swing.GroupLayout jPanel_clasificacionLayout = new javax.swing.GroupLayout(jPanel_clasificacion);
        jPanel_clasificacion.setLayout(jPanel_clasificacionLayout);
        jPanel_clasificacionLayout.setHorizontalGroup(
            jPanel_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        jPanel_clasificacionLayout.setVerticalGroup(
            jPanel_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel_clasificacion);

        jButton2.setBackground(new java.awt.Color(60, 63, 64));
        jButton2.setForeground(new java.awt.Color(155, 246, 254));
        jButton2.setText("Ordenar por Victorias");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(60, 63, 64));
        jButton3.setForeground(new java.awt.Color(155, 246, 254));
        jButton3.setText("Ordenar por porcentaje de victorias");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menu_inicioLayout = new javax.swing.GroupLayout(jPanel_menu_inicio);
        jPanel_menu_inicio.setLayout(jPanel_menu_inicioLayout);
        jPanel_menu_inicioLayout.setHorizontalGroup(
            jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156)
                .addComponent(label_juego, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 463, Short.MAX_VALUE))
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(307, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menu_inicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(287, 287, 287))
        );
        jPanel_menu_inicioLayout.setVerticalGroup(
            jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                .addGroup(jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_juego)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_bienvenido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_menu_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_menu_inicioLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jPanel_menu_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_border_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_menu_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE))
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu_lateral, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jPanel_border_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addComponent(jPanel_menu_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );

        jPanel_iniciar_sesion.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_iniciar_sesion.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel_iniciar_sesion.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel_iniciar_sesion.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_campos.setBackground(new java.awt.Color(243, 230, 0));

        jLabel_nombre_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_nombre_usuario.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_nombre_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_nombre_usuario.setText("Nombre de usuario :");

        jTextField_nombre_usuario.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_nombre_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField_nombre_usuario.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_nombre_usuario.setText("admin");
        jTextField_nombre_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_usuarioActionPerformed(evt);
            }
        });

        jLabel_contrasena.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_contrasena.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_contrasena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_contrasena.setText("Contraseña :");

        jTextField_contrasena.setBackground(new java.awt.Color(60, 63, 64));
        jTextField_contrasena.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField_contrasena.setForeground(new java.awt.Color(155, 246, 254));
        jTextField_contrasena.setText("admin");

        jButton_iniciar_sesion.setBackground(new java.awt.Color(60, 63, 64));
        jButton_iniciar_sesion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_iniciar_sesion.setForeground(new java.awt.Color(155, 246, 254));
        jButton_iniciar_sesion.setText("Iniciar sesión");
        jButton_iniciar_sesion.setMaximumSize(new java.awt.Dimension(72, 72));
        jButton_iniciar_sesion.setMinimumSize(new java.awt.Dimension(72, 72));
        jButton_iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_iniciar_sesionActionPerformed(evt);
            }
        });

        jLabel_error_iniciar_sesion.setForeground(new java.awt.Color(255, 0, 0));

        jButton_registro.setBackground(new java.awt.Color(60, 63, 64));
        jButton_registro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_registro.setForeground(new java.awt.Color(155, 246, 254));
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
                .addGroup(jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_registro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_iniciar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_nombre_usuario)
                            .addComponent(jTextField_contrasena)
                            .addComponent(jLabel_nombre_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_error_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel_camposLayout.setVerticalGroup(
            jPanel_camposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_camposLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel_nombre_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_contrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_registro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_error_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("MasterMind");

        javax.swing.GroupLayout jPanel_iniciar_sesionLayout = new javax.swing.GroupLayout(jPanel_iniciar_sesion);
        jPanel_iniciar_sesion.setLayout(jPanel_iniciar_sesionLayout);
        jPanel_iniciar_sesionLayout.setHorizontalGroup(
            jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_iniciar_sesionLayout.createSequentialGroup()
                .addContainerGap(470, Short.MAX_VALUE)
                .addGroup(jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_campos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(470, 470, 470))
        );
        jPanel_iniciar_sesionLayout.setVerticalGroup(
            jPanel_iniciar_sesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_iniciar_sesionLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel3)
                .addGap(51, 51, 51)
                .addComponent(jPanel_campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jPanel_partida_contrincante.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_partida_contrincante.setAlignmentX(0.0F);
        jPanel_partida_contrincante.setAlignmentY(0.0F);
        jPanel_partida_contrincante.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel_partida_contrincante.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel_partida_contrincante.setName(""); // NOI18N
        jPanel_partida_contrincante.setPreferredSize(new java.awt.Dimension(1280, 720));

        panel_intento_contrincante.setBackground(new java.awt.Color(243, 230, 0));

        combo_intento_color_contrincante_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_contrincante_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_contrincante_1ActionPerformed(evt);
            }
        });

        combo_intento_color_contrincante_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_contrincante_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_contrincante_2ActionPerformed(evt);
            }
        });

        combo_intento_color_contrincante_3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_contrincante_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_contrincante_3ActionPerformed(evt);
            }
        });

        combo_intento_color_contrincante_4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_contrincante_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_contrincante_4ActionPerformed(evt);
            }
        });

        panel_intento_color_contrincante_1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_contrincante_1Layout = new javax.swing.GroupLayout(panel_intento_color_contrincante_1);
        panel_intento_color_contrincante_1.setLayout(panel_intento_color_contrincante_1Layout);
        panel_intento_color_contrincante_1Layout.setHorizontalGroup(
            panel_intento_color_contrincante_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        panel_intento_color_contrincante_1Layout.setVerticalGroup(
            panel_intento_color_contrincante_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panel_intento_color_contrincante_2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_contrincante_2Layout = new javax.swing.GroupLayout(panel_intento_color_contrincante_2);
        panel_intento_color_contrincante_2.setLayout(panel_intento_color_contrincante_2Layout);
        panel_intento_color_contrincante_2Layout.setHorizontalGroup(
            panel_intento_color_contrincante_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_intento_color_contrincante_2Layout.setVerticalGroup(
            panel_intento_color_contrincante_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panel_intento_color_contrincante_3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_contrincante_3Layout = new javax.swing.GroupLayout(panel_intento_color_contrincante_3);
        panel_intento_color_contrincante_3.setLayout(panel_intento_color_contrincante_3Layout);
        panel_intento_color_contrincante_3Layout.setHorizontalGroup(
            panel_intento_color_contrincante_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_intento_color_contrincante_3Layout.setVerticalGroup(
            panel_intento_color_contrincante_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panel_intento_color_contrincante_4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_contrincante_4Layout = new javax.swing.GroupLayout(panel_intento_color_contrincante_4);
        panel_intento_color_contrincante_4.setLayout(panel_intento_color_contrincante_4Layout);
        panel_intento_color_contrincante_4Layout.setHorizontalGroup(
            panel_intento_color_contrincante_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        panel_intento_color_contrincante_4Layout.setVerticalGroup(
            panel_intento_color_contrincante_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_intento_contrincanteLayout = new javax.swing.GroupLayout(panel_intento_contrincante);
        panel_intento_contrincante.setLayout(panel_intento_contrincanteLayout);
        panel_intento_contrincanteLayout.setHorizontalGroup(
            panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intento_contrincanteLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento_color_contrincante_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_intento_color_contrincante_1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_color_contrincante_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_intento_color_contrincante_2, 0, 81, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_color_contrincante_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_intento_color_contrincante_3, 0, 81, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento_color_contrincante_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_intento_color_contrincante_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panel_intento_contrincanteLayout.setVerticalGroup(
            panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intento_contrincanteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_intento_contrincanteLayout.createSequentialGroup()
                        .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_intento_color_contrincante_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_intento_contrincanteLayout.createSequentialGroup()
                                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panel_intento_color_contrincante_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel_intento_color_contrincante_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_intento_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(combo_intento_color_contrincante_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(combo_intento_color_contrincante_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(combo_intento_color_contrincante_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_intento_contrincanteLayout.createSequentialGroup()
                        .addComponent(panel_intento_color_contrincante_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_intento_color_contrincante_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        boton_intento_comprobar_contrincante.setBackground(new java.awt.Color(60, 63, 64));
        boton_intento_comprobar_contrincante.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        boton_intento_comprobar_contrincante.setForeground(new java.awt.Color(155, 246, 254));
        boton_intento_comprobar_contrincante.setText("Comprobar");
        boton_intento_comprobar_contrincante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_intento_comprobar_contrincante_usuarioActionPerformed(evt);
            }
        });

        panel_intentos_contrincante.setBackground(new java.awt.Color(184, 174, 0));

        label_intento_contrincante.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        label_intento_contrincante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_intento_contrincante.setText("Intento:");

        contenido_intento_contrincante.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_intento_contrincante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contenido_intento_contrincante.setText("0");

        javax.swing.GroupLayout panel_intentos_contrincanteLayout = new javax.swing.GroupLayout(panel_intentos_contrincante);
        panel_intentos_contrincante.setLayout(panel_intentos_contrincanteLayout);
        panel_intentos_contrincanteLayout.setHorizontalGroup(
            panel_intentos_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intentos_contrincanteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_intentos_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenido_intento_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_intento_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_intentos_contrincanteLayout.setVerticalGroup(
            panel_intentos_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intentos_contrincanteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_intento_contrincante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contenido_intento_contrincante)
                .addGap(36, 36, 36))
        );

        jScroll_list_contrincante.setBackground(new java.awt.Color(220, 208, 0));
        jScroll_list_contrincante.setBorder(null);

        jPanel_list_contrincante.setBackground(new java.awt.Color(220, 208, 0));
        jPanel_list_contrincante.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel_list_contrincanteLayout = new javax.swing.GroupLayout(jPanel_list_contrincante);
        jPanel_list_contrincante.setLayout(jPanel_list_contrincanteLayout);
        jPanel_list_contrincanteLayout.setHorizontalGroup(
            jPanel_list_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_list_contrincanteLayout.setVerticalGroup(
            jPanel_list_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScroll_list_contrincante.setViewportView(jPanel_list_contrincante);

        boton_volver_contrincante.setBackground(new java.awt.Color(60, 63, 64));
        boton_volver_contrincante.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        boton_volver_contrincante.setForeground(new java.awt.Color(155, 246, 254));
        boton_volver_contrincante.setText("Volver");
        boton_volver_contrincante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_volver_contrincanteActionPerformed(evt);
            }
        });

        label_nombre_contrincante.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        label_nombre_contrincante.setForeground(new java.awt.Color(255, 0, 0));
        label_nombre_contrincante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_nombre_contrincante.setText("jLabel1");

        javax.swing.GroupLayout jPanel_partida_contrincanteLayout = new javax.swing.GroupLayout(jPanel_partida_contrincante);
        jPanel_partida_contrincante.setLayout(jPanel_partida_contrincanteLayout);
        jPanel_partida_contrincanteLayout.setHorizontalGroup(
            jPanel_partida_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_partida_contrincanteLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(boton_volver_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addGroup(jPanel_partida_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScroll_list_contrincante))
                .addGap(112, 112, 112)
                .addGroup(jPanel_partida_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boton_intento_comprobar_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_intentos_contrincante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(122, 122, 122))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_partida_contrincanteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_nombre_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_partida_contrincanteLayout.setVerticalGroup(
            jPanel_partida_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_partida_contrincanteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_partida_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_partida_contrincanteLayout.createSequentialGroup()
                        .addGroup(jPanel_partida_contrincanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScroll_list_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_volver_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(panel_intento_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_partida_contrincanteLayout.createSequentialGroup()
                        .addComponent(panel_intentos_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)
                        .addComponent(boton_intento_comprobar_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(label_nombre_contrincante)
                .addContainerGap())
        );

        jPanel_partida_usuario.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_partida_usuario.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel_partida_usuario.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel_partida_usuario.setPreferredSize(new java.awt.Dimension(1280, 720));

        panel_intento_usuario.setBackground(new java.awt.Color(243, 230, 0));

        combo_intento_color_usuario_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_usuario_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_usuario_1ActionPerformed(evt);
            }
        });

        combo_intento_color_usuario_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_usuario_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_usuario_2ActionPerformed(evt);
            }
        });

        combo_intento_color_usuario_3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_usuario_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_usuario_3ActionPerformed(evt);
            }
        });

        combo_intento_color_usuario_4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Negro", "Azul", "Rojo", "Verde", "Marron" }));
        combo_intento_color_usuario_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intento_color_usuario_4ActionPerformed(evt);
            }
        });

        panel_intento_color_usuario_1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_usuario_1Layout = new javax.swing.GroupLayout(panel_intento_color_usuario_1);
        panel_intento_color_usuario_1.setLayout(panel_intento_color_usuario_1Layout);
        panel_intento_color_usuario_1Layout.setHorizontalGroup(
            panel_intento_color_usuario_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        panel_intento_color_usuario_1Layout.setVerticalGroup(
            panel_intento_color_usuario_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panel_intento_color_usuario_2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_usuario_2Layout = new javax.swing.GroupLayout(panel_intento_color_usuario_2);
        panel_intento_color_usuario_2.setLayout(panel_intento_color_usuario_2Layout);
        panel_intento_color_usuario_2Layout.setHorizontalGroup(
            panel_intento_color_usuario_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_intento_color_usuario_2Layout.setVerticalGroup(
            panel_intento_color_usuario_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panel_intento_color_usuario_3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_usuario_3Layout = new javax.swing.GroupLayout(panel_intento_color_usuario_3);
        panel_intento_color_usuario_3.setLayout(panel_intento_color_usuario_3Layout);
        panel_intento_color_usuario_3Layout.setHorizontalGroup(
            panel_intento_color_usuario_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_intento_color_usuario_3Layout.setVerticalGroup(
            panel_intento_color_usuario_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panel_intento_color_usuario_4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_intento_color_usuario_4Layout = new javax.swing.GroupLayout(panel_intento_color_usuario_4);
        panel_intento_color_usuario_4.setLayout(panel_intento_color_usuario_4Layout);
        panel_intento_color_usuario_4Layout.setHorizontalGroup(
            panel_intento_color_usuario_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        panel_intento_color_usuario_4Layout.setVerticalGroup(
            panel_intento_color_usuario_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_intento_usuarioLayout = new javax.swing.GroupLayout(panel_intento_usuario);
        panel_intento_usuario.setLayout(panel_intento_usuarioLayout);
        panel_intento_usuarioLayout.setHorizontalGroup(
            panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intento_usuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento_color_usuario_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_intento_color_usuario_1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_color_usuario_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_intento_color_usuario_2, 0, 81, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_color_usuario_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_intento_color_usuario_3, 0, 81, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_intento_color_usuario_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_intento_color_usuario_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panel_intento_usuarioLayout.setVerticalGroup(
            panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intento_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_intento_usuarioLayout.createSequentialGroup()
                        .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_intento_color_usuario_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_intento_usuarioLayout.createSequentialGroup()
                                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panel_intento_color_usuario_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel_intento_color_usuario_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_intento_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(combo_intento_color_usuario_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(combo_intento_color_usuario_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(combo_intento_color_usuario_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_intento_usuarioLayout.createSequentialGroup()
                        .addComponent(panel_intento_color_usuario_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_intento_color_usuario_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        boton_intento_comprobar_usuario.setBackground(new java.awt.Color(60, 63, 64));
        boton_intento_comprobar_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        boton_intento_comprobar_usuario.setForeground(new java.awt.Color(155, 246, 254));
        boton_intento_comprobar_usuario.setText("Comprobar");
        boton_intento_comprobar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_intento_comprobar_usuario_usuarioActionPerformed(evt);
            }
        });

        panel_intentos_usuario.setBackground(new java.awt.Color(184, 174, 0));

        label_intento_usuario.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        label_intento_usuario.setText("Intento:");

        contenido_intento_usuario.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        contenido_intento_usuario.setText("0");

        javax.swing.GroupLayout panel_intentos_usuarioLayout = new javax.swing.GroupLayout(panel_intentos_usuario);
        panel_intentos_usuario.setLayout(panel_intentos_usuarioLayout);
        panel_intentos_usuarioLayout.setHorizontalGroup(
            panel_intentos_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intentos_usuarioLayout.createSequentialGroup()
                .addGroup(panel_intentos_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_intentos_usuarioLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(contenido_intento_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_intentos_usuarioLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(label_intento_usuario)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panel_intentos_usuarioLayout.setVerticalGroup(
            panel_intentos_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_intentos_usuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_intento_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contenido_intento_usuario)
                .addGap(36, 36, 36))
        );

        jScroll_list_usuario.setBackground(new java.awt.Color(220, 208, 0));
        jScroll_list_usuario.setBorder(null);

        jPanel_list_usuario.setBackground(new java.awt.Color(220, 208, 0));
        jPanel_list_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel_list_usuarioLayout = new javax.swing.GroupLayout(jPanel_list_usuario);
        jPanel_list_usuario.setLayout(jPanel_list_usuarioLayout);
        jPanel_list_usuarioLayout.setHorizontalGroup(
            jPanel_list_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_list_usuarioLayout.setVerticalGroup(
            jPanel_list_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScroll_list_usuario.setViewportView(jPanel_list_usuario);

        boton_volver_usuario.setBackground(new java.awt.Color(60, 63, 64));
        boton_volver_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        boton_volver_usuario.setForeground(new java.awt.Color(155, 246, 254));
        boton_volver_usuario.setText("Volver");
        boton_volver_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_volver_usuarioActionPerformed(evt);
            }
        });

        boton_reiniciar_usuario.setBackground(new java.awt.Color(60, 63, 64));
        boton_reiniciar_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        boton_reiniciar_usuario.setForeground(new java.awt.Color(155, 246, 254));
        boton_reiniciar_usuario.setText("Reiniciar entrenamiento");
        boton_reiniciar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciar_usuarioActionPerformed(evt);
            }
        });

        label_nombre_usuario.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        label_nombre_usuario.setForeground(new java.awt.Color(0, 0, 0));
        label_nombre_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_nombre_usuario.setText("jLabel1");

        javax.swing.GroupLayout jPanel_partida_usuarioLayout = new javax.swing.GroupLayout(jPanel_partida_usuario);
        jPanel_partida_usuario.setLayout(jPanel_partida_usuarioLayout);
        jPanel_partida_usuarioLayout.setHorizontalGroup(
            jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_partida_usuarioLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(boton_volver_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_intento_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScroll_list_usuario))
                .addGroup(jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_partida_usuarioLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_intentos_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_intento_comprobar_usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_partida_usuarioLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(boton_reiniciar_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_partida_usuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_partida_usuarioLayout.setVerticalGroup(
            jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_partida_usuarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_partida_usuarioLayout.createSequentialGroup()
                        .addGroup(jPanel_partida_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScroll_list_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_volver_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(panel_intento_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_partida_usuarioLayout.createSequentialGroup()
                        .addComponent(panel_intentos_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(boton_reiniciar_usuario)
                        .addGap(129, 129, 129)
                        .addComponent(boton_intento_comprobar_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(label_nombre_usuario)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1292, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 6, Short.MAX_VALUE)
                    .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel_partida_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel_partida_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1454, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_partida_contrincante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel_partida_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_iniciar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_iniciar_sesionActionPerformed
        sign_in();
    }//GEN-LAST:event_jButton_iniciar_sesionActionPerformed

    private void jTextField_nombre_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre_usuarioActionPerformed
        this.jLabel_error_iniciar_sesion.setText("");
    }//GEN-LAST:event_jTextField_nombre_usuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //System.out.println(Partida.getLista_partidas().toString());
		if (this.jPanel_menu_lateral.isVisible()) {
            this.jPanel_menu_lateral.setVisible(false);
            this.jPanel_border_lateral.setVisible(false);
        } else {
            this.jPanel_menu_lateral.setVisible(true);
            this.jPanel_border_lateral.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registroActionPerformed
        registro();
    }//GEN-LAST:event_jButton_registroActionPerformed

    private void jButton_menu_ajustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_menu_ajustesActionPerformed
        this.jFrame_ajustes.setVisible(true);
    }//GEN-LAST:event_jButton_menu_ajustesActionPerformed

    private void jButton_menu_clasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_menu_clasificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_menu_clasificacionActionPerformed

    private void jbutton_newgameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton_newgameActionPerformed
        boton_newGame();
    }//GEN-LAST:event_jbutton_newgameActionPerformed

    private void jButton_menu_entrenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_menu_entrenamientoActionPerformed
        entrenamiento();
    }//GEN-LAST:event_jButton_menu_entrenamientoActionPerformed

    private void jTextField_popup_nombre_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_popup_nombre_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_popup_nombre_usuarioActionPerformed

    private void jButton_popup_iniciar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_popup_iniciar_sesionActionPerformed
        sign_in();
    }//GEN-LAST:event_jButton_popup_iniciar_sesionActionPerformed

    private void jButton_popup_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_popup_registroActionPerformed
        registro();
    }//GEN-LAST:event_jButton_popup_registroActionPerformed

    private void combo_popup_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_popup_1ActionPerformed
        jPanel_popup_color_1.setBackground(pintar_paneles(((String) combo_popup_1.getSelectedItem())));
    }//GEN-LAST:event_combo_popup_1ActionPerformed

    private void combo_popup_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_popup_2ActionPerformed
        jPanel_popup_color_2.setBackground(pintar_paneles(((String) combo_popup_2.getSelectedItem())));        jPanel_popup_color_2.setBackground(pintar_paneles(((String) combo_popup_2.getSelectedItem())));        jPanel_popup_color_2.setBackground(pintar_paneles(((String) combo_popup_2.getSelectedItem())));    }//GEN-LAST:event_combo_popup_2ActionPerformed

    private void combo_popup_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_popup_3ActionPerformed
        jPanel_popup_color_3.setBackground(pintar_paneles(((String) combo_popup_3.getSelectedItem())));
    }//GEN-LAST:event_combo_popup_3ActionPerformed

    private void combo_popup_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_popup_4ActionPerformed
        jPanel_popup_color_4.setBackground(pintar_paneles(((String) combo_popup_4.getSelectedItem())));
    }//GEN-LAST:event_combo_popup_4ActionPerformed

    private void jButton_popup_savekeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_popup_savekeyActionPerformed
        leer_clave();
    }//GEN-LAST:event_jButton_popup_savekeyActionPerformed

    private void combo_intento_color_contrincante_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_contrincante_1ActionPerformed
        panel_intento_color_contrincante_1.setBackground(pintar_paneles(((String) combo_intento_color_contrincante_1.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_contrincante_1ActionPerformed

    private void combo_intento_color_contrincante_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_contrincante_2ActionPerformed
        panel_intento_color_contrincante_2.setBackground(pintar_paneles(((String) combo_intento_color_contrincante_2.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_contrincante_2ActionPerformed

    private void combo_intento_color_contrincante_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_contrincante_3ActionPerformed
        panel_intento_color_contrincante_3.setBackground(pintar_paneles(((String) combo_intento_color_contrincante_3.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_contrincante_3ActionPerformed

    private void combo_intento_color_contrincante_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_contrincante_4ActionPerformed
        panel_intento_color_contrincante_4.setBackground(pintar_paneles(((String) combo_intento_color_contrincante_4.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_contrincante_4ActionPerformed

    private void boton_intento_comprobar_contrincante_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_intento_comprobar_contrincante_usuarioActionPerformed
        correcto = intento(clave_contrincante, combinacion_contrincante(), contrincante);
        if (((Integer.parseInt(this.contenido_intento_contrincante.getText()) < intentos) && (!entrenamiento))&&!correcto) {
            this.jPanel_partida_contrincante.setVisible(false);
            this.jPanel_partida_usuario.setVisible(true);
            this.panel_puntuaciones_usuario.setVisible(true);
            this.jFrame_partida.setVisible(true);
            this.jLabel_contenido.setText(usuario_actual.getUsuario());
        }
        if(correcto && !entrenamiento){
             this.jLabel_ajustes.setText("¡GANADOR!");
             this.jLabel_contenido.setText(contrincante.getUsuario());
             this.jFrame_partida.setVisible(true);
        }
        
    }//GEN-LAST:event_boton_intento_comprobar_contrincante_usuarioActionPerformed

    private void boton_volver_contrincanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_volver_contrincanteActionPerformed
        panel_visible(jPanel_menu);
    }//GEN-LAST:event_boton_volver_contrincanteActionPerformed

    private void combo_intento_color_usuario_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_usuario_1ActionPerformed
        panel_intento_color_usuario_1.setBackground(pintar_paneles(((String) combo_intento_color_usuario_1.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_usuario_1ActionPerformed

    private void combo_intento_color_usuario_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_usuario_2ActionPerformed
        panel_intento_color_usuario_2.setBackground(pintar_paneles(((String) combo_intento_color_usuario_2.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_usuario_2ActionPerformed

    private void combo_intento_color_usuario_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_usuario_3ActionPerformed
        panel_intento_color_usuario_3.setBackground(pintar_paneles(((String) combo_intento_color_usuario_3.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_usuario_3ActionPerformed

    private void combo_intento_color_usuario_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intento_color_usuario_4ActionPerformed
        panel_intento_color_usuario_4.setBackground(pintar_paneles(((String) combo_intento_color_usuario_4.getSelectedItem())));
    }//GEN-LAST:event_combo_intento_color_usuario_4ActionPerformed

    private void boton_intento_comprobar_usuario_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_intento_comprobar_usuario_usuarioActionPerformed
        correcto = intento(clave_usuario_actual, combinacion_usuario(), usuario_actual);
        if ((Integer.parseInt(this.contenido_intento_usuario.getText()) < intentos) && (!entrenamiento) && !correcto) {
            this.jPanel_partida_contrincante.setVisible(true);
            this.jPanel_partida_usuario.setVisible(false);
            this.panel_puntuaciones_usuario.setVisible(true);
            this.jFrame_partida.setVisible(true);
            this.jLabel_contenido.setText(contrincante.getUsuario());
        }
        if(correcto && !entrenamiento){
             this.jLabel_ajustes.setText("¡GANADOR!");
             this.jLabel_contenido.setText(usuario_actual.getUsuario());
             this.jFrame_partida.setVisible(true);
        }
        if(correcto && entrenamiento){
            this.jLabel_ajustes.setText("¡CORRECTO!");
            this.jLabel_contenido.setText(usuario_actual.getUsuario());
            this.jFrame_partida.setVisible(true);
            entrenamiento();
        }
    }//GEN-LAST:event_boton_intento_comprobar_usuario_usuarioActionPerformed

    private void boton_volver_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_volver_usuarioActionPerformed
        this.jPanel_partida_usuario.setVisible(false);
        this.jPanel_menu.setVisible(true);

    }//GEN-LAST:event_boton_volver_usuarioActionPerformed

    private void boton_reiniciar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciar_usuarioActionPerformed
        entrenamiento();
    }//GEN-LAST:event_boton_reiniciar_usuarioActionPerformed

    private void jTextField_ajustes_rondasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ajustes_rondasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ajustes_rondasActionPerformed

    private void jButton_ajustes_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ajustes_guardarActionPerformed
        rondas = Integer.parseInt(jTextField_ajustes_rondas.getText());
        intentos = Integer.parseInt(jTextField_ajustes_intentos.getText());
        jFrame_ajustes.setVisible(false);
    }//GEN-LAST:event_jButton_ajustes_guardarActionPerformed

    private void jButton_ajustes_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ajustes_cancelarActionPerformed
        jFrame_ajustes.setVisible(false);
        jTextField_ajustes_rondas.setText(String.valueOf(rondas));
        jTextField_ajustes_intentos.setText(String.valueOf(intentos));
    }//GEN-LAST:event_jButton_ajustes_cancelarActionPerformed

    private void jButton_menu_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_menu_logoutActionPerformed
        usuario_actual = null;
        contrincante = null;
        clave_usuario_actual = null;
        clave_contrincante = null;
        ronda = null;
        partida = null;
        panel_visible(this.jPanel_iniciar_sesion);
    }//GEN-LAST:event_jButton_menu_logoutActionPerformed

    private void jButton_ajustes_guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ajustes_guardar1ActionPerformed
        jFrame_partida.setVisible(false);
        if(correcto && !entrenamiento){
        nueva_ronda();
        jLabel_ajustes.setText("TURNO DE:");
        jLabel_contenido.setText(usuario_actual.getUsuario());
        correcto = false;
        if(correcto && entrenamiento){
            entrenamiento();
        }
    }
        
    }//GEN-LAST:event_jButton_ajustes_guardar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clasificacion_por_victorias();
            this.revalidate();
            this.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clasificacion_por_porcentaje();
            this.revalidate();
            //this.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton_menu_estadisticas_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_menu_estadisticas_usuarioActionPerformed
        jTextField_usuario_estadisticas.setText("");
		jLabel_jugadas_usuario.setText(" ");
		jLabel_ganadas_usuario.setText(" ");
		jLabel_perdidas_usuario.setText(" ");
		jLabel_porcentaje_usuario.setText(" ");
		jLabel_puntos_favor_usuario.setText(" ");
		jLabel_puntos_contra_usuario.setText(" ");
		jLabel_jugadas_rival.setText(" ");
		jLabel_ganadas_rival.setText(" ");
		jLabel_perdidas_rival.setText(" ");
		jLabel_porcentaje_rival.setText(" ");
		jLabel_puntos_favor_rival.setText(" ");
		jLabel_puntos_contra_rival.setText(" ");
		jLabel_administrador_usuario.setText(" ");
		jLabel_administrador_rival.setText(" ");
		jPanel_head_rival.setVisible(false);
		jFrame_head_to_head.setVisible(true);
		jFrame_head_to_head.setSize(800,800);
		jFrame_head_to_head.revalidate();
    }//GEN-LAST:event_jButton_menu_estadisticas_usuarioActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Usuario u;
		u = Usuario.getUsuarioByUsuario(jTextField_rival_estadisticas.getText());
		jLabel_jugadas_rival.setText(u.getPartidas_jugadas() + "");
		jLabel_ganadas_rival.setText(u.getPartidas_ganadas() + "");
		jLabel_perdidas_rival.setText(u.getPartidas_perdidas() + "");
		jLabel_porcentaje_rival.setText(u.getPorcentaje_victorias() + " %");
		jLabel_puntos_favor_rival.setText(u.getPuntos_anotados() + "");
		jLabel_puntos_contra_rival.setText(u.getPuntos_encajados() + "");
		if (u.isAdministrador())
			jLabel_administrador_rival.setText("administrador");
		else
			jLabel_administrador_rival.setText(" ");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField_usuario_estadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_usuario_estadisticasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_usuario_estadisticasActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Usuario u;
		u = Usuario.getUsuarioByUsuario(jTextField_usuario_estadisticas.getText());
		jLabel_jugadas_usuario.setText(u.getPartidas_jugadas() + "");
		jLabel_ganadas_usuario.setText(u.getPartidas_ganadas() + "");
		jLabel_perdidas_usuario.setText(u.getPartidas_perdidas() + "");
		jLabel_porcentaje_usuario.setText(u.getPorcentaje_victorias() + " %");
		jLabel_puntos_favor_usuario.setText(u.getPuntos_anotados() + "");
		jLabel_puntos_contra_usuario.setText(u.getPuntos_encajados() + "");
		if (u.isAdministrador())
			jLabel_administrador_usuario.setText("administrador");
		else
			jLabel_administrador_usuario.setText(" ");
		jFrame_head_to_head.setSize(800,800);
		jFrame_head_to_head.revalidate();
		mostrar_lista_partidas(u);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.jPanel_head_rival.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

	private void jPanel1MouseClicked(java.awt.event.MouseEvent evt, javax.swing.JLabel username) {                                     
        mostrar_info_usuario(username.getText());
    }    
	

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
    private javax.swing.JButton boton_intento_comprobar_contrincante;
    private javax.swing.JButton boton_intento_comprobar_usuario;
    private javax.swing.JButton boton_reiniciar_usuario;
    private javax.swing.JButton boton_volver_contrincante;
    private javax.swing.JButton boton_volver_usuario;
    private javax.swing.JComboBox<String> combo_intento_color_contrincante_1;
    private javax.swing.JComboBox<String> combo_intento_color_contrincante_2;
    private javax.swing.JComboBox<String> combo_intento_color_contrincante_3;
    private javax.swing.JComboBox<String> combo_intento_color_contrincante_4;
    private javax.swing.JComboBox<String> combo_intento_color_usuario_1;
    private javax.swing.JComboBox<String> combo_intento_color_usuario_2;
    private javax.swing.JComboBox<String> combo_intento_color_usuario_3;
    private javax.swing.JComboBox<String> combo_intento_color_usuario_4;
    private javax.swing.JComboBox<String> combo_popup_1;
    private javax.swing.JComboBox<String> combo_popup_2;
    private javax.swing.JComboBox<String> combo_popup_3;
    private javax.swing.JComboBox<String> combo_popup_4;
    private javax.swing.JLabel contenido_intento_contrincante;
    private javax.swing.JLabel contenido_intento_usuario;
    private javax.swing.JLabel contenido_puntuaciones_contrincante;
    private javax.swing.JLabel contenido_puntuaciones_nrondas;
    private javax.swing.JLabel contenido_puntuaciones_usuario;
    private javax.swing.JLabel contenido_ronda_usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton_ajustes_cancelar;
    private javax.swing.JButton jButton_ajustes_guardar;
    private javax.swing.JButton jButton_ajustes_guardar1;
    private javax.swing.JButton jButton_iniciar_sesion;
    private javax.swing.JButton jButton_menu_ajustes;
    private javax.swing.JButton jButton_menu_clasificacion;
    private javax.swing.JButton jButton_menu_entrenamiento;
    private javax.swing.JButton jButton_menu_estadisticas_usuario;
    private javax.swing.JButton jButton_menu_logout;
    private javax.swing.JButton jButton_popup_iniciar_sesion;
    private javax.swing.JButton jButton_popup_registro;
    private javax.swing.JButton jButton_popup_savekey;
    private javax.swing.JButton jButton_registro;
    private javax.swing.JFrame jFrame_ajustes;
    private javax.swing.JFrame jFrame_head_to_head;
    private javax.swing.JFrame jFrame_partida;
    private javax.swing.JFrame jFrame_popup;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_administrador_rival;
    private javax.swing.JLabel jLabel_administrador_usuario;
    private javax.swing.JLabel jLabel_ajustes;
    private javax.swing.JLabel jLabel_ajustes_intentos;
    private javax.swing.JLabel jLabel_ajustes_rondas;
    private javax.swing.JLabel jLabel_contenido;
    private javax.swing.JLabel jLabel_contrasena;
    private javax.swing.JLabel jLabel_error_iniciar_sesion;
    private javax.swing.JLabel jLabel_ganadas_rival;
    private javax.swing.JLabel jLabel_ganadas_usuario;
    private javax.swing.JLabel jLabel_jugadas_rival;
    private javax.swing.JLabel jLabel_jugadas_usuario;
    private javax.swing.JLabel jLabel_menu_title;
    private javax.swing.JLabel jLabel_nombre_usuario;
    private javax.swing.JLabel jLabel_perdidas_rival;
    private javax.swing.JLabel jLabel_perdidas_usuario;
    private javax.swing.JLabel jLabel_popup_contrasena;
    private javax.swing.JLabel jLabel_popup_error_iniciar_sesion;
    private javax.swing.JLabel jLabel_popup_nombre_usuario;
    private javax.swing.JLabel jLabel_popup_secretkey;
    private javax.swing.JLabel jLabel_popup_user;
    private javax.swing.JLabel jLabel_porcentaje_rival;
    private javax.swing.JLabel jLabel_porcentaje_usuario;
    private javax.swing.JLabel jLabel_puntos_contra_rival;
    private javax.swing.JLabel jLabel_puntos_contra_usuario;
    private javax.swing.JLabel jLabel_puntos_favor_rival;
    private javax.swing.JLabel jLabel_puntos_favor_usuario;
    private javax.swing.JPanel jPanel_ajustes;
    private javax.swing.JPanel jPanel_ajustes_campos;
    private javax.swing.JPanel jPanel_border_lateral;
    private javax.swing.JPanel jPanel_campos;
    private javax.swing.JPanel jPanel_campos1;
    private javax.swing.JPanel jPanel_clasificacion;
    private javax.swing.JPanel jPanel_head_rival;
    private javax.swing.JPanel jPanel_head_usuario;
    private javax.swing.JPanel jPanel_iniciar_sesion;
    private javax.swing.JPanel jPanel_list_contrincante;
    private javax.swing.JPanel jPanel_list_usuario;
    private javax.swing.JPanel jPanel_login_contrincante;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_menu_inicio;
    private javax.swing.JPanel jPanel_menu_lateral;
    private javax.swing.JPanel jPanel_partida_contrincante;
    private javax.swing.JPanel jPanel_partida_usuario;
    private javax.swing.JPanel jPanel_partidas;
    private javax.swing.JPanel jPanel_popup_color_1;
    private javax.swing.JPanel jPanel_popup_color_2;
    private javax.swing.JPanel jPanel_popup_color_3;
    private javax.swing.JPanel jPanel_popup_color_4;
    private javax.swing.JPanel jPanel_popup_key;
    private javax.swing.JPanel jPanel_readkey;
    private javax.swing.JPanel jPanel_rondas_usuario;
    private javax.swing.JPanel jPanel_turn;
    private javax.swing.JPanel jPanel_turn_contenido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScroll_list_contrincante;
    private javax.swing.JScrollPane jScroll_list_usuario;
    private javax.swing.JTextField jTextField_ajustes_intentos;
    private javax.swing.JTextField jTextField_ajustes_rondas;
    private javax.swing.JTextField jTextField_contrasena;
    private javax.swing.JTextField jTextField_nombre_usuario;
    private javax.swing.JTextField jTextField_popup_contrasena;
    private javax.swing.JTextField jTextField_popup_nombre_usuario;
    private javax.swing.JTextField jTextField_rival_estadisticas;
    private javax.swing.JTextField jTextField_usuario_estadisticas;
    private javax.swing.JButton jbutton_newgame;
    private javax.swing.JLabel label_bienvenido;
    private javax.swing.JLabel label_intento_contrincante;
    private javax.swing.JLabel label_intento_usuario;
    private javax.swing.JLabel label_juego;
    private javax.swing.JLabel label_nombre_contrincante;
    private javax.swing.JLabel label_nombre_usuario;
    private javax.swing.JLabel label_puntuaciones_contrincante;
    private javax.swing.JLabel label_puntuaciones_username;
    private javax.swing.JLabel label_puntuaciones_usuario;
    private javax.swing.JLabel label_ronda_usuario;
    private javax.swing.JPanel panel_intento_color_contrincante_1;
    private javax.swing.JPanel panel_intento_color_contrincante_2;
    private javax.swing.JPanel panel_intento_color_contrincante_3;
    private javax.swing.JPanel panel_intento_color_contrincante_4;
    private javax.swing.JPanel panel_intento_color_usuario_1;
    private javax.swing.JPanel panel_intento_color_usuario_2;
    private javax.swing.JPanel panel_intento_color_usuario_3;
    private javax.swing.JPanel panel_intento_color_usuario_4;
    private javax.swing.JPanel panel_intento_contrincante;
    private javax.swing.JPanel panel_intento_usuario;
    private javax.swing.JPanel panel_intentos_contrincante;
    private javax.swing.JPanel panel_intentos_usuario;
    private javax.swing.JPanel panel_puntuaciones_usuario;
    // End of variables declaration//GEN-END:variables

	private void mostrar_info_usuario(String text) {
		
	}
	
	public javax.swing.JPanel crear_panel_partida(Partida partida)
	{
		JPanel jPanel_partida = new javax.swing.JPanel();
        JLabel jLabel_lista_partidas_usuario = new javax.swing.JLabel();
        JLabel jLabel_versus = new javax.swing.JLabel();
        JLabel jLabel_lista_partidas_rival = new javax.swing.JLabel();
        JLabel jLabel_numero_de_rondas = new javax.swing.JLabel();
        JLabel jLabel_rondas = new javax.swing.JLabel();
        JLabel jLabel_puntos_rival = new javax.swing.JLabel();
        JLabel victoria_usuario = new javax.swing.JLabel();
        JLabel jLabel_puntos_usuario = new javax.swing.JLabel();
        JLabel jLabel_fecha = new javax.swing.JLabel();
        JLabel victoria_rival = new javax.swing.JLabel();

        jPanel_partida.setBackground(new java.awt.Color(243, 230, 0));
        jPanel_partida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_partida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                partida_clicked(partida.getId_partida());
            }
        });

        jLabel_lista_partidas_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_lista_partidas_usuario.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_lista_partidas_usuario.setText("Pepe");

        jLabel_versus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_versus.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_versus.setText("VS");

        jLabel_lista_partidas_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_lista_partidas_rival.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_lista_partidas_rival.setText("Juan");

        jLabel_numero_de_rondas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_numero_de_rondas.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_numero_de_rondas.setText("Número de rondas:");

        jLabel_rondas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_rondas.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_rondas.setText("3");

        jLabel_puntos_rival.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_puntos_rival.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_puntos_rival.setText("10");

        victoria_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icon/baseline_star_black_18dp.png"))); // NOI18N

        jLabel_puntos_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_puntos_usuario.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_puntos_usuario.setText("18");

        jLabel_fecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_fecha.setForeground(new java.awt.Color(60, 63, 65));
        jLabel_fecha.setText("20/01/21");

        victoria_rival.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icon/baseline_star_black_18dp.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_partidaLayout = new javax.swing.GroupLayout(jPanel_partida);
        jPanel_partida.setLayout(jPanel_partidaLayout);
        jPanel_partidaLayout.setHorizontalGroup(
            jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_partidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_partidaLayout.createSequentialGroup()
                        .addComponent(victoria_usuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_puntos_usuario)
                            .addComponent(jLabel_lista_partidas_usuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_versus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_partidaLayout.createSequentialGroup()
                                .addComponent(jLabel_puntos_rival)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel_partidaLayout.createSequentialGroup()
                                .addComponent(jLabel_lista_partidas_rival)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(victoria_rival)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addComponent(jLabel_fecha))))
                    .addGroup(jPanel_partidaLayout.createSequentialGroup()
                        .addComponent(jLabel_numero_de_rondas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_rondas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_partidaLayout.setVerticalGroup(
            jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_partidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(victoria_usuario)
                    .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_lista_partidas_usuario)
                        .addComponent(jLabel_versus)
                        .addComponent(jLabel_lista_partidas_rival))
                    .addComponent(jLabel_fecha)
                    .addComponent(victoria_rival))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_puntos_usuario)
                    .addComponent(jLabel_puntos_rival))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_partidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_numero_de_rondas)
                    .addComponent(jLabel_rondas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
		System.out.println(jPanel_partida.toString());
		return (jPanel_partida);
	}

	private void partida_clicked(int id_partida) {
		Partida partida_solicitada;
		
		partida_solicitada = Partida.getPartidabyId_partida(id_partida);
		System.out.println("-------------------------------------------");
		
	}

	private void mostrar_lista_partidas(Usuario u) {
		JPanel panel_partida;
		ArrayList<Partida> lista_partidas;
		LayoutManager f;
		int i;
		
		if (!(jLabel_jugadas_rival.getText().equals("")))
			lista_partidas = Partida.getPartidasbyUsuario(u);
		else
			lista_partidas = Partida.getPartidasbyUsuarioandRival(u, Usuario.getUsuarioByUsuario(jTextField_rival_estadisticas.getText()));
		i = 0;
		//lista_partidas = Partida.getLista_partidas();
		System.out.println("$$$$$$$$$$$$" + Partida.getLista_partidas().toString());
		while (i < lista_partidas.size())
		{
			System.out.println("%%%%%%%%%%%%%%%%");
			panel_partida = crear_panel_partida(lista_partidas.get(i));
			System.out.println(panel_partida.toString());
			this.jPanel_partidas.add(panel_partida,jPanel_partidas.getComponentCount());
            f = this.jPanel_ajustes.getLayout();
            f.addLayoutComponent("panel_partida", panel_partida);
			jPanel_ajustes.setLayout(f);
			i++;
		}
		this.jPanel_partidas.revalidate();
		this.jPanel_partidas.repaint();
	}
}
