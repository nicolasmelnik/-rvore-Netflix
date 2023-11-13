import java.io.BufferedReader;
import java.io.FileReader;
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
        // String[] partes = linha.split(",");
        String[] partes = sb.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        double temporadas;
        if (partes[2].equalsIgnoreCase("MOVIE")) {
          temporadas = 0; // Set temporadas to 0 for movies
          partes[9] = "0";
        } else {
          temporadas = Double.parseDouble(partes[9]);
        }

        if (!containsEmpty(partes) && partes.length == 15) {
          int id = Integer.parseInt(removerNaoNumericos(partes[0]));// Tem letra no ID não da pra converter

          String titulo = partes[1];
          String showType = partes[2];
          String descricao = partes[3];
          int releaseYear = Integer.parseInt(partes[4]);
          String ageCertification = partes[5];
          int runtime = Integer.parseInt(partes[6]);

          String partes7Tratada = partes[7].substring(1, partes[7].length() - 1).replaceAll("\\s+", "");
          List<String> generos = Arrays.asList(partes7Tratada.split(","));

          String partes8Tratada = partes[8].substring(1, partes[8].length() - 1).replaceAll("\\s+", "");
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

    // Agora você tem a lista de programas Netflix com atributos preenchidos

    // Crie as árvores BST e AVL e insira os programas
    BST bst = new BST();
    AVL avl = new AVL();

    for (ProgramaNetflix programa : programas) {
      bst.insert(programa);
      avl.insert(programa);
    }

    // Realize operações com as árvores, se necessário

    // Lembre-se de detalhar no relatório qualquer modificação no dataset.
  }
}
