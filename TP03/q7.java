import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Personagem {
    // Atributos privados
    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    // Construtores
    public Personagem() {
        this.nome = "";
        this.altura = 0;
        this.peso = 0;
        this.corDoCabelo = "";
        this.corDaPele = "";
        this.corDosOlhos = "";
        this.anoNascimento = "";
        this.genero = "";
        this.homeworld = "";
    }

    public Personagem(String newNome, int newAltura, double newPeso, String newCorDoCabelo, String newCorDaPele,
            String newCorDosOlhos, String newAnoNascimento, String newGenero, String newHomeworld) {
        this.nome = newNome;
        this.altura = newAltura;
        this.peso = newPeso;
        this.corDoCabelo = newCorDoCabelo;
        this.corDaPele = newCorDaPele;
        this.corDosOlhos = newCorDosOlhos;
        this.anoNascimento = newAnoNascimento;
        this.genero = newGenero;
        this.homeworld = newHomeworld;
    }

    // Métodos gets e sets
    public String getNome() {
        return this.nome;
    }

    public void setNome(String newNome) {
        this.nome = newNome;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int newAltura) {
        this.altura = newAltura;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double newPeso) {
        this.peso = newPeso;
    }

    public String getCorDoCabelo() {
        return this.corDoCabelo;
    }

    public void setCorDoCabelo(String newCorDoCabelo) {
        this.corDoCabelo = newCorDoCabelo;
    }

    public String getCorDaPele() {
        return this.corDaPele;
    }

    public void setCorDaPele(String newCorDaPele) {
        this.corDaPele = newCorDaPele;
    }

    public String getCorDosOlhos() {
        return this.corDosOlhos;
    }

    public void setCorDosOlhos(String newCorDosOlhos) {
        this.corDosOlhos = newCorDosOlhos;
    }

    public String getAnoNascimento() {
        return this.anoNascimento;
    }

    public void setAnoNascimento(String newAnoNascimento) {
        this.anoNascimento = newAnoNascimento;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String newGenero) {
        this.genero = newGenero;
    }

    public String getHomeworld() {
        return this.homeworld;
    }

    public void setHomeworld(String newHomeworld) {
        this.homeworld = newHomeworld;
    }

    // Métodos imprimir e ler
    public void parsePersonagem(String jsonPersonagem) {

        for (int i = 0; i < jsonPersonagem.length(); i++) {

            String atributo = ""; // ESSES DOIS PRECISAM ESTAR DENTRO DA REPETIÇÃO PARA SEMPRE LIMPAREM
            String conteudo = "";

            if (jsonPersonagem.charAt(i) == '\'') {
                i++;
                while (jsonPersonagem.charAt(i) != '\'') {
                    atributo += jsonPersonagem.charAt(i);
                    i++;
                }
                i += 4; // ACRESCENTAR QUATRO, PARA JÁ PULAR PARA O CONTEÚDO
                if (atributo.equals("films")) {
                    break;
                }
                // System.out.println(atributo);
                while (jsonPersonagem.charAt(i) != '\'') {
                    conteudo += jsonPersonagem.charAt(i);
                    i++;
                }
                // System.out.println(conteudo);
                switch (atributo) {
                    case "name":
                        // string
                        setNome(conteudo);
                        break;
                    case "height":
                        // int
                        if (conteudo.equals("unknown")) {
                            setAltura(0);
                        } else {
                            int alt = Integer.parseInt(conteudo);
                            setAltura(alt);
                        }
                        break;
                    case "mass":
                        // double
                        if (conteudo.equals("unknown")) {
                            setPeso(0);
                        } else {
                            String pes1 = "";
                            for (int k = 0; k < conteudo.length(); k++) {
                                if (conteudo.charAt(k) != ',') {
                                    pes1 += conteudo.charAt(k);
                                }
                            }
                            double pes2 = Double.parseDouble(pes1);
                            setPeso(pes2);
                        }
                        break;
                    case "hair_color":
                        // string
                        setCorDoCabelo(conteudo);
                        break;
                    case "skin_color":
                        // string
                        setCorDaPele(conteudo);
                        break;
                    case "eye_color":
                        // string
                        setCorDosOlhos(conteudo);
                        break;
                    case "birth_year":
                        // string
                        setAnoNascimento(conteudo);
                        break;
                    case "gender":
                        // string
                        setGenero(conteudo);
                        break;
                    case "homeworld":
                        // string
                        setHomeworld(conteudo);
                        break;
                    default:
                        i = jsonPersonagem.length(); // INTERROMPE A REPETIÇÃO QUANDO ACABAR DE SETAR TUDO, PARA NÃO TER
                                                    // QUE IR ATÉ O FINAL
                        break;
                }
                atributo = "";
                conteudo = "";
            }
        }
    }

    public void print() {
        System.out.print(" ## " + getNome());
        System.out.print(" ## " + getAltura());
        System.out.print(" ## " + (int)getPeso());
        System.out.print(" ## " + getCorDoCabelo());
        System.out.print(" ## " + getCorDaPele());
        System.out.print(" ## " + getCorDosOlhos());
        System.out.print(" ## " + getGenero());
        System.out.println(" ## " + getHomeworld() + " ## ");
    }
}

