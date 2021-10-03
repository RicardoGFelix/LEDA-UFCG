package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (arrayLengthIsValid(array, leftIndex, rightIndex) && indexesAreValid(array, leftIndex, rightIndex)) {

			Integer minValue = findMinValue(array, leftIndex, rightIndex);
			Integer maxValue = findMaxValue(array, leftIndex, rightIndex);

			countingSort(array, minValue, maxValue, leftIndex, rightIndex);

		} else {

			return;
		}
	}

	private void countingSort(Integer[] array, int minValue, int maxValue, int leftIndex, int rightIndex) {
		Integer[] frequencyArray = new Integer[maxValue-minValue+1];
		Arrays.fill(frequencyArray, 0);

		for (int i = leftIndex; i<=rightIndex; i++) {
			frequencyArray[array[i]-minValue]++;
		}

		for (int i = 0; i<frequencyArray.length-1; i++) {
			frequencyArray[i+1] += frequencyArray[i];
		}

		Integer[] result = new Integer[rightIndex-leftIndex+1];

		for (int i = rightIndex; i>=leftIndex; i--) {
			result[--frequencyArray[array[i]-minValue]] = array[i];
		}

		for (int i = leftIndex; i<=rightIndex; i++) {
			array[i] = result[i-leftIndex];
		}
	}

	private int findMinValue(Integer[] array, int leftIndex, int rightIndex) {
		int result = array[leftIndex];

		for (int i = leftIndex+1; i<=rightIndex; i++) {
			if (array[i]<result) {
				result = array[i];
			}
		}

		return result;
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