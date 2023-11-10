package com.example.iamport.service;

import com.example.iamport.data.entity.PaymentInfo;
import com.example.iamport.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentsService {

      private final IamportClient iamportClientApi;
      private final PaymentRepository paymentRepository;
      @Value("${iamport.api_key}")
      private String API_KEY;

      @Value("${iamport.api_key}")
      private String SECRET_API_KEY;

      public PaymentsService(PaymentRepository paymentRepository)
      {
            this.paymentRepository = paymentRepository;
            this.iamportClientApi = new IamportClient(API_KEY, SECRET_API_KEY);
      }
      @Transactional
      public PaymentInfo paymentLookupService(int paymentNo) {
            PaymentInfo paymentsInfo = paymentRepository.getById(paymentNo);
            return paymentsInfo;
      }

      public IamportResponse<Payment> paymentLookup(String impUid) throws IamportResponseException, IOException
      {
            return iamportClientApi.paymentByImpUid(impUid);
      }

      public IamportResponse<Payment> paymentLookup(int paymentNo) throws IamportResponseException, IOException
      {
            PaymentInfo paymentInfo =  paymentLookupService(paymentNo);
            return iamportClientApi.paymentByImpUid(paymentInfo.getImpUid());
      }

}
