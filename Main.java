// Nome dos Integrantes:
// Caio Alexandre V.B. de Andrade, TIA - 32229690.
// Diego Oliveira Aluizio, TIA - 32247591.
// Nicolas Fernandes Melnik, TIA - 32241720.

// Referências:
// Maneiras de medir o tempo em Java sem bibliotecas externas. Disponível em: https://thiagovespa.com.br/blog/2015/09/29/maneiras-de-medir-o-tempo-em-java-sem-bibliotecas-externas/.
// Remove all non-alphanumeric characters from a String in Java. Disponível em: https://www.techiedelight.com/remove-non-alphanumeric-characters-from-string-java/#:~:text=Remove%20all%20non-alphanumeric%20characters%20from%20a%20String%20in,equivalent%20to%20%5B%5Ea-zA-Z_0-9%5D.%20...%202%202.%20Using%20Guava.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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

    Scanner sc = new Scanner(System.in);
    int selection = 0;
    BST bst = new BST();
    AVL avl = new AVL();

    while (selection != 8) {
      System.out.println("------------------------------ MENU ------------------------------");
      System.out.println("1. Ler dados de arquivo");
      System.out.println("2. Consultas realizadas");
      System.out.println("3. Inserir programa");
      System.out.println("4. Buscar programa");
      System.out.println("5. Remover programa");
      System.out.println("6. Exibir altura das árvores");
      System.out.println("7. Salvar dados em arquivos");
      System.out.println("8. Encerrar a aplicação");
      System.out.print("\nDigite uma opção do menu: ");
      try {
        selection = sc.nextInt();
        sc.nextLine();
      } catch (Exception e) {
        System.out.println("Seleção inválida!\n");
        sc.nextLine();
        continue;
      }
      if (selection < 1 || selection > 8) {
        System.out.println("Seleção inválida!\n");
        continue;
      }
      if (selection == 1) {
        // Ler o nome do arquivo de dados do usuário
        System.out.print("Digite o nome do arquivo de dados: ");
        String nomeArquivo = sc.nextLine();

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

              bst.insert(programa);
              avl.insert(programa);
            }
          }
          System.out.println("Arquivo lido com sucesso!\n");
        } catch (FileNotFoundException e) {
          System.out.println("Arquivo não encontrado. Verifique o caminho e o nome do arquivo e tente novamente.\n");
        } catch (IOException e) {
          System.out.println("Erro não esperado! Tente novamente.\n");
        }
      } else if (selection == 2) {
        if (avl.isEmpty() || bst.isEmpty()) {
          System.out.println("Antes de visualizar as consultas, realize a leitura do arquivo.\n");
        } else {
          avl.topCrimesAnos2000();
          avl.topWarProgramsByVotes();
          avl.topMovies2022ByScore();
          avl.topTVShowsByScore();
          avl.topOldestPrograms();
        }
      } else if (selection == 3) {
        if (avl.isEmpty() || bst.isEmpty()) {
          System.out.println("Antes de inserir um novo programa, realize a leitura do arquivo.\n");
        } else {
          // Código para inserir um novo programa
          System.out.print("Informe o ID: ");
          String id = sc.nextLine();

          System.out.print("Informe o título: ");
          String titulo = sc.nextLine();

          System.out.print("Informe o tipo (MOVIE/SHOW): ");
          String tipo = sc.nextLine();

          System.out.print("Informe a descrição: ");
          String descricao = sc.nextLine();

          System.out.print("Informe o ano de lançamento: ");
          int anoLancamento = sc.nextInt();

          System.out.print("Informe a classificação etária: ");
          String classificacaoEtaria = sc.next();

          System.out.print("Informe a duração em minutos: ");
          int duracao = sc.nextInt();

          System.out.print("Informe os gêneros (separados por vírgula): ");
          sc.nextLine(); // Consumir a quebra de linha pendente
          String[] generosArray = sc.nextLine().split(",");
          List<String> generos = Arrays.asList(generosArray);

          System.out.print("Informe os países de produção (separados por vírgula): ");
          String[] paisesArray = sc.nextLine().split(",");
          List<String> paises = Arrays.asList(paisesArray);

          int temporadas = 0;
          if (tipo == "MOVIE") {
            System.out.print("Informe o número de temporadas: ");
            temporadas = sc.nextInt();
          }

          System.out.print("Informe o ID IMDb: ");
          String idImdb = sc.next();

          System.out.print("Informe a pontuação IMDb: ");
          float pontuacaoImdb = sc.nextFloat();

          System.out.print("Informe os votos no IMDB: ");
          double imdb_votes = sc.nextDouble();

          System.out.print("Informe a popularidade no TMDB: ");
          float tmdb_popularity = sc.nextFloat();

          System.out.print("Informe a pontuação no TMDB: ");
          float tmdb_score = sc.nextFloat();

          System.out.println();

          ProgramaNetflix programaNovo = new ProgramaNetflix(
              id, titulo, tipo, descricao, anoLancamento, classificacaoEtaria, duracao,
              generos, paises, temporadas, idImdb, pontuacaoImdb, imdb_votes, tmdb_popularity,
              tmdb_score);

          // Inserir o novo programa nas árvores
          bst.insert(programaNovo);
          avl.insert(programaNovo);
        }
      } else if (selection == 4) {
        if (avl.isEmpty() || bst.isEmpty()) {
          System.out.println("Antes de buscar um programa, realize a leitura do arquivo.\n");
        } else {
          System.out.print("Informe o ID que deseja buscar: ");
          String id_buscado = sc.nextLine();
          // Busca na BST
          Integer[] count = { 0 };
          long startTime = System.nanoTime();
          bst.searchAndCountSteps(id_buscado, count);
          long totalTime = System.nanoTime() - startTime;

          System.out.println("\nNúmero de comparações na BST: " + count[0]);
          System.out.println("O tempo de busca na BST é de " + totalTime + " em nano segundos.");
          System.out.println();

          // Busca na AVL
          count[0] = 0;
          startTime = System.nanoTime();
          Node resultadoBuscaAVL = avl.searchAndCountSteps(id_buscado, count);
          totalTime = System.nanoTime() - startTime;
          System.out.println("Resultado da Busca na AVL: " + resultadoBuscaAVL);

          System.out.println("Número de comparações na AVL: " + count[0]);
          System.out.println("O tempo de busca na AVL é de " + totalTime + " em nano segundos.\n");
        }
      } else if (selection == 5) {
        if (avl.isEmpty() || bst.isEmpty()) {
          System.out.println("Antes de remover um programa, realize a leitura do arquivo.\n");
        } else {
          System.out.print("Informe o ID que deseja remover: ");
          String id_remover = sc.nextLine();

          // Remover o programa das árvores
          bst.remove(id_remover);
          avl.remove(id_remover);
        }

      } else if (selection == 6) {
        if (avl.isEmpty() || bst.isEmpty()) {
          System.out.println("Antes de ver as alturas, realize a leitura do arquivo.\n");
        } else {
          // Imprima novamente a altura das árvores após inserção e busca
          System.out.println("\nAltura da árvore BST: " + bst.getHeight());
          System.out.println("Altura da árvore AVL: " + avl.getHeight());
          System.out.println();
        }
      } else if (selection == 7) {
        if (avl.isEmpty() || bst.isEmpty()) {
          System.out.println("Não é possível salvar! Primeiro leia o arquivo.\n");
        } else {
          // Escrevendo o arquivo de saida
          System.out.print("Digite o nome do arquivo de gravação: ");
          String novoArquivoCSV = sc.nextLine();

          try (BufferedWriter writer = new BufferedWriter(new FileWriter(novoArquivoCSV))) {
            // Escreve o cabeçalho no novo arquivo
            writer.write("id,titulo,showType,descricao,releaseYear,ageCertification,runtime,generos," +
                "productionCountries,temporadas,imdbId,imdbScore,imdbVotes,tmdbPopularity,tmdbScore\n");

            // Obtém os programas da árvore AVL e escreve no arquivo
            List<ProgramaNetflix> programasAVL = avl.inOrderTraversalList();
            for (ProgramaNetflix programa : programasAVL) {
              String linha = programa.toCSVString();
              writer.write(linha + "\n");
            }
            System.out.println("Dados da AVL salvos em: " + novoArquivoCSV);
            System.out.println();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    bst = null;
    avl = null;
    System.gc();
    System.out.println("Fim!");
    sc.close();
  }
}
