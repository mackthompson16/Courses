import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

/**
 * ExampleClient.java
 * Provides example calls to WordLadderGame methods in an instance of
 * the Doublets class.
 *
 * The word list files must be extracted into the current directory
 * before running this class.
 *
 *      jar xf WordLists.jar
 *
 * @author Dean Hendrix (dh@auburn.edu)
 */
public class ExampleClient {

    /** Drives execution. */
    public static void main(String[] args) throws FileNotFoundException {
        WordLadderGame doublets = new Doublets(new FileInputStream(new File("C:\\Users\\macke\\OneDrive\\Desktop\\small1.txt")));



        System.out.println(doublets.getWordCount());

        System.out.println(doublets.isWordLadder(Arrays.asList("cat","rat", "hat", "bat", "sat", "pat", "tat", "mat")));
        System.out.println(doublets.getMinLadder("cat","dog"));
        System.out.println(doublets.isWord("aa"));
    }
}

/*

RUNTIME OUTPUT

0
4
-1
2
true
true
false
267751
false
true
[liger, niger, tiler, timer, titer, tiges]
[cat, hat]

 */
