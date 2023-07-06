import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class q3 {
    public static void main(String[] args) throws Exception {
        long tempoInicial = System.currentTimeMillis();
        String matricula = "793953";
        avl b = new avl();        
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

class no{
    public int nivel;
    public String nome;
    public no esq,dir;

    public no(){
        this.nome= "";
        this.esq = null;
        this.dir = null;
        this.nivel = 1;
    }
    public no(String nome){
        this(1, nome,null,null);
    }

    public no(String nome, int nivel){
        this(nivel,nome,null,null);
    }

    public no(int nivel, String nome, no noesq, no nodir) {
        this.nivel = nivel;
        this.dir = nodir;
        this.esq = noesq;
        this.nome = nome;
    }

    public void setNivel() {
	    this.nivel = 1 + Math.max(getNivel(esq),getNivel(dir));
	}
    public static int getNivel(no no) {
		return (no == null) ? 0 : no.nivel;
	}
    
}

class avl{
    
    public no raiz;
    public int numeroComparacoes=0;
    
    public avl(){
        raiz = null;
    }

    public void incrementaComparacoes(){
        numeroComparacoes++;
    }
  
  
    public int comparaNomeNo(String nome, no noComparado){
        int resultado = nome.compareTo(noComparado.nome);
        incrementaComparacoes();
        return resultado;
    }
    public int comparaNome(no noParacomparacao, no noComparado){
        int resultado = noParacomparacao.nome.compareTo(noComparado.nome);
        incrementaComparacoes();
        return resultado;
    }

    public void inserir(String elemento) throws Exception{
        raiz = inserir(elemento, raiz);
    }
    private no inserir(String elemento,no i) throws Exception {
      // Se a arvore estiver vazia
        if (i == null) {
            incrementaComparacoes();
            i = new no(elemento);
        }
        else if(comparaNomeNo(elemento, i)<0){
            incrementaComparacoes();
            i.esq = inserir(elemento, i.esq);
        }
        else if(comparaNomeNo(elemento, i)>0){
            incrementaComparacoes();
            i.dir = inserir(elemento, i.dir);
        }
        else{
            throw new Exception("Erro ao inserir");
        }
        return balancear(i);
   }

     
    public no balancear(no i) throws Exception {
        if (i != null) {
            incrementaComparacoes();
			int fator = i.getNivel(i.dir) - i.getNivel(i.esq);
			// Se balanceada
			if (Math.abs(fator) <= 1) {
                incrementaComparacoes();
				i.setNivel();
			// Se desbalanceada para a direita
			} else if (fator == 2) {
                incrementaComparacoes();
				int fatorFilhoDir = i.getNivel(i.dir.dir) - i.getNivel(i.dir.esq);
				// Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
                    incrementaComparacoes();
					i.dir = rotacaoDir(i.dir);
				}
				i = rotacaoEsq(i);
			// Se desbalanceada para a esquerda
			} else if (fator == -2) {
                incrementaComparacoes();
				int fatorFilhoEsq = i.getNivel(i.esq.dir) - i.getNivel(i.esq.esq);
				// Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
                    incrementaComparacoes();
					i.esq = rotacaoEsq(i.esq);
				}
				i = rotacaoDir(i);
			} else {
				throw new Exception(
						"Erro no No(" + i.nome + ") com fator de balanceamento (" + fator + ") invalido!");
			}
		}
		return i;
	}


    private no rotacaoDir(no no) {
        no noEsq = no.esq;
        no noEsqDir = noEsq.dir;
  
        noEsq.dir = no;
        no.esq = noEsqDir;
  
        return noEsq;
     }
  
    private no rotacaoEsq(no no) {
        no noDir = no.dir;
        no noDirEsq = noDir.esq;
  
        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
     }


    public String pesquisar(String nome){
        StringBuilder caminho = new StringBuilder();
        caminho.append(nome);
        caminho.append(" raiz ");
        pesquisarRecursivo(raiz,nome,caminho);
        
        return caminho.toString();
    }

    public void pesquisarRecursivo(no no,String nomeSerPesquisado, StringBuilder caminhoPercorrido){
        if (no == null){
            caminhoPercorrido.append("NÃO");
            return;
        } else if (comparaNomeNo(nomeSerPesquisado, no)==0) {
            caminhoPercorrido.append("SIM");
        } else if (comparaNomeNo(nomeSerPesquisado, no)<0) {
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
        String nomeArquivo = "793953_avl.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            String conteudoArquivo = matricula + "\t" + tempoExecucao + "\t" + numeroComparacoes;
            escritor.write(conteudoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}