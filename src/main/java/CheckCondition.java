import Interface.ICheckCondition;


import java.util.regex.Pattern;

public class CheckCondition  implements ICheckCondition {

    @Override
    public boolean CheckMainDisplayFunction(String inputFunction, int min, int max) {
    int select;
        inputFunction = inputFunction.trim(); // cắt bỏ dấu cách ở đầu và cuối
        // đọc chuỗi đầu vào và loại bỏ ký tự trắng
        if (inputFunction.isEmpty()) {
            System.out.println("Lỗi: Bạn chưa nhập chức năng. Vui lòng nhập lại.");
            return false;
        }
        else if (inputFunction.length() > 1 && inputFunction.startsWith("0")) {
            System.out.println("Chức năng không thể bắt đầu bằng số 0.");
            return false;
        } else if (!inputFunction.matches("\\d+")) {
            System.out.println("Lỗi: Chức năng bạn nhập không hợp lệ. Vui lòng nhập lại.");
            return false;
        } else {
            long check = Long.parseLong(inputFunction);
            if(check < Integer.MAX_VALUE && check > Integer.MIN_VALUE){
                select = (int) check;
                if (select >= min  && select <= max) {
                    System.out.println("Bạn chọn thành công chức năng "+ select);
                    return true;
                }
                else {
                    System.out.println("Lỗi: Chức năng bạn nhập không hợp lệ. Vui lòng nhập lại.");
                    return false;
                }
            }
            else {
                System.out.println("Lỗi: Chức năng bạn nhập không hợp lệ. Vui lòng nhập lại.");
                return false;
            }
        }

    }

    @Override
    public boolean CheckAccountNumber(String stk) {
        try {
            stk = stk.trim(); // cắt bỏ dấu cách ở đầu và cuối
            if (stk.isEmpty()) {
                System.out.println("Bạn chưa nhập số tài khoản");
                return false;
            } else {
                if ( Pattern.matches("^[a-zA-Z]$", stk)) {
                    System.out.println("\tSố tài khoản không hợp lệ, vui lòng nhập lại.");
                    return false;
                }
                else if (stk.length() == 6 && stk.startsWith("0")){
                    System.out.println("\t Số tài khoanr không thể bắt đầu bằng  số 0, vui lòng nhập lại");
                    return false;

                }else if (!Pattern.matches("^[0-9]{6}$", stk)) {
                    System.out.println("\tSố tài khoản gồm 6 chữ số, vui lòng nhập lại.");
                    return false;
                } else if (stk.equals("123456")) {
                    System.out.println("\tSố tài khoản đã tồn tại");
                    return false;
                } else if (Pattern.matches("^(\\d)\\1{5}$", stk)) {
                    System.out.println("\tBạn vừa nhập số tài khoản trùng nhau");
                    return true;
                }
                return true;
            }
        }catch (Exception ex){
            System.out.println("\tSố tài khoản không hợp lệ");
            return false;
        }

    }


    @Override
    public boolean CheckMoney(String balanceStr) {
        try {
            int balance;
            if (balanceStr.isEmpty()) {
                System.out.println("Bạn chưa nhập số dư!!!");
                System.out.println("Nhập lại số dư:");
                return false;
            }
            else if (balanceStr.matches("^[0-9]+$")) {
                long check = Long.parseLong(balanceStr);
                if(check <= Integer.MAX_VALUE && check >= Integer.MIN_VALUE){
                    balance = (int) check;
                    if (balance >= 0 && balance <= 2000000000) {
                        System.out.println("Thêm thành công");
                        return true;
                    } else {
                        System.out.println("Số dư tối thiểu 0đ và tối đa 2.000.000.000đ ");
                        System.out.println("Nhập lại số dư:");
                        return false;
                    }
                }
                else {
                    System.out.println("Số dư vượt quá giới hạn cho phép");
                    System.out.println("Nhập lại số dư:");
                    return false;
                }
            }
            else {
                System.out.println("Số dư không đúng định dạng, vui lòng nhập lại");
                System.out.println("Nhập lại số dư:");
                return false;
            }
        }catch (NumberFormatException ex){
            System.out.println("Số dư không hợp lệ");
            System.out.println("Nhập lại số dư:");
            return false;
        }
    }

    @Override
    public boolean CheckWithDrawMoney(String strInput, int withDrawMoney, int select, int accountBalance) {
       try {
           if (strInput.isEmpty()) {
               System.out.println("Bạn chưa nhập số tiền!!!");
               System.out.println("Nhập lại số tiền:");
               return false;
           }
           else {
               if (strInput.matches("^[0-9]+$")) {
                   long WithDrawMoneyCheck = Long.parseLong(strInput);
                   if(WithDrawMoneyCheck > Integer.MAX_VALUE || WithDrawMoneyCheck < Integer.MIN_VALUE){
                       System.out.println("Số tiền bạn nhập quá lớn hoặc quá bé. Vui lòng nhập lại");
                       return false;
                   }
                   else {
                       withDrawMoney = (int) WithDrawMoneyCheck;
                       if(select == 1 || select == 2 ){
                           if (withDrawMoney >= 10000 && withDrawMoney <= 5000000 && (withDrawMoney % 10000 == 0)){
                               return true;
                           }
                           else {
                               System.out.println("Số tiền giao dịch nằm trong khoảng 10.000đ đến 5.000.000đ và là bội số của 10.000đ");
                               System.out.println("Nhập lại số tiền:");
                               return false;
                           }
                       } else {
                           if(withDrawMoney % 10000 != 0){
                               System.out.println("Số tiền rút phải là bội số của 10.000đ");
                               System.out.println("Nhập lại số tiền:");
                               return false;
                           }
                           else {
                               if (withDrawMoney < 50000 || withDrawMoney > 5000000){
                                   System.out.println("Số tiền giao dịch nằm khoảng [50.000đ - 5.000.000đ]");
                                   System.out.println("Nhập lại số tiền:");
                                   return false;
                               }
                               else {
                                   if (withDrawMoney > accountBalance || (accountBalance - withDrawMoney - withDrawMoney*0.01) < 50000){
                                       System.out.println("Số tiền rút lớn hơn số dư hoặc số dư còn lại nhỏ hơn 50.000đ");
                                       System.out.println("Nhập lại số tiền:");
                                       return false;
                                   }
                                   else {
                                       System.out.println("Rút tiền thành công");
                                       return true;
                                   }
                               }
                           }
                       }
                   }
               }
               else {
                   System.out.println("Số tiền không hợp lệ");
                   System.out.println("Nhập lại số tiền:");
                   return false;
               }
           }
       }catch (NumberFormatException ex){
           System.out.println("Số tiền không hợp lệ");
           System.out.println("Nhập lại số tiền:");
           return false;
       }
    }
}
