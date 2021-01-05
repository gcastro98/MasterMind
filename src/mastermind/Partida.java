package mastermind;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Partida {
	private Usuario						local;
	private Usuario						visitante;
	private LocalDate					fecha;	
	private ArrayList<Ronda>			rondas;
	private static ArrayList<Partida>	lista_partidas;
	
	private Partida(Usuario local, Usuario visitante)
	{
		this.local = local;
		this.visitante = visitante;
		this.fecha = LocalDate.now();
		this.rondas = new ArrayList<Ronda>();
	}
	
	public static Partida jugar(Usuario local, Usuario visitante)
	{
		Partida.lista_partidas.add(new Partida(local, visitante));
		return (Partida.lista_partidas.get(Partida.lista_partidas.size() - 1));
	}
	
	public void nueva_ronda(Combinacion clave1, Combinacion clave2)
	{
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
		return "Partida{" + "local=" + local.toString() + ", visitante=" +
				visitante.toString() + ", fecha=" + fecha.toString() +
				", rondas=" + rondas.toString() + '}';
	}
}
