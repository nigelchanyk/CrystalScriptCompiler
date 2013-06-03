/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author User
 */
public class HashTableIterable<K, V> extends HashMap<K, V> {
	
	public Iterable<K> keys() {
		return new KeyIterable<>(this);
	}

	private static class KeyIterable<K, V> implements Iterable<K> {

		private HashMap<K, V> map;

		public KeyIterable(HashMap<K, V> map) {
			this.map = map;
		}
		
		@Override
		public Iterator<K> iterator() {
			return new KeyIterator<>(map);
		}
	}
	
	private static class KeyIterator<K, V> implements Iterator<K> {

		private Iterator<Map.Entry<K, V>> iterator;
		
		public KeyIterator(HashMap<K, V> moduleMapper) {
			iterator = moduleMapper.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public K next() {
			return iterator.next().getKey();
		}

		@Override
		public void remove() {
		}
	}

}
