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
        // Implementação da rotação à esquerda para objetos ProgramaNetflix
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
        // Implementação da rotação à direita para objetos ProgramaNetflix
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
        // Implementação da rotação esquerda-direita para objetos ProgramaNetflix
        root.setLeft(rotateLeft(root.getLeft()));
        return rotateRight(root);
    }

    public Node rotateRightLeft() {
        return rotateRightLeft(root);
    }

    private Node rotateRightLeft(Node root) {
        // Implementação da rotação direita-esquerda para objetos ProgramaNetflix
        root.setRight(rotateRight(root.getRight()));
        return rotateLeft(root);
    }

    public void balanceNode(Node node) {
        // Implementação para objetos ProgramaNetflix
        node.updateBalanceFactor();
        int balanceFactor = node.getBalanceFactor();

        if (balanceFactor > 1) {
            if (node.getRight().getBalanceFactor() < 0) {
                rotateRightLeft(node);
            } else {
                rotateLeft(node);
            }
        } else if (balanceFactor < -1) {
            if (node.getLeft().getBalanceFactor() > 0) {
                rotateLeftRight(node);
            } else {
                rotateRight(node);
            }
        }
        if (node.getParent() != null) {
            balanceNode(node.getParent());
        } else {
            this.root = node;
        }
    }

    public void insert(ProgramaNetflix programa) {
        // Implementação para objetos ProgramaNetflix
        Node node = new Node(programa);
        insert(this.root, node);
    }

    private void insert(Node auxiliar, Node node) {
        // Implementação para objetos ProgramaNetflix
        if (auxiliar == null) {
            this.root = node;
        } else {
            int compareResult = Integer.compare(node.getData().getId(), auxiliar.getData().getId());
            if (compareResult < 0) {
                if (auxiliar.getLeft() == null) {
                    auxiliar.setLeft(node);
                    node.setParent(auxiliar);
                    balanceNode(auxiliar);
                } else {
                    insert(auxiliar.getLeft(), node);
                }
            } else if (compareResult > 0) {
                if (auxiliar.getRight() == null) {
                    auxiliar.setRight(node);
                    node.setParent(auxiliar);
                    balanceNode(auxiliar);
                } else {
                    insert(auxiliar.getRight(), node);
                }
            } else {
                System.out.println("Não é possível inserir pois a chave '" + node.getData().getId() + "' já existe!");
            }
        }
    }

    public void remove(ProgramaNetflix programa) {
        // Implementação para objetos ProgramaNetflix
        remove(this.root, programa);
    }

    private void remove(Node NodeAtual, ProgramaNetflix programa) {
        // Implementação para objetos ProgramaNetflix
        if (NodeAtual == null) {
            return;
        } else {
            int compareResult = Integer.compare(programa.getId(), NodeAtual.getData().getId());
            if (compareResult < 0) {
                remove(NodeAtual.getLeft(), programa);
            } else if (compareResult > 0) {
                remove(NodeAtual.getRight(), programa);
            } else if (compareResult == 0) {
                removerNoEncontrado(NodeAtual);
            }
        }
    }

    private void removerNoEncontrado(Node node) {
        // Implementação para objetos ProgramaNetflix
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
