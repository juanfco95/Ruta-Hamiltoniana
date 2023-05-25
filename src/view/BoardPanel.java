/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package view;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import model.piezas.Pieza;

/**
 * Tablero donde se pintan las piezas y se muestra el algoritmo de backtracking
 * si se habilita la opción.
 */
public class BoardPanel extends JPanel {

    private int Item_Width;
    private int Item_Height;

    private int width;
    private int height;

    private int dimension;
    private Casilla casillas[][];

    private MainWindow Mview;
    private SolutionWindow Sview = null;

    public BoardPanel(int d, MainWindow view) {
        this.dimension = d;
        this.Mview = view;

        setLayout(null);

        this.width = view.getWidth() - (2 * view.MARGENLAT + 20);
        this.height = view.getHeight() - 50;

        setBounds(view.MARGENLAT + 10, 10, this.width, this.height);

        this.initC();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int xPosition = me.getX();
                int yPosition = me.getY();

                int fila = yPosition / Item_Height;
                int columna = xPosition / Item_Width;

                if (me.getButton() == MouseEvent.BUTTON1 && Mview.PiezaSelec != null) {
                    Pieza p = Pieza.newPieza(Mview.PiezaSelec, dimension);
                    addPieza(fila, columna, p, -1);
                } else if (me.getButton() == MouseEvent.BUTTON1){
                    Notificacion notificacion = new Notificacion("Debe seleccionar una ficha");
                }
                if (me.getButton() == MouseEvent.BUTTON3) {
                    removePieza(fila, columna);
                }
            }
        });

        this.setVisible(true);
    }
    
    public BoardPanel(int d, SolutionWindow view) {
        this.dimension = d;
        this.Sview = view;

        setLayout(null);

        this.width = view.getWidth() - (view.MARGENLAT + 60);
        this.height = view.getHeight() - 130;

        setBounds(100, 40, this.width, this.height);

        this.initC();
        this.setVisible(true);
    }
    
    private void initC() {
        this.casillas = new Casilla[this.dimension][this.dimension];

        Item_Width = this.width / this.dimension;
        Item_Height = this.height / this.dimension;

        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {

                Rectangle2D.Float r = new Rectangle2D.Float(j * Item_Width, i * Item_Height, Item_Width, Item_Height);

                Color color;
                if ((i + j) % 2 == 0) {
                    color = Color.WHITE;
                } else {
                    color = Color.BLACK;
                }

                this.casillas[i][j] = new Casilla(r, color, dimension);
            }
        }    
    }
    
    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (g2d == null) return; // Prevención de errores
        
        int x, y = 0;
        for (int i = 0; i < this.dimension; i++) {
            x = 0;

            for (int j = 0; j < this.dimension; j++) {
                casillas[i][j].paintComponent(g);

                Image img = casillas[i][j].getImage();
                if (img != null) {
                    g2d.drawImage(img,
                            x + Item_Width / 6, //posicion inicial
                            y + Item_Width / 6, //posicion inicial
                            this.Item_Width - (Item_Width / 7), //tamaño en ancho
                            this.Item_Height - (Item_Width / 7), //tamaño en alto
                            this);
                }

                x += Item_Width;
            }
            y += Item_Height;
        }
    }

    public void paintCasilla(int fila, int columna) {
        Graphics g2d = (Graphics2D) this.getGraphics();
        casillas[fila][columna].paintComponent(g2d);

        Image img = casillas[fila][columna].getImage();
        if (img != null) {
            int x = 0 + Item_Width * columna;
            int y = 0 + Item_Height * fila;
            g2d.drawImage(img,
                    x + (Item_Width / 4 + 2), //posicion inicial
                    y + (Item_Height / 4 + 2), //posicion inicial
                    this.Item_Width - (Item_Width / 3), //tamaño en ancho
                    this.Item_Height - (Item_Width / 3), //tamaño en alto
                    this);
        }
    }

    public void addPieza(int fila, int columna, Pieza p, int num) {
        casillas[fila][columna].setImage(p.getImagen());
        casillas[fila][columna].setPieza(p.getNombre());
        if (num != -1) {
            casillas[fila][columna].setNumero(num, dimension, p.getColor());
        }
        this.paintCasilla(fila, columna);
    }

    public void setNumero(int fila, int columna, int num) {
        casillas[fila][columna].setNumero(num, dimension, Color.red);
        this.paintCasilla(fila, columna);
    }

    public void removePieza(int fila, int columna) {
        casillas[fila][columna].setImage(null);
        casillas[fila][columna].removeNumero();
        casillas[fila][columna].setPieza("");
        this.paintCasilla(fila, columna);
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setDimension(int d) {
        this.dimension = d;
    }

    public int getW() {
        return this.width;
    }

    public int getH() {
        return this.height;
    }

    public ArrayList<Pieza> getPiezas() {
        ArrayList<Pieza> array = new ArrayList();
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                if (!"".equals(casillas[i][j].getPieza())) {
                    Pieza p = Pieza.newPieza(casillas[i][j].getPieza(), this.dimension);
                    p.setCordenates(i, j);
                    array.add(p);
                }
            }
        }
        return array;
    }

}
