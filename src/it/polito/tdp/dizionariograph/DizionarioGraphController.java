package it.polito.tdp.dizionariograph;

import java.util.List;

import it.polito.tdp.dizionariograph.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

	Model model=new Model();
	
	public void setModel(Model model) {
		
		this.model=model;
		
	}
	
	
    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGraph;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGrado;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaVicini(ActionEvent event) {
    	
         txtResult.clear();
         
       
         
         
         
        List<String> result= model.displayNeighbours(txtParola.getText());
    	 
    	 
         String elenco="";
         
         for(String s:result) {
        	 
        	 elenco+=s;
        	 
        	 elenco+="\n";
         }
         
         
         txtResult.setText(elenco);

    }

    @FXML
    void doCrea(ActionEvent event) {
    	
    	model.createGraph(Integer.parseInt(txtLettere.getText()));
    	
        txtResult.setText("Grafo creato con successo");
    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {

    	
    	txtResult.clear();
    	
    	txtResult.setText(((Integer)model.findMaxDegree()).toString());
    	
    	
    	
    }

}
