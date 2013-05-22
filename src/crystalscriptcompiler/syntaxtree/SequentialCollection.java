/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class SequentialCollection<T> extends ParseTreeNode implements Iterable<T> {
	
	private T current;
	private SequentialCollection<T> next;
	private ArrayDeque<T> collection;
	
	protected SequentialCollection() {
		collection = new ArrayDeque<>(0);
	}

	protected SequentialCollection(T obj) {
		current = obj;
		collection = new ArrayDeque<>();
		collection.addFirst(obj);
	}

	protected SequentialCollection(T current, SequentialCollection<T> next) {
		this.current = current;
		this.next = next;
		this.collection = next.collection;
		collection.addFirst(current);
	}

	@Override
	public Iterator<T> iterator() {
		return collection.iterator();
	}
	
}
