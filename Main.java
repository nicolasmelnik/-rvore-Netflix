import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static String removerNaoNumericos(String input) {
    Pattern pattern = Pattern.compile("[^0-9]");
    Matcher matcher = pattern.matcher(input);
    return matcher.replaceAll("");
  }

  private static boolean containsEmpty(String[] partes) {
    for (String parte : partes) {
      if (parte.isEmpty()) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    // Ler o nome do arquivo de dados do usuário
    String nomeArquivo = "titles.csv";

    List<ProgramaNetflix> programas = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
      String linha;
      br.readLine(); // Pule a primeira linha (cabeçalho)

      while ((linha = br.readLine()) != null) {
        boolean placeholder = true;
        StringBuilder sb = new StringBuilder();
        sb.append(linha.strip());

        while (placeholder) { // Necessário para ignorar quebra de linha nas colunas description do dataset
          try {
            if (sb.toString().charAt(sb.length() - 1) != ',') {
              Integer.parseInt(String.valueOf(sb.toString().charAt(sb.length() - 1)));
            }
            placeholder = false;
          } catch (Exception e) {
            sb.append(br.readLine().strip());
          }
        }

        String[] partes = sb.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        double temporadas;
        if (partes[2].equalsIgnoreCase("MOVIE")) {
          temporadas = 0; // Set temporadas to 0 for movies
          partes[9] = "0";
        } else {
          temporadas = Double.parseDouble(partes[9]);
        }

        if (!containsEmpty(partes) && partes.length == 15) {
          String id = partes[0];
          String titulo = partes[1];
          String showType = partes[2];
          String descricao = partes[3];
          int releaseYear = Integer.parseInt(partes[4]);
          String ageCertification = partes[5];
          int runtime = Integer.parseInt(partes[6]);

          String partes7Tratada = partes[7].replaceAll("[\\[\\]\'\\s]", "");
          List<String> generos = Arrays.asList(partes7Tratada.split(","));

          String partes8Tratada = partes[8].replaceAll("[\\[\\]\'\\s]", "");
          List<String> productionCountries = Arrays.asList(partes8Tratada.split(","));

          String imdbId = partes[10];
          double imdbScore = Double.parseDouble(partes[11]);
          double imdbVotes = Double.parseDouble(partes[12]);
          double tmdbPopularity = Double.parseDouble(partes[13]);
          double tmdbScore = Double.parseDouble(partes[14]);

          ProgramaNetflix programa = new ProgramaNetflix(id, titulo, showType, descricao, releaseYear,
              ageCertification, runtime, generos, productionCountries, temporadas, imdbId, imdbScore,
              imdbVotes, tmdbPopularity, tmdbScore);

          programas.add(programa);

        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Crie as árvores BST e AVL e insira os programas
    BST bst = new BST();
    AVL avl = new AVL();

    for (ProgramaNetflix programa : programas) {
      bst.insert(programa);
      avl.insert(programa);
    }

    // Realize operações com as árvores, se necessário
    avl.topCrimesAnos2000();
    System.out.println();
    avl.topWarProgramsByVotes();
    System.out.println();
    avl.topMovies2022ByScore();
    System.out.println();
    avl.topTVShowsByScore();
    System.out.println();
    avl.topOldestPrograms();

    // Inserindo um novo programa
    ProgramaNetflix programaNovo = new ProgramaNetflix(
        "tm987654",
        "Inception",
        "MOVIE",
        "A skilled thief, Cobb, is entrusted with the task of stealing secrets from a person's mind. He must navigate through the layers of reality and dive into the dreams of his targets.",
        2010,
        "PG-13",
        148,
        Arrays.asList("action", "adventure", "sci-fi"),
        Arrays.asList("US", "GB"),
        0.0,
        "tt1375666",
        8.8,
        2000000.0,
        30.5,
        8.7);

    // Inserir o novo programa nas árvores
    bst.insert(programaNovo);
    avl.insert(programaNovo);

    // Busca na BST
    Integer[] count = { 0 };

    // Realizar a busca e contar as comparações na BST
    long startTime = System.nanoTime();
    Node resultadoBuscaBST = bst.searchAndCountSteps(programaNovo, count);
    long totalTime = System.nanoTime() - startTime;

    if (resultadoBuscaBST != null) {
      System.out.println("Achou o programa");
    } else {
      System.out.println("Não achou o programa");
    }

    System.out.println("Número de comparações na BST: " + count[0]);
    System.out.println("O tempo de busca na BST é de " + totalTime + " in nano seconds");
    System.out.println();

    // Busca na AVL
    count[0] = 0;

    // Realizar a busca e contar as comparações na AVL
    startTime = System.nanoTime();
    Node resultadoBuscaAVL = avl.searchAndCountSteps(programaNovo, count);
    totalTime = System.nanoTime() - startTime;

    if (resultadoBuscaAVL != null) {
      System.out.println("Achou o programa");
    } else {
      System.out.println("Não achou o programa.");
    }

    System.out.println("Número de comparações na AVL: " + count[0]);
    System.out.println("O tempo de busca na AVL é de " + totalTime + " in nano seconds");

    // Remover o programa das árvores
    bst.remove("tm987654");
    avl.remove("tm987654");

    // Imprima novamente a altura das árvores após inserção e busca
    System.out.println("Altura da árvore BST após inserção e busca: " + bst.getHeight());
    System.out.println("Altura da árvore AVL após inserção e busca: " + avl.getHeight());

    // Escrevendo o arquivo de saida

    String novoArquivoCSV = "output.csv";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(novoArquivoCSV))) {
      // Escreve o cabeçalho no novo arquivo
      writer.write("id,titulo,showType,descricao,releaseYear,ageCertification,runtime,generos," +
          "productionCountries,temporadas,imdbId,imdbScore,imdbVotes,tmdbPopularity,tmdbScore\n");

      // Obtém os programas da árvore AVL e escreve no arquivo
      List<ProgramaNetflix> programasAVL = avl.inOrderTraversalList(); // Supondo que você tenha um método para
                                                                       // percorrer a árvore
      for (ProgramaNetflix programa : programasAVL) {
        String linha = programa.toCSVString(); // Supondo que você tenha um método na classe ProgramaNetflix para obter
                                               // uma representação CSV
        writer.write(linha + "\n");
      }

      System.out.println("Dados da AVL salvos em " + novoArquivoCSV);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
