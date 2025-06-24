package barajas;

import java.util.ArrayList;
import java.util.Random;

public class BarajaEspecial {
    private ArrayList<String> cartasEspeciales = new ArrayList<>();

    public BarajaEspecial() {
        for (int i = 0; i < 2; i++) {
            cartasEspeciales.add("Cambio de Carta");
            cartasEspeciales.add("Sumador");
        }
    }

    public String repartirCarta() {
        Random random = new Random();
        return cartasEspeciales.get(random.nextInt(cartasEspeciales.size()));
    }
}