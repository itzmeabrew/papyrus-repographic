package com.prx.repographics.Service;

import com.prx.repographics.Model.UserInfo;
import com.prx.repographics.Service.ServiceIntrf.RazorPayIntrf;
import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorPay implements RazorPayIntrf
{

    RazorpayClient razorpay = null;

    public RazorPay(@Value("${rzp.key}") String key,@Value("${rzp.secret}") String secret)
    {
        try
        {
            razorpay = new RazorpayClient(key, secret);
        }
        catch (RazorpayException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order createOrder(float INR)
    {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",INR);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", "receipt#1");

        Order order = null;
        try
        {
            order = razorpay.orders.create(orderRequest);
        }
        catch (RazorpayException e)
        {
            throw new RuntimeException(e);
        }

        return order;
    }

    @Override
    public Order fetchOrder(String orderId)
    {
        Order order = null;
        try
        {
            order = razorpay.orders.fetch(orderId);
        }
        catch (RazorpayException e)
        {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public Order fetchPayments(String orderId)
    {
        return null;
    }

    @Override
    public PaymentLink createPaymentLink(Order order, UserInfo info)
    {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("upi_link",true);
        paymentLinkRequest.put("amount",order.toJson().get("amount"));
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        paymentLinkRequest.put("description","Payment for policy ur order");
        JSONObject customer = new JSONObject();
        customer.put("name",info.getName());
        customer.put("contact",info.getPhNo());
        customer.put("email",info.getEmail());
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);


        PaymentLink payment = null;
        try
        {
            payment = razorpay.paymentLink.create(paymentLinkRequest);
        }
        catch (RazorpayException e)
        {
            throw new RuntimeException(e);
        }

        return payment;
    }
}
