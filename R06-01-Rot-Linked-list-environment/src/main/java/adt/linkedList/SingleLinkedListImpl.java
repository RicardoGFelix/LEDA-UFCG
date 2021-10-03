package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;

		if (this.head.isNIL()) {
			result = true;
		}

		return result;
	}

	@Override
	public int size() {
		int result = 0;

		if (!this.head.isNIL()) {
			SingleLinkedListNode<T> node = this.head;

			while (!node.isNIL()) {
				result++;
				node = node.getNext();

			}
		}

		return result;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element!=null) {

			SingleLinkedListNode node = this.getHead();

			while (!node.isNIL()) {

				if (node.getData().equals(element)) {
					result = (T) node.getData();
					break;
				} else {
					node = node.getNext();
				}
			}

		}

		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> node = this.head;

		if (element!=null) {

			if (isEmpty()) {

				SingleLinkedListNode newHead = new SingleLinkedListNode(element, new SingleLinkedListNode());
				this.setHead(newHead);

			} else {

				while (!node.isNIL()) {
					node = node.getNext();
				}

				node.setData(element);
				node.setNext(new SingleLinkedListNode<>());
			}
		}


	}

	@Override
	public void remove(T element) {
		if (element!=null) {

			if (this.getHead().getData().equals(element)) {

				this.head = this.head.getNext();

			} else {

				SingleLinkedListNode nextNode = this.head;
				SingleLinkedListNode prevNode = this.head;

				while ((!nextNode.isNIL()) && (!nextNode.getData().equals(element))) {
					prevNode = nextNode;
					nextNode = nextNode.getNext();
				}

				if (!nextNode.isNIL()) {
					prevNode.setNext(nextNode.getNext());
				}

			}
		}
	}

	@Override
	public T[] toArray() {
		List<T> array = new ArrayList<>();

		SingleLinkedListNode node = this.head;

		while (!node.isNIL()) {
			array.add((T) node.getData());
			node = node.getNext();
		}

		return (T[]) array.toArray();
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
