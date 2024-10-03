
public class GenericsA {

    public static <T> int search(T[] a, T target) {

        for (int i = 0; i < a.length; i++) {

            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Integer[] a1 = {4, 10, 2, 8, 6};
        int i = GenericsA.<Integer>search(a1, 8);
        System.out.println(i);
    }

}