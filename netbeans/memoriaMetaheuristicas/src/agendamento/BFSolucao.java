package agendamento;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.ArrayList;
import java.util.List;
import memoria.Primes;

/**
 * Classe para representar a solucao do problema de
 * Sequenciamento de tarefas em uma unica maquina com data de entrega comum
 * e diferentes penalidades de atraso e adiantamento
 * 
 * Como proposto por Biskup e Feldmann
 * 
 * @author Maycon Amaro e Paula Toledo
 */

public class BFSolucao implements Solucao{

    /* Atributos envolvidos na representação da solucao */
    public List<Integer> tarefas;
    
    /* Atributos envolvidos para auxiliar o calculo da funcao objetivo */
    List<Integer> custosAtraso;         // beta
    List<Integer> custosAdiantamento;   // alpha
    List<Integer> atrasos;              // T
    List<Integer> adiantamentos;        // E
    int c;      // acumulador de soma de tempos de processamento
    int tarefa; // registro temporario para tarefa atual
    float soma; // acumulador de soma de custos
    BFInstancia bfins; // registro de instancia
    
    /**
     * Calcula o valor de função da solução considerando uma instância
     * @param ins Instancia do problema a se considerar
     * @return resultado da função objetivo
     */
    @Override
    public float calcularValorDeFuncao(Instancia ins) {
        
        if(bfins == null){
            bfins = (BFInstancia) ins;
        }
        
        // Garantindo que há uma instância valida
        assert bfins != null;
        
        if(custosAtraso == null){
            custosAtraso = new ArrayList(bfins.numeroTarefas);
        }
        
        if(custosAdiantamento == null){
            custosAdiantamento = new ArrayList(bfins.numeroTarefas);
        }
        
        if(atrasos == null){
            atrasos = new ArrayList(bfins.numeroTarefas);
        }
        
        if(adiantamentos == null){
            adiantamentos = new ArrayList(bfins.numeroTarefas);
        }
        
        c = 0;
        
        for(int i = 0; i < tarefas.size(); i++){
            
            tarefa = tarefas.get(i);
            
            c += bfins.temposProcessamento.get(tarefa);
            custosAdiantamento.set(i, bfins.custosAdiantamento.get(tarefa));
            custosAtraso.set(i, bfins.custosAtraso.get(tarefa));
            atrasos.set(i, Math.max(bfins.dataEntrega - c, 0));
            adiantamentos.set(i, Math.max(c - bfins.dataEntrega, 0));
        }
        
        soma = 0;
        
        for(int i = 0; i < tarefas.size(); i++){
            
            soma += adiantamentos.get(i) * custosAdiantamento.get(i) + atrasos.get(i) * custosAtraso.get(i);
        }
        
        // Garantindo que a função objetivo foi calculada (não garante que o valor está certo)
        assert soma != 0;
        
        return soma;
    }

    /**
     * Criador de cópia
     * @return uma cópia desse objeto
     */
    @Override
    public Solucao retornarCopia() {
        BFSolucao s = new BFSolucao();
        s.tarefas = new ArrayList(this.tarefas);
        s.bfins = this.bfins;
        return s;
    }

    @Override
    public Solucao gerarSolucaoAleatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        BFSolucao bf = (BFSolucao) obj;
        
        for(int i = 0; i < this.tarefas.size(); i++){
            if(!bf.tarefas.get(i).equals(this.tarefas.get(i))){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        
        int sum = 0;
        int i = 0;
        List<Integer> primos = Primes.gerarPrimos();
        
        for(int j : this.tarefas){
            sum += j * primos.get(i++);
        }
        
        return sum;
    }
    
    
}
