import Interface.*;
import Models.Account;
import Models.Customer;

import java.util.Scanner;

public class CheckFunction implements ICheckFunction {
    ICustomerRepository customerRepository = new CustomerRepository();
    IDigitalBank digitalBank = new DigitalBank();
    Account account = new Account();
    IDisplay display = new Display();
    ICheckCondition checkCondition = new CheckCondition();
    Scanner input = new Scanner(System.in);
    int select = 0;
    boolean sl;
    @Override
    public void CheckMainFunction() {
        Customer customer;
        customer = new Customer();
        String softwareName = "NGÂN HÀNG ĐIỆN TỬ";
        String version = "@1.0.0";
        account.setCode("FX123");
        do {
            try {
                do {
                    display.clearConsole();
                    display.displayHeaderB4(softwareName, account.getCode(), version);
                    display.displayBodyB4();
                    System.out.print("Chọn chức năng: ");
                    String inputString = input.nextLine().trim();
                    sl = checkCondition.CheckMainDisplayFunction(inputString, 0, 5);
                    if(sl){
                        select = Integer.parseInt(inputString);
                        break;
                    }
                } while (true);
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
            switch (select){
                case 0 -> System.exit(0);
                case 1-> customerRepository.showCustomer("123456");
                case 2, 3 -> {
                    String stk ;
                    String accountNumber ;
                        while (true) {
                            System.out.println("+---------------------------------+");
                            System.out.print("Nhập số tài khoản (6 ký tự): ");
                            stk = input.nextLine();
                            stk = stk.trim(); // cắt bỏ dấu cách ở đầu và cuối
                            sl = checkCondition.CheckAccountNumber(stk);
                            if(sl){
                                accountNumber = stk;
                                break;
                            }
                        }
                    customer.setSTK(accountNumber);
                    int balance ;
                    String balanceStr;
                    System.out.println("Nhập số dư ban đầu:");
                    do {
                        balanceStr = input.nextLine().trim(); // bỏ qua khoảng trắng đầu và cuối
                        sl = checkCondition.CheckMoney(balanceStr);
                        if(sl){
                            balance = Integer.parseInt(balanceStr);
                            break;
                        }
                    } while (true);
                    if(select == 2){
                        customer.setAccountBalance(balance);
                        System.out.println("Số tài khoản ATM: " + stk + ", số dư: " + balance + " đ");
                    }else{
                        customer.setAccountBalance(balance);
                        System.out.println("Số tài khoản tín dụng: " + stk + ", số dư: " + balance + " đ");
                    }
                }

                case 4-> {
                    String string = customerRepository.GetAccountBalance();
                    StringBuilder tradingDate = new StringBuilder();
                    StringBuilder balanceWithDraw = new StringBuilder();
                    int len = string.length();
                    for (int i = 0; i < len; i++) {
                        if (i <= 18) tradingDate.append(string.charAt(i));
                        else balanceWithDraw.append(string.charAt(i));
                    }
                    int accountBalance = Integer.parseInt(balanceWithDraw.toString());
                    int withDrawMoney =0;
                    do {
                        System.out.println("+---------------------------------+");
                        System.out.println("\tSố dư hiện tại :" + accountBalance + " đ");
                        display.displayTransaction();
                        String inputString = input.nextLine().trim();
                        sl = checkCondition.CheckMainDisplayFunction(inputString, 0, 3);
                        if(sl){
                            select = Integer.parseInt(inputString);
                            if(select == 0){
                                System.exit(0);
                            }else break;
                        }
                    } while (true);
                    String strInput;
                    System.out.print("Nhập số tiền: ");
                    do {
                        strInput = input.nextLine().trim();
                        sl = checkCondition.CheckWithDrawMoney(strInput,withDrawMoney, select, accountBalance);
                        if(sl){
                            withDrawMoney = Integer.parseInt(strInput);
                            CheckTradingDisplayFunction(withDrawMoney, accountBalance, tradingDate, balanceWithDraw);
                            break;
                        }
                    } while (true);

                }
                case 5-> digitalBank.TradingHistory();
                default-> System.out.println("Bạn nhập chưa chính xác");
            }
        }while (select >= 0);
        input.close();
    }

    @Override
    public void CheckTradingDisplayFunction(int withDrawMoney, int accountBalance, StringBuilder tradingDate, StringBuilder balanceWithDraw ) {
        if(select >=0 && select <= 4){
            switch (select){
                case 0 -> System.exit(0);
                case 1 -> {
                    if (customerRepository.WithDraw("LOAN", 123456, withDrawMoney, accountBalance + withDrawMoney - withDrawMoney * 0.01)) {
                        System.out.println("Vay thành công!!!");
                        tradingDate = new StringBuilder(digitalBank.GetTradingDate());
                        accountBalance = Integer.parseInt(balanceWithDraw.toString());
                        display.TradingDisplay("LOAN", tradingDate.toString(), 123456, withDrawMoney, accountBalance + withDrawMoney - withDrawMoney * 0.01);
                    }
                }
                case 2 -> {
                    customerRepository.WithDraw("SAVINGS", 123456, withDrawMoney, accountBalance + withDrawMoney);
                    System.out.println("Gửi tiết kiệm thành công!!!");
                    tradingDate = new StringBuilder(digitalBank.GetTradingDate());
                    accountBalance = Integer.parseInt(balanceWithDraw.toString());
                    display.TradingDisplay("SAVINGS", tradingDate.toString(), 123456, withDrawMoney, accountBalance + withDrawMoney);
                }
                case 3 -> {
                    customerRepository.WithDraw("TRANSACTION", 123456, -withDrawMoney, accountBalance - withDrawMoney - withDrawMoney * 0.01);
                    tradingDate = new StringBuilder(digitalBank.GetTradingDate());
                    accountBalance = Integer.parseInt(balanceWithDraw.toString());
                    display.TradingDisplay("WITHDRAW", tradingDate.toString(), 123456, -withDrawMoney, accountBalance - withDrawMoney - withDrawMoney * 0.01);
                }
                default -> System.out.println("Bạn nhập chưa chính xác");
            }
        }
        else {
            System.out.println("bạn nhập chưa chính xác");
        }

    }
}
