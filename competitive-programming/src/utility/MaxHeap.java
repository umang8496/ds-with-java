package utility;

public class MaxHeap {
	private int[] Heap;
	private int size;
	private int maxsize;

	private static final int FRONT = 1;

	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new int[this.maxsize + 1];
		Heap[0] = Integer.MAX_VALUE;
	}

	private int _parent(int pos) {
		return (pos / 2);
	}

	private int _leftChild(int pos) {
		return (2 * pos);
	}

	private int _rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean _isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private void _swap(int fpos, int spos) {
		int tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	private void _maxHeapify(int pos) {
		if (!this._isLeaf(pos)) {
			if (Heap[pos] < Heap[this._leftChild(pos)] || Heap[pos] < Heap[this._rightChild(pos)]) {
				if (Heap[this._leftChild(pos)] > Heap[this._rightChild(pos)]) {
					this._swap(pos, this._leftChild(pos));
					this._maxHeapify(this._leftChild(pos));
				} else {
					this._swap(pos, this._rightChild(pos));
					this._maxHeapify(this._rightChild(pos));
				}
			}
		}
	}

	public void insert(int element) {
		Heap[++size] = element;
		int current = size;

		while (Heap[current] > Heap[this._parent(current)]) {
			this._swap(current, this._parent(current));
			current = this._parent(current);
		}
	}

	public void maxHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			this._maxHeapify(pos);
		}
	}

	public int remove() {
		int popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--];
		this._maxHeapify(FRONT);
		return popped;
	}

}

