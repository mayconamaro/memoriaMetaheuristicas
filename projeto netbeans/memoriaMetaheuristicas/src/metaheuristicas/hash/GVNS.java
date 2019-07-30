package metaheuristicas.hash;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import memoria.Tuple;

/**
 * Implementação do Variable Neighbourhood Search usando como busca local o
 * Variable Neighbourhood Descent. Também conhecido como General VNS. Utiliza a
 * tabela Hash como estrutura de memória.
 *
 * @author Maycon Amaro e Paula Toledo
 */
public class GVNS {

    private static final int ITER_MAX = 30;
    private static final int K_MAX = 2;
    private static final long SEED = 1100111001;
    private HashMap<Solucao, Float> memoria;
    private int revisitacoes;
    private float valorFuncao;
    private Solucao resposta;
    private int iteracoestotal;
    public List<Tuple<Integer, Float>> historico;

    public void rodar(Solucao s, Instancia i, boolean usarOperadorBloco, boolean usarMemoria) {

        historico = new ArrayList<>();
        
        // inicializando revisitacoes
        revisitacoes = 0;

        //inicializando contador de iteracoes
        iteracoestotal = 0;

        // Gerador de numeros aleatorios
        Random rand = new Random(SEED);

        if (usarMemoria) {
            // Arquivo de solucoes
            memoria = new HashMap(2 ^ 16);
        }
        int iteracao = 0;
        int k;
        Solucao s1 = s.retornarCopia();
        float fstar = s1.calcularValorDeFuncao(i);

        if (usarMemoria) {
            memoria.put(s1, fstar);
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
                    // se a solucao esta no arquivo, nao é necessario revisitar
                    if (memoria.containsKey(s)) {
                        fo = memoria.get(s);
                        revisitacoes++;

                        //firstImprovement
                        s = firstImprovement(fo, s, i, rand);
                        fo = s.calcularValorDeFuncao(i);

                        if (usarOperadorBloco) { // OPERADOR PAULA

                            Solucao s2 = s.retornarCopia();
                            s2.perturbar(3, rand);
                            float f2;
                            if (memoria.containsKey(s2)) {
                                f2 = memoria.get(s2);
                            } else {
                                f2 = s2.calcularValorDeFuncao(i);
                                memoria.put(s2, f2);
                            }

                            if (f2 < fo) {
                                s = s2.retornarCopia();
                                fo = f2;
                            }
                        }

                    } else {
                        // uma solucao visitada é guardada no arquivo
                        fo = s.calcularValorDeFuncao(i);
                        memoria.put(s, fo);
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

            historico.add(new Tuple<>(iteracoestotal, fstar));
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
                if (memoria.containsKey(s)) {
                    fo = memoria.get(s);
                    revisitacoes++;
                } else {
                    fo = s.calcularValorDeFuncao(i);
                    memoria.put(s, fo);
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

            if (memoria.containsKey(s1)) {
                fo_novo = memoria.get(s1);
            } else {
                fo_novo = s1.calcularValorDeFuncao(i);
                memoria.put(s1, fo_novo);
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
