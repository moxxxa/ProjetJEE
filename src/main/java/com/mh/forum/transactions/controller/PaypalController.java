package com.mh.forum.transactions.controller;

import com.mh.forum.post.services.ForumService;
import com.mh.forum.transactions.model.Order;
import com.mh.forum.transactions.services.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PaypalController {

    @Autowired
    ForumService forumService;
    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/pay/{idPost}")
    public String payment(@RequestBody Order order,@PathVariable String idPost) {
        try {
            System.out.println("*************get post par id***********"+ forumService.addCollectes(idPost,order.getPrice()));
            Payment payment = service.createPayment(
                    order.getPrice(),
                    order.getCurrency(),
                   // order.getMethod(),
                   // order.getIntent(),
                    //order.getDescription(),
                    "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL
            );
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

//    @PostMapping("/pay")
//    public String payment(@ModelAttribute("order") Order order) {
//        try {
//            Payment payment = service.createPayment(
//                    order.getPrice(),
//                    order.getCurrency(),
//                    order.getMethod(),
//                    order.getIntent(),
//                    //order.getDescription(),
//                    "http://localhost:8080/" + CANCEL_URL,
//                    "http://localhost:8080/" + SUCCESS_URL
//            );
//            for (Links link : payment.getLinks()) {
//                if (link.getRel().equals("approval_url")) {
//                    return "redirect:" + link.getHref();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        List<String> transactions = new ArrayList<>();
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            transactions = Collections.singletonList(payment.toJSON());
           // System.out.println("payment.toJSON()"+ payment.toJSON());
//
//            System.out.println("*************getPotentialPayerInfo***********"+ payment.getPotentialPayerInfo());
//            System.out.println("*************getPayer***********"+ payment.getPayer().getPayerInfo());
//            System.out.println("*************getPayee***********"+ payment.getPayee());
//            System.out.println("*************getPaymentInstruction***********"+ payment.getPaymentInstruction());
//            System.out.println("*************getPaymentInstruction***********"+ payment.getPaymentInstruction());
//            System.out.println("*************getTransactions***********"+ payment.getTransactions());



            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println("e.getMessage()"+e.getMessage());
        }
        return "redirect:/";
    }

}



//@PostMapping("/pay")
//	public String payment(@RequestBody Order order) {
//		try {
//			Payment payment = service.createPayment(
//					order.getPrice(),
//					order.getCurrency(),
//					order.getMethod(),
//					order.getIntent(),
//					//order.getDescription(),
//					"http://localhost:9090/" + CANCEL_URL,
//					"http://localhost:9090/" + SUCCESS_URL
//			);
//			for (Links link : payment.getLinks()) {
//				if (link.getRel().equals("approval_url")) {
//					return "redirect:" + link.getHref();
//				}
//			}
//
//		} catch (PayPalRESTException e) {
//
//			e.printStackTrace();
//		}
//		return "redirect:/";
//	}