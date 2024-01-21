package testtask.smssupplier.servicies;

public interface SmsSupplierService {
    void sendSms(String phoneNumber, String code);
}
