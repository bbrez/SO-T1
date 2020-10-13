/**
 * PCB: classe que representa um Bloco de Controle de Processo (Process Control Block do ingles),
 * e contém as informações essenciais dos processos, como seu ID,
 * e o tempo que passou em cada fase do processo de escalonamento.
 * Todos os tempos relacionados são medidos em milissegundos
 */
public class PCB implements Comparable<PCB> {
    private final int pid; //identificador do processo
    private final long tempoChegada; //quando o processo chegou
    private final long tempoProcessamento; //tempo total que o processo tem que executar para acabar
    private long tempoRestante; //tempo restante para o processo acabar
    private long tempoExecutado; //tempo total que o processo ja executou
    private long tempoEspera; //tempo total que o processo ja esperou
    private long tempoComeco; //tempo em que começou a executar
    private long tempoFim; //tempo em que terminou de executar

    //Construtor de processos
    //Entrada: ID, Tempo de Chegada e o Tempo de processamento do novo processo
    //Saida: Nenhuma
    //Pre-condicao: Nenhuma
    //Pos-condicao: Processo criado
    public PCB(int pid, long tempoChegada, long tempoProcessamento){
        this.pid = pid;
        this.tempoChegada = tempoChegada;
        this.tempoProcessamento = tempoProcessamento;
        this.tempoRestante = tempoProcessamento;
        this.tempoExecutado = 0;
    }

    //Getters e Setters

    public int getPid() { return pid; }

    public long getTempoChegada() { return tempoChegada; }

    public long getTempoProcessamento() { return tempoProcessamento; }

    public long getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(long tempoRestante) {
        this.tempoRestante = tempoRestante;
    }


    public long getTempoExecutado() {
        return tempoExecutado;
    }

    public void setTempoExecutado(long tempoExecutado) {
        this.tempoExecutado = tempoExecutado;
    }

    public long getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(long tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public long getTempoComeco() {
        return tempoComeco;
    }

    public void setTempoComeco(long tempoComeco) {
        this.tempoComeco = tempoComeco;
    }

    public long getTempoFim() {
        return tempoFim;
    }

    public void setTempoFim(long tempoFim) {
        this.tempoFim = tempoFim;
    }

    //Compara qual processo tem o menor tempo de chegada
    //Entrada: Processo que sera comparado
    //Saida: -1 se o processo passado por parametro for maior, 0 se eles tem o mesmo tempo de chegada e 1 se se o
    //processo passado por parametro for menor
    //Pre-condicao: Processos validos
    //Pos-condicao: Comparacao realizada
    @Override
    public int compareTo(PCB pcb) {
        if(this.tempoChegada < pcb.getTempoChegada()) return -1;
        if(this.tempoChegada > pcb.getTempoChegada()) return 1;
        else return 0;
    }

}
