import java.util.*;

public class PartA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by space:");
        String input = sc.nextLine();

        String[] tokens = input.split(" ");
        for (String t : tokens) {
          // Parsing String → int → Autobox to Integer
            Integer num = Integer.parseInt(t);
            numbers.add(num);
        }

        int sum = 0;
        // Unboxing happens automatically here
        for (int n : numbers) {
            sum += n;
        }

        System.out.println("Sum of integers: " + sum);
        sc.close();
    }
}
