/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model.piezas;

/**
 * Pieza de la reina, sus movimientos són n casillas en vertical, horizontal o
 * cualquiera de las dos diagonales.
 */
public class Reina extends Pieza {
    
    public Reina(int d) {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = "Reina";
        imagen = "src/imagenes/reina.png";
        movx = new int[(d - 1) * 4 * 2];
        movy = new int[(d - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0;   // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i;   // horizontal
                movy[pos++] = 0; //horizontal
                movx[pos] = i;   //   oblicuo 1
                movy[pos++] = i; //    oblicuo1
                movx[pos] = -i;  //   oblicuo 2
                movy[pos++] = i; //    oblicuo2               
            }
        }
    }
}
