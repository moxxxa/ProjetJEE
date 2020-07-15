package com.mh.forum.transactions.services;

import com.mh.forum.transactions.dao.CreditorRepository;
import com.mh.forum.transactions.dao.DealingRepository;
import com.mh.forum.transactions.dao.DebtorRepository;
import com.mh.forum.transactions.model.Creditor;
import com.mh.forum.transactions.model.Dealing;
import com.mh.forum.transactions.model.Debtor;
import com.mh.forum.transactions.model.Sum;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;
	@Autowired
	private DebtorRepository debtorRepository;
    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    DealingRepository dealingRepository;

    public Payment createPayment(Double total, String currency, String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        //transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        //payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
       // payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }


    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {

        Payment payment = new Payment();
        Transaction transactions;
        Payee payee;
        PayerInfo payerInfo;
        Amount amount;

        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        transactions=  payment.execute(apiContext, paymentExecute).getTransactions().get(0);
        payee = transactions.getPayee();
        amount =transactions.getAmount();

        payerInfo = payment.execute(apiContext, paymentExecute).getPayer().getPayerInfo();
		Debtor debtor = new Debtor(payerInfo.getEmail(),payerInfo.getFirstName(),payerInfo.getLastName(),payerInfo.getPayerId(),payerInfo.getCountryCode());
        Creditor creditor = new Creditor(payee.getEmail(),payee.getMerchantId());
        Sum sum = new Sum(amount.getCurrency(),amount.getTotal());

        Dealing dealing = new Dealing(debtor,creditor, payment.execute(apiContext, paymentExecute).getCreateTime(),sum);
        dealingRepository.save(dealing);

		return payment.execute(apiContext, paymentExecute);
    }

}
