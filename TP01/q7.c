/*
 * Testa-se o caractere dentro da iteração é uma vogal, se for adiciona no contador, caso o
 * contador seja igual ao tamanho da string, retorna true.
 */
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <string.h>
void PassaParaMinuscula(char stringRecebida[], int tamanhoDaString)
{
    for (int c = 0; c < tamanhoDaString; c++)
    {
        stringRecebida[c] = tolower(stringRecebida[c]);
    }
}
int IsVogal(char stringRecebida[], int tamanhoDaString)
{
    int contador = 0;
    int resposta = 0;
    PassaParaMinuscula(stringRecebida, tamanhoDaString);
    for (int c = 0; c < tamanhoDaString; c++)
    {
        if (stringRecebida[c] == 'a' || stringRecebida[c] == 'e' || stringRecebida[c] == 'i' || stringRecebida[c] == 'o' || stringRecebida[c] == 'u')
        {
            contador++;
        }
    }
    if (contador == tamanhoDaString)
    {
        resposta = 1;
    }
    return resposta;
}

int IsConsoante(char stringRecebida[], int tamanhoDaString) // comportamento similar ao IsVogal, com
                                                            // uma adendo, testa se todos são de um
                                                            // intervalo de valor na tabela ascii e
                                                            // se não são vogais
{
    int contador = 0;
    int resposta = 0;
    for (int c = 0; c < tamanhoDaString; c++)
    {
        int ascii = (int)stringRecebida[c];
        if (ascii >= 'b' && ascii <= 'z' && stringRecebida[c] != 'a' && stringRecebida[c] != 'e' && stringRecebida[c] != 'i' && stringRecebida[c] != 'o' && stringRecebida[c] != 'u')
        {
            contador++;
        }
    }
    if (contador == tamanhoDaString)
    {
        resposta = true;
    }
    return resposta;
}

/*
 * verifica se todos os caracteres são correspondentes a números, se sim o contador será igual
 * ao tamanho da string e retorna verdadeiro
 */
int IsInt(char stringRecebida[], int tamanhoDaString)
{
    int resposta = 0;
    int contador = 0;
    int ascii;
    for (int c = 0; c < tamanhoDaString; c++)
    {
        ascii = (int)stringRecebida[c];
        if (ascii >= '0' && ascii <= '9')
        {
            contador++;
        }
    }
    if (contador == tamanhoDaString)
    {
        resposta = 1;
    }
    return resposta;
}

/*
 * verifica se existe uma vírgula ou ponto, se sim, adiciona no contador de vírgulas, depois
 * verifica todos os caracteres. Para retornar true, a quantidade de caracteres de números -1
 * deve ser igual ao contador e o contador de vírgula ou ponto igual a 1.
 */
int IsReal(char stringRecebida[], int tamanhoDaString)
{
    bool resposta = false;
    int contador = 0, contadorVirgulaOuPonto = 0;
    int ascii;
    for (int c = 0; c < tamanhoDaString; c++)
    {
        ascii = (int)stringRecebida[c];
        if (ascii >= '0' && ascii <= '9')
        {
            contador++;
        }
        if (ascii == '.' || ascii == ',')
        {
            contadorVirgulaOuPonto++;
        }
    }
    if (contador == tamanhoDaString - 1 && contadorVirgulaOuPonto == 1)
    {
        resposta = true;
    }
    return resposta;
}

int main(void)
{
    char stringRecebida[1000];
    int i = 0;
    scanf(" %[^\n]s", stringRecebida);
    getchar();
    i = strlen(stringRecebida);
    for (int c=0;c<i;c++)
    {
        stringRecebida[c]=tolower(stringRecebida[c]);
    }
   
    
    while ((stringRecebida[0] != 'f') || (stringRecebida[1] != 'i') || (stringRecebida[2] != 'm'))
    {
        /*
         * Se a String é somente composta por vogais, imprime como sim, lê a próxima string e pula o restante das verificações
         */
        if (IsVogal(stringRecebida, i) == 1)
        {
            printf("SIM ");
            printf("NAO ");
            printf("NAO ");
            printf("NAO\n");
        }
        else if (IsConsoante(stringRecebida, i) == 1) // no caso de não ser, testa se é composta por consoantes.
        {
            printf("NAO ");
            printf("SIM ");
            printf("NAO ");
            printf("NAO\n");
        }
        else if (IsInt(stringRecebida, i) == 1) // no caso contrário, testa se é composta por números, se sim, a resposta é sim para o módulo três e quatro.
        {
            printf("NAO ");
            printf("NAO ");
            printf("SIM ");
            printf("SIM\n");
        }
        else if (IsReal(stringRecebida, i) == 1) // por final testa se é composta por números reais. Do contrário imprime não
        {
            printf("NAO ");
            printf("NAO ");
            printf("NAO ");
            printf("SIM\n");
        }
        else
        {
            printf("NAO ");
            printf("NAO ");
            printf("NAO ");
            printf("NAO\n");
        }
        scanf(" %[^\n]s", stringRecebida);
        getchar();
        i = strlen(stringRecebida);
        for (int c=0;c<i;c++)
    {
        stringRecebida[c]=tolower(stringRecebida[c]);
    }
        
    }
}