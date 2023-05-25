/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package controller;

import java.awt.*;
import java.util.*;
import model.*;
import model.piezas.Pieza;
import view.MainWindow;
import view.Notificacion;

/**
 * Controlador de la aplicación, encargado de ejecutar el algoritmo de 
 * backtacking para buscar un camino entre todas las piezas para reocorrer todo
 * el tablero.
 */
public class Controller extends Thread{
    
    private String tipo; // Algoritmo que se va a ejecutar
    
    private ArrayList<Pieza> piezas; // Piezas colocadas en el tablero
    private ArrayList<ArrayList<Point>> itineraris; // Movimientos de cada pieza
        
    private MainWindow view;
    private BoardDefinition model;
    
    private int KMAX; // Máximo nivel de profundidad en la recusividad
    
    public Controller (MainWindow v, String t){        
        this.view = v;        
        this.tipo = t;
        init();
    }
    
    /**
     * Inicializa las variables del controlador.
     */
    private void init(){
        this.model = new BoardDefinition(
                view.getBoardPanel().getDimension(),
                view.getBoardPanel().getPiezas()
        );
        
        this.piezas = model.getPiezas();
        this.KMAX = model.getDimension() * model.getDimension() - piezas.size();
        
        this.itineraris = new ArrayList();
        
        int i = 0;
        for(Pieza p : piezas){
            Random rnd = new Random();
            ArrayList<Point> itinerari = new ArrayList();
            itinerari.add(new Point(p.x, p.y));
            p.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
            itineraris.add(itinerari);
            view.getBoardPanel().addPieza(p.x, p.y, p, i++);
        }
        
    }
    
    /**
     * Hilo de ejecución del controlador para realizar los cálculos del
     * algoritmo de backtracking simultánemante a la viualización por pantalla.
     */
    @Override
    public void run() {
        if(piezas.isEmpty()){
            Notificacion n = new Notificacion("No hay ninguna pieza en el tablero");
            return;
        }
        boolean solucionat;
        if("recursivo".equals(tipo)){
            long inicio = System.nanoTime();
            solucionat = Recursivo(piezas.get(0), 0);
            view.setTiempoRespuesta(System.nanoTime()-inicio);
        }else {
            long inicio = System.nanoTime();
            solucionat = Iterativo();
            view.setTiempoRespuesta(System.nanoTime()-inicio);
        }
        if(solucionat){
            this.model.setItineraris(this.itineraris);
            this.view.setModel(this.model);
            Notificacion n = new Notificacion("Se ha encontrado una solución");
            return;
        }
        Notificacion n = new Notificacion("NO se ha encontrado ninguna solución");
    }
    
    /**
     * Algoritmo de backtraking iterativo que busca un recorrido con todas las
     * piezas de modo que todas las casillas sean visitadas exactamente una vez.
     * @return true si ha encontrado una solución.
     */
    private boolean Iterativo() {
        int max = model.getDimension() * model.getDimension() - piezas.size();

        //array solució
        int t[] = new int[max];
        for (int i = 0; i < t.length; i++) {
            t[i] = -1;
        }

        //init node
        int k = 0; //nivell profunditat node que s'està processant
        
        while (k >= 0) { //encara no hem recorregut tot l'arbre
            int torn = k % piezas.size();
            Pieza p = piezas.get(torn);
            int size = itineraris.get(torn).size();
            if (t[k] < p.getNumMovs() - 1) { //encara no s'ha processat tots els valors pel node k
                t[k]++; //processar node k

                Point pAux = itineraris.get(torn).get(size - 1);
                int x1 = pAux.x + p.getMovX(t[k]);
                int y1 = pAux.y + p.getMovY(t[k]);
                Point pAux2 = new Point(x1, y1);
                if (isValid(p,pAux,pAux2) && (k == t.length - 1)) { //solució
                    itineraris.get(torn).add(pAux2);
                    model.setC(x1, y1);
                    if (view.isVisualize()) {
                        view.getBoardPanel().addPieza(x1, y1, p, k + piezas.size());
                    }
                    return true;
                } else if (isValid(p,pAux,pAux2) && (k < t.length - 1)) { //possibilitat solució
                    itineraris.get(torn).add(pAux2);
                    
                    model.setC(x1, y1);
                   
                    if (view.isVisualize()) {
                        view.getBoardPanel().addPieza(x1, y1, p, k + piezas.size());
                    }
                    
                    k++; //Passam al següent nivell;
                }
            } else { //s'han processat tots els valors pel node k
                //inicialitzar el node k
                t[k] = -1;

                //tornar al nivell anterior
                k--;
                if (k < 0) {
                    return false;
                }
                torn = k % piezas.size();
                size = itineraris.get(torn).size();
                Point paux = itineraris.get(torn).get(size-1);
                model.removeC(paux.x, paux.y);
                itineraris.get(torn).remove(size - 1);
            }
        }
        return false;
    }  
        
