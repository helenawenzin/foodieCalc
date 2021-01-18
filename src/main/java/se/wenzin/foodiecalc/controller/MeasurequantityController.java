package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.repo.MeasureQuantityRepository;

@RestController
public class MeasurequantityController {

    @Autowired
    private MeasureQuantityRepository repository;

    

}
