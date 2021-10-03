package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;

import java.util.Arrays;

import static util.Util.swap;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex<rightIndex && arrayLengthIsValid(array, leftIndex, rightIndex) && indexesAreValid(array, leftIndex, rightIndex)) {

			int median = defineMedian(array, leftIndex, rightIndex);

			swap(array, median, rightIndex-1);

			int pivotIndex = partition(array, leftIndex, rightIndex);

			sort(array, leftIndex, pivotIndex-1);
			sort(array, pivotIndex+1, rightIndex);

		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;

		for (int j = leftIndex+1; j<=rightIndex; j++) {
			if (array[j].compareTo(pivot)<0) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, leftIndex, i);
		return i;
	}

	private int defineMedian(T[] array, int leftIndex, int rightIndex) {

		int median = (leftIndex+rightIndex)/2;

		if (array[rightIndex].compareTo(array[leftIndex])<0) {
			swap(array, rightIndex, leftIndex);
		}
		if (array[rightIndex].compareTo(array[median])<0) {
			swap(array, rightIndex, median);
		}
		if (array[median].compareTo(array[leftIndex])<0) {
			swap(array, leftIndex, median);
		}


		return median;
	}

	//private void sortThreeElements()

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
