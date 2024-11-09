class JointBankAccount {

    int balance = 0;

    public synchronized void deposit(int amount, String name) {
        balance += amount;
        System.out.println(name + " deposited: " + amount);
        System.out.println("Current balance: " + balance);
    }

    public synchronized void withdraw(int amount, String name) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(name + " withdrew: " + amount);
            System.out.println("Current balance: " + balance);
        } else {
            System.out.println(name + " attempted to withdraw: " + amount);
            System.out.println("Insufficient Balance.");
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        JointBankAccount jb = new JointBankAccount();

        Runnable vignesh = () -> {
            jb.deposit(100, "vignesh");
            jb.withdraw(50, "vignesh");
        };
        Runnable harsha = () -> {
            jb.deposit(100, "harsha");
            jb.withdraw(50, "harsha");
        };
        Runnable venkat = () -> {
            jb.deposit(100, "venkat");
            jb.withdraw(50, "venkat");
        };

        Thread thread1 = new Thread(vignesh);
        Thread thread2 = new Thread(harsha);
        Thread thread3 = new Thread(venkat);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
