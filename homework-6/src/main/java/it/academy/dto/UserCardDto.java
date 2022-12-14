package it.academy.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserCardDto {
    Long id;
    String username;
    List<CardDto> paymentCards;
}
