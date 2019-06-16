#ifndef BSP_HPP
#define BSP_HPP

class Solucao;

typedef struct no {
    
    /* Nó de uma BSP contem uma solucao, seu valor de funcao objetivo
     * uma flag pra indicar se aquele subespaço está fechado (completamente revisitado)
     * e dois nós filhos, possivelmente vazios (nullptr) */
    
    Solucao* elemento;
    float valorDeFuncao;
    
    bool estaAberto;
    
    struct no *filhoEsq;
    struct no *filhoDir;
} BSP;

void inicializar(BSP* arvore);

void inserir(BSP* arvore, Solucao* sol, float fo, int *revisitacoes);
    
//procurar valor de funcao de uma solucao
float buscar(BSP* arvore, Solucao* sol);
    
//imprimir a arvore
void prettyPrint(BSP* arvore, int nivel = 1);
    
//verificar revisitacao e retornar uma solucao nao visitada
Solucao* vizinhoNaoVisitado(BSP* arvore, Solucao* sol);

#endif
