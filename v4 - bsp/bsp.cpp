#include <iostream>
#include <cassert>
#include <cmath>
#include "bsp.hpp"
#include "problema.hpp"

float disEuclidiana(int* vet1, int* vet2, int n){

    float soma = 0;
    for(int i = 0; i < n; i++){
    
        soma += std::pow(vet1[i] - vet2[i], 2);
    }
    
    return std::sqrt(soma);
}

void inicializar(BSP* arvore){
    
    arvore->estaAberto = true;
    arvore->valorDeFuncao = 0;
    arvore->elemento = nullptr;
    arvore->filhoEsq = nullptr;
    arvore->filhoDir = nullptr;
}
    
     
void inserir(BSP* arvore, Solucao* sol, float fo, int *revisitacoes){

    
    assert (sol->numeroTarefas > 0);
    
    if(arvore->elemento == nullptr){
        arvore->elemento = new Solucao(sol->numeroTarefas);
        sol->copiarPara(arvore->elemento);
        arvore->valorDeFuncao = fo;
    }else{
    
        if(arvore->filhoEsq == nullptr){
        
            arvore->filhoEsq = new BSP();
            inserir(arvore->filhoEsq, sol, fo, revisitacoes);
        }else{
        
            if(arvore->filhoDir == nullptr){
            
                arvore->filhoDir = new BSP();
                inserir(arvore->filhoDir, sol, fo, revisitacoes);
            }else{
            
                // Distancia euclidiana define pra onde vai
                int posMaiorDiferenca = 0;
                int maiorDiferenca = 0;
                for(int i = 0; i < sol->numeroTarefas; i++){
        
                    if(std::abs(sol->tarefas[i] - arvore->elemento->tarefas[i]) > maiorDiferenca){
            
                        posMaiorDiferenca = i;
                        maiorDiferenca = std::abs(sol->tarefas[i] - arvore->elemento->tarefas[i]);
                    }
                }
                
                if(maiorDiferenca == 0){
                    (*revisitacoes)++;
                }else{
                
                    int dis1 = std::abs(sol->tarefas[posMaiorDiferenca] - arvore->filhoEsq->elemento->tarefas[posMaiorDiferenca]);
                    int dis2 = std::abs(sol->tarefas[posMaiorDiferenca] - arvore->filhoDir->elemento->tarefas[posMaiorDiferenca]);
                    
                    if(dis1 <= dis2){
                    
                        inserir(arvore->filhoEsq, sol, fo, revisitacoes);
                    }else{
                    
                        inserir(arvore->filhoDir, sol, fo, revisitacoes);
                    }
                
                }
            }
        }
    }
}
    
    
float buscar(BSP* arvore, Solucao* sol){

    //TODO
    return 0;
}
    
    
void prettyPrint(BSP* arvore, int nivel){

    if(arvore->elemento != nullptr){
        //arvore->elemento->imprimir();
        std::cout << arvore->valorDeFuncao;
        std::cout << "\n";
    }
    
    if(arvore->filhoEsq != nullptr){
    
        for(int i = 0; i < nivel; i++)
            std::cout << "  ";
        prettyPrint(arvore->filhoEsq, nivel+1);
    }
    
    if(arvore->filhoDir != nullptr){
    
        for(int i = 0; i < nivel; i++)
            std::cout << "  ";
        prettyPrint(arvore->filhoDir, nivel+1);
    }
}
    
    
Solucao* vizinhoNaoVisitado(BSP* arvore, Solucao* sol){

    //TODO
    return nullptr;
}
