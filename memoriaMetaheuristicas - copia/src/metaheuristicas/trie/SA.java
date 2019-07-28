/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheuristicas.trie;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.Random;
import memoria.Trie;

/**
 * Metaheurística Simulated Annealing utilizando Tabela Hash como memória
 * @author Maycon Amaro e Paula Toledo
 */
public class SA {

    private static final long SEED = 1100111001;
    private int revisitacoes;
    private float valorFuncao;
    private Solucao resposta;
    private Trie memoria;

    public void rodar(Solucao s, Instancia i) {

        memoria = new Trie();

        Solucao sstar = s.retornarCopia();
        float fstar = s.calcularValorDeFuncao(i);

        memoria.insert(s.toString(), fstar);

        Solucao s1;
        double taxaResfriamento = 0.003;
        revisitacoes = 0;

        Random rand = new Random(SEED);
        float temperatura = 100000;

        // enquanto o sistema nao esfriar
        while (temperatura > 1) {

             // se esta na memoria nao é necessario revisitar
            float f1 = memoria.containsNode(s.toString());
            
            // se nao esta, colocar
            if(f1 == -1){
                f1 = s.calcularValorDeFuncao(i);
                memoria.insert(s.toString(), f1);
            }else{
                revisitacoes++;
            }
           
            s1 = s.retornarCopia();

            // vizinho aleatorio
            s1.perturbar(2, rand);

            float f2 = memoria.containsNode(s1.toString());
            
            if(f2 == -1){
                f2 = s1.calcularValorDeFuncao(i);
                memoria.insert(s1.toString(), f2);
            }else{
                revisitacoes++;
            }

            // aleatoriamente aceita o vizinho
            if (probabiblidadeAceitacao(f1, f2, temperatura) > rand.nextFloat()) {

                s = s1.retornarCopia();
                f1 = f2;
            }

            // sempre guardando a melhor solucao
            if (f1 < fstar) {

                fstar = f1;
                sstar = s.retornarCopia();
            }

            // esfriar :)
            temperatura *= (1 - taxaResfriamento);
        }

        resposta = sstar.retornarCopia();
        valorFuncao = fstar;
    }

    public double probabiblidadeAceitacao(float energiaAntes, float energiaDepois, double temperatura) {

        // sempre aceita se a solucao melhora 
        if (energiaAntes < energiaDepois) {
            return 1.0;
        }

        // se nao melhora, probabilisticamente define se aceita ou nao
        return Math.exp((energiaAntes - energiaDepois) / temperatura);
    }

    public int getRevisitacoes() {
        return revisitacoes;
    }

    public float getValorFuncao() {
        return valorFuncao;
    }

    public Solucao getResposta() {
        return resposta;
    }
}
