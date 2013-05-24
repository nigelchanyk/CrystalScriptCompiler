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
		this.collection = next.collection;
		for (T item : current.iterateReversed())
			collection.addFirst(item);
	}

	public SequentialCollection(T current, SequentialCollection<T> next) {
		this.collection = next.collection;
		collection.addFirst(current);
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
