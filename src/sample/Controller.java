package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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
        prekini.setDisable(true);
        lista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {
            @Override
            public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue){
                try {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("novi.fxml"));
                    Parent root1 = loader1.load();
                    Stage stage = new Stage();
                    stage.setTitle("Slanje");
                    stage.setScene(new Scene(root1, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.show();
                }catch(Exception e){

                }
            }
        });
    }

    public ObservableList<File> getFajlovi(){
        return fajlovi;
    }

    public void trazi(ActionEvent actionEvent) {
        trazi.setDisable(true);
        prekini.setDisable(false);
        fajlovi.removeAll();
        lista.getItems().clear();
        model = new ListaFajlovaModel(this);
        model.setTrazena(rijec.getText());
        model.setPretraga(true);
        Thread thr = new Thread(model);
        thr.start();
    }

    public void prekini(ActionEvent actionEvent) {
        prekini.setDisable(true);
        trazi.setDisable(false);
        model.stop();
    }
}
