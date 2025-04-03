package atm.service;

import atm.model.User;

public interface ATMService {
    boolean authenticate(User user, int pin);
    double checkBalance(User user);
    void deposit(User user, double amount);
    boolean withdraw(User user, double amount);
}
