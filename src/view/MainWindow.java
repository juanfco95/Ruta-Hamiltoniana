/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package view;

import javax.swing.*;
import model.*;
import controller.Controller;

/**
 * JFrame que compone la vista, estando el resto de componentes de la interfaz
 * integrados en la ventana principal.
 */
public class MainWindow extends JFrame {
    
    private final int width;
    private final int height;
    public final int MARGENLAT = 160;

    private BoardPanel boardPanel;
    private LateralPanel mIz;
    private LateralPanel mDe;
        
    private int dimensionT;
    
    private boolean visualize;
    
    public String PiezaSelec = null;
    
    private Controller controller;
    private BoardDefinition model;
    private SolutionWindow solutionWindow;
    
    public MainWindow(int h, int w){
        this.width = w;
        this.height = h;
        this.dimensionT = 10;
  
        System.out.println("");
        this.setSize(width + MARGENLAT, height);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establece la posición de la ventana en el centro de la pantalla
        setLocationRelativeTo(null);
        setLayout(null);
        // Desactiva la posibilidad de reescalar la ventana
        setResizable(false);
        
        initC();
        
        // Imagen del fondo del JFrame
        JLabel fondo;
        ImageIcon img = new ImageIcon("src/imagenes/fondo.jpg");
        fondo = new JLabel("", img, JLabel.CENTER);
        fondo.setBounds(0, 0, getWidth(), getHeight());
        add(fondo);
    }

    private void initC() {
        this.boardPanel = new BoardPanel(this.dimensionT, this);
        add(boardPanel);
        
        mIz = new LateralPanel(this);
        mIz.setLocation(10, 10);
        mIz.initA();
        add(mIz);
        
        mDe = new LateralPanel(this);
        mDe.setLocation(this.width , 10);
        mDe.initB();
        add(mDe);
    }
    
    protected int getBoardWidth(){
        return this.boardPanel.getW();
    }
    
    protected int getBoardHeight(){
        return this.boardPanel.getH();
    }
    
    protected int getDim(){
        return this.dimensionT;
    }

    protected void SetPiezaSelected(String s) {
        this.PiezaSelec = s;
        this.mIz.repaint();
    }

    void resetDimension(int d) {
        this.dimensionT = d;
        remove(boardPanel);
        
        this.paintComponents(this.getGraphics());
        
        this.boardPanel = new BoardPanel(d, this);
        add(boardPanel);
        
        this.boardPanel.repaint();
        
        this.mDe.removeSolutionBotton();
    }

    public BoardPanel getBoardPanel(){
        return this.boardPanel;
    }

    public void newItter() {
        if(this.controller == null){
            Controller c = new Controller(this, "iterativo");
            c.start();
        }
    }

    public void newRecur() {
        if(this.controller == null){
            Controller c = new Controller(this, "recursivo");
            c.start();
        }
    }

    public void setModel(BoardDefinition model) {
        this.model = model;
        this.mDe.showSolutionBotton();
    }
    
    public BoardDefinition getModel() {
        return this.model;
    }

    public void pintaSolucion() {
        this.boardPanel.repaint();
        //boardPanel.repintadoSolucion();
    }

    void showSolution() {
        if(this.solutionWindow != null){
            this.solutionWindow.dispose();
        }
        
        this.solutionWindow = new SolutionWindow(this.model, this.width, this.height);
    }
    
    public void setVisualize(boolean b) {
        this.visualize = b;
    }
    
    public boolean isVisualize() {
        return this.visualize;
    }
    
    public void setTiempoRespuesta(long n) {
        mDe.setTiempoRespuesta(n);
    }

}
