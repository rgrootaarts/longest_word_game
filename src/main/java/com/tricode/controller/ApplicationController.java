package com.tricode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {

    @RequestMapping(value="/start", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        return "start";
    }

    @RequestMapping(value="/result", method=RequestMethod.POST)
    public String resultPage( Model model) {
        return "result";
    }
}