class Celula {
    public Personagem elemento;
    public Celula next;
    public Celula ant;
    public Celula() {
        this(null); // Celula(null)
    }
    public Celula(Personagem x) {
        this.elemento = x;
        this.next = null;
        this.ant = null;
    }
}

class FilaDuplamenteEncadeada {
    private Celula ultimo;
    private Celula primeiro;

    FilaDuplamenteEncadeada() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    // Inserir (no fim)
    public void push(Personagem p) {
        Celula c = new Celula(p);
        ultimo.next = c;
        c.ant = ultimo;
        ultimo = c;
        c = null;
    }

    // Tamanho
    public int length() {
        Celula c;
        int tam = 0;
        for(c = this.primeiro; c.next != null; c = c.next, tam++);

        return tam;
    }

    // Mostrar
    public void print() {
        Celula tmp = primeiro.next;
        while(tmp != null) {
            tmp.elemento.print();
            tmp = tmp.next;
        }
    }

    // Outros metodos
    public Celula get(int pos) {
        Celula c = primeiro.next;
        for(int i = 0; i < pos; i++, c = c.next);
        return c;
    }

    // Quicksort
    public void quicksort() {
        quicksort(0, length() - 1);
    }

    private void quicksort(int esq, int dir) {
        if(esq < dir) {
            int pivo = particiona(esq, dir);
            quicksort(esq, pivo - 1);
            quicksort(pivo + 1, dir);
        }
    }

    private int particiona(int esq, int dir) {
        String pivo = get(dir).elemento.getCorDoCabelo();
        String pivo2 = get(dir).elemento.getNome();
        int i = esq - 1;
        for(int j = esq; j < dir; j++) {
            if(get(j).elemento.getCorDoCabelo().compareTo(pivo) < 0) {
                i++;
                swap(i, j);
            } else if(get(j).elemento.getCorDoCabelo().compareTo(pivo) == 0) {
                if(get(j).elemento.getNome().compareTo(pivo2) < 0) {
                    i++;
                    swap(i, j);
                }
            } else if(get(j).elemento.getCorDoCabelo().compareTo(pivo) == 0 && get(j).elemento.getNome().compareTo(pivo2) == 0) {
                i++;
                swap(i, j);
            }
        }       
        // Para colocar o pivo que estava na posição dir na posição certa
        swap(i + 1, dir);
        return i + 1;
    }

    private void swap(int i, int j) {
        Celula ci = get(i);
        Celula cj = get(j);
        Personagem tmp = ci.elemento;
        ci.elemento = cj.elemento;
        cj.elemento = tmp;
    }
}

public class TP03Q07 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "ISO-8859-1"); // Scanner(File source, String charsetName)
        String linha = sc.nextLine().replaceAll("é", "\u00E9");

        // Instancia FilaDuplamenteEncadeada
        FilaDuplamenteEncadeada personagens = new FilaDuplamenteEncadeada(); // Aqui já cria a célula sentinela

        while (eof(linha) == false) {

            String jsonPersonagem = lerPubIn(linha); // lê os dados de um personagem e salva em uma string gigante
            //System.out.println(jsonPersonagem);
            Personagem aux = new Personagem();
            aux.parsePersonagem(jsonPersonagem); // faz o tratamento da string identificando cada campo e salvando o dado no seu
                                                 // respectivo lugar
            personagens.push(aux);
            
            linha = sc.nextLine().replaceAll("é", "\u00E9");
        }

        personagens.quicksort();
        
        personagens.print();
        
        sc.close();
    }

    private static boolean eof(String linha) {
        boolean resp = false;
        if (linha.equals("FIM")) {
            resp = true;
        }

        return (resp); // Retorna true se forem iguais e false se diferentes
    }

    private static String lerPubIn(String path) {
        try {
            RandomAccessFile file = new RandomAccessFile(path, "r");
            String jsonPersonagem = file.readLine().replaceAll("é", "\u00e9");
            file.close();
            return jsonPersonagem;
        } catch (IOException e) {
            System.err.println("File not Found");
            e.printStackTrace(); // printa a pilha de execução até o erro
        }
        return null;
    }
}
