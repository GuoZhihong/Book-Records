// -----------------------------------------------------
// Assignment #3
// Part: (1/2)
// Written by: Zhihong Guo(40038183),Tiantian Ji(27781083)
// -----------------------------------------------------
import java.io.Serializable;

/**
 * @author Zhihong Guo,Tiantian Ji
 * @version 1.8
 */
public class Book implements Serializable{
    /**
     * private attributes
     */
    private long ISBN;
    private  String title;
    private  int issueYear;
    private String authorName;
    private double price;
    private int numberOfPage;

    /**
     * default constructor
     */
    public Book() {
    }

    /**
     *Parametrized constructor
     * @param ISBN is a long value
     * @param title is a String value
     * @param issueYear is an integer value
     * @param authorName is a String value
     * @param price is a double value
     * @param numberOfPage is an integer value
     */
    public Book(long ISBN, String title, int issueYear, String authorName, double price, int numberOfPage) {
        this.ISBN = ISBN;
        this.title = title;
        this.issueYear = issueYear;
        this.authorName = authorName;
        this.price = price;
        this.numberOfPage = numberOfPage;
    }

    /**
     *
     * @return a long value ISBN
     */
    public long getISBN() {
        return ISBN;
    }

    /**
     *
     * @param ISBN is a long value
     */
    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     *
     * @return a String value title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title is a String value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return an integer value issueYear
     */
    public int getIssueYear() {
        return issueYear;
    }

    /**
     *
     * @param issueYear is an integer value
     */
    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    /**
     *
     * @return a String value authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     *
     * @param authorName is a String value
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     *
     * @return a double value price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price is a double value
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return an integer value numberOfPage
     */
    public int getNumberOfPage() {
        return numberOfPage;
    }

    /**
     *
     * @param numberOfPage is an integer value
     */
    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }
}
