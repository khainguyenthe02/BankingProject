import Interface.ICustomerRepository;
import Interface.IDigitalBank;
import Models.Customer;

public class CustomerRepository implements ICustomerRepository {
    @Override
    public void DisplayInformation(Customer customer) {
        System.out.println("Tên khách hàng: " + Customer.CUSTOMER_NAME);
        System.out.println("Mã khách hàng: " + Customer.CUSTOMER_ID);
    }

    @Override
    public void showCustomer(String Id) {
        IDigitalBank acticeBank = new DigitalBank();
        Customer customer = acticeBank.getCustomerById(Id);
        if (customer != null) {
            DisplayInformation(customer);
        }
    }

    @Override
    public String GetAccountBalance() {
        IDigitalBank acticeBank = new DigitalBank();
        return acticeBank.GetBalance();
    }

    @Override
    public boolean WithDraw(String Type, int STK, int TradingMoney, double Balance) {
        IDigitalBank acticeBank = new DigitalBank();
        return acticeBank.WriteFile(Type, STK, TradingMoney, Balance);
    }


}
