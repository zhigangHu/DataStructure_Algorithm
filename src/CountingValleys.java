public class CountingValleys {
    public static void main(String[] args) {
        //Initialize String path
        String path="UDDDUDUU";
        //Array fr will store frequencies of element


        System.out.println("---------------------------------------");
        System.out.println(" CountingValleys output " + countingValleys(8, path));
        System.out.println("---------------------------------------");



    }

    public static int countingValleys(int steps, String path) {
        int valley=0;
        int seaLevel=0;
        char[] path_char=path.toCharArray();
        for(char c:path_char){
            if(c=='U'){
                seaLevel++;
                if(seaLevel==0){
                    valley++;
                }
            }
            else{
                seaLevel--;
            }

        }
        return valley;



    }

}
