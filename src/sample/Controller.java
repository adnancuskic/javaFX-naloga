package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements Initializable {
    public Button shranjevanjePodatkov;
    public Label obvestilo;
    public RadioButton gorivoDrugoOznaka;
    public TextField gorivoDrugoText;
    public Tab barPodatkiOZavarovancu;
    public Tab barPodatkiOZavarovanju;
    public Tab barPodatkiORegistraciji;
    public Tab barPodatkiOVozilu;
    public TextField kraj;
    public TabPane barVrstica;
    public TextField znamka;
    public TextField oznaka;
    public RadioButton gorivoBencin;
    public RadioButton gorivoDizel;
    public RadioButton gorivoElektrika;
    public TextField gorivoDrugo;
    public ToggleGroup gorivo;
    public TextField barva;
    public TextField steviloSedezev;
    public TextField prostornina;
    public RadioButton vrstaBus;
    public ToggleGroup vrsta;
    public RadioButton vrstaTruck;
    public RadioButton vrstaTractor;
    public RadioButton vrstaBike;
    public RadioButton vrstaCar;
    public TextField ime;
    public TextField priimek;
    public TextField ulica;
    public TextField hisnaStevilka;
    public TextField postnaStevilka;
    public DatePicker datumRojstva;
    public RadioButton mladVoznik;
    public ToggleGroup izkusnje;
    public RadioButton izkusenVoznik;
    public ToggleGroup osnovnoZavarovanje;
    public RadioButton AOplus;
    public RadioButton AO;
    public RadioButton polno;
    public ToggleGroup kasko;
    public RadioButton odbitnaFransiza;
    public RadioButton brez;
    public CheckBox zavarovanjeStekla;
    public CheckBox zavarovanjeParkirisce;
    public CheckBox zavarovanjePotnike;
    public CheckBox zavarovanjeTretjeOsebe;
    public CheckBox zavarovanjeGume;
    public CheckBox zavarovanjeKrajo;
    public CheckBox zavarovanjeToco;
    public DatePicker datumPrveRegistracije;
    public TextField registerskaOznacba;
    public TextField krajPrveRegistracije;
    public Label textTrenutnoOdprt;
    public Label textSteviloZavarovancev;
    public Button nadaljujGumb;
    public Button pocisti;
    public TextField moc;
    int stevec;
    boolean pejt = false;
    boolean pejt2 = false;
    boolean pejt3 = false;
    boolean pejt4 = false;

        public static List<String> findFiles(Path path, String fileExtension)
                throws IOException {
            if (!Files.isDirectory(path)) {
                throw new IllegalArgumentException("Path must be a directory!");
            }
            List<String> result;
            try (Stream<Path> walk = Files.walk(path)) {
                result = walk
                        .filter(p -> !Files.isDirectory(p))
                        // this is a path, not string,
                        // this only test if path end with a certain path
                        //.filter(p -> p.endsWith(fileExtension))
                        // convert path to string first
                        .map(p -> p.toString().toLowerCase())
                        .filter(f -> f.endsWith(fileExtension))
                        .collect(Collectors.toList());
            }
            return result;
        }
    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi CSV datoteko");
        File f = fc.showOpenDialog(null);
        if (f!=null){
            pocistiPolja();
            barVrstica.setDisable(false);
            barPodatkiOVozilu.setDisable(false);
            barPodatkiOZavarovancu.setDisable(true);
            barPodatkiOZavarovanju.setDisable(true);
            barPodatkiORegistraciji.setDisable(true);
            nadaljujGumb.setDisable(false);
            try(BufferedReader csvReader = new BufferedReader(new FileReader(f))) {
                String line;
                csvReader.readLine();
                while ((line=csvReader.readLine()) != null){
                    String[] data = line.split(",");
                    znamka.setText(data[0]);
                    oznaka.setText(data[1]);
                    gorivoBencin.setSelected(Boolean.parseBoolean(data[2]));
                    gorivoDizel.setSelected(Boolean.parseBoolean(data[3]));
                    gorivoElektrika.setSelected(Boolean.parseBoolean(data[4]));
                    gorivoDrugoOznaka.setSelected(Boolean.parseBoolean(data[5]));
                    gorivoDrugoText.setText(data[6]);
                    steviloSedezev.setText(data[7]);
                    prostornina.setText(data[8]);
                    moc.setText(data[9]);
                    barva.setText(data[10]);
                    vrstaBus.setSelected(Boolean.parseBoolean(data[11]));
                    vrstaTruck.setSelected(Boolean.parseBoolean(data[12]));
                    vrstaTractor.setSelected(Boolean.parseBoolean(data[13]));
                    vrstaBike.setSelected(Boolean.parseBoolean(data[14]));
                    vrstaCar.setSelected(Boolean.parseBoolean(data[15]));
                    ime.setText(data[16]);
                    priimek.setText(data[17]);
                    textTrenutnoOdprt.setText(data[16] + " " + data[17].charAt(0));
                    ulica.setText(data[19]);
                    hisnaStevilka.setText(data[20]);
                    kraj.setText(data[21]);
                    postnaStevilka.setText(data[18]);
                    datumRojstva.setValue(LocalDate.parse(data[22]));
                    mladVoznik.setSelected(Boolean.parseBoolean(data[23]));
                    izkusenVoznik.setSelected(Boolean.parseBoolean(data[24]));
                    AO.setSelected(Boolean.parseBoolean(data[25]));
                    AOplus.setSelected(Boolean.parseBoolean(data[26]));
                    polno.setSelected(Boolean.parseBoolean(data[27]));
                    odbitnaFransiza.setSelected(Boolean.parseBoolean(data[28]));
                    brez.setSelected(Boolean.parseBoolean(data[29]));
                    zavarovanjeStekla.setSelected(Boolean.parseBoolean(data[30]));
                    zavarovanjeParkirisce.setSelected(Boolean.parseBoolean(data[31]));
                    zavarovanjePotnike.setSelected(Boolean.parseBoolean(data[32]));
                    zavarovanjeTretjeOsebe.setSelected(Boolean.parseBoolean(data[33]));
                    zavarovanjeGume.setSelected(Boolean.parseBoolean(data[34]));
                    zavarovanjeKrajo.setSelected(Boolean.parseBoolean(data[35]));
                    zavarovanjeToco.setSelected(Boolean.parseBoolean(data[36]));
                    datumPrveRegistracije.setValue(LocalDate.parse(data[37]));
                    registerskaOznacba.setText(data[38]);
                    krajPrveRegistracije.setText(data[39]);
                }
                csvReader.close();
            } catch (Exception e) {}
        }
    }

    public void izhodCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void avtorCB(ActionEvent actionEvent) {
        List<String> zav = new ArrayList<String>();
        try {
            List<String> files = findFiles(Paths.get("."), "csv");
            for (String x : files) {
                zav.add("\n");
                zav.add(x);
            }
            zav.add("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.NONE, "default Dialog",ButtonType.CLOSE);
        alert.setTitle("VSE DATOTEKE");
        alert.setHeaderText("Spodaj so naštete vse datoteke, ki ste jih ustvarili do sedaj\n" +
                "Odprete jih lahko tako, da stisnete na gumb izberi obstoječe.");
        alert.setContentText(String.valueOf(zav));
        Image image = new Image("sample/showall.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        alert.setGraphic(imageView);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CLOSE)).setText("ZAPRI");
        alert.show();
    }
    public void navodilaCB(ActionEvent actionEvent) {
        List<String> zav = new ArrayList<String>();
        try {
            List<String> files = findFiles(Paths.get("."), "csv");
            for (String x : files) {
                zav.add(x);
                zav.add("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.NONE, "default Dialog",ButtonType.CLOSE);
        alert.setTitle("NAVODILA ZA UPORABO");
        alert.setHeaderText("Spodaj so našteta vsa potrebna navodila za izpolnjevanje obrazca");
        alert.setContentText("1. Za začetek ustvarite novo aplikacijo ali pa odprite novo \n" +
                "2. Vpišite vse potrebne podatke, izpolniti morate vsa polja. \n" +
                "3. Ko bojo vsa polja pravilno izpolnjena, se vam bo obarval zeleni gumb in lahko kliknete na njega.\n" +
                "V primeru, da se vam ne omogoči drugo okno, preverite spodnjo obvestilo vrstico.");
        Image image2 = new Image("sample/inst.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        alert.setGraphic(imageView2);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CLOSE)).setText("ZAPRI");
        alert.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<String> files = findFiles(Paths.get("."), "csv");
            files.forEach(x -> stevec++);
            textSteviloZavarovancev.setText(String.valueOf(stevec));
        } catch (IOException e) {
            e.printStackTrace();
        }
        prostornina.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prostornina.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        moc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    moc.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        steviloSedezev.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    steviloSedezev.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        hisnaStevilka.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    hisnaStevilka.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        postnaStevilka.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    postnaStevilka.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        barVrstica.setDisable(true);
        barPodatkiOVozilu.setDisable(true);
        barPodatkiOZavarovancu.setDisable(true);
        barPodatkiOZavarovanju.setDisable(true);
        barPodatkiORegistraciji.setDisable(true);
        LocalDate minDate = LocalDate.of(1989, 4, 16);
        LocalDate maxDate = LocalDate.now().minusYears(18);
        datumRojstva.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});
        LocalDate maxDate2 = LocalDate.now();
        datumPrveRegistracije.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate2) || item.isBefore(minDate));
                    }});
        barVrstica.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                pejt = false;
                pejt2 = false;
                pejt3 = false;
                pejt4 = false;
                shranjevanjePodatkov.setDisable(true);
            }
        });
        gorivoDrugoOznaka.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    gorivoDrugoText.setDisable(false);
                } else {
                    gorivoDrugoText.setDisable(true);
                }
            }
        });

    }
    public void ustvariNovo(ActionEvent mouseEvent) {
        pocistiPolja();
        barVrstica.setDisable(false);
        barPodatkiOVozilu.setDisable(false);
        pocisti.setDisable(false);
        nadaljujGumb.setDisable(false);
        textTrenutnoOdprt.setText("Nov zavarovalec");
    }
    public void pocistiVse(ActionEvent mouseEvent) {
        pocistiPolja();
    }
    public void pocistiPolja(){
        znamka.setText("");
        oznaka.setText("");
        gorivoBencin.setSelected(false);
        gorivoDizel.setSelected(false);
        gorivoElektrika.setSelected(false);
        gorivoDrugoOznaka.setSelected(false);
        gorivoDrugoText.setText("");
        steviloSedezev.setText("");
        prostornina.setText("");
        moc.setText("");
        barva.setText("");
        vrstaBus.setSelected(false);
        vrstaTruck.setSelected(false);
        vrstaTractor.setSelected(false);
        vrstaBike.setSelected(false);
        vrstaCar.setSelected(false);
        ime.setText("");
        priimek.setText("");
        ulica.setText("");
        postnaStevilka.setText("");
        hisnaStevilka.setText("");
        kraj.setText("");
        datumRojstva.setValue(null);
        mladVoznik.setSelected(false);
        izkusenVoznik.setSelected(false);
        AO.setSelected(false);
        AOplus.setSelected(false);
        polno.setSelected(false);
        odbitnaFransiza.setSelected(false);
        brez.setSelected(false);
        zavarovanjeStekla.setSelected(false);
        zavarovanjeParkirisce.setSelected(false);
        zavarovanjePotnike.setSelected(false);
        zavarovanjeTretjeOsebe.setSelected(false);
        zavarovanjeGume.setSelected(false);
        zavarovanjeKrajo.setSelected(false);
        zavarovanjeToco.setSelected(false);
        datumPrveRegistracije.setValue(null);
        registerskaOznacba.setText("");
        krajPrveRegistracije.setText("");

        barVrstica.setDisable(true);
        barPodatkiOVozilu.setDisable(true);
        barPodatkiOZavarovancu.setDisable(true);
        barPodatkiOZavarovanju.setDisable(true);
        barPodatkiORegistraciji.setDisable(true);
        pocisti.setDisable(true);
        nadaljujGumb.setDisable(true);
        textTrenutnoOdprt.setText("----");
        SingleSelectionModel<Tab> selectionModel = barVrstica.getSelectionModel();
        selectionModel.select(0);
        obvestilo.setText("Počistili ste vse vnose. Za nov začetek odprite novo aplikacijo ali izberite obstoječo");
    }
    public void nadaljujNaprej(MouseEvent mouseEvent) {
        preveri();
        if (pejt){
            preveri2();
        }
        if (pejt2){
            preveri3();
        }
        if (pejt3){
            preveri4();
        }

    }
    private void preveri() {
        RadioButton selectedRadioButton = (RadioButton) gorivo.getSelectedToggle();
        RadioButton selectedRadioButton2 = (RadioButton) vrsta.getSelectedToggle();
        if (znamka.getText().equals("") || oznaka.getText().equals("") || selectedRadioButton == null || barva.getText().equals("") ||
                steviloSedezev.getText().equals("") || (gorivoDrugoOznaka.isSelected() && gorivoDrugoText.getText().equals("")) || prostornina.getText().equals("") ||
                selectedRadioButton2 == null || moc.getText().equals("")
        ){
            obvestilo.setText("Izpolni vsa polja v okencu 'Podatki o vozilu' ");
            pejt = false;
        }
        else {
            obvestilo.setText("Izpolni vsa polja v okencu 'Podatki o zavarovancu' ");
            pejt = true;
            barPodatkiOZavarovancu.setDisable(false);
        }
    }
    private void preveri2() {
        RadioButton selectedRadioButton3 = (RadioButton) izkusnje.getSelectedToggle();
        if (ime.getText().equals("") || priimek.getText().equals("") || ulica.getText().equals("") ||
                hisnaStevilka.getText().equals("") || postnaStevilka.getText().equals("") || kraj.getText().equals("") || datumRojstva.getValue() == null ||
                selectedRadioButton3 == null
        ){
            obvestilo.setText("Izpolni vsa polja v okencu 'Podatki o zavarovancu' ");
            pejt2 = false;
        }
        else {
            pejt2 = true;
            barPodatkiOZavarovanju.setDisable(false);
        }
    }
    private void preveri3() {
        RadioButton selectedRadioButton4 = (RadioButton) osnovnoZavarovanje.getSelectedToggle();
        RadioButton selectedRadioButton5 = (RadioButton) kasko.getSelectedToggle();

        if ( selectedRadioButton4 == null || selectedRadioButton5 == null
        ){
            obvestilo.setText("Izpolni vsa polja v okencu 'Podatki o zavarovanju' ");
            pejt3 = false;
        }
        else {
            pejt3 = true;
            obvestilo.setText("Izpolni vsa polja v okencu 'Podatki o registraciji' ");
            barPodatkiORegistraciji.setDisable(false);
        }
    }
    private void preveri4() {
        if (datumPrveRegistracije.getValue() == null || registerskaOznacba.getText().equals("") || krajPrveRegistracije.getText().equals("")){
            obvestilo.setText("Izpolni vsa polja v okencu 'Podatki o registraciji' ");
            pejt4 = false;
        }
        else {
            pejt4 = true;
            shranjevanjePodatkov.setDisable(false);
            obvestilo.setText("VSI PODATKI SO USPEŠNO IZPOLNJENI. OBRAZEC SHRANITE S KLIKOM NA GUMB SHRANI' ");

        }
    }
    public void shraniVsePodatke(MouseEvent mouseEvent) throws IOException {
        String podatekZnamka = znamka.getText();
        String podatekOznaka = oznaka.getText();
        String podatekGorivoBencin = String.valueOf(gorivoBencin.isSelected());
        String podatekGorivoDizel = String.valueOf(gorivoDizel.isSelected());
        String podatekGorivoElektrika = String.valueOf(gorivoElektrika.isSelected());
        String podatekGorivoDrugoOznaka = String.valueOf(gorivoDrugoOznaka.isSelected());
        String podatekGorivoDrugoText = gorivoDrugoText.getText();
        String podatekSteviloSedezev = steviloSedezev.getText();
        String podatekProstornina = prostornina.getText();
        String podatekMoc = moc.getText();
        String podatekBarva = barva.getText();
        String podatekVrstaBus = String.valueOf(vrstaBus.isSelected());
        String podatekVrstaTruck = String.valueOf(vrstaTruck.isSelected());
        String podatekVrstaTractor = String.valueOf(vrstaTractor.isSelected());
        String podatekVrstaBike = String.valueOf(vrstaBike.isSelected());
        String podatekVrstaCar = String.valueOf(vrstaCar.isSelected());
        String podatekIme = ime.getText();
        String podatekPriimek = priimek.getText();
        String podatekUlica = ulica.getText();
        String podatekPostnaStevilka = postnaStevilka.getText();
        String podatekHisnaStevilka = hisnaStevilka.getText();
        String podatekKraj = kraj.getText();
        String podatekDatumRojstva = String.valueOf(datumRojstva.getValue());
        String podatekMladiVoznik = String.valueOf(mladVoznik.isSelected());
        String podatekIzkusenVoznik = String.valueOf(izkusenVoznik.isSelected());
        String podatekAO = String.valueOf(AO.isSelected());
        String podatekAOplus = String.valueOf(AOplus.isSelected());
        String podatekPolno = String.valueOf(polno.isSelected());
        String podatekOdbitnaFransiza = String.valueOf(odbitnaFransiza.isSelected());
        String podatekBrez = String.valueOf(brez.isSelected());
        String podatekZavarovanjeStekla = String.valueOf(zavarovanjeStekla.isSelected());
        String podatekZavarovanjeParkirisce = String.valueOf(zavarovanjeParkirisce.isSelected());
        String podatekZavarovanjePotnike = String.valueOf(zavarovanjePotnike.isSelected());
        String podatekZavarovanjeTretjeOsebe = String.valueOf(zavarovanjeTretjeOsebe.isSelected());
        String podatekZavarovanjeGume = String.valueOf(zavarovanjeGume.isSelected());
        String podatekZavarovanjeKrajo = String.valueOf(zavarovanjeKrajo.isSelected());
        String podatekZavarovanjeToco = String.valueOf(zavarovanjeToco.isSelected());
        String podatekDatumPrveRegistracije = String.valueOf(datumPrveRegistracije.getValue());
        String podatekRegisterskaOznacba = registerskaOznacba.getText();
        String podatekKrajPrveRegistracije = krajPrveRegistracije.getText();
        FileWriter csvWriter = new FileWriter(priimek.getText() + ime.getText() +".csv");
        csvWriter.append("znamka");
        csvWriter.append(",");
        csvWriter.append("oznaka");
        csvWriter.append(",");
        csvWriter.append("gorivoBencin");
        csvWriter.append(",");
        csvWriter.append("gorivoDizel");
        csvWriter.append(",");
        csvWriter.append("gorivoElektrika");
        csvWriter.append(",");
        csvWriter.append("gorivoDrugoOznaka");
        csvWriter.append(",");
        csvWriter.append("gorivoDrugoText");
        csvWriter.append(",");
        csvWriter.append("steviloSedezev");
        csvWriter.append(",");
        csvWriter.append("prostornina");
        csvWriter.append(",");
        csvWriter.append("moc");
        csvWriter.append(",");
        csvWriter.append("barva");
        csvWriter.append(",");
        csvWriter.append("vrstaBus");
        csvWriter.append(",");
        csvWriter.append("vrstaTruck");
        csvWriter.append(",");
        csvWriter.append("vrstaTractor");
        csvWriter.append(",");
        csvWriter.append("vrstaBike");
        csvWriter.append(",");
        csvWriter.append("vrstaCar");
        csvWriter.append(",");
        csvWriter.append("ime");
        csvWriter.append(",");
        csvWriter.append("priimek");
        csvWriter.append(",");
        csvWriter.append("ulica");
        csvWriter.append(",");
        csvWriter.append("hisnaStevilka");
        csvWriter.append(",");
        csvWriter.append("kraj");
        csvWriter.append(",");
        csvWriter.append("postnaStevilka");
        csvWriter.append(",");
        csvWriter.append("datumrojstva");
        csvWriter.append(",");
        csvWriter.append("mladVoznik");
        csvWriter.append(",");
        csvWriter.append("izkusenVoznik");
        csvWriter.append(",");
        csvWriter.append("AO");
        csvWriter.append(",");
        csvWriter.append("AOplus");
        csvWriter.append(",");
        csvWriter.append("polno");
        csvWriter.append(",");
        csvWriter.append("odbitaFransiza");
        csvWriter.append(",");
        csvWriter.append("brez");
        csvWriter.append(",");
        csvWriter.append("zavarovanjeStekla");
        csvWriter.append(",");
        csvWriter.append("zavarovanjeParkirisce");
        csvWriter.append(",");
        csvWriter.append("zavarovanjePotnike");
        csvWriter.append(",");
        csvWriter.append("zavarovanjeTretjeOsebe");
        csvWriter.append(",");
        csvWriter.append("zavarovanjeGume");
        csvWriter.append(",");
        csvWriter.append("zavarovanjeKrajo");
        csvWriter.append(",");
        csvWriter.append("zavarovanjeToco");
        csvWriter.append(",");
        csvWriter.append("datumPrveRegistracije");
        csvWriter.append(",");
        csvWriter.append("registerskaOznacba");
        csvWriter.append(",");
        csvWriter.append("krajPrveRegistracije");
        csvWriter.append("\n");
        csvWriter.append("");
        String[] vrednosti = { podatekZnamka, podatekOznaka, podatekGorivoBencin, podatekGorivoDizel, podatekGorivoElektrika,
        podatekGorivoDrugoOznaka, podatekGorivoDrugoText,podatekSteviloSedezev, podatekProstornina, podatekMoc, podatekBarva,
        podatekVrstaBus, podatekVrstaTruck, podatekVrstaTractor, podatekVrstaBike, podatekVrstaCar, podatekIme, podatekPriimek,
                podatekPostnaStevilka, podatekUlica, podatekHisnaStevilka, podatekKraj, podatekDatumRojstva, podatekMladiVoznik, podatekIzkusenVoznik,
        podatekAO, podatekAOplus, podatekPolno, podatekOdbitnaFransiza, podatekBrez, podatekZavarovanjeStekla, podatekZavarovanjeParkirisce,
        podatekZavarovanjePotnike, podatekZavarovanjeTretjeOsebe, podatekZavarovanjeGume,podatekZavarovanjeKrajo, podatekZavarovanjeToco,
        podatekDatumPrveRegistracije, podatekRegisterskaOznacba, podatekKrajPrveRegistracije};
        csvWriter.append(String.join(",", vrednosti));
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();
        if ( pejt && pejt2 && pejt3 && pejt4){
            obvestilo.setText("VSI PODATKI SO VREDU IN SHRANJENI");
        }
        else {
            obvestilo.setText("POPRAVI PODATKE");
        }
        try {
            stevec = 0;
            List<String> files = findFiles(Paths.get("."), "csv");
            files.forEach(x -> stevec++);
            textSteviloZavarovancev.setText(String.valueOf(stevec));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





