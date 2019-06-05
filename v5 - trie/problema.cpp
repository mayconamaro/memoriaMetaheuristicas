#include <cassert>
#include <algorithm>
#include <iostream>
#include "problema.hpp"

Solucao::Solucao(int n){

    assert (n > 0);
    
    //alocando tudo de uma vez para evitar alocações no cálculo da função objetivo
    this->numeroTarefas = n;
    this->tarefas = new int[n];
    this->alpha = new int[n];
    this->E = new int[n];
    this->beta = new int[n];
    this->T = new int[n];
}

float Solucao::valorDeFuncao(Instancia* ins) {

    assert (ins != nullptr);
    
    // somatório dos custos de atraso e adiantamento das tarefas
    
    this->c = 0;
    
    for(int i = 0; i < this->numeroTarefas; i++){
    
        this->tarefa = this->tarefas[i];
        
        this->c += ins->temposProcessamento[tarefa];
        
        this->alpha[i] = ins->custosAdiantamento[tarefa];
        this->beta[i] = ins->custosAtraso[tarefa];
        
        this->E[i] = std::max(ins->dataEntrega - this->c, 0);
        this->T[i] = std::max(this->c - ins->dataEntrega, 0);
    }
    
    this->soma = 0;
    
    for(int i = 0; i < this->numeroTarefas; i++){
    
        soma += this->E[i] * this->alpha[i] + this->T[i] * this->beta[i];
    }
    
    assert (soma > 0);
    
    return soma;
}

void Solucao::copiarPara(Solucao* sol){

    assert (sol->numeroTarefas == this->numeroTarefas);
    
    for(int i = 0; i < this->numeroTarefas; i++)
        sol->tarefas[i] = this->tarefas[i];
}

void Solucao::imprimir(){

    for(int i = 0; i < this->numeroTarefas; i++)
        std::cout << this->tarefas[i] << " ";
}

Solucao::~Solucao(){

    delete this->tarefas;
    delete this->alpha;
    delete this->beta;
    delete this->E;
    delete this->T;
}
