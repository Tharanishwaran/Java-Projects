import java.util.Scanner;

public class ReverseTheNumber {


    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number : ");

        int number = scan.nextInt();

        int reverse_of_the_number = 0;

        while (number>0){

            int remainder = number % 10;

            number /= 10;

            reverse_of_the_number = reverse_of_the_number * 10 + remainder;

        }


        System.out.println(reverse_of_the_number);

    }

}
