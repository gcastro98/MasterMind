/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.io.Serializable;

/**
 *
 * @author sigui
 */
public class Ronda implements Serializable{

    private Usuario usuario1;
    private Usuario usuario2;
    private int puntuacion1;
    private int puntuacion2;
    private Combinacion clave1;
    private Combinacion clave2;

    public Ronda(Usuario usuario1, Usuario usuario2, Combinacion clave1, Combinacion clave2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.clave1 = clave1;
        this.clave2 = clave2;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public int getPuntuacion1() {
        return puntuacion1;
    }

    public void setPuntuacion1(int puntuacion1) {
        this.puntuacion1 = puntuacion1;
    }

    public int getPuntuacion2() {
        return puntuacion2;
    }

    public void setPuntuacion2(int puntuacion2) {
        this.puntuacion2 = puntuacion2;
    }

    public Combinacion getClave1() {
        return clave1;
    }

    public void setClave1(Combinacion clave1) {
        this.clave1 = clave1;
    }

    public Combinacion getClave2() {
        return clave2;
    }

    public void setClave2(Combinacion clave2) {
        this.clave2 = clave2;
    }
    
    
    public void combinacionGanadora(Combinacion combinacion, Combinacion clave) {
        if (combinacion.equals(clave)) {
            System.out.println("Es correcta");
        } else {
            int numeros_posicionados = 0;
            int numeros_correctos = 0;
            for (int i = 0; i < 4; i++) {
                if (combinacion.getCodigo()[i] == clave.getCodigo()[i]) {
                    numeros_posicionados++;
                }
            }
        }

    }

	@Override
	public String toString() {
		return "Ronda{" + "usuario1=" + usuario1.toString() + ", usuario2=" + usuario2.toString() + ", puntuacion1=" + puntuacion1 + ", puntuacion2=" + puntuacion2 + ", clave1=" + clave1.toString() + ", clave2=" + clave2.toString() + '}';
	}
	
}
