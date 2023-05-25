/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package view;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 *
 */
public class Casilla extends JPanel {

    private Rectangle2D.Float rectangulo;
    private Color fondo;
    private Color colorNumero;

    private String nombrePieza;
    private Image imgPieza;

    private String nC;
    private int dimensionT;

    public Casilla(Rectangle2D.Float r, Color c, int dimensionT) {
        this.dimensionT = dimensionT;
        this.rectangulo = r;
        this.fondo = c;
        this.nC = "";
        this.nombrePieza = "";
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (g2d == null) return; // Prevención de errores
        
        g2d.setColor(fondo);
        g2d.fill(rectangulo);

        g2d.setColor(this.colorNumero);
        g2d.setFont(new Font("Britannic Bold", Font.PLAIN, (int) 144 / dimensionT));
        if(dimensionT > 2){
            g2d.drawString(nC, rectangulo.x + 2, rectangulo.y + (((int) (100 / (dimensionT-1)))));
        }else{
            g2d.drawString(nC, rectangulo.x + 2, rectangulo.y + (((int) (100 / (dimensionT)))));
        }
    }

    public void setImage(Image img) {
        this.imgPieza = img;
    }

    public void setPieza(String s) {
        this.nombrePieza = s;
    }

    public String getPieza() {
        return this.nombrePieza;
    }

    public Image getImage() {
        return this.imgPieza;
    }

    public Rectangle2D.Float getCasilla() {
        return this.rectangulo;
    }

    public Color getColorFondo() {
        return this.fondo;
    }

    public void setNumero(int i, int dimensionT, Color c) {
        nC = ("" + i);
        this.colorNumero = c;
    }

    public void removeNumero() {
        nC = ("");
    }

}
