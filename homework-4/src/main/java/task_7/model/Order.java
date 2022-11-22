package task_7.model;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
@Builder
public class Order implements Serializable, BaseEntity<Integer> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "SUM")
    private double sum;
    private ClientInfo clientInfo;
    private Address address;
}
