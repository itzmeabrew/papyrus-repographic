package com.prx.repographics.Service.ServiceIntrf;

import com.prx.repographics.Model.UserInfo;
import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface RazorPayIntrf
{
    Order createOrder(float INR);
    Order fetchOrder(String orderId);
    Order fetchPayments(String orderId);
    PaymentLink createPaymentLink(Order order, UserInfo userInfo);
}
