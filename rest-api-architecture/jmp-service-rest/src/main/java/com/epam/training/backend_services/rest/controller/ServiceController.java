package com.epam.training.backend_services.rest.controller;

import com.epam.training.backend_services.rest.dto.SubscriptionRequestDto;
import com.epam.training.backend_services.rest.dto.SubscriptionResponseDto;
import com.epam.training.backend_services.rest.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
@Tag(name = "Subscription API")
public class ServiceController {

    @Autowired
//    @Qualifier("HeapSubscriptionService")
    @Qualifier("DbSubscriptionService")
    private SubscriptionService subscriptionService;

    @Operation(summary = "Add new subscription", description = "Returns a newly created subscription", tags = { "subscriptions", "post" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created", content = {
                    @Content(schema = @Schema(implementation = SubscriptionResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bed request")
    })
    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@Validated @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.create(subscriptionRequestDto));
    }

    @Operation(summary = "Get subscriptions list", description = "Returns a list of all subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAll());
    }

    @Operation(summary = "Get a subscription by id", description = "Returns a subscription as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The subscription was not found")
    })
    @GetMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(
            @PathVariable(value = "subscriptionId") Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.get(subscriptionId));
    }

    @Operation(summary = "Update the given subscription", description = "Returns updated subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @PutMapping("/subscriptions")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@Validated @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return ResponseEntity.ok(subscriptionService.update(subscriptionRequestDto));
    }

    @Operation(summary = "Delete given subscription by its id", description = "Remove subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted")
    })
    @DeleteMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
        subscriptionService.delete(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
