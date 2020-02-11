/**
 * Klasa Measurement umożliwia przechowywanie informacjo o nazwie parametru i jego wartości.
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */
public class Measurement {

    /**
     * nazwa parametru jakości powietrza
     *
     */
    private final String parameter;
    /**
     * zmierzona wartość parametru jakości powietrza
     *
     */
    private final double value;

    /**
     * Konstruktor przeciążony tworzy obiekt <code>Measurement</code> z nazwą parametru jakości powietrza oraz mierzoną wartość parametru jakości powietrza.
     * @param parameter ustawia nazwę parametru jakości powietrza
     * @param value ustawia zmierzoną wartość parametru jakości powietrza
     */
    public Measurement(String parameter, double value) {
        this.parameter = parameter;
        this.value = value;
    }
    /**
     * Zwraca nazwę parametru jakości powietrza
     * @return parameter
     */

    public String getParameter() {
        return parameter;
    }

    /**
     * Zwraca zmierzoną wartość parametru jakości powietrza
     * @return value
     */

    public double getValue() {
        return value;
    }

    /**
     * Zwraca obiekt klasy Measurement jako String.
     *
     */

    @Override
    public String toString() {
        return "Measurement{" +
                "parameter='" + parameter + '\'' +
                ", value=" + value +
                '}';
    }
}
