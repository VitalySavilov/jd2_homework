package it.academy.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "u_seq")
    @SequenceGenerator(name = "u_seq", sequenceName = "t_user_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "USER_MALE")
    private String usermale;
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PaymentCard> paymentCards = new ArrayList<>();

    public void addCard(PaymentCard paymentCard) {
        paymentCards.add(paymentCard);
        paymentCard.setUser(this);
    }
}
