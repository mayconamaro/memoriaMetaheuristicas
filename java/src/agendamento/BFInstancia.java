package agendamento;

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
 * Instancias do problema de agendamento de tarefas em uma única máquina com
 * data de entrega comum e diferentes penalinades de atraso e adiantamento.
 *
 * @author Maycon Amaro e Paula Toledo
 */
public class BFInstancia extends Instancia {

    public List<Integer> temposProcessamento;
    public List<Integer> custosAdiantamento;
    public List<Integer> custosAtraso;
    public final double h = 0.4;    // definido por Nearchou e Omirou 
    public int dataEntrega = 0;
    public int numeroTarefas = 0;

    @Override
    public void lerDeArquivo(String arquivo) {

        try (FileReader fr = new FileReader(arquivo); 
            BufferedReader br = new BufferedReader(fr)) {

            String linha = br.readLine();
            String[] palavras = linha.split(" ");
            numeroTarefas = Integer.parseInt(palavras[0]);

            temposProcessamento = new ArrayList();
            custosAdiantamento = new ArrayList();
            custosAtraso = new ArrayList();

            linha = br.readLine();

            while (linha != null) {

                palavras = linha.split(" ");
                temposProcessamento.add(Integer.parseInt(palavras[0]));
                custosAdiantamento.add(Integer.parseInt(palavras[1]));
                custosAtraso.add(Integer.parseInt(palavras[2]));

                linha = br.readLine();
            }
            
            dataEntrega = 0;
            
            temposProcessamento.forEach((p) -> {
                dataEntrega += p;
            });
            
            dataEntrega = (int) Math.floor(h * dataEntrega);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BFInstancia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BFInstancia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
