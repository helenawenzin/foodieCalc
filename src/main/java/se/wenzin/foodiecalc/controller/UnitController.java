package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.Unit;
import se.wenzin.foodiecalc.repo.UnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UnitController {

    @Autowired
    private UnitRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "unit/allunits")
    public List<Unit> getAlUnits() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/unit/{id}")
    public Optional<Unit> getUnitById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unit")
    public Unit createUnit(@RequestBody Unit unit) {
        return repository.save(unit);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/unit/update")
    public Unit updateUnit(@RequestBody Unit unit) {
        return repository.save(unit);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/unit/{id}")
    public void removeUnit(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
