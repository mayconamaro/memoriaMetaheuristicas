#ifndef METAHEURISTICAS_HPP
#define METAHEURISTICAS_HPP

class Solucao;
class Instancia;
class Trie;

Solucao* gerarSolucaoAleatoria();

Solucao* vns(Instancia* ins, int numVizinhancas, int maxIteracoes);

void vnd(Instancia* ins, int numVizinhancas, Solucao* sol, Trie* arvore);

void firstImprovement(Instancia* ins, int movimento, Solucao* sol, Trie* arvore);

#endif
