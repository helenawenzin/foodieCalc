package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.Measure;
import se.wenzin.foodiecalc.repo.MeasureRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MeasureController {

    @Autowired
    private MeasureRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/measures")
    public List<Measure> getAllMeasures() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/measure/{id}")
    public Optional<Measure> getMeasureById(@PathVariable("id")UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/measure")
    public Measure createMeasure(@RequestBody Measure measure) {
        return repository.save(measure);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/measure")
    public Measure updateMeasure(@RequestBody Measure measure) {
        return repository.save(measure);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/measure/{id}")
    public void removeMeasure(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
