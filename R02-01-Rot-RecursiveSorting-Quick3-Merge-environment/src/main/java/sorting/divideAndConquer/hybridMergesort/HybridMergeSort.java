package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;

		if (arrayLengthIsValid(array, leftIndex, rightIndex) && indexesAreValid(array, leftIndex, rightIndex)) {

			hybridMergeSort(array, leftIndex, rightIndex);

		} else {
			return;
		}
	}

	private void hybridMergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex<rightIndex) {

			if ((rightIndex-leftIndex)>SIZE_LIMIT) {

				int middleIndex = (leftIndex+rightIndex)/2;

				hybridMergeSort(array, leftIndex, middleIndex);
				hybridMergeSort(array, middleIndex+1, rightIndex);

				merge(array, leftIndex, middleIndex, rightIndex);

			} else {
				insertionSort(array, leftIndex, rightIndex);
			}

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
		MERGESORT_APPLICATIONS++;

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

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i<=rightIndex; i++) {

			int j = i;

			while (j>0 && array[j].compareTo(array[j-1])<0) {

				swap(array, j, j-1);
				j--;
			}
			INSERTIONSORT_APPLICATIONS++;
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

	public static int getMergesortApplications() {
		return MERGESORT_APPLICATIONS;
	}

	public static int getInsertionsortApplications() {
		return INSERTIONSORT_APPLICATIONS;
	}
}