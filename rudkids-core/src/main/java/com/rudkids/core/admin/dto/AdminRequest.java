package com.rudkids.core.admin.dto;

public class AdminRequest {

    public record ChangeUserRole(String roleType) {}

    public record RegisterDeliveryTrackingNumber(String deliveryTrackingNumber) { }
}
