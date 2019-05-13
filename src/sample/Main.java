package sample;
//Kile pilgrim
//CS 110
//JavaFx program that demonstrates a boggle game
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.ArrayList;


public class Main extends Application {

    private Game game; // the Boggle Game
    private GridPane grid;  // the board of squares
    private BorderPane mainPane;  // primary layout of application
    private VBox statusPane; //status VBox
    private VBox buttonPane; //buttons VBox
    private VBox labelPane; //place for score and word labels
    private Text status; //status variable
    private Text title; //title variable
    private Label scoreLabel; //label for score
    private Label validWords; //label for validWords
    private String temp = ""; //temp empty string
    private Button endGame, testSelected, exitGame, newGame; //Buttons for boggle game
    private ArrayList<Button> selectedButtons = new ArrayList<Button>(); //Arraylist holding selectedButtons
    private Button[][] boardButtons = new Button[4][4]; //Arraylist of boardButtons


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Boggle");
        // set up panes
        mainPane = new BorderPane(root);
        mainPane.setStyle("-fx-background-color: gray;" + "-fx-color: black;");
        grid = new GridPane();
        // initialize game and draw board
        game = new Game();
        drawBoard();
        //center grid
        mainPane.setCenter(grid);
        //pad grid
        grid.setPadding(new Insets(25, 25, 25, 25));

        statusPane = new VBox();
        title = new Text("The Game of Boggle");
        title.setStyle("-fx-border-width: 5;" + "-fx-font-size: 50");

        statusPane.setAlignment(Pos.CENTER);
        status = new Text("Press a tile to start");
        status.setFont(Font.font("Times New Roman", 50));
        status.setFill(Color.HONEYDEW);
        statusPane.getChildren().add(title);
        statusPane.getChildren().add(status);
        mainPane.setTop(statusPane);


        buttonPane = new VBox(200);
        buttonPane.setAlignment(Pos.CENTER);


