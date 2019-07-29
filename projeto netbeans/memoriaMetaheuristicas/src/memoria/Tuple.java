/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

import java.util.Objects;

/**
 * Classe que implementa uma estrutura de dados comum em notações matemáticas
 * e algoritmos em geral, a tupla. Consiste de dois elementos imutáveis.
 * 
 * @author Maycon Amaro e Paula Toledo
 * @param <X> first
 * @param <Y> second
 */
public class Tuple <X, Y> {
    
    private final X x; 
    private final Y y; 
    
    public Tuple(X x, Y y) { 
        this.x = x; 
        this.y = y; 
    }
    
    /**
     * Função first
     * @return o primeiro elemento da tupla
     */
    public X fst(){
        
        return x;
    }
    
    /**
     * Função second
     * @return o segundo elemento da lista
     */
    public Y snd(){
        
        return y;
    }

    @Override
    public int hashCode() {
        return x.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuple<?, ?> other = (Tuple<?, ?>) obj;
        return Objects.equals(this.x, other.x);
    }
}
