package it.academy.service;

import it.academy.dao.PaymentCardDao;
import it.academy.dao.UserDao;
import it.academy.dto.UserCardDto;
import it.academy.mapper.Mapper;
import it.academy.model.PaymentCard;
import it.academy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserCardService {
    private final UserDao userDao;
    private final PaymentCardDao paymentCardDao;
    private final Mapper<User, UserCardDto> mapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserCardDto addCard(Long userId, PaymentCard card) {
        User user = userDao.getById(userId);
        user.addCard(card);
        userDao.saveOrUpdate(user);
        return mapper.mapFrom(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserCardDto deleteCard(Long id) {
        PaymentCard card = paymentCardDao.getById(id);
        User user = card.getUser();
        user.getPaymentCards().remove(card);
        paymentCardDao.delete(card);
        return mapper.mapFrom(user);
    }
}
