import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Escalonador {
    protected final LinkedList<PCB> pcbList; //Lista Encadeada que segura os processos
    protected int numProcessos; //Numero de processos
    protected long tempoTotal = 0; //Tempo para executar todos os processos
    protected float tempoExecMedio = 0, tempoEspMedio = 0; //Tempo de execução e de espera médio de todos os processos

    private String arquivoSaida;

    Escalonador(LinkedList<PCB> pcbs, String arquivoSaida) {
        this.pcbList = pcbs; //
        Collections.sort(pcbList);
        this.numProcessos = this.pcbList.size();
        this.arquivoSaida = arquivoSaida;
    }

    //Retorna a soma de todos os tempos de execução dos processos
    private long tempoExecTotal() {
        long soma = 0;
        for (PCB pcb : this.pcbList) {
            soma += pcb.getTempoProcessamento();
        }
        return soma;
    }

    protected void setupSaida(){
        if (!this.arquivoSaida.isBlank()) {
            try {
                System.setOut(new PrintStream(new File(arquivoSaida)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("não foi possivel abrir arquivo de saída");
                System.exit(1);
            }
        }
    }

    protected void calculaMedias() {
        for(PCB pcb : this.pcbList){
            this.tempoEspMedio += pcb.getTempoEspera();
            this.tempoExecMedio += pcb.getTempoProcessamento();
        }

        this.tempoEspMedio = this.tempoEspMedio / this.pcbList.size();
        this.tempoExecMedio = this.tempoExecMedio / this.pcbList.size();
    }

    protected abstract void executar();
}
