#ifndef METAHEURISTICAS_HPP
#define METAHEURISTICAS_HPP

class Solucao;
class Instancia;

Solucao* gerarSolucaoAleatoria();

Solucao* vns(Instancia* ins, int numVizinhancas, int maxIteracoes);

void vnd(Instancia* ins, int numVizinhancas, Solucao* sol);

void firstImprovement(Instancia* ins, int movimento, Solucao* sol);

#endif
