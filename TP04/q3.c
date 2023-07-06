#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
/**
 * Crie uma Arvore AVL, fazendo inser¸c˜oes de registros conforme a entrada ´
padr˜ao. A chave de pesquisa ´e o atributo Nome do Personagem. N˜ao insira um elemento
se sua chave estiver na ´arvore. Em seguida, pesquise se alguns registros est˜ao cadastrados na
Arvore, mostrando seus respectivos caminhos de pesquisa. A entrada padr˜ao ´e igual a da quest˜ao ´
de “Pesquisa Sequencial”. A sa´ıda padr˜ao ´e composta por v´arias linhas, uma para cada pesquisa.
Cada linha ´e composta pelo caminho ou sequˆencia de ponteiros (raiz, esq ou dir) utilizados na
pesquisa e, no final, pelas palavras SIM ou NAO. Al´em disso, crie um arquivo de log na pasta ˜
corrente com o nome matr´ıcula arvoreBinaria.txt com uma ´unica linha contendo sua matr´ıcula,
tempo de execu¸c˜ao do seu algoritmo e n´umero de compara¸c˜oes. Todas as informa¸c˜oes do arquivo
de log devem ser separadas por uma tabula¸c˜ao ’\t’.
 * 
 */
// Definição do registro do personagem
typedef struct No {
    char Nome[50];
	struct No *esq, *dir;
	short altura;
} No;


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
} Personagem;

// Função para retornar o resto da divisão com double
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
Personagem montaPersonagem(char caminhoArquivo[])
{
    FILE *leitura = fopen(caminhoArquivo, "r");

    char descricaoPersonagem[1000];

    fscanf(leitura, " %[^\n]s", descricaoPersonagem);

    Personagem personagem; // Cria a variável struct

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
                strcpy(personagem.nome, atributo);
                break;
            case 2:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                personagem.altura = atoi(atributo);
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
                personagem.peso = atof(atributo);
                break;
            case 4:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem.corDoCabelo, atributo);
                break;
            case 5:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem.corDaPele, atributo);
                break;
            case 6:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem.corDosOlhos, atributo);
                break;
            case 7:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem.anoNascimento, atributo);
                break;
            case 8:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem.genero, atributo);
                break;
            case 9:
                leituraAtributo(atributo, descricaoPersonagem, i + 3);
                strcpy(personagem.homeworld, atributo);

                i = strlen(descricaoPersonagem); // Encerra os ciclos de repetição desnecessários
                break;
            default:
                break;
            }
        }
    }

    fclose(leitura);

    return personagem;
}

// Função para criar arquivo de log
void criarLog(time_t inicio, int contador[])
{
    float tempo;
    time_t final = time(NULL); // Marcar o final da execução

    tempo = difftime(final, inicio);

    FILE *log = fopen("793953_avl.txt", "w");
    int numeroComparacoes=contador[0];
    int numeroMovimentacoes=contador[1];

    fprintf(log, "Matricula: 793953\tNumero Comparacoes: %d\tNumero Movimentacoes: %d\tTempo Execucao: %fs\n", numeroComparacoes, numeroMovimentacoes, tempo);

    fclose(log);
}

No* novoNo(const char* nome) {
   No *novo = malloc(sizeof(No));
   if(novo){
    strcpy(novo->Nome,nome);
    novo->esq = NULL;
    novo->dir = NULL;
    novo->altura = 0;
   }
   return novo;
}

short maior(short a,short b){
    return (a> b)? a: b;
}

short alturaDonO(No *No){
    if(No == NULL )
        return -1;
    else
        return No-> altura;
}

short fatorDeBalanceamento(No *No){
    if(No)
        return (alturaDonO(No->esq)-alturaDonO(No->dir));
    else
        return 0;
}

No* balancear(No *no){
    if(no!= NULL){
        int fator = no->altura;
    }
}

No* rotacaoDireita(No *no){
    No *noEsq,*noEsqDir;

    noEsq = no ->esq;
    noEsqDir = noEsq ->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    no -> altura =  maior(alturaDonO(no->esq),alturaDonO(no->dir))+1;
    noEsq -> altura = maior(alturaDonO(noEsq->esq),alturaDonO(noEsq->dir))+1;


    return noEsq;
}

