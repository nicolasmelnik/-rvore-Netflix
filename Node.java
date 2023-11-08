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

public class Node {

	private ProgramaNetflix data;
	private Node parent;
	private Node left;
	private Node right;
	private int balanceFactor;

	public Node() {
		data = null;
		balanceFactor = 0;
	}

	public Node(ProgramaNetflix data) {
		this.data = data;
	}

	public Node(ProgramaNetflix data, Node parent) {
		this.data = data;
		this.parent = parent;
		balanceFactor = 0;
	}

	public Node(ProgramaNetflix data, Node parent, Node left, Node right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
		balanceFactor = 0;
	}

	public ProgramaNetflix getData() {
		return data;
	}

	public void setData(ProgramaNetflix data) {
		this.data = data;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;

		if (this.left != null) {
			this.left.setParent(this);
		}
		updateBalanceFactor();
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;

		if (this.right != null) {
			this.right.setParent(this);
		}
		updateBalanceFactor();
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	protected void updateBalanceFactor() {
		int leftHeight = hasLeftChild() ? left.getHeight() + 1 : 0;
		int rightHeight = hasRightChild() ? right.getHeight() + 1 : 0;
		balanceFactor = rightHeight - leftHeight;
	}

	public boolean hasLeftChild() {
		return left != null;
	}

	public boolean hasRightChild() {
		return right != null;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

	public int getDegree() {
		int degree = 0;

		if (hasLeftChild()) {
			++degree;
		}

		if (hasRightChild()) {
			++degree;
		}

		return degree;
	}

	public int getLevel() {
		if (isRoot()) {
			return 0;
		}

		return parent.getLevel() + 1;
	}

	public int getHeight() {
		if (isLeaf()) {
			return 0;
		}

		int height = 0;

		if (hasLeftChild()) {
			height = Math.max(height, left.getHeight());
		}

		if (hasRightChild()) {
			height = Math.max(height, right.getHeight());
		}

		return height + 1;
	}

	public static void printAllNodeInfo(Node node) {
		if (node != null) {
			System.out.print("Node - [data = " + node.getData() +
					", parent = " + (node.getParent() != null ? node.getParent().getData() : "null") +
					", left = " + (node.getLeft() != null ? node.getLeft().getData() : "null") +
					", right = " + (node.getRight() != null ? node.getRight().getData() : "null") +
					", raiz = " + node.isRoot() +
					", folha = " + node.isLeaf() +
					", grau = " + node.getDegree() +
					", nível = " + node.getLevel() +
					", altura = " + node.getHeight() +
					", fb = " + node.getBalanceFactor() + "]\n");

			printAllNodeInfo(node.getLeft());
			printAllNodeInfo(node.getRight());
		}
	}

	// Para usar o método, você pode chamá-lo a partir da raiz da árvore:
	public static void printTreeInfo(BinaryTree tree) {
		printAllNodeInfo(tree.getRoot());
	}
}