package mastermind;

import java.util.ArrayList;

public class Login {

    public static Usuario sign_in(String usuario, String clave) {
        int i;
        ArrayList<Usuario> lista_usuarios;

        i = 0;
        lista_usuarios = Usuario.getLista_usuarios();
        while (i < lista_usuarios.size()) {
            if ((lista_usuarios.get(i).getUsuario().equals(usuario)) && (lista_usuarios.get(i).getClave().equals(clave))) {
                return (lista_usuarios.get(i));
            }
            i++;
        }
        return (null);
    }

    public static Usuario registro(String usuario, String clave) {
        Usuario user = new Usuario(usuario, clave, false);
        Usuario.addUsuario(user);
        return user;
    }
}
