import java.io.RandomAccessFile;


public class q1 {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria b = new ArvoreBinaria();        
        MyIO.setCharset("UTF-8");
        String input = MyIO.readLine(); 
        while (!input.equals("FIM")){
            Personagem personagem = new Personagem();
            personagem.ler(input);
            b.inserir(personagem.getNome());
            input = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        input = MyIO.readLine().replaceAll("é", "\u00e9");
        while (!input.equals("FIM")){
            String caminhoPercorrido = b.pesquisar(input);
            System.out.println(caminhoPercorrido.replaceAll("Ã", "\uFFFD"));
            input = MyIO.readLine().replaceAll("é", "\u00e9");

        }
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

class No{
    public String chave;
    public No dir,esq;
    
    public No(String chave){
        this.chave = chave;
    }
    public No(String chave, No esq, No dir) {
        this.chave = chave;
        this.esq = esq;
        this.dir = dir;
    }

    public String getchave(){
        return chave;
    }

    public void setChave(Personagem chave) {
        String nome = chave.getNome();
        this.chave = nome;
    }
    public No getDir() {
        return dir;
    }
    public No getEsq() {
        return esq;
    }
    public void setDir(No dir) {
        this.dir = dir;
    }
    public void setEsq(No esq) {
        this.esq = esq;
    }
}

class ArvoreBinaria{
    private No raiz;

    public ArvoreBinaria(){
        raiz = null;
    }

    // Este método, removerAcentos, utiliza do método normalize da classe Normalizer, 
    //para trocar os caracteres especiais por um espaço vazio.
    // private static String removerAcentos(String texto) {
    //     return Normalizer.normalize(texto, Normalizer.Form.NFD)
    //             .replaceAll("[^\\p{ASCII}]", "");
    // }

    public String pesquisar(String nome)throws Exception{
        StringBuilder caminho = new StringBuilder();
        caminho.append(nome);
        caminho.append(" raiz ");
        pesquisarRecursivo(raiz, nome, caminho);
        
        return caminho.toString();
    }

    private void pesquisarRecursivo(No no,String nome,StringBuilder caminho) throws Exception{

        if (no == null) {
            caminho.append("NÃO");
            return;
        }


        int comparacao = nome.compareTo(no.chave);

        if(comparacao == 0){
            caminho.append("SIM");
            return;
        }
        else if(comparacao < 0){
            caminho.append("esq ");
            pesquisarRecursivo(no.esq, nome, caminho);
        }
        else if(comparacao>0){
            caminho.append("dir ");
            pesquisarRecursivo(no.dir, nome, caminho);
        }
    }

    //O método inserir, deve inserir um novo nome de Personagem 
    //como chave de nó apenas se este nome não estiver como chave de algum nó.

    public void inserir(String x) throws Exception{
        raiz = inserirRecursivo(x,raiz);
    }
    private No inserirRecursivo(String x, No i)throws Exception{
        


        // String nome = removerAcentos(x);
        // String nomeNo = removerAcentos(i.getchave());


        if(i == null){
            return new No(x);
        }

        //O método compareTo é utilizado para comparar os nomes em ordem alfabética
        //Caso o nome anteceda o nomeNo na chamada, o resultado será <0.
        //Caso contrário será 1. 
        int resultado = x.compareTo(i.chave);

        if(resultado < 0){
            i.esq = inserirRecursivo(x, i.esq); 
        }
        else if(resultado > 0){
            i.dir = inserirRecursivo(x, i.dir);
        }
        return i;
    }

}
