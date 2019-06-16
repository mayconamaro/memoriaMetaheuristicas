#include <cassert>
#include <cstdlib>
#include <iostream>
#include "problema.hpp"
#include "instancia.hpp"
#include "metaheuristicas.hpp"
#include "movimentos.hpp"

/** ------------------- GERACAO DE SOLUCOES ----------------------- **/

Solucao* gerarSolucaoAleatoria(Instancia* ins){

    Solucao* sol = new Solucao(ins->numeroTarefas);
  
   // int otimo[] = {3, 1, 2, 6, 8, 5, 4, 7, 0, 9};
   // int otimo[] = {32, 41, 42, 29, 7, 11, 31, 23, 6, 18, 28, 2, 22, 44, 43, 25, 14, 13, 36, 20, 12, 38, 27, 9, 47, 0, 49, 19, 30, 16, 45, 8, 26, 37, 34, 15, 5, 46, 39, 1, 33, 48, 21, 40, 4, 3, 24, 10, 35, 17};
    int otimo[] = {43, 195, 181, 45, 29, 41, 24, 40, 111, 1, 193, 189, 3, 18, 26, 102, 66, 144, 119, 71, 16, 171, 88, 178, 114, 9, 63, 133, 78, 166, 120, 10, 95, 160, 112, 90, 192, 30, 113, 87, 115, 25, 17, 59, 121, 135, 131, 143, 145, 146, 128, 36, 2, 187, 163, 190, 57, 129, 21, 155, 142, 176, 175, 98, 82, 173, 37, 47, 153, 76, 42, 8, 182, 134, 79, 162, 159, 4, 73, 137, 39, 64, 124, 53, 67, 130, 164, 125, 154, 11, 165, 149, 55, 161, 141, 62, 151, 110, 46, 127, 0, 27, 123, 54, 89, 28, 60, 199, 156, 32, 183, 80, 188, 169, 174, 38, 69, 108, 185, 179, 97, 48, 94, 147, 51, 7, 83, 5, 50, 109, 152, 158, 33, 150, 116, 118, 191, 167, 139, 104, 56, 132, 19, 22, 72, 157, 122, 172, 65, 13, 101, 197, 52, 148, 194, 105, 77, 81, 92, 6, 96, 70, 86, 100, 20, 12, 58, 61, 177, 15, 34, 138, 184, 84, 140, 126, 99, 180, 49, 198, 103, 74, 186, 44, 75, 35, 106, 23, 91, 168, 31, 196, 85, 170, 14, 93, 117, 68, 107, 136};
    for(int i = 0; i < ins->numeroTarefas; i++){
    
        //sol->tarefas[i] = i+1;
        sol->tarefas[i] = otimo[i];
    }
    
    /*for(int i = 0; i < ins->numeroTarefas; i++){
    
        int p1 = rand() % ins->numeroTarefas;
        int p2 = rand() % ins->numeroTarefas;
       // std::cout << p1 << " " << p2 << ", ";
        int aux = sol->tarefas[p1];
        sol->tarefas[p1] = sol->tarefas[p2];
        sol->tarefas[p2] = aux;
    }*/
    
    assert (sol->numeroTarefas > 0);
    
    return sol;
}

/*
Solucao* gerarSolucaoGulosa(Instancia* ins){

    Solucao* sol = new Solucao(ins->numeroTarefas);
    
    int* podeUsar = new int[ins->numeroTarefas);
    int menor = 0;
    int cont = 0;
    
    for(int i = 0; i < ins->numeroTarefas; i++){
    
        podeUsar[i] = i;
    }
    
    
    
    for(int i = 0; i < ins->numeroTarefas; i++){
    
        for(int j = 0; j < ins->numeroTarefas; j++)
            if(cont < ins->numeroTarefas / 2 && ins->a[j] < ins->a[menor])
                menor = j;
            else
                
    }
}
*/

/** ---------------------- VARIABLE NEIGHBOURHOOD --------------------------- **/

Solucao* vns(Instancia* ins, int numVizinhancas, int maxIteracoes){

    int iteracao = 0;
    int k = 1;
    
    Solucao* melhor_sol = gerarSolucaoAleatoria(ins);
    float melhor_f = melhor_sol->valorDeFuncao(ins);
    
    float f_auxiliar = 0;
    
    assert (numVizinhancas > 0);
    assert (ins->numeroTarefas > 0);
    
    Solucao* sol = new Solucao(ins->numeroTarefas);
    
    melhor_sol->copiarPara(sol);
    
    std::cout << "Solucao inicial: "; sol->imprimir(); std::cout << std::endl;
    std::cout << "Valor: " << sol->valorDeFuncao(ins) << std:: endl;
    
    bool melhorou = false;
    
    while(iteracao < maxIteracoes){
       // std::cout <<"chegou aqui ";
        //std::cout << iteracao << " ";
        k = 1;
        melhorou = false;
        
        while(k <= numVizinhancas){
            vnd(ins, numVizinhancas, sol);
            f_auxiliar = sol->valorDeFuncao(ins);
            
            if(f_auxiliar < melhor_f){
            
                sol->copiarPara(melhor_sol);
                melhor_f = f_auxiliar;
                k = 1;
                melhorou = true;
            }else{
                melhor_sol->copiarPara(sol);
                k++;
            }
        }
        
        iteracao += (melhorou ? 0 : 1);
    }
    //melhor_sol->copiarPara(sol);
    delete melhor_sol;
    return sol;
}

/** --------------------- METODOS DE DESCIDA ---------------------- **/

void vnd(Instancia* ins, int numVizinhancas, Solucao* sol){

    Solucao* melhor_sol = new Solucao(ins->numeroTarefas);
    sol->copiarPara(melhor_sol);
    float melhor_f = melhor_sol->valorDeFuncao(ins);
    float f_auxiliar = 0;
    int k = 1;
    
    while(k <= numVizinhancas){
        
        firstImprovement(ins, k, sol);
        f_auxiliar = sol->valorDeFuncao(ins);
        
        if(f_auxiliar < melhor_f){
        
            sol->copiarPara(melhor_sol);
            melhor_f = f_auxiliar;
            k = 1;
        }else{
            
            melhor_sol->copiarPara(sol);
            k++;
        }
    }
    //melhor_sol->copiarPara(sol);
    delete melhor_sol;
}

void firstImprovement(Instancia* ins, int movimento, Solucao* sol){

    int cardinalidadeMaxima = 10;
    int cardinalidade = 0;
    bool houveMelhora = false;
    
    //Solucao* melhor_sol = new Solucao(ins->numeroTarefas);
    //sol->copiarPara(melhor_sol);
    float melhor_f = sol->valorDeFuncao(ins);
    float f_auxiliar = 0;
    
    int i, j, k = 0;
    
    while(cardinalidade < cardinalidadeMaxima && !houveMelhora){
    
        movimentar(sol, movimento, &i, &j, &k);
        f_auxiliar = sol->valorDeFuncao(ins);
        
        if(f_auxiliar < melhor_f){
        
            houveMelhora = true;
            break;
        }else{
            
            assert (i != 0 || j != 0 || k != 0);
            desfazer(sol, movimento, i, j, k);
            cardinalidade++;
        }
    }
}
