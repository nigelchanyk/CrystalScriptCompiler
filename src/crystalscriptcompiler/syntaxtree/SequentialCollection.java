/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.types.Type;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class SequentialCollection<T extends ParseTreeNode> extends ParseTreeNode implements Iterable<T> {

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

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);

		for (T element : this)
			element.setSymbolTable(symbolTable);
	}

	@Override
	public void addDeclarationToTable() {
		for (T element : this)
			element.addDeclarationToTable();
	}

	@Override
	public void createInheritanceTree() {
		for (T element : this)
			element.createInheritanceTree();
	}

	@Override
	public void linkInheritedSymbolTables() {
		for (T element : this)
			element.linkInheritedSymbolTables();
	}

	@Override
	public void determineReferenceType() {
		for (T element : this)
			element.determineReferenceType();
	}

	@Override
	public void addMethodToTable() {
		for (T element : this)
			element.addMethodToTable();
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		for (T element : this)
			element.addVariablesToTable(statementIndex);
	}

	@Override
	public void validateModifiers() {
		for (T element : this)
			element.validateModifiers();
	}

	@Override
	public Type validate() {
		for (T element : this)
			element.validate();

		return null;
	}

}
