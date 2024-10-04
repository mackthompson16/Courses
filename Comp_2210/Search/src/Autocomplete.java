import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {
    public static void main(String[] args) {
        Term[] terms = new Term[5];
        terms[0] = new Term("abcd",2) ;
        terms[1] = new Term("a",4) ;
        terms[2] = new Term("abcde",6) ;
        terms[3] = new Term("ab",8) ;
        terms[4] = new Term("abc",10) ;
        Term[] a = allMatches(terms, "a");
        for(Term t:a) {
            System.out.println(t.toString());
        }
    }
                  //   [abcd       2, a       4, abcde       6, ab       8, abc       10]



    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
   /* public Autocomplete(Term[] t) {

        if (t == null) {
            throw new NullPointerException();
        }
        terms = t;
    }
    */
    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public static Term[] allMatches(Term[]terms, String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }

        int count = 0;
        for (Term t : terms) {
            if (t.query.startsWith(prefix)) {
                count++;
            }
        }
        Term[] matches = new Term[count];
        int index = 0;
        for (Term t : terms) {

            if (t.query.startsWith(prefix)) {

                matches[index] = t;
                index++;
            }
        }
        for (int i = 0; i < count + 1 ; i++) {
            for (int j = 1; i + j < count; j++) {

                if (matches[i].weight < matches[i + j].weight) {
                    Term temp = matches[i];
                    matches[i] = matches[i + j];
                    matches[i + j] = temp;
                }

            }
        }
        return matches;
    }
}





