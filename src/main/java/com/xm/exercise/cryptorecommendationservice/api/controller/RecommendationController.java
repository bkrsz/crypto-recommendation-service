package com.xm.exercise.cryptorecommendationservice.api.controller;

import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.application.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Crypto Recommendation Service")
@RestController
@RequestMapping(value = "/crypto")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Operation(
         summary = "All cryptos normalized range",
         description = "Exposes an endpoint that will return a descending sorted list of all the cryptos,\n" +
            "comparing the normalized range"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "404", description = "No data found"),
            @ApiResponse(responseCode = "500", description = "Error in the operation"),
    })
    @RequestMapping(value = "/normalized", method = RequestMethod.GET)
    public List<CryptoNormalizedRange> getAllCryptosNormalized() {
        return recommendationService.calculateNormalizedRanges();
    }

    @Operation(
         summary = "Crypto calculated values",
         description = "Exposes an endpoint that will return the oldest/newest/min/max values for the requested\n" +
            "crypto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "404", description = "No data found"),
            @ApiResponse(responseCode = "500", description = "Error in the operation"),
    })
    @RequestMapping(value = "/values/{symbol}", method = RequestMethod.GET)
    public CryptoCalculatedValues getCryptoValues(@PathVariable(name = "symbol") String symbol) {
        return recommendationService.calculateValues(symbol);
    }

    @Operation(
         summary = "Crypto by the highest normalized range by a date",
         description = "Exposes an endpoint that will return the crypto with the highest normalized range for a\n" +
            "specific day"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "404", description = "No data found"),
            @ApiResponse(responseCode = "500", description = "Error in the operation"),
    })
    @RequestMapping(value = "/highest-normalized/{date}", method = RequestMethod.GET)
    public CryptoNormalizedRange getHighestNormalizedRange(@PathVariable(name = "date") String date) {
        return recommendationService.calculateHighestNormalizedRange(date);
    }
}
