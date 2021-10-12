 package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	@FXML
	private MenuItem menuItemSobre;
	@FXML
	private MenuItem menuItemDepartamento;
	@FXML
	private MenuItem menuItemFuncionario;
	
	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/About.fxml");
	}
	@FXML
	public void onMenuItemDepartamentoAction() {
		loadView("/gui/DepartamentList.fxml");
	}
	@FXML
	public void onMenuItemFuncionarioAction() {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized void loadView(String absoluteName) {		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox =loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) (  (ScrollPane) mainScene.getRoot()  ).getContent();
			
			Node mainMenu = mainVbox.getChildren().get(0);
			
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVbox.getChildren());
						
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro Carregando a tela", e.getMessage(),AlertType.ERROR );
			e.printStackTrace();
		}
	};
}
