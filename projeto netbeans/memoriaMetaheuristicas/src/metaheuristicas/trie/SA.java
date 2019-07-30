/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheuristicas.trie;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import memoria.Trie;
import memoria.Tuple;

/**
 * Metaheurística Simulated Annealing utilizando Tabela Hash como memória
 *
 * @author Maycon Amaro e Paula Toledo
 */
public class SA {

    private static final long SEED = 1100111001;
    private int revisitacoes;
    private float valorFuncao;
    private Solucao resposta;
    private Trie memoria;
    private int iteracoes;
    public List<Tuple<Integer, Float>> historico;

    public void rodar(Solucao s, Instancia i, boolean usarOperadorBloco, boolean usarMemoria) {
        historico = new ArrayList<>();
        iteracoes = 0;

        if (usarMemoria) {
            memoria = new Trie();
        }
        boolean esfriar;
        Solucao sstar = s.retornarCopia();
        float fstar = s.calcularValorDeFuncao(i);

        if (usarMemoria) {
            memoria.insert(s.toString(), fstar);
        }

        Solucao s1;
        double taxaResfriamento = 0.003;
        revisitacoes = 0;

        Random rand = new Random(SEED);
        float temperatura = 100000;

        // enquanto o sistema nao esfriar
        while (temperatura > 1) {

            float f1;

            if (usarMemoria) {
                // se esta na memoria nao é necessario revisitar
                f1 = memoria.containsNode(s.toString());

                // se nao esta, colocar
                if (f1 == -1) {
                    f1 = s.calcularValorDeFuncao(i);
                    memoria.insert(s.toString(), f1);
                } else {

                }
            } else {
                f1 = s.calcularValorDeFuncao(i);
            }
            s1 = s.retornarCopia();

            // vizinho aleatorio
            s1.perturbar(2, rand);

            float f2;

            if (usarMemoria) {
                f2 = memoria.containsNode(s1.toString());

                if (f2 == -1) {
                    f2 = s1.calcularValorDeFuncao(i);
                    memoria.insert(s1.toString(), f2);
                } else {
                    revisitacoes++;

                    //first improvement 
                    s1 = firstImprovement(f2, s1, i, rand);
                    f2 = s1.calcularValorDeFuncao(i);

                    if (usarOperadorBloco) { // OPERADOR PAULA
                        Solucao s3 = s.retornarCopia();
                        float f3;
                        s3.perturbar(3, rand);

                        f3 = memoria.containsNode(s3.toString());

                        if (f3 == -1) {
                            f3 = s3.calcularValorDeFuncao(i);
                            memoria.insert(s3.toString(), f3);
                        }

                        if (f3 < f2) {
                            f2 = f3;
                            s1 = s3.retornarCopia();
                        }
                    }
                }
            } else {
                f2 = s1.calcularValorDeFuncao(i);
            }
            // aleatoriamente aceita o vizinho
            esfriar = (f2 >= f1);
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
            if(esfriar)
                temperatura *= (1 - taxaResfriamento);
            historico.add(new Tuple<>(iteracoes, fstar));
            iteracoes++;
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

    public Solucao firstImprovement(float fo, Solucao s, Instancia i, Random rand) {

        Solucao s1 = s.retornarCopia();
        Solucao sstar = s.retornarCopia();
        int cont = 0;
        boolean melhorou = false;
        float fstar = fo;
        float fo_novo;

        while (!melhorou && cont < 30) {

            s1.perturbar(2, rand);

            fo_novo = memoria.containsNode(s1.toString());

            if (fo_novo == -1) {
                fo_novo = s1.calcularValorDeFuncao(i);
                memoria.insert(s1.toString(), fo_novo);
            }

            if (fo_novo < fstar) {
                fstar = fo_novo;
                sstar = s1.retornarCopia();
                melhorou = true;
            } else {
                cont++;
            }
        }

        return sstar;
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

    public int getIteracoes() {
        return iteracoes;
    }

}
