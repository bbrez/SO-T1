import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * FCFS: classe que contém a estrutura do algoritmo FCFS (do ingles First Come, First Served),
 * onde o primeiro processo a ser inserido sera executado ate o final,
 * sendo assim feito sucessivamente por ordem de inserçao, portanto sendo nao preemptivo
 */
public class FCFS extends Escalonador {
    private String outputFile;

    FCFS(LinkedList<PCB> pcbs, String outputFile) {
        super(pcbs);
        this.outputFile = outputFile;
    }

    //Calcula o tempo em que os processos na lista de PCBs atual começaria e terminaria de executar
    private void calcularTempos(){
        this.pcbList.getFirst().setTempoComeco(this.pcbList.getFirst().getTempoChegada());
        this.pcbList.getFirst().setTempoFim(this.pcbList.getFirst().getTempoProcessamento());
        for (int i = 1; i < this.numProcessos; ++i){
            PCB atual = this.pcbList.get(i);
            if(atual.getTempoChegada() <= this.pcbList.get(i-1).getTempoFim()){
                atual.setTempoComeco(this.pcbList.get(i-1).getTempoFim());
            } else {
                atual.setTempoComeco(atual.getTempoChegada());
            }
            atual.setTempoFim(atual.getTempoComeco()+atual.getTempoProcessamento());
        }
        this.tempoTotal = this.pcbList.getLast().getTempoFim();
    }

    //Executa o escalonamento usando o algoritmo FCFS
    public void executar(){
        if(!this.outputFile.isBlank()){
            try {
                System.setOut(new PrintStream(new File(outputFile)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("não foi possivel abrir arquivo de saída");
                System.exit(1);
            }
        }

        System.out.println("Executando escalonamento com algoritmo FCFS:");
        System.out.println("Formato: Processo [pid]:([tempoChegada], [tempoProcessamento])");

        this.calcularTempos();

        LinkedList<PCB> pcbListCopy = new LinkedList<>(pcbList);
        pcbListCopy.remove();
        long tempoAtual =  0;

        for (PCB pcb: pcbList) {
            tempoAtual = pcb.getTempoComeco();
            pcb.setTempoExecutado(pcb.getTempoProcessamento());
            pcb.setTempoEspera(pcb.getTempoComeco() - pcb.getTempoChegada());
            pcb.setTempoRestante(0);

            System.out.println("Tempo Atual: "+ tempoAtual + "ms");
            System.out.println("Processo sendo executado: "+pcb.getPid());
            System.out.println("Processo "+pcb.getPid() + " esperou " + pcb.getTempoEspera() + "ms para começar");

            System.out.println("Processos prontos:");
            for(PCB pcb_aux: pcbListCopy){
                if(pcb_aux.getTempoChegada() < tempoAtual){
                    System.out.println("Processo " + pcb_aux.getPid() + ":("+pcb_aux.getTempoChegada()+", "+
                            pcb_aux.getTempoProcessamento()+ ")");
                }
            }

            System.out.println("Processo " + pcb.getPid() + " concluido\n");
            System.out.println("---------------------------");
            if(!pcbListCopy.isEmpty()) pcbListCopy.remove();
        }

        System.out.println("Fim dos Processos");
        this.calculaMedias();
    }

}

//Por Bruno de Castro Brezolin e Leonardo Benitez