        endGame = new Button("End Game");//End game button shows user their points that they won along with the option for a new game.
        endGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int temp = game.getTotalPoints();
                grid.getChildren().clear();
                buttonPane.getChildren().clear();
                labelPane.getChildren().clear();
                String winningText = "You got " + temp + " points!";
                status.setFill(Color.BLACK);
                status.setText(winningText);
                ;
                mainPane.setRight(newGame);
                mainPane.setLeft(exitGame);
            }
        });
        buttonPane.getChildren().add(endGame);

        //exitGame button and Event Handler for when pressed
        exitGame = new Button("Exit Game");
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        //newGame button and Event Handler for when pressed
        newGame = new Button("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                mainPane.getChildren().clear();
                mainPane.setTop(statusPane);
                grid = new GridPane();
                game.newGame();
                drawBoard();
                mainPane.setCenter(grid);
                grid.setPadding(new Insets(25, 25, 25, 25));
                buttonPane.getChildren().add(endGame);
                buttonPane.getChildren().add(testSelected);
                mainPane.setRight(buttonPane);

                status.setText("New Game Started");
                status.setFont(Font.font("Times New Roman", 30));
                status.setFill(Color.BLACK);
            }
        });

        //testSelected button and Event Handler for when pressed
        testSelected = new Button("Test word");
        testSelected.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int temp = game.getCorrectWords().size();
                game.testSelected();
                if (game.getCorrectWords().size() == temp + 1) { //if a valid word gets added than getCorrectWords will equal temp+1
                    status.setText("Correct Word!");
                    status.setFont(Font.font("Times New Roman", 30));
                    status.setFill(Color.DARKGREEN);
                } else { //if getCorrectWords does not have +1 then the word was not valid
                    status.setText("Invalid Word/Already Selected Word!");
                    status.setFont(Font.font("Times New Roman", 30));
                    status.setFill(Color.DARKRED);
                }
                drawBoard();
            }
        });
        buttonPane.getChildren().add(testSelected); //add testSelected to buttonPane

        mainPane.setRight(buttonPane);

        // complete setup
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    //Take the information from the button clicked on the board and either adds or removes from selectedLists
    private void boardButtonClicked(Button clickedBoardButton) {
        status.setFont(Font.font("Times New Roman", 40));
        status.setFill(Color.HONEYDEW);
        int[] btnCords = getBoardButtonCoordinates(clickedBoardButton);
        int row = btnCords[0];
        int col = btnCords[1];
        Tile selectedTile = game.getTile(row, col);

        if (game.isAlreadySelected(selectedTile)) {
            game.removeFromSelected(btnCords[0], btnCords[1]); //remove from selected
            for (int i = selectedButtons.size() - 1; i > selectedButtons.indexOf(clickedBoardButton); i--) {
                //set button back to normal color if unselected
                selectedButtons.get(i).setStyle("-fx-border-color: honeydew;" +
                        "-fx-border-width: 5;" + "-fx-font-size: 20");
                status.setText("Deselected Tile(s)");
                selectedButtons.remove(i);
            }
            clickedBoardButton.setStyle("-fx-border-color: honeydew;" +
                    "-fx-border-width: 5;" + "-fx-font-size: 20");

        } else {
            if (game.isValidSelection(row, col)) {
                game.addToSelected(btnCords[0], btnCords[1]); //add to selected
                selectedButtons.add(clickedBoardButton); //add to selectedButtons
                //make button blue
                clickedBoardButton.setStyle("-fx-border-color: blue;" +
                        "-fx-border-width: 5;" + "-fx-font-size: 50");
                status.setText("Valid Selection");
            } else {
                status.setText("Invalid Selection");
            }
        }
    }

    //get the coordinates of the button pressed and return them in an array
    private int[] getBoardButtonCoordinates(Button clickedBoardButton) {
        int[] cords = new int[2];
        for (int row = 0; row < boardButtons.length; row++) {
            for (int col = 0; col < boardButtons[row].length; col++) {
                if (boardButtons[row][col].equals(clickedBoardButton)) {
                    cords[0] = row;
                    cords[1] = col;
                }
            }
        }
        return cords;
    }


    // using information from game, create cell panes
    public void drawBoard() {

        grid.getChildren().clear(); // clear the board

        //labels for validWords and score are created in the board so they can be updated as the game is played
        labelPane = new VBox(10);
        labelPane.setAlignment(Pos.CENTER);
        scoreLabel = new Label("Score: " + game.getTotalPoints());
        scoreLabel.setStyle("-fx-color: honeydew;" +
                "-fx-border-width: 5;" + "-fx-font-size: 20");
        labelPane.getChildren().add(scoreLabel);

        validWords = new Label("Correct Words: " + game.getCorrectWords());
        validWords.setStyle("-fx-color: honeydew;" +
                "-fx-border-width: 5;" + "-fx-font-size: 20");
        labelPane.getChildren().add(validWords);
        mainPane.setBottom(labelPane);

        //create the 4x4 board of boardButtons and add to grid by row and column
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++) {
                Tile tile = game.getTile(r, c);
                if (tile.getTileLetterQu() != null) { //Deals with tile letter Qu
                    Button cp = new Button(game.getTile(r, c).getTileLetterQu());
                    boardButtons[r][c] = cp;
                    cp.setPrefWidth(150);
                    cp.setPrefHeight(130);
                    cp.setStyle("-fx-border-color: honeydew;"
                            + "-fx-border-width: 5;" + "-fx-font-size: 20");
                    cp.setOnAction((event) -> boardButtonClicked(cp));//if a button is clicked it will go to boardButtonClicked event handler
                    grid.add(cp, c, r);
                } else { //deals with the rest of the letters
                    char tempLetter = tile.getTileLetter();
                    temp = Character.toString(tempLetter);
                    Button cp = new Button(temp);
                    boardButtons[r][c] = cp;
                    cp.setPrefWidth(150);
                    cp.setPrefHeight(130);
                    cp.setStyle("-fx-border-color: honeydew;"
                            + "-fx-border-width: 5;" + "-fx-font-size: 20");
                    cp.setOnAction((event) -> boardButtonClicked(cp));//if a button is clicked it will go to boardButtonClicked event handler
                    grid.add(cp, c, r);
                }
            }
    }

    //Launch the main arg
    public static void main(String[] args) {
        launch(args);
    }
}
