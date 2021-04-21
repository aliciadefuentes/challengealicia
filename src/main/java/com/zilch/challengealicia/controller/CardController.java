package com.zilch.challengealicia.controller;


import com.zilch.challengealicia.model.Card;
import com.zilch.challengealicia.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "Create a Zilch card since the name of the owner of the card")
    @PostMapping(path = "/cards", consumes = {"application/json"})
    public ResponseEntity<Card> createCard(
            @ApiParam("Json with field name of the person that want a new card") @RequestBody Card card) {

        Card cardGen = cardService.generateCard(card.getName());
        return ResponseEntity.status(HttpStatus.CREATED).header("ContentType", "application/json").body(cardGen);
    }

    @ApiOperation(value = "Listing all the card in the database")
    @GetMapping("/cards")
    public List<Card> findCards() {

        return cardService.findAllCards();
    }

    @ApiOperation(value = "Retrieve a card by id card")
    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> findCard(@ApiParam("Id of the card that we want to find") @PathVariable(required = true) Integer id) {

        Optional<Card> card = cardService.findCard(id);
        if (card.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(card.get());
        }
    }

}
