/* Descripción: Clase que simula un comensal
   que consume porciones de comida */

// TODO: Implementar interfaz gráfica

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Consumidor extends Thread {
    private int id;
    private Buffer b;
    public JButton eat; // Botón que simula la acción de comer
    
    // Constructor de la clase
    public Consumidor(Buffer b, int id, JButton eat) {
        this.b = b;
        this.id = id;
        this.eat = eat;
    }
    
    // Hilo que simula el consumo de comida
    @Override
    public void run() {
        while(true) {
            try {
                // Colorea el boton del consumidor que está comiendo
                b.consumir(eat);
                Main.eat_text.setText(Main.eat_text.getText() + '\n' +
                        "Consumidor " + id + " ha comido"
                    );
                System.out.println("Consumidor " + id + " ha comido");
                eat.setBackground(java.awt.Color.GREEN);
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
