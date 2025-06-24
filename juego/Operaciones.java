package juego;

import barajas.BarajaInglesa;
import personas.Jugador;
import personas.Crupier;

// ... existing code ...

// En funcionAs, iterar hacia atrás para eliminar elementos de la lista sin
// errores:
// for (int i = jugador.getManoInglesa().size() - 1; i >= 0; i--) { ... }

boolean funcionAs(Jugador jugador){boolean res=false;for(int i=jugador.getManoInglesa().size()-1;i>=0;i--){if(jugador.getManoInglesa().get(i)==11){res=true;jugador.getManoInglesa().remove(i);}}return res;}