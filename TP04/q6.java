import java.util.Arrays;
import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class q6 {
    public static void main(String[] args) throws Exception {
        long tempoInicial = System.currentTimeMillis();
        String matricula = "793953";
        hash h = new hash();        
        MyIO.setCharset("UTF-8");
        String input = MyIO.readLine(); 
        while (!input.equals("FIM")){
            Personagem personagem = new Personagem();
            personagem.ler(input);
            h.inserir(personagem);
            input = MyIO.readLine().replaceAll("é", "\u00c3");
        }

        input = MyIO.readLine().replaceAll("é", "\u00e9");
        while (!input.equals("FIM")){
            String caminhoPercorrido = h.montarSaida(input);
            System.out.println(caminhoPercorrido.replaceAll("Ã", "\u00c3"));
            input = MyIO.readLine().replaceAll("é", "\u00e9");

        }
        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;

        ArquivoLog arquivo = new ArquivoLog();
        arquivo.criarArquivoLog(matricula,tempoExecucao,h.numComparacoes);

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
}

class hash{
    public Personagem[] tabela;
    private final int tamanho = 25;
    public int numComparacoes=0;


    public hash(){
        tabela = new Personagem[tamanho];
        Arrays.fill(tabela,null);
    }

    public int posicaoTabela(int altura){
        return altura%tamanho;
    }

    public int rehash(int altura){
        return (++altura)%tamanho;
    }

    public void inserir(Personagem objPersonagem) {

        int indice = posicaoTabela(objPersonagem.getAltura());
       
        //consultar se a posicao na tabela esta vaga(=null). Se sim inserir. Se não rehash.
        if (tabela[indice]== null) {
            // Espaço vazio, insere o nome diretamente
            numComparacoes++;
            tabela[indice] = objPersonagem;
        } else {
            //caso o espaço não esteja vazio. calcule o rehash
            int indiceRehash = rehash(objPersonagem.getAltura());
            if(tabela[indiceRehash]==null){
                numComparacoes++;
                tabela[indiceRehash] = objPersonagem;
            }
        }
    }
    
    public boolean search(String nome) {
        for (Personagem personagem : tabela) {
            numComparacoes++;
            if (personagem != null && personagem.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public String montarSaida(String nome){
        StringBuilder saida = new StringBuilder(nome);
        if(search(nome)){
            numComparacoes++;
            saida.append(" SIM");
        }
        else{
            numComparacoes++;
            saida.append(" NÃO");
        }
        return saida.toString();
    }
}

class ArquivoLog {
    public void criarArquivoLog(String matricula, long tempoExecucao, int numeroComparacoes) {
        String nomeArquivo = "793953_hashRehash.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            String conteudoArquivo = matricula + "\t" + tempoExecucao + "\t" + numeroComparacoes;
            escritor.write(conteudoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}