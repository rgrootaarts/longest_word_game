package com.tricode.controller;

import com.tricode.business.WordValidator;
import com.tricode.domain.FormInput;
import com.tricode.exception.NoExistingWordException;
import com.tricode.exception.NotAWordException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {

    @RequestMapping(value="/start", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("formInput", new FormInput());
        return "start";
    }

    @RequestMapping(value="/result", method=RequestMethod.POST)
    public String resultPage(@ModelAttribute FormInput formInput, Model model) {
        WordValidator wordValidator = new WordValidator();
        boolean result = false;
        try {
            result = wordValidator.validateWord(formInput.getWord());
        } catch (NotAWordException e) {
            return "start";
        } catch (NoExistingWordException e) {
            return "start";
        }

        int score =0;
        if (result==true) {
            score = wordValidator.calculatePoints(formInput.getWord());
        }

        model.addAttribute("score", score);
        return "result";
    }
}