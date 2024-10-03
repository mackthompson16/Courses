public class WordSearchGameClient {
    public static void main(String[] args) {
        WordSearchGame game = WordSearchGameFactory.createGame();
        game.loadLexicon("C:\\Users\\macke\\Downloads\\Collins Scrabble Words (2019).txt");
        game.setBoard(new String[]
                {"Y","M","E","J",
                "A","T","T","V",
                "N","E","I","N",
                "E","S","I","U"});
       // System.out.println("LENT is on the board at the following positions: ");
       // System.out.println(game.isOnBoard("SENTRY"));

       // System.out.print("POPE is not on the board: ");
       // System.out.println(game.isOnBoard("POPE"));

        System.out.println("All words of length 4 or more: ");
       System.out.println( game.getAllScorableWords(4));

    }
}