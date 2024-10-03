public class min {

    /**
     * Driver for the class.
     */
    public static void main(String[] args) {
        System.out.println(min1(1,2,3));
        System.out.println(min2(3,1,2));
    }


    public static int min1(int a, int b, int c) {
        if ((a < b) && (a < c)) {
            return a;
        }
        if ((b < a) && (b < c)) {
            return b;
        }
        return c;
    }


    public static int min2(int a, int b, int c) {
        if (a < b) {
            if (a < c) {
                return a;
            }
            else if (c < a) {
                return c;
            }
            else {
                return a;
            }
        }
        else {
            if (b < c) {
                return b;
            }
            else if (c < b) {
                return c;
            }
            else {
                return b;
            }
        }
    }
}
