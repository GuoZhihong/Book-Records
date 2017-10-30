// -----------------------------------------------------
// Assignment #3
// Part: (1)
// Written by: Zhihong Guo(40038183),Tiantian Ji(27781083)
// -----------------------------------------------------

import java.io.*;
import java.util.Scanner;

/**
 * @author Zhihong Guo,Tiantian Ji
 * @version 1.8
 */
public class BookInventory1 {

    /**
     *
     * @param sn is Scanner value
     * @return a integer value arraySize
     * method to determine the size of array
     */
    private static int arraySize(Scanner sn) {
        int arraySize = 0;
        while (sn.hasNextLine()) {//condition to stop the loop and find how many books should be in the array.
            sn.nextLine();
            arraySize++;
        }
        return arraySize;
    }

    /**
     *private static attributes
     **/
    private static Book[] bkArr;

    /**
     *
     * @param newISBN is a long value
     * @throws DuplicateISBNException
     * private method to find duplicate entry.
     */
    private static void duplicateEntry(long newISBN) throws DuplicateISBNException {
        for (int i = 0; i < bkArr.length; i++) {
            if (bkArr[i].getISBN() == newISBN) {
                throw new DuplicateISBNException("Initial appearance of ISBN " + newISBN + " was found at record #: " + (i + 1));
            }
        }
    }

    /**
     *
     * @param inputStream is a Scanner value
     * @param outputStream is a PrintWriter value
     * @throws IOException
     * method to fix inventory in an array
     */
    public static void fixInventory(Scanner inputStream, PrintWriter outputStream) throws IOException {
        Scanner inputISBN = new Scanner(System.in);
        long newISBN = 0;
        if (inputStream.hasNextLine() || bkArr.length >= 1) {//file is not empty or has only one book.
            for (int i = 0; i < bkArr.length; i++) {//use a for-loop to put books into array.
                Book book = new Book();// create a book
                book.setISBN(inputStream.nextLong());
                book.setTitle(inputStream.next());
                book.setIssueYear(inputStream.nextInt());
                book.setAuthorName(inputStream.next());
                book.setPrice(inputStream.nextDouble());
                book.setNumberOfPage(inputStream.nextInt());
                bkArr[i] = book;// put this book into array
                if (inputStream.hasNext())
                    inputStream.nextLine();//take in the next book
            }
            System.out.println("The file has " + bkArr.length + " Records");

            /*
            This part uses a nested loop to check duplicate ISBN then assign an another irrelevant ISBN into the array of books.
             */
            for (int i = 0; i < bkArr.length; i++) {
                for (int j = i + 1; j < bkArr.length; j++) {
                    if (bkArr[i].getISBN() == bkArr[j].getISBN()) {
                        System.out.print("Duplicate ISBN " + bkArr[i].getISBN() + " detected in record ## " + (j + 1)+".");
                        System.out.print(" Please enter the correct ISBN: ");
                        newISBN = inputISBN.nextLong();
                        boolean done = false;
                        while (!done) {// this part indicates if user prompts a existed ISBN, if it does,then redo it until it is unique.
                            if(inputISBN.hasNextLine()) {
                                try {
                                    duplicateEntry(newISBN);
                                } catch (DuplicateISBNException e) {
                                    System.out.println(e.getMessage());
                                    System.out.print("Duplicate ISBN " + bkArr[i].getISBN() + " detected in record ## " + (j + 1)+".");
                                    System.out.print(" Please enter the correct ISBN: ");
                                    newISBN = inputISBN.nextLong();
                                    continue;//break for this
                                }
                                done = true;// break the loop if there is no duplicate entry.
                            }
                        }
                        bkArr[j].setISBN(newISBN);//assign the new and corrected ISBN into the array of books.
                    }
                }
            }

            for (int i = 0; i < bkArr.length; i++) {//output the corrected file
                outputStream.print(bkArr[i].getISBN()+" ");
                outputStream.print(bkArr[i].getTitle()+" ");
                outputStream.print(bkArr[i].getIssueYear()+" ");
                outputStream.print(bkArr[i].getAuthorName()+" ");
                outputStream.print(bkArr[i].getPrice()+" ");
                outputStream.print(bkArr[i].getNumberOfPage()+" ");
                outputStream.println();
            }
        } else {//scenario of this file has no books or just one.
            System.out.println("This file only has no books or one,nothing needs to be fixed");
            inputStream.close();
            outputStream.close();
            System.exit(0);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     *
     * @param inFileStreamName is a BufferedReader
     * @throws IOException
     * method to display the file
     */
    public static void displayFileContents(BufferedReader inFileStreamName) throws IOException {
        int x;
        System.out.println("====================================================================");
        x = inFileStreamName.read();
        while (x != -1) {
            System.out.print((char) x);
            x = inFileStreamName.read();
        }
        inFileStreamName.close();
    }

    /**
     *
     * @param args
     * main method
     */
    public static void main(String[] args) {
        String s;
        PrintWriter pw = null;
        Scanner userInput = new Scanner(System.in);
        Scanner sn = null;
        File f = null;
        System.out.print("Please enter the name of output file,which will have correct information :");
        s = userInput.next();
        f = new File(s);

        /*
        Enter existing file name and keep asking for reenter from the user
         */
        while (f.exists()) {
            System.out.println("Error: There is an existing file called " + s);
            System.out.println("That file already has a size of " + f.length() + "bytes.");
            System.out.print("Please enter another file name to create :");
            s = userInput.next();
            f = new File(s);
        }

        /*
        proceed the correction.
         */
        try {
            sn = new Scanner(new FileInputStream("Initial_Book_Info.txt"));
            pw = new PrintWriter(new FileOutputStream(s));
            bkArr = new Book[arraySize(sn)];
            sn.close();// close last file search in arraySize(sn) method.
            sn = new Scanner(new FileInputStream("Initial_Book_Info.txt"));//restart to read the file.
            fixInventory(sn, pw);
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening files.Cannot proceed");
            System.out.println("Program will terminate.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error: An error has occurred while reading from the " + s + " file. ");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        /*
        display old file and corrected file
         */
        BufferedReader cr = null;
        BufferedReader cr2 = null;
        try {
            cr2 = new BufferedReader(new FileReader("Initial_Book_Info.txt"));
            cr = new BufferedReader(new FileReader(s));
            System.out.println();
            System.out.println("Here are the contents of file Initial_Book_Info.txt AFTER copying operation:");
            displayFileContents(cr2);
            System.out.println();
            System.out.println();
            System.out.println("Here are the contents of file " + s + " :");
            displayFileContents(cr);

        } catch (FileNotFoundException e1) {
            System.out.println("Problem opening files.Cannot proceed");
            System.out.println("Program will terminate.");
            System.exit(0);
        } catch (IOException e1) {
            System.out.println("Error: An error has occurred while reading from the " + s + " file. ");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
    }

}
