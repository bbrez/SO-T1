import java.util.LinkedList;

/**
 * RR: classe que contem a estrutura do algoritmo Round Robin,
 * onde cada processo sera processado por quantidades iguais de tempo,
 * sem prioridade entre os processos
 */
public class RR extends Escalonador {
    private final long quantum;

    RR(LinkedList<PCB> pcbs, long quantum, String arquivoSaida) {
        super(pcbs, arquivoSaida);
        this.quantum = quantum;
    }

    //Verifica se o processo terminou
    //Entrada: Nenhuma
    //Saida: True se o processo tiver encerrado, False se ainda tiver tempo restante nele
    //Pre-condicao: Lista de processos nao vazia
    //Pos-condicao: Verificacao realizada
    private boolean terminou() {
        for (PCB pcb : this.pcbList) {
            if (pcb.getTempoRestante() > 0) return false;
        }
        return true;
    }

    //Imprime a troca que vai acontecer
    //Entrada: Processo que ira sair, processo que ira entrar e o tempo que passou desde o inicio do algoritmo
    //Saida: Nenhuma
    //Pre-condicao: Nenhuma
    //Pos-condicao: Troca impressa
    private void trocaProcesso(PCB saindo, PCB entrando, long tempo) {
        System.out.println("Tempo: " + tempo + "ms");
        System.out.println("Processo pid:" + saindo.getPid() + " saindo\n");
        System.out.println("---------------------------\n");
        System.out.println("Processo pid:" + entrando.getPid() + " entrando");
    }

    //Imprime um processo
    //Entrada: Processo que sera impresso
    //Saida: Nenhuma
    //Pre-condicao: Processo valido
    //Pos-condicao: Processo impresso
    private void printProcesso(PCB processo){
        System.out.println("Processo " + processo.getPid() + ":(" +
                processo.getTempoChegada() + ", " +
                processo.getTempoProcessamento() + ", " +
                processo.getTempoRestante() + ", " +
                processo.getTempoEspera() + ")");
    }

    //Executa o algoritmo, realizando o escalonamento
    //Entrada: Nenhuma
    //Saida: Nenhuma
    //Pre-condicao: Arquivo valido, lista de processos nao vazia e quantum condigurado
    //Pos-condicao: Escalonamento realizado
    @Override
    protected void executar() {
        this.setupSaida();

        System.out.println("Executando escalonamento com algoritmo Round Robin (RR):");
        System.out.println("Formato: Processo [pid]:([tempoChegada], [tempoProcessamento], [tempoRestante], [tempoEspera]");

        PCB anterior = this.pcbList.getFirst();
        long tempoAtual = this.pcbList.getFirst().getTempoChegada();

        while (!terminou()) {

            System.out.println("Tempo Atual: " + tempoAtual + "ms");

            for (PCB pcb : this.pcbList) {
                if (pcb.getTempoChegada() <= tempoAtual && pcb.getTempoRestante() > 0) {
                    if (pcb != anterior) {
                        trocaProcesso(anterior, pcb, tempoAtual);
                    }

                    anterior = pcb;
                    System.out.println("Processo atual:");
                    printProcesso(pcb);

                    System.out.println("Processos prontos:");
                    for (PCB pcb_aux : this.pcbList) {
                        if (pcb != pcb_aux && pcb_aux.getTempoRestante() > 0) {
                            long aux;
                            printProcesso(pcb_aux);
                            if (pcb.getTempoRestante() >= this.quantum) {
                                pcb_aux.setTempoEspera(pcb_aux.getTempoEspera() + this.quantum);
                            } else {
                                pcb_aux.setTempoEspera(pcb_aux.getTempoEspera() + pcb.getTempoRestante());
                            }
                        }
                    }

                    if (pcb.getTempoRestante() < this.quantum) {
                        System.out.println("Processo vai executar por " + pcb.getTempoRestante() + "ms");
                        tempoAtual += pcb.getTempoRestante();
                        pcb.setTempoExecutado(pcb.getTempoExecutado() + pcb.getTempoRestante());
                        pcb.setTempoRestante(0);
                        System.out.println("Processo " + pcb.getPid() + " finalizado");
                    } else {
                        System.out.println("Processo vai executar por " + this.quantum + "ms");
                        tempoAtual += this.quantum;
                        pcb.setTempoExecutado(pcb.getTempoExecutado() + this.quantum);
                        pcb.setTempoRestante(pcb.getTempoRestante() - this.quantum);
                    }

                } else {

                    for(PCB pcb_aux: pcbList){
                        if(pcb_aux.getTempoRestante() > 0){
                            break;
                        } else {
                            tempoAtual = pcb.getTempoChegada();
                            pcb.setTempoEspera(pcb.getTempoChegada() - pcb_aux.getTempoChegada());
                        }
                    }

                }
            }
        }

        System.out.println("Fim dos Processos\n");
        System.out.println("---------------------------\n");

        this.calculaMedias();

        System.out.println("Tempo de execução médio: " + this.tempoExecMedio);
        System.out.println("Tempo de espera médio: " + this.tempoEspMedio);
    }
}
