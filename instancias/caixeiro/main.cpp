#include <iostream>
#include <stdlib.h>
#include <stdio.h>
using namespace std;


int main(){
	int TAM = 200;
	int matriz[TAM][TAM];
	int vet[TAM];
	int num = 0;
	srand (2005);

	FILE* arq;
	//PREENCHENDO A MATRIZ
	for(int i = 0; i<TAM; i++){
		for (int j=0; j<TAM; j++){
			if (i==j){
				matriz[i][j] = 0;
			}else{
				num = rand();
				matriz[i][j] = num;
				matriz[j][i] = num;
			}
		}
	}

	//CRIANDO O ARQUIVO
	if((arq = fopen("cv200k5.txt","w"))==NULL){
		cout<<"Erro na abertura do arquivo"<<endl;
		return 0;
	}

	//ESCREVENDO NO ARQUIVO
	for (int k=0;k<TAM;k++){
		vet[k] = 0;
		vet[0]=TAM;
		//cout<<vet[k]<<" ";
		fprintf(arq,"%d",vet[k]);
		fprintf(arq," ");
	}
	fprintf(arq,"\n");

	for(int i = 0; i<TAM; i++){
		for (int j=0; j<TAM; j++){
			fprintf(arq,"%d" , matriz[i][j]);
			fprintf(arq," ");
		}
		fprintf(arq,"\n");
	}

	fclose(arq);
	
/*	for(int i = 0; i<TAM; i++){
		for (int j=0; j<TAM; j++){
			cout<<matriz[i][j]<<" ";
		}
		cout<<endl;
	}*/
	return 0;
}