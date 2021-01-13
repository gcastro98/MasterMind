/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author sigui
 */
public class Combinacion {

    private Colour[] codigo;

    public Combinacion() {
        Random r = new Random();
        Colour[] color = new Colour[4];
        for (int i = 0; i < 4; i++) {
            color[i] = Colour.getRandomColor();
        }
        this.codigo = color;
    }

    public Combinacion(Colour[] codigo) {
        this.codigo = codigo;
    }

    public Colour[] getCodigo() {
        return codigo;
    }

    public void setCodigo(Colour[] codigo) {
        this.codigo = codigo;
    }

    public boolean esGanadora(Combinacion combinacion) {
        return codigo.equals(combinacion);
    }

    public boolean contains(Colour color) {
        for (int i = 0; i < 4; i++) {
            if (this.codigo[i] == color) {
                return true;
            }
        }
        return false;
    }

    public boolean posicionado(Colour color, int i) {
        return (this.codigo[i] == color);
    }

    @Override
    public String toString() {

        return "Combinacion{" + "codigo=" + Arrays.toString(codigo) + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Combinacion combinacion = (Combinacion) obj;
        return combinacion.getCodigo()[0] == this.codigo[0] & combinacion.getCodigo()[1] == this.codigo[1] & combinacion.getCodigo()[2] == this.codigo[2] & combinacion.getCodigo()[3] == this.codigo[3];
    }

}
