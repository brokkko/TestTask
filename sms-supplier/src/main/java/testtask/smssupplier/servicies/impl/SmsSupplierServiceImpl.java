package testtask.smssupplier.servicies.impl;

import org.springframework.stereotype.Service;
import testtask.smssupplier.servicies.SmsSupplierService;

@Service
public class SmsSupplierServiceImpl implements SmsSupplierService {
    @Override
    public void sendSms(String phoneNumber, String code) {
        System.out.println("Sending SMS to " + phoneNumber + " with code: " + code);
    }
}
