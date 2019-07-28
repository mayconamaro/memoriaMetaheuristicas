package corte;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.Random;

/**
 * Classe para representar a solucao do problema de corte guilhotinado com dimensao aberta
 * 
 * @author Maycon Amaro e Paula Toledo
 */

public class CGSolucao implements Solucao {

    @Override
    public float calcularValorDeFuncao(Instancia i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Solucao retornarCopia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void perturbar(int movimento, Random rand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gerarSolucaoAleatoria(Instancia i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
