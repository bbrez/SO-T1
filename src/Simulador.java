import java.io.IOException;

public class Simulador {
    public static void main(String[] args) {
        try{
            Config cfg = new Config(args[1]);
        } catch (IOException e) {
            System.out.println("Não foi possivel ler o arquivo de configuração");
            System.out.println(e.getMessage());
        }
    }
}
