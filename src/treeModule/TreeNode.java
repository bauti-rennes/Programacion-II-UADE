package treeModule;

public class TreeNode <E>{

    //Un nodo puede tener un valor, un hijo izq y un hijo der
    public TreeNode<E> left = null;
    public TreeNode<E> right = null;
    public E value;

    public TreeNode(E value) {
        this.value = value;
    }
}
