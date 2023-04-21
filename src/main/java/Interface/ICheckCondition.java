package Interface;

public interface ICheckCondition {
    boolean CheckMainDisplayFunction( String  inputFunction, int min, int max);
    boolean CheckAccountNumber(String stk);
    boolean CheckMoney(String balanceStr);
    boolean CheckWithDrawMoney(String strInput, int withDrawMoney, int select, int accountBalance);
}
