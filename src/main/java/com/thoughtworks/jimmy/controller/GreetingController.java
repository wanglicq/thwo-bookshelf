package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public ModelAndView greeting(@PathVariable String name) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("person",  new Person(name));
        modelAndView.setViewName("hello");

        return modelAndView;

    }

}
