package com.algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	// pointer to first element
	private Node first;
	// pointer to the last element
	private Node last;
	// size of the deque
	private int size;

	private class Node {
		Item node;
		Node next;
		Node previous;
	}

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return this.size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return this.size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node newItem = new Node();
		newItem.node = item;
		newItem.previous = null;
		newItem.next = first;

		if (first == null) {
			// it was empty, we just entered the first item
			last = newItem;
		} else {
			first.previous = newItem;
		}

		this.first = newItem;
		size++;
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node newItem = new Node();
		newItem.node = item;

		if (last == null) {
			// empty, we just entered first item
			last = newItem;
			first = newItem;
			newItem.previous = null;
		} else {
			newItem.previous = last;
			last.next = newItem;
		}

		newItem.next = null;
		this.last = newItem;
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item rt = first.node;
		Node temp = first.next;
		if (first.next != null) {
			first.next.previous = null;
			first.previous = null;
		}
		first = temp;

		size--;

		if (size == 0) {
			last = null;
		}

		return rt;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item rt = last.node;

		Node temp = last.previous;
		if (last.previous != null) {
			last.previous.next = null;
			last.previous = null;
		}
		last = temp;

		size--;

		if (size == 0) {
			first = null;
		}

		return rt;
	}

	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		Node itr = first;

		@Override
		public boolean hasNext() {
			return itr != null;
		}

		@Override
		public Item next() {
			if (itr == null) {
				throw new NoSuchElementException();
			}
			Item rt = itr.node;
			itr = itr.next;
			return rt;
		}

		@Override
	    public void remove() {
	        throw new UnsupportedOperationException("remove");
	    }

	}

	// unit testing (optional)
	public static void main(String[] args) {
		Deque<String> dq = new Deque<>();
		dq.addFirst("add first remove last");
		String s = dq.removeLast();
		System.out.println(s);

		System.out.println(dq.isEmpty());

		dq.addLast("add last remove first");
		s = dq.removeFirst();
		System.out.println(s);

		System.out.println(dq.isEmpty());

		dq.addFirst("add first remove first");
		s = dq.removeFirst();
		System.out.println(s);

		System.out.println(dq.isEmpty());
		dq.addLast("add first remove last");
		s = dq.removeLast();
		System.out.println(s);

		System.out.println(dq.isEmpty());
		dq.addLast("add last 1");
		dq.addLast("add last 2");
		System.out.println(dq.removeLast());
		System.out.println(dq.removeLast());

		System.out.println(dq.isEmpty());
		dq.addFirst("add first 1");
		dq.addLast("add last 2");
		System.out.println(dq.removeLast());
		System.out.println(dq.removeLast());

	}

}
