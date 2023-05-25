/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import model.BoardDefinition;
import model.piezas.Pieza;

/**
 * Ventana que aparece tras ejecutar un algoritmo de backtraking, encontrar una
 * solución y presionar el botón lateral de mostrar solucion; A
 */
public class SolutionWindow extends JFrame{
    
    public final int MARGENLAT = 160;
    
    private ArrayList<Pieza> piezas;
    ArrayList<ArrayList<Point>> itineraris;
    private int index = 0;
    
    private BoardDefinition model;
    private BoardPanel boardPanel;
    
    public SolutionWindow(BoardDefinition m, int w, int h){
        this.model = m;
        //this.setSize(w + MARGENLAT, h);
        this.setSize(w, h+100);
        
        // Configura el cierre de la ventana al presionar la x
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Establece la posición de la ventana en el centro de la pantalla
        setLocationRelativeTo(null);
        setLayout(null);
        // Desactiva la posibilidad de reescalar la ventana
        setResizable(false);
         
        this.piezas = model.getPiezas();
        this.itineraris = model.getItineraris();
        this.index = 0;
        
        initC();
        // Imagen del fondo del JFrame
        JLabel fondo;
        ImageIcon img = new ImageIcon("src/imagenes/fondo.jpg");
        fondo = new JLabel("", img, JLabel.CENTER);
        fondo.setBounds(0, 0, getWidth(), getHeight());
        add(fondo);   
    }

    private void initC() {
        this.boardPanel = new BoardPanel(this.model.getDimension(), this);
        add(this.boardPanel);
        
        JButton leftB = new JButton("<");
        leftB.setFont(new Font("Britannic Bold", Font.PLAIN, 40));
        leftB.setBounds(10,
                290,
                80,
                100);
        leftB.setBackground(Color.BLACK);
        leftB.setForeground(Color.WHITE);
        leftB.setFocusPainted(false);
        leftB.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        leftB.addActionListener((ActionEvent e) -> {
            if(index != 0){
                index--;
                int indexItinerari = (index + 1) % piezas.size();
                Pieza p = piezas.get(indexItinerari);
                Point point = itineraris.get(indexItinerari).get(index/piezas.size());
                boardPanel.removePieza(point.x, point.y);
            }
        });
        add(leftB);
        
        JButton rightB = new JButton(">");
        rightB.setFont(new Font("Britannic Bold", Font.PLAIN, 40));
        rightB.setBounds(boardPanel.getW()+110,
                290,
                80,
                100);   
        rightB.setBackground(Color.BLACK);
        rightB.setForeground(Color.WHITE);
        rightB.setFocusPainted(false);
        rightB.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightB.addActionListener((ActionEvent e) -> {
            if(index != model.getDimension() * model.getDimension()){
                int indexItinerari = (index + 1) % piezas.size();
                Pieza p = piezas.get(indexItinerari);
                Point point = itineraris.get(indexItinerari).get(index/piezas.size());
                
                boardPanel.addPieza(point.x, point.y, p, index++);
            }
        });
        add(rightB);
        
        this.setVisible(true); 
    }
    
}
