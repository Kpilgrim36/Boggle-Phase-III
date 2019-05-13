package sample;

public class Tile {
    //Initial Fields
    private String qu;
    private char letter;
    private int row;
    private int column;


    /**
     * sample.Tile Constructor that takes in a char letter, int row, and int column. Sets it to a variables.
     *
     * @param letter random letter
     * @param row    row number
     * @param column column number
     */
    public Tile() {

    }

    public Tile(char letter, int row, int column) {
        this.row = row;
        this.column = column;
        this.letter = letter;
    }

    /**
     * Overloaded sample.Tile constructor that takes in a String instead of char
     *
     * @param letter sets letter variable
     * @param row    sets row number
     * @param column sets column number
     */
    public Tile(String letter, int row, int column) {
        if (letter.length() == 1) {
            this.letter = letter.charAt(0);
        } else {
            this.qu = letter;
        }
        this.row = row;
        this.column = column;
    }

    //Getter for tileLetter
    public char getTileLetter() {
        return this.letter;
    }

    //Getter for tileLetterQu
    public String getTileLetterQu() {
        return this.qu;
    }

    //Getter for tileRow
    public int getTileRow() {
        return this.row;
    }

    //Getter for TileColumn
    public int getTileColumn() {
        return this.column;
    }


    /**
     * toString Override method that sets the tile to a string
     *
     * @return returns the tile as a string
     */
    @Override
    public String toString() {
        String w = "";
        w += letter + " ";
        w += row + " ";
        w += column + " ";
        return w;
    }

}
