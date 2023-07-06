public class q1 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String input = MyIO.readLine(); 
        boolean respFinal;
        while(!input.equals("FIM")){
            respFinal = palindromo(input);
            if(respFinal){
                System.out.println("SIM");
            }
            else{
                System.out.println("NAO");
            }
            input = MyIO.readLine();
        }
    }

public static boolean palindromo(String palavra){
        String reverse = "";
        boolean resp;
        for (int i = palavra.length() - 1; i >= 0; i--) {
            reverse += palavra.charAt(i);
        }

        if (palavra.equals(reverse)) {
            resp=true;
        } else {
            resp = false;        
        }
        return resp;
    }
}



