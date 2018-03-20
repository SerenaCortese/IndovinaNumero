package it.polito.tdp.indonumero;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//il controller è creato dall'fxml loader quindi devo farmelo restituire da lui
			//per cui devo usare fxmlLoader come oggetto 
			//in modo da richiamare più metodi da esso e non solo in nodo radice
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("IndoNumero.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Model model = new Model();
			((IndoNumeroController)loader.getController()).setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
