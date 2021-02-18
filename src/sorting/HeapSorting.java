package sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSorting extends Sorting{
    private int heapSize;


    @Override
    protected void sort() {
        // Heapify
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0;i<array.length;i++){
            maxHeap.add((Integer) array[i]);
        }
//        while(heapSize>1){
//            // Swap top of heap and tail elements
//            swap(0,heapSize-1);
//            heapSize--;
//            // Siftdown the 0 location
//
//        }
        for(int i= array.length-1;i>=0;i--){
            int tmp=maxHeap.peek();
            array[i]=tmp;
            maxHeap.remove(tmp);
        }
    }

    }

