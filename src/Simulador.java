import java.io.IOException;

public class Simulador {
    public static void main(String[] args) {

        Config c;
        try{
            c = new Config(args[0]);
            System.out.println(c.getAlgoritmo());
            System.out.println(c.getTamanhoQuantum());
            System.out.println(c.getPcbList());

            if(c.getAlgoritmo().equals("fcfs")){
                FCFS algoritmo = new FCFS(c.getPcbList(), "resultado_fcfs.txt");
                algoritmo.executar();
            }

            if(c.getAlgoritmo().equals("rr")){

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}

//Por Bruno de Castro Brezolin e Leonardo Benitez