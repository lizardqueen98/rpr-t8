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

public class ListaFajlovaModel implements Runnable{

    private ObservableList<File> fajlovi = FXCollections.observableArrayList();
    private ObjectProperty<File> trenutniFajl = new SimpleObjectProperty<>();
    private SimpleStringProperty trazena = new SimpleStringProperty();
    public static String put = "C:\\Users\\Nadija";

    public ListaFajlovaModel(String s){
        trazena.setValue(s);
    }
    public String getTrazena(){
        return trazena.get();
    }
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
    /*public void napuni(String put) {
        try {
            File file = new File(put); //"C:\\Users\\Nadija"
            File[] putevi = file.listFiles();
            for (File f : putevi) {
                if (f.isFile() && f.getAbsolutePath().contains(getTrazena())) {
                    fajlovi.add(f);
                    //System.out.println(f.getAbsolutePath());
                } else if (f.isDirectory()) napuni(f.getAbsolutePath());
            }
        } catch (Exception e) {
            //System.out.println(e.getCause());
        }
    }*/

    @Override
    public void run() {
        try {
            File file = new File(put); //"C:\\Users\\Nadija"
            File[] putevi = file.listFiles();
            for (File f : putevi) {
                if (f.isFile() && f.getAbsolutePath().contains(getTrazena())) {
                    fajlovi.add(f);
                    //System.out.println(f.getAbsolutePath());
                } else if (f.isDirectory()){
                    put = f.getAbsolutePath();
                    run();
                }
            }
        } catch (Exception e) {
            //System.out.println(e.getCause());
        }
    }
}
