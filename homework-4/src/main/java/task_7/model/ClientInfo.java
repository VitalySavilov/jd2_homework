package task_7.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ClientInfo {
    @Column(name = "CLIENT_NAME")
    private String clientName;
    @Column(name = "CLIENT_TEL_NUMBER")
    private String clientTelNumber;
}
