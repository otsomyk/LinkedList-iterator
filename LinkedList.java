package linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList implements Iterable<Object> {
	private Node root;
	private int size;

	public LinkedList() {
		size = 0;
	}

	public void addFirst(Object obj) {
		Node n = new Node();
		n.obj = obj;
		if (root != null) {
			n.ref = root;
		}
		root = n;
		size++;
	}

	public void addLast(Object obj) {
		Node n = new Node();
		n.obj = obj;
		if (root == null) {
			root = n;
		} else {
			Node last = root;
			Node cp = root;
			while (cp.ref != null) {
				last = cp;
				cp = cp.ref;
			}
			last.ref = n;
		}
		size++;
	}

	public void addAfter(Object obj, Object previous) {
		Node prevPointer = null;
		Node cp = root;
		do {
			if (cp.obj == prevPointer) {
				prevPointer = cp;
				break;
			}
			cp = cp.ref;
		} while (cp != null && cp.ref != null);
		if (prevPointer == null) {
			throw new IllegalStateException("Previous object does not exist.");
		}
		Node n = new Node();
		n.obj = obj;
		if (prevPointer != null) {
			n.ref = prevPointer.ref;
		}
		prevPointer.ref = n;
		size++;
	}

	public int getSize() {
		return size;
	}

	private class Node {
		Object obj;
		Node ref;
	}

	public void printList() {
		if (size == 0) {
			System.out.println("List is empty.");
		}
		System.out.println("{");
		Node cp = root;
		while (cp.ref != null) {
			System.out.println(cp.obj + ", ");
			cp = cp.ref;
		}
		System.out.println(cp.obj);
		System.out.println("}");
	}

	
		@Override
		public Iterator<Object> iterator() {
			return new SLLIterator();
		}

		private class SLLIterator implements Iterator<Object> {
			private Node cur = null;

			public SLLIterator() {
			}

			@Override
			public boolean hasNext() {
				if ((cur == null && root != null)
						|| (cur != null && cur.ref != null)) {
					return true;
				}
				return false;
			}

			@Override
			public Object next() {
				if (cur == null && root != null) {
					cur = root;
				} else if (hasNext()) {
					cur = cur.ref;
				}
				return cur.obj;
			}

		}
	}

	

