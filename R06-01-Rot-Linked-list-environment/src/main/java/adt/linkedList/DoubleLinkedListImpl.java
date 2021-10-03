package adt.linkedList;

public class DoubleLinkedListImpl<Integer> extends SingleLinkedListImpl<Integer> implements
		DoubleLinkedList<Integer> {

	protected DoubleLinkedListNode<Integer> last;

	@Override
	public void insert(Integer element) {
		if (this.isEmpty()) {

			this.insertFirst(element);

		} else {

			DoubleLinkedListNode<Integer> newNode = new  DoubleLinkedListNode();

			newNode.setData(element);
			newNode.setNext(new DoubleLinkedListNode());
			newNode.setPrevious(this.getLast());

			this.getLast().setNext(newNode);
			this.setLast(newNode);
		}
	}

	@Override
	public void insertFirst(Integer element) {

		if (element!=null) {

			if (this.isEmpty()) {
				this.setLast(new DoubleLinkedListNode<Integer>(element, new DoubleLinkedListNode<Integer>(), new DoubleLinkedListNode<Integer>()));
				this.setHead(this.last);
			} else {
				DoubleLinkedListNode<Integer> newNode = new DoubleLinkedListNode<>();

				newNode.setData(this.getHead().getData());
				newNode.setNext(this.getHead().getNext());

				this.setHead(new DoubleLinkedListNode<Integer>(element, newNode, new DoubleLinkedListNode<>()));
			}
		}
	}

	@Override
	public void remove(Integer element) {
		if (this.getHead().getData().equals(element)) {

			this.removeFirst();

		} else if (this.last.getData().equals(element)) {

			this.removeLast();

		} else {

			DoubleLinkedListNode<Integer> node = (DoubleLinkedListNode<Integer>) this.getHead();

			while ((!node.isNIL()) && (!node.getData().equals(element))) {
				node = (DoubleLinkedListNode<Integer>) node.getNext();
			}

			if (!node.isNIL()) {
				node.getPrevious().setNext(node.getNext());
				((DoubleLinkedListNode<Integer>) node.getNext()).setPrevious(node.getPrevious());
			}
		}
	}

	@Override
	public void removeFirst() {

		if (!this.isEmpty()) {

			if (this.size()==1) {

				this.setHead(this.getHead().getNext());

				if (this.getHead() instanceof DoubleLinkedListNode<?>) {
					this.setLast((DoubleLinkedListNode<Integer>) this.getHead());
				}

			} else {

				this.setHead(this.getHead().getNext());
			}
		}
	}

	@Override
	public void removeLast() {

		if (!this.isEmpty()) {

			if (this.size()==1) {

				this.setLast(new DoubleLinkedListNode<Integer>());
				this.setHead(this.last);

			} else {

				setLast(this.last.getPrevious());
				this.last.setNext(new DoubleLinkedListNode<>());
			}
		}
	}

	public DoubleLinkedListNode<Integer> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<Integer> last) {
		this.last = last;
	}

}
