package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {

		boolean result = false;

		if (this.getData()==null) {
			result = true;
		}

		return result;
	}

	@Override
	public int size() {

		int result = 0;

		if (!this.isEmpty()) {
			result = 1 + this.next.size();
		}

		return result;
	}

	@Override
	public T search(T element) {

		T result = null;

		if (element!=null) {

			if (!this.isEmpty()) {

				if (this.getData().equals(element)) {
					result = element;
				} else {
					result = this.next.search(element);
				}
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {

		if (element!=null) {

			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			}  else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {

		if (element!=null) {

			if (!this.isEmpty()) {

				if (this.getData().equals(element)) {

					if (this.getNext()==null) {
						this.setData(null);
					} else {
						this.setData(this.next.data);
						this.setNext(this.next.next);
					}

				} else if (this.getNext()!=null) {
					this.next.remove(element);
				}
			}
		}
	}

	@Override
	public T[] toArray() {

		List<T> array = new ArrayList<>();

		recursiveToArray(array);

		return (T[]) array.toArray();
	}

	private void recursiveToArray(List<T> array){

		if (!this.isEmpty()) {
			array.add(this.getData());
			this.getNext().recursiveToArray(array);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
