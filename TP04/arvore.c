#include <stdio.h>
#include <stdlib.h>

// Definição da estrutura do nó da árvore binária
typedef struct Node {
    int valor;
    struct Node* esquerda;
    struct Node* direita;
} Node;

// Função para criar um novo nó
Node* novoNo(int valor) {
    Node* no = (Node*)malloc(sizeof(Node));
    no->valor = valor;
    no->esquerda = NULL;
    no->direita = NULL;
    return no;
}

// Função para inserir um novo nó na árvore binária
Node* inserir(Node* raiz, int valor) {
    if (raiz == NULL) {
        return novoNo(valor);
    } else {
        if (valor < raiz->valor) {
            raiz->esquerda = inserir(raiz->esquerda, valor);
        } else {
            raiz->direita = inserir(raiz->direita, valor);
        }
        return raiz;
    }
}

// Função para buscar um valor na árvore binária
Node* buscar(Node* raiz, int valor) {
    if (raiz == NULL || raiz->valor == valor) {
        return raiz;
    } else if (valor < raiz->valor) {
        return buscar(raiz->esquerda, valor);
    } else {
        return buscar(raiz->direita, valor);
    }
}

// Função para imprimir a árvore binária em ordem (ordem crescente)
void imprimirEmOrdem(Node* raiz) {
    if (raiz != NULL) {
        imprimirEmOrdem(raiz->esquerda);
        printf("%d ", raiz->valor);
        imprimirEmOrdem(raiz->direita);
    }
}

// Função para liberar a memória ocupada pela árvore binária
void liberarArvore(Node* raiz) {
    if (raiz != NULL) {
        liberarArvore(raiz->esquerda);
        liberarArvore(raiz->direita);
        free(raiz);
    }
}

int main() {
    Node* raiz = NULL;

    // Exemplo de inserção de valores na árvore
    raiz = inserir(raiz, 50);
    raiz = inserir(raiz, 30);
    raiz = inserir(raiz, 70);
    raiz = inserir(raiz, 20);
    raiz = inserir(raiz, 40);
    raiz = inserir(raiz, 60);
    raiz = inserir(raiz, 80);

    // Exemplo de busca e impressão da árvore em ordem
    Node* resultado = buscar(raiz, 40);
    if (resultado != NULL) {
        printf("Valor encontrado: %d\n", resultado->valor);
    } else {
        printf("Valor não encontrado.\n");
    }

    printf("Árvore binária em ordem: ");
    imprimirEmOrdem(raiz);
    printf("\n");

    // Liberar a memória ocupada pela árvore
    liberarArvore(raiz);

    return 0;
}
