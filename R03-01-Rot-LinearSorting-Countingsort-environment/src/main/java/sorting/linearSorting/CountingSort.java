package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (arrayLengthIsValid(array, leftIndex, rightIndex) && indexesAreValid(array, leftIndex, rightIndex)) {

			int maxValue = findMaxValue(array, leftIndex, rightIndex);

			countingSort(array, maxValue, leftIndex, rightIndex);

		} else {

			return;
		}
	}

	private void countingSort(Integer[] array, int maxValue, int leftIndex, int rightIndex) {
		Integer[] frequencyArray = new Integer[maxValue+1];
		Arrays.fill(frequencyArray, 0);

		for (int i = leftIndex; i<=rightIndex; i++) {
			frequencyArray[array[i]]++;
		}

		for (int i = 0; i<frequencyArray.length-1; i++) {
			frequencyArray[i+1] += frequencyArray[i];
		}

		Integer[] result = new Integer[rightIndex-leftIndex+1];

		for (int i = rightIndex; i>=leftIndex; i--) {
			result[--frequencyArray[array[i]]] = array[i];
		}

		for (int i = leftIndex; i<=rightIndex; i++) {
			array[i] = result[i-leftIndex];
		}
	}

	private int findMaxValue(Integer[] array, int leftIndex, int rightIndex) {
		int result = array[leftIndex];

		for (int i = leftIndex+1; i<=rightIndex; i++) {
			if (array[i]>result) {
				result = array[i];
			}
		}

		return result;
	}

	private boolean arrayLengthIsValid(Integer[] array, int leftIndex, int rightIndex) {
		boolean result = true;

		if (array.length<=1) {
			result = false;
		}

		return result;
	}

	private boolean indexesAreValid(Integer[] array, int leftIndex, int rightIndex) {
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
