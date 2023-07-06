public class q6{
    public static void main(String[] args)
    {
        String stringRecebida = MyIO.readLine();
        while (!stringRecebida.equals("FIM"))
        {
            /*
             * Se a String é somente composta por vogais, imprime como sim, lê a próxima string e pula o restante das verificações
             */
            if (IsVogal(stringRecebida))
            {
                MyIO.print("SIM ");
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.println("NAO");
                stringRecebida = MyIO.readLine();
                continue;
            } else if (IsConsoante(stringRecebida))//no caso de não ser, testa se é composta por consoantes.
            {
                MyIO.print("NAO ");
                MyIO.print("SIM ");
                MyIO.print("NAO ");
                MyIO.println("NAO");
                stringRecebida = MyIO.readLine();
                continue;
            } else if (IsInt(stringRecebida))//no caso contrário, testa se é composta por números, se sim, a resposta é sim para o módulo três e quatro.
            {
                MyIO.print("NAO ");
                MyIO.print("NAO ");
                MyIO.print("SIM ");
                MyIO.println("SIM ");
                stringRecebida = MyIO.readLine();
                continue;
            } else if (IsReal(stringRecebida))//por final testa se é composta por números reais. Do contrário imprime não
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

class Is {
    /*
     * Testa-se o caractere dentro da iteração é uma vogal, se for adiciona no contador, caso o
     * contador seja igual ao tamanho da string, retorna true.
     */
    public static boolean IsVogal(String stringRecebida)
    {
        int tamanhoDaString = stringRecebida.length();
        int contador = 0;
        boolean resposta = false;
        stringRecebida = stringRecebida.toLowerCase();
        for (int c = 0; c < tamanhoDaString; c++)
        {
            if (stringRecebida.charAt(c) == 'a' || stringRecebida.charAt(c) == 'e'
                    || stringRecebida.charAt(c) == 'i' || stringRecebida.charAt(c) == 'o'
                    || stringRecebida.charAt(c) == 'u')
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

    public static boolean IsConsoante(String stringRecebida)// comportamento similar ao IsVogal, com
                                                            // uma adendo, testa se todos são de um
                                                            // intervalo de valor na tabela ascii e
                                                            // se não são vogais
    {
        int tamanhoDaString = stringRecebida.length();
        int contador = 0;
        boolean resposta = false;
        stringRecebida = stringRecebida.toLowerCase();
        for (int c = 0; c < tamanhoDaString; c++)
        {
            int ascii = (int) stringRecebida.charAt(c);
            if (ascii >= 'b' && ascii <= 'z' && stringRecebida.charAt(c) != 'a'
                    && stringRecebida.charAt(c) != 'e' && stringRecebida.charAt(c) != 'i'
                    && stringRecebida.charAt(c) != 'o' && stringRecebida.charAt(c) != 'u')
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
    public static boolean IsInt(String stringRecebida)
    {
        boolean resposta = false;
        int contador = 0;
        int ascii;
        int tamanhoDaString = stringRecebida.length();
        for (int c = 0; c < tamanhoDaString; c++)
        {
            ascii = (int) stringRecebida.charAt(c);
            if (ascii >= '0' && ascii <= '9')
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
     * verifica se existe uma vírgula ou ponto, se sim, adiciona no contador de vírgulas, depois
     * verifica todos os caracteres. Para retornar true, a quantidade de caracteres de números -1
     * deve ser igual ao contador e o contador de vírgula ou ponto igual a 1.
     */
    public static boolean IsReal(String stringRecebida)
    {
        boolean resposta = false;
        int contador = 0, contadorVirgulaOuPonto = 0;
        int ascii;
        int tamanhoDaString = stringRecebida.length();
        for (int c = 0; c < tamanhoDaString; c++)
        {
            ascii = (int) stringRecebida.charAt(c);
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
}

