package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if ((element!=null) && (this.search(element)==null)) {

			if (super.isFull()) {
				throw new HashtableOverflowException();
			}

			boolean wasInserted = false;
			int probe = 0;

			while (!wasInserted) {
				int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe++);

				if ((super.table[hash]==null) || (super.table[hash].equals(super.deletedElement))) {

					super.table[hash] = element;
					super.elements++;
					wasInserted = true;

				} else {
					super.COLLISIONS++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if ((element!=null) && (!super.isEmpty())) {

			int probe = 0;

			while (probe<super.capacity()) {
				int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

				if (super.table[hash]==null) {

					break;

				} else if (super.table[hash].equals(element)) {

					super.table[hash] = super.deletedElement;
					super.elements--;
					break;
				}

				probe++;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;

		if ((element!=null) && (!super.isEmpty())) {

			int hash = this.indexOf(element);

			if (hash!=-1) {
				result = (T) super.table[hash];
			}
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;

		if ((element!=null) && (!super.isEmpty())) {

			int probe = 0;

			while (probe<super.capacity()) {
				int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

				if ((super.table[hash]==null) || (super.table[hash].equals(super.deletedElement))) {

					break;

				} else if (super.table[hash].equals(element)) {

					result = hash;
					break;
				}

				probe++;
			}
		}

		return result;
	}

}
