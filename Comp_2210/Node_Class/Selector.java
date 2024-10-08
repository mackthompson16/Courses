import java.util.*;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Mack Thompson (JMT0107@auburn.edu)
 *
 */

public final class Selector {
    public static void main(String[] args) {
Collection<Integer> coll = new ArrayList<Integer>();
coll.add(2);
coll.add(2);
coll.add(3);

        System.out.println(kmin(coll,0,comp));
        print(coll);
    }


    public static String print(Collection<Integer> a){
        String s = "";
        for(Integer obj: a) {
            s+= obj + ", ";
        }

        return s;
    }
    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }

    public static Comparator<Integer> comp = new Comparator<Integer>() {

        public int compare(Integer a, Integer b) {

            return a - b;
        }
    };

    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if(coll==null){throw new IllegalArgumentException();}
        if(coll.isEmpty()){throw new NoSuchElementException();}
       T min =  null;
        for(T obj : coll){
                if(min == null){min = obj;}
                if(comp.compare(obj,min)<0){min=obj;}
        }

        return min;
    }



    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {

        if(coll==null){throw new IllegalArgumentException();}
        if(coll.isEmpty()){throw new NoSuchElementException();}
        T max =  null;
        for(T obj : coll){
            if(max == null){max = obj;}
            if(comp.compare(obj,max)>0){max=obj;}
        }

        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        if(coll==null||comp==null){throw new IllegalArgumentException();}
        Set<T> set = new HashSet<>(coll);
        if(coll.isEmpty()||k>set.size()||k<=0){throw new NoSuchElementException();}


        T kmin = null;

        for(int i = k; i>0; i--){

         kmin = min(set, comp);
         set.remove(kmin);

        }


        return kmin;
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {

        if(coll==null||comp==null){throw new IllegalArgumentException();}
        Set<T> set = new HashSet<>(coll);
        if(coll.isEmpty()||k>set.size()||k==0){throw new NoSuchElementException();}

        T kmax = null;

        for(int i = k; i>0; i--){

            kmax = max(set, comp);
            set.remove(kmax);

        }


        return kmax;
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                          Comparator<T> comp) {

        if(coll==null||comp==null){throw new IllegalArgumentException();}
        T max = max(coll,comp);
        T min = min(coll, comp);
        if(coll.isEmpty()||comp.compare(max,low)<0||comp.compare(high,min)<0){throw new NoSuchElementException();}

        Collection<T> range = new ArrayList<T>();

        for(T obj : coll){

            if(comp.compare(obj,low)>=0&&comp.compare(obj,high)<=0){
                System.out.println(obj);
                range.add(obj);
            }
        }
        return range;
    }



    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {


        if(coll==null||comp==null){throw new IllegalArgumentException();}
        T max = max(coll,comp);

        if(coll.isEmpty()||comp.compare(max,key)<0){throw new NoSuchElementException();}
        if(comp.compare(min(coll,comp),key)==0){return key;}

        return min(range(coll,key,max,comp),comp);

    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {

        if(coll==null||comp==null){throw new IllegalArgumentException();}
        T min = min(coll,comp);

        if(coll.isEmpty()||comp.compare(key,min)<0){throw new NoSuchElementException();}
if(comp.compare(max(coll,comp),key)==0){return key;}
        return max(range(coll,min,key,comp),comp);
    }

}
