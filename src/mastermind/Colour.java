package mastermind;

import java.util.Random;

public enum Colour {
    Blanco, Negro, Azul, Rojo, Verde, Marron;

    public static Colour getRandomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return this.name() ;
    }

}
