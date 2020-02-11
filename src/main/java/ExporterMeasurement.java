/**
 * Klasa Exporter umożliwia przechowywyanie danych o jakości poowietrza w danym mieście oraz zapisanie całych danych do pliku w formacie json.
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */
public class ExporterMeasurement {

    /**
     * nazwa parametru jakości powietrza
     *
     */
    private String parameter;
    /**
     * liczba pomiarów danego parametru z różnych stacji pomiarowych
     *
     */
    private String count;
    /**
     * minimalna wartość danego parametru zmierzoną przez jedną z stacji pomiarowych
     *
     */
    private String minValue;
    /**
     * maksymalna wartość danego parametru zmierzoną przez jedną z stacji pomiarowych
     *
     */
    private String maxValue;
    /**
     * średnia wartość danego parametru wyliczona jako wartość średnia z pomiarów
     *
     */
    private String avgValue;
    /**
     * odchylenie standardowe od wartości średniej danego parametru
     *
     */
    private String stdValue;

    /**
     * Konstruktor przeciążony tworzy obiekt <code>ExporterMeasurement</code> z nazwą parametru, liczbą pomiarów, wartością minimalną, wartością maksymalną, wartością średnią i pdchyleniem standardowym od wartości średniej.
     * @param parameter uswawia nazwę mierzonego parametru jakości powietrza
     * @param count ustawia liczbę pomiarów danego parametru z różnych stacji pomiarowych
     * @param minValue ustawia minimalną wartość danego parametru zmierzoną przez jedną z stacji pomiarowych
     * @param maxValue ustawia maksymalna wartość danego parametru zmierzona przez jedną z stacji pomiarowych
     * @param avgValue ustawia średnią wartość danego parametru wyliczona jako wartość średnia z pomiarów
     * @param stdValue ustawia odchylenie standardowe od wartości średniej danego parametru
     */
    public ExporterMeasurement(String parameter, String count, String minValue, String maxValue, String avgValue, String stdValue) {
        this.parameter = parameter;
        this.count = count;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.avgValue = avgValue;
        this.stdValue = stdValue;
    }


    /**
     * Zwraca nazwę parametru jakości powietrza
     * @return parameter
     */
    public String getParameter() {
        return parameter;
    }
    /**
     * Zwraca liczbę pomiarów danego parametru z różnych stacji pomiarowych
     * @return count
     */
    public String getCount() {
        return count;
    }
    /**
     * Zwraca minimalną wartość danego parametru zmierzoną przez jedną z stacji pomiarowych
     * @return minValue
     */
    public String getMinValue() {
        return minValue;
    }

    /**
     * Zwraca maksymalną wartość danego parametru zmierzoną przez jedną z stacji pomiarowych
     * @return maxValue
     */
    public String getMaxValue() {
        return maxValue;
    }

    /**
     * Zwraca średnią wartość danego parametru wyliczona jako wartość średnia z pomiarów
     * @return avgValue
     */
    public String getAvgValue() {
        return avgValue;
    }
    /**
     * Zwraca odchylenie standardowe od wartości średniej danego parametru
     * @return stdValue
     */
    public String getStdValue() {
        return stdValue;
    }

    /**
     * Zwraca obiekt klasy ExporterMeasurement jako String.
     */
    @Override
    public String toString() {
        return "ExporterMeasurement{" +
                "parameter='" + parameter + '\'' +
                '}';
    }
}
