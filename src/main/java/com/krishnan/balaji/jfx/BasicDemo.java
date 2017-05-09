package com.krishnan.balaji.jfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BasicDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();
        Scene scene = new Scene(root,400,400,Color.BLACK);

        Circle cir = new Circle(200,200,100);
        cir.setFill(Color.CORAL);
        root.getChildren().add(cir);
        
        primaryStage.setTitle("JavaFX Scene Graph Example");
        primaryStage.setScene(scene);
        primaryStage.show();
             
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}