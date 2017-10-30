// -----------------------------------------------------
// Assignment #3
// Part: (1)
// Written by: Zhihong Guo(40038183),Tiantian Ji(27781083)
// -----------------------------------------------------

/**
 * @author Zhihong Guo,Tiantian Ji
 * @version 1.8
 */
public class DuplicateISBNException extends Exception {

    /**
     *default constructor
     */
    public DuplicateISBNException()
    {
        super("Attempt of duplicate entry to a previous record.");
    }

    /**
     *Parametrized constructor
     * @param message is a String value
     */
    public DuplicateISBNException(String message) {
        super("Attempt of duplicate entry to a previous record." +"\n" + message +"\n"+"Error.... Duplicate entry of ISBN.The entered ISBN exists for another record.");
    }
}
