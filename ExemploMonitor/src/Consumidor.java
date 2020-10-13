public class Consumidor extends Thread {
    private Armazem armazem;
    private int id;

    public Consumidor(Armazem a, int id){
        this.armazem = a;
        this.id = id;
    }

    public void run(){
        for(int i = 0; i < 10; ++i){
            int produto = this.armazem.pegar();
            System.out.println("Consumidor id:" + this.id + " pegou produto " + produto);
        }
    }
}
