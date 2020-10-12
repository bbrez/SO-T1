import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que serve como base para os algoritmos de escalonamento
 */

public abstract class Escalonador {
    protected final LinkedList<PCB> pcbList; //Lista Encadeada que segura os processos
    protected int numProcessos; //Numero de processos
    protected long tempoTotal = 0; //Tempo para executar todos os processos
    protected float tempoExecMedio = 0, tempoEspMedio = 0; //Tempo de execução e de espera médio de todos os processos

    private String arquivoSaida;

    //Construtor da classe
    //Entrada: Lista de processos e uma string com o nome do arquivo de saida
    //Saida: Nenhuma
    //Pre-condicao: Lista de processos nao vazia e string valida
    //Pos-condicao: Construcao realizada
    Escalonador(LinkedList<PCB> pcbs, String arquivoSaida) {
        this.pcbList = pcbs; //
        Collections.sort(pcbList);
        this.numProcessos = this.pcbList.size();
        this.arquivoSaida = arquivoSaida;
    }

    //Configura o arquivo como terminal de saida
    //Entrada: Nenhuma
    //Saida: Nenhuma
    //Pre-condicao: Nenhuma
    //Pos-condicao: Configuraçao realizada
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

    //Calcula o tempo de espera
    //Entrada: Nenhuma
    //Saida: Nenhuma
    //Pre-condicao: Lista de processos nao vazia
    //Pos-condicao: Tempo de espera calculado
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
