package problems;

import static util.Util.swap;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer result = null;
		quickSort(array, 0, array.length-1);

		if (isValid(array)) {

			Integer index = recursiveFloorBinarySearch(array, x, 0, array.length-1);

			if (index!=-1) {

				result = array[index];
			}
		}

		return result;
	}

	private static Integer recursiveFloorBinarySearch(Integer[] a, Integer x, int start, int end) {
		Integer result = -1;
		Integer middle = (start + end) / 2;

		if (start <= end) {

			if (x!=a[middle]) {

				if (x<a[middle]) {
					result = recursiveFloorBinarySearch(a, x, start, middle-1);
				} else {
					result = recursiveFloorBinarySearch(a, x, middle+1, end);
				}
			} else {

				result = middle;
			}
		} else {

			if (middle>=0 && middle==end) {

				result = middle;
			}
		}

		return result;
	}

	public void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex<rightIndex) {

			int median = defineMedian(array, leftIndex, rightIndex);

			swap(array, median, rightIndex-1);

			int pivotIndex = partition(array, leftIndex, rightIndex);

			quickSort(array, leftIndex, pivotIndex-1);
			quickSort(array, pivotIndex+1, rightIndex);

		}
	}

	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		Integer pivot = array[leftIndex];
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

	private int defineMedian(Integer[] array, int leftIndex, int rightIndex) {

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

	private boolean isValid(Integer[] array) {
		boolean result = true;

		if (array.length<1 || array==null) {
			result = false;
		}

		return result;
	}

}
