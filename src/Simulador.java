import java.io.IOException;
/**
 * Classe que contem o main para executar os algoritmos de escalonamento
 */
public class Simulador {
    public static void main(String[] args) {

        Config c;
        try{
            c = new Config(args[0]);
            //System.out.println(c.getAlgoritmo());
            //System.out.println(c.getTamanhoQuantum());
            //System.out.println(c.getPcbList());

            Escalonador algoritmo = null;
            if(c.getAlgoritmo().equals("fcfs")){
                algoritmo = new FCFS(c.getPcbList(), "resultado_fcfs.txt");
            }

            if(c.getAlgoritmo().equals("rr")){
                algoritmo = new RR(c.getPcbList(), c.getTamanhoQuantum(), "resultado_rr.txt");
            }

            if(algoritmo != null){
                algoritmo.executar();
            } else {
                System.out.println("Falha ao montar o algoritmo");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}

//Por Bruno de Castro Brezolin e Leonardo Benitez