/*
 * CIFRAMANETO DE CÉSAR
 * ideia basica é um simples deslocamento de caracteres.
 * Assim, por exemplo, se a chave utilizada para criptografar as mensagens
 * for 3, todas as ocorr^encias do caractere 'a' são substituídas pelo caractere 'd', as do 'b' por 'e',
 * e assim sucessivamente.
 */

public class q3 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String input = MyIO.readLine(); 
        while(!input.equals("FIM")){
            System.out.println(ciframentoCesar(input));
           
                input = MyIO.readLine();
            } 
    }
    public static String ciframentoCesar(String entrada){
        int tamanho = entrada.length()-1;
        StringBuilder saida = new StringBuilder(tamanho);

        for(int i=0; i<=tamanho; i++){   
            int deslocamento = (int)entrada.charAt(i) + 3;
            saida.append((char)deslocamento);
        }
        return saida.toString();
    }
}



