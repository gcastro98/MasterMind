/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author sigui
 */
public class Combinacion implements Serializable{

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

	public static int[] comprobacion(Colour[] clave, Colour[] codigo) // int[0] contenidos e int[1] posicionados;
	{
		int resultado[];
		int frecuencias_clave[];
		int frecuencias_codigo[];
		int i;
		
		resultado = new int[2];
		resultado[0] = 0;
		resultado[1] = 0;
		frecuencias_clave = frecuencia(clave);
		frecuencias_codigo = frecuencia(codigo);
		i = 0;
		while (i < 6)
		{
			resultado[0] += minimo(frecuencias_clave[i], frecuencias_codigo[i]);
		}
		i = 0;
		while (i < 4)
		{
			if (clave[i].equals(codigo[i]))
				resultado[1]++;
			i++;
		}
		return (resultado);
	}
	
	public static int minimo(int i1, int i2)
	{
		if (i1 < i2)
			return (i1);
		return (i2);
	}
	
	public static int[] frecuencia(Colour[] clave)
	{
		int frecuencias_clave[];
		int i;
		
		frecuencias_clave = new int[6];
		i = 0;
		while (i < 4)
		{
			switch (clave[i].name())
			{
				case "Blanco" :
					frecuencias_clave[0]++;
					break;
				case "Negro" :
					frecuencias_clave[1]++;
					break;
				case "Azul" :
					frecuencias_clave[2]++;
					break;
				case "Rojo" :
					frecuencias_clave[3]++;
					break;
				case "Verde" :
					frecuencias_clave[4]++;
					break;
				case "Marron" :
					frecuencias_clave[5]++;
					break;
			}
			i++;
		}
		return (frecuencias_clave);
	}
}
