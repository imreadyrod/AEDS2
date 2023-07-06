#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef struct Personagem{ 
    char nome[40];
    int altura;
    double peso;
    char corDoCabelo[40];
    char corDaPele[40];
    char corDosOlhos[40];
    char anoNascimento[40];
    char genero[40];
    char homeworld[40];
}Personagem;

typedef struct Celula{
    struct Celula* prox;
    struct Celula* ant;
    struct Personagem* person;
} Celula;

struct Celula *novaCelula(Personagem* personagem){

    Celula* novaCelula = (Celula*)malloc(sizeof(Celula));
    novaCelula->prox = novaCelula->ant = NULL;
    novaCelula->person = NULL;

    return novaCelula;
}

double fmod(double x, double y)
{
    return x - (int)(x / y) * y;
}

// Capturar o atributo entre aspas simples
void leituraAtributo(char atributo[], char descricaoPersonagem[], int index)
{
    int i = 0;

    while (descricaoPersonagem[index] != '\'')
    {
        atributo[i] = descricaoPersonagem[index];

        i++;
        index++;
    }

    atributo[i] = '\0';
}

// Função para testar o fim do arquivo
bool testaFim(char palavra[])
{
    bool teste = false;

    if (palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M')
    {
        teste = true;
    }

    return teste;
}


// Função para estruturar o personagem
void montaPersonagem2(char caminhoArquivo[], Personagem * personagem){

    FILE *leitura = fopen(caminhoArquivo, "r");

    char descricaoPersonagem[1000];

    fscanf(leitura, " %[^\n]s", descricaoPersonagem);
    int contador = 0;

    for (int i = 0; i < strlen(descricaoPersonagem); i++)
    {
        if (descricaoPersonagem[i] == ':')
        {
            char atributo[50];
            contador++;
            switch (contador)
            {
            case 1:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->nome, atributo);
                break;
            case 2:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                personagem->altura = atoi(atributo);
                break;
            case 3:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                for (int i = 0; i < strlen(atributo); i++)
                {
                    if (atributo[i] == ',')
                    {
                        atributo[i] = atributo[i - 1];
                        atributo[i - 1] = '0';
                    }
                }
                personagem->peso = atof(atributo);
                break;
            case 4:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->corDoCabelo, atributo);
                break;
            case 5:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->corDaPele, atributo);
                break;
            case 6:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->corDosOlhos, atributo);
                break;
            case 7:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->anoNascimento, atributo);
                break;
            case 8:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->genero, atributo);
                break;
            case 9:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem->homeworld, atributo);

                i = strlen(descricaoPersonagem); // Encerra os ciclos de repetição desnecessários
                break;
            default:
                break;
            }
        }
    }

    fclose(leitura);

    return;
}

void imprimirAtributos(Personagem* listaPersonagens){

        printf(" ## %s", listaPersonagens->nome);
        printf(" ## %d", listaPersonagens->altura);
        if (fmod(listaPersonagens->peso, 1) == 0)
            printf(" ## %.0lf", listaPersonagens->peso);
        else
            printf(" ## %.1lf", listaPersonagens->peso);
        printf(" ## %s", listaPersonagens->corDoCabelo);
        printf(" ## %s", listaPersonagens->corDaPele);
        printf(" ## %s", listaPersonagens->corDosOlhos);
        printf(" ## %s", listaPersonagens->anoNascimento);
        printf(" ## %s", listaPersonagens->genero);
        printf(" ## %s", listaPersonagens->homeworld);
        printf(" ## \n");
}


Personagem* newPersonagem()
{
    Personagem *person = (Personagem *)malloc(sizeof(Personagem));
    person->nome[0]='\0';
    int altura=0;
    double peso=0;
    person->corDoCabelo[0]='\0';
    person->corDaPele[0]='\0';
    person->corDosOlhos[0]='\0';
    person->anoNascimento[0]='\0';
    person->genero[0]='\0';
    person->homeworld[0]='\0';
    return person;
}

/*Criando a lista*/
Celula* primeiro;
Celula* ultimo;

void listadupla(){
    primeiro = novaCelula(NULL);
    ultimo = primeiro;
}

void mostrar() {
   Celula* i;
   for (i=primeiro->prox; i != NULL; i = i->prox) {
      imprimirAtributos(i->person);
   }
}

int verificaTamanho(){
    int contador = 0;
    for(Celula* i=primeiro; i!=ultimo; i=i->prox, contador++);
    return contador;
}

Personagem* pegaPivo(int tamanho){
    int posPivo = tamanho/2;
    Personagem* elemento;
    Celula* tmp = primeiro;
    for(int i=0; i<=posPivo; i++){
        tmp = tmp->prox;
    }
    elemento = tmp;
    tmp = NULL;
    return elemento;
} 

void quicksort(int n) {
    quicksortRec(0, n);
}

void quicksortRec(int esq, int dir) {
    int i = esq, j = dir;
    
    Celula* tmpI = primeiro;
    Celula* tmpJ = primeiro;

    for(int i=0; i<esq; i++,tmpI = tmpI->prox);
    for(int j=0; j<dir; j++,tmpJ = tmpJ->prox);

    Personagem* pivo = pegaPivo((esq+dir)/2);
    while (strcmp(tmpI->person->corDoCabelo,pivo->corDoCabelo)<=0) {
        while (strcmp(tmpI->person->corDoCabelo,pivo->corDoCabelo)<0) tmpI=tmpI->prox;
        while (strcmp(tmpJ->person->corDoCabelo,pivo->corDoCabelo)<0) tmpJ=tmpJ->ant;
        if (strcmp(tmpI->person->corDoCabelo,tmpJ->person->corDoCabelo)<=0) {
            Personagem* aux1 = tmpI->person;
            Personagem* aux2 = tmpJ ->person;
            tmpJ->person = aux1;
            tmpI->person = aux2;  
            tmpI = tmpI->prox;
            tmpJ = tmpJ->ant;
        }
    }
    if (esq < j)  quicksortRec(esq, j);
    if (i < dir)  quicksortRec(i, dir);
}





int main(void){

    listadupla();

    char caminhoArquivo[100], nomePersonagem[100];
    int contadorTamanho = 0;
    
    
    while(fgets(caminhoArquivo,100,stdin)!="FIM"){
        Personagem* personagem=newPersonagem();
        montaPersonagem2(caminhoArquivo, personagem);
        inserir(personagem);       
    }

    int tamanhoLista = verificaTamanho();
    
    quicksort(tamanhoLista);

    mostrar();
    
    return 0;
}