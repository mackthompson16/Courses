import java.util.ArrayList;

/**
 * Defines a library of selection methods
 * on arrays of ints.
 *
 * @author   YOUR NAME (JMT0107@auburn.edu)
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  TODAY
 *
 */
public final class Selector {
    public static void main(String[] args) {
        int[] newlist = {-3, -7, -3, -3, -1, -9, -1, -1, -1, -5};
     print(newlist);
        System.out.println("list was:" + print(newlist));
        System.out.println("result: " + kmin(newlist, 4));
        System.out.println("list is now " + print(newlist));
    }
    public static String print(int[]a){
        String s = "";
        for(int i = 0; i < a.length-1; i++) {
            s+= a[i] + ", ";
        }
       s+=a[a.length-1];
        return s;
    }
    private Selector() {
    }

    public static int min(int[] a) {
        if(a==null||a.length==0){throw new IllegalArgumentException("List must contain values");}
        int min = a[0];
        for (int i = 1; i < a.length; i++) {

            if (a[i] < min) {
                min = a[i];
            }

        }
        return min;
    }

    public static int max(int[] a) {
        if(a==null||a.length==0){throw new IllegalArgumentException("List must contain values");}
        int max = a[0];

        for (int i = 1; i < a.length; i++) {

            if (a[i] > max) {

                max = a[i];
            }
        }
        return max;
    }

    public static int kmin(int[] a, int k) {


        if(a==null||a.length==0){throw new IllegalArgumentException("List must contain values");}

        int [] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);



        if(k>sort(b).length){throw new IllegalArgumentException("Invalid K");}

        return sort(b)[k-1];
    }

    private static int[] sort(int[] a) {


        int count = 1;

        for (int i = 0; i < a.length; i++) {


            for (int b = 1; i + b < a.length; b++) {
                if (a[i] > a[i + b]) {
                    int temp = a[i];
                    a[i] = a[i + b];
                    a[i + b] = temp;
                }

            }
        }

        //eliminate duplicates

        for(int i = 0; i < a.length-1; i++) {
            if ( a[i] != a[i + 1]) {
                count++;
            }
        }

        int [] thin = new int [count];


        thin [0] = a[0];
        int index = 1;
        for (int i = 0; i< a.length-1; i++) {


            if (a[i] != a[i + 1]) {

                thin[index] = a[i+1];
                index++;
            }
        }


        return thin;
    }

    public static int kmax(int[] a, int k) {

        if (a == null || a.length == 0) throw new IllegalArgumentException("List must contain values");

        int [] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);

        if (k>sort(b).length) throw new IllegalArgumentException("Invalid K");

        return  sort(b)[sort(b).length - k];

    }

    public static int[] range(int[] a, int low, int high) {

        if(a==null||a.length==0){throw new IllegalArgumentException("List must contain values");}

        int count = 0;
        for(int i = 0; i < a.length; i++) {
            if (a[i] >= low && a[i] <= high) {
                count++;
            }
        }

        int[] range = new int[count];
        int currentIndex = 0;

        for(int i = 0; i < a.length; i++) {
            if (a[i] >= low && a[i] <= high) {
                range[currentIndex] = a[i];
                currentIndex++;
            }
        }

        return range;
    }

    public static int ceiling(int[] a, int key) {

        if(a==null||a.length==0){throw new IllegalArgumentException("List must contain values");}

        int [] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);


        if(max(b)<key){throw new IllegalArgumentException("Key must be in range of values");}
        int min = min(b);
        for (int i = 1; i < b.length; i++) {

            if (min >= key) {
                return min;
            }
            min = kmin(b, i+1);

        }
        return max(b);
    }

    public static int floor(int[] a, int key) {

        if(a==null||a.length==0){throw new IllegalArgumentException("List must contain values");}

        int [] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);


        if(min(b)>key){throw new IllegalArgumentException("Key must be in range of values");}

        int max = max(b);
        for (int i = 1; i < b.length; i++) {
            System.out.println(max);
            if (max <= key) {
                return max;
            }

           else max = kmax(b, i+1);

        }
        return min(b);
    }
}
