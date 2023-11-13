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
            // Use os getters do objeto ProgramaNetflix
            sb.append(node.getData() != null ? node.getData().getId() : "<null>");
            sb.append(position); // Mostra se o nó é esquerdo ou direito
            sb.append("\n");

            String newPrefix = prefix + (isRight ? " |      " : "        ");
            printTree(node.getRight(), newPrefix, true, " (D)", sb);
            printTree(node.getLeft(), newPrefix, false, " (E)", sb);
        }
    }
}
