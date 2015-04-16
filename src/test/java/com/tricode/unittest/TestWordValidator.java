package com.tricode.unittest;

import com.tricode.business.WordValidator;
import com.tricode.exception.NoExistingWordException;
import com.tricode.exception.NotAWordException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestWordValidator {

    @Test
    public void testValidCharacters() throws NotAWordException, NoExistingWordException {
        String testString = new String("house");

        WordValidator wordValidator = new WordValidator();
        assertTrue(wordValidator.validateWord(testString));
    }

    @Test(expected = NotAWordException.class)
    public void testInvalidCharacters() throws NotAWordException, NoExistingWordException {
        String testString = new String("averylongword withspace");

        WordValidator wordValidator = new WordValidator();
        wordValidator.validateWord(testString);

        testString = new String("averylongword$withdollar");
        wordValidator.validateWord(testString);
    }

    @Test
    public void testExistingWord() throws NotAWordException, NoExistingWordException {
        String testString = new String("house");

        WordValidator wordValidator = new WordValidator();
        assertTrue(wordValidator.validateWord(testString));
    }

    //TODO Enable again when json call can be made
    @Ignore
    @Test(expected = NoExistingWordException.class)
    public void testNonExistingWord() throws NotAWordException, NoExistingWordException {
        String testString = new String("averylongwordthatdoesnotexist");

        WordValidator wordValidator = new WordValidator();
        assertTrue(wordValidator.validateWord(testString));
    }

    @Test
    public void testCorrectCalculationWord() {
        String testString = new String("house");

        WordValidator wordValidator = new WordValidator();
        assertEquals(wordValidator.calculatePoints(testString), 5);
    }
}
