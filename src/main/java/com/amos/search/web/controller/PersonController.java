package com.amos.search.web.controller;

import com.amos.search.dao.Person;
import com.amos.search.dao.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * DESCRIPTION: PersonController
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/9/17
 */
@RestController
@RequestMapping("person")
public class PersonController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private PersonRepository personRepository;


    @GetMapping
    public Person get(String id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("person is not found"));
    }

    @PostMapping
    public String post(@RequestBody Person person) {
        Person save = personRepository.save(person);

        LOGGER.info("person save success, [{}]({})", save.getName(), save.getId());

        return save.getId();
    }

    @GetMapping("all")
    public Iterable<Person> all() {

        return personRepository.findAll();
    }

    @GetMapping("findByName/{name}")
    public Iterable<Person> findByName(@PathVariable("name") String name) {

        return personRepository.findByNameLike(name);
    }

    @GetMapping("findByDesc/{desc}")
    public Iterable<Person> findByDesc(@PathVariable("desc") String desc) {

        return personRepository.findByDesc(desc);
    }

}
