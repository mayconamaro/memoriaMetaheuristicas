package principal;

import agendamento.BFInstancia;
import agendamento.BFSolucao;
import caixeiro.TSInstancia;
import caixeiro.TSSolucao;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import memoria.Tuple;

/**
 *
 * @author maycon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // true/false
        boolean USAR_MEMORIA = true;

        //true/false, é preciso usar memoria pra usar esse
        boolean USAR_OPERADOR_POR_BLOCO = false;

        List<Integer> seeds = new ArrayList<>(Arrays.asList(1100111001, 1100111010, 1100111011, 1100111100, 1100111101, 1100111110, 1100111111, 1101000000, 1101000001, 1101000010));
        List<Integer> tamanhos = new ArrayList<>(Arrays.asList(50, 100, 200));

        for (int PROBLEMA = 1; PROBLEMA <= 1; PROBLEMA++) {
            for (int ESTRUTURA_MEMORIA = 1; ESTRUTURA_MEMORIA <= 1; ESTRUTURA_MEMORIA++) {
                for (int METAHEURISTICA = 1; METAHEURISTICA <= 1; METAHEURISTICA++) {
                    for (int tamanho : tamanhos) {
                        for (int i = 1; i <= 3; i++) {
                            float media_fo = 0;
                            float melhor_fo = Float.MAX_VALUE;
                            float pior_fo = 0;
                            double media_tempo = 0;
                            double melhor_tempo = 0;
                            double pior_tempo = 0;
                            float media_revisitacao = 0;
                            float melhor_revisitacao = 0;
                            float pior_revisitacao = 0;
                            float media_iteracao = 0;
                            float melhor_iteracao = 0;
                            float pior_iteracao = 0;
                            boolean sol_incial = false;
                            for (long seed : seeds) {

                                if (PROBLEMA == 1) {
                                    BFInstancia bfi = new BFInstancia();
                                    bfi.lerDeArquivo("C:\\Users\\mayco\\Documents\\NetBeansProjects\\instancias\\agendamento\\sch" + tamanho + "k" + i + ".txt");

                                    BFSolucao bfs = new BFSolucao();
                                    bfs.gerarSolucaoAleatoria(bfi);
                                    if (!sol_incial) {
                                        System.out.println("Valor da solucao inicial: " + bfs.calcularValorDeFuncao(bfi));
                                        sol_incial = true;
                                    }
                                    if (ESTRUTURA_MEMORIA == 1) {
                                        if (METAHEURISTICA == 1) {
                                            metaheuristicas.hash.GVNS teste = new metaheuristicas.hash.GVNS();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();
                                            double tempo = (fim - inicio) / 1000.0;

                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            //teste.historico.forEach((aux) -> {
                                            //System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            //});*/
                                        } else {
                                            metaheuristicas.hash.SA teste = new metaheuristicas.hash.SA();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();
                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        }
                                    } else {
                                        if (METAHEURISTICA == 1) {
                                            metaheuristicas.trie.GVNS teste = new metaheuristicas.trie.GVNS();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();

                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        } else {
                                            metaheuristicas.trie.SA teste = new metaheuristicas.trie.SA();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();

                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        }
                                    }
                                } else {
                                    TSInstancia tsi = new TSInstancia();
                                    tsi.lerDeArquivo("C:\\Users\\mayco\\Documents\\NetBeansProjects\\instancias\\agendamento\\cv" + tamanho + "k" + i + ".txt");

                                    TSSolucao tss = new TSSolucao();
                                    tss.gerarSolucaoAleatoria(tsi);
                                    if (!sol_incial) {
                                        System.out.println("Valor da solucao inicial: " + tss.calcularValorDeFuncao(tsi));
                                        sol_incial = true;
                                    }
                                    if (ESTRUTURA_MEMORIA == 1) {
                                        if (METAHEURISTICA == 1) {
                                            metaheuristicas.hash.GVNS teste = new metaheuristicas.hash.GVNS();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();

                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            System.out.println("--Historico--");
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        } else {
                                            metaheuristicas.hash.SA teste = new metaheuristicas.hash.SA();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();

                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            System.out.println("--Historico--");
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        }
                                    } else {
                                        if (METAHEURISTICA == 1) {
                                            metaheuristicas.trie.GVNS teste = new metaheuristicas.trie.GVNS();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();

                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            System.out.println("--Historico--");
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        } else {
                                            metaheuristicas.trie.SA teste = new metaheuristicas.trie.SA();

                                            long inicio = System.currentTimeMillis();
                                            teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA, seed);
                                            long fim = System.currentTimeMillis();

                                            double tempo = (fim - inicio) / 1000.0;
                                            media_fo += teste.getValorFuncao();
                                            media_tempo += tempo;
                                            media_revisitacao += teste.getRevisitacoes();
                                            media_iteracao += teste.getIteracoes();

                                            if (teste.getValorFuncao() < melhor_fo) {
                                                melhor_fo = teste.getValorFuncao();
                                                melhor_tempo = tempo;
                                                melhor_revisitacao = teste.getRevisitacoes();
                                                melhor_iteracao = teste.getIteracoes();
                                            }

                                            if (teste.getValorFuncao() > pior_fo) {
                                                pior_fo = teste.getValorFuncao();
                                                pior_tempo = tempo;
                                                pior_revisitacao = teste.getRevisitacoes();
                                                pior_iteracao = teste.getIteracoes();
                                            }

                                            /*System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                                            System.out.println("Iteracoes: " + teste.getIteracoes());
                                            System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                                            System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                                            System.out.println("--Historico--");
                                            teste.historico.forEach((aux) -> {
                                                System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                                            });*/
                                        }
                                    }
                                }
                            }

                            media_fo = media_fo / seeds.size();
                            media_tempo = media_tempo / seeds.size();
                            media_iteracao = media_iteracao / seeds.size();
                            media_revisitacao = media_revisitacao / seeds.size();

                            System.out.println("PROBLEMA: " + PROBLEMA);
                            System.out.println("ESTRUTURA: " + ESTRUTURA_MEMORIA);
                            System.out.println("METAHEURISTICA: " + METAHEURISTICA);
                            System.out.println("USANDO MEMORIA? " + USAR_MEMORIA);
                            System.out.println("Instancia: " + tamanho + " ( " + i + ")");
                            System.out.println("media fo: " + media_fo);
                            System.out.println("media tempo: " + media_tempo);
                            System.out.println("media revisitacao: " + media_revisitacao);
                            System.out.println("media iteracao: " + media_iteracao);
                            System.out.println("melhor fo: " + melhor_fo);
                            System.out.println("melhor tempo: " + melhor_tempo);
                            System.out.println("melhr revisitacao: " + melhor_revisitacao);
                            System.out.println("melhor iteracao: " + melhor_iteracao);
                            System.out.println("pior fo: " + pior_fo);
                            System.out.println("pior tempo: " + pior_tempo);
                            System.out.println("pior revisitacao: " + pior_revisitacao);
                            System.out.println("pior iteracao: " + pior_iteracao + "\n\n");

                        }
                    }
                }
            }
        }

        /*
        
        // true/false
        boolean USAR_MEMORIA = false;
        
        //true/false, é preciso usar memoria pra usar esse
        boolean USAR_OPERADOR_POR_BLOCO = false;
        
        // 1 para GVNS, 2 para Simulated Annealing
        int METAHEURISTICA = 1;
        
        // 1 para tabela hash, 2 para arvore de prefixos
        int ESTRUTURA_MEMORIA = 1;
        
        // 1 para scheduling, 2 para caixeiro viajante
        int PROBLEMA = 1;
        
        
        /************* procedimento de teste 
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File f = jf.getSelectedFile();
        
        
        
        if(PROBLEMA == 1){
            BFInstancia bfi = new BFInstancia();
            bfi.lerDeArquivo(f.getAbsolutePath());
            
            BFSolucao bfs = new BFSolucao();
            bfs.gerarSolucaoAleatoria(bfi);
            
            if(ESTRUTURA_MEMORIA == 1){
                if(METAHEURISTICA == 1){
                    metaheuristicas.hash.GVNS teste = new metaheuristicas.hash.GVNS();
                    System.out.println("Valor da solucao inicial: " + bfs.calcularValorDeFuncao(bfi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                     //teste.historico.forEach((aux) -> {
                        //System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    //});
                }else{
                    metaheuristicas.hash.SA teste = new metaheuristicas.hash.SA();
                    System.out.println("Valor da solucao inicial: " + bfs.calcularValorDeFuncao(bfi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                     teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }
            }else{
                if(METAHEURISTICA == 1){
                    metaheuristicas.trie.GVNS teste = new metaheuristicas.trie.GVNS();
                    System.out.println("Valor da solucao inicial: " + bfs.calcularValorDeFuncao(bfi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                     teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }else{
                    metaheuristicas.trie.SA teste = new metaheuristicas.trie.SA();
                    System.out.println("Valor da solucao inicial: " + bfs.calcularValorDeFuncao(bfi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(bfs, bfi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                     teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }
            }
        }else{
            TSInstancia tsi = new TSInstancia();
            tsi.lerDeArquivo(f.getAbsolutePath());
            
            TSSolucao tss = new TSSolucao();
            tss.gerarSolucaoAleatoria(tsi);
            
            if(ESTRUTURA_MEMORIA == 1){
                if(METAHEURISTICA == 1){
                    metaheuristicas.hash.GVNS teste = new metaheuristicas.hash.GVNS();
                    System.out.println("Valor da solucao inicial: " + tss.calcularValorDeFuncao(tsi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                    System.out.println("--Historico--");
                    teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }else{
                    metaheuristicas.hash.SA teste = new metaheuristicas.hash.SA();
                    System.out.println("Valor da solucao inicial: " + tss.calcularValorDeFuncao(tsi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                    System.out.println("--Historico--");
                    teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }
            }else{
                if(METAHEURISTICA == 1){
                    metaheuristicas.trie.GVNS teste = new metaheuristicas.trie.GVNS();
                    System.out.println("Valor da solucao inicial: " + tss.calcularValorDeFuncao(tsi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                    System.out.println("--Historico--");
                    teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }else{
                    metaheuristicas.trie.SA teste = new metaheuristicas.trie.SA();
                    System.out.println("Valor da solucao inicial: " + tss.calcularValorDeFuncao(tsi));
                    long inicio = System.currentTimeMillis();
                    teste.rodar(tss, tsi, USAR_OPERADOR_POR_BLOCO, USAR_MEMORIA);
                    long fim = System.currentTimeMillis();
                    System.out.println("Valor da solucao final: " + teste.getValorFuncao());
                    System.out.println("Iteracoes: " + teste.getIteracoes());
                    System.out.println("Revisitacoes evitadas: " + teste.getRevisitacoes());
                    System.out.println("Tempo gasto (segundos): " + String.valueOf((fim - inicio) / 1000.0));
                    System.out.println("--Historico--");
                    teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
                }
            }
        }
         */
    }

}
