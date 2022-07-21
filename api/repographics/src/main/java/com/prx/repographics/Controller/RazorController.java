package com.prx.repographics.Controller;

import com.prx.repographics.Model.UserInfo;
import com.prx.repographics.Repository.UserRepo;
import com.prx.repographics.Service.RazorPay;
import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/razor")
public class RazorController
{
    @Autowired
    RazorPay razorPay;
    @Autowired
    UserRepo userRepo;

    @PostMapping("create-order")
    private ResponseEntity<JSONObject> createOrder(@RequestBody Map<String, String> payload) throws RazorpayException, ParseException
    {
        final float amount = Float.parseFloat(payload.get("amount"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo user = userRepo.getByRegNo(auth.getName());
        Order rzOrder = razorPay.createOrder(amount);
        //PaymentLink link = razorPay.createPaymentLink(rzOrder,user);

        JSONObject response  = new JSONObject();
        response.put("id", rzOrder.toJson().get("id"));
        response.put("amount_due", rzOrder.toJson().get("amount_due"));
        response.put("currency", rzOrder.toJson().get("currency"));
        response.put("created_at", rzOrder.toJson().get("created_at"));

        return ResponseEntity.ok(response);
    }


}
