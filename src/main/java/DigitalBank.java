import Interface.IDigitalBank;
import Models.Customer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;


public class DigitalBank  implements IDigitalBank {
    private static final Logger logger = Logger.getLogger(Main.class);
    @Override
    public Customer getCustomerById(String id) {
        Customer _customer = new Customer();
        if  (id.equals("123456")) {
            _customer.setCustomerId("123456");
            _customer.setCustomerName("Nguyễn Thế Khải");
        }
        if (id.equals("111222")) {
            _customer.setCustomerId("111222");
            _customer.setCustomerName("Admin");
        }

        return _customer;
    }

    @Override
    public boolean WriteFile(String Type, int STK, int TradingMoney, double Balance) {
        System.setProperty("filename", "log1");
        String log4jConfPath = "D:\\BE_Intern\\Java\\BankingProject\\src\\main\\resources\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedTradingMoney = formatter.format(TradingMoney);
        String formattedBalance = formatter.format((int)Balance);

        String formattedString = String.format("%" + 6 + "s", STK);
        formattedString += " | ";
        formattedString += String.format("%" + 12 + "s", Type); // type
        formattedString += " | ";
        formattedString += String.format("%" + 12 + "s", formattedTradingMoney);
        formattedString += "đ | ";
        formattedString += String.format("%" + 12 + "s", formattedBalance);
        formattedString += "đ";

        logger.info(formattedString);
        return true;
    }

    @Override
    public void ReadFile() {
        //String balance ="";
        try {
            //creates a new file instance
            File file = new File("D:\\BE_Intern\\Java\\BankingProject\\src\\main\\resources\\springtest.txt");
            FileReader fileReader =new FileReader(file);   //reads the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);  //creates a buffering character input stream
            StringBuilder stringBuffer = new StringBuilder();    //constructs a string buffer with no characters
            String line ;
            while ( (line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);      //appends line to string buffer
                stringBuffer.append("\n");     //line feed
            }
            fileReader.close();
            System.out.println(stringBuffer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String GetTradingDate() {
        String balance;
        String lastLineTime = ""; // biến lưu thời gian của dòng cuối cùng
        try {
            File file = new File("D:\\BE_Intern\\Java\\BankingProject\\src\\main\\resources\\springtest.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line ;
            while ((line = bufferedReader.readLine()) != null) {
                balance = line; // gán biến balance bằng line
                lastLineTime = line.substring(0, 19); // lấy 19 ký tự đầu tiên của line (thời gian)
            }
            fileReader.close();
            System.out.println("Thời gian dòng cuối cùng: " + lastLineTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  lastLineTime;
    }
    @Override
    public String GetBalance() {
        StringBuilder balance = new StringBuilder();
        StringBuilder tradingDate = new StringBuilder();
        try {
            File file=new File("D:\\BE_Intern\\Java\\BankingProject\\src\\main\\resources\\springtest.txt");    //creates a new file instance
            FileReader fileReader = new FileReader(file);   //reads the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);  //creates a buffering character input stream
            //constructs a string buffer with no characters
            String line;
            // Count line
            int i = 0;
            while((line = bufferedReader.readLine())!=null)
            {
                i++;
            }

            // Get Balance
            FileReader fileReaderBalance = new FileReader(file);   //reads the file
            BufferedReader bufferedReaderBalance = new BufferedReader(fileReaderBalance);
            int k = 0;


            while((line=bufferedReaderBalance.readLine())!=null) {
                int len = line.length();
                if (k == i - 1) {
                    for (int j = 0; j < len; j++) {
                        if (j <= 18) {
                            tradingDate.append(line.charAt(j));
                        }
                        if (j >= 62 && line.charAt(j) != ',' && (line).charAt(j) != 'đ' && (line).charAt(j) != ' ') {
                            balance.append((line).charAt(j));
                        }
                    }
                }
                k++;
            }

            fileReader.close();    //closes the stream and release the resources
            fileReaderBalance.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return tradingDate + balance.toString();
    }
    @Override
    public void TradingHistory() {
        IDigitalBank acticeBank = new DigitalBank();
        System.out.println("+" + "-".repeat(75) +"+");
        System.out.println("                             LỊCH SỬ GIAO DỊCH ");
        System.out.println("_".repeat(76));
        System.out.println("Thời gian giao dịch |  STK   |Loại giao dịch|   Số tiền gd  |   Số dư");
        System.out.println("-".repeat(76));
        acticeBank.ReadFile();
        System.out.println("+" + "-".repeat(75) +"+");
    }
}
