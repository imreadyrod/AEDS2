#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


typedef struct Personagem
{  
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

typedef struct Celula
{
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

/*Criando a lista*/
Celula* primeiro;
Celula* ultimo;

void listadupla(){
    primeiro = novaCelula(NULL);
    ultimo = primeiro;
}

void inserirInicio(Personagem* x){
    
    Celula* tmp = novaCelula(x);
    
    tmp->ant = primeiro;
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if(primeiro == ultimo){
        ultimo=tmp;
    }
    else{
        tmp->prox->ant=tmp;
    }
    tmp = NULL;
}

Personagem* removerFim(){
    if(primeiro==ultimo){
        perror("erro ao remover(vazia)!");
    }
    Personagem* resp = ultimo->person;
    ultimo = ultimo->ant;
    ultimo->prox->ant = NULL;
    free(ultimo->prox);
    ultimo->prox = NULL;
    return resp;
}

int verificaTamanho(){
    int contador = 0;
    for(Celula* i=primeiro; i!=ultimo; i=i->prox, contador++);
    return contador;
}

int mediaAltura(){
    int soma = 0;
    double resultado;
    int denominador = verificaTamanho();
    for(Celula* i=primeiro->prox;i!=ultimo; i = i->prox, soma += i->person->altura);
    resultado = (double) soma/denominador;
    int resultadoArredondado = ceil(resultado);
    return resultadoArredondado;
}

int inserir(Personagem* personagem){
    int tamanhoLista = verificaTamanho();
    if(tamanhoLista=6){
        removerFim();
        inserirInicio(personagem);
        printf("%d\n",mediaAltura);
    }
    else{
        inserirInicio(personagem);
        printf(mediaAltura);
    }
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

void imprimirAtributos(Personagem listaPersonagens[], int tamanhoTotal){

    for (int i = 0; i < tamanhoTotal; i++){
    
        printf(" ## %s", listaPersonagens[i].nome);
        printf(" ## %d", listaPersonagens[i].altura);
        if (fmod(listaPersonagens[i].peso, 1) == 0)
            printf(" ## %.0lf", listaPersonagens[i].peso);
        else
            printf(" ## %.1lf", listaPersonagens[i].peso);
        printf(" ## %s", listaPersonagens[i].corDoCabelo);
        printf(" ## %s", listaPersonagens[i].corDaPele);
        printf(" ## %s", listaPersonagens[i].corDosOlhos);
        printf(" ## %s", listaPersonagens[i].anoNascimento);
        printf(" ## %s", listaPersonagens[i].genero);
        printf(" ## %s", listaPersonagens[i].homeworld);
        printf(" ## \n");
    }
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

int main(void){

    listadupla();

    char caminhoArquivo[100], nomePersonagem[100];
    int contadorTamanho = 0;
    /*lendo a entrada e atribuindo a caminhoArquivo*/
    scanf("%[^\n]s", caminhoArquivo);

    while (testaFim(caminhoArquivo) == false){
    /**Verificando qual a posição final da entrada "caminhoArquivo" com os delimitadores \r\n*/
        char * p=strchr(caminhoArquivo,'\r\n');
        if(p!=NULL)
        {/*Atribuindo o fim da String "caminhoArquivo" na posição encontrada pelo strchr*/
            *p='\0';
        }
        Personagem* personagem=newPersonagem();
        montaPersonagem2(caminhoArquivo, personagem);
        inserir(personagem);
        scanf(" %[^\n]s", caminhoArquivo);
    }

    int repeticoes;
    scanf(" %[^\n]s", repeticoes);

    while (repeticoes != 0){
        char entrada[128];
        char pedacos[2];
        char delimitador[]=" ";
        
    /*Aqui estamos dividindo a entrada em comando(pedacos[0]) e caminho do arquivo(pedacos[1])*/
        scanf(" %[^\n]s", entrada);
        
        pedacos[0]=strtok(entrada,delimitador);
        
        int i = 1;
        while (i < 2 && pedacos[i-1] != NULL) {
            pedacos[i] = strtok(NULL, delimitador);
            i++;
        }
        if(pedacos[0]=="I"){
            Personagem* personagem = newPersonagem();
            montaPersonagem2(pedacos[1],personagem);
            inserir(personagem);
        }
        else if(pedacos[1]=="R"){
            printf("%s\n",removerFim()->nome);
        }
        
        scanf(" %[^\n]s", entrada);
        getchar();
    }
    return 0;
}