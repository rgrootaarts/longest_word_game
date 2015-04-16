package com.tricode.controller;

import com.tricode.business.WordCalculator;
import com.tricode.business.WordValidator;
import com.tricode.domain.Entry;
import com.tricode.exception.NoExistingWordException;
import com.tricode.exception.NotAWordException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class ApplicationController {

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startForm(Model model) {
        model.addAttribute("entry", new Entry());
        return "start";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String resultPage(@ModelAttribute Entry formInput, Model model) {

        if (validateInput(formInput.getWord()) == false) {
            // clear result
            model.addAttribute("entry", new Entry());
            model.addAttribute("error", "Not a valid word");
            return "start";
        }

        int score = calculatePoints(formInput.getWord());
        model.addAttribute("score", score);

        if (isPersonalHighScore(formInput)) {
            model.addAttribute("personal", "Personal highscore");
        }

        if (isGlobalHighScore(formInput)) {
            model.addAttribute("global", "Global highscore");
        }

        return "result";
    }

    private boolean validateInput(String wordInput) {
        WordValidator wordValidator = new WordValidator();

        try {
            wordValidator.validateWord(wordInput);
        } catch (NotAWordException e) {
            return false;
        } catch (NoExistingWordException e) {
            return false;
        }
        return true;
    }

    private int calculatePoints(String wordInput) {
        WordCalculator wordCalculator = new WordCalculator();
        return  wordCalculator.calculatePoints(wordInput);
    }

    public boolean isPersonalHighScore (Entry entry) {
        return true;
    }

    public boolean isGlobalHighScore (Entry entry) {
        return true;
    }

    private ArrayList<Entry> getHighScoresOfTheDay() {
        return null;
    }
}