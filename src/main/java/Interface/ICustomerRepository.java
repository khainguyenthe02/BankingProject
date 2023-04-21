package Interface;


import Models.Customer;

public interface ICustomerRepository {
    void DisplayInformation(Customer customer); // Giao diện
    void showCustomer(String Id); // Hiển thị khách hàng
    String GetAccountBalance(); // Lấy số dư
    boolean WithDraw(String Type, int STK, int TradingMoney, double Balance); // Rút tiền

}
