package testtask.cellphonevalidator.servicies.impl;

import testtask.cellphonevalidator.models.VerificationData;
import testtask.cellphonevalidator.servicies.StorageService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class StorageServiceImpl implements StorageService {
    private final ConcurrentHashMap<String, VerificationData> dataStore = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public StorageServiceImpl() {
        executorService.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();
            dataStore.entrySet().removeIf(entry -> currentTime > entry.getValue().getExpiryTime());
        }, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public Optional<VerificationData> getVerificationData(String phoneNumber) {
        return Optional.ofNullable(dataStore.get(phoneNumber));
    }

    @Override
    public void addVerificationData(String phoneNumber, VerificationData data) {
        dataStore.put(phoneNumber, data);
    }

    @Override
    public void deleteVerificationDataByPhoneNumber(String phoneNumber) {
        dataStore.remove(phoneNumber);
    }

}
