#ifndef PROBLEMA_HPP
#define PROBLEMA_HPP

#include "instancia.hpp"

class Solucao {

public:
    Solucao(int n);
    float valorDeFuncao(Instancia* ins);
    void copiarPara(Solucao* sol);
    void imprimir();
    int* tarefas;
    int numeroTarefas;
    ~Solucao();

private:
    // Essas variaveis sao uteis para o calculo da função objetivo
    // mas alocar durante o cálculo repetidamente é mais custoso
    // que só alocar uma vez e reutilizar
    int* alpha;
    int* E;
    int* beta;
    int* T;
    int c;
    int tarefa;
    float soma;
};

#endif
