import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Klasa Contorller umożliwia sterowanie aplikacją dla użytkownika.
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */

public class Controller {

        /**
         * etykieta miasto
         *
         */
        @FXML
        private Label labelMiasto;

        /**
         * nazwa miast, dla której sprawdzamy jakoś powietrza
         *
         */
        @FXML
        private TextField poleMiasto;

        /**
         * nazwa pliku, z którego odczytujemy w celu wizualizacji zmierzone parametry jakości powietrza
         *
         */
        @FXML
        private TextField nazwaPliku;

        /**
         * przycisk wyświetl
         *
         */
        @FXML
        private Button btnWyswietl;

        /**
         * tabela obiektów klasy ExporterMeasurement, które przechowuje prezentowane wyniki
         *
         */
        @FXML
        private TableView<ExporterMeasurement> tabelaWyniki;

        /**
         * pole typu ComboBox przechwoujące informacje, jakie parametry można zilustrować na wykresie
         *
         */
        @FXML
        private ComboBox<String> comboJakiParametrNarysowac;

        /**
         * wykres słupkowi przedstawiający wybrane dane oraz normy dla nich
         *
         */
        @FXML
        private BarChart<String, Number> wykresSlupkowy;

        /**
         * przycisk rysuj wykres
         *
         */
        @FXML
        private Button btnRysuj;

        /**
         * obiekt klasy Exporter przechowujący uzyskane dane
         *
         */
        private Exporter dane;

        /**
         * kolumna w której jest wyświetlana wartość minimalna spośród wszystkich zmierzonych pomiarów
         *
         */
        @FXML
        private TableColumn<ExporterMeasurement, String> columnMin;

        /**
         * kolumna w której jest wyświetlana wartość maksymalna spośród wszystkich zmierzonych pomiarów
         *
         */
        @FXML
        private TableColumn<ExporterMeasurement, String> columnMax;

        /**
         * kolumna w której jest wyświetlana wartość średnia ze wszystkich zmierzonych pomiarów
         *
         */
        @FXML
        private TableColumn<ExporterMeasurement, String> columnAvg;

        /**
         * Metoda, która zakolorowuje komórki na odpowiedni kolor w zależności od wartości.
         *
         */
        @FXML
        void initialize() {
                Callback<TableColumn<ExporterMeasurement, String>, TableCell<ExporterMeasurement, String>> call = new Callback<TableColumn<ExporterMeasurement, String>, TableCell<ExporterMeasurement, String>>() {
                        @Override
                        public TableCell call(TableColumn column) {

                                TableCell<ExporterMeasurement, String> cell = new TableCell<ExporterMeasurement, String>() {

                                        @Override
                                        protected void updateItem(String item, boolean empty) {
                                                setText("");
                                                setStyle("-fx-background-color: default;");

                                                if (!empty) {
                                                        int currentIndex = indexProperty().getValue() < 0 ? 0 : indexProperty().getValue();
                                                        ExporterMeasurement ex = (ExporterMeasurement) column.getTableView().getItems().get(currentIndex);
                                                        String param = ex.getParameter();
                                                        setStyle("-fx-background-color: " + ocena(param, item) + ";");
                                                        setText(item);
                                                }
                                        }

                                };

                                return cell;
                        }
                };
                columnMin.setCellFactory(call);
                columnAvg.setCellFactory(call);
                columnMax.setCellFactory(call);
        }


        private ObservableList<String> rodzajParametrowDoNarysowanie = FXCollections
                .observableArrayList();

        Norma pm10 = new Norma("pm10",21,61,101,141,201);
        Norma pm25 = new Norma("pm25",13,37,61,85,121);
        Norma o3 = new Norma("o3",71,121,151,181,241);
        Norma no2 = new Norma("no2",41,101,151,201,401);
        Norma so2 = new Norma("so2",51,101,201,351,501);
        Norma co = new Norma("co",3,7,11,15,21);

        /**
         * lista obecnych norm dla różnych kategorii parametrów jakości pomiaru powietrza
         *
         */
        private ArrayList <Norma> listaNorm = new ArrayList<>();


