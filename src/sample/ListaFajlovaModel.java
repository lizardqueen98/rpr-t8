package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ListaFajlovaModel {

    private ObservableList<File> fajlovi = FXCollections.observableArrayList();
    private ObjectProperty<File> trenutniFajl = new SimpleObjectProperty<>();

    public ObservableList<File> getFajlovi(){
        return fajlovi;
    }
    public ObjectProperty<File> trenutniProperty(){
        return trenutniFajl;
    }
    public File getTrenutni(){
        return trenutniFajl.get();
    }
    public void setTrenutni(File file){
        trenutniFajl.setValue(file);
    }
    public void napuni(String trazena, String put) {
        try {
            File file = new File(put); //"C:\\Users\\Nadija"
            File[] putevi = file.listFiles();
            for (File f : putevi) {
                if (f.isFile() && f.getAbsolutePath().contains(trazena)) {
                    fajlovi.add(f);
                    //System.out.println(f.getAbsolutePath());
                } else if (f.isDirectory()) napuni(trazena, f.getAbsolutePath());
            }
        } catch (Exception e) {
            //System.out.println(e.getCause());
        }
    }
}
