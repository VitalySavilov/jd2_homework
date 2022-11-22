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
@Table(name = "t_provider")
@PrimaryKeyJoinColumn(name = "PRODUCT_ID")
public class Provider extends Product{
    @Column(name = "PROVIDER_NAME")
    private String providerName;
    @Column(name = "PRICE")
    private double price;

    @Builder
    public Provider(Integer id, String productName, String providerName, double price) {
        super(id, productName);
        this.providerName = providerName;
        this.price = price;
    }
}
