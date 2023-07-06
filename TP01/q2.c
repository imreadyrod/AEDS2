#include<stdio.h>
#include <stdbool.h>
#include <string.h>

int main(){
    FILE *pontLeitura = fopen("pub.in","r");
    //Devemos abrir o arquivo pub.in no modo leitura
    if(pontLeitura==NULL){
        perror("Erro ao abrir o arquivo!");
        exit(1);
    }
    char pedacos[128];

    /*guardando os pedaços do texto em uma linha buffer*/
    size_t len = sizeof(pedacos);
    char *linha = malloc(len);
    if(linha == NULL){
        perror("Nao foi possivel alocar memoria para a linha do buffer");
        exit(1);
    }

    linha[0]='\0';
    /*enquanto a entrada for diferente de 'FIM'*/
    while(fgets(pedacos,sizeof(pedacos),pontLeitura)!='FIM'){
        /*se o tamanho de 128 pedacos estiver cheio. Iremos realocar memoria*/
        if(len-strlen(linha)<sizeof(pedacos)){
            /*primeiro multiplicamos o pedaco x2*/
            len*=2;
            /* se nao for suficiente o tamanho x2. Iremos realocar memoria*/
            if((linha=realloc(linha,len))==NULL){
                perror("Nao foi possivel realocar memoria para a linha do buffer");
                free(linha);
                exit(1);
            }
        }
        /*se temos memoria suficiente no array "linha" nos podemos concatenar
         * os pedacos do texto no fim da linha
        */
       strcat(linha,pedacos);

       /*
       * se a linha contem '\n'(delimitador de fim de linha), podemos processar a linha do texto! 
       */
    if (ePalindromo(linha)){
        printf("SIM\n");
        /*após a verificação devemos liberar a linha pra ser processada para a nota entrada*/
        linha[0]='\0';
        }
    else{
        printf("NAO");
        linha[0]='\0';
        } 
    }
    fclose(pontLeitura);
    free(linha);
}

bool ePalindromo(const char* linha) {
    int esquerda = 0;
    int direita = strlen(linha) - 1;

    while (esquerda < direita) {
        if (linha[esquerda] != linha[direita]) {
            return false;
        }
        esquerda++;
        direita--;
    }

    return true;
}