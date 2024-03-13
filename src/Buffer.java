/* Propósito: Clase que simula un buffer de un productor-consumidor. */

/* Descripción:
   El buffer es una clase que simula un buffer de un productor-consumidor. La clase
   tiene un método para producir (despertar) y otro para consumir. El buffer tiene
   un atributo que es la cantidad de comida que tiene.
*/

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Buffer { 

    public int comida;
    
    public Buffer() {
        this.comida = 0;
    }
    
    public int getcomida() {
        return this.comida;
    }
    
    public synchronized void producir(JButton eat, JTextArea text) {
        while(this.comida > 0){
            try {
                wait();
                eat.setBackground(java.awt.Color.RED);
                text.setText("Esperando...");
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.comida = Productor.noPlatillos;
        notifyAll();
    }
    
    public synchronized void consumir(JButton eat) {
        while(this.comida == 0){
            try {
                wait();
                eat.setBackground(java.awt.Color.RED);
                eat.setText("Esperando...");
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.comida--;
        notifyAll();
    }
}
