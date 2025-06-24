package juego;

import java.util.ArrayList;
import barajas.BarajaInglesa;
import personas.Jugador;
import personas.Crupier;

public class Operaciones {

    String calcularJugada(Jugador jugador) {
        BarajaInglesa barajaInglesa = new BarajaInglesa();

        int unaCarta = barajaInglesa.repartirUnaCarta();

        ArrayList<Integer> manoInglesa = jugador.getManoInglesa();
        manoInglesa.add(unaCarta);
        jugador.setManoInglesa(manoInglesa);

        int sumaMano = 0;

        for (int i = 0; i < manoInglesa.size(); i++) {
            sumaMano += manoInglesa.get(i);
        }

        if (sumaMano > 21) {
            return "El jugador " + jugador.getNombre() + " se ha pasado, mano: " + sumaMano;
        } else {
            return "Tu mano es: " + sumaMano;
        }
    }

    void funcion2(Crupier crupier) {
        BarajaInglesa barajaInglesa = new BarajaInglesa();
        ArrayList<Integer> manoCrupier = crupier.getMano();

        int unaCarta = barajaInglesa.repartirUnaCarta();

        manoCrupier.add(unaCarta);
        crupier.setMano(manoCrupier);

    }

    boolean funcionAs(Jugador jugador) {
        boolean res = false;
        for (int i = 0; i < jugador.getManoInglesa().size(); i++) {
            if (jugador.getManoInglesa().get(i) == 11) {
                res = true;
                jugador.getManoInglesa().remove(i);
            }
        }
        return res;
    }

}
