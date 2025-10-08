//Task 1: Sorting and Filtering using Lambda
//Create a program that sorts and filters a list of objects using lambda expressions and the Comparator interface.

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int age;
    String city;

    public Person(String name, int age, String city){
        this.name = name;
        this.age = age;
        this.city = city;
    }
    @Override
    public String toString() {
        return name + " (" + age + "), " + city;
    }
}

class LambdaSortFilterDemo {
    public static void main(String[] args) {
        //1. Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25, "New York"));
        people.add(new Person("Bob", 30, "Chicago"));
        people.add(new Person("Charlie", 22, "New York"));
        people.add(new Person("David", 28, "Boston"));
        people.add(new Person("Eve", 35, "Chicago"));

        System.out.println("Original list:");
        people.forEach(System.out::println);

        // 2. Sort by age using lambda
        people.sort((p1, p2) -> Integer.compare(p1.age, p2.age));

        System.out.println("\nSorted by age:");
        people.forEach(System.out::println);

        // 3. Filter by city using removeIf()
        String targetCity = "New York";
        people.removeIf(p -> !p.city.equalsIgnoreCase(targetCity));

        System.out.println("\nAfter filtering (only from " + targetCity + "):");
        people.forEach(System.out::println);
    }
}
