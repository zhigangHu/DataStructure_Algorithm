public class JumpingClouds {
    public static void main(String[] args) {
        //Initialize String path
        int[] clouds=new int[]{0,0,1,0,0,1,0};



        System.out.println("---------------------------------------");
        System.out.println(" Jump output " + jumpingClouds(clouds));
        System.out.println("---------------------------------------");



    }
    public static int jumpingClouds(int[] c){
        int jump=0;
        for(int i=0;i<c.length-1;i++){
            jump++;
            if(i<c.length-2&&c[i+2]==0){
                i++;
            }
        }
        return jump;
    }
}