    /**
     * Algoritmo de backtraking recursivo que busca un recorrido con todas las
     * piezas de modo que todas las casillas sean visitadas exactamente una vez.
     * @param p pieza que va a mover.
     * @param k máximo nivel de profundidad en el árbol de llamadas.
     * @return true si ha encontrado una solución.
     */
    private boolean Recursivo(Pieza p, int k) {
        if (k == this.KMAX) return true;
        
        for (int i = 0; i < p.getNumMovs(); i++) {
            int size = itineraris.get(piezas.indexOf(p)).size();
            
            Point pAux = itineraris.get(piezas.indexOf(p)).get(size - 1);
            
            int x1 = pAux.x + p.getMovX(i);
            int y1 = pAux.y + p.getMovY(i);
            
            Point pAux2 = new Point(x1, y1);
           
            if (isValid(p, pAux, pAux2)) {
                itineraris.get(piezas.indexOf(p)).add(pAux2);            
                if (view.isVisualize()) {
                    view.getBoardPanel().addPieza(x1, y1, p, k + piezas.size());
                }
                model.setC(x1, y1);
                
                if (Recursivo(piezas.get((k + 1) % piezas.size()), k + 1)) {
                    return true;
                }

                itineraris.get(piezas.indexOf(p)).remove(size);
                if (view.isVisualize()) {
                    view.getBoardPanel().removePieza(x1, y1);
                }
                model.removeC(x1, y1);
            }
        }
        return false;
    }

    /**
     * Método que comprueba si el movimiento de una pieza es valido, es decir,
     * si en el/los desplazamientos que dan lugar al movimiento no se topa con
     * ninguna otra pieza desde el punto inicial hasta el final.
     * @param p pieza que se quiere mover.
     * @param posI posición inicial del movimiento.
     * @param posF posición final del movimiento.
     * @return true si no se topa con otra pieza en alguno de los desplazamientos.
     */
    public boolean isValid(Pieza p, Point posI, Point posF) {
        if ((posF.x < 0) || (posF.x >= model.getDimension()) || (posF.y < 0) || (posF.y >= model.getDimension())) {
            return false;
        }
        if (model.isOcupada(posF.x, posF.y)) {
            return false;
        }
        if (p.afectaDimension()) {
            if (posI.x > posF.x) {
                int max = posI.x - posF.x;
                if(posI.y>posF.y){
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x-i,posI.y-i)) return false;
                    }
                } else if(posI.y==posF.y){
                    for (int i = 1; i < max; i++) {
                        
                        if(model.isOcupada(posI.x-i,posI.y)) return false;
                    }
                } else{//posI.y<posF.y
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x-i,posI.y+i)) return false;
                    }
                }
            } else if(posI.x == posF.x){
                if(posI.y>posF.y){
                    int max = posI.y - posF.y;
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x, posI.y-i)) return false;
                    }
                } else{///posI.y<posF.y
                    int max = posF.y - posI.y;
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x, posI.y+i)) return false;
                    }
                }
            } else{//posI.x < posF.x
                int max = posF.x - posI.x;
                if(posI.y>posF.y){
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x+i, posI.y-i)) return false;
                    }
                } else if(posI.y==posF.y){
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x+i, posI.y)) return false;
                    }
                } else{//posI.y<posF.y
                    for (int i = 1; i < max; i++) {
                        if(model.isOcupada(posI.x+i, posI.y+i)) return false;
                    }
                }
            }
        }
        return true;
    }
    
}
