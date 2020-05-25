package ds.heap;

public class MinHeap {
	private int[] Heap;
	private int size;
	private int maxsize;

	private static final int FRONT = 1;

	public MinHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new int[this.maxsize + 1];
		Heap[0] = Integer.MIN_VALUE;
	}

	private int _parent(int pos) {
		return pos / 2;
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

	private void _minHeapify(int pos) {
		if (!this._isLeaf(pos)) {
			if (Heap[pos] > Heap[this._leftChild(pos)] || Heap[pos] > Heap[this._rightChild(pos)]) {
				if (Heap[this._leftChild(pos)] < Heap[this._rightChild(pos)]) {
					this._swap(pos, this._leftChild(pos));
					this._minHeapify(this._leftChild(pos));
				} else {
					this._swap(pos, this._rightChild(pos));
					this._minHeapify(this._rightChild(pos));
				}
			}
		}
	}

	public void insert(int element) {
		Heap[++size] = element;
		int current = size;

		while (Heap[current] < Heap[this._parent(current)]) {
			this._swap(current, this._parent(current));
			current = this._parent(current);
		}
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print("PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]);
			System.out.println();
		}
	}

	public void minHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			this._minHeapify(pos);
		}
	}

	public int remove() {
		int popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--];
		this._minHeapify(FRONT);
		return popped;
	}

}
