package mx.jairaviles.amazonviewer.model;

import java.util.Date;

public interface IVisualizable {

    /**
     * Este metodo captura el tiempo exacto de visualizacion
     *
     * @param startDate Es un objeto {@code Date} con el tiempo de inicio exacto
     * @return Devuelve la fecha y hora capturada
     * */
    Date startToSee(Date startDate);

    /**
     * Este metodo captura el tiempo exacto de inicio y final de visualizacion
     * @param startDate Es un objeto {@code Date} con el tiempo de inicio exacto
     * @param endDate Es un objeto {@code Date} con el tiempo de fin exacto
     * */
    void stopToSee(Date startDate, Date endDate);

}
