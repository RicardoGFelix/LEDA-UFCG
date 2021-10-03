package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = null;
		BSTImpl<Integer> bst = new BSTImpl<Integer>();

		if (array.length>0) {

			int minorElement = -1;

			for (Integer integer : array) {
				if (integer<=minorElement) {
					minorElement=integer;
				}
				bst.insert(integer);
			}

			result = floor(bst.getRoot(), numero, minorElement);

		}

		return result;
	}

	private Integer floor(BSTNode<Integer> node, double numero, int minorElement) {
		Integer result = null;

		if (node!=null && !node.isEmpty()) {
			if (node.getData()==numero) {
				result = node.getData();
			} else if (node.getData()>numero) {
				result = floor((BSTNode<Integer>) node.getLeft(), numero, minorElement);
			} else {

				Integer floor = floor((BSTNode<Integer>) node.getRight(), numero, minorElement);

				if (floor==null) {
					if (node.getData()>=minorElement) {
						result = node.getData();
					} else {
						result = floor;
					}
				}else {
					result = floor;
				}
			}

		}

		return result;
	}



	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = null;
		BSTImpl<Integer> bst = new BSTImpl<Integer>();

		if (array.length>0) {

			for (Integer integer : array) {
				bst.insert(integer);
			}

			result = ceil(bst.getRoot(), numero);
		}

		return result;
	}

	private Integer ceil(BSTNode<Integer> node, double numero) {
		Integer result = null;

		if (node!=null && !node.isEmpty()) {

			if (node.getData()==numero) {
				result = node.getData();
			} else if (node.getData()<numero) {
				result = ceil((BSTNode<Integer>) node.getRight(), numero);
			} else {
				Integer ceil = ceil((BSTNode<Integer>) node.getLeft(), numero);

				if (ceil!=null) {

					if (ceil>=numero) {
						result = ceil;
					} else {
						result = node.getData();
					}

				} else {
					result = node.getData();
				}
			}

		}

		return result;
	}
}