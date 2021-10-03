package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivotNode = (BSTNode<T>) node.getRight();
		BSTNode<T> leftPivot = (BSTNode<T>) pivotNode.getLeft();
		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

		if (parentNode != null) {
			if (parentNode.getLeft().equals(node)) {
				parentNode.setLeft(pivotNode);
			} else {
				parentNode.setRight(pivotNode);
			}
		}

		pivotNode.setParent(parentNode);
		node.setParent(pivotNode);
		node.setRight(leftPivot);
		pivotNode.setLeft(node);

		if (leftPivot != null) {
			leftPivot.setParent(node);
		}

		return pivotNode;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivotNode = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightPivot = (BSTNode<T>) pivotNode.getRight();
		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

		if (parentNode != null) {
			if (parentNode.getLeft().equals(node)) {
				parentNode.setLeft(pivotNode);
			} else {
				parentNode.setRight(pivotNode);
			}
		}

		pivotNode.setParent(parentNode);
		node.setParent(pivotNode);
		node.setLeft(rightPivot);
		pivotNode.setRight(node);

		if (rightPivot != null) {
			rightPivot.setParent(node);
		}

		return pivotNode;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
