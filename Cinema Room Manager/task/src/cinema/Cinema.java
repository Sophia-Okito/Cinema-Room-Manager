package cinema;
import java.util.Scanner;
import java.io.*;

public class Cinema {
    
    public static int halfRow;
    public static int row;
    public static int rows;
    public static int seatPerRow;
    public static int seatNumber;
    public static int totalSeat;
    final static Scanner sc = new Scanner(System.in);
    public static String[][] theatre;
    public static int numOfPurchasedTicket;
    public static int currentIncome;
    
    public Cinema(int rows, int seatPerRow) {
        Cinema.rows = rows;
        Cinema.seatPerRow = seatPerRow;
        theatre = new String[rows][seatPerRow];
        createMultiArray();
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatPerRow = sc.nextInt();
        
        halfRow = rows / 2;
        totalSeat = rows * seatPerRow;
        Cinema cin = new Cinema(rows, seatPerRow);
        
        numOfPurchasedTicket = 0;
        currentIncome = 0;
        
        while (true) {
            System.out.println();
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");

            int action = sc.nextInt();

            System.out.println();


            switch (action) {
                case 1:
                    System.out.println("Cinema:");
                    cin.drawTheatre();
                    break;
                case 2:
                    while (true) {

                        System.out.println("Enter a row number:");
                        row = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = sc.nextInt();

                        var boo = cin.updateTheatre(row, seatNumber);
                        if (boo) {
                            System.out.println("Ticket price: $" + ticketPrice(row));
                            numOfPurchasedTicket += 1;
                            currentIncome = currentIncome + ticketPrice(row);
                            break;
                        } else {
                            continue;
                        }
                    }
                break;
                case 3:
                    System.out.println();
                    cin.printStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option");
                    break;

            }
        }
    }
        
    
    
   
    public static int ticketPrice(int a) {
        int ticket;
        if (totalSeat > 59) {
            ticket = a > halfRow ? 8 : 10;
        } else {
            ticket = 10;
        }
        return ticket;
    }
    
    public void createMultiArray() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatPerRow; j++) {
                theatre[i][j] = "S";
            }
        }
    }
    
    public void drawTheatre() {
        System.out.print("  ");
        for (int i = 0; i < seatPerRow; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatPerRow; j++) {
                System.out.print(theatre[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean updateTheatre(int x, int y) {
        if (row > rows || seatNumber > seatPerRow) {
            System.out.println("Wrong input!");
            return false;
        }
        if ("B".equals(theatre[row - 1][seatNumber - 1])) {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        
        theatre[x - 1][y - 1] = "B";
        return true;
    }
    
    
    
        
    
    public void printStatistics(){
        int profit;
        if (totalSeat > 59) {
            profit = rows % 2 == 0 ? (halfRow * seatPerRow * 10) + (halfRow * seatPerRow * 8) : (halfRow * seatPerRow * 10) + ((halfRow + 1) * seatPerRow * 8);
        } else {
            profit = totalSeat * 10;
        }
        double percentage = numOfPurchasedTicket / totalSeat * 100.0;
        System.out.println("Number of purchased tickets:" + numOfPurchasedTicket);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" +  profit);
    }
    
}