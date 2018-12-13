package sample;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ListaFajlovaModel implements Runnable{

    private SimpleStringProperty trazena = new SimpleStringProperty();
    public static String put = "C:\\Users\\Nadija";
    public Controller cont;
    private boolean pretragaUToku = true;

    public void setPretraga(Boolean b){
        pretragaUToku = b;
    }

    public ListaFajlovaModel(Controller c){
        cont=c;
    }
    public String getTrazena(){
        return trazena.get();
    }
    public void setTrazena(String s){
        trazena.set(s);
    }
    @Override
    public void run() {
        if(!pretragaUToku){
            return;
        }
        try {
            File file = new File(put); //"C:\\Users\\Nadija"
            File[] putevi = file.listFiles();
            for (File f : putevi) {
                if (f.isFile() && f.getAbsolutePath().contains(getTrazena())) {
                    Platform.runLater(()->{
                        cont.getFajlovi().add(f);
                    });
                    System.out.println(f.getAbsolutePath());
                } else if (f.isDirectory()){
                    put = f.getAbsolutePath();
                    run();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    public void stop(){
        pretragaUToku = false;
    }
}
