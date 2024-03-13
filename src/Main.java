import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.*;
import javax.swing.*;

public class Main {
    // Productor-Consumidor
    private static JButton eat1 = new JButton("1");
    private static JButton eat2 = new JButton("2");
    private static JButton eat3 = new JButton("3");
    private static JButton eat4 = new JButton("4");
    private static JButton produce = new JButton("Productor");
    private static JTextArea productor_text = new JTextArea();

    public static JTextArea eat_text = new JTextArea();

    // Filosofos
    private static JTextArea f1 = new JTextArea();
    private static JTextArea f2 = new JTextArea();
    private static JTextArea f3 = new JTextArea();
    private static JTextArea f4 = new JTextArea();
    private static JTextArea f5 = new JTextArea();

    public Main() { }

    private void pc_GUI() {
        JFrame frame = new JFrame("Productor-Consumidor");
        JScrollPane scrollPane = new JScrollPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(3, 3));

        eat1.setBackground(Color.RED);
        eat2.setBackground(Color.RED);
        eat3.setBackground(Color.RED);
        eat4.setBackground(Color.RED);
        produce.setBackground(Color.GREEN);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.add(eat1);
        panel.add(eat2);
        panel.add(eat3);
        panel.add(eat4);
        panel.add(produce);
        panel.add(productor_text);
        // Hacer que el area del scroll panel abarque lo que resta de pantalla
        scrollPane.setViewportView(eat_text);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void productor_consumidor() {
        pc_GUI();
        Buffer b = new Buffer();
        
        Productor p = new Productor(b, produce, productor_text);
        
        Consumidor c1 = new Consumidor(b, 1, eat1);
        Consumidor c2 = new Consumidor(b, 2, eat2);
        Consumidor c3 = new Consumidor(b, 3, eat3);
        Consumidor c4 = new Consumidor(b, 4, eat4);
        
        p.start();
        try {
            p.join(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }

    private void filosofos() {
        Tenedor CH = new Tenedor();
        new Filosofo(CH, f1).start();
        new Filosofo(CH, f2).start();
        new Filosofo(CH, f3).start();
        new Filosofo(CH, f4).start();
        new Filosofo(CH, f5).start();
        f_GUI();
    }

    private void f_GUI() {
        JFrame frame = new JFrame("Filosofos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(3, 3));
        frame.add(f1);
        frame.add(f2);
        frame.add(f3);
        frame.add(f4);
        frame.add(f5);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Check args and run the program
        // if is pc run productor_consumidor

        if (args[0].equals("-pc")) {
            Main m = new Main();
            m.productor_consumidor();
        } else if (args[0].equals("-f")) {
            Main m = new Main();
            m.filosofos();
        } else {
            System.out.println("Invalid argument");
        }
    }
}
