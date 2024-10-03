public class interview {
    public static void main(String[] args) {
        int []nums = {1,2,3,4};
        System.out.println(twice(nums));
    }
    public static boolean twice(int[] a){
        for(int i = 0; i<a.length; i++){
            for(int j = 1; j+i<a.length; j++){
                if(a[i]==a[i+j]){return true;}
            }
        }
        return false;
    }
}
