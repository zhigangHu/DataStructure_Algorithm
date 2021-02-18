package sorting;
import tools.*;

public class SelectionSorting {
    public static void main(String[] args) {
        Integer[] array=Integers.random(10,2,100);
        System.out.println("The original value of array is:");
        Integers.println(array);

        array=selectionSorting(array);
        System.out.println("---------------------------------------");
        System.out.println("SelectionSorting output: " );
        Integers.println(array);

        System.out.println("---------------------------------------");

    }

    private static Integer[] selectionSorting(Integer[] array){
        for(int end=array.length-1;end>0;end--){
            int maxIndex=0;
            for(int begin=1;begin<=end;begin++){
                if(array[maxIndex]<array[begin]){
                    maxIndex=begin;
                }

            }
            int tmp=array[maxIndex];
            array[maxIndex]=array[end];
            array[end]=tmp;

        }
        return array;
    }
}
