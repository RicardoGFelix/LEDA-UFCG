package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = null;
		HeapImpl<Integer> heap = new HeapImpl<Integer>(super.comparator);


		if (array!=null && array.length>0) {

			int aux = array[0];

			for (Integer integer : array) {
				heap.insert(integer);

				if (integer<aux) {
					aux = integer;
				}
			}

			int iterator = 0;

			while (iterator< array.length) {

				Integer currentInteger = heap.extractRootElement();

				if (currentInteger==numero) {
					aux = currentInteger;
					break;
				} else if (currentInteger<numero && currentInteger>aux) {
					aux = currentInteger;
				}

				iterator++;
			}

			if (aux<=numero) {
				result = aux;
			}
		}

		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = null;
		HeapImpl<Integer> heap = new HeapImpl<Integer>(super.comparator);

		if (array!=null && array.length>0) {

			int aux = array[0];

			for (Integer integer : array) {
				heap.insert(integer);

				if (integer>aux) {
					aux = integer;
				}
			}

			int iterator = 0;

			while (iterator< array.length) {

				Integer currentInteger = heap.extractRootElement();

				if (currentInteger==numero) {
					aux = currentInteger;
					break;
				} else if (currentInteger>numero && currentInteger<aux) {
					aux = currentInteger;
				}

				iterator++;
			}

			if (aux>=numero) {
				result = aux;
			}
		}

		return result;
	}

}
