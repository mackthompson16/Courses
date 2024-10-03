import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * Provides a factory method for creating word search games.
 */
public class WordSearchGameFactory implements WordSearchGame{


        TreeSet<String> lexicon = new TreeSet<String>();
         String board [][];
        int len = 0;
        public void loadLexicon (String fileName)
        {

            if (fileName == null) {
                throw new IllegalArgumentException();
            }

            try {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine().toLowerCase();
                    String[] words = line.split(" ");
                    lexicon.add(words[0]);


                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException();
            }
        }

        public void setBoard (String[] letterArray)

        {
            if (letterArray == null || Math.sqrt(letterArray.length) % 1 != 0) {
                throw new IllegalArgumentException();
            }
            len = (int)Math.sqrt(letterArray.length);
            int index = 0;

           String[][] newboard = new String[len][len];

           for(int i = 0; i < len; i++){
               for(int j = 0; j < len; j++){

                   newboard[i][j] = letterArray[index].toLowerCase();
                   index++;
           }

        }
        board = newboard;
        }

        public String getBoard () {
            String getBoard = "";

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    getBoard += board[i][j];
                }
            }
            return getBoard;
        }

        public SortedSet<String> getAllScorableWords ( int minimumWordLength)
        {
            if(minimumWordLength < 1){ throw new IllegalArgumentException();}
            if(lexicon.isEmpty()){ throw new IllegalStateException();}
            int X=0;
            int Y=0;
            Stack<Integer> backlog = new Stack<>();
            SortedSet<String>words =  new TreeSet<>();


            int i = 0;
            while(i<len*len){

            int conventionalIndex = Y*len+X;
                backlog.push(conventionalIndex);

                String possibleWord = lexicon.ceiling(board[Y][X]);


                while(possibleWord!=null)
                {
                    if(possibleWord.length()>=minimumWordLength)
                    {

                    Stack<Integer> foundWord = DFS(X, Y, possibleWord, board[Y][X].length(), backlog);

                    if(foundWord!=null)
                    {
                        backlog = foundWord;
                        String word = "";

                        Stack <Integer> RevWord = new Stack<>();
                        while (!backlog.isEmpty())
                        {
                            RevWord.push(backlog.pop());

                        }
                        while(!RevWord.empty())
                        {
                            int index = RevWord.pop();
                            int y = 0;
                            while(index > len -1){
                                index -=len;
                                y++;
                            }
                            word += board[y][index];
                        }

                        backlog.push(conventionalIndex);
                        words.add(word);


                    }

                     }
                //update the word
                    possibleWord = lexicon.higher(possibleWord);

                    if(possibleWord!=null&&!possibleWord.startsWith(board[Y][X])){
                        possibleWord = null;
                    }

                }
        if(!backlog.isEmpty()){backlog.pop();}
        if(X<len-1){X++;} else {Y++; X=0;}
        i++;
          }

            return words;

        }

        public int getScoreForWords (SortedSet < String > words,int minimumWordLength)
        {
            if (words.isEmpty()||minimumWordLength<1) { throw new IllegalArgumentException();}
            if(lexicon.isEmpty()){     throw new IllegalStateException();}
            int score = 0;

            for(String str: words){
                if(str.length() >=minimumWordLength&&isValidWord(str)&&!isOnBoard(str).isEmpty()){
                    score +=str.length()-minimumWordLength +1;
                }

            }
            return score;
        }

        public boolean isValidWord (String wordToCheck)
        {
            if (wordToCheck == null) { throw new IllegalArgumentException();}
            if(lexicon.isEmpty()){     throw new IllegalStateException();}
            return lexicon.contains(wordToCheck.toLowerCase());
        }

        public boolean isValidPrefix (String prefixToCheck)
        {
         if (prefixToCheck == null) { throw new IllegalArgumentException();}
         if(lexicon.isEmpty()){       throw new IllegalStateException();}

            String next = lexicon.ceiling(prefixToCheck.toLowerCase());

            return next != null && next.startsWith(prefixToCheck.toLowerCase());
        }

        public List<Integer> isOnBoard (String wordToCheck)

        {

            wordToCheck = wordToCheck.toLowerCase();


            Stack<Integer> backlog = new Stack<Integer>();
            int X = 0;
            int Y = 0;

int i = 0;
    while(i<len*len)
    {
                 int conventionalIndex = len*Y + X;
                 backlog.push(conventionalIndex);


     if(wordToCheck.startsWith(board[Y][X]))
     {

       Stack<Integer> word = DFS(X, Y, wordToCheck, board[Y][X].length(), backlog);

       if (word!=null)  {backlog = word;break;}
    }

    if(X<len-1){X++;} else {Y++; X=0;}
    backlog.pop();
    i++;
    }


List<Integer> isOnBoard = new ArrayList<>();


i = 0;
    while(i <wordToCheck.length())
    {
        if (!backlog.empty())
        {
            isOnBoard.add(backlog.pop());
        }
    i++;
    }

    Collections.reverse(isOnBoard);

return isOnBoard;
        }
private Stack<Integer> DFS(int X, int Y,  String wordToCheck, int currentSize, Stack<Integer> wordlog) {
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    for (int i = 0; i < dx.length; i++) {
        if((wordlog.size()==wordToCheck.length()||currentSize==wordToCheck.length())){ return wordlog;}
int newX =X+dx[i];
int newY = Y+dy[i];

        if(newY < len && newY > -1 && newX < len && newX > -1 ){

            int conventionalIndex = len*(newY) + newX;

            if (board[newY][newX].charAt(0) == wordToCheck.charAt(currentSize) && !wordlog.contains(conventionalIndex)) {
               // System.out.println(wordToCheck + ": Neighbor for " + board[Y][X] + ": " + board[newY][newX] + ". Word length: " + (currentSize + 1) + " Index: " + conventionalIndex);

                wordlog.push(conventionalIndex);

                Stack<Integer> result = DFS(newX, newY, wordToCheck, currentSize + board[newY][newX].length(), wordlog);

                // If the path found the word, return it. Otherwise, backtrack.
                if (result != null) {
                    return result;
                }

                wordlog.pop();  // backtrack
            }
        }
    }

 return null;
}
        public static WordSearchGame createGame() {

          return new WordSearchGameFactory();

        }
    }
