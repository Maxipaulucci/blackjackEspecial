package barajas;

import java.util.ArrayList;
import java.util.Random;

public class BarajaInglesa {

    private ArrayList<Integer> numeros = new ArrayList<>();

    public BarajaInglesa() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 0; j < 4; j++) {
                numeros.add(i);
            }
        }

        for (int j = 0; j < 16; j++) {
            numeros.add(10);
        }

    }

    public ArrayList<Integer> repartirCarta() {
        ArrayList<Integer> listaCartas = new ArrayList<>();
        Random random = new Random();
        listaCartas.add(numeros.get(random.nextInt(numeros.size())));
        listaCartas.add(numeros.get(random.nextInt(numeros.size())));
        return listaCartas;
    }

    public int repartirUnaCarta() {
        Random random = new Random();
        return numeros.get(random.nextInt(numeros.size()));
    }

    public int sumaMano(ArrayList<Integer> listaCartas) {
        int suma = 0;
        for (int carta : listaCartas) {
            suma += carta;
        }
        return suma;
    }

}
