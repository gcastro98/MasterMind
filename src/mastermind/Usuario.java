package mastermind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Usuario implements Serializable{

    private String usuario;
    private String clave;
    private boolean administrador;
    private int partidas_jugadas;
    private int partidas_ganadas;
    private int partidas_perdidas;
    private int puntos_anotados;
    private int puntos_encajados;
    private int porcentaje_victorias;
    private static ArrayList<Usuario> lista_usuarios;

    public Usuario(String usuario, String clave, boolean administrador) {
        this.usuario = usuario;
        this.clave = clave;
        this.administrador = administrador;
        this.partidas_jugadas = 0;
        this.partidas_ganadas = 0;
        this.partidas_perdidas = 0;
        this.puntos_anotados = 0;
        this.puntos_encajados = 0;
        this.porcentaje_victorias = 0;
    }

    public Usuario(String usuario, String clave, boolean administrador, int partidas_jugadas, int partidas_ganadas, int partidas_perdidas, int puntos_anotados, int puntos_encajados) {
        this.usuario = usuario;
        this.clave = clave;
        this.administrador = administrador;
        this.partidas_jugadas = partidas_jugadas;
        this.partidas_ganadas = partidas_ganadas;
        this.partidas_perdidas = partidas_perdidas;
        this.puntos_anotados = puntos_anotados;
        this.puntos_encajados = puntos_encajados;
        this.porcentaje_victorias = partidas_ganadas / partidas_jugadas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public int getPartidas_jugadas() {
        return partidas_jugadas;
    }

    public void setPartidas_jugadas(int partidas_jugadas) {
        this.partidas_jugadas = partidas_jugadas;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public int getPartidas_perdidas() {
        return partidas_perdidas;
    }

    public void setPartidas_perdidas(int partidas_perdidas) {
        this.partidas_perdidas = partidas_perdidas;
    }

    public int getPuntos_anotados() {
        return puntos_anotados;
    }

    public void setPuntos_anotados(int puntos_anotados) {
        this.puntos_anotados = puntos_anotados;
    }

    public int getPuntos_encajados() {
        return puntos_encajados;
    }

    public void setPuntos_encajados(int puntos_encajados) {
        this.puntos_encajados = puntos_encajados;
    }

    public int getPorcentaje_victorias() {
        return porcentaje_victorias;
    }

    public void setPorcentaje_victorias(int porcentaje_victorias) {
        this.porcentaje_victorias = porcentaje_victorias;
    }

    public static ArrayList<Usuario> getLista_usuarios() {
        return lista_usuarios;
    }

    public static void setLista_usuarios(ArrayList<Usuario> lista_usuarios) {
        Usuario.lista_usuarios = lista_usuarios;
    }

    public static boolean addUsuario(Usuario usuario) {
        if (!Usuario.lista_usuarios.contains(usuario)) {
            Usuario.lista_usuarios.add(usuario);
            return (true);
        }
        return (false);
    }

    public static boolean removeUsuario(Usuario usuario) {
        if (Usuario.lista_usuarios.contains(usuario)) {
            Usuario.lista_usuarios.remove(usuario);
            return (true);
        }
        return (false);
    }
    public static boolean usernameExiste(String username){
        for(Usuario usuario : lista_usuarios){
            if ((usuario.getUsuario()).equals(username)) return true;
        }
        return false;
    }
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Usuario usuario1 = (Usuario) object;
        return isAdministrador() == usuario1.isAdministrador()
                && getPartidas_jugadas() == usuario1.getPartidas_jugadas()
                && getPartidas_ganadas() == usuario1.getPartidas_ganadas()
                && getPartidas_perdidas() == usuario1.getPartidas_perdidas()
                && getPuntos_anotados() == usuario1.getPuntos_anotados()
                && getPuntos_encajados() == usuario1.getPuntos_encajados()
                && getPorcentaje_victorias() == usuario1.getPorcentaje_victorias()
                && java.util.Objects.equals(getUsuario(), usuario1.getUsuario())
                && java.util.Objects.equals(getClave(), usuario1.getClave());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "\nUsuario='" + usuario + '\''
                + "\n, partidas_jugadas=" + partidas_jugadas
                + "\n, partidas_ganadas=" + partidas_ganadas
                + "\n, partidas_perdidas=" + partidas_perdidas
                + "\n, puntos_anotados=" + puntos_anotados
                + "\n, puntos_encajados=" + puntos_encajados
                + "\n, porcentaje_victorias=" + porcentaje_victorias
                + '}';
    }
	 public void actualizar_usuario()
	 {
		 double d;
		 
		 d  = 0;
		 this.partidas_perdidas = this.partidas_jugadas - this.partidas_ganadas;
		 System.out.println(this.usuario + " " + this.partidas_jugadas);
		 if (partidas_jugadas != 0)
		 {
			 d = ((double)this.partidas_ganadas / (double)this.partidas_jugadas) * 100;
		 System.out.println(this.usuario + " " + d);
			 this.porcentaje_victorias = (int) d;
		 }
		 System.out.println(this.usuario + " " + d);
	 }
	 
	public static Usuario getUsuarioByUsuario(String usuario)
	{
		int	i;
		ArrayList<Usuario> lista = Usuario.getLista_usuarios();
		
		i = 0;
		while (i < Usuario.getLista_usuarios().size())
		{
			if (lista.get(i).getUsuario().equals(usuario))
				return (Usuario.getLista_usuarios().get(i));
			i++;
		}
		return (null);
	}
}
