import static java.lang.Thread.sleep;

public class Produtor extends Thread {
    private Armazem armazem;
    private int id;

    public Produtor(Armazem a, int id){
        this.armazem = a;
        this.id = id;
    }

    public void run(){
        for(int i = 0 ; i < 10 ; ++i){
            this.armazem.colocar(i);
            System.out.println("Produtor id" + this.id + " colocou o produto " + i);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
