package adt.avltree;

import adt.bst.BSTNode;

import java.util.*;

import static adt.bt.Util.leftRotation;
import static adt.bt.Util.rightRotation;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array!=null) {

			Arrays.sort(array);

			Map<Integer, List<T>> levels = new TreeMap<>();

			this.organizesLevelMap(levels, 0, array.length-1, 0, array);

			for (List<T> level : levels.values()) {

				for (T element : level) {
					super.insert(element);
				}
			}
		}
	}

	private void organizesLevelMap(Map<Integer, List<T>> map, int leftIndex, int rightIndex, int level, T[] array) {

		if (leftIndex<=rightIndex) {

			int middleIndex = (leftIndex+rightIndex)/2;

			if (!map.containsKey(level)) {
				map.put(level, new ArrayList<>());
			}

			map.get(level).add(array[middleIndex]);

			organizesLevelMap(map, leftIndex, middleIndex-1, level+1, array);
			organizesLevelMap(map, middleIndex+1, rightIndex, level+1, array);
		}
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		BSTNode<T> subtreeRoot;

		if (balance > 1) {

			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				subtreeRoot = rightRotation(node);
				this.LLcounter++;
			} else {
				leftRotation((BSTNode<T>) node.getLeft());
				subtreeRoot = rightRotation(node);
				this.LRcounter++;
			}

			if (subtreeRoot.getParent() == null) {
				super.root = subtreeRoot;
			}

		} else if (balance < -1) {

			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				subtreeRoot = leftRotation(node);
				this.RRcounter++;
			} else {
				rightRotation((BSTNode<T>) node.getRight());
				subtreeRoot = leftRotation(node);
				this.RLcounter++;
			}

			if (subtreeRoot.getParent() == null) {
				super.root = subtreeRoot;
			}
		}
	}
}
