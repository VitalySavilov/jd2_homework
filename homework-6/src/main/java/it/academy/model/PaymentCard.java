package it.academy.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "t_payment_card")
public class PaymentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CARD_TYPE")
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(name = "CARD_NUM")
    private String cardNum;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
