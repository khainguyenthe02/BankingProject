package Models;

public class Customer {
    public static String CUSTOMER_ID;
    public static int ACCOUNT_BALANCE;
    public static String CUSTOMER_NAME;
    public static String STK;

    public String getCustomerId() {
        return CUSTOMER_ID;
    }

    public void setCustomerId(String customerId) {
        CUSTOMER_ID = customerId;
    }

    public int getAccountBalance() {
        return ACCOUNT_BALANCE;
    }

    public void setAccountBalance(int accountBalance) {
        ACCOUNT_BALANCE = accountBalance;
    }

    public String getCustomerName() {
        return CUSTOMER_NAME;
    }

    public void setCustomerName(String customerName) {
        CUSTOMER_NAME = customerName;
    }

    public String getSTK() {
        return STK;
    }

    public void setSTK(String STK) {
        Customer.STK = STK;
    }
}
