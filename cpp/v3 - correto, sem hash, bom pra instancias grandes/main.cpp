#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <ctime>
#include <cassert>
#include <cmath>
#include "instancia.hpp"
#include "problema.hpp"
#include "metaheuristicas.hpp"

#define H 0.4

int main(int argc, char* argv[]){

    // Configurando semente
    srand(time(NULL));
    
    /** -------- Lendo instancia --------**/
    
    int n, p, a, b, i1, i2 = -1;
    std::cin >> n >> i1 >> i2;
    
    assert (n != -1);
    
    Instancia* ins = new Instancia(n);
    
    int soma = 0;
    
    for(int i = 0; i < n; i++){
    
        std::cin >> p >> a >> b;
        ins->temposProcessamento[i] = p;
        soma += p;
        ins->custosAdiantamento[i] = a;
        ins->custosAtraso[i] = b;
    }
    
    ins->dataEntrega = std::floor(soma * H);
    
    /** -------- Rodando o VNS -------- **/
   
    clock_t inicio = clock();
    
    Solucao* sol = vns(ins, 2, 13);
    
    clock_t fim = clock();
    double segundos = ((double(fim - inicio)) * 1.0) / CLOCKS_PER_SEC;
    
    /** -------- Imprimindo Resultados -------- **/
    std::cout << "Solucao: "; sol->imprimir(); std::cout << std::endl;
    std::cout << "Valor da solucao: " << sol->valorDeFuncao(ins) << std::endl;
    std::cout << "Tempo gasto: " << segundos << " segundos" << std::endl;
        
    delete sol;
    delete ins;
    
    return 0;
}
