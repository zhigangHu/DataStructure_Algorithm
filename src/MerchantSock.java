import java.util.HashSet;

public class MerchantSock {
    public static void main(String[] args) {
        //Initialize array
        int[] arr = new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20};
        //Array fr will store frequencies of element


        System.out.println("---------------------------------------");
        System.out.println(" sockMerchant output " + sockMerchant(9, arr));
        System.out.println("---------------------------------------");

    }

    static int sockMerchant(int n, int[] ar) {
//        int pairs = 0;
//        int frequencyArray[] = new int[ar.length];
//        int frequencyTemp = -1;
//        for (int i = 0; i < ar.length; i++) {
//            int count = 1;
//            for (int j = i + 1; j < ar.length; j++) {
//                if (ar[i] == ar[j]) {
//                    count++;
//                    frequencyArray[j] = frequencyTemp;
//                }
//            }
//            if (frequencyArray[i] != frequencyTemp) {
//                frequencyArray[i] = count;
//            }
//        }
//
//        for (int i = 0; i < frequencyArray.length; i++) {
//            if (frequencyArray[i] != frequencyTemp) {
//                int divide = frequencyArray[i] / 2;
//                pairs += divide;
//            }
//        }
        HashSet<Integer> unmatched=new HashSet<>();
        int pairs=0;
        for(int i=0;i<ar.length;i++){
            if(!unmatched.add(ar[i])){
                unmatched.remove(ar[i]);
                pairs++;
            }
        }
        return pairs;
    }
}
