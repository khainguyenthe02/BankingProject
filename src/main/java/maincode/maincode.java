//import Interface.ICheckCondition;
//import Interface.ICustomerRepository;
//import Interface.IDigitalBank;
//import Interface.IDisplay;
//import Models.Account;
//import Models.Customer;
//import java.util.Scanner;
//
//
//// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
//// then press Enter. You can now see whitespace characters in your code.
//public class Main {
//    //private static Logger logger = Logger.getLogger(Main.class);
//    public static void main(String[] args) {
//
//        Customer customer;
//        customer = new Customer();
//        ICustomerRepository customerRepository = new CustomerRepository();
//        IDigitalBank digitalBank = new DigitalBank();
//        Account account = new Account();
//        IDisplay display = new Display();
//        ICheckCondition checkCondition = new CheckCondition();
//        Scanner input = new Scanner(System.in);
//        String softwareName = "NGÂN HÀNG ĐIỆN TỬ";
//        String version = "@1.0.0";
//        account.setCode("FX123");
//        int select = 0;
//        boolean sl;
//        do{
//            try {
//                do {
//                    display.clearConsole();
//                    display.displayHeaderB4(softwareName, account.getCode(), version);
//                    display.displayBodyB4();
//                    System.out.print("Chọn chức năng: ");
//                    String inputString = input.nextLine().trim();
//                    sl = checkCondition.CheckMainDisplayFunction(inputString, 0, 5);
//                    if(sl){
//                        select = Integer.parseInt(inputString);
//                        break;
//                    }
//                } while (true);
//            }catch (NumberFormatException ex){
//                System.out.println(ex.getMessage());
//            }
//
//            switch (select) {
//                case 1:
//                    customerRepository.showCustomer("123456");
//                    break;
//                case 2: {
//                    String stk ="";
//                    String accountNumber = "";
//                    try {
//                        while (true) {
//                            System.out.println("+---------------------------------+");
//                            System.out.print("Nhập số tài khoản (6 ký tự): ");
//                            stk = input.nextLine();
//                            stk = stk.trim(); // cắt bỏ dấu cách ở đầu và cuối
//                            sl = checkCondition.CheckAccountNumber(stk);
//                            if(sl){
//                                accountNumber = stk;
//                                break;
//                            }
//                        }
//                    }catch (Exception ex){
//                        System.out.println("\tSố tài khoản không hợp lệ");
//                    }
//                    customer.setSTK(accountNumber);
//                    int balance = 0;
//                    String balanceStr;
//                    System.out.println("Nhập số dư ban đầu:");
//                    try{
//                        do {
//                            balanceStr = input.nextLine().trim(); // bỏ qua khoảng trắng đầu và cuối
//                            sl = checkCondition.CheckMoney(balanceStr);
//                            if(sl){
//                                balance = Integer.parseInt(balanceStr);
//                                break;
//                            }
//                        } while (true);
//                    }catch (Exception ex){
//                        System.out.println(ex.getMessage());
//                    }
//                    customer.setAccountBalance(balance);
//                    System.out.println("Số tài khoản ATM: " + stk + ", số dư: " + balance + " đ");
//                }
//                break;
//                case 3:
//                {
//                    String stk = "";
//                    String accountNumber = "";
//                    try {
//                        while (true) {
//                            System.out.println("+---------------------------------+");
//                            System.out.print("Nhập số tài khoản (6 ký tự): ");
//                            stk = input.nextLine();
//                            stk = stk.trim(); // cắt bỏ dấu cách ở đầu và cuối
//                            sl = checkCondition.CheckAccountNumber(stk);
//                            if(sl){
//                                accountNumber = stk;
//                                break;
//                            }
//                        }
//                    }catch (Exception ex){
//                        System.out.println("\tSố tài khoản không hợp lệ");
//                    }
//
//                    customer.setSTK(accountNumber);
//                    int balance = 0;
//                    String balanceStr;
//                    System.out.println("Nhập số dư ban đầu:");
//                    try{
//                        do {
//                            balanceStr = input.nextLine().trim(); // bỏ qua khoảng trắng đầu và cuối
//                            sl = checkCondition.CheckMoney(balanceStr);
//                            if(sl){
//                                balance = Integer.parseInt(balanceStr);
//                                break;
//                            }
//                        } while (true);
//                    }catch (Exception ex){
//                        System.out.println(ex.getMessage());
//                    }
//                    customer.setAccountBalance(balance);
//                    System.out.println("Số tài khoản tín dụng: " + stk + ", số dư: " + balance + " đ");
//                }
//                case 4: {
//                    String string = customerRepository.GetAccountBalance();
//                    StringBuilder tradingDate = new StringBuilder();
//                    StringBuilder balanceWithDraw = new StringBuilder();
//                    int len = string.length();
//                    for (int i = 0; i < len; i++) {
//                        if (i <= 18) tradingDate.append(string.charAt(i));
//                        else balanceWithDraw.append(string.charAt(i));
//                    }
//                    int accountBalance = Integer.parseInt(balanceWithDraw.toString());
//                    int withDrawMoney =0;
//                    try {
//                        do {
//                            display.displayTransaction();
//                            String inputString = input.nextLine().trim();
//                            sl = checkCondition.CheckMainDisplayFunction(inputString, 0, 3);
//                            if(sl){
//                                select = Integer.parseInt(inputString);
//                                break;
//                            }
//                        } while (true);
//                    }catch (NumberFormatException ex){
//                        System.out.println(ex.getMessage());
//                    }
//                    String strInput;
//                    System.out.print("Nhập số tiền: ");
//                    try{
//                        do {
//                            strInput = input.nextLine().trim();
//                            sl = checkCondition.CheckWithDrawMoney(strInput,withDrawMoney, select, accountBalance);
//                            if(sl){
//                                withDrawMoney = Integer.parseInt(strInput);
//                                break;
//                            }
//                        } while (true);
//                    }catch (NumberFormatException ex){
//                        System.out.println(ex.getMessage());
//
//                    }
//
//                    if (select == 0){
//                        System.exit(0);
//                        break;
//                    }
//
//                    if(select == 1){
//                        if (customerRepository.WithDraw("LOAN", 123456, withDrawMoney, accountBalance + withDrawMoney - withDrawMoney * 0.01)) {
//                            System.out.println("Vay thành công!!!");
//                            tradingDate = new StringBuilder(digitalBank.GetTradingDate());
//                            accountBalance = Integer.parseInt(balanceWithDraw.toString());
//                            display.TradingDisplay("LOAN", tradingDate.toString(), 123456, withDrawMoney, accountBalance + withDrawMoney - withDrawMoney * 0.01);
//                        }
//                    }
//                    if(select == 2){
//                        customerRepository.WithDraw("SAVINGS", 123456, withDrawMoney, accountBalance + withDrawMoney);
//                        System.out.println("Gửi tiết kiệm thành công!!!");
//                        tradingDate = new StringBuilder(digitalBank.GetTradingDate());
//                        accountBalance = Integer.parseInt(balanceWithDraw.toString());
//                        display.TradingDisplay("SAVINGS", tradingDate.toString(), 123456, withDrawMoney, accountBalance + withDrawMoney);
//                    }
//                    if (select == 3){
//                        customerRepository.WithDraw("TRANSACTION", 123456, -withDrawMoney, accountBalance - withDrawMoney - withDrawMoney * 0.01);
//                        System.out.println("Rút tiền thành công!!!");
//                        tradingDate = new StringBuilder(digitalBank.GetTradingDate());
//                        accountBalance = Integer.parseInt(balanceWithDraw.toString());
//                        display.TradingDisplay("WITHDRAW", tradingDate.toString(), 123456, -withDrawMoney, accountBalance - withDrawMoney - withDrawMoney * 0.01);
//                    }
//                }break;
//                case 5:
//                    digitalBank.TradingHistory();
//                    break;
//                default:
//                    break;
//            }
//        } while (select != 0);
//        input.close();
//    }
//}