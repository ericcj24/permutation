package com.algorithms.week2;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> qu =  new RandomizedQueue<>();

		try {
			String values = StdIn.readString();
			while(values != null) {
				qu.enqueue(values);
				values = StdIn.readString();
			}
		} catch (NoSuchElementException e) {
			//StdOut.println(e.toString());
		}

		for (int i=0; i< k; i++) {
			StdOut.println(qu.dequeue());
		}
	}
}