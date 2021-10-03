package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		boolean result = true;

		if (!bst.isEmpty()) {
			result = this.isBST(bst.getRoot());
		}

		return result;
	}

	private boolean isBST(BSTNode<T> node) {
		boolean result = true;

		if (node.getLeft()!=null && node.getRight()!=null) {

			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			BSTNode<T> right = (BSTNode<T>) node.getRight();

			if (!left.isEmpty() && !right.isEmpty()) {

				if (node.getData().compareTo(left.getData())<0 || node.getData().compareTo(right.getData())>0) {
					result = false;
				} else {
					result = isBST(left);

					if (result==true) {
						result = isBST(right);
					}
				}

			} else if (!left.isEmpty() && right.isEmpty()) {

				if (node.getData().compareTo(left.getData())<0) {
					result = false;
				} else {
					result = isBST(left);
				}

			} else if (left.isEmpty() && !right.isEmpty()) {

				if (node.getData().compareTo(right.getData())>0) {
					result = false;
				} else {
					result = isBST(right);
				}
			}
		}

		return result;
	}
	
}
