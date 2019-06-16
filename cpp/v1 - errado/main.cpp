#include <iostream>
#include <cmath>
#include <algorithm>
#include <ctime>
#include <cstdlib>

using namespace std;

void imprimeSOl(int* sol, int n){

    for(int i = 0; i < n; i++){
    
        cout << sol[i] << " ";
    }
    
    cout << endl;
}

int* copiar(int* sol, int n){

    int* a = new int[n];
    for(int i = 0; i < n; i++)
        a[i] = sol[i];
    return a;
}

void movimento(int numero, int* sol, int n){

    //cout << "mov ";
    
    
    int pos;
    int aux;
    int pos1, pos2, aux1;
    switch(numero){
    
        case 1:
            
            pos = rand() % (n-1) + 1;
            aux = sol[pos];
            sol[pos] = sol[pos-1];
            sol[pos-1] = aux;
            break;
        case 2:
            
            pos1 = rand() % n;
            pos2 = pos1;
            while (pos2 == pos1){
                pos2 = rand() % n;
                //cout << pos2 << " ";
            }
            aux1 = sol[pos1];
            sol[pos1] = sol[pos2];
            sol[pos2] = aux1;
            break;
        default:
            break;
    }
}

int sum(int* x, int n){

    int cont = 0;
    for (int i = 0; i < n; i++){
    
        cont += x[i];
    }
    return cont;
}

float calcularFO(int* solucao, int n, int* P, int* a, int* b, int d){

    int c = 0;
    int tarefa;
    int* E = new int[n];
    int* T = new int[n];
    int* alpha = new int[n];
    int* beta = new int[n];
    
    for(int i = 0; i < n; i++){
    
        tarefa = solucao[i];
        c += P[tarefa];
        E[i] = max(d - c, 0);
        T[i] = max(c - d, 0);
        alpha[i] = a[tarefa];
        beta[i] = b[tarefa];
    }
    
    int soma = 0;
    for(int i = 0; i < n; i++){
    
        soma += E[i] * alpha[i] + T[i] * beta[i];
    }

    delete E;
    delete T;
    delete alpha;
    delete beta;
    
    return soma;
}

void firstImpro(int mov, int* sol, int n, int* P, int* a, int* b, int d){
    
    //cout << "firstimpro ";
    float f_star = calcularFO(sol, n, P, a, b, d);
    int* sstar = copiar(sol, n);
    
    int cardinalidadeMaxima = 80;
    int cardinalidade = 0;
    bool houveMelhora = false;
    
    while (cardinalidade < cardinalidadeMaxima && !houveMelhora){
    
        int* slinha = copiar(sol, n);
        movimento (mov, sol, n);
        float f = calcularFO(sol, n, P, a, b, d);
        //cout << "novo f fimpro " << f << endl;
        if(f < f_star){
        
            f_star = f;
         //   delete sstar;
            sstar = copiar(sol, n);
        }else{
        
            cardinalidade++;
           // delete sol;
            sol = copiar(slinha, n);
        }
        
     //   delete slinha;
    }
    
   // delete sstar;
}

void vnd(int num_k, int* solInicial, int n, int* P, int* a, int* b, int d){
    
    //cout << "vnd ";
    float f_star = calcularFO(solInicial, n, P, a, b, d);
    int* sstar = copiar(solInicial, n);
    
        int k = 1;
        while(k <= num_k){
            
            firstImpro(k, solInicial, n, P, a, b, d);
            float f = calcularFO(solInicial, n, P, a, b, d);
           // cout << "novo f vnd " << f << endl;
            if(f < f_star){
                f_star = f;
                //delete sstar;
                sstar = copiar(solInicial, n);
                k = 1;
            }else{
            
                k++;
                //delete solInicial;
                solInicial = copiar(sstar, n);
            }
        }
        
        //delete sstar;
}

void vns(int num_k, int* solInicial, int n, int* P, int* a, int* b, int d){
   // cout << "vns ";
    float f_star = calcularFO(solInicial, n, P, a, b, d);
    int* sstar = copiar(solInicial, n);
    
    int cardinalidadeMaxima = 13;
    int cardinalidade = 0;
    
    while(cardinalidade < cardinalidadeMaxima){
        
        cout << f_star << endl;
        int k = 1;
        while(k <= num_k){
            
            vnd(num_k, solInicial, n, P, a, b, d);
            float f = calcularFO(solInicial, n, P, a, b, d);
           // cout << "novo f vns " << f << endl;
            if(f < f_star){
                
                f_star = f;
                //cout << "f no vns " << f_star << endl;
                //delete sstar;
                sstar = copiar(solInicial, n);
                k = 1;
                cardinalidade = 0;
            }else{
            
                k++;
                cardinalidade++;
                //delete solInicial;
                solInicial = copiar(sstar, n);
            }
        }
    }
    
    //delete sstar;
}

int main(int argc, char* argv[]){
    srand(time(NULL));
    int njobs;
    int ignorar;
    
    //lendo numero de tarefas
    cin >> njobs >> ignorar >> ignorar;
    
    //lendo instancia
    int* P = new int[njobs];
    int* a = new int[njobs];
    int* b = new int[njobs];
    for(int i = 0; i < njobs; i++){
    
        cin >> P[i] >> a[i] >> b[i];
    }
    
    int d = floor(0.4 * sum(P, njobs));
    /**  Teste de leitura OK
    for(int i = 0; i < njobs; i++){
    
        cout << P[i] << " " << a[i] << " " << b[i] << endl;
    }
    */
    
    int * solInicial = new int[njobs];
    //for(int i = 0; i < njobs; i++)
        //solInicial[i] = i;
    solInicial[0] = 8;
    solInicial[1] = 7;
    solInicial[2] = 2;
    solInicial[3] = 6;
    solInicial[4] = 3;
    solInicial[5] = 5;
    solInicial[6] = 4;
    solInicial[7] = 1;
    solInicial[8] = 0;
    solInicial[9] = 9;
    
    cout << "FO solucao inicial " << calcularFO(solInicial, njobs, P, a, b, d) << endl;  
    imprimeSOl(solInicial, njobs);
    clock_t end,start;
    start = clock();
    vns(2, solInicial, njobs, P, a, b, d);
    end = clock();
    //cout.flush();
    cout << "FO solucao final " << calcularFO(solInicial, njobs, P, a, b, d) << endl;
    imprimeSOl(solInicial, njobs);
    cout << "tempo: " << (double)(end-start)/(double)(CLOCKS_PER_SEC) << " segundos" << endl;
    //cout << "terminei " << endl;
    
    delete P;
    delete a;
    delete b;
    delete solInicial;
    
    return 0;
}
