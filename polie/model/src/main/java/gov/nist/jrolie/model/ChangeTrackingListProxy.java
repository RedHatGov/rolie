package gov.nist.jrolie.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChangeTrackingListProxy<E extends JResource> implements List<E> {
	private JResource owningObject;
	private List<E> delegate;

	public ChangeTrackingListProxy(JResource obj, List<E> delegate) {
		super();
		this.owningObject = obj;
		this.delegate = delegate;
	}

	@Override
	public boolean add(E arg0) {
		boolean retval = delegate.add(arg0);
		owningObject.markChanged();
		return retval;
	}

	@Override
	public void add(int arg0, E arg1) {
		delegate.add(arg0, arg1);
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		boolean retval = delegate.addAll(arg0);
		owningObject.markChanged();
		return retval;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		return delegate.addAll(arg0, arg1);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int arg0, E arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
