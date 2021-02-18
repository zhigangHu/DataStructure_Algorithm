package sorting;
import tools.*;

public class BubbleSorting {
    public static void main(String[] args) {
        Integer[] array=Integers.random(10,2,100);

        array=bubbleSorting(array);
        System.out.println("---------------------------------------");
        System.out.println("BubbleSorting output " );
        Integers.println(array);
        System.out.println();
        System.out.println("---------------------------------------");
    }

    private static Integer[] bubbleSorting(Integer[] array) {
        for(int end=array.length-1;end>0;end--){
            for(int begin=1;begin<=end;begin++){
                if(array[begin]<array[begin-1]){
                    int tmp=array[begin];
                    array[begin]=array[begin-1];
                    array[begin-1]=tmp;
                }
                else{
                    continue;
                }
            }
        }


        return array;
    }

    private static Integer[] bubbleSorting2(Integer[] array){
        for(int end=array.length-1;end>0;end--){
            int sortedIndex=1;
            for(int begin=1;begin<=end;begin++){
                if(array[begin]<array[begin-1]){
                    int tmp=array[begin];
                    array[begin]=array[begin-1];
                    array[begin-1]=tmp;

                    sortedIndex=begin;
                }

            }
            end=sortedIndex;
        }


        return array;
    }


}
