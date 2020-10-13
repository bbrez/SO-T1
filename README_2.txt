Informações Básicas:
O Projeto está na pasta simulador
Foi escolhida a linguagem Java devido a facilidade de programação
Os algoritmos escolhidos foram First Come, First Serve e Round Robin

Montagem do arquivo de configuração:
* Criar um arquivo de texto
* Na primeira linha colocar a sigla do algoritmo a ser utilizado
    * fcfs para First Come, First Serve
    * rr para round robin
* No caso de round robin, a segunda linha deve ser o tamanho do quantum (recomendação: 4)
* Depois disso, todas as linhas seguintes são os processos a serem escalonados, e devem seguir exatamente este formato:
x:(y,z)
* Onde:
    * x é o PID, ou identificador do processo.
    * y é o tempo de chegada do processo.
    * z é o tempo que o processo precisa executar.
* Esses tres numeros devem ser inteiros.
* Observações:
    * A ordem dos processos é ignorada
    * O programa so faz o escalonamento com um algoritmo por vez

Compilação do programa:
* Carregar os arquivos do projeto na IDE compativel com Java desejada e compilar normalmente
    * Recomendado IntelliJ IDEA Ultimate ou Community
* Testado com Java entre as versões 13 e 15, porém deve funcionar com quase qualquer versão mais velha

Execução do programa:
* Executar o programa da maneira desejada, passando como argumento o nome do arquivo de configuração
    * Isso pode ser feito normalmente pela interface da IDE

