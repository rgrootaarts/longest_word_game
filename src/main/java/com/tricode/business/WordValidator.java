package com.tricode.business;

import com.tricode.exception.NoExistingWordException;
import com.tricode.exception.NotAWordException;

public class WordValidator {

    public boolean validateWord (String input) throws NotAWordException, NoExistingWordException {
        if (!onlyCharacters(input)) {
            throw new NotAWordException();
        }
        if (!existingWord(input)) {
            throw new NoExistingWordException();
        }
        return true;
    }

    private boolean onlyCharacters (String rawString) {
        return rawString.matches("[a-zA-Z]+");
    }

    private boolean existingWord(String input) {
        // json call to external rest api?

        return true;
    }

    public int calculatePoints(String word) {
        return word.length();
    }
}
