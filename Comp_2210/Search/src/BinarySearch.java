import java.util.Comparator;
import java.util.Arrays;
import static java.lang.Math.log;

public class BinarySearch {
    public static void main(String[] args) {
        int []a = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(firstIndexOf(a,2,comp));
    }
    public static Comparator<Integer> comp = new Comparator<Integer>() {

        public int compare(Integer a, Integer b) {

            return a - b;
        }
    };
    /**
     *
     * Returns the index of the first key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static int firstIndexOf(int[] a, Integer key, Comparator<Integer> comparator) {

if(a.length == 0 || key == null || comparator == null){ throw new NullPointerException(); }

        int index = a.length/2;


        for(int i =0; i <= log(a.length)/log(2); i++){
            System.out.println(index);
            if(index<0){return -1;}
            int  comp = (comparator.compare(a[index],key));

            if(comp == 0) {
                if(index==0){return 0;}

                while(a[index]==a[index-1]){
                    index--;
                    if(index==0){return 0;}
                }

            return index;
            }



            if(comp>0){ index -= index/2;}

            else index +=index/2;

        }


    return -1;

    }

    /**
     * Returns the index of the last key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        int len = a.length;
        if(len == 0 || key == null || comparator == null){ throw new NullPointerException(); }


        int index = len/2;


        for(int i =0; i <= log(len)/log(2); i++){
            if(index>=len){return -1;}
            int  comp = (comparator.compare(a[index],key));


            if(comp == 0) {
                if(index==len-1){return index;}

                while(a[index]==a[index+1]){
                    index++;
                    if(index==len-1){return index;}
                }

                return index;
            }

            if(comp>0){ index -= index/2;}
            else index +=index/2;

        }
        return -1;

    }
}