package it.academy.dao;

import it.academy.model.PaymentCard;

public interface PaymentCardDao {
    PaymentCard saveOrUpdate(PaymentCard paymentCard);
    PaymentCard getById(Long id);
    void delete(PaymentCard paymentCard);
}
