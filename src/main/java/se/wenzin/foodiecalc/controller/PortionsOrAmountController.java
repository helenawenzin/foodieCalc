package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.PortionsOrAmount;
import se.wenzin.foodiecalc.repo.PortionsOrAmountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PortionsOrAmountController {

    @Autowired
    private PortionsOrAmountRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/portionsoramount/{id}")
    public Optional<PortionsOrAmount> getPortionsOrAmountById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/portionsoramounts")
    public List<PortionsOrAmount> getAllPortionsOrAmounts() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/portionsoramount")
    public PortionsOrAmount createPortionsOrAmount(@RequestBody PortionsOrAmount portionsOrAmount) {
        return repository.save(portionsOrAmount);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/portionsoramount")
    public PortionsOrAmount updatePortionsOrAmount(@RequestBody PortionsOrAmount portionsOrAmount) {
        return repository.save(portionsOrAmount);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/portionsoramount/{id}")
    public void removePortionsOrAmount(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }


}
