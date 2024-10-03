import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

import java.util.stream.Collectors;


/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author Mack Thompson (jmt0107@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    Set<String> lexicon;
    public Doublets(InputStream in) {
        lexicon = new HashSet<>();
        try (Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)))) {
            while (s.hasNext()) {
                String str = s.next();
              lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


    public int getWordCount(){
        return lexicon.size();

    }


    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
    public boolean isWord(String str){

        return lexicon.contains(str);

    }



    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
    public int getHammingDistance(String str1, String str2){
        if(str1.length()!=str2.length()){return -1;}

        int Dist = 0;
        for(int i = 0; i < str1.length(); i++)
        {
            if(str1.charAt(i)!=str2.charAt(i))
            {
                Dist++;
            }
        }

        return Dist;

    }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
    public List<String> getNeighbors(String word){
List<String> Neighbors = new ArrayList<>();

for(String str: lexicon)//improve time complexity using tree
{
    if(getHammingDistance(str,word)==1)
    {
        Neighbors.add(str);
    }
}

        return Neighbors;
    }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
    public boolean isWordLadder(List<String> sequence){
if(sequence.isEmpty()||!isWord(sequence.get(sequence.size()-1))){return false;}
        for(int i = 0; i < sequence.size() - 1; i++)
        {
            if(getHammingDistance(sequence.get(i),sequence.get(i+1))!=1||!isWord(sequence.get(i)))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Returns a minimum-length word ladder from start to end. If multiple
     * minimum-length word ladders exist, no guarantee is made regarding which
     * one is returned. If no word ladder exists, this method returns an empty
     * list.
     *
     * Breadth-first search must be used in all implementing classes.
     *
     * @param  start  the starting word
     * @param  end    the ending word
     * @return        a minimum length word ladder from start to end
     */
    public List<String> getMinLadder(String start, String end){

        if(start.length()!=end.length()){return Collections.emptyList();}

        List<String> Ladder    = new ArrayList<>();
        Set<String> Visited    = new HashSet<>();
        Deque <Node> Queue     = new ArrayDeque<>();
        List<String> Neighbors;

        start = start.toLowerCase();
        end = end.toLowerCase();

        if(start.equals(end)){Ladder.add(start); return Ladder;}

        Node n = new Node(start);
        Queue.add(n);
        Visited.add(n.word);



       while(!Queue.isEmpty())

       {

           Node currentWord = Queue.pop();
           Neighbors = getNeighbors(currentWord.word);

            for(String str: Neighbors)
            {

                 if(Objects.equals(str, end))
                {
                    Node a = new Node(str, currentWord);


                     while(a!=null)
                     {
                         Ladder.add(a.word);
                          a = a.parent;
                     }

                     Collections.reverse(Ladder);
                     return Ladder;

                }
                if(!Visited.contains(str))
                {
                     Visited.add(str);
                     Queue.add(new Node(str, currentWord));
                }
            }

       }


        return Collections.emptyList();

    }

    private static class Node
    { String word;
      Node parent = null;


      public Node(String name, Node n)

      {
          word = name;
          parent = n;


      }
        public Node(String name)

        {
            word = name;



        }

    }


}

