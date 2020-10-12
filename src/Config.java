import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Classe Config: lê do arquivo e armazena as configurações do programa
 */
public class Config {
    private final String algoritmo; //Algoritmo a ser usado para o escalonamento, pode ser fcfs ou rr
    private final LinkedList<PCB> pcbList; //lista dos PCBs dos processos a serem escalonados
    private final long tamanhoQuantum; //tamanho do quantum caso esteja usando Round Robin

    //Construtor da classe config: le o arquivo de configuração
    public Config(String filename) throws Exception{
        File arquivo = new File(filename);
        Scanner fin = new Scanner(arquivo);

        this.algoritmo = fin.nextLine();

        if(this.algoritmo.equals("rr")){
            this.tamanhoQuantum = fin.nextInt();
        } else {
            this.tamanhoQuantum = 0;
        }

        this.pcbList = new LinkedList<>();
        while(fin.hasNext()){
            String linha = fin.nextLine();
            String[] dados = linha.split(":\\(|,|\\)");
            if(dados.length != 3) throw new Exception("Erro na leitura do arquivo de configuração");
            PCB pcb = new PCB(Integer.parseInt(dados[0]), Long.parseLong(dados[1]), Long.parseLong(dados[2]));
            this.pcbList.add(pcb);
        }
    }

    public String getAlgoritmo(){
        return this.algoritmo;
    }

    public LinkedList<PCB> getPcbList() {
        return pcbList;
    }

    public long getTamanhoQuantum() {
        return tamanhoQuantum;
    }
}

//Por Bruno de Castro Brezolin e Leonardo Benitez