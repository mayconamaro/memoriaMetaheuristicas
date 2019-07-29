/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import agendamento.BFInstancia;
import agendamento.BFSolucao;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;

/**
 *
 * @author mayco
 */
public class ConversaoString {
    
    public static void main(String[] args){
        
        BFInstancia bfi = new BFInstancia();
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File f = jf.getSelectedFile();
        
        bfi.lerDeArquivo(f.getAbsolutePath());
        BFSolucao bf = new BFSolucao();
        
        bf.tarefas = new ArrayList<>(Arrays.asList(3, 1, 2, 6, 5, 8, 4, 7, 0, 9));
        System.out.println(bf.toString());
    }
    
}
