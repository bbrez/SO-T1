import java.io.*;

/**
 * Classe Config: lê do arquivo e armazena as configurações do programa
 */
public class Config {
    public Config(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String read;
        while((read = br.readLine()) != null){
            System.out.println(read);
        }
    }

}
