package com.boot.controller;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {
    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @GetMapping(value = "shipwrecks")
    public List<Shipwreck> list() {
        return shipwreckRepository.findAll();
    }

    @PostMapping(value = "shipwrecks")
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @GetMapping(value = "shipwrecks/{id}")
    public Shipwreck get(@PathVariable Long id) {
        return shipwreckRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
//        return shipwreckRepository.getOne(id);
    }

    @PutMapping(value = "shipwrecks/{id}")
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        Shipwreck existingShipwreck = shipwreckRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        BeanUtils.copyProperties(shipwreck, existingShipwreck);
        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @DeleteMapping(value = "shipwrecks/{id}")
    public Shipwreck delete(@PathVariable Long id) {
        Shipwreck existingShipwreck = shipwreckRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        shipwreckRepository.delete(existingShipwreck);
        return existingShipwreck;
    }
}
