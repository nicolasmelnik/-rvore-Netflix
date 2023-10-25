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

public class BST extends BinaryTree {

    public BST() {
        super();
    }

    public BST(Node root) {
        super(root);
    }

    public Node search(int data) {
        return search(root, data);
    }

    private Node search(Node node, int data) {
        if (node == null) {
            System.out.println("Nó não foi encontrado!");
            return null;
        }

        int compareResult = Integer.compare(data, node.getData());

        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return search(node.getLeft(), data);
        } else {
            return search(node.getRight(), data);
        }
    }

    public void insert(int data) {
        root = insert(root, data, null);
    }

    private Node insert(Node node, int data, Node parent) {
        if (node == null) {
            return new Node(data, parent, null, null);
        }
        int compareResult = Integer.compare(data, node.getData());
        if (compareResult < 0) {
            parent = node;
            node.setLeft(insert(node.getLeft(), data, parent));
        } else if (compareResult > 0) {
            parent = node;
            node.setRight(insert(node.getRight(), data, parent));
        } else {
            System.out.println("Não é possível inserir pois a chave '" + data + "' já existe!");
        }

        return node;
    }

    public void remove(int data) {
        root = remove(root, data);
    }

    private Node remove(Node node, int data) {
        if (node == null) {
            System.out.println("Nó a ser removido não encontrado!");
            return node;
        }
        int compareResult = Integer.compare(data, node.getData());
        if (compareResult < 0) {
            node.setLeft(remove(node.getLeft(), data));
        } else if (compareResult > 0) {
            node.setRight(remove(node.getRight(), data));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            Node predecessor = findPredecessor(node.getData());
            node.setData(predecessor.getData());
            node.setLeft(remove(node.getLeft(), predecessor.getData()));
        }
        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }
        return node;
    }

    public Node findMin() {
        return findMin(root);
    }

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    public Node findMax() {
        return findMax(root);
    }

    private Node findMax(Node node) {
        if (node == null) {
            return null;
        }

        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node;
    }

    public Node findPredecessor(int data) {
        Node node = search(data);
        if (node == null) {
            return null;
        }

        if (node.getLeft() != null) {
            return findMax(node.getLeft());
        }

        Node predecessor = null;
        Node current = root;
        while (current != null) {
            int compareResult = Integer.compare(data, current.getData());
            if (compareResult < 0) {
                current = current.getLeft();
            } else if (compareResult > 0) {
                predecessor = current;
                current = current.getRight();
            } else {
                break;
            }
        }

        return predecessor;
    }

    public Node findSuccessor(int data) {
        Node node = search(data);
        if (node == null) {
            return null;
        }

        if (node.getRight() != null) {
            return findMin(node.getRight());
        }

        Node successor = null;
        Node current = root;
        while (current != null) {
            int compareResult = Integer.compare(data, current.getData());
            if (compareResult > 0) {
                current = current.getRight();
            } else if (compareResult < 0) {
                successor = current;
                current = current.getLeft();
            } else {
                break;
            }
        }

        return successor;
    }

    public void clear() {
        root = clear(root);
        System.out.println("Clear executado com sucesso!!!");
    }

    private Node clear(Node node) {
        if (node == null) {
            return null;
        }

        // Pós-ordem = percurso LRN.
        node.setLeft(clear(node.getLeft()));
        node.setRight(clear(node.getRight()));
        node.setParent(null);

        return null;
    }
}