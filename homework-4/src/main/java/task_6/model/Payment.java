package task_6.model;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_payment")
@Builder
public class Payment implements Serializable, BaseEntity<Integer> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "payment_gen")
    @TableGenerator(name = "payment_gen", table = "id_table", pkColumnName = "table_name",
            valueColumnName = "pk_value", allocationSize = 1)
    private Integer id;
    @Column(name = "DATE")
    private String date;
    @Column(name = "CLIENT_NAME")
    private String clientName;
}
