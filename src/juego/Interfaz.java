package juego;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import personas.Jugador;
import personas.Crupier;
import barajas.BarajaInglesa;
import barajas.BarajaEspecial;

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
    private JPanel botonesEspecialesPanel;
    private JPanel botonesEspecialesPanel2;
    private JPanel rondaSoloPanel2;
    private JButton continuar;
    private JButton verResumen;
    private Crupier crupier;

    // Colores personalizados
    private final Color COLOR_FONDO = new Color(53, 101, 77); // Verde casino
    private final Color COLOR_BOTONES = new Color(214, 177, 96); // Dorado
    private final Color COLOR_TEXTO = new Color(255, 255, 255); // Blanco
    private final Color COLOR_HOVER = new Color(234, 197, 116); // Dorado más claro
    private final Color COLOR_BORDE = new Color(184, 147, 66); // Dorado oscuro
    private final Color COLOR_CONTENEDOR = new Color(45, 85, 65); // Verde más oscuro para contenedores

    // Método para crear un panel con efecto de sombra
    private JPanel crearPanelConSombra() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dibujar sombra
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 20, 20);

                // Dibujar fondo
                g2.setColor(COLOR_CONTENEDOR);
                g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 20, 20);

                g2.dispose();
            }
        };
    }

    // Método para crear un borde decorativo moderno
    private Border crearBordeDecorado(String titulo) {
        Border lineBorder = BorderFactory.createLineBorder(COLOR_BORDE, 2);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        if (titulo != null) {
            TitledBorder titledBorder = BorderFactory.createTitledBorder(compoundBorder, titulo);
            titledBorder.setTitleColor(COLOR_TEXTO);
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
            return titledBorder;
        }
        return compoundBorder;
    }

    // Método para crear un panel contenedor moderno
    private JPanel crearContenedor(String titulo) {
        JPanel panel = crearPanelConSombra();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(crearBordeDecorado(titulo));
        panel.setBackground(COLOR_CONTENEDOR);
        return panel;
    }

    // Método para crear un panel de información
    private JPanel crearPanelInfo(String titulo) {
        JPanel panel = crearPanelConSombra();
        panel.setLayout(new GridBagLayout()); // Cambiado a GridBagLayout
        panel.setBorder(crearBordeDecorado(titulo));
        panel.setBackground(COLOR_CONTENEDOR);
        return panel;
    }

    // Método para crear un panel de botones
    private JPanel crearPanelBotones(String titulo) {
        JPanel panel = crearPanelConSombra();
        if ("Cartas Especiales".equals(titulo)) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setMaximumSize(new Dimension(300, 150)); // Limitar altura máxima
            panel.setBorder(BorderFactory.createCompoundBorder(
                    crearBordeDecorado(titulo),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20))); // Padding interno
        } else {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            panel.setBorder(crearBordeDecorado(titulo));
        }
        panel.setBackground(COLOR_CONTENEDOR);
        return panel;
    }

    // Método para agregar componentes a un panel con GridBagLayout
    private void agregarComponenteConConstraints(JPanel panel, Component componente, int gridx, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(componente, gbc);
    }

    // Método para estilizar botones
    private void estilizarBoton(JButton boton) {
        boton.setBackground(COLOR_BOTONES);
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("Arial", Font.BOLD, 14));

        // Crear borde redondeado para botones
        boton.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_BORDE);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, 15, 15);
                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(8, 20, 8, 20);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover mejorado
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(COLOR_BOTONES);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                boton.setBackground(COLOR_BORDE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boton.setBackground(COLOR_HOVER);
            }
        });
    }

    // Método para estilizar etiquetas
    private void estilizarLabel(JLabel label) {
        label.setForeground(COLOR_TEXTO);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Método para estilizar paneles
    private void estilizarPanel(JPanel panel) {
        panel.setBackground(COLOR_CONTENEDOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void actualizarPanelCartasEspeciales(Jugador jugador, JPanel panel, JButton sumador, JButton cambio1,
            JButton cambio2, JPanel panelPadre, int gridy) {
        if (panelPadre == panelJugar2 && rondaSoloPanel2 != null) {
            panelPadre.remove(panel);
            panelPadre.remove(rondaSoloPanel2);
        } else {
            panelPadre.remove(panel);
        }
        panel.removeAll();
        boolean hayEspeciales = false;
        if ("Cambio de Carta".equals(jugador.getManoEspecial())) {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            panel.add(cambio1);
            panel.add(cambio2);
            hayEspeciales = true;
        }
        if ("Sumador".equals(jugador.getManoEspecial())) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(sumador);
            hayEspeciales = true;
        }
        if (hayEspeciales) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.insets = new Insets(10, 10, 10, 10);
            panelPadre.add(panel, gbc);
        }
        if (panelPadre == panelJugar2 && rondaSoloPanel2 != null) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(10, 10, 10, 10);
            panelPadre.add(rondaSoloPanel2, gbc);
        }
        panel.setVisible(hayEspeciales);
        panel.revalidate();
        panel.repaint();
        panelPadre.revalidate();
        panelPadre.repaint();
    }

    // Función auxiliar para pedir valor del comodín
    private int pedirValorComodin() {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, "¡Te tocó un comodín! Ingresa el valor (1 a 11):",
                    "Comodín", JOptionPane.QUESTION_MESSAGE);
            if (valor == null || valor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los valores válidos son del 1 al 11.", "Valor inválido",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
            try {
                int val = Integer.parseInt(valor.trim());
                if (val >= 1 && val <= 11)
                    return val;
                else {
                    JOptionPane.showMessageDialog(null, "Los valores válidos son del 1 al 11.", "Valor inválido",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Los valores válidos son del 1 al 11.", "Valor inválido",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public Interfaz() {
        super("Blackjack Especial");
        try {
            // Configuración del JFrame
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600); // Ventana más grande
            setLocationRelativeTo(null);

            // Configurar el CardLayout
            cardLayout = new CardLayout();
            setLayout(cardLayout);

            // --- Pantalla inicial moderna y centrada ---
            panelInicial = new JPanel();
            panelInicial.setLayout(new GridBagLayout());
            estilizarPanel(panelInicial);

            // Contenedor para la imagen
            JPanel contenedorImagen = crearContenedor("Blackjack Especial");
            JLabel imagen = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/blackjack.jpg"));
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImg);
            imagen.setIcon(newIcon);
            imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
            contenedorImagen.add(imagen);

            // Contenedor para los botones
            JPanel contenedorBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            contenedorBotones.setBorder(crearBordeDecorado("Menú Principal"));
            contenedorBotones.setBackground(COLOR_CONTENEDOR);
            JButton botonJugarPanelInicial = new JButton("Jugar");
            JButton botonSalirPanelInicial = new JButton("Salir");
            JButton botonReglas = new JButton("Reglas");
            JButton estrategia = new JButton("Estrategia");
            estilizarBoton(botonJugarPanelInicial);
            estilizarBoton(botonSalirPanelInicial);
            estilizarBoton(botonReglas);
            estilizarBoton(estrategia);
            contenedorBotones.add(botonJugarPanelInicial);
            contenedorBotones.add(botonReglas);
            contenedorBotones.add(estrategia);
            contenedorBotones.add(botonSalirPanelInicial);

            // Panel intermedio para centrar el contenedor de botones
            JPanel panelBotonesWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelBotonesWrapper.setOpaque(false);
            panelBotonesWrapper.add(contenedorBotones);

            // Panel central que contiene la imagen y el menú
            JPanel panelCentral = new JPanel();
            panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
            panelCentral.setOpaque(false);
            panelCentral.add(contenedorImagen);
            panelCentral.add(Box.createRigidArea(new Dimension(0, 30)));
            panelCentral.add(panelBotonesWrapper);

            // Centrar el panelCentral en panelInicial
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            panelInicial.add(panelCentral, gbc);

            // Listeners de los botones de la pantalla inicial
            botonReglas.addActionListener(e -> {
                StringBuilder contenido = new StringBuilder();
                try (BufferedReader br = new BufferedReader(
                        new FileReader(getClass().getResource("/Archivos/Reglas.txt").getFile()))) {
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
            });
            estrategia.addActionListener(e -> {
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Imagenes/estrategia.jpg"));
                JOptionPane.showMessageDialog(null, null, "Estrategia", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            });
            botonSalirPanelInicial.addActionListener(e -> System.exit(0));
            botonJugarPanelInicial.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Pedir nombres
                    String Nombre1 = JOptionPane.showInputDialog("Ingrese el nombre del jugador Nº1: ");
                    String Nombre2 = JOptionPane.showInputDialog("Ingrese el nombre del jugador Nº2: ");

                    // Inicializar objetos de juego
                    Jugador jugador1 = new Jugador(Nombre1, null, null, 5);
                    Jugador jugador2 = new Jugador(Nombre2, null, null, 5);
                    jugador1.setEspecialUsada(false);
                    jugador2.setEspecialUsada(false);
                    crupier = new Crupier(null);
                    BarajaInglesa barajaInglesa = new BarajaInglesa();
                    BarajaEspecial barajaEspecial = new BarajaEspecial();

                    // Inicialización y organización de los paneles de juego
                    panelJugar = new JPanel(new GridBagLayout());
                    panelJugar2 = new JPanel(new GridBagLayout());
                    panelResultado = new JPanel(new GridBagLayout());
                    panelResumen = new JPanel(new GridBagLayout());
                    panelFinal = new JPanel(new GridBagLayout());
                    estilizarPanel(panelJugar);
                    estilizarPanel(panelJugar2);
                    estilizarPanel(panelResultado);
                    estilizarPanel(panelResumen);
                    estilizarPanel(panelFinal);

                    // Labels y componentes de juego
                    JLabel textoPuntosJugador1 = new JLabel();
                    JLabel textoPuntosJugador2 = new JLabel();
                    JLabel textoRondasPanelJugar = new JLabel("Ronda: " + rondas);
                    JLabel textoRondasPanelJugar2 = new JLabel("Ronda: " + rondas);
                    JLabel textoRondasPanelResumen = new JLabel("Ronda: " + rondas);
                    estilizarLabel(textoPuntosJugador1);
                    estilizarLabel(textoPuntosJugador2);
                    estilizarLabel(textoRondasPanelJugar);
                    estilizarLabel(textoRondasPanelJugar2);
                    estilizarLabel(textoRondasPanelResumen);
                    JButton botonPedirPanelJugar = new JButton("Pedir");
                    JButton botonPlantarsePanelJugar = new JButton("Plantarse");
                    JButton botonCambioDeCarta1 = new JButton("Cambiar carta nº1");
                    JButton botonCambioDeCarta2 = new JButton("Cambiar carta nº2");
                    JButton botonCambioDeCarta1_2 = new JButton("Cambiar carta nº1");
                    JButton botonCambioDeCarta2_2 = new JButton("Cambiar carta nº2");
                    JButton sumador1 = new JButton("Sumador");
                    JButton sumador2 = new JButton("Sumador");
                    estilizarBoton(botonPedirPanelJugar);
                    estilizarBoton(botonPlantarsePanelJugar);
                    estilizarBoton(botonCambioDeCarta1);
                    estilizarBoton(botonCambioDeCarta2);
                    estilizarBoton(botonCambioDeCarta1_2);
                    estilizarBoton(botonCambioDeCarta2_2);
                    estilizarBoton(sumador1);
                    estilizarBoton(sumador2);
                    JLabel textoManoJugadorInglesa = new JLabel();
                    JLabel textoManoJugadorInglesaLista = new JLabel();
                    JLabel textoManoJugadorEspecial = new JLabel();
                    JLabel textoManoCrupier = new JLabel();
                    estilizarLabel(textoManoJugadorInglesa);
                    estilizarLabel(textoManoJugadorInglesaLista);
                    estilizarLabel(textoManoJugadorEspecial);
                    estilizarLabel(textoManoCrupier);
                    JLabel textoManoJugadorInglesa2 = new JLabel();
                    JLabel textoManoJugadorInglesaLista2 = new JLabel();
                    JLabel textoManoJugadorEspecial2 = new JLabel();
                    JLabel textoManoCrupier2 = new JLabel();
                    estilizarLabel(textoManoJugadorInglesa2);
                    estilizarLabel(textoManoJugadorInglesaLista2);
                    estilizarLabel(textoManoJugadorEspecial2);
                    estilizarLabel(textoManoCrupier2);
                    JButton botonPedirPanelJugar2 = new JButton("Pedir");
                    JButton botonPlantarsePanelJugar2 = new JButton("Plantarse");
                    estilizarBoton(botonPedirPanelJugar2);
                    estilizarBoton(botonPlantarsePanelJugar2);
                    JLabel textoManoCompletaCrupier = new JLabel();
                    JLabel textoManoJugador1 = new JLabel();
                    JLabel textoManoJugador2 = new JLabel();
                    JLabel textoGanadores = new JLabel();
                    estilizarLabel(textoManoCompletaCrupier);
                    estilizarLabel(textoManoJugador1);
                    estilizarLabel(textoManoJugador2);
                    estilizarLabel(textoGanadores);
                    JButton siguenteRonda = new JButton("Siguiente ronda");
                    estilizarBoton(siguenteRonda);
                    JLabel textoFinal = new JLabel();
                    estilizarLabel(textoFinal);
                    JButton botonFinal = new JButton("Salir");
                    estilizarBoton(botonFinal);
                    // Paneles internos y organización visual
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);
                    JPanel infoJugadorPanel = crearPanelInfo("Información del Jugador 1");
                    agregarComponenteConConstraints(infoJugadorPanel, textoManoJugadorInglesa, 0, 0);
                    agregarComponenteConConstraints(infoJugadorPanel, textoManoJugadorInglesaLista, 0, 1);
                    agregarComponenteConConstraints(infoJugadorPanel, textoManoJugadorEspecial, 0, 2);
                    agregarComponenteConConstraints(infoJugadorPanel, textoManoCrupier, 0, 3);
                    JPanel botonesJugarPanel = crearPanelBotones("Acciones");
                    botonesJugarPanel.add(botonPedirPanelJugar);
                    botonesJugarPanel.add(botonPlantarsePanelJugar);
                    botonesEspecialesPanel = crearPanelBotones("Cartas Especiales");
                    botonesEspecialesPanel2 = crearPanelBotones("Cartas Especiales");
                    JPanel puntosPanel = crearPanelBotones(null);
                    puntosPanel.add(textoPuntosJugador1);
                    puntosPanel.add(textoPuntosJugador2);
                    puntosPanel.add(textoRondasPanelJugar);
                    JPanel infoJugador2Panel = crearPanelInfo("Información del Jugador 2");
                    agregarComponenteConConstraints(infoJugador2Panel, textoManoJugadorInglesa2, 0, 0);
                    agregarComponenteConConstraints(infoJugador2Panel, textoManoJugadorInglesaLista2, 0, 1);
                    agregarComponenteConConstraints(infoJugador2Panel, textoManoJugadorEspecial2, 0, 2);
                    agregarComponenteConConstraints(infoJugador2Panel, textoManoCrupier2, 0, 3);
                    JPanel botonesJugar2Panel = crearPanelBotones("Acciones");
                    botonesJugar2Panel.add(botonPedirPanelJugar2);
                    botonesJugar2Panel.add(botonPlantarsePanelJugar2);
                    JPanel infoResultadoPanel = crearPanelInfo("Resultado de la Ronda");
                    agregarComponenteConConstraints(infoResultadoPanel, textoManoCompletaCrupier, 0, 0);
                    agregarComponenteConConstraints(infoResultadoPanel, textoManoJugador1, 0, 1);
                    agregarComponenteConConstraints(infoResultadoPanel, textoManoJugador2, 0, 2);
                    agregarComponenteConConstraints(infoResultadoPanel, textoGanadores, 0, 3);
                    JPanel botonesResultadoPanel = crearPanelBotones("Acciones");
                    botonesResultadoPanel.add(continuar);
                    botonesResultadoPanel.add(verResumen);
                    JPanel infoResumenPanel = crearPanelInfo("Resumen del Juego");
                    agregarComponenteConConstraints(infoResumenPanel, textoPuntosJugador1, 0, 0);
                    agregarComponenteConConstraints(infoResumenPanel, textoPuntosJugador2, 0, 1);
                    agregarComponenteConConstraints(infoResumenPanel, textoRondasPanelResumen, 0, 2);
                    agregarComponenteConConstraints(infoResumenPanel, siguenteRonda, 0, 3);
                    JPanel contenedorFinal = crearContenedor("Fin del Juego");
                    contenedorFinal.setLayout(new BoxLayout(contenedorFinal, BoxLayout.Y_AXIS));
                    JPanel resultadoFinalPanel = new JPanel();
                    resultadoFinalPanel.setBackground(COLOR_FONDO);
                    resultadoFinalPanel.add(textoFinal);
                    JPanel botonFinalPanel = new JPanel();
                    botonFinalPanel.setBackground(COLOR_FONDO);
                    botonFinalPanel.add(botonFinal);
                    contenedorFinal.add(resultadoFinalPanel);
                    contenedorFinal.add(Box.createRigidArea(new Dimension(0, 20)));
                    contenedorFinal.add(botonFinalPanel);
                    panelFinal.add(contenedorFinal);
                    // Organización de panelJugar
                    GridBagConstraints gbcJugar = new GridBagConstraints();
                    gbcJugar.insets = new Insets(10, 10, 10, 10);
                    gbcJugar.gridx = 0;
                    gbcJugar.gridy = 0;
                    panelJugar.add(infoJugadorPanel, gbcJugar);
                    gbcJugar.gridy = 1;
                    panelJugar.add(botonesJugarPanel, gbcJugar);
                    gbcJugar.gridy = 2;
                    panelJugar.add(botonesEspecialesPanel, gbcJugar);
                    gbcJugar.gridy = 3;
                    panelJugar.add(puntosPanel, gbcJugar);
                    // Organización de panelJugar2
                    GridBagConstraints gbcJugar2 = new GridBagConstraints();
                    gbcJugar2.insets = new Insets(10, 10, 10, 10);
                    gbcJugar2.gridx = 0;
                    gbcJugar2.gridy = 0;
                    panelJugar2.add(infoJugador2Panel, gbcJugar2);
                    gbcJugar2.gridy = 1;
                    panelJugar2.add(botonesJugar2Panel, gbcJugar2);
                    gbcJugar2.gridy = 2;
                    panelJugar2.add(botonesEspecialesPanel2, gbcJugar2);
                    gbcJugar2.gridy = 3;
                    rondaSoloPanel2 = crearPanelBotones(null);
                    rondaSoloPanel2.add(textoRondasPanelJugar2);
                    panelJugar2.add(rondaSoloPanel2, gbcJugar2);
                    // Organización de panelResultado
                    GridBagConstraints gbcResultado = new GridBagConstraints();
                    gbcResultado.insets = new Insets(10, 10, 10, 10);
                    gbcResultado.gridx = 0;
                    gbcResultado.gridy = 0;
                    panelResultado.add(infoResultadoPanel, gbcResultado);
                    gbcResultado.gridy = 1;
                    panelResultado.add(botonesResultadoPanel, gbcResultado);
                    // Organización de panelResumen
                    GridBagConstraints gbcResumen = new GridBagConstraints();
                    gbcResumen.insets = new Insets(10, 10, 10, 10);
                    gbcResumen.gridx = 0;
                    gbcResumen.gridy = 0;
                    panelResumen.add(infoResumenPanel, gbcResumen);
                    gbcResumen.gridy = 1;
                    JPanel siguienteRondaPanel = crearPanelBotones("Siguiente Ronda");
                    siguienteRondaPanel.add(siguenteRonda);
                    panelResumen.add(siguienteRondaPanel, gbcResumen);
                    // Asignar cartas iniciales y cartas especiales
                    ArrayList<Integer> mano1 = barajaInglesa.repartirCarta();
                    for (int i = 0; i < mano1.size(); i++) {
                        if (mano1.get(i) == 11) {
                            String valor = JOptionPane.showInputDialog(null,
                                    "Te tocó un comodín. Ingresa el valor (1-11):", "Comodín",
                                    JOptionPane.QUESTION_MESSAGE);
                            try {
                                int val = Integer.parseInt(valor);
                                if (val >= 1 && val <= 11)
                                    mano1.set(i, val);
                            } catch (Exception ex) {
                            }
                        }
                    }
                    jugador1.setManoInglesa(mano1);
                    if (!jugador1.isEspecialUsada()) {
                        jugador1.setManoEspecial(barajaEspecial.repartirCarta());
                    } else {
                        jugador1.setManoEspecial("");
                    }
                    ArrayList<Integer> mano2 = barajaInglesa.repartirCarta();
                    for (int i = 0; i < mano2.size(); i++) {
                        if (mano2.get(i) == 11) {
                            String valor = JOptionPane.showInputDialog(null,
                                    "Te tocó un comodín. Ingresa el valor (1-11):", "Comodín",
                                    JOptionPane.QUESTION_MESSAGE);
                            try {
                                int val = Integer.parseInt(valor);
                                if (val >= 1 && val <= 11)
                                    mano2.set(i, val);
                            } catch (Exception ex) {
                            }
                        }
                    }
                    jugador2.setManoInglesa(mano2);
                    if (!jugador2.isEspecialUsada()) {
                        jugador2.setManoEspecial(barajaEspecial.repartirCarta());
                    } else {
                        jugador2.setManoEspecial("");
                    }
                    crupier.setMano(barajaInglesa.repartirCarta());

                    // Actualizar labels de información
                    textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                    textoManoJugadorInglesa.setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                    textoManoJugadorEspecial.setText("Cartas especiales: " + jugador1.getManoEspecial());
                    textoManoCrupier.setText("Carta del Crupier: " + crupier.unaCarta());

                    textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                    textoManoJugadorInglesa2.setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                    textoManoJugadorEspecial2.setText("Cartas especiales: " + jugador2.getManoEspecial());
                    textoManoCrupier2.setText("Carta del Crupier: " + crupier.unaCarta());

                    // Mostrar panel de cartas especiales solo si corresponde
                    actualizarPanelCartasEspeciales(jugador1, botonesEspecialesPanel, sumador1, botonCambioDeCarta1,
                            botonCambioDeCarta2, panelJugar, 2);
                    actualizarPanelCartasEspeciales(jugador2, botonesEspecialesPanel2, sumador2, botonCambioDeCarta1_2,
                            botonCambioDeCarta2_2, panelJugar2, 2);

                    // Actualizar puntos
                    textoPuntosJugador1.setText("Puntos de " + jugador1.getNombre() + ": " + jugador1.puntos);
                    textoPuntosJugador2.setText("Puntos de " + jugador2.getNombre() + ": " + jugador2.puntos);

                    // Al mostrar la pantalla de resultado tras el turno del jugador 2:
                    textoManoCompletaCrupier
                            .setText("Cartas del crupier: " + crupier.getMano() + " | Suma: " + crupier.sumarMano());
                    textoManoJugador1.setText("Cartas de " + jugador1.getNombre() + ": " + jugador1.getManoInglesa()
                            + " | Suma: " + jugador1.sumarMano());
                    textoManoJugador2.setText("Cartas de " + jugador2.getNombre() + ": " + jugador2.getManoInglesa()
                            + " | Suma: " + jugador2.sumarMano());

                    // Lógica para mostrar solo el botón correspondiente
                    definirBotonesResultado();

                    // --- FUNCIONALIDAD DE BOTONES JUGADOR 1 ---
                    botonPedirPanelJugar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int prob = (int) (Math.random() * 52);
                            int carta;
                            if (prob < 10) {
                                carta = pedirValorComodin();
                            } else {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                carta = nuevaBaraja.repartirUnaCarta();
                            }
                            jugador1.getManoInglesa().add(carta);
                            textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                            textoManoJugadorInglesa
                                    .setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                            if (jugador1.sumarMano() > 21) {
                                JOptionPane.showMessageDialog(null, "Te has pasado de 21. Turno del jugador 2.");
                                cardLayout.show(getContentPane(), "panelJugar2");
                            }
                        }
                    });

                    botonPlantarsePanelJugar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(getContentPane(), "panelJugar2");
                        }
                    });

                    sumador1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jugador1.puntos++;
                            jugador1.setManoEspecial("");
                            jugador1.setEspecialUsada(true);
                            textoPuntosJugador1.setText("Puntos de " + jugador1.getNombre() + ": " + jugador1.puntos);
                            actualizarPanelCartasEspeciales(jugador1, botonesEspecialesPanel, sumador1,
                                    botonCambioDeCarta1, botonCambioDeCarta2, panelJugar, 2);
                            JOptionPane.showMessageDialog(null,
                                    "Nueva puntuación de " + jugador1.getNombre() + " = " + jugador1.puntos);
                        }
                    });

                    botonCambioDeCarta1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (jugador1.getManoInglesa().size() > 0) {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                jugador1.getManoInglesa().set(0, nuevaBaraja.repartirUnaCarta());
                                jugador1.setManoEspecial("");
                                jugador1.setEspecialUsada(true);
                                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                                textoManoJugadorInglesa
                                        .setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                                actualizarPanelCartasEspeciales(jugador1, botonesEspecialesPanel, sumador1,
                                        botonCambioDeCarta1, botonCambioDeCarta2, panelJugar, 2);
                            }
                        }
                    });

                    botonCambioDeCarta2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (jugador1.getManoInglesa().size() > 1) {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                jugador1.getManoInglesa().set(1, nuevaBaraja.repartirUnaCarta());
                                jugador1.setManoEspecial("");
                                jugador1.setEspecialUsada(true);
                                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                                textoManoJugadorInglesa
                                        .setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                                actualizarPanelCartasEspeciales(jugador1, botonesEspecialesPanel, sumador1,
                                        botonCambioDeCarta1, botonCambioDeCarta2, panelJugar, 2);
                            }
                        }
                    });

                    // --- FUNCIONALIDAD DE BOTONES JUGADOR 2 ---
                    botonPedirPanelJugar2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int prob = (int) (Math.random() * 52);
                            int carta;
                            if (prob < 10) {
                                carta = pedirValorComodin();
                            } else {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                carta = nuevaBaraja.repartirUnaCarta();
                            }
                            jugador2.getManoInglesa().add(carta);
                            textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                            textoManoJugadorInglesa2
                                    .setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                            if (jugador2.sumarMano() > 21) {
                                textoManoCompletaCrupier.setText(
                                        "Cartas del crupier: " + crupier.getMano() + " | Suma: " + crupier.sumarMano());
                                textoManoJugador1.setText("Cartas de " + jugador1.getNombre() + ": "
                                        + jugador1.getManoInglesa() + " | Suma: " + jugador1.sumarMano());
                                textoManoJugador2.setText("Cartas de " + jugador2.getNombre() + ": "
                                        + jugador2.getManoInglesa() + " | Suma: " + jugador2.sumarMano());
                                JOptionPane.showMessageDialog(null, "Te has pasado de 21. Fin de la ronda.");
                                cardLayout.show(getContentPane(), "panelResultado");
                                definirBotonesResultado();
                            }
                        }
                    });

                    botonPlantarsePanelJugar2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            textoManoCompletaCrupier.setText(
                                    "Cartas del crupier: " + crupier.getMano() + " | Suma: " + crupier.sumarMano());
                            textoManoJugador1.setText("Cartas de " + jugador1.getNombre() + ": "
                                    + jugador1.getManoInglesa() + " | Suma: " + jugador1.sumarMano());
                            textoManoJugador2.setText("Cartas de " + jugador2.getNombre() + ": "
                                    + jugador2.getManoInglesa() + " | Suma: " + jugador2.sumarMano());
                            cardLayout.show(getContentPane(), "panelResultado");
                            definirBotonesResultado();
                        }
                    });

                    sumador2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jugador2.puntos++;
                            jugador2.setManoEspecial("");
                            jugador2.setEspecialUsada(true);
                            textoPuntosJugador2.setText("Puntos de " + jugador2.getNombre() + ": " + jugador2.puntos);
                            actualizarPanelCartasEspeciales(jugador2, botonesEspecialesPanel2, sumador2,
                                    botonCambioDeCarta1_2, botonCambioDeCarta2_2, panelJugar2, 2);
                            JOptionPane.showMessageDialog(null,
                                    "Nueva puntuación de " + jugador2.getNombre() + " = " + jugador2.puntos);
                        }
                    });

                    botonCambioDeCarta1_2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (jugador2.getManoInglesa().size() > 0) {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                jugador2.getManoInglesa().set(0, nuevaBaraja.repartirUnaCarta());
                                jugador2.setManoEspecial("");
                                jugador2.setEspecialUsada(true);
                                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                                textoManoJugadorInglesa2
                                        .setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                                actualizarPanelCartasEspeciales(jugador2, botonesEspecialesPanel2, sumador2,
                                        botonCambioDeCarta1_2, botonCambioDeCarta2_2, panelJugar2, 2);
                            }
                        }
                    });

                    botonCambioDeCarta2_2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (jugador2.getManoInglesa().size() > 1) {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                jugador2.getManoInglesa().set(1, nuevaBaraja.repartirUnaCarta());
                                jugador2.setManoEspecial("");
                                jugador2.setEspecialUsada(true);
                                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                                textoManoJugadorInglesa2
                                        .setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                                actualizarPanelCartasEspeciales(jugador2, botonesEspecialesPanel2, sumador2,
                                        botonCambioDeCarta1_2, botonCambioDeCarta2_2, panelJugar2, 2);
                            }
                        }
                    });

                    // Listener para el botón 'Continuar'
                    continuar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (crupier.sumarMano() < 17) {
                                BarajaInglesa nuevaBaraja = new BarajaInglesa();
                                crupier.getMano().add(nuevaBaraja.repartirUnaCarta());
                            }
                            textoManoCompletaCrupier.setText(
                                    "Cartas del crupier: " + crupier.getMano() + " | Suma: " + crupier.sumarMano());
                            definirBotonesResultado();
                        }
                    });

                    // Listener para el botón 'Ver resumen'
                    verResumen.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int sumaCrupier = crupier.sumarMano();
                            int suma1 = jugador1.sumarMano();
                            int suma2 = jugador2.sumarMano();

                            if (suma1 > 21) {
                                jugador1.puntos--;
                            } else if ((sumaCrupier > 21 && suma1 <= 21) || (suma1 > sumaCrupier && suma1 <= 21)) {
                                jugador1.puntos++;
                            } else if ((suma1 < sumaCrupier && sumaCrupier <= 21)
                                    || (sumaCrupier <= 21 && suma1 > 21)) {
                                jugador1.puntos--;
                            }

                            if (suma2 > 21) {
                                jugador2.puntos--;
                            } else if ((sumaCrupier > 21 && suma2 <= 21) || (suma2 > sumaCrupier && suma2 <= 21)) {
                                jugador2.puntos++;
                            } else if ((suma2 < sumaCrupier && sumaCrupier <= 21)
                                    || (sumaCrupier <= 21 && suma2 > 21)) {
                                jugador2.puntos--;
                            }

                            StringBuilder resumen = new StringBuilder();
                            resumen.append("Crupier: " + sumaCrupier + "\n");
                            resumen.append("\n");
                            resumen.append(jugador1.getNombre() + ": " + suma1 + "\n");
                            resumen.append(jugador2.getNombre() + ": " + suma2 + "\n");
                            resumen.append("\n");
                            resumen.append("Puntuación total " + jugador1.getNombre() + ": " + jugador1.puntos + "\n");
                            resumen.append("Puntuación total " + jugador2.getNombre() + ": " + jugador2.puntos + "\n");
                            JOptionPane.showMessageDialog(null, resumen.toString(), "Resumen de la ronda",
                                    JOptionPane.INFORMATION_MESSAGE);
                            rondas++;
                            jugador1.getManoInglesa().clear();
                            jugador1.setManoEspecial("");
                            jugador2.getManoInglesa().clear();
                            jugador2.setManoEspecial("");
                            crupier.getMano().clear();
                            BarajaInglesa barajaInglesa = new BarajaInglesa();
                            BarajaEspecial barajaEspecial = new BarajaEspecial();
                            jugador1.setManoInglesa(barajaInglesa.repartirCarta());
                            if (!jugador1.isEspecialUsada()) {
                                jugador1.setManoEspecial(barajaEspecial.repartirCarta());
                            } else {
                                jugador1.setManoEspecial("");
                            }
                            jugador2.setManoInglesa(barajaInglesa.repartirCarta());
                            if (!jugador2.isEspecialUsada()) {
                                jugador2.setManoEspecial(barajaEspecial.repartirCarta());
                            } else {
                                jugador2.setManoEspecial("");
                            }
                            crupier.setMano(barajaInglesa.repartirCarta());
                            if (jugador1.getManoInglesa() != null && !jugador1.getManoInglesa().isEmpty()) {
                                textoManoJugadorInglesaLista.setText("Cartas inglesas: " + jugador1.getManoInglesa());
                                textoManoJugadorInglesa
                                        .setText("Suma de " + jugador1.getNombre() + ": " + jugador1.sumarMano());
                            }
                            if (jugador2.getManoInglesa() != null && !jugador2.getManoInglesa().isEmpty()) {
                                textoManoJugadorInglesaLista2.setText("Cartas inglesas: " + jugador2.getManoInglesa());
                                textoManoJugadorInglesa2
                                        .setText("Suma de " + jugador2.getNombre() + ": " + jugador2.sumarMano());
                            }
                            textoManoJugadorEspecial.setText("Cartas especiales: " + jugador1.getManoEspecial());
                            textoManoJugadorEspecial2.setText("Cartas especiales: " + jugador2.getManoEspecial());
                            if (crupier.getMano() != null && !crupier.getMano().isEmpty()) {
                                textoManoCrupier.setText("Carta del Crupier: " + crupier.unaCarta());
                                textoManoCrupier2.setText("Carta del Crupier: " + crupier.unaCarta());
                            }
                            textoPuntosJugador1.setText("Puntos de " + jugador1.getNombre() + ": " + jugador1.puntos);
                            textoPuntosJugador2.setText("Puntos de " + jugador2.getNombre() + ": " + jugador2.puntos);
                            textoRondasPanelJugar.setText("Ronda: " + rondas);
                            textoRondasPanelJugar2.setText("Ronda: " + rondas);
                            textoRondasPanelResumen.setText("Ronda: " + rondas);
                            actualizarPanelCartasEspeciales(jugador1, botonesEspecialesPanel, sumador1,
                                    botonCambioDeCarta1, botonCambioDeCarta2, panelJugar, 2);
                            actualizarPanelCartasEspeciales(jugador2, botonesEspecialesPanel2, sumador2,
                                    botonCambioDeCarta1_2, botonCambioDeCarta2_2, panelJugar2, 2);
                            cardLayout.show(getContentPane(), "panelJugar");

                            // Después de mostrar el resumen de la ronda, antes de iniciar la siguiente
                            // ronda:
                            if ((jugador1.puntos == 10 && jugador2.puntos < 10)
                                    || (jugador2.puntos == 0 && jugador1.puntos != 0)) {
                                JOptionPane.showMessageDialog(null,
                                        "Felicidades " + jugador1.getNombre() + " ganaste la partida!", "Fin del juego",
                                        JOptionPane.INFORMATION_MESSAGE);
                                cardLayout.show(getContentPane(), "panelInicial");
                                return;
                            }
                            if ((jugador2.puntos == 10 && jugador1.puntos < 10)
                                    || (jugador1.puntos == 0 && jugador2.puntos != 0)) {
                                JOptionPane.showMessageDialog(null,
                                        "Felicidades " + jugador2.getNombre() + " ganaste la partida!", "Fin del juego",
                                        JOptionPane.INFORMATION_MESSAGE);
                                cardLayout.show(getContentPane(), "panelInicial");
                                return;
                            }
                            if ((jugador1.puntos == 10 && jugador2.puntos == 10)
                                    || (jugador1.puntos == 0 && jugador2.puntos == 0)) {
                                JOptionPane.showMessageDialog(null, "Felicidades ambos son los ganadores!",
                                        "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                                cardLayout.show(getContentPane(), "panelInicial");
                                return;
                            }
                        }
                    });

                    add(panelJugar, "panelJugar");
                    add(panelJugar2, "panelJugar2");
                    add(panelResultado, "panelResultado");
                    add(panelResumen, "panelResumen");
                    add(panelFinal, "panelFinal");
                    cardLayout.show(getContentPane(), "panelJugar");
                }
            });

            // Al final, agrega los paneles al CardLayout:
            add(panelInicial, "panelInicial");

            // Muestra el panel inicial
            cardLayout.show(getContentPane(), "panelInicial");

            // Inicialización de los botones de resultado como atributos de la clase
            continuar = new JButton("Continuar");
            verResumen = new JButton("Ver resumen");
            estilizarBoton(continuar);
            estilizarBoton(verResumen);

        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    private void definirBotonesResultado() {
        if (panelResultado == null)
            return;
        int sumaCrupier = crupier.sumarMano();
        if (sumaCrupier < 17) {
            continuar.setVisible(true);
            verResumen.setVisible(false);
        } else {
            continuar.setVisible(false);
            verResumen.setVisible(true);
        }
    }
}
