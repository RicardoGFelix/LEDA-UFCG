package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}

		this.list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T result = this.head();

		this.list.removeFirst();

		return result;
	}

	@Override
	public T head() {
		T result = null;

		if (!this.isEmpty()) {

			T[] array = this.list.toArray();
			result = array[0];

		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;

		if (this.list.isEmpty()) {
			result = true;
		}

		return result;
	}

	@Override
	public boolean isFull() {
		boolean result = false;

		if (this.list.size()==this.size) {
			result = true;
		}

		return result;
	}

}
