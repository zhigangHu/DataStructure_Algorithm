public class AVLTree<E> extends BinarySearchTree<E>{
    public AVLTree(){

    }

    public AVLTree(Comparator comparator){
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node=node.parent)!=null){
            if(isBalanced(node)){
//  Update Height
                updateHeight(node);
            }
            else {
//  Restoring Balance
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }

    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor())<=1;
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();

    }

    private void rebalance(Node<E> grand){
        Node<E> parent=((AVLNode<E>)grand).tallerChild();
        Node<E> node=((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){ // L
            if(node.isLeftChild()){ //LL
                rotateRight(grand);

            }else{// LR
                rotateLeft(parent);
                rotateRight(node);
            }
        }else{ // R
            if(node.isLeftChild()){ //RL
                rotateRight(parent);
                rotateLeft(grand);
            }else{ //RR
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> grand){
        Node<E> parent=grand.right;
        Node<E> child=parent.left;
        grand.right=child;
        parent.left=grand;

        afterRotate(grand,parent,child);


    }
    private void rotateRight(Node<E> grand){
        Node<E> parent=grand.left;
        Node<E> child=parent.right;
        grand.left=child;
        parent.right=grand;

        afterRotate(grand,parent,child);
    }

//    Unified Rotation
    private void rotate(){

    }

    private void afterRotate(Node<E> grand,Node<E> parent, Node<E> child){
        //        Let parent node become the root of subtree
        parent.parent=grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left=parent;
        }else if(grand.isRightChild()){
            grand.parent.right=parent;
        }else{ // grand is root
            root=parent;
        }

//        update parent node of child node
        if(child!=null){
            child.parent=grand;
        }

        grand.parent=parent;

//        update height
        updateHeight(grand);
        updateHeight(parent);
    }

    private static class AVLNode<E> extends Node<E>{
        int height=0;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){
            int leftHeight=left==null?0:((AVLNode<E>)left).height;
            int rightHeight=right==null?0:((AVLNode<E>)right).height;
            return leftHeight-rightHeight;

        }

        public void updateHeight(){
            int leftHeight=left==null?0:((AVLNode<E>)left).height;
            int rightHeight=right==null?0:((AVLNode<E>)right).height;
            height=1+Math.max(leftHeight,rightHeight);
        }

        public Node<E> tallerChild(){
            int leftHeight=left==null?0:((AVLNode<E>)left).height;
            int rightHeight=right==null?0:((AVLNode<E>)right).height;
            if(leftHeight>rightHeight){
                return left;
            }
            if(rightHeight>leftHeight) return right;
            return isLeftChild()?left:right;
        }
    }
}
