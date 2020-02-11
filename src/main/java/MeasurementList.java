import java.util.ArrayList;
/**
 * Klasa MeasurementList rozszerza klasę ArrayList i zapewnia implemetację własnych metod służących do znajdowania
 * wartości maksymalnej i minimalnej, liczby elemetów, wartości średniej i odchylenia standardowegow w danej liście.
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */

class MeasurementList<T> extends ArrayList<T> {


    /**
     * Zwraca wartość minimalną znalezioną na danej liście dla danego parametru
     * @param parameter - nazwa parametru jakości powietrza, którego wartości minimalnej szukamy
     * @return minVal - zwraca znalezioną wartość minimalną parametru jakości powietrza w danej liście
     */
    double min(String parameter) {
        double minVal = Double.MAX_VALUE;
        if (super.size() == 0) minVal = -1;
        for (T m : this) {
        Measurement measurement = (Measurement)m;
        if (measurement.getParameter().equals(parameter) && measurement.getValue() < minVal)
            minVal = measurement.getValue();
        }
        return minVal;
    }
    /**
     * Zwraca wartość maksymalną znalezioną na danej liście dla danego parametru
     * @param parameter - nazwa parametru jakości powietrza, którego wartości maksymalnej szukamy
     * @return maxVal - zwraca znalezioną wartość maksymalną parametru jakości powietrza w danej liście
     */

    double max(String parameter) {
        double maxVal = Double.MIN_VALUE;
        if (super.size() == 0) maxVal = -1;
        for (T m : this) {
            Measurement measurement = (Measurement)m;
            if (measurement.getParameter().equals(parameter) && measurement.getValue() > maxVal)
                maxVal = measurement.getValue();
        }
        return maxVal;
    }

    /**
     * Zwraca wyliczoną wartość średnią z zmierzonych wartości pomiarów dla danego parametru
     * @param parameter - nazwa parametru jakości powietrza, którego wartości średniej szukamy
     * @return sredniaValue - zwraca wyliczoną wartość średnią parametru jakości powietrza w danej liście
     */

    double avg(String parameter) {
        double sredniaWartosc = 0;
        int licznik = 0;
        if (super.size() == 0) sredniaWartosc = -1;
        for (T m : this) {
            Measurement measurement = (Measurement)m;
            if (measurement.getParameter().equals(parameter)){
                licznik++;
                sredniaWartosc = sredniaWartosc + measurement.getValue();
            }
        }
        return sredniaWartosc / licznik;
    }

    /**
     * Zwraca wyliczoną wartość odchylenia standardowego z zmierzonych wartości pomiarów dla danego parametru
     * @param parameter - nazwa parametru jakości powietrza, którego wartości odchylenia standardowego szukamy
     * @return odchylenie - zwraca wyliczoną wartość odchylenia standardowego parametru jakości powietrza w danej liście
     */
    double std(String parameter) {
        double sredniaWartosc = avg(parameter);
        double odchylenie  = 0;
        int licznik = 0;
        if (super.size() == 0) sredniaWartosc = -1;
        for (T m : this) {
            Measurement measurement = (Measurement)m;
            if (measurement.getParameter().equals(parameter)){
                licznik++;
                odchylenie = odchylenie + Math.pow((measurement.getValue() - sredniaWartosc),2);
            }
        }
        odchylenie = odchylenie / licznik;
        return Math.sqrt(odchylenie);
    }

    /**
     * Zwraca wyliczoną liczbę pomiarów dnaego paramteru dla danego miast.
     * Wylicza liczbę stacji mierzących dany parametr w danym mieście.
     * @param parameter - nazwa parametru jakości powietrza, którego liczby pomiarów szukamy
     * @return licznik - zwraca wyliczoną liczbę pomiarów danego parametru jakości powietrza w danej liście
     */
    double count(String parameter){
        double licznik = 0 ;
        for (T m : this) {
            Measurement measurement = (Measurement)m;
            if (measurement.getParameter().equals(parameter))
                licznik++;
        }
        return licznik;
    }

}
