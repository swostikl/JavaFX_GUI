//Task 1: Calculate mean of an array in functional way (e.g. no for-loops)

import java.util.Arrays;

public class MeanArray {
    public static void main(String[] args){
        int[] arr = {1,3,2,9,8,10};
        double means = Arrays.stream(arr).average().orElse(0);
        System.out.println(means);

    }

}
