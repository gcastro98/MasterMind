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
}
