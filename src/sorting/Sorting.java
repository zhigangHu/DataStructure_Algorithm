package sorting;

public abstract class Sorting<E extends Comparable<E>>  {
    protected E[] array;
    protected int cmpCount; //The number of comparing
    protected int swapCount;// the number of swapping

    public void sort(E[] array){
        if(array==null||array.length<2) return;

        this.array=array;
        sort();
    }

    protected abstract void sort();
    /*
     *

     */
    protected int compare(int i1,int i2){
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }
    protected int compare(E e1,E e2){
        cmpCount++;
        return e1.compareTo(e2);
    }

    protected void swap(int i1, int i2){
        swapCount++;
        E tmp=array[i1];
        array[i1]=array[i2];
        array[i2]=tmp;
    }


}
