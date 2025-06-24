package personas;

import java.util.ArrayList;

public class Crupier {
    private ArrayList<Integer> mano;
    int suma = 0;

    public int sumarMano() {
        suma = 0;
        for (int i = 0; i < mano.size(); i++) {
            suma += mano.get(i);
        }
        return suma;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public Crupier(ArrayList<Integer> mano) {
        this.mano = mano;
    }

    public int unaCarta() {
        return mano.get(0);
    }

    public ArrayList<Integer> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Integer> mano) {
        this.mano = mano;
    }
}