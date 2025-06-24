package barajas;

import java.util.ArrayList;
import java.util.Random;

public class BarajaEspecial {

    private ArrayList<String> especiales = new ArrayList<>();

    public BarajaEspecial() {
        especiales.add("Sumador");
        especiales.add("Cambio de Carta");
    }

    public String repartirCarta() {
        Random random = new Random();
        String cartaEspecial = especiales.get(random.nextInt(especiales.size()));
        return cartaEspecial;
    }
}
