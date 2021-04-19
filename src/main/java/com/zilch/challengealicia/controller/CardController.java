package com.zilch.challengealicia.controller;


import com.zilch.challengealicia.model.Card;
import com.zilch.challengealicia.service.CardService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(
            @ApiParam("The name of the person that want a new card") @RequestBody String name) {

        Card card = cardService.generateCard(name);
        return ResponseEntity.ok(card);
    }
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> findCards() {

        List<Card> cards = cardService.findAllCards();
        return ResponseEntity.ok(cards);
    }
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
