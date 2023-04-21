import Interface.IDisplay;

import java.io.IOException;
import java.text.DecimalFormat;

public class Display implements IDisplay {

    @Override
    public void TradingDisplay(String type, String tradingDate, int STK, int tradingMoney, double balance) {
        int width = 50;
        DecimalFormat formatter = new DecimalFormat("#,###");
        System.out.println("+" + "-".repeat(width) + "+");

        HeaderDisplay(type);
        DisplayTransactionDetails(tradingDate, STK, tradingMoney, formatter);
        TransactionFeeDisplay(type, tradingMoney, formatter);
        displayBalance(balance, formatter);

        System.out.println("+" + "-".repeat(width) + "+");
    }
    public void displayHeaderB4(String softwareName, String code, String version) {
        System.out.println("+--------------------------------+");
        System.out.println("| "+ softwareName +" | " + code + version +  " |");

    }
    public void displayBodyB4() {
        System.out.println("+---------------------------------+");
        System.out.println("|1. Thông tin khách hàng          |");
        System.out.println("|2. Thêm tài khoản ATM            |");
        System.out.println("|3. Thêm tài khoản tín dụng       |");
        System.out.println("|4. Giao dịch                     |");
        System.out.println("|5. Lịch sử giao dịch             |");
        System.out.println("|0. Thoát                         |");
        System.out.println("+---------------------------------+");
    }

    @Override
    public void displayTransaction() {
        System.out.println("Chọn phương thức giao dịch");
        System.out.println("1. LOAN (Vay)");
        System.out.println("2. SAVINGS (Gửi tiết kiệm)");
        System.out.println("3. WITHDRAW (Rút tiền)");
        System.out.print("Lựa chọn của bạn: ");
    }


    @Override
    public void HeaderDisplay(String type) {
        String TYPE = "         BIÊN LAI GIAO DỊCH " + type;
        System.out.println(TYPE);
    }

    @Override
    public void DisplayTransactionDetails(String tradingDate, int STK, int tradingMoney, DecimalFormat formatter) {
        System.out.print("NGÀY:");
        String string = String.format("%" + 41 + "s", tradingDate);
        System.out.println(string);

        System.out.print("ATM ID:");
        string = String.format("%" + 43 + "s", "DIGITAL-BANK-ATM 2023");
        System.out.println(string);

        System.out.print("SỐ TK:");
        string = String.format("%" + 44 + "s", STK);
        System.out.println(string);

        System.out.print("SỐ TIỀN:");
        String formatted = formatter.format(tradingMoney);
        string = String.format("%" + 41 + "s", formatted);
        System.out.println(string + "đ");
    }

    @Override
    public void TransactionFeeDisplay(String type, int tradingMoney, DecimalFormat formatter) {
        System.out.print("PHI + VAT:");
        if (type.equals("SAVINGS")) {
            String string = String.format("%" + 39 + "s", "0");
            System.out.println(string + "đ");
        } else if (type.equals("LOAN"))  {
            tradingMoney = -tradingMoney;
            int a = (int)(tradingMoney * 0.01);
            String formatted = formatter.format(a);
            String string = String.format("%" + 39 + "s", formatted);
            System.out.println(string + "đ");
        } else  {
            int a = (int)(tradingMoney * 0.01);
            String formatted = formatter.format(a);
            String string = String.format("%" + 39 + "s", formatted);
            System.out.println(string + "đ");
        }
    }

    @Override
    public void displayBalance(double balance, DecimalFormat formatter) {
        System.out.print("SO DU:");
        balance = (int)balance;
        String formatted = formatter.format(balance);
        String str = String.format("%" + 43 + "s", formatted);
        System.out.println(str + "đ");
    }

    @Override
    public void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {}
    }


}
