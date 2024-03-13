public class Tenedor {
    // Iniciar todos a false
    private boolean[] taking={false,false,false,false,false};

    public synchronized void release() {
        Filosofo phi=(Filosofo) Thread.currentThread();
	    int num = phi.num;
	    System.out.format("Philosopher\t%d\treleases Chopstick\n", num);
	    taking[num]=false;
	    taking[((num+1)%5)]=false;
	    notifyAll();
    }

    public synchronized void take() {
	    Filosofo phi=(Filosofo) Thread.currentThread();
	    int num=phi.num;
	    while(taking[((num+1)%5)] || taking[num]){
            try {
                wait();
            } catch (InterruptedException e){}
        }
        System.out.format("Philosopher\t%d\ttakes Chopstick\n", num);
        taking[num]=true;
        taking[((num+1)%5)]=true;
    }
}
