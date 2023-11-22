// Nome dos Integrantes:
// Caio Alexandre V.B. de Andrade, TIA - 32229690.
// Diego Oliveira Aluizio, TIA - 32247591.
// Nicolas Fernandes Melnik, TIA - 32241720.

public class BST extends BinaryTree {

    public BST() {
        super();
    }

    public BST(Node root) {
        super(root);
    }

    public Node search(ProgramaNetflix programa) {
        return search(root, programa);
    }

    private Node search(Node node, ProgramaNetflix programa) {
        if (node == null) {
            System.out.println("Nó não foi encontrado!");
            return null;
        }

        int compareResult = programa.getId().compareTo(node.getData().getId());

        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return search(node.getLeft(), programa);
        } else {
            return search(node.getRight(), programa);
        }
    }

    public Node searchAndCountSteps(String Id, Integer[] count) {
        return searchAndCountSteps(root, Id, count);
    }

    private Node searchAndCountSteps(Node node, String Id, Integer[] count) {
        if (node == null) {
            return null;
        }

        count[0]++; // Atualize o valor do contador

        int compareResult = Id.compareTo(node.getData().getId());

        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return searchAndCountSteps(node.getLeft(), Id, count);
        } else {
            return searchAndCountSteps(node.getRight(), Id, count);
        }
    }

    public void insert(ProgramaNetflix programa) {
        root = insert(root, programa, null);
    }

    private Node insert(Node node, ProgramaNetflix programa, Node parent) {
        if (node == null) {
            return new Node(programa, parent, null, null);
        }
        int compareResult = programa.getId().compareTo(node.getData().getId());
        if (compareResult < 0) {
            parent = node;
            node.setLeft(insert(node.getLeft(), programa, parent));
        } else if (compareResult > 0) {
            parent = node;
            node.setRight(insert(node.getRight(), programa, parent));
        } else {
            System.out.println("Não é possível inserir pois a chave '" + programa.getId() + "' já existe!");
        }

        return node;
    }

    public void remove(String programaId) {
        root = remove(root, programaId);
    }

    private Node remove(Node node, String programaId) {
        if (node == null) {
            System.out.println("\nPrograma a ser removido não encontrado na BST!");
            return null;
        }
        int compareResult = programaId.compareTo(node.getData().getId());
        if (compareResult < 0) {
            node.setLeft(remove(node.getLeft(), programaId));
        } else if (compareResult > 0) {
            node.setRight(remove(node.getRight(), programaId));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            Node predecessor = findPredecessor(node.getData());
            node.setData(predecessor.getData());
            node.setLeft(remove(node.getLeft(), predecessor.getData().getId()));
            System.out.println("\nPrograma removido da BST com sucesso!");
        }
        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }
        return node;
    }

    public Node findPredecessor(ProgramaNetflix programa) {
        Node node = search(programa);
        if (node == null) {
            return null;
        }

        if (node.getLeft() != null) {
            return findMax(node.getLeft());
        }

        Node predecessor = null;
        Node current = root;
        while (current != null) {
            int compareResult = programa.getId().compareTo(current.getData().getId());
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

    public Node findSuccessor(ProgramaNetflix programa) {
        Node node = search(programa);
        if (node == null) {
            return null;
        }

        if (node.getRight() != null) {
            return findMin(node.getRight());
        }

        Node successor = null;
        Node current = root;
        while (current != null) {
            int compareResult = programa.getId().compareTo(current.getData().getId());
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
}
