public class q15 {
    /*
     * Testa-se o caractere dentro da iteração é uma vogal, se for adiciona no contador, caso o
     * contador seja igual ao tamanho da string, retorna true.
     */


    public static boolean isVogal(String stringRecebida, int posAtual)
    {
        int tamanhoDaString = stringRecebida.length();
        boolean resposta = false;
        stringRecebida = stringRecebida.toLowerCase();
        if (stringRecebida.charAt(posAtual) == 'a' || stringRecebida.charAt(posAtual) == 'e'
                || stringRecebida.charAt(posAtual) == 'i' || stringRecebida.charAt(posAtual) == 'o'
                || stringRecebida.charAt(posAtual) == 'u')
        {
            if (posAtual < tamanhoDaString - 1)
            {
                resposta = isVogal(stringRecebida, posAtual + 1);
            }
            if (posAtual == tamanhoDaString - 1)
            {
                resposta = true;
            }
        }


        return resposta;
    }

    public static boolean isConsoante(String stringRecebida, int posAtual)// comportamento similar
                                                                          // ao isVogal, com
    // uma adendo, testa se todos são de um
    // intervalo de valor na tabela ascii e
    // se não são vogais
    {
        int tamanhoDaString = stringRecebida.length();
        boolean resposta = false;
        stringRecebida = stringRecebida.toLowerCase();
        int ascii = (int) stringRecebida.charAt(posAtual);
        if (ascii >= 'b' && ascii <= 'z' && stringRecebida.charAt(posAtual) != 'a'
                && stringRecebida.charAt(posAtual) != 'e' && stringRecebida.charAt(posAtual) != 'i'
                && stringRecebida.charAt(posAtual) != 'o' && stringRecebida.charAt(posAtual) != 'u')
        {
            if (posAtual < tamanhoDaString - 1)
            {
                resposta = isConsoante(stringRecebida, posAtual + 1);
            }
            if (posAtual == tamanhoDaString - 1)
            {
                resposta = true;
            }
        }



        return resposta;
    }

    /*
     * verifica se todos os caracteres são correspondentes a números, se sim o contador será igual
     * ao tamanho da string e retorna verdadeiro
     */
    public static boolean isInt(String stringRecebida, int posAtual)
    {
        boolean resposta = false;
        int ascii;
        int tamanhoDaString = stringRecebida.length();

        ascii = (int) stringRecebida.charAt(posAtual);
        if (ascii >= '0' && ascii <= '9')
        {
            if (posAtual < tamanhoDaString - 1)
            {
                resposta = isInt(stringRecebida, posAtual + 1);
            }
            if (posAtual == tamanhoDaString - 1)
            {
                resposta = true;
            }
        }

        
        return resposta;

    }

    /*
     * verifica se existe uma vírgula ou ponto, se sim, adiciona no contador de vírgulas, depois
     * verifica todos os caracteres. Para retornar true, a quantidade de caracteres de números -1
     * deve ser igual ao contador e o contador de vírgula ou ponto igual a 1.
     */
    public static boolean isReal(String stringRecebida, int posAtual, int contador, int contadorVirgulaOuPonto)
    {
        boolean resposta = false;
        int ascii;
        int tamanhoDaString = stringRecebida.length();

        ascii = (int) stringRecebida.charAt(posAtual);
        if (ascii >= '0' && ascii <= '9')
        {
            contador++;
        }
        if (ascii == '.' || ascii == ',')
        {
            contadorVirgulaOuPonto++;
        }
        if (posAtual < tamanhoDaString - 1)
        {
            resposta = isReal(stringRecebida, posAtual + 1, contador, contadorVirgulaOuPonto);
        }

        if (contador == tamanhoDaString - 1 && contadorVirgulaOuPonto == 1)
        {
            resposta = true;
        }
        return resposta;
    }

    public static void main(String[] args)
    {
        String stringRecebida = MyIO.readLine();
        while (!stringRecebida.equals("FIM"))
        {
            /*
             * Se a String é somente composta por vogais, imprime como sim, lê a próxima string e
             * pula o restante das verificações
             */
            if (isVogal(stringRecebida, 0))
            {
                MyIO.print("SIM ");
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.println("NAO");
                stringRecebida = MyIO.readLine();
                continue;
            } else if (isConsoante(stringRecebida, 0))// no caso de não ser, testa se é composta por
                                                      // consoantes.
            {
                MyIO.print("NAO ");
                MyIO.print("SIM ");
                MyIO.print("NAO ");
                MyIO.println("NAO");
                stringRecebida = MyIO.readLine();
                continue;
            } else if (isInt(stringRecebida, 0))// no caso contrário, testa se é composta por
                                                // números, se sim, a resposta é sim para o módulo
                                                // três e quatro.
            {
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.print("SIM ");
                MyIO.println("SIM ");
                stringRecebida = MyIO.readLine();
                continue;
            } else if (isReal(stringRecebida, 0,0,0))// por final testa se é composta por números reais.
                                                 // Do contrário imprime não
            {
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.println("SIM");
                stringRecebida = MyIO.readLine();
                continue;
            } else
            {
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.println("NAO");
            }
            stringRecebida = MyIO.readLine();
        }
    }

}