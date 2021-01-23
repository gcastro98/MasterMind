package mastermind;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Partida implements Serializable{

    private Usuario local;
    private Usuario visitante;
    private LocalDate fecha;
    private ArrayList<Ronda> rondas;
	private final int id_partida;
    private static ArrayList<Partida> lista_partidas;

    public Partida(Usuario local, Usuario visitante) {
        this.local = local;
        this.visitante = visitante;
        this.fecha = LocalDate.now();
        this.rondas = new ArrayList<>();
		this.id_partida = lista_partidas.size();
    }

    public static Partida jugar(Usuario local, Usuario visitante) {
        Partida.lista_partidas.add(new Partida(local, visitante));
        return (Partida.lista_partidas.get(Partida.lista_partidas.size() - 1));
    }

    public void nueva_ronda(Combinacion clave1, Combinacion clave2) {
        this.rondas.add(new Ronda(this.local, this.visitante, clave1, clave2));
    }

    public Usuario getLocal() {
        return local;
    }

    public void setLocal(Usuario local) {
        this.local = local;
    }

    public Usuario getVisitante() {
        return visitante;
    }

    public void setVisitante(Usuario visitante) {
        this.visitante = visitante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

	public int getId_partida() {
		return id_partida;
	}

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(ArrayList<Ronda> rondas) {
        this.rondas = rondas;
    }

    public static ArrayList<Partida> getLista_partidas() {
        return lista_partidas;
    }

    public static void setLista_partidas(ArrayList<Partida> lista_partidas) {
        Partida.lista_partidas = lista_partidas;
    }
    
    public static void addPartida(Partida partida){
        lista_partidas.add(partida);
    }
    
    public void addRonda(Ronda ronda){
        rondas.add(ronda);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partida other = (Partida) obj;
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.visitante, other.visitante)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.rondas, other.rondas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Partida{" + "local=" + local.toString() + ", visitante="
                + visitante.toString() + ", fecha=" + fecha.toString()
                + ", rondas=" + rondas.toString() + '}';
    }

	public static Partida getPartidabyId_partida(int id_partida) {
		return (lista_partidas.get(id_partida));
	}

	static ArrayList<Partida> getPartidasbyUsuario(Usuario u) {
		int i;
		ArrayList<Partida> lista_partidas_usuario;

		lista_partidas_usuario = (ArrayList<Partida>) getLista_partidas().clone();
		i = lista_partidas_usuario.size() - 1;
		while (i >= 0)
		{
			if (!((lista_partidas_usuario.get(i).getLocal().equals(u))
					|| (lista_partidas_usuario.get(i).getVisitante().equals(u))))
			{
				lista_partidas_usuario.remove(i);
			}
			i--;
		}
		Collections.reverse(lista_partidas_usuario);
		return (lista_partidas_usuario);
	}

	static ArrayList<Partida> getPartidasbyUsuarioandRival(Usuario u1, Usuario u2) {
		int i;
		ArrayList<Partida> lista_partidas_usuario_and_rival;

		lista_partidas_usuario_and_rival = (ArrayList<Partida>) getLista_partidas().clone();
		i = lista_partidas_usuario_and_rival.size() - 1;
		while (i >= 0)
		{
			if (!(((lista_partidas_usuario_and_rival.get(i).getLocal().equals(u1)) 
					&& (lista_partidas_usuario_and_rival.get(i).getVisitante().equals(u2))) 
					|| ((lista_partidas_usuario_and_rival.get(i).getLocal().equals(u2)) 
					&& (lista_partidas_usuario_and_rival.get(i).getVisitante().equals(u2)))))
			{
				lista_partidas_usuario_and_rival.remove(i);
			}
			i--;
		}
		Collections.reverse(lista_partidas_usuario_and_rival);
		return (lista_partidas_usuario_and_rival);
	}
}
