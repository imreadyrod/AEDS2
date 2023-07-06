public class q14 {
       private static int substituiVariaveis(char vetorChar[], int tamanhoDaString)
    {
        int A, B, C;
        A = vetorChar[2];
        B = vetorChar[4];
        C = vetorChar[6];
        int inicioIndice = 6;// inicia a variável caso seja um caso de duas variáveis
        if (vetorChar[0] == '3')
        {
            inicioIndice = 8;// altera o início da pesquisa, caso seja de três variáveis
        }
        /*
         * analisa cada membro do array e altera o seu valor para o valor de 0 ou 1, dependendo da
         * variável convertido para char.
         */
        if(tamanhoDaString>inicioIndice)
        {
            substituiVariaveis(vetorChar, tamanhoDaString-1);           
        }
            if (vetorChar[tamanhoDaString] == 'A')
            {
                vetorChar[tamanhoDaString] = (char) A;
            } else if (vetorChar[tamanhoDaString] == 'B')
            {
                vetorChar[tamanhoDaString] = (char) B;
            } else if (vetorChar[tamanhoDaString] == 'C')
            {
                vetorChar[tamanhoDaString] = (char) C;
            }
        return inicioIndice;
    }
    private static int encontraAbreParentese(char vetorChar[],int fechaParentese)
    {   int abreParentese;
        if (vetorChar[fechaParentese]=='(')
        {
            abreParentese=fechaParentese;
        }
        else
        {
        
        abreParentese=encontraAbreParentese(vetorChar, fechaParentese-1);;
        }
        return abreParentese;

    }
    
    private static void encontraFechaParentese(char vetorChar[],int posicaoInicial,int abreParentese, int fechaParentese, int posicaoFinal)    /* Neste método procura-se o fecha  parêntese mais interno da expressão */
        {
            
            if (vetorChar[posicaoInicial] == ')')
            {
                fechaParentese=posicaoInicial;
                abreParentese=encontraAbreParentese(vetorChar,fechaParentese-1);//procura o parêntese aberto, tenta na posição conhecida -1 pois na posição do parêntese fechado não encontrará
                encontraOperacao(vetorChar, abreParentese, fechaParentese);
            }
           
          if (posicaoInicial<posicaoFinal)
          {
            encontraFechaParentese(vetorChar, posicaoInicial+1, abreParentese, fechaParentese, posicaoFinal);
          }
            
            if(posicaoInicial==posicaoFinal)
            {
                System.out.println(vetorChar[fechaParentese]);
                return;
            }



        }
        private static void encontraOperacao(char vetorChar[],int abreParentese,int fechaParentese)
        {
            if (vetorChar[abreParentese-1]=='t')
            {   
                operacaoNot(vetorChar,abreParentese-3,fechaParentese);//chama-se a função com o intervalo entre parêntese e o tamanho do nome da operação
            }
            else if(vetorChar[abreParentese-1]=='d')
            {
                vetorChar[fechaParentese]='1';//assume que a resposta é verdadeira

                operacaoAnd(vetorChar,abreParentese-3,fechaParentese);//chama o método para descobrir se esta é falsa
            }
            else if(vetorChar[abreParentese-1]=='r')
            {
                vetorChar[fechaParentese]='0';//assume que a resposta é falsa, posteriormente chama o método para verificar se é verdadeira;
                operacaoOr(vetorChar,abreParentese-2,fechaParentese);//chama-se a função com o intervalo entre parêntese juntamente com o nome da operação

            }
        } 
        private static void operacaoNot(char vetorChar[],int abreParentese,int fechaParentese)
        {
                if (vetorChar[abreParentese]=='0')//testa se a proposição é falsa, se for inverte o valor 
                {
                    vetorChar[fechaParentese]='1';//grava o resultado na posição do último parêntese
                }
                else if(vetorChar[abreParentese]=='1')//testa se é falsa, se for, inverte o valor
                {
                    vetorChar[fechaParentese]='0';
                }
                vetorChar[abreParentese] = ' ';// apaga todos os caracteres do intervalo após efetuada a operação
                if (abreParentese<fechaParentese-1)
                {
                    operacaoNot(vetorChar,abreParentese+1,fechaParentese);
                }
               
           
        }
        private static void operacaoAnd(char vetorChar[],int abreParentese,int fechaParentese)
        {
           
                if(vetorChar[abreParentese]=='0')
                {
                vetorChar[fechaParentese]='0';//caso uma das proposições seja falsa, altera a resposta para falso
                }
                vetorChar[abreParentese]=' ';
                if(abreParentese<fechaParentese-1)
                {
                operacaoAnd(vetorChar,abreParentese+1,fechaParentese);
                }
        }
        private static void operacaoOr(char vetorChar[],int abreParentese,int fechaParentese)
        {
                if(vetorChar[abreParentese]=='1')
                {
                    vetorChar[fechaParentese]='1';//caso uma das proposições seja verdadeira, altera a resposta para verdadeiro
                    
                }
                vetorChar[abreParentese]=' ';
                if(abreParentese<fechaParentese-1)
                {
                    operacaoOr(vetorChar, abreParentese+1, fechaParentese);
                }
        }
        
        
    public static void main(String[] args)
    {
        int inicioIndice;
        String stringRecebida=MyIO.readLine();
        do
        {
            int tamanhoDaString=stringRecebida.length();
            char vetorChar[]= new char[tamanhoDaString];
            vetorChar = stringRecebida.toCharArray();
            inicioIndice=substituiVariaveis(vetorChar,tamanhoDaString-1);//chama o método informando a última posição do vetor, recebe o inicío da pesquisa
            encontraFechaParentese(vetorChar,inicioIndice,0,0,tamanhoDaString-1);
            stringRecebida = MyIO.readLine();
        } while (stringRecebida.length()>1);


    }
}