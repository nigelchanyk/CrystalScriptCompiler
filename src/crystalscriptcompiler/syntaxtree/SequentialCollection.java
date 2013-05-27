/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class SequentialCollection<T> extends ParseTreeNode implements Iterable<T> {

	public static enum Order {
		FORWARD,
		REVERSE
	}
	
	private LinkedList<T> collection;
	private Iterable<T> descIterable = new Iterable<T>() {

		@Override
		public Iterator<T> iterator() {
			return collection.descendingIterator();
		}
	};
	
	public SequentialCollection() {
		collection = new LinkedList<>();
	}

	public SequentialCollection(T obj) {
		collection = new LinkedList<>();
		collection.addFirst(obj);
	}

	public SequentialCollection(LinkedList<T> collection) {
		this.collection = collection;
	}

	public SequentialCollection(SequentialCollection<T> collection) {
		this.collection = collection.collection;
	}

	public SequentialCollection(SequentialCollection<T> current, SequentialCollection<T> next) {
		this(current, next, Order.FORWARD);
	}

	public SequentialCollection(SequentialCollection<T> current, SequentialCollection<T> next, Order order) {
		this.collection = next.collection;
		if (order == Order.FORWARD) {
			for (T item : current.iterateReversed())
				collection.addFirst(item);
		}
		else {
			for (T item : current)
				collection.addLast(item);
		}
	}

	public SequentialCollection(T current, SequentialCollection<T> next) {
		this(current, next, Order.FORWARD);
	}

	public SequentialCollection(T current, SequentialCollection<T> next, Order order) {
		this.collection = next.collection;
		if (order == Order.FORWARD)
			collection.addFirst(current);
		else
			collection.addLast(current);
	}

	@Override
	public Iterator<T> iterator() {
		return collection.iterator();
	}

	public Iterable<T> iterateReversed() {
		return descIterable;
	}
	
	public int size() {
		return collection.size();
	}
}
