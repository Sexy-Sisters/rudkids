package com.rudkids.rudkids.domain.order.domain;

import com.rudkids.rudkids.domain.order.exception.InvalidDeliveryFragmentException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class DeliveryFragment {
    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private String receiverAddress1;
    private String receiverAddress2;
    private String etcMessage;

    @Builder
    public DeliveryFragment(
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage
    ) {
        if (StringUtils.isEmpty(receiverName)) throw new InvalidDeliveryFragmentException();
        if (StringUtils.isEmpty(receiverPhone)) throw new InvalidDeliveryFragmentException();
        if (StringUtils.isEmpty(receiverZipcode)) throw new InvalidDeliveryFragmentException();
        if (StringUtils.isEmpty(receiverAddress1)) throw new InvalidDeliveryFragmentException();
        if (StringUtils.isEmpty(receiverAddress2)) throw new InvalidDeliveryFragmentException();
        if (StringUtils.isEmpty(etcMessage)) throw new InvalidDeliveryFragmentException();

        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.etcMessage = etcMessage;
    }

    public void update(String receiverName,
                       String receiverPhone,
                       String receiverZipcode,
                       String receiverAddress1,
                       String receiverAddress2,
                       String etcMessage) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.etcMessage = etcMessage;
    }
}
