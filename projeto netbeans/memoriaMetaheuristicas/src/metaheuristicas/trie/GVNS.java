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
    private int iteracoestotal;

    public void rodar(Solucao s, Instancia i, boolean usarOperadorBloco, boolean usarMemoria) {

        // inicializando revisitacoes
        revisitacoes = 0;

        //inicializando contador de iteracoes
        iteracoestotal = 0;

        // Gerador de numeros aleatorios
        Random rand = new Random(SEED);

        if (usarMemoria) {
            // Arquivo de solucoes
            memoria = new Trie();
        }

        int iteracao = 0;
        int k;
        Solucao s1 = s.retornarCopia();
        float fstar = s1.calcularValorDeFuncao(i);

        if (usarMemoria) {
            memoria.insert(s1.toString(), fstar);
        }

        while (iteracao < ITER_MAX) {

            k = 1;

            while (k <= K_MAX) {

                // movimento com vizinhança k
                s.perturbar(k, rand);

                // busca local
                vnd(s, i, rand, usarMemoria);

                float fo;

                if (usarMemoria) {
                    fo = memoria.containsNode(s.toString());

                    // se nao esta na memoria, colocar
                    if (fo == -1) {
                        fo = s.calcularValorDeFuncao(i);
                        memoria.insert(s.toString(), fo);
                    } else {
                        // ja estava na memoria
                        revisitacoes++;

                        //first improvement
                        s = firstImprovement(fo, s, i, rand);
                        fo = s.calcularValorDeFuncao(i);

                        if (usarOperadorBloco) { // OPERADOR PAULA

                            Solucao s2 = s.retornarCopia();
                            float f2;
                            s2.perturbar(3, rand);

                            f2 = memoria.containsNode(s2.toString());

                            if (f2 == -1) {
                                f2 = s2.calcularValorDeFuncao(i);
                                memoria.insert(s2.toString(), f2);
                            }

                            if (f2 < fo) {
                                fo = f2;
                                s = s2.retornarCopia();
                            }
                        }
                    }
                } else {
                    fo = s.calcularValorDeFuncao(i);
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

            iteracoestotal++;
        }

        valorFuncao = fstar;
        resposta = s1;
    }

    public void vnd(Solucao s, Instancia i, Random rand, boolean usarMemoria) {

        int k = 1;
        Solucao s1 = s.retornarCopia();
        float fstar = s1.calcularValorDeFuncao(i);

        while (k <= K_MAX) {

            s.perturbar(k, rand);

            float fo;

            if (usarMemoria) {
                fo = memoria.containsNode(s.toString());

                if (fo == -1) {
                    fo = s.calcularValorDeFuncao(i);
                    memoria.insert(s.toString(), fo);
                } else {
                    revisitacoes++;
                }
            } else {
                fo = s.calcularValorDeFuncao(i);
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
        return this.revisitacoes;
    }

    public float getValorFuncao() {
        return this.valorFuncao;
    }

    public Solucao getSolucao() {
        return this.resposta;
    }

    public int getIteracoes() {
        return iteracoestotal;
    }
}
