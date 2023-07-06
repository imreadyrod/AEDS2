import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class q4 {
    public static void main(String[] args) throws Exception {
        long tempoInicial = System.currentTimeMillis();
        String matricula = "793953";
        Alvinegra b = new Alvinegra();        
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
            System.out.println(caminhoPercorrido.replaceAll("Ã", "\u00c3"));
            input = MyIO.readLine().replaceAll("é", "\u00e9");

        }

        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;
        
        ArquivoLog arquivo = new ArquivoLog();
        arquivo.criarArquivoLog(matricula,tempoExecucao,b.numeroComparacoes);
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

class NoAN{

    public boolean cor;
    public String nome;
    public NoAN esq,dir;

    public NoAN(){
        this("juquinha");
    }
    public NoAN(String nome){
        this(false, nome,null,null);
    }

    public NoAN(String nome, boolean b){
        this(b,nome,null,null);
    }

    public NoAN(boolean b, String nome2, NoAN noesq, NoAN nodir) {
        this.cor = b;
        this.dir = nodir;
        this.esq = noesq;
        this.nome = nome2;
    }
    
}

class Alvinegra{
    
    public NoAN raiz;
    public int numeroComparacoes=0;
    
    public Alvinegra(){
        raiz = null;
    }

    public void incrementaComparacoes(){
        numeroComparacoes++;
    }
  
  
    public int comparaNome(String nome, NoAN noComparado){
        int resultado = nome.compareTo(noComparado.nome);
        return resultado;
    }
    public int comparaNome(NoAN noParacomparacao, NoAN noComparado){
        int resultado = noParacomparacao.nome.compareTo(noComparado.nome);
        return resultado;
    }

   public void inserir(String elemento) throws Exception {
      // Se a arvore estiver vazia
      if (raiz == null) {
         raiz = new NoAN(elemento);

      // Senao, se a arvore tiver um elemento
      } else if (raiz.esq == null && raiz.dir == null) {
         if (comparaNome(elemento, raiz)<0) {
            raiz.esq = new NoAN(elemento);
         } else {
            raiz.dir = new NoAN(elemento);
         }

      // Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null) {
         if (comparaNome(elemento, raiz)<0) {
            raiz.esq = new NoAN(elemento);
         } else if (comparaNome(elemento, raiz.dir)<0) {
            raiz.esq = new NoAN(raiz.nome);
            raiz.nome = elemento;
         } else {
            raiz.esq = new NoAN(raiz.nome);
            raiz.nome = raiz.dir.nome;
            raiz.dir.nome = elemento;
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null) {
         if (comparaNome(elemento, raiz)>0) {
            raiz.dir = new NoAN(elemento);
         } else if (comparaNome(elemento, raiz.esq)>0) {
            raiz.dir = new NoAN(raiz.nome);
            raiz.nome = elemento;
         } else {
            raiz.dir = new NoAN(raiz.nome);
            raiz.nome = raiz.esq.nome;
            raiz.esq.nome = elemento;
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, a arvore tem tres ou mais elementos
      } else {
         inserir(elemento, null, null, null, raiz);
      }
      raiz.cor = false;
   }


    private void inserir(String nome, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {

        if (i == null) {
           if (comparaNome(nome, pai)<0) {
              i = pai.esq = new NoAN(nome, true);
           } else {
              i = pai.dir = new NoAN(nome, true);
           }
           if (pai.cor == true) {
              balancear(bisavo, avo, pai, i);
           }
        } else {
           // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
           if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
              i.cor = true;
              i.esq.cor = i.dir.cor = false;
              if (i == raiz) {
                 i.cor = false;
              } else if (pai.cor == true) {
                 balancear(bisavo, avo, pai, i);
              }
           }
           if (comparaNome(nome, i)<0) {
              inserir(nome, avo, pai, i, i.esq);
           } else if (comparaNome(nome, i)>0) {
              inserir(nome, avo, pai, i, i.dir);
           } else {
              throw new Exception("Erro inserir (nome repetido)!");
           }
        }
     }
     
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
           // 4 tipos de reequilibrios e acoplamento
           if (comparaNome(pai,avo)>0){ // rotacao a esquerda ou direita-esquerda
              if (comparaNome(i,pai)>0) {
                 avo = rotacaoEsq(avo);
              } else {
                 avo = rotacaoDirEsq(avo);
              }
           } else { // rotacao a direita ou esquerda-direita
              if (comparaNome(i,pai)<0) {
                 avo = rotacaoDir(avo);
              } else {
                 avo = rotacaoEsqDir(avo);
              }
           }
           if (bisavo == null) {
              raiz = avo;
           } else if (comparaNome(avo, bisavo)<0) {
              bisavo.esq = avo;
           } else {
              bisavo.dir = avo;
           }
           // reestabelecer as cores apos a rotacao
           avo.cor = false;
           avo.esq.cor = avo.dir.cor = true;
        } 
     }


    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;
  
        noEsq.dir = no;
        no.esq = noEsqDir;
  
        return noEsq;
     }
  
    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;
  
        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
     }
  
    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
     }
  
    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
     }

    public String pesquisar(String nome){
        StringBuilder caminho = new StringBuilder();
        caminho.append(nome);
        caminho.append(" raiz ");
        pesquisarRecursivo(raiz,nome,caminho);
        
        return caminho.toString();
    }

    public void pesquisarRecursivo(NoAN no,String nomeSerPesquisado, StringBuilder caminhoPercorrido){
        if (no == null){
            caminhoPercorrido.append("NÃO");
            return;
        } else if (comparaNome(nomeSerPesquisado, no)==0) {
            caminhoPercorrido.append("SIM");
        } else if (comparaNome(nomeSerPesquisado, no)<0) {
            caminhoPercorrido.append("esq ");
            pesquisarRecursivo(no.esq,nomeSerPesquisado, caminhoPercorrido);
      } else {
            caminhoPercorrido.append("dir ");
            pesquisarRecursivo(no.dir, nomeSerPesquisado,caminhoPercorrido);
      }
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