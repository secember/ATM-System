package atm.service;

import atm.model.User;

public class ATMServiceImpl implements ATMService {

    @Override
    public boolean authenticate(User user, int pin) {
        return user.getPin() == pin;
    }

    @Override
    public double checkBalance(User user) {
        return user.getBalance();
    }

    @Override
    public void deposit(User user, double amount) {
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
        }
    }

    @Override
    public boolean withdraw(User user, double amount) {
        if (amount > 0 && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            return true;
        }
        return false;
    }
}
