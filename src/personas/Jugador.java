package personas;

import java.util.ArrayList;

public class Jugador {
    public String nombre;
    public ArrayList<Integer> manoInglesa;
    public String manoEspecial;
    public int suma = 0;
    public int manoAnterior = 0;
    public int puntos;

    public Jugador(String nombre, ArrayList<Integer> manoInglesa, String manoEspecial, int puntos) {
        this.nombre = nombre;
        this.manoInglesa = manoInglesa;
        this.manoEspecial = manoEspecial;
        this.puntos = puntos;
    }

    public int getManoAnterior() {
        return manoAnterior;
    }

    public void setManoAnterior(int manoAnterior) {
        this.manoAnterior = manoAnterior;
    }

    public int sumarMano() {
        suma = 0;
        for (int i = 0; i < manoInglesa.size(); i++) {
            suma += manoInglesa.get(i);
        }
        return suma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getManoInglesa() {
        return manoInglesa;
    }

    public void setManoInglesa(ArrayList<Integer> manoInglesa) {
        this.manoInglesa = manoInglesa;
    }

    public String getManoEspecial() {
        return manoEspecial;
    }

    public void setManoEspecial(String manoEspecial) {
        this.manoEspecial = manoEspecial;
    }
}
