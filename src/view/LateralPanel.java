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
import javax.swing.border.LineBorder;
import model.piezas.Pieza;

/**
 * Paneles laterales de la ventana con las piezas y los distintos botones
 * para interactuar con el tablero.
 */
public class LateralPanel extends JPanel implements ActionListener {

    private int Margen = 10;
    private int tamPieza = 60;

    private static final Color VERDE = new Color(0, 100, 0);
    private static final Color ROJO = new Color(100, 0, 0);

    private MainWindow view;

    private JLabel piezaActual = new JLabel();
    private JLabel tiempoRespuesta;
    
    private JButton iterB;
    private JButton recurB;
    private JButton solutionB;
    private JButton visualizeB;

    private JWindow ventanaEmergente;

    private boolean esRecursivo = true;

    

    public LateralPanel(MainWindow v) {
        this.view = v;
        setSize(v.MARGENLAT - 20, v.getBoardHeight());
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(new Color(194, 155, 97));
        setLayout(null);

        // Inicializa la ventana emergente
        ventanaEmergente = new JWindow();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(iterB)) {
            view.newItter();
        } else if (ae.getSource().equals(recurB)) {
            view.newRecur();
        } else if (ae.getSource().equals(solutionB)) {
            view.showSolution();
        } else if (ae.getSource().equals(visualizeB)) {
            if (view.isVisualize()) {
                visualizeB.setBackground(ROJO);
                view.setVisualize(false);
            } else {
                visualizeB.setBackground(VERDE);
                view.setVisualize(true);
            }
        }
    }

    void initA() {
        String[] nombrePiezas = new String[6];
        nombrePiezas[0] = "Caballo";
        nombrePiezas[1] = "Reina";
        nombrePiezas[2] = "Torre";
        nombrePiezas[3] = "Rey";
        nombrePiezas[4] = "Alfil";
        nombrePiezas[5] = "Drac";

        Pieza pieza;
        ImageIcon imgPieza;
        int dimension = view.getDim();

        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                piezaActual.setBorder(null);
                view.SetPiezaSelected(e.getComponent().getName());
                piezaActual = (JLabel) e.getComponent();
                piezaActual.setBackground(Color.LIGHT_GRAY);
                piezaActual.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(Color.BLACK, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mostrarVentanaEmergente(e.getComponent().getName());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cerrarVentanaEmergente();
            }
        };

        for (int i = 0; i < nombrePiezas.length; i++) {
            pieza = Pieza.newPieza(nombrePiezas[i], dimension);

            Image imgAux = pieza.getImagen();
            Image img = imgAux.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
            imgPieza = new ImageIcon(img);

            JLabel labelPieza = new JLabel(imgPieza);

            labelPieza.setBounds(getWidth() / 4, (Margen * (1 + i * 2) + tamPieza * i), imgPieza.getIconWidth(), imgPieza.getIconHeight());
            labelPieza.setBackground(Color.LIGHT_GRAY);

            labelPieza.setName(nombrePiezas[i]);
            labelPieza.addMouseListener(listener);

            add(labelPieza);
        }
    }

    void initB() {
        this.recurB = new JButton("RECURSIVO");
        this.recurB.setBounds(10, 20, getWidth() - 20, getHeight() / 8);
        this.recurB.setBackground(Color.BLACK);
        this.recurB.setForeground(Color.WHITE);
        this.recurB.setFocusPainted(false);
        this.recurB.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.recurB.addActionListener(this);
        add(this.recurB);

        this.iterB = new JButton("ITERATIVO");
        this.iterB.setBounds(10, 40 + getHeight() / 8, getWidth() - 20, getHeight() / 8);
        this.iterB.setBackground(Color.BLACK);
        this.iterB.setForeground(Color.WHITE);
        this.iterB.setFocusPainted(false);
        this.iterB.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.iterB.addActionListener(this);
        add(this.iterB);

        this.view.setVisualize(false); // inicializamos visualize a false
        this.visualizeB = new JButton();
        // Añadimos el texto en html para centrar el texto sin que se desborde
        this.visualizeB.setText("<html><center>MOSTRAR<br>RECORRIDO</center></html>");
        this.visualizeB.setBounds(10, 130 + getHeight() / 8, getWidth() - 20, getHeight() / 8);
        this.visualizeB.setBackground(ROJO);
        this.visualizeB.setForeground(Color.WHITE);
        this.visualizeB.setFocusPainted(false);
        this.visualizeB.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.visualizeB.addActionListener(this);
        add(this.visualizeB);

        JTextField textField = new JTextField("Tamaño del tablero");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setForeground(Color.GRAY);
        textField.setBounds(10, 2 * (75 + getHeight() / 8), getWidth() - 20, getHeight() / 16);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Tamaño del tablero")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText("Tamaño del tablero");
                }
            }
        });
        add(textField);
        
        JButton changeButton = new JButton("✔");
        changeButton.setBounds(getWidth() / 2 + 14, (115 + getHeight() / 8) + getHeight() / 4, getWidth() / 3, getHeight() / 18);
        changeButton.setBackground(Color.BLACK);
        changeButton.setForeground(Color.WHITE);
        changeButton.setFocusPainted(false);
        changeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        changeButton.addActionListener((ActionEvent e) -> {
            String text = textField.getText();
            if (!"Tamaño del tablero".equals(text)) {
                int d;
                try { 
                    d = Integer.parseInt(text);
                    if (d > 0 && d < 61) {
                        view.resetDimension(d);
                    } else if (d < 1) {
                        Notificacion notifica = new Notificacion("El tamaño mínimo es 1");
                    } else {
                        Notificacion notifica = new Notificacion("El tamaño máximo es 20");
                    }
                } catch (NumberFormatException ex) {
                    Notificacion notifica = new Notificacion("Introduce un número entero natural");
                }
            }
        });
        add(changeButton);

        this.tiempoRespuesta = new JLabel();
        this.tiempoRespuesta.setBounds(10, 2 * (115 + getHeight() / 8), getWidth() - 20, getHeight() / 8);
        this.tiempoRespuesta.setBackground(Color.WHITE);
        this.tiempoRespuesta.setForeground(Color.BLACK);
        this.tiempoRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
        this.tiempoRespuesta.setVerticalAlignment(SwingConstants.CENTER);
        this.tiempoRespuesta.setOpaque(true);
        this.tiempoRespuesta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        this.tiempoRespuesta.setText("<html><center>Tiempo respuesta:<br>‎ </center></html>");
        add(this.tiempoRespuesta);

        this.solutionB = new JButton("View Solution");
        this.solutionB.setBounds(10, (250 + getHeight() / 8) + getHeight() / 4, getWidth() - 20, getHeight() / 8);
        this.solutionB.setBackground(Color.BLACK);
        this.solutionB.setForeground(Color.WHITE);
        this.solutionB.setFocusPainted(false);
        this.solutionB.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.solutionB.addActionListener(this);
        add(this.solutionB);

        //Not shown until solution is found
        this.solutionB.setVisible(true);
        this.solutionB.setEnabled(false);
    }

    private void mostrarVentanaEmergente(String s) {
        String imgPath = "src/imagenes/" + s + "-leyenda.png";
        ImageIcon img = new ImageIcon(imgPath);
        JLabel labelPieza = new JLabel(img);
        labelPieza.setSize(10, 10);

        int x = view.getLocationOnScreen().x - img.getIconWidth() + 8;
        int y = view.getLocationOnScreen().y + view.getHeight() - img.getIconHeight() - 7;
        ventanaEmergente.setLocation(x, y);
        ventanaEmergente.getContentPane().add(labelPieza);
        ventanaEmergente.pack();
        ventanaEmergente.setVisible(true);
    }

    private void cerrarVentanaEmergente() {
        ventanaEmergente.setVisible(false);
        ventanaEmergente.getContentPane().removeAll();
    }

    protected void showSolutionBotton() {
        this.solutionB.setEnabled(true);
    }

    protected void removeSolutionBotton() {
        this.solutionB.setEnabled(false);
    }

    public void setTiempoRespuesta(long n) {
        this.tiempoRespuesta.setText("<html><center>Tiempo respuesta:<br>" + n
                + "ns</center></html>");
    }

}
