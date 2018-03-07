package mx.jairaviles.amazonviewer.model;

import java.util.Date;

public interface IVisualized {

    Date startToSee(Date startDate);

    void stopToSee(Date startDate, Date endDate);

}
