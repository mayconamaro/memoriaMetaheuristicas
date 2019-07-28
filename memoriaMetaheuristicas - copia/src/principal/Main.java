package principal;

import agendamento.BFInstancia;
import agendamento.BFSolucao;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author maycon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            Trie arvore = new Trie();
            long inicio = System.currentTimeMillis();
            for(int i = 0; i < 1000; i++){
                arvore.insert("012345678"+i, 300);
                arvore.insert("3541298760"+i, 500);
                arvore.insert("0145698203"+i, 800);
            }
            long fim = System.currentTimeMillis();
            System.out.println((fim - inicio)/1000.0);
            System.out.println(arvore.containsNode("0123456789"));
            System.out.println(arvore.containsNode("0348291725"));

            Primes.gerarPrimos().forEach((i) -> {
                System.out.print(i + ", ");
            });
        */
        
        /*
        HashSet<BFSolucao> tabela = new HashSet(2^16);
        BFSolucao sol1 = new BFSolucao();
        sol1.tarefas = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        BFSolucao sol2 = new BFSolucao();
        sol2.tarefas = new ArrayList(Arrays.asList(6, 7, 1, 2, 3, 8, 0, 4, 9, 5));
        
        tabela.add(sol1);
        tabela.add(sol2);
        
        System.out.println(tabela.contains(sol2));
        tabela.forEach((x) -> System.out.println(x));
        System.out.println(tabela.size());
        */
        
        BFInstancia bfi = new BFInstancia();
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File f = jf.getSelectedFile();
        
        bfi.lerDeArquivo(f.getAbsolutePath());
        BFSolucao bfs = new BFSolucao();
        bfs.gerarSolucaoAleatoria(bfi);
        
        System.out.println(bfs.calcularValorDeFuncao(bfi));
        
        metaheuristicas.trie.SA teste = new metaheuristicas.trie.SA();
        teste.rodar(bfs, bfi);
        
        System.out.println(teste.getValorFuncao());
        
    }
    
}
