package ch03;

class Account {
    int balance;
    final String name;

    Account(String name) {
        this.name = name;
    }

    void deposit(int dollars) {
        balance += dollars;
    }

    void withdraw(@SuppressWarnings("SameParameterValue") int dollars) {
        if (balance < dollars) {
            throw new InsufficientFundsException("balance only " + balance);
        }
        balance -= dollars;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public boolean hasPositiveBalance() {
        return balance > 0;
    }
}