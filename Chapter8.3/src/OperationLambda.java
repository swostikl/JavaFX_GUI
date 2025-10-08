//Collection Operations with Lambdas
//Create a program that demonstrates basic operations on a collection of integers using lambda expressions without using
// the Stream API in Java.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationLambda {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 5, 8, 20, 15, 3, 12));
        System.out.println( "original List" + numbers);

        // Filter Even Numbers: Use lambda expressions to filter out even numbers from the list.
        numbers.removeIf((n-> n % 2 == 0));
        System.out.println( " After removing even numbers " + numbers);

        //Double the Odd Numbers: Use lambda expressions to double the value of odd numbers in the list.
        numbers.replaceAll( n-> n * 2);
        System.out.println(" After doubling odd numbers " + numbers);

        //Sum the Numbers: Use lambda expressions to calculate the sum of all numbers in the list.
        int [] sum ={0};  // mutable container(array)
        numbers.forEach(n-> sum [0] +=n);
        System.out.println(" Sum of numbers " + sum[0]);
    }


}
