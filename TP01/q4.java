/*
 * Crie um método iterativo que recebe uma string, sorteia
 * duas letras minúsculas aleatórias (código ASCII >= 'a' e <= 'z'), substitui todas as ocorrências da
 * primeira letra na string pela segunda e retorna a string com as alterações efetuadas.
 */

import java.util.Random;

public class q4 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String input = MyIO.readLine(); 
        while(!input.equals("FIM")){
            System.out.println(alteracaoAleatoria(input));
            input = MyIO.readLine();
            } 
    }

    public static String alteracaoAleatoria(String entrada){
        
        Random gerador = new Random( );
        gerador.setSeed(4);

        String primeiroSorteado = String.valueOf((char)((gerador.nextInt(26))));
        String segundoSorteado = String.valueOf((char)((gerador.nextInt(26))));

       
        String saida = entrada.replaceAll(primeiroSorteado, segundoSorteado);
        
        return saida;
    }
}

