public class BinarySearchTree<E> {

    private int size;
    public Node<E> root;
    static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;


        public Node(E element,Node<E> parent){
            this.element=element;
            this.parent=parent;
        }

        public boolean isLeaf(){
            return left==null && right==null;
        }

        public boolean hasTwoChildren(){
            return left != null && right !=null;
        }

        public boolean isLeftChild(){
            return parent != null && this==parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this==parent.right;
        }

        public Node sibling(){
            if(isLeftChild()){
                return parent.right;
            }
            if(isRightChild()){
                return parent.left;
            }

            return null;
        }

    }
    public BinarySearchTree(Comparator<E> comparator){
        this.comparator=comparator;
    }
    public BinarySearchTree(){
        this(null);
    }

    private Comparator<E> comparator;

    private void elementNotNullCheck(E element){
        if(element==null){
            throw new IllegalArgumentException("element must not be null");
        }
    }
    private int compare(E e1, E e2){
        if(comparator!=null){
            return comparator.compare(e1,e2);
        }

        return ((Comparable<E>)e1).compareTo(e2);

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void clear(){

    }

    public void add(E element){
        elementNotNullCheck(element);

        // Add the first element
        if(root==null){
           root=createNode(element,null);
           size++;
           afterAdd(root);
           return;

        }

//        Add normal node
//        Step 1: Find parent node
        Node<E> parent=root;
        Node<E> node=root;
        int cmp=0;

        while (node!=null){
            cmp=compare(element, node.element);

            parent=node;

            if(cmp>0){
                node=node.right;
            }else if(cmp<0){
                node=node.left;
            }else {
                return;
            }
        }

//        Step 2: Insert element to the location of the parent node
        Node<E> newNode=createNode(element,parent);
        if(cmp>0){
            parent.right=newNode;
        }else {
            parent.left=newNode;
        }
        size++;

        afterAdd(newNode);





    }

    protected void afterAdd(Node<E> node){

    }

    public void remove(E element){

    }

    public boolean contains(E element){
        return false;
    }

    protected Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element,parent);
    }

}
