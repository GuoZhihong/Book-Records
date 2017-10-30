// -----------------------------------------------------
// Assignment #3
// Part: (2)
// Written by: Zhihong Guo(40038183),Tiantian Ji(27781083)
// -----------------------------------------------------

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.Serializable;

/**
 * @author Zhihong Guo,Tiantian Ji
 * @version 1.8
 */
public class BookInventory2 implements Serializable {

    /**
     *private static attributes
     */
    private static int n = 0;
    private static Book bkArr[];
    private static long lastISBN = 0;

    /**
     *
     * @param sc is a Scanner value
     * @return an integer value i
     * @throws IOException
     * private method to get how many numbers there are in the record
     */
    private static int getRecordNum(Scanner sc)throws IOException{
        int i = 0;
        while(sc.hasNextLine()){
            sc.nextLine();
            i++;
        }
        return i;
    }

    /**
     *
     * @param sc is a Scanner value
     * @return a long value ISBN
     * @throws FileNotFoundException
     * private method to get last ISBN
     */
    private static long getLastISBN(Scanner sc)throws FileNotFoundException{
        long ISBN = 0;
        while(sc.hasNextLine()) {
            ISBN = sc.nextLong();
            sc.nextLine();
        }
        return ISBN;
    }

    /**
     *
     * @param outFileStreamName is a PrintWriter value
     * @throws FileNotFoundException
     */
    public static void addRecords(PrintWriter outFileStreamName)throws FileNotFoundException{
        Scanner kb = new Scanner(System.in);
        System.out.print("We need to store your new book's information:");
        String y;

        do{
            System.out.print("\nPlease enter (long)ISBN: ");
            long ISBN = kb.nextLong();

            while(ISBN <= lastISBN){
                System.out.print("\nThe ISBN#  is smaller than the previous records in the file.Please enter another (long)ISBN: ");
                ISBN = kb.nextLong();
            }//if the ISBN is smaller than the last one, ask again till proper

            outFileStreamName.print(ISBN + " ");	// Write this info to the file
            lastISBN = ISBN;//assign the newly input ISBN as the last one, since it's the largest number

            System.out.print("\nPlease enter your (String)title: ");
            String title = kb.next();
            outFileStreamName.print(title + " ");	// Write this info to the file

            System.out.print("\nPlease enter your (int)issueYear: ");
            int issueYear = kb.nextInt();
            outFileStreamName.print(issueYear + " ");	// Write this info to the file

            System.out.print("\nPlease enter your (String)author: ");
            String author = kb.next();
            outFileStreamName.print(author + " ");	// Write this info to the file

            System.out.print("\nPlease enter your (double)price: ");
            double price = kb.nextDouble();
            outFileStreamName.print(price + " ");	// Write this info to the file

            System.out.print("\nPlease enter your (int)pages: ");
            int pages = kb.nextInt();
            outFileStreamName.println(pages);	// Write this info to the file

            System.out.print("\nNext information? Please enter Y/N: ");
            y = kb.next();
        }while(y.equalsIgnoreCase("y"));//keep adding if user input y, ignoring the case
        outFileStreamName.close();//close the file
    }

    /**
     *
     * @param inFileStreamName is a Scanner value
     * @throws FileNotFoundException
     * private static method to read book information and store it in the array
     */
    private static void bookArr(Scanner inFileStreamName)throws FileNotFoundException {
        Scanner sc = inFileStreamName;
        long ISBN ;
        String title;
        int issueYear;
        String author;
        double price;
        int pages;
        int i = 0;
        while(sc.hasNextLine()) {
            ISBN = sc.nextLong();
            title = sc.next();
            issueYear = sc.nextInt();
            author = sc.next();
            price = sc.nextDouble();
            pages = sc.nextInt();
            bkArr[i]= new Book(ISBN,title,issueYear,author,price,pages);
            sc.nextLine();
            i++;
        } //keep reading till there's no next line
        sc.close();
    }

    /**
     *
     * @param inFileStreamName is a BufferedReader value
     * @throws IOException
     * method to display the file
     */
    public static void displayFileContents(BufferedReader inFileStreamName) throws IOException{
        int x;
        x = inFileStreamName.read();
        while(x != -1) //x will be -1 when there's no next line
        {
            System.out.print((char)x);	//convert int to char
            x = inFileStreamName.read();//keep reading
        }
        inFileStreamName.close();
    }

