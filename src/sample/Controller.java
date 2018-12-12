package sample;

import javafx.application.Platform;
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

public class Controller implements Initializable{
    public TextField rijec;
    public ListView lista;
    public Button trazi;
    public Button prekini;
    private ListaFajlovaModel model;
    private ObservableList<File> fajlovi = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista.setItems(fajlovi);
    }

    public ObservableList<File> getFajlovi(){
        return fajlovi;
    }

    public void trazi(ActionEvent actionEvent) {
        model = new ListaFajlovaModel(this);
        model.setTrazena(rijec.getText());
        Thread thr = new Thread(model);
        thr.start();
    }

    public void prekini(ActionEvent actionEvent) {
    }
}
