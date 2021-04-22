package com.fintech.demo.controller;

import com.fintech.demo.dto.ApiResponseJSON;
import com.fintech.demo.dto.CardDetailsResponseJSON;
import com.fintech.demo.dto.HitCount;
import com.fintech.demo.persistence.repository.HitsRepository;
import com.fintech.demo.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Validated
@Api(tags = "Card verification Endpoints", description = "Endpoints for Card Verification")
@RestController
@RequestMapping(value = "/card-scheme")

public class PublicController {

    private CardService cardService;

    @Autowired
    private HitsRepository repository;


    @Autowired
    public PublicController(CardService cardService ) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "Card Details", notes = "This is used to get details of a card")
    @GetMapping(value = "/verify/{cardNo}")
    @ResponseBody
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponseJSON<CardDetailsResponseJSON>> verify(@PathVariable String cardNo){
        CardDetailsResponseJSON responseJSON = cardService.getCardDetails(cardNo);
                ApiResponseJSON<CardDetailsResponseJSON> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",responseJSON, true );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }


    @ApiOperation(value = "Card Details", notes = "This is used to get details of a card")
    @GetMapping(value = "/stats")
    @ResponseBody
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponseJSON<HashMap<String, Long>>> stats( @RequestParam(defaultValue = "0") Integer start,
                                                                  @RequestParam(defaultValue = "10") Integer limit){
        HashMap<String, Long> responseJSON = cardService.getMostSearchedCards(start,limit);
        Long size = cardService.getTotalCount();
        ApiResponseJSON<HashMap<String, Long>> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully", true, start, limit,size,responseJSON );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }
}
