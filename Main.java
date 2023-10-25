// Nome dos Integrantes:
// Caio Alexandre V.B. de Andrade, TIA - 32229690.
// Diego Oliveira Aluizio, TIA - 32247591.
// Nicolas Fernandes Melnik, TIA - 32241720.

// Referências:
// Lab1c feito por nós.
// Material do moodle sobre Herança. Disponível em: https://graduacao.mackenzie.br/mod/resource/view.php?id=977139.
// Material do moodle sobre árvores binárias. Disponível em: https://graduacao.mackenzie.br/mod/resource/view.php?id=986864.
// Material do moodle sobre árvore binária de busca. Disponível em: https://graduacao.mackenzie.br/mod/resource/view.php?id=993583.
// Material do moodle sobre árvore AVL. Disponível em: https://graduacao.mackenzie.br/mod/resource/view.php?id=997342.
// Algoritmos de busca, inserção e remoção de nós em uma BST. Disponíevem em: https://graduacao.mackenzie.br/mod/assign/view.php?id=995146.
// How to print binary tree diagram in Java. Disponível em: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java.

class Main {
  public static void main(String[] args) {

    System.out.println("a) Inserir, nessa ordem, os nós com chaves 1, 2 e 3:");
    AVL avl1 = new AVL();

    // Adicione elementos à árvore conforme necessário.
    avl1.insert(1);
    avl1.insert(2);
    avl1.insert(3);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl1);
    Node.printTreeInfo(avl1);

    System.out.println("\nb) Inserir, nessa ordem, os nós com chaves 3, 2 e 1:");
    AVL avl2 = new AVL();

    // Adicione elementos à árvore conforme necessário.
    avl2.insert(3);
    avl2.insert(2);
    avl2.insert(1);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl2);
    Node.printTreeInfo(avl2);

    System.out.println("\nc) Inserir, nessa ordem, os nós com chaves 3, 1 e 2:");
    AVL avl3 = new AVL();

    // Adicione elementos à árvore conforme necessário.
    avl3.insert(3);
    avl3.insert(1);
    avl3.insert(2);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl3);
    Node.printTreeInfo(avl3);

    System.out.println("\nd) Inserir, nessa ordem, os nós com chaves 1, 3 e 2:");
    AVL avl4 = new AVL();

    // Adicione elementos à árvore conforme necessário.
    avl4.insert(1);
    avl4.insert(3);
    avl4.insert(2);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl4);
    Node.printTreeInfo(avl4);

    System.out.println("\ne) Inserir, nessa ordem, os nós com chaves 5, 4, 3, 1, 2, 6, 7, 9 e 8:");
    AVL avl5 = new AVL();

    // Adicione elementos à árvore conforme necessário.
    avl5.insert(5);
    avl5.insert(4);
    avl5.insert(3);
    avl5.insert(1);
    avl5.insert(2);
    avl5.insert(6);
    avl5.insert(7);
    avl5.insert(9);
    avl5.insert(8);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl5);
    Node.printTreeInfo(avl5);

    System.out.println("\nf) Remover o nó 4 da árvore do item (e):");

    // Removendo nó 4 da árvore do item (e).
    avl5.remove(4);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl5);
    Node.printTreeInfo(avl5);

    System.out.println("\ng) Remover o nó 5 da árvore do item (f):");

    // Removendo nó 5 da árvore do item (f).
    avl5.remove(5);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl5);
    Node.printTreeInfo(avl5);

    System.out.println("\nh) Remover o nó 1 da árvore do item (g):");

    // Removendo o nó 1 da árvore do item (g).
    avl5.remove(1);

    System.out.println("\nExibindo dados da árvore:");
    System.out.println(avl5);
    Node.printTreeInfo(avl5);
  }
}