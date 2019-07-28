package metaheuristicas.trie;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.Random;
import memoria.Trie;

/**
 * Implementação do Variable Neighbourhood Search usando como busca local o
 * Variable Neighbourhood Descent. Também conhecido como General VNS. Utiliza a
 * Arvore de prefixos como estrutura de memória.
 *
 * @author Maycon Amaro e Paula Toledo
 */
public class GVNS {

    private static final int ITER_MAX = 30;
    private static final int K_MAX = 2;
    private static final long SEED = 1100111001;
    private Trie memoria;
    private int revisitacoes;
    private float valorFuncao;
    private Solucao resposta;

    public void rodar(Solucao s, Instancia i) {

        // inicializando revisitacoes
        revisitacoes = 0;
        
        // Gerador de numeros aleatorios
        Random rand = new Random(SEED);
        
        // Arquivo de solucoes
        memoria = new Trie();

        int iteracao = 0;
        int k;
        Solucao s1 = s.retornarCopia();
        float fstar = s1.calcularValorDeFuncao(i);
        memoria.insert(s1.toString(), fstar);

        while (iteracao < ITER_MAX) {
            
            k = 1; 
            
            while (k <= K_MAX) {
                
                // movimento com vizinhança k
                s.perturbar(k, rand);
                
                // busca local
                vnd(s, i, rand);

                float fo = memoria.containsNode(s.toString());
                
                // se nao esta na memoria, colocar
                if(fo == -1){
                    fo = s.calcularValorDeFuncao(i);
                    memoria.insert(s.toString(), fo);
                }else{
                    // ja estava na memoria
                    revisitacoes++;
                }

                if (fo < fstar) {

                    fstar = fo;
                    s1 = s.retornarCopia();
                    iteracao = 0;
                    k = 1;
                } else {

                    s = s1.retornarCopia();
                    k++;
                    iteracao++;
                }
            }
        }
        
        valorFuncao = fstar;
        resposta = s1;
    }

    public void vnd(Solucao s, Instancia i, Random rand) {

        int k = 1;
        Solucao s1 = s.retornarCopia();
        float fstar = s1.calcularValorDeFuncao(i);

        while (k <= K_MAX) {

            s.perturbar(k, rand);
            
            float fo = memoria.containsNode(s.toString());
          
            if(fo == -1){
                fo = s.calcularValorDeFuncao(i);
                memoria.insert(s.toString(), fo);
            }else{
                revisitacoes++;
            }
            
            if (fo < fstar) {

                fstar = fo;
                s1 = s.retornarCopia();
                k = 1;
            } else {

                s = s1.retornarCopia();
                k++;
            }
        }
    }
    
    public int getRevisitacoes(){
        return this.revisitacoes;
    }
    
    public float getValorFuncao(){
        return this.valorFuncao;
    }
    
    public Solucao getSolucao(){
        return this.resposta;
    }
}
