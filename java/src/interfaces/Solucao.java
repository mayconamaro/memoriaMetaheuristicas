package interfaces;

/**
 * Interface para definir os metodos comuns em todas as representacoes
 * de solucao para usar esse código
 * 
 * @author Maycon Amaro e Paula Toledo
 */
public interface Solucao {
    
    public abstract float calcularValorDeFuncao(Instancia i);
    
    public abstract Solucao retornarCopia();
    
    public abstract Solucao gerarSolucaoAleatoria();
}
