package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex<rightIndex && arrayLengthIsValid(array, leftIndex, rightIndex) && indexesAreValid(array, leftIndex, rightIndex)) {
			int middleIndex = (leftIndex+rightIndex)/2;

			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex+1, rightIndex);

			merge(array, leftIndex, middleIndex, rightIndex);

		} else {
			return;
		}
	}

	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		T[] aux = (T[]) new Comparable[array.length];
		for (int i = leftIndex; i <= rightIndex; i++) {
			aux[i] = array[i];
		}

		int i = leftIndex;
		int j = middleIndex+1;
		int k = leftIndex;

		while (i<=middleIndex && j<=rightIndex) {
			if (aux[i].compareTo(aux[j])<0) {
				array[k++] = aux[i++];
			} else {
				array[k++] = aux[j++];
			}
		}

		while (i<=middleIndex) {
			array[k++] = aux[i++];
		}

		while (j<=rightIndex) {
			array[k++] = aux[j++];
		}
	}

	private boolean arrayLengthIsValid(T[] array, int leftIndex, int rightIndex) {
		boolean result = true;

		if (array.length<=1) {
			result = false;
		}

		return result;
	}

	private boolean indexesAreValid(T[] array, int leftIndex, int rightIndex) {
		boolean result = true;

		if (leftIndex>rightIndex) {
			result = false;
		} else if (leftIndex<0 || leftIndex>=array.length) {
			result = false;
		} else if (rightIndex<0 || rightIndex>=array.length) {
			result = false;
		}

		return result;
	}
}
