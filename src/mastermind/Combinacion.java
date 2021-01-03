/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.Random;

/**
 *
 * @author sigui
 */
public class Combinacion {
    private enum Color{ B , N , A , R , V , M;
          
    public static Color getRandomColor() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
     }
    }
 
    private Color[] codigo;
    
    public Combinacion(){
        Random r = new Random();
        Color[] color = new Color[4];
        for (int i=0; i<3; i++){
            color[i]=Color.getRandomColor();
        }
        this.codigo = color;
    }
    public Combinacion(Color[] codigo){
        this.codigo = codigo;
    }

    public Color[] getCodigo() {
        return codigo;
    }

    public void setCodigo(Color[] codigo) {
        this.codigo = codigo;
    }
    
    public boolean esGanadora(Combinacion combinacion){
        return codigo.equals(combinacion);
    }
    
    public boolean contains(Color color){
        for(int i=0;i<4;i++){
            if(this.codigo[i]==color) return true;
        }
        return false;
    }
    public boolean posicionado(Color color, int i){
        return (this.codigo[i]==color);
    }

    @Override
    public String toString() {
        return "Combinacion{" + "codigo=" + codigo + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (obj==this) return true;
        if (obj.getClass() != this.getClass()) return false;
        Combinacion combinacion = (Combinacion) obj;
        return combinacion.getCodigo()[0]==this.codigo[0];
    }
    
    
    
    
}
