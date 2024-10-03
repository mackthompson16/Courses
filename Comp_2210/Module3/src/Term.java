import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;

public class Term implements Comparable<Term> {
    public static void main(String[] args) {
    }
    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public String query;
    public long weight;
    public Term(String q, long w) {

        if (q == null ) {throw new NullPointerException();}
        if (w < 0){ throw new IllegalArgumentException();}

        query = q;
        weight = w;

        /**
         * Compares the two terms in descending order of weight.
         */
    }
    public static Comparator<Term> byDescendingWeightOrder() {

        return new Comparator<Term>() {
            public int compare(Term term1, Term term2) {

                    long dif = term2.weight - term1.weight;
if(dif>0){return 1;}
if(dif==0){return 0;}
return -1;

            }
        };
    }
    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }

        return new Comparator<Term>() {
            @Override
            public int compare(Term term1, Term term2) {
                int len = length;

if(term1.query.length()<len){ len = term1.query.length();}
if(term2.query.length()<len){ len = term2.query.length();}

                String prefix1 = term1.query.substring(0, len);
                String prefix2 = term2.query.substring(0, len);

                return prefix1.compareTo(prefix2);
            }
        };
    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {

        return query.compareTo(other.query);

    }

    @Override
    public String toString(){

        return (query + "\t" + weight);

    }

}