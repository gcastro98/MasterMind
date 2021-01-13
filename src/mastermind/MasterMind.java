package mastermind;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterMind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*if (args.length > 0)
		{
			cargar_datos(args[0]);
		}
		else
		{
			cargar_datos("datos.bin");
		}*/
        Partida.setLista_partidas(new ArrayList<Partida>());
        Usuario.setLista_usuarios(new ArrayList<Usuario>());
        Usuario.addUsuario(new Usuario("admin", "admin", true));
        IU iu = new IU();
        iu.setVisible(true);
    }

    public static void cargar_datos(String ruta) throws IOException, ClassNotFoundException {
        leer_binario();
        if (no_hay_datos()) {
            crear_datos_prueba();
        }
    }

    public static void guardar_datos() throws IOException {
        FileOutputStream fos = null;
        FileOutputStream fos2 = null;
        try {
            fos = new FileOutputStream("usuarios.bin");
            fos2 = new FileOutputStream("partidas.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos.writeObject(Usuario.getLista_usuarios());
            oos2.writeObject(Partida.getLista_partidas());
            oos.close();
            oos2.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MasterMind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void leer_binario() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        FileInputStream fis2 = null;
        try {
            fis = new FileInputStream("usuarios.bin");
            fis2 = new FileInputStream("partidas.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            Usuario.setLista_usuarios((ArrayList<Usuario>) ois.readObject());
            Partida.setLista_partidas((ArrayList<Partida>) ois2.readObject());
            ois.close();
            ois2.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MasterMind.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fis2 != null) {
                    fis2.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MasterMind.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static boolean no_hay_datos() {
        if (Usuario.getLista_usuarios() == null) {
            return (true);
        }
        if (Partida.getLista_partidas() == null) {
            return (true);
        }
        return (false);
    }

    private static void crear_datos_prueba() {
        if (Usuario.getLista_usuarios() == null) {
            Usuario.addUsuario(new Usuario("admin", "admin", true));
        }
        if (Partida.getLista_partidas() == null) {

        }
    }
}
