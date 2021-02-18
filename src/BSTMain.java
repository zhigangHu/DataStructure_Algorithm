public class BSTMain {
   private static class MyComparator implements Comparator<Person>{
      public int compare(Person e1, Person e2){
         return e1.getAge()-e2.getAge();
      }
   }

   public static void main(String[] args){
//      Integer data[]=new Integer[]{
//              7,4,9,2,5,8,11,3
//      };


      BinarySearchTree<Person> bst=new BinarySearchTree<>(new MyComparator());
      bst.add(new Person(12));
      bst.add(new Person(15));

//      for(int i=0;i<data.length;i++){
//         bst.add(data[i]);
//      }
   }
}
