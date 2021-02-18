package sorting;
import tools.*;

public class Main {
    public static void main(String[] args) {
        Integer[] array=Integers.random(10,1,100);
        System.out.println("The original value of array is:");
        Integers.println(array);
        HeapSorting heapSorting=new HeapSorting();
        heapSorting.sort(array);
        System.out.println("_______________________________");
        System.out.println("The output of heapSorting is:");
        Integers.println(array);
    }
}
