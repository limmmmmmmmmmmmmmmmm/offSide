package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.PointService;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired PointService pointService;


}