        /**
         * Metoda pozwalająca na narysowanie wykresu słupkowego dla wybranego parametru wraz z odpowiednimi dla niego normami
         *
         */
        @FXML
        void rysujWykres() {

                wykresSlupkowy.getData().clear();
                listaNorm.add(pm10);
                listaNorm.add(pm25);
                listaNorm.add(o3);
                listaNorm.add(no2);
                listaNorm.add(so2);
                listaNorm.add(co);

                String parametrDoWykresu = (String) comboJakiParametrNarysowac.getValue();
                ExporterMeasurement daneDoWykresu = null;
                Norma normaDoWykresu = null;


                for(ExporterMeasurement exporterMeasurement: dane.getMeasurements()){
                        if(exporterMeasurement.getParameter().equals(parametrDoWykresu))
                                daneDoWykresu = exporterMeasurement;
                }

                for(Norma norma: listaNorm){
                        if(norma.getNazwa().equals(parametrDoWykresu))
                                normaDoWykresu = norma;
                }



                XYChart.Series dataSeries1 = new XYChart.Series();

                if(daneDoWykresu.getParameter().equals("bc")){

                        dataSeries1.getData().add(new XYChart.Data("Średnia",Double.parseDouble(daneDoWykresu.getAvgValue())));
                        dataSeries1.getData().add(new XYChart.Data("Min",Double.parseDouble(daneDoWykresu.getMinValue())));
                        dataSeries1.getData().add(new XYChart.Data("Max",Double.parseDouble(daneDoWykresu.getMaxValue())));
                }else {

                        dataSeries1.getData().add(new XYChart.Data("Średnia", Double.parseDouble(daneDoWykresu.getAvgValue())));
                        dataSeries1.getData().add(new XYChart.Data("Min", Double.parseDouble(daneDoWykresu.getMinValue())));
                        dataSeries1.getData().add(new XYChart.Data("Max", Double.parseDouble(daneDoWykresu.getMaxValue())));
                        dataSeries1.getData().add(new XYChart.Data("Indeks bardzo dobry", normaDoWykresu.getBardzoDobry()));
                        dataSeries1.getData().add(new XYChart.Data("Indeks dobry", normaDoWykresu.getDobry()));
                        dataSeries1.getData().add(new XYChart.Data("Indeks umiarkowany", normaDoWykresu.getUmiarkowany()));
                        dataSeries1.getData().add(new XYChart.Data("Indeks dostateczny", normaDoWykresu.getDostateczny()));
                        dataSeries1.getData().add(new XYChart.Data("Indeks zły", normaDoWykresu.getZly()));
                }



                wykresSlupkowy.getData().add(dataSeries1);
                wykresSlupkowy.setLegendVisible(false);

        }

        private String ocena(String parametr, String value) {
                String ocena = "default";

                if (value.equals("brak danych") || parametr.equals("bc"))
                        return ocena;
                else {
                        for (Norma norma : listaNorm) {
                                if (norma.getNazwa().equals(parametr)) {
                                        if (Double.parseDouble(value) < Double.parseDouble(String.valueOf(norma.getBardzoDobry()))) {
                                                ocena = "#25c224";
                                        } else if (Double.parseDouble(value) < Double.parseDouble(String.valueOf(norma.getDobry()))) {
                                                ocena = "#b7ff66";
                                        } else if (Double.parseDouble(value) < Double.parseDouble(String.valueOf(norma.getUmiarkowany()))) {
                                                ocena = "#f2ef00";
                                        } else if (Double.parseDouble(value) < Double.parseDouble(String.valueOf(norma.getDostateczny()))) {
                                                ocena = "#f29901";
                                        } else if (Double.parseDouble(value) < Double.parseDouble(String.valueOf(norma.getZly()))) {
                                                ocena = "#f2371a";
                                        } else {
                                                ocena = "#9D2816";
                                        }
                                }
                        }
                }
                return ocena;
        }
        
