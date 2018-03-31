package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Dog;
import io.khasang.jrepetitor.service.DogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Properties;

@Controller
public class DogsController {

    @Autowired
    private DogsService dogsService;

    @RequestMapping("/dogs")
    public String setupForm(Map<String,Object> map){
        Dog dog = new Dog();
        map.put("dog",dog);
        map.put("dogsList",dogsService.findAll());
        return "dogs";
    }

    @RequestMapping(value="/dogs.do",method = RequestMethod.POST)
    public String doActions(@ModelAttribute Dog dog, BindingResult result, @RequestParam String action, Map<String,Object> map){
        Dog dogResult = new Dog();
        switch (action.toLowerCase()){
            case "insert":
                dogsService.insert(dog);
                dogResult=dog;
                break;
            case "update":
                dogsService.update(dog);
                dogResult=dog;
                break;
            case "delete":
                dogsService.delete(dog.getId());
                dogResult=new Dog();
                break;
            case "search":
                dogResult=dogsService.getDog(dog.getId());
                dogResult=dogResult!=null ? dogResult : new Dog();
                break;
        }
        map.put("dog",dogResult);
        map.put("dogsList",dogsService.findAll());
        return "dogs";
    }
}
