package task_8_joined.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_sale")
@PrimaryKeyJoinColumn(name = "PRODUCT_ID")
public class Sale extends Product{
    @Column(name = "CLIENT_NAME")
    private String clientName;
    @Column(name = "ORDER_PRICE")
    private double orderPrice;

    @Builder
    public Sale(Integer id, String productName, String clientName, double orderPrice) {
        super(id, productName);
        this.clientName = clientName;
        this.orderPrice = orderPrice;
    }
}
