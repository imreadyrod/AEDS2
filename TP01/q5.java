/*
 * Crie um método iterativo que recebe uma string, sorteia
 * duas letras minúsculas aleatórias (código ASCII >= 'a' e <= 'z'), substitui todas as ocorrências da
 * primeira letra na string pela segunda e retorna a string com as alterações efetuadas.
 */

import java.util.Random;

public class q5 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String input = MyIO.readLine(); 
        while(!input.equals("FIM")){
            System.out.println(verdFalse(input));
            input = MyIO.readLine();
            } 
    }

    public static boolean verdFalse(String entrada){
        String[] divisaoEntrada = entrada.split(" ");
        boolean resp;
        
        int qntNumeros = Integer.parseInt(divisaoEntrada[0]);
        int[] numeros = new int[qntNumeros];
        for (int i= 0; i<qntNumeros; i++){
            numeros[i] = Integer.parseInt(divisaoEntrada[i+1]);
        }
        String expressao = divisaoEntrada[qntNumeros+1];
        //primeiro iremos substituir todos os caracteres '(' , ',', por espaços em branco" ".
        expressao.replaceAll(" ", "");
        expressao.replaceAll(")", " ");
        expressao.replaceAll(",", " ");

        for (int j = 0;j<expressao.length()-1;j++){
            ex
        }



        return resp;
    }
}


