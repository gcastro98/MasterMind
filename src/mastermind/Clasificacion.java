package mastermind;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clasificacion {

    public static boolean volcar_a_txt(File fichero) {
        FileWriter writer;
        int i;
        ArrayList<Usuario> lista_ordenada;
        WinsSorter ws;
        try {

            writer = new FileWriter(fichero);
            if (fichero.createNewFile()) {
                System.out.println("File created: " + fichero.getName());
            } else {
                System.out.println("File already exists.");
            }
            i = 0;
            ws = new WinsSorter();
            lista_ordenada = (ArrayList<Usuario>) Usuario.getLista_usuarios().clone();
            lista_ordenada.sort(ws);
            while (i < Usuario.getLista_usuarios().size()) {
                writer.write(lista_ordenada.get(i).toString());
                writer.write("\n");
            }
            return (true);
        } catch (IOException ex) {
            Logger.getLogger(Clasificacion.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }

    }

    public static List<Usuario> calificacion_por_victorias() {
		//System.out.println(Usuario.getLista_usuarios().toString());
        List<Usuario> lista_ordenada = Usuario.getLista_usuarios().subList(0, Usuario.getLista_usuarios().size());
        lista_ordenada.sort(new WinsSorter());
		//System.out.println(lista_ordenada.toString());
        return (lista_ordenada);
    }

    public static List<Usuario> calificacion_por_porcentaje() {
		//System.out.println(Usuario.getLista_usuarios().toString());
        List<Usuario> lista_ordenada = Usuario.getLista_usuarios().subList(0, Usuario.getLista_usuarios().size());
        lista_ordenada.sort(new PercentageSorter());
        return (lista_ordenada);
    }
}
