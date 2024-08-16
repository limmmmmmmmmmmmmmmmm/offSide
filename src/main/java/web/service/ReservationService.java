package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ReservationDao;

@Service
public class ReservationService {
    @Autowired ReservationDao reservationDao;


}
