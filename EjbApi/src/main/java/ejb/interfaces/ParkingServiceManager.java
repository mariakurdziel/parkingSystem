package ejb.interfaces;

import ejb.dto.Notification;
import ejb.dto.SpotStatus;

public interface ParkingServiceManager {

    SpotStatus handleParkingEvent(Notification event);
}