        /**
         * Metoda pozwalająca na pobranie wartości parametrów jakości powietrza w danej chwili z serwera
         * @param event
         */
        @FXML
        void pobierzDaneMiastaZSerwera(MouseEvent event) {
                String miasto = poleMiasto.getText();
                wykresSlupkowy.getData().clear();
                nazwaPliku.setText("");
                dane = zapiszNowyPlik(miasto);
                if(dane == null) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Ostrzeżenie");
                        alert.setHeaderText("Nie udało się pobrać informacji o jakości powietrza.");
                        alert.setContentText("Proszę upewnij się, że podałeś poprawną nazwę miasta.");
                        alert.showAndWait();
                } else {
                        prezentujDane(dane);
                }
        }
        /**
         * Metoda pomocnicza do łączenia się z API
         * @param miasto - miasto dla którego chcemy pobrać dane o jakości powietrza
         */
        private static Exporter zapiszNowyPlik(String miasto) {
                StringBuilder builder = new StringBuilder();
                builder.append("https://api.openaq.org/v1/latest?city=");
                builder.append(miasto);
                String url = builder.toString();
                StringBuffer response = new StringBuffer();
                MeasurementList<Measurement> listaPomiarow = new MeasurementList<>();

                try {
                        URL obj = new URL(url);
                        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                        connection.setRequestMethod("GET");
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;

                        while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                        }

                        in.close();

                } catch (MalformedURLException ex) {
                        System.out.println("Bad url adress.");
                        return null;
                } catch (IOException ex) {
                        System.out.println("Connection failed or this name of city don't exist ");
                        return null;
                }


                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();

                // Cala odpowiedz do JsonObject
                String calosc_s = String.valueOf(response);
                JsonObject calosc = gson.fromJson(calosc_s, JsonObject.class);

                // Pobieramy results, które jest tablicą, do JsonArray
                String results_s = calosc.get("results").toString();
                JsonArray results = gson.fromJson(results_s, JsonArray.class);

                if(results.size() == 0) return null;

                // Iterujemy po results (dostając stację pomiarową)
                for (JsonElement r : results) {
                        JsonObject result = r.getAsJsonObject();
                        // Pobieramy measurements, które jest tablicą, do JsonArray
                        JsonArray measurements = gson.fromJson(result.get("measurements").toString(), JsonArray.class);
                        // Iterujemy po measurements
                        for (JsonElement m : measurements) {
                                JsonObject measurementLine = m.getAsJsonObject();
                                String dataPomiaru = measurementLine.get("lastUpdated").toString().substring(1, 11);
                                if (dataPomiaru.equals(LocalDate.now().toString())) {
                                        String parametr = measurementLine.get("parameter").toString();
                                        parametr = parametr.substring(1, parametr.length() - 1);
                                        Measurement measurement = new Measurement(parametr, Double.parseDouble(measurementLine.get("value").toString()));
                                        listaPomiarow.add(measurement);
                                }
                        }
                }

                String [] mierzoneParametry = {"bc","no2","o3","co","pm10","pm25","so2"};
                ArrayList<ExporterMeasurement> measurements = new ArrayList<>();

                // Tworzenie ExporterMeasurement
                for( int i = 0 ; i < mierzoneParametry.length; i++){

                        ExporterMeasurement exporterMeasurement;

                        if(listaPomiarow.count(mierzoneParametry[i]) == 0){
                                exporterMeasurement = new ExporterMeasurement(mierzoneParametry[i],
                                        String.valueOf((int)listaPomiarow.count(mierzoneParametry[i])),
                                        "brak danych",
                                        "brak danych",
                                        "brak danych",
                                        "brak danych");
                        }else {
                                double min = listaPomiarow.min(mierzoneParametry[i]);
                                double max = listaPomiarow.max(mierzoneParametry[i]);
                                double avg = listaPomiarow.avg(mierzoneParametry[i]);
                                double std = listaPomiarow.std(mierzoneParametry[i]);

                                exporterMeasurement = new ExporterMeasurement(mierzoneParametry[i],
                                        String.valueOf((int)listaPomiarow.count(mierzoneParametry[i])),
                                        String.valueOf(min),
                                        String.valueOf(max),
                                        String.format("%.2f", avg),
                                        String.format("%.2f", std));
                        }
                        measurements.add(exporterMeasurement);
                }


                // Tworzenie Exporter
                Exporter exporter = new Exporter(miasto, LocalDate.now().toString(), measurements);

                // Zapisz plik na dysku w historii
                String nazwaNowegoPliku = miasto;
                exporter.saveToJson(nazwaNowegoPliku);

                // Zwracaj exporter
                return exporter;
        }


        /**
         * Metoda pozwalająca na pobranie danych z zapisanego pliku i zilustrowanie ich
         * @param event
         */
        @FXML
        void pobierzDaneMiastaZPliku(MouseEvent event) {
                String nazwaPliku = this.nazwaPliku.getText();
                poleMiasto.setText("");
                dane = wczytajZPliku(nazwaPliku);
                if(dane == null) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Ostrzeżenie");
                        alert.setHeaderText("Nie udało się wczytać podanego pliku");
                        alert.setContentText("Proszę sprawdź, czy nazwa pliku jest poprawna.");
                        alert.showAndWait();
                } else {
                        prezentujDane(dane);
                }
        }

        /**
         * Metoda pomocnicza do przerabiania JSON na obiekt klasy Exporter
         * @param nazwaPliku - nazwa pliku, z którego zczytujemy dane
         */
        private Exporter wczytajZPliku(String nazwaPliku) {
                Gson gson = new Gson();
                Exporter wczytany = null;
                try(BufferedReader bufferedReader = new BufferedReader(new FileReader(nazwaPliku))) {
                        wczytany = gson.fromJson(bufferedReader, Exporter.class);
                } catch (IOException e){
                        e.printStackTrace();
                }
                return wczytany;
        }



        /**
         * Metoda pozwalająca na prezentowanie danych
         * @param dane - obiekt klasy typu Exporter zawierający dane do prezentacji
         */
        void prezentujDane(Exporter dane) {
                tabelaWyniki.getItems().clear();
                ArrayList<ExporterMeasurement> pomiaryWArrayList = dane.getMeasurements();
                ObservableList<ExporterMeasurement> pomiary = FXCollections.observableArrayList();
                rodzajParametrowDoNarysowanie.clear();

                for (ExporterMeasurement exporterMeasurement : pomiaryWArrayList) {
                        pomiary.add(exporterMeasurement);

                        if(Integer.parseInt(exporterMeasurement.getCount()) != 0){
                                rodzajParametrowDoNarysowanie.add(exporterMeasurement.getParameter());
                        }
                }


                comboJakiParametrNarysowac.setItems(rodzajParametrowDoNarysowanie);
                comboJakiParametrNarysowac.setValue(rodzajParametrowDoNarysowanie.get(0));

                tabelaWyniki.setItems(pomiary);

        }


}