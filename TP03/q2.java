import java.io.RandomAccessFile;


public class q2 {
    public static void main(String[] args) throws Exception {
        lista l = new lista();        
        MyIO.setCharset("ISO-8859-1");
        String input = MyIO.readLine().replaceAll("é", "\u00e9");
        while (!input.equals("FIM")){
            Personagem personagem = new Personagem();
            personagem.ler(input);
            l.inserirFim(personagem);
            input = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        int repeticoes = MyIO.readInt();
        input = MyIO.readLine().replaceAll("é", "\u00e9");
        while (repeticoes!=0){
            if(input.charAt(0)=='R'){
                System.out.print("(R) ");
                l.removerFim().imprimir(); 
            }
            else{
                String caminho = input.substring(2);     
                Personagem personagem = new Personagem();
                personagem.ler(caminho);
                l.inserirFim(personagem);   
            }
            input = MyIO.readLine().replaceAll("é", "\u00e9");
            repeticoes--;
        }
        l.mostrar();

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


class celula{
    public Personagem elemento;
    public celula prox;
    
    public celula(){
        this(null);
    }

    public celula(Personagem x){
        this.elemento = x;
        this.prox = null;
    } 
}

class lista {
    
    private celula primeiro,ultimo;
    
    public lista(){
        primeiro = new celula();
        ultimo = primeiro;
    }

    public void inserirInicio(Personagem x){
        celula tmp = new celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo){
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Personagem x){
    
        ultimo.prox = new celula(x);
        ultimo = ultimo.prox;
    }
    
    public Personagem removerInicio() throws Exception{
        if(primeiro == ultimo)//Garante que a lista não está vazia;
            throw new Exception("Erro!Lista vazia!");
        celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem elemento = tmp.elemento;
        tmp.prox = null;
        tmp = null;
        return elemento; 
    }

    public Personagem removerFim() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");
        celula i;
        for(i=primeiro.prox; i.prox!=ultimo; i=i.prox);
        Personagem elemento = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return elemento;
    }

    public int tamanho(){
        int resp = 0;
        for (celula i = primeiro; i.prox!=null; i=i.prox)
            resp++;
        return resp;
    }

    public void inserir(Personagem x, int pos) throws Exception{
        int tamanho = tamanho();
        if(pos<0 || pos>tamanho || pos<tamanho)
            throw new Exception("Erro!");
        else if (pos==0)
            inserirInicio(x);
        else{
            celula i = primeiro;
            for (int j = 0;j<pos; j++,i=i.prox);
            celula tmp = new celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public Personagem remover(int pos) throws Exception{
        int tamanho=tamanho();
        Personagem resp;
        if(primeiro==ultimo||pos<0||pos>tamanho){
            throw new Exception("Erro!");}
        else if (pos == 0){
            resp = removerInicio();}
        else if (pos==tamanho){
            resp = removerFim();}
        else{
            celula i = primeiro;
            for (int j=0;j<pos;j++,i=i.prox);
            celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        return resp;
    }

    public void mostrar(){
        Personagem personagem;
        int j=0;
        for (celula i = primeiro.prox; i.prox!=null; i=i.prox){
            personagem = i.elemento;
            System.out.print("["+j+"]");
            personagem.imprimir();
            j++;
        }
    }
}