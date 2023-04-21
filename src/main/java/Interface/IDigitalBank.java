package Interface;

import Models.Customer;

public interface IDigitalBank {
    Customer getCustomerById(String id);
    boolean WriteFile(String Type, int STK, int TradingMoney, double Balance);
    void ReadFile();
    String GetBalance();
    String GetTradingDate();
    void TradingHistory(); // Lịch sử giao dịch
}
