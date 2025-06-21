import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Interfaz extends JFrame {
    private JPanel panelInicial;
    private JPanel panelJugar;
    private JPanel panelJugar2;
    private JPanel panelResultado;
    private JPanel panelResumen;
    private JPanel panelFinal;
    private CardLayout cardLayout;
    public int rondas = 1;
    boolean perdioPunto1 = false;
    boolean ganoPunto1 = false;
    boolean perdioPunto2 = false;
    boolean ganoPunto2 = false;

    public Interfaz() {

        // Título
        super("Blackjack Especial");

        // Configuración del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 185);
        setLocationRelativeTo(null);


        // Creación de paneles
        panelInicial = new JPanel();
        panelInicial.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelJugar = new JPanel();
        panelJugar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelJugar2 = new JPanel();
        panelJugar2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelResultado = new JPanel();
        panelResultado.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelResumen = new JPanel();
        panelResumen.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelFinal = new JPanel();
        panelFinal.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Creación de elementos varios
        JLabel textoPuntosJugador1 = new JLabel();
        JLabel textoPuntosJugador2 = new JLabel();
        JLabel textoRondasPanelJugar = new JLabel("Ronda: " + rondas);
        JLabel textoRondasPanelJugar2 = new JLabel("Ronda: " + rondas);
        JLabel textoRondasPanelResumen = new JLabel("Ronda: " + rondas);

        // Creación de elementos para el panelInicial
        JButton botonJugarPanelInicial = new JButton("Jugar");
        JButton botonSalirPanelInicial = new JButton("Salir");
        JButton botonReglas = new JButton("Reglas");
        JButton estrategia = new JButton("Estrategia");
        JLabel imagen = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/blackjack.jpg"));
        imagen.setIcon(icon);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(200, 100, Image.SCALE_SMOOTH); // Ajusta el tamaño aquí
        ImageIcon newIcon = new ImageIcon(newImg);
        imagen.setIcon(newIcon);

        // Creación de elementos para el panelJugar
        JButton botonPedirPanelJugar = new JButton("Pedir");
        JButton botonPlantarsePanelJugar = new JButton("Plantarse");
        JButton botonCambioDeCarta1 = new JButton("Cambiar carta nº1");
        JButton botonCambioDeCarta2 = new JButton("Cambiar carta nº2");
        JButton botonCambioDeCarta1_2 = new JButton("Cambiar carta nº1");
        JButton botonCambioDeCarta2_2 = new JButton("Cambiar carta nº2");
        JButton sumador1 = new JButton("Sumador");
        JButton sumador2 = new JButton("Sumador");

        JLabel textoManoJugadorInglesa = new JLabel();
        JLabel textoManoJugadorInglesaLista = new JLabel();
        JLabel textoManoJugadorEspecial = new JLabel();
        JLabel textoManoCrupier = new JLabel();

        // Creación de elementos para el panelJugar2
        JButton botonPedirPanelJugar2 = new JButton("Pedir");
        JButton botonPlantarsePanelJugar2 = new JButton("Plantarse");

        JLabel textoManoJugadorInglesa2 = new JLabel();
        JLabel textoManoJugadorInglesaLista2 = new JLabel();
        JLabel textoManoJugadorEspecial2 = new JLabel();
        JLabel textoManoCrupier2 = new JLabel();

        // Creación de elementos para el panelResultado
        JButton continuar = new JButton("Continuar");
        JButton verResumen = new JButton("Ver resumen");
        JLabel textoManoCompletaCrupier = new JLabel();
        JLabel textoManoJugador1 = new JLabel();
        JLabel textoManoJugador2 = new JLabel();
        JLabel textoGanadores = new JLabel();
        JLabel textoGanadoresJugador1 = new JLabel();
        JLabel textoGanadoresJugador2 = new JLabel();

        // Creación de elementos para el panelResumen
        JButton siguenteRonda = new JButton("Siguiente ronda");

        // Creación de elementos para el panelFinal
        JLabel textoFinal = new JLabel();
        JButton botonFinal = new JButton("Salir");

        // Creación de los objetos
        Jugador jugador1 = new Jugador(null, null, null, 5);
        Jugador jugador2 = new Jugador(null, null, null, 5);
        Crupier crupier = new Crupier(null);

        BarajaInglesa barajaInglesa = new BarajaInglesa();
        BarajaEspecial barajaEspecial = new BarajaEspecial();


        // Botones del panel "panelInicial"

        // Agregar acción al botón de "estrategia"
        estrategia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String imagePath = "C:\\Users\\PC\\Desktop\\Blackjack Especial\\src\\Imagenes\\estrategia.jpg";

                ImageIcon imageIcon = new ImageIcon(imagePath);

                // Mostrar el JOptionPane con el mensaje y la imagen
                JOptionPane.showMessageDialog(null,null, "Estrategia", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
        });

        // Agregar acción al botón de "botonReglas"
        botonReglas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String ruta = "C:\\Users\\PC\\Desktop\\Blackjack Especial\\src\\Archivos\\Reglas.txt";

                StringBuilder contenido = new StringBuilder();

                try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                } catch (IOException error) {
                    error.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + error.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(null, contenido.toString());

            }
        });

        // Agregar acción al botón de "botonJugarPanelInicial"
        botonJugarPanelInicial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelJugar");

                String Nombre1 = JOptionPane.showInputDialog("Ingrese el nombre del jugador Nº1: ");
                String Nombre2 = JOptionPane.showInputDialog("Ingrese el nombre del jugador Nº2: ");

                jugador1.setNombre(Nombre1);
                jugador1.setManoInglesa(barajaInglesa.repartirCarta());
                jugador1.setManoEspecial(barajaEspecial.repartirCarta());

                crupier.setMano(barajaInglesa.repartirCarta());

                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                textoManoJugadorInglesa.setText("Suma de " + Nombre1 + ": " + jugador1.sumarMano());
                textoManoJugadorEspecial.setText("Cartas especiales: " + jugador1.getManoEspecial());
                textoManoCrupier.setText("Carta del Crupier: " + crupier.unaCarta());

                jugador2.setNombre(Nombre2);
                jugador2.setManoInglesa(barajaInglesa.repartirCarta());
                jugador2.setManoEspecial(barajaEspecial.repartirCarta());

                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                textoManoJugadorInglesa2.setText("Suma de " + Nombre2 + ": " + jugador2.sumarMano());
                textoManoJugadorEspecial2.setText("Cartas especiales: " + jugador2.getManoEspecial());
                textoManoCrupier2.setText("Carta del Crupier: " + crupier.unaCarta());

                Operaciones operaciones = new Operaciones();

                boolean res = operaciones.funcionAs(jugador1);

                if (res == true) {
                    int decision = Integer.parseInt(JOptionPane.showInputDialog("Te ha tocado un comodin, ingrese el valor del comodin / de 1 a 11: "));
                    while (decision < 1 || decision > 11) {
                        decision = Integer.parseInt(JOptionPane.showInputDialog("Error, ingrese el valor del comodin / de 1 a 11: "));
                    }
                    int nuevoValor = decision;
                    ArrayList<Integer> manoInglesa = jugador1.getManoInglesa();
                    manoInglesa.add(nuevoValor);
                    jugador1.setManoInglesa(manoInglesa);

                    textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                    textoManoJugadorInglesa.setText("Suma de " + Nombre1 + ": " + jugador1.sumarMano());
                    res = false;
                }

                if (jugador1.getManoEspecial() == "Cambio de Carta") {
                    panelJugar.add(botonCambioDeCarta1);
                    panelJugar.add(botonCambioDeCarta2);
                }

                if (jugador2.getManoEspecial() == "Cambio de Carta") {
                    panelJugar2.add(botonCambioDeCarta1_2);
                    panelJugar2.add(botonCambioDeCarta2_2);
                }

                if (jugador1.getManoEspecial() == "Sumador") {
                    panelJugar.add(sumador1);
                }

                if (jugador2.getManoEspecial() == "Sumador") {
                    panelJugar2.add(sumador2);
                }

                textoPuntosJugador1.setText("Puntos de " + jugador1.getNombre() + ": " + jugador1.puntos);
                textoPuntosJugador2.setText("Puntos de " + jugador2.getNombre() + ": " + jugador2.puntos);

            }
        });

        // Agregar acción al botón de "botonSalirPanelInicial"
        botonSalirPanelInicial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Botones del panel "panelInicial"

        // Agregar acción al botón de "botonPedirPanelJugar"
        botonPedirPanelJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelJugar");

                Operaciones operaciones = new Operaciones();

                String texto = operaciones.calcularJugada(jugador1);

                textoManoJugadorInglesa.setText(texto);

                boolean res = operaciones.funcionAs(jugador1);

                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());

                if (res == true) {
                    int decision = Integer.parseInt(JOptionPane.showInputDialog("Te ha tocado un comodin, ingrese el valor del comodin / de 1 a 11: "));
                    while (decision < 1 || decision > 11) {
                        decision = Integer.parseInt(JOptionPane.showInputDialog("Error, ingrese el valor del comodin / de 1 a 11: "));
                    }
                    int nuevoValor = decision;
                    ArrayList<Integer> manoInglesa = jugador1.getManoInglesa();
                    manoInglesa.add(nuevoValor);
                    jugador1.setManoInglesa(manoInglesa);

                    textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                    textoManoJugadorInglesa.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                    res = false;
                }

                if (texto.contains("El jugador")) {

                    JOptionPane.showMessageDialog(null, "Te has pasado de 21");
                    cardLayout.show(getContentPane(), "panelJugar2");

                    boolean res2 = operaciones.funcionAs(jugador2);

                    textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());

                    if (res2 == true) {
                        int decision = Integer.parseInt(JOptionPane.showInputDialog("Te ha tocado un comodin, ingrese el valor del comodin / de 1 a 11: "));
                        while (decision < 1 || decision > 11) {
                            decision = Integer.parseInt(JOptionPane.showInputDialog("Error, ingrese el valor del comodin / de 1 a 11: "));
                        }
                        int nuevoValor = decision;
                        ArrayList<Integer> manoInglesa = jugador2.getManoInglesa();
                        manoInglesa.add(nuevoValor);
                        jugador2.setManoInglesa(manoInglesa);

                        textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                        textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                        res2 = false;
                    }


                }
            }
        });

        // Agregar acción al botón de "botonPedirPanelJugar2"
        botonPedirPanelJugar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelJugar2");

                Operaciones operaciones = new Operaciones();

                String texto = operaciones.calcularJugada(jugador2);

                textoManoJugadorInglesa2.setText(texto);

                boolean res = operaciones.funcionAs(jugador2);

                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());

                if (res == true) {
                    int decision = Integer.parseInt(JOptionPane.showInputDialog("Te ha tocado un comodin, ingrese el valor del comodin / de 1 a 11: "));
                    while (decision < 1 || decision > 11) {
                        decision = Integer.parseInt(JOptionPane.showInputDialog("Error, ingrese el valor del comodin / de 1 a 11: "));
                    }
                    int nuevoValor = decision;
                    ArrayList<Integer> manoInglesa = jugador2.getManoInglesa();
                    manoInglesa.add(nuevoValor);
                    jugador2.setManoInglesa(manoInglesa);

                    textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                    textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                    res = false;
                }

                if (texto.contains("El jugador")) {
                    JOptionPane.showMessageDialog(null, "Te has pasado de 21");
                    cardLayout.show(getContentPane(), "panelResultado");
                    textoManoCompletaCrupier.setText("Suma del crupier: " + crupier.sumarMano());
                    textoManoJugador1.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                    textoManoJugador2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());

                    verResumen.setVisible(false);
                }
            }
        });

        // Agregar acción al botón de "botonPlantarsePanelJugar"
        botonPlantarsePanelJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelJugar2");

                Operaciones operaciones = new Operaciones();

                boolean res2 = operaciones.funcionAs(jugador2);

                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());

                if (res2 == true) {
                    int decision = Integer.parseInt(JOptionPane.showInputDialog("Te ha tocado un comodin, ingrese el valor del comodin / de 1 a 11: "));
                    while (decision < 1 || decision > 11) {
                        decision = Integer.parseInt(JOptionPane.showInputDialog("Error, ingrese el valor del comodin / de 1 a 11: "));
                    }
                    int nuevoValor = decision;
                    ArrayList<Integer> manoInglesa = jugador2.getManoInglesa();
                    manoInglesa.add(nuevoValor);
                    jugador2.setManoInglesa(manoInglesa);

                    textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                    textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                    res2 = false;
                }
            }
        });

        // Agregar acción al botón de "botonPlantarsePanelJugar2"
        botonPlantarsePanelJugar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelResultado");
                textoManoCompletaCrupier.setText("Suma del crupier: " + crupier.sumarMano());
                textoManoJugador1.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                textoManoJugador2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());

                verResumen.setVisible(false);
            }
        });

        // Agregar acción al botón de "sumador1"
        sumador1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BarajaInglesa barajaInglesa = new BarajaInglesa();
                ArrayList<Integer> manoInglesa = jugador1.getManoInglesa();

                manoInglesa.remove(0);
                jugador1.setManoEspecial("");

                jugador1.puntos++;
                JOptionPane.showMessageDialog(null, "Has sumado otro punto mas! | Puntos: " + jugador1.puntos);

                textoPuntosJugador1.setText(jugador1.getNombre() + ": " + jugador1.puntos + " puntos | Carta especial: " + jugador1.getManoEspecial());

                panelJugar.remove(sumador1);
                panelJugar.revalidate();
                panelJugar.repaint();
            }
        });

        // Agregar acción al botón de "sumador2"
        sumador2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BarajaInglesa barajaInglesa = new BarajaInglesa();
                ArrayList<Integer> manoInglesa = jugador2.getManoInglesa();

                manoInglesa.remove(0);
                jugador2.setManoEspecial("");

                jugador2.puntos++;
                JOptionPane.showMessageDialog(null, "Has sumado otro punto mas! | Puntos: " + jugador2.puntos);

                textoPuntosJugador2.setText(jugador2.getNombre() + ": " + jugador2.puntos + " puntos | Carta especial: " + jugador2.getManoEspecial());

                panelJugar2.remove(sumador2);
                panelJugar2.revalidate();
                panelJugar2.repaint();
            }
        });

        // Agregar acción al botón de "botonCambioDeCarta1"
        botonCambioDeCarta1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BarajaInglesa barajaInglesa = new BarajaInglesa();
                ArrayList<Integer> manoInglesa = jugador1.getManoInglesa();

                manoInglesa.remove(0);
                jugador1.setManoEspecial("");

                manoInglesa.add(barajaInglesa.repartirUnaCarta());
                jugador1.setManoInglesa(manoInglesa);

                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                textoManoJugadorInglesa.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());

                panelJugar.remove(botonCambioDeCarta1);
                panelJugar.remove(botonCambioDeCarta2);
                panelJugar.revalidate();
                panelJugar.repaint();
            }
        });

        // Agregar acción al botón de "botonCambioDeCarta2"
        botonCambioDeCarta2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BarajaInglesa barajaInglesa = new BarajaInglesa();
                ArrayList<Integer> manoInglesa = jugador1.getManoInglesa();

                manoInglesa.remove(1);
                jugador1.setManoEspecial("");

                manoInglesa.add(barajaInglesa.repartirUnaCarta());
                jugador1.setManoInglesa(manoInglesa);

                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                textoManoJugadorInglesa.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());

                panelJugar.remove(botonCambioDeCarta1);
                panelJugar.remove(botonCambioDeCarta2);
                panelJugar.revalidate();
                panelJugar.repaint();
            }
        });

        // Agregar acción al botón de "botonCambioDeCarta1_2"
        botonCambioDeCarta1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BarajaInglesa barajaInglesa = new BarajaInglesa();
                ArrayList<Integer> manoInglesa = jugador2.getManoInglesa();

                manoInglesa.remove(0);
                jugador2.setManoEspecial("");

                manoInglesa.add(barajaInglesa.repartirUnaCarta());
                jugador2.setManoInglesa(manoInglesa);

                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());

                panelJugar2.remove(botonCambioDeCarta1_2);
                panelJugar2.remove(botonCambioDeCarta2_2);
                panelJugar2.revalidate();
                panelJugar2.repaint();
            }
        });

        // Agregar acción al botón de "botonCambioDeCarta2_2"
        botonCambioDeCarta2_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BarajaInglesa barajaInglesa = new BarajaInglesa();
                ArrayList<Integer> manoInglesa = jugador2.getManoInglesa();

                manoInglesa.remove(1);
                jugador2.setManoEspecial("");

                manoInglesa.add(barajaInglesa.repartirUnaCarta());
                jugador2.setManoInglesa(manoInglesa);

                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());

                panelJugar2.remove(botonCambioDeCarta1_2);
                panelJugar2.remove(botonCambioDeCarta2_2);
                panelJugar2.revalidate();
                panelJugar2.repaint();
            }
        });

        // Botones del panel "panelResultado"

        // Agregar acción al botón de "continuar"
        continuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelResultado");

                textoManoCompletaCrupier.setText("Suma del crupier: " + crupier.sumarMano());
                textoManoJugador1.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                textoManoJugador2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano() + "\n");
                Operaciones operaciones = new Operaciones();

                perdioPunto1 = false;
                ganoPunto1 = false;
                perdioPunto2 = false;
                ganoPunto2 = false;

                if (crupier.sumarMano() < 17) {
                    operaciones.funcion2(crupier);
                    textoManoCompletaCrupier.setText("Suma del crupier: " + crupier.sumarMano());
                } else {
                    verResumen.setVisible(true);
                }

                if (jugador1.sumarMano() > 21 && jugador2.sumarMano() > 21) {
                    JOptionPane.showMessageDialog(null, "Perdieron ambos jugadores");
                    perdioPunto1 = true;
                    perdioPunto2 = true;
                }

                if (crupier.sumarMano() >= 17 && crupier.sumarMano() <= 21) {
                    if (crupier.sumarMano() < jugador1.sumarMano() && jugador1.sumarMano() <= 21) {
                        textoGanadores.setText("Ganadores:\n");
                        textoGanadoresJugador1.setText(jugador1.getNombre());
                        ganoPunto1 = true;
                    }
                    if (crupier.sumarMano() < jugador2.sumarMano() && jugador2.sumarMano() <= 21) {
                        textoGanadores.setText("Ganadores:\n");
                        textoGanadoresJugador2.setText(jugador2.getNombre());
                        ganoPunto2 = true;
                    }
                }
                if (crupier.sumarMano() > 21) {
                    if (jugador1.sumarMano() <= 21) {
                        textoGanadores.setText("Ganadores:\n");
                        textoGanadoresJugador1.setText(jugador1.getNombre());
                        ganoPunto1 = true;
                    }
                    if (jugador2.sumarMano() <= 21) {
                        textoGanadores.setText("Ganadores:\n");
                        textoGanadoresJugador2.setText(jugador2.getNombre());
                        ganoPunto2 = true;
                    }

                }

                if ((jugador1.sumarMano() < crupier.sumarMano() && crupier.sumarMano() <= 21) || jugador1.sumarMano() > 21) {
                    perdioPunto1 = true;

                }
                if ((jugador2.sumarMano() < crupier.sumarMano() && crupier.sumarMano() <= 21) || jugador2.sumarMano() > 21) {
                    perdioPunto2 = true;
                }

            }
        });

        // Agregar acción al botón de "verResumen"
        verResumen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelResumen");

                if (perdioPunto1 == true) {
                    jugador1.puntos--;
                } else if (ganoPunto1 == true) {
                    jugador1.puntos++;
                }

                if (perdioPunto2 == true) {
                    jugador2.puntos--;
                } else if (ganoPunto2 == true) {
                    jugador2.puntos++;
                }

                textoPuntosJugador1.setText(jugador1.getNombre() + ": " + jugador1.puntos + " puntos | Carta especial: " + jugador1.getManoEspecial());
                textoPuntosJugador2.setText(jugador2.getNombre() + ": " + jugador2.puntos + " puntos | Carta especial: " + jugador2.getManoEspecial());


                if (jugador1.puntos == 0 && jugador2.puntos == 0) {
                    cardLayout.show(getContentPane(), "panelFinal");
                    textoFinal.setText("Perdieron ambos jugadores, es un empate.");
                }
                if (jugador1.puntos == 10 && jugador2.puntos == 10) {
                    cardLayout.show(getContentPane(), "panelFinal");
                    textoFinal.setText("Ganaron ambos jugadores, es un empate.");
                }
                if (jugador1.puntos <= 0) {
                    cardLayout.show(getContentPane(), "panelFinal");
                    textoFinal.setText("El ganador es: " + jugador2.getNombre());
                } else if (jugador2.puntos >= 10) {
                    cardLayout.show(getContentPane(), "panelFinal");
                    textoFinal.setText("El ganador es: " + jugador2.getNombre());
                } else if (jugador2.puntos <= 0) {
                    cardLayout.show(getContentPane(), "panelFinal");
                    textoFinal.setText("El ganador es: " + jugador1.getNombre());
                } else if (jugador1.puntos >= 10) {
                    cardLayout.show(getContentPane(), "panelFinal");
                    textoFinal.setText("El ganador es: " + jugador1.getNombre());
                }

            }
        });

        // Botones del panel "panelResumen"

        // Agregar acción al botón de "siguenteRonda"
        siguenteRonda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "panelJugar");

                rondas++;
                textoRondasPanelJugar.setText("Ronda: " + rondas);
                textoRondasPanelJugar2.setText("Ronda: " + rondas);
                textoRondasPanelResumen.setText("Ronda: " + rondas);

                jugador1.setManoInglesa(null);
                jugador2.setManoInglesa(null);
                crupier.setMano(null);
                textoGanadores.setText(null);
                textoGanadoresJugador1.setText(null);
                textoGanadoresJugador2.setText(null);

                jugador1.setManoInglesa(barajaInglesa.repartirCarta());

                crupier.setMano(barajaInglesa.repartirCarta());

                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                textoManoJugadorInglesa.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                textoManoJugadorEspecial.setText("Cartas especiales: " + jugador1.getManoEspecial());
                textoManoCrupier.setText("Carta del Crupier: " + crupier.unaCarta());

                jugador2.setManoInglesa(barajaInglesa.repartirCarta());

                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                textoManoJugadorEspecial2.setText("Cartas especiales: " + jugador2.getManoEspecial());
                textoManoCrupier2.setText("Carta del Crupier: " + crupier.unaCarta());

                Operaciones operaciones = new Operaciones();

                boolean res = operaciones.funcionAs(jugador1);

                if (res == true) {
                    int decision = Integer.parseInt(JOptionPane.showInputDialog("Te ha tocado un comodin, ingrese el valor del comodin / de 1 a 11: "));
                    while (decision < 1 || decision > 11) {
                        decision = Integer.parseInt(JOptionPane.showInputDialog("Error, ingrese el valor del comodin / de 1 a 11: "));
                    }
                    int nuevoValor = decision;
                    ArrayList<Integer> manoInglesa = jugador1.getManoInglesa();
                    manoInglesa.add(nuevoValor);
                    jugador1.setManoInglesa(manoInglesa);

                    textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                    textoManoJugadorInglesa.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                    res = false;
                }

                if (jugador1.getManoEspecial() == "Cambio de Carta") {
                    panelJugar.add(botonCambioDeCarta1);
                    panelJugar.add(botonCambioDeCarta2);
                }

                if (jugador2.getManoEspecial() == "Cambio de Carta") {
                    panelJugar2.add(botonCambioDeCarta1_2);
                    panelJugar2.add(botonCambioDeCarta2_2);
                }

                textoPuntosJugador1.setText("Puntos de " + jugador1.getNombre() + ": " + jugador1.puntos);
                textoPuntosJugador2.setText("Puntos de " + jugador2.getNombre() + ": " + jugador2.puntos);

            }
        });

        // Botones del panel "panelFinal"

        // Agregar acción al botón de "botonFinal"
        botonFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Agregar botones al panelInicial
        panelInicial.add(imagen);
        panelInicial.add(botonJugarPanelInicial);
        panelInicial.add(botonSalirPanelInicial);
        panelInicial.add(botonReglas);
        panelInicial.add(estrategia);

        // Agregar botones al panelJugar
        panelJugar.add(botonPedirPanelJugar);
        panelJugar.add(botonPlantarsePanelJugar);
        panelJugar.add(textoManoJugadorInglesa);
        panelJugar.add(textoManoJugadorInglesaLista);
        panelJugar.add(textoManoJugadorEspecial);
        panelJugar.add(textoManoCrupier);
        panelJugar.add(textoRondasPanelJugar);

        // Agregar botones al panelJugar2
        panelJugar2.add(botonPedirPanelJugar2);
        panelJugar2.add(botonPlantarsePanelJugar2);
        panelJugar.add(textoPuntosJugador1);
        panelJugar.add(textoPuntosJugador2);
        panelJugar2.add(textoManoJugadorInglesa2);
        panelJugar2.add(textoManoJugadorInglesaLista2);
        panelJugar2.add(textoManoJugadorEspecial2);
        panelJugar2.add(textoManoCrupier2);
        panelJugar2.add(textoRondasPanelJugar2);

        // Agregar botones al panelResultado
        panelResultado.add(continuar);
        panelResultado.add(textoManoCompletaCrupier);
        panelResultado.add(textoManoJugador1);
        panelResultado.add(textoManoJugador2);
        panelResultado.add(textoGanadores);
        panelResultado.add(textoGanadoresJugador1);
        panelResultado.add(textoGanadoresJugador2);
        panelResultado.add(verResumen);

        // Agregar botones al panelResumen
        panelResumen.add(siguenteRonda);
        panelResumen.add(textoPuntosJugador1);
        panelResumen.add(textoPuntosJugador2);
        panelResumen.add(textoRondasPanelResumen);

        // Agregar botones al panelFinal
        panelFinal.add(textoFinal);
        panelFinal.add(botonFinal);

        // Crear un CardLayout y asignarlo al contenedor principal
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);

        // Agregar paneles al contenedor principal con una clave.
        getContentPane().add(panelInicial, "panelInicial");
        getContentPane().add(panelJugar, "panelJugar");
        getContentPane().add(panelJugar2, "panelJugar2");
        getContentPane().add(panelResultado, "panelResultado");
        getContentPane().add(panelResumen, "panelResumen");
        getContentPane().add(panelFinal, "panelFinal");

        // Mostrar el JFrame
        setVisible(true);
    }

}