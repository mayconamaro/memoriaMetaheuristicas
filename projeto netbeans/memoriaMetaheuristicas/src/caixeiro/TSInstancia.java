package caixeiro;

import agendamento.BFInstancia;
import interfaces.Instancia;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Instancias do problema do caxeiro viajante
 *
 * @author Maycon Amaro e Paula Toledo
 */
public class TSInstancia extends Instancia {

    public int numeroCidades = 0;
    public List<List<Integer>> distancia;

    @Override
    public void lerDeArquivo(String arquivo) {
        try (FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr)) {

            String linha = br.readLine();
            String[] palavras = linha.split(" ");
            numeroCidades = Integer.parseInt(palavras[0]);

            distancia = new ArrayList(numeroCidades);

            linha = br.readLine();

            int cont = 0;
            while (linha != null) {

                palavras = linha.split(" ");

                assert (palavras.length == numeroCidades);
                distancia.add(new ArrayList());

                for (int i = 0; i < numeroCidades; i++) {

                    distancia.get(cont).add(Integer.parseInt(palavras[i]));

                }

                linha = br.readLine();
                cont++;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BFInstancia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BFInstancia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
