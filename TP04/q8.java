import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class q8Rod {
    public static void main(String[] args) throws Exception
    {
        int contadorComparacao = 0;
        long inicioTempo = System.nanoTime();
        String stringRecebida;
        Trie primeira=new Trie();
        Trie segunda=new Trie();

        MyIO.setCharset("ISO-8859-1");
        stringRecebida = MyIO.readLine().replaceAll("é", "\u00e9");
        while (!stringRecebida.equals("FIM"))
        {
            Personagem personagem = new Personagem();
            personagem.ler(stringRecebida);
            String nome=personagem.getNome();
            primeira.inserir(nome);
            stringRecebida = MyIO.readLine().replaceAll("é", "\u00e9");
        }
        //pesquisa
        stringRecebida = MyIO.readLine().replaceAll("é", "\u00e9");
        while (!stringRecebida.equals("FIM"))
        {
            Personagem personagem = new Personagem();
            personagem.ler(stringRecebida);
            String nome=personagem.getNome();
            segunda.inserir(nome);
            stringRecebida = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        List<String> nomesPrimeiraTRIE = primeira.recuperarNomes();        
        List<String> nomesSegundaTRIE = segunda.recuperarNomes(); 
        
        ArrayList<String> nomesFinal = new ArrayList<>();


        for (String nome:nomesPrimeiraTRIE){
            contadorComparacao++;
            if(!nomesFinal.contains(nome)){
                nomesFinal.add(nome);
            }
        }
        for (String nome:nomesSegundaTRIE){
            contadorComparacao++;
            if(!nomesFinal.contains(nome)){
                nomesFinal.add(nome);
            }
        }

        Trie terceira = new Trie();
        for(String nome:nomesFinal){
            terceira.inserir(nome);
        }


        stringRecebida = MyIO.readLine().replaceAll("é", "\u00e9");
        while (!stringRecebida.equals("FIM"))
        {
            MyIO.print(stringRecebida);
            if(terceira.pesquisar(stringRecebida))
            {
                MyIO.println(" SIM");
            }
            else
            {
                MyIO.println(" NÃO");
            }
        
            stringRecebida=MyIO.readLine().replaceAll("é", "\u00e9");//
        }
        long fimTempo = System.nanoTime();
        String tempoDecorrido = " , tempo decorrido em nanosegundos :" + (fimTempo - inicioTempo);
        String conteudoArquivo = "Número de comparações " + contadorComparacao+"\t"+ tempoDecorrido;
        String nomeDoArquivo = "793953_arvoreTRIE.txt";
        Arq.openWriteClose(nomeDoArquivo, conteudoArquivo);
    }
}
class Trie {
    public No raiz;
    public int compara;

    public Trie(){
        raiz = new No();
    }

    public void inserir(String palavra)
{
    inserir(0,palavra,raiz);
}
    private void inserir (int posicao, String palavra, No no)
{   
    compara++;
    if(no==null)
    {
        no=new No();
    }
    if(no.alfabeto[palavra.charAt(posicao)]==null)
    {
        compara++;
        no.alfabeto[(int)palavra.charAt(posicao)]=new No(palavra.charAt(posicao));
        compara++;
        if(posicao<palavra.length()-1){
        posicao++;
        inserir(posicao, palavra, no.alfabeto[(int)palavra.charAt(posicao)]);
        }
        compara++;
        if(posicao==palavra.length()-1){
        no.fim=true;
        }
    }
    else{
        compara++;
        if(posicao<palavra.length()-1){
        posicao++;
        inserir(posicao, palavra, no.alfabeto[(int)palavra.charAt(posicao)]);
        }
        compara++;
        if(posicao==palavra.length()-1){
        no.fim=true;
        }
    }

}
    
    public List<String> recuperarNomes() {
        List<String> nomes = new ArrayList<>();
        StringBuilder nomeAtual = new StringBuilder();
        recuperarNomesRecursivo(raiz, nomeAtual, nomes);
        return nomes;
    }

    private void recuperarNomesRecursivo(No no, StringBuilder nomeAtual, List<String> nomes) {
        if (no.fim==true) {
            nomes.add(nomeAtual.toString());
        }

        for (int i = 0; i < no.alfabeto.length; i++) {
            No filho = no.alfabeto[i];
            if (filho != null) {
                char caracterAtual = (char) (i);
                nomeAtual.append(caracterAtual);
                recuperarNomesRecursivo(filho, nomeAtual, nomes);
                nomeAtual.deleteCharAt(nomeAtual.length() - 1);
            }
        }
    }


public Boolean pesquisar(String nome)
{
    return pesquisar(nome, raiz,0);
}
private Boolean pesquisar(String nome, No no, int posicao)
{ Boolean resp=false;
    compara++;
    if(no==null)
    {
        resp=false;
    }
    else if(no.alfabeto[(int)nome.charAt(posicao)]==null)
    {
        compara++;
        resp=false;
    }
    else if(no.fim==true)
    {
        resp=true;
    }
    else
    {
        resp=pesquisar(nome,no.alfabeto[(int)nome.charAt(posicao)],posicao+1);
    }
    return resp;

}
public void mostrar()
{
    mostrar("",raiz);
}

public void mostrar(String s,No no) {
    if (no.fim == true) {
    System.out.println("Palavra: " + (s + no.letra));
    } else {
    for (int i = 0; i < no.alfabeto.length; i++) {
        if (no.alfabeto[i] != null) {
        mostrar(s + no.letra, no.alfabeto[i]);
                }
            }
}
}
}
class No{
    char letra;
    No alfabeto[];
    Boolean fim;
    
    public char getLetra()
    {
        return letra;
    }
    public void setLetra(char letra)
    {
        this.letra = letra;
    }
    public No[] getAlfabeto()
    {
        return alfabeto;
    }
    public void setAlfabeto(No[] alfabeto)
    {
        this.alfabeto = alfabeto;
    }
    No(){
        letra=0;
        alfabeto=new No[256];
        fim=false;
    }
    No(char letra)
    {
        this.letra=letra;
        alfabeto=new No[256];
        fim=false;
    }


}

class Personagem {
    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele,
            String corDosOlhos, String anoNascimento, String genero, String homeworld) {
        setNome(nome);
        setAltura(altura);
        setPeso(peso);
        setCorDoCabelo(corDoCabelo);
        setCorDaPele(corDaPele);
        setCorDosOlhos(corDosOlhos);
        setAnoNascimento(anoNascimento);
        setGenero(genero);
        setHomeworld(homeworld);
    }

    Personagem() {
        setNome("");
        setAltura(0);
        setPeso(0.0);
        setCorDoCabelo("");
        setCorDaPele("");
        setCorDosOlhos("");
        setAnoNascimento("");
        setGenero("");
        setHomeworld("");
    }

    // Setter e Getter para o atributo nome
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }

    // Setter e Getter para o atributo altura
    public void setAltura(int altura)
    {
        this.altura = altura;
    }

    public int getAltura()
    {
        return altura;
    }

    // Setter e Getter para o atributo peso
    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    public double getPeso()
    {
        return peso;
    }

    // Setter e Getter para o atributo corDoCabelo
    public void setCorDoCabelo(String corDoCabelo)
    {
        this.corDoCabelo = corDoCabelo;
    }

    public String getCorDoCabelo()
    {
        return corDoCabelo;
    }

    // Setter e Getter para o atributo corDaPele
    public void setCorDaPele(String corDaPele)
    {
        this.corDaPele = corDaPele;
    }

    public String getCorDaPele()
    {
        return corDaPele;
    }

    // Setter e Getter para o atributo corDosOlhos
    public void setCorDosOlhos(String corDosOlhos)
    {
        this.corDosOlhos = corDosOlhos;
    }

    public String getCorDosOlhos()
    {
        return corDosOlhos;
    }

    // Setter e Getter para o atributo anoNascimento
    public void setAnoNascimento(String anoNascimento)
    {
        this.anoNascimento = anoNascimento;
    }

    public String getAnoNascimento()
    {
        return anoNascimento;
    }

    // Setter e Getter para o atributo genero
    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    public String getGenero()
    {
        return genero;
    }

    // Setter e Getter para o atributo homeworld
    public void setHomeworld(String homeworld)
    {
        this.homeworld = homeworld;
    }

    public String getHomeworld()
    {
        return homeworld;
    }

    public void ler(String filePath) throws Exception
    {

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        // String jsonString = file.readLine().replaceAll("é", "\u00e9");
        String jsonString = file.readLine();
        file.close();

        // Separando keys e values e atribuindo a suas variaveis
        String[] pairs = jsonString.substring(1, jsonString.length() - 1).split(", '");
        for (String pair : pairs)
        {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].replaceAll("\'", "");
            String value = keyValue[1].replaceAll("\'", "").trim();

            if (key.equals("name"))
            {
                setNome(value);
            } else if (key.equals("height"))
            {
                if (value.equals("unknown"))
                {
                    setAltura(0);
                    continue;
                }
                setAltura(Integer.parseInt(value));
            } else if (key.equals("mass"))
            {
                if (value.equals("unknown"))
                {
                    setPeso(0);
                    continue;
                }
                setPeso(Double.parseDouble(value.replaceAll(",", "")));
            } else if (key.equals("hair_color"))
            {
                setCorDoCabelo(value);
            } else if (key.equals("skin_color"))
            {
                setCorDaPele(value);
            } else if (key.equals("eye_color"))
            {
                setCorDosOlhos(value);
            } else if (key.equals("birth_year"))
            {
                setAnoNascimento(value);
            } else if (key.equals("gender"))
            {
                setGenero(value);
            } else if (key.equals("homeworld"))
            {
                setHomeworld(value);
            } else
            {
                break;
            }
        }
    }

    public void imprimir()
    {
        MyIO.setCharset("UTF-8");
        MyIO.print(" ## " + this.nome);
        MyIO.print(" ## " + this.altura);
        if (this.peso % 1.0 != 0)
        {
            MyIO.print(" ## " + String.format("%s", this.peso));
        } else
        {
            MyIO.print(" ## " + String.format("%.0f", this.peso));
        }
        MyIO.print(" ## " + this.corDoCabelo);
        MyIO.print(" ## " + this.corDaPele);
        MyIO.print(" ## " + this.corDosOlhos);
        MyIO.print(" ## " + this.anoNascimento);
        MyIO.print(" ## " + this.genero);
        MyIO.print(" ## " + this.homeworld);
        MyIO.print(" ## ");
        MyIO.println("");
    }

    public Personagem clone()
    {
        Personagem cloned = new Personagem();

        cloned.nome = this.nome;
        cloned.altura = this.altura;
        cloned.peso = this.peso;
        cloned.corDoCabelo = this.corDoCabelo;
        cloned.corDaPele = this.corDaPele;
        cloned.corDosOlhos = this.corDosOlhos;
        cloned.anoNascimento = this.anoNascimento;
        cloned.genero = this.genero;
        cloned.homeworld = this.homeworld;

        return cloned;
    }
    /*
     * public int hashInsercao(Personagem personagem) { int remocao=0;
     * 
     * }
     */
}