import javax.swing.JTextArea;

public class Filosofo extends Thread{
    int num;
    static int number=0;
    private Tenedor tenedor;
    private JTextArea f;

    public Filosofo(Tenedor tenedor, JTextArea f) {
        super();
	    this.tenedor=tenedor;
        num=number;
	    number++;
        this.f = f;
    }
    
    private void comiendo() {
        // Poner al filosofo en el JTextArea
        f.append("Filosofo " + num + " comiendo\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
    }
    private void pensando() {
        // Poner al filosofo en el JTextArea
        f.append("Filosofo " + num + " pensando\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
    }

    public void run(){
        while(true){
            pensando();
            tenedor.take();
            comiendo();
            tenedor.release();
        }
    }
}
