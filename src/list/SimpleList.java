package list;

// E es un genérico en el que puede ir cualquier cosa. Siempre la E tiene que ser de lo mismo igual
// Si pongo Integer E, todas las E se convierten en Integer

public interface SimpleList<E> {
    public boolean add(E element);
    public void add(int index, E element);
    public E remove(int index);
    public boolean remove(Object object);
    public void clear();
    public boolean contains(Object object);
    public E get(int index);
    public E set(int index, E element);
    public int size();
    public boolean isEmpty();
}
