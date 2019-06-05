#ifndef INSTANCIA_HPP
#define INSTANCIA_HPP

class Instancia {

public:
    Instancia(int n);
    int numeroTarefas;
    int* temposProcessamento;
    int* custosAtraso;
    int* custosAdiantamento;
    int dataEntrega;
    ~Instancia();
};

#endif
