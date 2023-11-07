import java.util.Random;

enum Priority {
    PRIO_CARD_THEFT,
    PRIO_CLOSE_ACCOUNT,
    PRIO_OPEN_ACCOUNT,
    PRIO_PAY_BILL,
    PRIO_DEPOSIT,
    PRIO_DRAW,
}

class Customer {
    int customerId;
    Priority priority;

    public Customer(int customerId, Priority priority) {
        this.customerId = customerId;
        this.priority = priority;
    }

    public String toString() {
        return "Priority is: " + priority.ordinal() + ", CustomerID is: " + customerId;
    }
}

public class BankingSimulator {
    public static void main(String[] args) {
        Customer[] queue = new Customer[10];
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int customerId = random.nextInt(1001) + 1000;
            Priority priority = Priority.values()[random.nextInt(Priority.values().length)];

            Customer customer = new Customer(customerId, priority);

            int insertIndex;
            for (insertIndex = 0; insertIndex < i; insertIndex++) {
                if (queue[insertIndex].priority.ordinal() > customer.priority.ordinal()) {
                    break;
                }
            }


            for (int j = i; j > insertIndex; j--) {
                queue[j] = queue[j - 1];
            }

            queue[insertIndex] = customer;

            System.out.println("Customer added. " + customer);
            printWaitingCustomers(queue, i + 1);

            if (random.nextBoolean() && i >= 0) {
                Customer removedCustomer = queue[0];
                for (int j = 1; j <= i; j++) {
                    queue[j - 1] = queue[j];
                }
                queue[i] = null;
                i--;

                System.out.println("Customer removed. " + removedCustomer);
                printWaitingCustomers(queue, i + 1);
            }
        }
    }

    private static void printWaitingCustomers(Customer[] queue, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println(queue[i]);
        }
        System.out.println("———————————————————————————————————");
    }
}
