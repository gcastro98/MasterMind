package mastermind;

import java.util.Comparator;

public class PercentageSorter implements Comparator<Usuario> {

    @Override
    public int compare(Usuario usuario1, Usuario usuario2) {
        Integer u1;
        Integer u2;

        u1 = usuario1.getPorcentaje_victorias();
        u2 = usuario2.getPorcentaje_victorias();
        return u2.compareTo(u1);
    }
}
