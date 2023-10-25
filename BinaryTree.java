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

public class BinaryTree {

    protected Node root;

    public BinaryTree() {
        this(null);
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getDegree() {
        return getDegree(root);
    }

    private int getDegree(Node node) {
        if (node == null || node.isLeaf()) {
            return 0;
        }

        int degree = node.getDegree();

        if (node.hasLeftChild()) {
            degree = Math.max(degree, getDegree(node.getLeft()));
        }

        if (node.hasRightChild()) {
            degree = Math.max(degree, getDegree(node.getRight()));
        }

        return degree;
    }

    public int getHeight() {
        if (isEmpty()) {
            return -1;
        }

        return root.getHeight();
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    protected void inOrderTraversal(Node node) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia!");
        }
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node);
            inOrderTraversal(node.getRight());
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    protected void preOrderTraversal(Node node) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia!");
        }
        if (node != null) {
            System.out.print(node);
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    protected void postOrderTraversal(Node node) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia!");
        }
        if (node != null) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "A árvore está vazia!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nRAIZ\n");
        printTree(root, "", true, "", sb);
        return sb.toString();
    }

    private void printTree(Node node, String prefix, boolean isRight, String position, StringBuilder sb) {
        if (node != null) {
            sb.append(prefix);
            sb.append(isRight ? " /" : " \\");
            sb.append("----- ");
            // O número -9999.8956 foi escolhido so pra formatar o print, já que é bem
            // dificil aparecer um nó com exatamente esse valor
            sb.append(node.getData() != -9999.8956 ? Integer.toString(node.getData()) : "<null>");
            sb.append(position); // Mostra se o nó é esquerdo ou direito
            sb.append("\n");

            String newPrefix = prefix + (isRight ? " |      " : "        ");
            printTree(node.getRight(), newPrefix, true, " (D)", sb);
            printTree(node.getLeft(), newPrefix, false, " (E)", sb);
        }
    }
}