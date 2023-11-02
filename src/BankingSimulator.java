import java.util.LinkedList;
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
        LinkedList<Customer> queue = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int customerId = random.nextInt(1001) + 1000;
            Priority priority = Priority.values()[random.nextInt(Priority.values().length)];

            Customer customer = new Customer(customerId, priority);

            int insertIndex = 0;
            while (insertIndex < queue.size() && queue.get(insertIndex).priority.ordinal() <= customer.priority.ordinal()) {
                insertIndex++;
            }

            queue.add(insertIndex, customer);

            System.out.println("Customer added. " + customer);
            printWaitingCustomers(queue);

            if (random.nextBoolean() && !queue.isEmpty()) {
                Customer removedCustomer = queue.removeFirst();
                System.out.println("Customer removed. " + removedCustomer);
                printWaitingCustomers(queue);
            }
        }
    }

    private static void printWaitingCustomers(LinkedList<Customer> queue) {
        for (Customer customer : queue) {
            System.out.println(customer);
        }
        System.out.println("———————————————————————————————————");
    }
}
