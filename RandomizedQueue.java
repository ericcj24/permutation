package com.algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	// pointer to first element
	private Item[] queueArray;
	private int size;

	// construct an empty randomized queue
	public RandomizedQueue() {
		queueArray = (Item[]) new Object[2];
		size = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the queue
	public int size() {
		return this.size;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < size; i++) {
            copy[i] = queueArray[i];
        }
        queueArray = copy;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}

		if( size == queueArray.length) {
            resize(2*queueArray.length);
        }

		queueArray[size++] = item;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int randIdx = StdRandom.uniform(this.size);
		Item rt = queueArray[randIdx];
		if (randIdx != size-1) {
			queueArray[randIdx] = queueArray[size-1];
		}
		queueArray[size-1] = null;

		size--;

		if (size > 0 && size < queueArray.length/4) {
			resize(queueArray.length/2);
		}

		return rt;
	}

	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int randIdx = StdRandom.uniform(this.size);

		Item rt = queueArray[randIdx];
		return rt;
	}

	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item[] queueArrayCp;
		private int sizeCp = size;

		public RandomizedQueueIterator() {
			queueArrayCp = (Item[]) new Object[size];

			for (int i = 0; i< size; i++) {
				queueArrayCp[i] = queueArray[i];
			}
		}

		@Override
		public boolean hasNext() {
			return sizeCp != 0;
		}

		@Override
		public Item next() {
			if (sizeCp == 0) {
				throw new NoSuchElementException();
			}
			int randIdx = StdRandom.uniform(sizeCp);
			Item rt = queueArrayCp[randIdx];
			if (randIdx != sizeCp-1) {
				queueArrayCp[randIdx] = queueArrayCp[sizeCp-1];
			}
			queueArrayCp[sizeCp-1] = null;
			sizeCp--;

			return rt;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}


	// unit testing (optional)
	public static void main(String[] args) {

		RandomizedQueue<String> rq = new RandomizedQueue<>();
		rq.enqueue("first");
		System.out.println(rq.dequeue());

		rq.enqueue("first");
		rq.enqueue("last");
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
	}


}

