public class Armazem {
    private int conteudo;
    private boolean ocupado = false;

    public synchronized int pegar(){
        while(!this.ocupado){
            try {
                System.out.println("Esperando produto disponível");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ocupado = false;
        notifyAll();
        return this.conteudo;
    }

    public synchronized void colocar(int conteudo){
        while(this.ocupado){
            try {
                System.out.println("Esperando armazem esvaziar");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.conteudo = conteudo;
        this.ocupado = true;
        notifyAll();
    }
}
