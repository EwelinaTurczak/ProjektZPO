/**
 * Klasa Norma pozwala na przechowywania wartości kategori aktualnych norm wartości pomiaru danego parametru powietrza
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */
public class Norma {


    /**
     * nazwa parametru jakości powietrza
     *
     */
    private String nazwa;
    /**
     * górna wartość normy parametru jakości powietrza dla kategorii bardzo dobry
     *
     */
    private double bardzoDobry;
    /**
     * górna wartość normy parametru jakości powietrza dla kategorii dobry
     *
     */
    private double dobry;
    /**
     * górna wartość normy parametru jakości powietrza dla kategorii umiarkowany
     *
     */
    private double umiarkowany;
    /**
     * górna wartość normy parametru jakości powietrza dla kategorii dostateczny
     *
     */
    private double dostateczny;
    /**
     * górna wartość normy parametru jakości powietrza dla kategorii zły
     *
     */
    private double zly;


    /**
     * Konstruktor przeciążony tworzy obiekt <code>Norma</code> z
     * @param nazwa ustawia nazwę parametru jakości powietrza
     * @param bardzoDobry ustawia górną wartość normy parametru jakości powietrza dla kategorii bardzo dobry
     * @param dobry ustawia górną wartość normy parametru jakości powietrza dla kategorii dobry
     * @param umiarkowany ustawia górna wartość normy parametru jakości powietrza dla kategorii umiarkowany
     * @param dostateczny ustawia górna wartość normy parametru jakości powietrza dla kategorii dostateczny
     * @param zly ustawia górna wartość normy parametru jakości powietrza dla kategorii zły
     */
    public Norma(String nazwa, double bardzoDobry, double dobry, double umiarkowany, double dostateczny, double zly) {
        this.nazwa = nazwa;
        this.bardzoDobry = bardzoDobry;
        this.dobry = dobry;
        this.umiarkowany = umiarkowany;
        this.dostateczny = dostateczny;
        this.zly = zly;
    }
    /**
     * Zwraca nazwę parametru jakości powietrza
     * @return nazwa
     */
    public String getNazwa() {
        return nazwa;
    }
    /**
     * Zwraca górną wartość normy parametru jakości powietrza dla kategorii bardzo dobry
     * @return bardzoDobry
     */
    public double getBardzoDobry() {
        return bardzoDobry;
    }
    /**
     * Zwraca górną wartość normy parametru jakości powietrza dla kategorii dobry
     * @return dobry
     */
    public double getDobry() {
        return dobry;
    }
    /**
     * Zwraca wartość normy parametru jakości powietrza dla kategorii umiarkowany
     * @return umiarkowany
     */
    public double getUmiarkowany() {
        return umiarkowany;
    }
    /**
     * Zwraca wartość normy parametru jakości powietrza dla kategorii dostateczny
     * @return dostateczny
     */
    public double getDostateczny() {
        return dostateczny;
    }
    /**
     * Zwraca wartość normy parametru jakości powietrza dla kategorii zly
     * @return zly
     */
    public double getZly() {
        return zly;
    }

}
