package agendamento;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
            custosAtraso = new ArrayList();
            for(int i = 0; i < bfins.numeroTarefas; i++){
                custosAtraso.add(-1);
            }
        }
        
        if(custosAdiantamento == null){
            custosAdiantamento = new ArrayList();
            for(int i = 0; i < bfins.numeroTarefas; i++){
                custosAdiantamento.add(-1);
            }
        }
        
        if(atrasos == null){
            atrasos = new ArrayList(bfins.numeroTarefas);
            for(int i = 0; i < bfins.numeroTarefas; i++){
                atrasos.add(-1);
            }
        }
        
        if(adiantamentos == null){
            adiantamentos = new ArrayList(bfins.numeroTarefas);
            for(int i = 0; i < bfins.numeroTarefas; i++){
                adiantamentos.add(-1);
            }
        }
        
        c = 0;
        
        for(int i = 0; i < tarefas.size(); i++){
            
            tarefa = tarefas.get(i);
            
            c += bfins.temposProcessamento.get(tarefa);
            
            custosAdiantamento.set(i, bfins.custosAdiantamento.get(tarefa));
            custosAtraso.set(i, bfins.custosAtraso.get(tarefa));
            atrasos.set(i, Math.max(c - bfins.dataEntrega, 0));
            adiantamentos.set(i, Math.max(bfins.dataEntrega - c, 0));
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
    public void gerarSolucaoAleatoria(Instancia i) {
        
        BFInstancia bf = (BFInstancia) i;
        List<Integer> tar = new ArrayList<>();
        
        for(int j = 0; j < bf.numeroTarefas; j++){
            
            tar.add(j);
        }
        
        Collections.shuffle(tar, new Random(100));
        tarefas = tar;
        bfins = bf;
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

    @Override
    public void perturbar(int movimento, Random rand) {
        int i,j;
        switch(movimento){
            
            case 1:
                i = rand.nextInt(tarefas.size());
                j = (i == tarefas.size()-1 ? 0 : i+1);
                
                int aux = tarefas.get(i);
                tarefas.set(i, tarefas.get(j));
                tarefas.set(j, aux);
                break;
            case 2:
                i = rand.nextInt(tarefas.size());
                j = i;
                while (i == j)
                    j = rand.nextInt(tarefas.size());
                
                int aux1 = tarefas.get(i);
                tarefas.set(i, tarefas.get(j));
                tarefas.set(j, aux1);
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        
        String resp = "";
        
        resp = tarefas.stream().map((pos) -> String.valueOf(pos)).reduce(resp, String::concat);
        
        return resp;
    }
    
}