    /**
     *
     * @param bkArr is an array of books
     * @param startIndex is an integer value
     * @param endIndex is an integer value
     * @param searchISBN is a long value
     * @return an integer value
     */
    public  static int binaryBookSearch( Book[] bkArr,int startIndex,int endIndex,long searchISBN){
        int i=1 ;
        int low = startIndex - 1;
        int high= endIndex - 1;

        if(low > high){
            throw new IllegalArgumentException("The index of array is illegal, startIndex > endIndex:");//startIndex > end, illegal
        }
        while(low <= high){
            int middle = (low+high)/2;//compare with the middle
            if(searchISBN == bkArr[middle].getISBN()){
                return i;
            }
            if(searchISBN > bkArr[middle].getISBN()){
                low = middle + 1  ;
                i++;
            }//take the second half and compare
            if(searchISBN < bkArr[middle].getISBN()){
                high = middle - 1 ;
                i++;
            }//take the first half and compare
        }
        return -1 ;
    }

    /**
     *
     * @param bkArr is an array of books
     * @param startIndex is an integer value
     * @param endIndex is an integer value
     * @param searchISBN is a long value
     * @return an integer value
     * method to do sequential book search
     */
    public  static int sequentialBookSearch( Book[] bkArr,int startIndex,int endIndex,long searchISBN){
        int i=1 ;
        int low = startIndex;
        int high= endIndex;
        if(low > high){
            throw new IllegalArgumentException("The index of array is illegal, startIndex > endIndex:");
        }
        for(int j=low -1 ; j <= high - 1; j++){
            if(searchISBN == bkArr[j].getISBN()){
                return i;
            }
            i++;
        }
        return -1 ;

    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner kb = new Scanner(System.in);
        Scanner sc;
        String s = null;
        String s1 = "Sorted_Book_Info.txt";
        PrintWriter pw = null;
        BufferedReader br = null;

        /*
        get ISBN and assign it to lastISBN
         */
        try
        {
            sc = new Scanner(new FileInputStream(s1));
            lastISBN = getLastISBN(sc);
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files. Cannot proceed to fix.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }

        /*
        append books
         */
        try
        {
            pw=new PrintWriter(new FileOutputStream(s1,true));
            addRecords(pw);//calling addRecords() method to append info
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files. Cannot proceed to fix.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        pw.close();

        /*
         displayFile
         */
        System.out.println("\nHere are the contents of file " + s1 + ":");
        System.out.println("======================================================");

        try
        {
            br = new BufferedReader(new FileReader(s1));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files. Cannot proceed to displayFileContents.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        try
        {
            displayFileContents(br);
        }
        catch(IOException e)
        {
            System.out.println("Error: An error has occurred while reading from the " + s + " file. ");
            System.out.println("Program will terminate.");
            System.exit(0);
        }

        /*
        input file to the array
         */
        try
        {
            sc = new Scanner(new FileInputStream(s1));
            n=getRecordNum(sc);
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files. Cannot proceed to fix.");
            System.out.println("Program :getRecordNum(sc) will terminate.");
            System.exit(0);
        }
        bkArr= new Book[n];
        try
        {
            sc = new Scanner(new FileInputStream(s1));
            bookArr(sc);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files. Cannot proceed to fix.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }

        /*
        Search for the ISBN through binary Book Search
         */
        System.out.println("Search the ISBN");
        System.out.print("please input the (int)startIndex which is greater than or equal to 1:");
        int startIndex =kb.nextInt();
        System.out.print("please input the  (int)endIndex which is less or equal than "+ bkArr.length +":");
        int endIndex = kb.nextInt();
        System.out.print("please input the (long) searchISBN:");
        long searchISBN = kb.nextLong();
        try
        {
            int i = binaryBookSearch( bkArr,startIndex,endIndex,searchISBN);
            if (i > 0){
                System.out.println("The ISBN is found by " + i + " iterations ");
            }
            if (i == -1)
                System.out.println("Sorry, The ISBN doesn't exist.");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println( e );
        }

        /*
        do sequential book search
         */
        System.out.println("Search the ISBN");
        System.out.print("please input the (int)startIndex, which should be greater than or equal to 1:");
        int startIndex1 =kb.nextInt();
        System.out.print("please input the  (int)endIndex, which is less than or equal to: "+n);
        int endIndex1 = kb.nextInt();
        System.out.print("please input the (long) searchISBN:");
        long searchISBN1 = kb.nextLong();
        try
        {
            int i = sequentialBookSearch( bkArr,startIndex1,endIndex1,searchISBN1);
            if (i > 0){
                System.out.println("The ISBN is found by " + i + " iterations and it's at the "+i+"th position ");
            }
            if (i == -1)
                System.out.println("Sorry, The ISBN doesn't exist.");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println( e );
        }

        for(int i =0;i < n;i++){
            System.out.println(bkArr[i]);
        }

        /*
        write to the binary file
         */
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Books.dat")) ;// writing a binary file
            for(int i =0;i < n;i++){
                oos.writeUTF(bkArr[i].toString());
            }
            oos.close();
            System.out.println("Objects have been written to the file : Books.dat.");
        }
        catch(IOException e)
        {
            System.out.println("Error: Problem Reading from file: Books.dat.");
            System.out.println("Program writeObject will terminate.");
            System.exit(0);
        }
    }
}