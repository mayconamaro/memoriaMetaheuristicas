package principal;

import agendamento.BFInstancia;
import java.util.Scanner;
import memoria.Primes;
import memoria.Trie;
/**
 *
 * @author maycon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        
        Scanner sc = new Scanner(System.in);
        new BFInstancia().lerDeArquivo(sc.nextLine());
        sc.close();
    }
    
}
