// Nome dos Integrantes:
// Caio Alexandre V.B. de Andrade, TIA - 32229690.
// Diego Oliveira Aluizio, TIA - 32247591.
// Nicolas Fernandes Melnik, TIA - 32241720.

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

	@Override
	public String toString() {
		if (data != null) {
			return "Programa buscado: " + data.getTitulo();
		} else {
			return "";
		}
	}
}