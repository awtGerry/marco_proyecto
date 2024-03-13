import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Productor extends Thread {
    
    private final Buffer b;
    public static int noPlatillos = 8;
    private JButton eat;
    private JTextArea text;
    
    public Productor(Buffer b, JButton eat, JTextArea text) {
        this.b = b;
        this.eat = eat;
        this.text = text;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                b.producir(eat, text);
                eat.setBackground(java.awt.Color.GREEN);
                text.setText(text.getText() + '\n' +
                        "Productor ha producido " + b.getcomida() + " porciones de comida"
                    );
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
            
}
