package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class noviController implements Initializable{
    public TextField ime;
    public TextField prezime;
    public TextField adresa;
    public TextField broj;
    public ComboBox grad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grad.getItems().addAll("Sarajevo", "Mostar", "Zenica", "Foca", "Tuzla", "Doboj");
        new Thread(()-> {
            ime.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                    if (!n) {
                        if (validanNaziv(ime.getText())) {
                            ime.getStyleClass().removeAll("poljeNijeIspravno");
                            ime.getStyleClass().add("poljeIspravno");
                        } else {
                            ime.getStyleClass().removeAll("poljeIspravno");
                            ime.getStyleClass().add("poljeNijeIspravno");
                        }
                    }
                }
            });
            prezime.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                    if (!n) {
                        if (validanNaziv(prezime.getText())) {
                            prezime.getStyleClass().removeAll("poljeNijeIspravno");
                            prezime.getStyleClass().add("poljeIspravno");
                        } else {
                            prezime.getStyleClass().removeAll("poljeIspravno");
                            prezime.getStyleClass().add("poljeNijeIspravno");
                        }
                    }
                }
            });
            adresa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                    if (!n) {
                        if (validnaAdresa(adresa.getText())) {
                            adresa.getStyleClass().removeAll("poljeNijeIspravno");
                            adresa.getStyleClass().add("poljeIspravno");
                        } else {
                            adresa.getStyleClass().removeAll("poljeIspravno");
                            adresa.getStyleClass().add("poljeNijeIspravno");
                        }
                    }
                }
            });
                broj.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                        if (!n) {
                            if (validanBroj(broj.getText())) {
                                broj.getStyleClass().removeAll("poljeNijeIspravno");
                                broj.getStyleClass().add("poljeIspravno");
                            } else {
                                broj.getStyleClass().removeAll("poljeIspravno");
                                broj.getStyleClass().add("poljeNijeIspravno");
                            }
                        }
                    }
                });
        }).start();
    }
    private boolean validnaAdresa(String novo) {
        if(novo.length()==0)
            return false;

        if(!novo.contains(" "))
            return false;

        String ime_ulice = novo.substring(0, novo.indexOf(' '));
        String broj_ulice = novo.substring(novo.indexOf(' ')+1, novo.length());

        if(!ime_ulice.matches("[a-zA-Z]+") || !broj_ulice.matches("[0-9]+"))
            return false;

        return true;
    }
    public boolean validanNaziv(String novo_ime){
        if(novo_ime.length()>20 || novo_ime.length()<=0) return false;
        if(!novo_ime.matches("[a-zA-Z]+")) return false;
        if(Character.isLowerCase(novo_ime.charAt(0))) return false;
        for(int i=1; i<novo_ime.length(); i++)
        {
            if(Character.isUpperCase(novo_ime.charAt(i)))
                return false;
        }
        return true;
    }
    public boolean validanBroj(String novi_broj){
        String adresa = "http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=";
        String validan =  null;
        Platform.runLater(()->{
            try{
                URL url = new URL(adresa+novi_broj);
                BufferedReader ulaz = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                //String validan = ulaz.readLine();
                //System.out.println(validan);
            }catch(Exception e){
                System.out.println("Nesto ne valja.");
            }
        });
        if(validan.equals("OK")) return true;
        return false;
    }
    private void upozorenje(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Greška");
        alert.setHeaderText("Forma nije validna!");
        alert.setContentText("Unesite ispravne podatke!");
        alert.showAndWait();
    }
    public void salji(ActionEvent actionEvent) {
        if(!validno()) {
            upozorenje(actionEvent);
        }
        else{
            //da ako je sve ispravno, znaci da je poslano, prozor se zatvara
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
    public boolean validno(){
        ArrayList<ObservableList<String>> validno = new ArrayList<>();

        validno.add(ime.getStyleClass());
        validno.add(prezime.getStyleClass());
        validno.add(broj.getStyleClass());
        validno.add(adresa.getStyleClass());

        for(ObservableList<String> o : validno)
        {
            if(o.contains("poljeNijeIspravno"))
                return false;
        }
        return true;
    }
}
