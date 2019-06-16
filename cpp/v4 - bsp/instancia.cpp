#include "instancia.hpp"

Instancia::Instancia(int n){

    this->numeroTarefas = n;
    this->temposProcessamento = new int[n];
    this->custosAtraso = new int[n];
    this->custosAdiantamento = new int[n];
}

Instancia::~Instancia(){

    delete this->temposProcessamento;
    delete this->custosAtraso;
    delete this->custosAdiantamento;
}
