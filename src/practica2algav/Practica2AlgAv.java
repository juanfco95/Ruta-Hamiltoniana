/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package practica2algav;

import mesurament.Mesurament;
import view.MainWindow;

/**
 * Clase principal que crea una nueva ventana desde la cual se inicializan todas
 * las variables y punteros necesarios.
 */
public class Practica2AlgAv {
    
    private void inicio() {
        Mesurament.mesura();
        MainWindow view = new MainWindow(600, 800);        
        view.setVisible(true);
    }

    public static void main(String[] args) {
        (new Practica2AlgAv()).inicio();
    }
    
}
