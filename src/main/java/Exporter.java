import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa Exporter umożliwia przechowywyanie danych o jakości poowietrza w danym mieście oraz zapisanie całych danych do pliku w formacie json.
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */

public class Exporter {

    /**
     * miasto, w którym dokonujemy pomiaru
     *
     */
    private String city;
    /**
     * data wykonania zapytania odnośnie jakości powietrza
     *
     */
    private String date;
    /**
     * lista przechowująca obiekty typu ExporterMeasuremnt, która zawiera informację o wartości zmierzonego parametru
     *
     */
    private ArrayList<ExporterMeasurement> measurements;


    /**
     * Konstruktor przeciążony tworzy obiekt <code>Exporter</code> z
      miastem pomiaru, datą wykonania zapytania oraz listą zmieroznych wartości parametrów
     * @param city ustawia nazwę miast w którym dokonujemy pomiaru
     * @param date ustawia datę wykonania zapytania odnośnie stanu jakości powietrza w danym mieście.
     * @param measurements ustawia listę przechowującą obiekty typu ExporterMeasuremnt, która zawiera informację o wartości zmierzonego parametru
     */

    public Exporter(String city, String date, ArrayList<ExporterMeasurement> measurements) {
        this.city = city;
        this.date = date;
        this.measurements = measurements;
    }

    /**
     * Zwraca miasto, w którym dokonujemy pomiaru.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Zwraca datę wykonania zapytania odnośnie pomairu jakości powietrza.
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Zwraca listę obiektów klasy ExporterMeasurement, która przechowuje wartości pomiarów.
     * @return measurements
     */
    public ArrayList<ExporterMeasurement> getMeasurements() {
        return measurements;
    }

    /**
     * Metoda generująca pliku o rozszerzeniu .json z pobranych wartości dotyczących pomiaru jakości powietrza w danym mieście.
     * @return measurements
     */
    public void saveToJson (String filename){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(filename);
        stringBuilder.append("-");
        stringBuilder.append(date);
        stringBuilder.append(".json");
        String name = stringBuilder.toString();
        File file = new File(name);

        try (FileWriter fileWriter = new FileWriter(file)) {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            gson.toJson(this, fileWriter);
        }
        catch (IOException e) {
            System.out.println("IO error");
        }

    }

    /**
     * Zwraca obiekt klasy Exporter jako String.
     */
    @Override
    public String toString() {
        return "Exporter{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", measurements=" + measurements +
                '}';
    }
}
