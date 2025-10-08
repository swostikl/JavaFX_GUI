//Task 2: Filtering and Transforming a List of Numbers
//Filter out all the even numbers from the list.
//Double each remaining number.
//Find the sum of the resulting numbers.

import java.util.Arrays;
import java.util.List;

public class FilterTransform {
    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(2,4,3,5,8,1);
        int result = numbers.stream().filter(n-> n % 2 != 0)// remove even num
                .map(n->n*2) // doubling remaining numbers
                .reduce(0, Integer::sum); // sum
        System.out.println(result);



    }
}
