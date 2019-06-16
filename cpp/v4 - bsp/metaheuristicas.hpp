#ifndef METAHEURISTICAS_HPP
#define METAHEURISTICAS_HPP
#include "bsp.hpp"

class Solucao;
class Instancia;

Solucao* gerarSolucaoAleatoria();

Solucao* vns(Instancia* ins, int numVizinhancas, int maxIteracoes);

void vnd(Instancia* ins, int numVizinhancas, Solucao* sol, BSP* arquivo, int *revisitacoes);

void firstImprovement(Instancia* ins, int movimento, Solucao* sol, BSP* arquivo, int *revisitacoes);

#endif
