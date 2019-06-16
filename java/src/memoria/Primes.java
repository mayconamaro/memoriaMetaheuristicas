package memoria;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Paula Toledo
 */
public class Primes {
    
   private static List<Integer> primos = null;
    
    public static List<Integer> gerarPrimos(){
        
        if(primos == null){
            primos = crivoDeEratostenes(20000);
        }
            return primos;
    }
    
    public static List<Integer> crivoDeEratostenes(int n) {
        final boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = false;
            }
        }

        final List<Integer> primes1 = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i])
                primes1.add(i);
        }
        return primes1;
    }
    
}
