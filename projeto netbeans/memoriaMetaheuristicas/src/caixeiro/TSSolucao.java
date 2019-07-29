package caixeiro;

import interfaces.Instancia;
import interfaces.Solucao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import memoria.Primes;

/**
 *
 * Classe para representar a solucao do problema do caixeiro viajante
 *
 * @author Maycon Amaro e Paula Toledo
 */
public class TSSolucao implements Solucao {

    /* Atributos envolvidos na representação da solucao */
    public List<Integer> cidades;
    float soma; // acumulador de soma de custos
    TSInstancia tsins; // registro de instancia

    @Override
    public float calcularValorDeFuncao(Instancia ins) {
        if (tsins == null) {
            tsins = (TSInstancia) ins;
        }
        // Garantindo que há uma instância valida
        assert tsins != null;
        soma = 0;
        int tam = cidades.size();
        //System.out.println("tamanho " + tam);
        for (int i = 0; i < tam; i++) {
            if (i != (tam - 1)) {
                soma += tsins.distancia.get(cidades.get(i) - 1).get(cidades.get(i + 1) - 1);
            } else {
                soma += tsins.distancia.get(cidades.get(i) - 1).get(cidades.get(0) - 1);
            }
        }

        assert (soma != 0);
        return soma;
    }

    @Override
    public Solucao retornarCopia() {
        TSSolucao s = new TSSolucao();
        s.cidades = new ArrayList(this.cidades);
        s.tsins = this.tsins;
        return s;
    }

    @Override
    public void gerarSolucaoAleatoria(Instancia i) {
         TSInstancia ti = (TSInstancia) i;
        List<Integer> cid = new ArrayList<>();
        
        for(int j = 1; j <= ti.numeroCidades; j++){
            
            cid.add(j);
        }
        
        Collections.shuffle(cid, new Random(100));
        cidades = cid;
        tsins = ti;
    }

    @Override
    public void perturbar(int movimento, Random rand) {
        int i, j;
        
        switch(movimento){
            case 1:
                i = rand.nextInt(cidades.size());
                j = (i == cidades.size()-1 ? 0 : i+1);
                
                int aux = cidades.get(i);
                cidades.set(i, cidades.get(j));
                cidades.set(j, aux);
                break;
            case 2:
                 i = rand.nextInt(cidades.size());
                j = i;
                while (i == j)
                    j = rand.nextInt(cidades.size());
                
                int aux1 = cidades.get(i);
                cidades.set(i, cidades.get(j));
                cidades.set(j, aux1);
                break;
            // BUSCA POR BLOCO - PAULA
            case 3:
                
                int tam_bloco = 5; //divisor de todos os tamanhos
                
                int qtnde_blocos = this.cidades.size() / tam_bloco;
                
                int bloco1 = rand.nextInt(qtnde_blocos);
                int bloco2 = bloco1;
                
                while(bloco1 == bloco2)
                    bloco2 = rand.nextInt(qtnde_blocos);
                
                List<Integer> novas_cidades = new ArrayList<>(cidades);
                
                int inicioBloco1 = 5 * bloco1;
                int inicioBloco2 = 5 * bloco2;
                
                for(int i1 = 0; i1 < tam_bloco; i1++){
                    
                    this.cidades.set(inicioBloco1+i1, novas_cidades.get(inicioBloco2+i1));
                    this.cidades.set(inicioBloco2+i1, novas_cidades.get(inicioBloco1+i1));
                }
                
                break;
            default:
                break;
        }
    }

    @Override
    public int hashCode() {
         int sum = 0;
        int i = 0;
        List<Integer> primos = Primes.gerarPrimos();
        
        for(int j : this.cidades){
            sum += j * primos.get(i++);
        }
        
        return sum;
    }

    @Override
    public String toString() {
        String resp = "";
        
        resp = cidades.stream().map((pos) -> String.valueOf(pos)).reduce(resp, String::concat);
        
        return resp;
    }

    @Override
    public boolean equals(Object obj) {
        TSSolucao ts = (TSSolucao) obj;
        
        for(int i = 0; i < this.cidades.size(); i++){
            if(!ts.cidades.get(i).equals(this.cidades.get(i))){
                return false;
            }
        }
        
        return true;
    }
}
