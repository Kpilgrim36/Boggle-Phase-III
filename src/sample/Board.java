package sample;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    //Arraylist of arraylists named tiles
    public ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();


    //Initial Fields
    String randomChar;

    /**
     * Board Constructor that sets up a 4 by 4 grid of random letters, using sample.Tile objects with different columns and rows
     */
    public Board() {
        ArrayList<Tile> row0 = new ArrayList<>(4);
        row0.add(new Tile(randomCharacter(), 0, 0));
        row0.add(new Tile(randomCharacter(), 0, 1));
        row0.add(new Tile(randomCharacter(), 0, 2));
        row0.add(new Tile(randomCharacter(), 0, 3));
        tiles.add(row0);

        ArrayList<Tile> row1 = new ArrayList<>(4);
        row1.add(new Tile(randomCharacter(), 1, 0));
        row1.add(new Tile(randomCharacter(), 1, 1));
        row1.add(new Tile(randomCharacter(), 1, 2));
        row1.add(new Tile(randomCharacter(), 1, 3));
        tiles.add(row1);

        ArrayList<Tile> row2 = new ArrayList<>(4);
        row2.add(new Tile(randomCharacter(), 2, 0));
        row2.add(new Tile(randomCharacter(), 2, 1));
        row2.add(new Tile(randomCharacter(), 2, 2));
        row2.add(new Tile(randomCharacter(), 2, 3));
        tiles.add(row2);

        ArrayList<Tile> row3 = new ArrayList<>(4);
        row3.add(new Tile(randomCharacter(), 3, 0));
        row3.add(new Tile(randomCharacter(), 3, 1));
        row3.add(new Tile(randomCharacter(), 3, 2));
        row3.add(new Tile(randomCharacter(), 3, 3));
        tiles.add(row3);

    }

    /**
     * The Array of 16 die all with six sides all set to a letter
     *
     * @return returns the Array die with 16 dice in it
     */
    public ArrayList<String> dieArray() {
        ArrayList<String> die = new ArrayList<>(16);
        String die0 = ("R" + "I" + "F" + "O" + "B" + "X");
        String die1 = ("I" + "F" + "E" + "H" + "E" + "Y");
        String die2 = ("D" + "E" + "N" + "O" + "W" + "S");
        String die3 = ("U" + "T" + "O" + "K" + "N" + "D");
        String die4 = ("H" + "M" + "S" + "R" + "A" + "O");
        String die5 = ("L" + "U" + "P" + "E" + "T" + "S");
        String die6 = ("A" + "C" + "I" + "T" + "O" + "A");
        String die7 = ("Y" + "L" + "G" + "K" + "U" + "E");
        String die8 = ("Qu" + "B" + "M" + "J" + "O" + "A");
        String die9 = ("E" + "H" + "I" + "S" + "P" + "N");
        String die10 = ("V" + "E" + "T" + "I" + "G" + "N");
        String die11 = ("B" + "A" + "L" + "I" + "Y" + "T");
        String die12 = ("E" + "Z" + "A" + "V" + "N" + "D");
        String die13 = ("R" + "A" + "L" + "E" + "S" + "C");
        String die14 = ("U" + "W" + "I" + "L" + "R" + "G");
        String die15 = ("P" + "A" + "C" + "E" + "M" + "D");
        //Add 16 die to ArrayList die
        die.add(die0);
        die.add(die1);
        die.add(die2);
        die.add(die3);
        die.add(die4);
        die.add(die5);
        die.add(die6);
        die.add(die7);
        die.add(die8);
        die.add(die9);
        die.add(die10);
        die.add(die11);
        die.add(die12);
        die.add(die13);
        die.add(die14);
        die.add(die15);

        return die;
    }

    /**
     * Generates a random Character from the die array and sets it accordingly if its a Qu tile
     *
     * @return returns the random Character
     */
    public String randomCharacter() {
        Random ran = new Random();
        int randomNumber = ran.nextInt(dieArray().size());
        //Dealing with Qu die number
        if (randomNumber == 8) {
            String randomDie = dieArray().get(randomNumber);
            char[] charArray = randomDie.toCharArray();
            int randomNumber2 = ran.nextInt(charArray.length);
            if (randomNumber2 == 0 || randomNumber2 == 1) {
                randomChar = "Qu";
                return randomChar;
            } else {
                char randomChar = charArray[randomNumber2];
                String randomCharToString = String.valueOf(randomChar);
                return randomCharToString;

            }
        } else {
            String randomDie = dieArray().get(randomNumber);
            char[] charArray = randomDie.toCharArray();
            int randomNumber2 = ran.nextInt(charArray.length);
            char randomChar = charArray[randomNumber2];
            String randomCharToString = String.valueOf(randomChar);
            return randomCharToString;

        }

    }

    //Getter for randomChar
    public String getRandomChar() {
        return randomChar;
    }

    public Tile getTileAt(int row, int column) {
        Tile returnTile = tiles.get(row).get(column);
        return returnTile;
    }

    /**
     * Override for toString method. Returns the content of the ArrayList tile and puts together a 4x4 board of random Characters.
     *
     * @return returns the 4x4 board of random characters
     */
    @Override
    public String toString() {
        //Return String variable
        String returnString = "";
        //Fancy super neat code for making the board appear as a grid when printed
        for (int i = 0; i <= 16; i++) {
            if (i == 4 || i == 8 || i == 12 || i == 16) {
                returnString += "\n";
            }
            for (ArrayList<Tile> tileArray : tiles) {
                for (Tile tile : tileArray) {
                    if (tile.getTileLetterQu() != null) {

                        returnString += tile.getTileLetterQu();
                        returnString += " ";
                        i++;
                        if (i == 4 || i == 8 || i == 12 || i == 16) {
                            returnString += "\n";
                        }
                    } else {
                        returnString += tile.getTileLetter();
                        returnString += "  ";
                        i++;
                        if (i == 4 || i == 8 || i == 12) {
                            returnString += "\n";
                        }
                    }
                }
            }
        }
        return returnString;
    }

}