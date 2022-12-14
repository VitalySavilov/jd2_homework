package it.academy.dto;

import it.academy.model.CardType;
import lombok.Value;

@Value
public class CardDto {
    CardType cardType;
    String cardNum;
}
