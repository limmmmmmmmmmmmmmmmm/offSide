package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PointDao;

@Service
public class PointService {
    @Autowired PointDao pointDao;





}