No* rotacaoEsquerda(No *no){
    No *noDir,*noDirEsq;

    noDir = no ->dir;
    noDirEsq = noDir -> esq;

    noDir->esq = no;
    no ->dir = noDirEsq;

    no -> altura =  maior(alturaDonO(no->esq),alturaDonO(no->dir))+1;
    noDir -> altura = maior(alturaDonO(noDir->esq),alturaDonO(noDir->dir))+1;

    return noDir;
}

No* rotacaoDireitaEsquerda(No *no){
    no->dir = rotacaoDireita(no->dir);
    return rotacaoEsquerda(no);
}

No* rotacaoEsquerdaDireita(No *no){
    no->esq = rotacaoEsquerda(no->esq);
    return rotacaoDireita(no);
}

No* inserir(No* raiz,char nome[]){
    //Realiza a inserção normal de um nó em uma árvore binária de busca
    if(raiz == NULL){
        return novoNo(nome);
    }
    else{
        if(strcmp(nome,raiz->Nome)<0){
            raiz->esq = inserir(raiz->esq,nome);
        }
        else if(strcmp(nome,raiz->Nome)>0){
            raiz->dir = inserir(raiz->dir,nome);
        }
    }
    
    raiz->altura = maior(alturaDonO(raiz->esq),alturaDonO(raiz->dir))+1;
    
    raiz = balancear(raiz);
    
    return raiz;
}

char pesquisar(No* raiz,const char* nome){
    char caminho[];
    caminho[0] = "nome";
    caminho[1] = " raiz ";
    pesquisarRecursivo(raiz,nome,caminho);

    return caminho;
}

char pesquisarRecursivo(No* raiz, const char* nome, const char* caminho){
    if(raiz == NULL){
        strcat(caminho,"NÃO");
        return;
    }
    else if(strcmp(nome,raiz->Nome)<0){
        strcat(caminho,"esq ");
        no->esq = pesquisarRecursivo(raiz->esq,nome,caminho);
    }
    else if(strcmp(nome,raiz->Nome)>0){
        strcat(caminho,"dir ");
        no->dir = pesquisarRecursivo(raiz->dir,nome,caminho);
    }

}

No* balancear(No* raiz){
    short fb = fatorDeBalanceamento(raiz);

    //Rotação à esquerda
    if( fb< -1 && fatorDeBalanceamento(raiz->dir)<=0)
        raiz = rotacaoEsquerda(raiz);
    
    //Rotação à direita
    else if(fb> 1 && fatorDeBalanceamento(raiz->esq)>= 0)
        raiz = rotacaoDireita(raiz);
    
    //Rotação dupla à esquerda
    else if(fb>1 && fatorDeBalanceamento(raiz->esq)<0)
        raiz = rotacaoEsquerdaDireita(raiz);
    
    //Rotação dupla à direita
    else if (fb > -1 && fatorDeBalanceamento(raiz->esq)<0)
        raiz = rotacaoEsquerdaDireita(raiz);
    
    return raiz;
}


// Função para liberar a memória ocupada pela árvore AVL
void liberarArvore(No *no) {
    if (no != NULL) {
        liberarArvore(no->esq);
        liberarArvore(no->dir);
        free(no);
    }
}


int main(int argc, char const *argv[])
{
    char caminhoArquivo[100], nomePersonagem[100];
    int contadorTamanho = 0, numeroComparacoes = 0, numeroMovimentacoes = 0;
    int contador[2]={0,0};
    No* raiz = NULL;

    scanf(" %[^\n]s", caminhoArquivo);
    getchar();

    while (testaFim(caminhoArquivo) == false)
    {
        Personagem* novoPersona = montaPersonagem(caminhoArquivo);
        raiz = inserir(raiz, novoPersona->nome);
        scanf(" %[^\n]s", caminhoArquivo);
        getchar();
    }
    char input[];
    scanf("%d",&input);
    while(testaFim(input)==false)
    {
        pesquisar(input);
        scanf("%[^\n]s", input);
        getchar();
    }

    return 0;
}
