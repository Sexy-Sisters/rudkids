package com.rudkids.core.order.infrastructure.dto;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class KakaoNotificationTalkRequest {

    @NoArgsConstructor
    @Getter
    public static class Send {
        @FormProperty("message_type")
        private String messageType;

        @FormProperty("sender_key")
        private String senderKey;

        @FormProperty("cid")
        private String cid;

        @FormProperty("template_code")
        private String templateCode;

        @FormProperty("phone_number")
        private String phoneNumber;

        @FormProperty("sender_no")
        private String senderNo;

        @FormProperty("message")
        private String message;

        @Builder
        public Send(String senderKey,
                    String cid,
                    String templateCode,
                    String phoneNumber,
                    String senderNo,
                    String message) {
            this.messageType = "AT";
            this.senderKey = senderKey;
            this.cid = cid;
            this.templateCode = templateCode;
            this.phoneNumber = phoneNumber;
            this.senderNo = senderNo;
            this.message = message;
        }
    }
}
