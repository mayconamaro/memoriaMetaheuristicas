#include <random>
#include <chrono>
#include "movimentos.hpp"
#include "problema.hpp"
#include "instancia.hpp"


void movimentar(Solucao* sol, int movimento, int* i, int* j, int* k){
    
    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
    std::default_random_engine generator (seed);
    std::uniform_int_distribution<int> distribution(0, sol->numeroTarefas - 1);
    
    if(movimento == TROCA_ADJACENTE){
    
        int pos = distribution(generator); //rand() % sol->numeroTarefas;
        
        (*i) = pos;
        (*j) = (pos == 0 ? sol->numeroTarefas-1 : pos-1);
        (*k) = -1;
        
        int aux = sol->tarefas[(*i)];
        sol->tarefas[(*i)] = sol->tarefas[(*j)];
        sol->tarefas[(*j)] = aux;
    } else {
    
        if(movimento == TROCA_DOIS){
        
            int pos1 = distribution(generator); //rand() % sol->numeroTarefas;
            int pos2 = pos1;
            while (pos2 == pos1){
                pos2 = distribution(generator); //rand() % sol->numeroTarefas;
                //cout << pos2 << " ";
            }
            int aux1 = sol->tarefas[pos1];
            sol->tarefas[pos1] = sol->tarefas[pos2];
            sol->tarefas[pos2] = aux1;
            (*i) = pos1;
            (*j) = pos2;
            (*k) = -1;
        } else {
        
            if(movimento == TROCA_TRES){
            
                //TODO
            } else {
            
                //TODO
            }
        }
    }
}

void desfazer(Solucao* sol, int movimento, int i, int j, int k){

    if(movimento == TROCA_ADJACENTE){
    
        int aux = sol->tarefas[i];
        sol->tarefas[i] = sol->tarefas[j];
        sol->tarefas[j] = aux;
    } else {
    
        if(movimento == TROCA_DOIS){
        
            int aux = sol->tarefas[i];
            sol->tarefas[i] = sol->tarefas[j];
            sol->tarefas[j] = aux;
        } else {
        
            if(movimento == TROCA_TRES){
            
                //TODO
            } else {
            
                //TODO
            }
        }
    }
}
