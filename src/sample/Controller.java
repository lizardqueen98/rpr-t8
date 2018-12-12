package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller {
    public TextField rijec;
    public ListView lista;
    public Button trazi;
    private ListaFajlovaModel model;
    /*public Controller(ListaFajlovaModel model){
        this.model = model;
    }*/
    public void trazi(ActionEvent actionEvent) {
        model = new ListaFajlovaModel();
        model.napuni(rijec.getText(),"C:\\Users\\Nadija");
        lista.setItems(model.getFajlovi());
    }

}
