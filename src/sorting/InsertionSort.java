package sorting;

public class InsertionSort<E extends Comparable<E>> extends Sorting{

    @Override
    protected void sort() {
        for(int begin=1;begin< array.length;begin++){
            int cur=begin;
            //Optimize
            E v=array[cur]
////        Origin
////            while(cur>0&&compare(cur,cur-1)<0){
//                swap(cur,cur-1);
//                cur--;
//            }
//           Optimize
            while(cur>0 && compare(v,array[cur-1])<0){
                array[cur]=array[cur-1];
                cur--
            }
            array[cur]=v;
        }
    }
}
