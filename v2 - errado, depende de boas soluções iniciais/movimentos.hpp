#ifndef MOVIMENTOS_HPP
#define MOVIMENTOS_HPP

#define TROCA_ADJACENTE 1
#define TROCA_DOIS 2
#define TROCA_TRES 3
#define REALOCACAO 4

class Solucao;
class Instancia;

void movimentar(Solucao* sol, int movimento, int* i, int* j, int* k = nullptr);
void desfazer(Solucao* sol, int movimento, int i, int j, int k = -1);

#endif
