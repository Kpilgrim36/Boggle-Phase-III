package sample;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class Game {
    //Private board
    private Board board;
    //Private dictionary
    private Dictionary dict;
    //Private ArrayLists
    private ArrayList<Tile> selectedTiles = new ArrayList<>();
    private ArrayList<String> correctWordList = new ArrayList<>();
    private ArrayList<String> selectedCharList = new ArrayList<>();
    //Private Fields
    private String testWord = "";
    private String selectedCharacter = "";
    private int totalPoints;


    /**
     * Game Constructor that sets up the board and words
     *
     * @Try catch for not IOException for Dictionary
     */
    public Game(){
        this.board = new Board();
        try {
            this.dict = new Dictionary("C:\\Users\\Kile Pilgrim\\IdeaProjects\\Boggle Phase III\\src\\sample\\dictionary.txt");
        }catch(IOException e){
            System.out.println("File not found!");
        }
    }


    public void newGame() {
        this.board = new Board();
        this.selectedCharList.clear();
        this.correctWordList.clear();
        this.selectedTiles.clear();
        this.totalPoints = 0;
    }



    /**
     * isValidSelection Method that tests to see if the selection is adjacent to the last tile.
     *
     * @param row    takes in a int row of user selection
     * @param column takes in a int column of user selection
     * @return returns a boolean value
     */
    public boolean isValidSelection(int row, int column) {
        if (selectedTiles.isEmpty()) {
            return true;
        }
        if (Math.abs(selectedTiles.get(selectedTiles.size() - 1).getTileRow() - row) < 2) {
            if (Math.abs(selectedTiles.get(selectedTiles.size() - 1).getTileColumn() - column) < 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * getTile method that shows the tile chosen at that point
     *
     * @param row    takes in a int row of user selection
     * @param column takes in a int column of user selection
     * @return returns that tile at the point in the board
     */
    public Tile getTile(int row, int column) {
        return board.getTileAt(row, column);
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

    public ArrayList<String> getCorrectWords() {
        return this.correctWordList;
    }

    /**
     * getSelectedTiles method that gets all the selected tiles
     *
     * @return returns the current selected tiles
     * Not really sure why you would want to list the selected tiles when they are already shown.
     */


    public boolean isAlreadySelected(Tile obj) {
        for (int i = 0; i <= selectedTiles.size() - 1; i++)
            if (obj == selectedTiles.get(i)) {
                return true;
            }
        return false;
    }

    public ArrayList<Tile> getSelectedTiles() {
        return selectedTiles;
    }

    /**
     * addToSelected method that adds the tile selected by the user to the ArrayList selectedTiles
     *
     * @param row    takes in a int row of user selection
     * @param column takes a in int column of user selection
     */
    public void addToSelected(int row, int column) {
        selectedTiles.add(board.getTileAt(row, column));
        for (Tile temp : selectedTiles) {
            if (temp.getTileLetterQu() != null) {
                this.selectedCharacter = temp.getTileLetterQu();
            } else {
                char letter = temp.getTileLetter();
                this.selectedCharacter = (Character.toString(letter));
            }
        }
        selectedCharList.add(this.selectedCharacter);
        //Print out console interface
        System.out.println("Selected: " + selectedCharList);
        System.out.println("Words" + correctWordList);
        System.out.println("Score: " + totalPoints);
    }

    /**
     * removeFromSelected method that removes the tile selected by the user from the ArrayList selectedTiles
     *
     * @param row    takes in a int row of user selection
     * @param column takes in a int column of user selection
     */
    public void removeFromSelected(int row, int column) {


        int temp2 = selectedTiles.indexOf(board.getTileAt(row, column));
        for (int i = selectedTiles.size() - 1; i > temp2; i--) {
            selectedTiles.remove(i);
        }
        selectedTiles.remove(board.getTileAt(row, column));
        selectedCharList.clear();
        for (Tile temp : selectedTiles) {
            if (temp.getTileLetterQu() != null) {
                this.selectedCharacter = temp.getTileLetterQu();
                selectedCharList.add(this.selectedCharacter);
            } else {
                char letter = temp.getTileLetter();
                this.selectedCharacter = (Character.toString(letter));
                selectedCharList.add(this.selectedCharacter);
            }
        }

        //Print out console interface
        System.out.println("Selected: " + selectedCharList);
        System.out.println("Words" + correctWordList);
        System.out.println("Score: " + totalPoints);
    }

    /**
     * clearSelected method that clears the ArrayLists selectedTiles and selectedCharList
     */
    public void clearSelected() {
        selectedTiles.clear();
        selectedCharList.clear();
    }

    /**
     * testSelected method that tests all the selected tiles characters as a word against the dictionary.
     * this decides weather or not the user picked a valid english word in the dictionary and adds points accordingly
     */
    public void testSelected() {

        for (Tile temp : selectedTiles) {
            if (temp.getTileLetterQu() != null) {
                this.testWord += (temp.getTileLetterQu());
            } else {
                this.testWord += (temp.getTileLetter());
            }
        }
        for(int i = 0; i<=correctWordList.size()-1;i++){
            if(correctWordList.get(i).equals(this.testWord)){
                clearSelected();
                this.testWord = "";
            }
        }
        if (dict.isValidWord(selectedTiles)&& this.testWord != "") {
            Word selectedWord = new Word(selectedTiles);
            System.out.println();
            totalPoints += selectedWord.getPoints();
            correctWordList.add(this.testWord);
            this.testWord = "";
            clearSelected();
            //Print out console interface
            System.out.println("Selected: " + selectedCharList);
            System.out.println("Words: " + correctWordList);
            System.out.println("Score: " + totalPoints);
        } else {
            System.out.println("Invalid Word");
            this.testWord = "";
            clearSelected();
        }

    }


    /**
     * Override for the toString method to return the board as a String
     *
     * @return
     */
    @Override
    public String toString() {
        String s = board.toString();
        return s;
    }
}


