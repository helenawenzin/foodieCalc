package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.FoodCategory;
import se.wenzin.foodiecalc.model.MeasureQuantity;
import se.wenzin.foodiecalc.repo.MeasureQuantityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MeasureQuantityController {

    @Autowired
    private MeasureQuantityRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/measurequantities")
    public List<MeasureQuantity> getAllMeasureQuantities() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/measurequantity/{id}")
    public Optional<MeasureQuantity> getMeasureQuantityById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/measurequantity")
    public MeasureQuantity createMeasureQuantity(@RequestBody MeasureQuantity measureQuantity) {
        return repository.save(measureQuantity);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/measurequantity")
    public MeasureQuantity updateMeasureQuantity(@RequestBody MeasureQuantity measureQuantity) {
        return repository.save(measureQuantity);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/measurequantity/{id}")
    public void removeMeasureQuantity(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }
}
