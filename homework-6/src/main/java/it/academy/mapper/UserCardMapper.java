package it.academy.mapper;

import it.academy.dto.CardDto;
import it.academy.dto.UserCardDto;
import it.academy.model.PaymentCard;
import it.academy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserCardMapper implements Mapper<User, UserCardDto> {
    private final Mapper<PaymentCard, CardDto> mapper;

    @Override
    public UserCardDto mapFrom(User object) {
        return new UserCardDto(object.getId(),
                object.getUsername(),
                object.getPaymentCards().stream()
                        .map(mapper::mapFrom)
                        .collect(Collectors.toList()));
    }
}
