import java.util.*;

public class q5teste {
    public static void main(String[] args) {
        String entrada = "2 0 0 and(not(A) , not(B))";
        String[] divisaoEntrada = entrada.split(" ");

        int qntNumeros = Integer.parseInt(divisaoEntrada[0]);
        int[] numeros = new int[qntNumeros];
        for (int i= 0; i<qntNumeros; i++){
            numeros[i] = Integer.parseInt(divisaoEntrada[i+1]);
        }
        String expressao = divisaoEntrada[qntNumeros+1];
        String ex1 = expressao.replaceAll("and","&&");
        String ex2 = ex1.replaceAll("not","!");
        String ex3 = ex2.replaceAll("or", "||");
        boolean drgdr = Boolean.parseBoolean("1");
        

        boolean expressaoFinal = 
    }
}