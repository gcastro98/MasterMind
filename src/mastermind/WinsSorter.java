package mastermind;

import java.util.Comparator;

public class WinsSorter implements Comparator<Usuario>
{
	@Override
	public int compare(Usuario usuario1, Usuario usuario2) {
            Integer u1;
            Integer u2;
            
            u1 = usuario1.getPartidas_ganadas();
            u2 = usuario2.getPartidas_ganadas();
            return u1.compareTo(u2);
	}
}