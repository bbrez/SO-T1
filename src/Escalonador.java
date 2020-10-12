import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Escalonador {
    protected final LinkedList<PCB> pcbList; //Lista Encadeada que segura os processos
    protected int numProcessos; //Numero de processos
    protected long tempoTotal = 0; //Tempo para executar todos os processos
    protected float tempoExecMedio, tempoEspMedio; //Tempo de execução e de espera médio de todos os processos

    Escalonador(LinkedList<PCB>  pcbs){
        this.pcbList = pcbs; //
        Collections.sort(pcbList);
        this.numProcessos = this.pcbList.size();
    }

    //Retorna a soma de todos os tempos de execução dos processos
    private long tempoExecTotal(){
        long soma = 0;
        for (PCB pcb: this.pcbList) {
            soma += pcb.getTempoProcessamento();
        }
        return soma;
    }

    protected void calculaMedias(){

    }

    protected abstract void executar();
}
