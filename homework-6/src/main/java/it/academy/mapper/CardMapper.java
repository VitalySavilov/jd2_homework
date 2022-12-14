package it.academy.mapper;

import it.academy.dto.CardDto;
import it.academy.model.PaymentCard;
import org.springframework.stereotype.Component;

@Component
public class CardMapper implements Mapper<PaymentCard, CardDto> {

    @Override
    public CardDto mapFrom(PaymentCard paymentCard) {
        return new CardDto(paymentCard.getCardType(), paymentCard.getCardNum());
    }
}
