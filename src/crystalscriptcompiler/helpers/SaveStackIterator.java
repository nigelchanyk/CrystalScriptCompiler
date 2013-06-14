/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 *
 * @author User
 */
public interface SaveStackIterator<T> extends Iterator<T> {

	public abstract void saveStack();
	public abstract void restoreStack();

	public static class ArrayListIterator<T> implements SaveStackIterator<T> {

		private ArrayList<T> list;
		private Stack<Integer> positionStack = new Stack<>();
		private ListIterator<T> currentIterator;

		public ArrayListIterator(ArrayList<T> list) {
			this.list = list;
			currentIterator = list.listIterator();
		}

		@Override
		public void saveStack() {
			positionStack.push(currentIterator.nextIndex());
		}

		@Override
		public void restoreStack() {
			currentIterator = list.listIterator(positionStack.pop());
		}

		@Override
		public boolean hasNext() {
			return currentIterator.hasNext();
		}

		@Override
		public T next() {
			return currentIterator.next();
		}

		@Override
		public void remove() {
			currentIterator.remove();
		}
	}
}