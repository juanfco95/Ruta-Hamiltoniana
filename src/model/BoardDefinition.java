/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model;

import java.awt.Point;
import java.util.ArrayList;
import model.piezas.*;

/**
 * Modelo de la aplicación, aquí se almacenan los datos sobre el tablero, las
 * piezas y las soluciones del algoritmo de backtracking con cada configuración.
 */
public class BoardDefinition {
    
    private boolean casillas [][];
    private int dimension;
    
    private ArrayList<Pieza> piezas; // Lista de piezas en el tablero
    ArrayList<ArrayList<Point>> itineraris; // Lista de movimientos de cada pieza
    
    public BoardDefinition(int d, ArrayList<Pieza> p){
        this.dimension = d;
        this.piezas = p;
        init();
    }
    
    public int getDimension() {
        return this.dimension;
    }
    
    public void setDimension(int d){
        this.dimension = d;
        init();
    }
        
    /**
     * Inicialización del array de casillas.
     */
    private void init(){
        this.casillas = new boolean[this.dimension][this.dimension];
        for (Pieza p : piezas) {
            casillas[p.x][p.y] = true;
        }
    }
    
    public void setC(int fila, int columna){
        this.casillas[fila][columna] = true;
    }
    
    public void removeC(int fila, int columna){
        this.casillas[fila][columna] = false;
    }
    
    public boolean isOcupada(int fila, int columna){
        return this.casillas[fila][columna];
    }
    
    public ArrayList<Pieza> getPiezas(){
        return this.piezas;
    }

    public void setItineraris(ArrayList<ArrayList<Point>> it) {
        this.itineraris = it;
    }
    public ArrayList<ArrayList<Point>> getItineraris(){
        return this.itineraris;
    }
    
}
