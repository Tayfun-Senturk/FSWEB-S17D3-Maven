package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KoalaController {
    private Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping("/koalas")
    public List<Koala> getAllKoalas() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/koalas/{id}")
    public Koala getKoalaById(@PathVariable Long id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Not found", HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be positive", HttpStatus.BAD_REQUEST);
        }
        return koalas.get(id);
    }

    @PostMapping("/koalas")
    public Koala addKoala(@RequestBody Koala koala) {
        if(koala.getId() <= 0 || koala.getName() == null || koala.getGender() == null || koala.getSleepHour() == 0.0||koala.getWeight() == 0.0){
            throw new ZooException("Given information is not valid", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/koalas/{id}")
    public Koala updateKoala(@PathVariable Long id, @RequestBody Koala koala) {
        koalas.put(id, koala);
        return koalas.get(id);
    }

    @DeleteMapping("/koalas/{id}")
    public Koala deleteKoala(@PathVariable Long id) {
        Koala koala = koalas.get(id);
        koalas.remove(id);
        return koala;
    }
}
