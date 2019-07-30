package principal;

import agendamento.BFInstancia;
import agendamento.BFSolucao;
import caixeiro.TSInstancia;
import caixeiro.TSSolucao;
import java.io.File;
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
        boolean USAR_OPERADOR_POR_BLOCO = true;
        
        // 1 para GVNS, 2 para Simulated Annealing
        int METAHEURISTICA = 1;
        
        // 1 para tabela hash, 2 para arvore de prefixos
        int ESTRUTURA_MEMORIA = 1;
        
        // 1 para scheduling, 2 para caixeiro viajante
        int PROBLEMA = 1;
        
        
        /************* procedimento de teste ********/
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
                     teste.historico.forEach((aux) -> {
                        System.out.println("Iteracao: " + aux.fst() + " melhor solucao: " + aux.snd());
                    });
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
    }
    
}
