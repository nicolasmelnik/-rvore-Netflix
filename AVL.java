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

public class AVL extends BST {

    public AVL() {
        super();
    }

    public AVL(Node root) {
        super(root);
    }

    public Node rotateLeft() {
        return rotateLeft(root);
    }

    private Node rotateLeft(Node root) {

        Node right = root.getRight();
        right.setParent(root.getParent());
        root.setRight(right.getLeft());

        if (root.getRight() != null) {
            root.getRight().setParent(root);
        }

        right.setLeft(root);
        root.setParent(right);

        if (right.getParent() != null) {

            if (right.getParent().getRight() == root) {
                right.getParent().setRight(right);

            } else if (right.getParent().getLeft() == root) {
                right.getParent().setLeft(right);
            }
        }

        root.updateBalanceFactor();
        right.updateBalanceFactor();
        return right;
    }

    public Node rotateRight() {
        return rotateRight(root);
    }

    private Node rotateRight(Node root) {

        Node left = root.getLeft();
        left.setParent(root.getParent());
        root.setLeft(left.getRight());

        if (root.getLeft() != null) {
            root.getLeft().setParent(root);
        }

        left.setRight(root);
        root.setParent(left);

        if (left.getParent() != null) {

            if (left.getParent().getRight() == root) {
                left.getParent().setRight(left);

            } else if (left.getParent().getLeft() == root) {
                left.getParent().setLeft(left);
            }
        }

        root.updateBalanceFactor();
        left.updateBalanceFactor();
        return left;
    }

    public Node rotateLeftRight() {
        return rotateLeftRight(root);
    }

    private Node rotateLeftRight(Node root) {
        root.setLeft(rotateLeft(root.getLeft()));
        return rotateRight(root);
    }

    public Node rotateRightLeft() {
        return rotateRightLeft(root);
    }

    private Node rotateRightLeft(Node root) {
        root.setRight(rotateRight(root.getRight()));
        return rotateLeft(root);
    }

    public void balanceNode(Node node) {
        node.updateBalanceFactor();
        int balanceFactor = node.getBalanceFactor();

        if (balanceFactor > 1) {
            // Rotação à esquerda ou rotação esquerda-direita
            if (node.getRight().getBalanceFactor() < 0) {
                // Rotação direita no filho direito e, em seguida, rotação à esquerda no nó
                // atual
                rotateRightLeft(node);
            } else {
                // Rotação à esquerda no nó atual
                rotateLeft(node);
            }
        } else if (balanceFactor < -1) {
            // Rotação à direita ou rotação direita-esquerda
            if (node.getLeft().getBalanceFactor() > 0) {
                // Rotação esquerda no filho esquerdo e, em seguida, rotação à direita no nó
                // atual
                rotateLeftRight(node);
            } else {
                // Rotação à direita no nó atual
                rotateRight(node);
            }
        }
        if (node.getParent() != null) {
            balanceNode(node.getParent());
        } else {
            this.root = node;
        }
    }

    public void insert(int data) {
        Node node = new Node(data);
        insert(this.root, node);
    }

    private void insert(Node auxiliar, Node node) {
        if (auxiliar == null) {
            this.root = node;
        } else {
            if (node.getData() < auxiliar.getData()) {
                if (auxiliar.getLeft() == null) {
                    auxiliar.setLeft(node);
                    node.setParent(auxiliar);
                    balanceNode(auxiliar);
                } else {
                    insert(auxiliar.getLeft(), node);
                }
            } else if (node.getData() > auxiliar.getData()) {
                if (auxiliar.getRight() == null) {
                    auxiliar.setRight(node);
                    node.setParent(auxiliar);
                    balanceNode(auxiliar);
                } else {
                    insert(auxiliar.getRight(), node);
                }
            } else {
                System.out.println("Não é possível inserir pois a chave '" + node.getData() + "' já existe!");
            }
        }
    }

    public void remove(int data) {
        remove(this.root, data);
    }

    private void remove(Node NodeAtual, int data) {
        if (NodeAtual == null) {
            return;
        } else {
            if (NodeAtual.getData() > data) {
                remove(NodeAtual.getLeft(), data);
            } else if (NodeAtual.getData() < data) {
                remove(NodeAtual.getRight(), data);
            } else if (NodeAtual.getData() == data) {
                removerNoEncontrado(NodeAtual);
            }
        }
    }

    private void removerNoEncontrado(Node node) {
        Node r;
        if (node.getLeft() == null || node.getRight() == null) {

            if (node.getParent() == null) {
                this.root = null;
                node = null;
                return;
            }
            r = node;

        } else {
            r = findPredecessor(node.getData());
            node.setData(r.getData());
        }

        Node p;
        if (r.getLeft() != null) {
            p = r.getLeft();
        } else {
            p = r.getRight();
        }

        if (p != null) {
            p.setParent(r.getParent());
        }

        if (r.getParent() == null) {
            this.root = p;
        } else {
            if (r == r.getParent().getLeft()) {
                r.getParent().setLeft(p);
            } else {
                r.getParent().setRight(p);
            }
            balanceNode(r.getParent());
        }
        r = null;
    }
}