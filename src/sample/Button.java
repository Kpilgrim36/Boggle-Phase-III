//package sample;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.HBox;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//public class Button extends HBox {
//
//    private int row; // cell knows it's row/col location
//    private int col;
//    private Button chButton;
//
//    public Button(String ch, int r, int c) {
//        this.setStyle("-fx-border-color: orange;"
//                + "-fx-border-width: 5;");
//        this.setPrefSize(60, 50);
//        row = r;
//        col = c;
//        setCell(ch);
//
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public int getCol() {
//        return col;
//    }
//
//
//
//    public void setCell(String ch) {
//        this.chButton = new Button(ch);
//        chButton.setPrefWidth(60);
//        chButton.setPrefHeight(50);
//        this.getChildren().add(chButton);
//        ch = "";
//        this.chButton = new Button(ch);
//
//    }
//}
//
