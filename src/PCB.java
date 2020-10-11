/**
 * PCB: classe que representa um Bloco de Controle de Processo (Process Control Block do ingles),
 * e contém as informações essenciais dos processos, como seu ID,
 * e o tempo que passou em cada fase do processo de escalonamento
 */
public class PCB {
    private final int pid; //identificador do processo
    private final long tempoChegada; //quando o processo chegou
    private final long tempoProcessamento; //tempo total que o processo tem que executar para acabar
    private long tempoRestante; //tempo restante para o processo acabar
    private long tempoExecutado; //tempo que o processo ja executou
    private long tempoEspera; //tempo que o processo esta em espera
    private long tempoComeco; //tempo em que começou a executar
    private long tempoFim; //tempo em que terminou de executar

    public PCB(int pid, long tempoProcessamento, long tempoChegada){
        this.pid = pid;
        this.tempoProcessamento = tempoProcessamento;
        this.tempoChegada = tempoChegada;
    }

    public int getPid() {
        return pid;
    }

    public long getTempoChegada() {
        return tempoChegada;
    }

    public long getTempoProcessamento() {
        return tempoProcessamento;
    }

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
}
