public class ExemploMonitor {
    public static void main(String[] args) {
        Armazem a = new Armazem();
        Produtor p = new Produtor(a, 1);
        Consumidor c = new Consumidor(a, 1);

        p.start();
        c.start();
    }
}
