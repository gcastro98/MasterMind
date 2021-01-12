/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

/**
 *
 * @author sigui
 */
public class Ronda {
    
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
    
    public void combinacionGanadora(Combinacion combinacion, Combinacion clave){
        if(combinacion.equals(clave)){
            System.out.println("Es correcta");
        }else{
            int numeros_posicionados = 0;
            int numeros_correctos = 0;
            for(int i=0; i<4;i++){
                if(combinacion.getCodigo()[i]==clave.getCodigo()[i]){
                    numeros_posicionados++;
                }
            }
        }
        
    }
}

