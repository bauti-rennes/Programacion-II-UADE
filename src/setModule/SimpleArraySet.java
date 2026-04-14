package setModule;

public class SimpleArraySet<E> implements SimpleSet<E> {

    private E[] elements; //esto es el array de los elementos
    private static final int DEFAULT_CAPACITY = 4;
    private int size = 0;

    public SimpleArraySet(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public SimpleArraySet(int capacity){
        //el profe hizo esta función pero yo no entiendo por qué puede haber dos funciones uqe se llamen igual
        elements = (E[]) new Object[capacity];
    }

    @Override
    public boolean add(E element) {
        if(element == null)
            throw new IllegalArgumentException("Element to add cannot be null.");

        if(contains(element))
            return false;

        validateSize(size + 1);

        elements[size] = element;
        size++;

        return true;
    }

    @Override
    public boolean remove(E element) {
        if(element == null)
            throw new IllegalArgumentException("Element to add cannot be null.");

        /* No usamos contains() porque igual tenemos que iterar sobre el array
        Si el array contiene el elemento y usamos contains, son dos fros en vez de uno
        Es cuestión de ahorro */
        for(int i = 0; i < size; i++) {

            if (element.equals(elements[i])) {
                if (i == size -1) elements[i] = null; //Si es el último elemento, lo nulleo
                else elements[i] = elements [size-1]; //Si no es el último elemento, reemplazo el elemento por el último del array. Esto se hace para que no queden espacios vacíos
                size--;
            }
        }
        return false;

    }

    @Override
    public boolean contains(E element) {
        if(element == null)
            throw new IllegalArgumentException("The element to check cannot be null.");

        //Recorremos el array, si encontramos lo que buscamos devolvemos true
        for(int i = 0; i < size; i++)
            if(element.equals(elements[i]))
                return true;

        //Si no estaba, termina el bucle y llega aca, devolviendo false
        return false;
    }


    @Override
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {

        //Sobrescrivo el conjunto por uno nuevo
        elements = ((E[]) new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public E[] toArray() {
        //Cómo creamos un array que contenga todos los elementos del set?
        E[] result = (E[]) new Object[size];

        for (int i = 0; i < size;i++){
            result[i] = elements[i];
        }
        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {

        if(other == null)
            throw new IllegalArgumentException("Other set cannot be null");

        //Cómo creamos y devolvemos un Set que contenga todo lo de este y other?
        SimpleSet<E> result = new SimpleArraySet<E>();

        //Agregamos todos los elementos de este set
        for (int i = 0; i < size; i++)
            result.add(elements[i]);

        //Agregamos todos los elementos del otro Set
        E[] otherElements = other.toArray();

        for (int i = 0; i < other.size();i++)
           result.add(otherElements[i]);

        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {

        //Cómo creamos y devolvemos un Set que contenga todo lo de este y other?
        SimpleSet<E> result = new SimpleArraySet<E>();

        if(other == null)
            throw new IllegalArgumentException("Other set cannot be null");

        for (int i = 0; i < size; i++){
            if(other.contains(elements[i]))
                result.add(elements[i]);
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {

        //Cómo creamos y devolvemos un Set que contenga todo lo de este y other?
        SimpleSet<E> result = new SimpleArraySet<E>();

        if(other == null)
            throw new IllegalArgumentException("Other set cannot be null");

        for (int i = 0; i < size; i++){
            if(!other.contains(elements[i])) //si el other set no contiene el elemento, lo agrego al resultado
                result.add(elements[i]);
        }
        /*acá en total hay como 3 fors porque tenés uno en el for, otro en el contains y otro en el add        */

        return result;
    }

    private void validateSize(int newSize){
        if (newSize > elements.length)
        {
            resize();
        }
        /*recordar que no es lo mismo la cantidad de elementos que tiene el array
        vs la cantidad de espacios que tiene el array
        el array puede tener 4 espacios pero solo 3 elementos,
        entonces el tamaño del array es 4 pero el tamaño del conjunto es 3 */
    }

    private void resize(){

        //generamos un array temporal con el doble de capacidad que el actual
        E[] temp = (E[]) new Object[elements.length * 2];

        for (int i = 0; i < size;i++){
            temp[i] = elements[i];
        }
        elements = temp;
    }
}
