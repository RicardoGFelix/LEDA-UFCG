package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int l = leftIndex;
		int r = rightIndex;

		while (l<r) {

			for (int i = l; i < r; i++) {

				if (array[i].compareTo(array[i+1])>0) {

					swap(array, i, i+1);
				}
			}
			r--;

			for (int j = r; j>l; j--) {

				if (array[j].compareTo(array[j-1])<0) {

					swap(array, j, j-1);
				}
			}
			l++;
		}
	}
}
