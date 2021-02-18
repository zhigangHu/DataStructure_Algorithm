package sorting;

public class InsertionSort<E extends Comparable<E>> extends Sorting{

    @Override
    protected void sort() {
        for(int begin=1;begin< array.length;begin++){
            int cur=begin;
            while(cur>0&&compare(cur,cur-1)<0){
                swap(cur,cur-1);
                cur--;
            }
        }
    }
}
