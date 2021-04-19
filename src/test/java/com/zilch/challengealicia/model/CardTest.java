package com.zilch.challengealicia.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator;

    @BeforeEach
    void setUp() {
        validator = factory.getValidator();
    }

    @Test
    void testToString() {
        Card card = new Card();
        card.setId(1);
        card.setNumber("222");
        card.setExpireMonth(3);
        card.setExpireYear(2044);
        card.setName("5555");
        card.setCvc(666);
        String expected = "Card{" +
                "id='1'" +
                ", number='222'" +
                ", expireMonth='3'" +
                ", expireYear='2044'" +
                ", name='5555'" +
                ", cvc=666" +
                '}';
        assertEquals(expected, card.toString());
    }

    @Test
    void validateExpireYear() {
        Card card = createCardModel();
        card.setExpireYear(2044);
        Set<ConstraintViolation<Card>> constraintViolations = validator.validate(card);
        assertEquals(1, constraintViolations.size());
        assertEquals(CardValidationErrorMessages.IT_IS_NOT_A_VALID_YEAR, constraintViolations.iterator().next().getMessage());
    }

    @Test
    void validateExpireMonth() {
        Card card = createCardModel();
        card.setExpireMonth(13);
        Set<ConstraintViolation<Card>> constraintViolations = validator.validate(card);
        assertEquals(1, constraintViolations.size());
        assertEquals(CardValidationErrorMessages.IT_IS_NOT_A_VALID_MONTH, constraintViolations.iterator().next().getMessage());
    }

    @Test
    void validateNumber() {
        Card card = createCardModel();
        card.setNumber("13");
        Set<ConstraintViolation<Card>> constraintViolations = validator.validate(card);
        assertEquals(1, constraintViolations.size());
        assertEquals(CardValidationErrorMessages.THE_CARD_NUMBER_IS_INCORRECT_SIZE, constraintViolations.iterator().next().getMessage());
    }

    @Test
    void validateCvc() {
        Card card = createCardModel();
        card.setCvc(1345);
        Set<ConstraintViolation<Card>> constraintViolations = validator.validate(card);
        assertEquals(1, constraintViolations.size());
        assertEquals(CardValidationErrorMessages.IT_IS_NOT_A_VALID_CVC, constraintViolations.iterator().next().getMessage());
    }

    @Test
    void validateName() {
        Card card = createCardModel();
        card.setName("1111");
        Set<ConstraintViolation<Card>> constraintViolations = validator.validate(card);
        assertEquals(1, constraintViolations.size());
        assertEquals(CardValidationErrorMessages.IT_IS_NOT_A_VALID_NAME, constraintViolations.iterator().next().getMessage());
    }


    private Card createCardModel() {
        Card card = new Card();
        card.setId(1);
        card.setNumber("1111222233334444");
        card.setExpireMonth(3);
        card.setExpireYear(44);
        card.setName("Alicia Test");
        card.setCvc(666);
        return card;
    }

}
