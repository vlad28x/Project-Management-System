package ru.example.projectservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.example.projectservice.dto.DebtRequestDto;
import ru.example.projectservice.dto.DebtResponseDto;

@FeignClient(
        name = "payment-service",
        url = "${payment-service.url}"
)
public interface PaymentClient {

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/accounts/pay")
    ResponseEntity<DebtResponseDto> payDebt(@RequestHeader("Authorization") String jwt, @RequestBody DebtRequestDto debt);

}
