package Interface;

import java.text.DecimalFormat;

public interface IDisplay {
    void displayHeaderB4(String softwareName, String code, String version);
    void displayBodyB4();
    void displayTransaction();
    void TradingDisplay(String type, String tradingDate, int STK, int tradingMoney, double balance);
    void HeaderDisplay(String type);
    void DisplayTransactionDetails(String tradingDate, int STK, int tradingMoney, DecimalFormat formatter);
    void TransactionFeeDisplay(String type, int tradingMoney, DecimalFormat formatter);
    void displayBalance(double balance, DecimalFormat formatter);
    void clearConsole();

}
