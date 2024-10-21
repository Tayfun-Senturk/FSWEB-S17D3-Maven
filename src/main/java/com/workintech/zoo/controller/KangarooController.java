package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooErrorResponse;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable Long id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Not found", HttpStatus.NOT_FOUND);
        }
        if(id<=0){
            throw new ZooException("id must be positive", HttpStatus.BAD_REQUEST);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if(kangaroo.getId() <= 0 || kangaroo.getName() == null || kangaroo.getGender() == null ||kangaroo.getWeight() == 0.0){
            throw new ZooException("Given information is not valid", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Long id, @RequestBody Kangaroo kangaroo) {
        kangaroos.put(id, kangaroo);
        return kangaroos.get(id);
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }
}
