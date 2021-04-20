package com.zilch.challengealicia.model;

import com.zilch.challengealicia.service.CreditCardNumberGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Entity
@Validated
@ApiModel("A bank card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id card")
    private int id;
    @NotNull
    @Size(min = 16, max = 16, message = CardValidationErrorMessages.THE_CARD_NUMBER_IS_INCORRECT_SIZE)
    @ApiModelProperty("Number card of 16 digit")
    private String number;
    @NotNull
    @Min(value = 1, message= CardValidationErrorMessages.IT_IS_NOT_A_VALID_MONTH)
    @Max(value = 12, message = CardValidationErrorMessages.IT_IS_NOT_A_VALID_MONTH)
    @ApiModelProperty("Expire month")
    private int expireMonth;
    @NotNull
    @Min(value =21, message= CardValidationErrorMessages.IT_IS_NOT_A_VALID_YEAR)
    @Max(value = 99, message = CardValidationErrorMessages.IT_IS_NOT_A_VALID_YEAR)
    @ApiModelProperty("Expire year")
    private int expireYear;
    @NotNull
    @Pattern(regexp = "^[A-Za-z ]*$", message = CardValidationErrorMessages.IT_IS_NOT_A_VALID_NAME)
    @ApiModelProperty("Name of the owner of the card")
    private String name;
    @NotNull
    @Min(value =1, message= CardValidationErrorMessages.IT_IS_NOT_A_VALID_CVC)
    @Max(value = 999, message = CardValidationErrorMessages.IT_IS_NOT_A_VALID_CVC)
    @ApiModelProperty("Cvc card")
    private int cvc;

    public Card(String name) {
        this.name = name;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.expireMonth = localDate.getMonthValue();
        this.expireYear = localDate.getYear() + 2;
        this.expireYear = localDate.getYear() + 2 - 2000;
        Random rand = new Random(System.currentTimeMillis());
        int upperbound = 999;
        this.cvc = rand.nextInt(upperbound);
        CreditCardNumberGenerator ccg = new CreditCardNumberGenerator();
        this.number = ccg.generateRandomCreditCardNumber("5169", 16);
    }

    public Card(int id, String name, int expireMonth, int expireYear, int cvc, String number) {
        this.id = id;
        this.name = name;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.cvc = cvc;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {

        this.number = number;
    }

    public int getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(int month) {
        this.expireMonth = month;
    }

    public int getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(int year) {
        this.expireYear = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public Card() {
    }


    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", expireMonth='" + expireMonth + '\'' +
                ", expireYear='" + expireYear + '\'' +
                ", name='" + name + '\'' +
                ", cvc=" + cvc +
                '}';
    }
}
