package it.academy.dao;

import it.academy.model.PaymentCard;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentCardDaoImpl implements PaymentCardDao {
    private final SessionFactory sessionFactory;

    @Override
    public PaymentCard saveOrUpdate(PaymentCard paymentCard) {
        sessionFactory.getCurrentSession().saveOrUpdate(paymentCard);
        return paymentCard;
    }

    @Override
    public PaymentCard getById(Long id) {
        return sessionFactory.getCurrentSession().get(PaymentCard.class, id);
    }

    @Override
    public void delete(PaymentCard paymentCard) {
        sessionFactory.getCurrentSession().delete(paymentCard);
    }
}
