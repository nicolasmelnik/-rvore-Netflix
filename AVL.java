import java.util.ArrayList;
import java.util.List;

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
        Node node = new Node(programa);
        insert(this.root, node);
    }

    private void insert(Node auxiliar, Node node) {
        if (auxiliar == null) {
            this.root = node;
        } else {
            int compareResult = node.getData().getId().compareTo(auxiliar.getData().getId());
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
        remove(this.root, programa);
    }

    private void remove(Node NodeAtual, ProgramaNetflix programa) {
        if (NodeAtual == null) {
            return;
        } else {
            int compareResult = programa.getId().compareTo(NodeAtual.getData().getId());
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

    // Consultas:

    public void topTV14CrimeTitles() {
        List<ProgramaNetflix> topTitles = new ArrayList<>();
        topTV14CrimeTitles(root, topTitles);
        displayTopTitles(topTitles, "Top 10 TV-14 Crime Titles:");
    }

    private void topTV14CrimeTitles(Node node, List<ProgramaNetflix> result) {
        if (node != null && result.size() < 10) {
            topTV14CrimeTitles(node.getRight(), result);

            ProgramaNetflix programa = node.getData();
            if (programa.getAgeCertification().equalsIgnoreCase("TV-14") && programa.getGeneros().contains("crime")) {
                System.out.println(programa.getTitulo());
                result.add(programa);
            }

            topTV14CrimeTitles(node.getLeft(), result);
        }
    }

    private void displayTopTitles(List<ProgramaNetflix> titles, String message) {
        System.out.println(message);
        for (ProgramaNetflix programa : titles) {
            System.out.println(programa.getTitulo());
        }
        System.out.println();
    }
}
