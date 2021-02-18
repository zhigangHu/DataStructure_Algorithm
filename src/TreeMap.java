public class TreeMap<K, V> implements Map<K, V>{
    private static  final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K,V> root;

    private Comparator<K> comparator;

    public TreeMap(){
        this(null);
    }
    public TreeMap(Comparator<K> comparator){
        this.comparator=comparator;
    }

    private void keyNotNullCheck(K key){
        if(key==null){
            throw new IllegalArgumentException("key must not be null");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        root=null;
        size=0;

    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);

        // Add the first element
        if(root==null){
            root=new Node<>(key,value,null);
            size++;
            afterPut(root);
            return null;

        }

//        Add normal node
//        Step 1: Find parent node
        Node<K,V> parent=root;
        Node<K,V> node=root;
        int cmp=0;

        while (node!=null){
            cmp=compare(key, node.key);

            parent=node;

            if(cmp>0){
                node=node.right;
            }else if(cmp<0){
                node=node.left;
            }else {
                node.key=key;
                V oldValue=value;
                node.value=value;

                return oldValue;
            }
        }

//        Step 2: Insert element to the location of the parent node
        Node<K,V> newNode=new Node<>(key,value,parent);
        if(cmp>0){
            parent.right=newNode;
        }else {
            parent.left=newNode;
        }
        size++;

        afterPut(newNode);
        return value;




    }
    private int compare(K e1, K e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        return ((Comparable<K>) e1).compareTo(e2);

    }

    private void afterPut(Node<K,V> node) {
        Node father, grandFather;
        while ((father = node.getParent()) != null && father.getColor() == RED) {
            grandFather = father.getParent();
            if (grandFather.getLeft() == father) {  //F为G左儿子的情况，如之前的分析
                Node uncle = grandFather.getRight();
                if (uncle != null && uncle.getColor() == RED) {
                    setBlack(father);
                    setBlack(uncle);
                    setRed(grandFather);
                    node = grandFather;
                    continue;
                }
                if (node == father.getRight()) {
                    leftRotate(father);
                    Node tmp = node;
                    node = father;
                    father = tmp;
                }
                setBlack(father);
                setRed(grandFather);
                rightRotate(grandFather);
            } else {                               //F为G的右儿子的情况，对称操作
                Node uncle = grandFather.getLeft();
                if (uncle != null && uncle.getColor() == RED) {
                    setBlack(father);
                    setBlack(uncle);
                    setRed(grandFather);
                    node = grandFather;
                    continue;
                }
                if (node == father.getLeft()) {
                    rightRotate(father);
                    Node tmp = node;
                    node = father;
                    father = tmp;
                }
                setBlack(father);
                setRed(grandFather);
                leftRotate(grandFather);
            }
        }
        setBlack(root);
    }

    private boolean isBlack(Node node) {
        if (node == null)
            return true;
        return node.getColor() == BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.getColor() == RED;
    }

    private void leftRotate(Node node) {
        Node right = node.getRight();
        Node parent = node.getParent();
        if (parent == null) {
            root = right;
            right.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            right.setParent(parent);
        }
        node.setParent(right);
        node.setRight(right.getLeft());
        if (right.getLeft() != null) {
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
    }

    private void rightRotate(Node node) {
        Node left = node.getLeft();
        Node parent = node.getParent();
        if (parent == null) {
            root = left;
            left.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            left.setParent(parent);
        }
        node.setParent(left);
        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setRight(node);
    }

    private void setBlack(Node node) {
        node.setColor(BLACK);
    }

    private void setRed(Node node) {
        node.setColor(RED);
    }

    private Node<K,V> node(K key){
        Node<K,V> node=root;
        while (node!=null){
            int cmp=compare(key,node.key);
            if(cmp==0) return node;
            if(cmp>0){
                node=node.right;
            }else{
                node=node.left;
            }
        }
        return  null;
    }


    @Override
    public V get(K key) {
        Node<K,V> node=node(key);
        return node !=null?node.value:null;
    }

    @Override
    public V remove(K key) {
        Node<K,V> node=node(key);
        if (node == null)
            return null;
        size --;

        if (node.getLeft() != null && node.getRight() != null) {
            Node replaceNode = node;
            Node tmp = node.getRight();
            while (tmp != null) {
                replaceNode.key = tmp.key;
                replaceNode.right=tmp.right;
                tmp = tmp.getLeft();
            }


            return (V) replaceNode.value;
        }
        Node replaceNode = null;
        if (node.getLeft() != null)
            replaceNode = node.getLeft();
        else
            replaceNode = node.getRight();

        Node parent = node.getParent();
        if (parent == null) {
            root = replaceNode;
            if (replaceNode != null)
                replaceNode.setParent(null);
        } else {
            if (replaceNode != null)
                replaceNode.setParent(parent);
            if (parent.getLeft() == node)
                parent.setLeft(replaceNode);
            else {
                parent.setRight(replaceNode);
            }
        }
        if (node.getColor() == BLACK)
            removeFix(parent, replaceNode);
        return (V) replaceNode.value;
    }

    private void removeFix(Node father, Node node) {
        while ((node == null || node.getColor() == BLACK) && node != root) {
            if (father.getLeft() == node) {  //S为P的左儿子的情况，如之前的分析
                Node brother = father.getRight();
                if (brother != null && brother.getColor() == RED) {
                    setRed(father);
                    setBlack(brother);
                    leftRotate(father);
                    brother = father.getRight();
                }
                if (brother == null || (isBlack(brother.getLeft()) && isBlack(brother.getRight()))) {
                    setRed(brother);
                    node = father;
                    father = node.getParent();
                    continue;
                }
                if (isRed(brother.getLeft())) {
                    setBlack(brother.getLeft());
                    setRed(brother);
                    rightRotate(brother);
                    brother = brother.getParent();
                }

                brother.setColor(father.getColor());
                setBlack(father);
                setBlack(brother.getRight());
                leftRotate(father);
                node = root;//跳出循环
            } else {                         //S为P的右儿子的情况，对称操作
                Node brother = father.getLeft();
                if (brother != null && brother.getColor() == RED) {
                    setRed(father);
                    setBlack(brother);
                    rightRotate(father);
                    brother = father.getLeft();
                }
                if (brother == null || (isBlack(brother.getLeft()) && isBlack(brother.getRight()))) {
                    setRed(brother);
                    node = father;
                    father = node.getParent();
                    continue;
                }
                if (isRed(brother.getRight())) {
                    setBlack(brother.getRight());
                    setRed(brother);
                    leftRotate(brother);
                    brother = brother.getParent();
                }

                brother.setColor(father.getColor());
                setBlack(father);
                setBlack(brother.getLeft());
                rightRotate(father);
                node = root;//跳出循环
            }
        }

        if (node != null)
            node.setColor(BLACK);
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visistor) {

    }

    private static class Node<K,V>{

        K key;
        V value;
        boolean color=RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;


        public Node(K key, V value, Node<K,V> parent){
            this.key=key;
            this.value=value;
            this.parent=parent;
        }




        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }


    }
}
