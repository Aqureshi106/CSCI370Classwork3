import java.util.*;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 85),
                new Student("Bob", 90),
                new Student("Charlie", 78),
                new Student("David", 92),
                new Student("Eve", 88)
        );

        // using a loop, we can calculate the mean and standard deviation
        double mean = 
            students.stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0);

        // using the mean, we can calculate the standard deviation
        double standardDeviation = Math.sqrt(students.stream()
            .mapToDouble(s -> Math.pow(s.getGrade() - mean,2))               .average()
            .orElse(0));

        // now we can define a function that applies the curve to a grade
        double curve = 0.05 * standardDeviation;
        List<Student> studentsCurved = students.stream()
            .map(s -> new Student(
                s.getName(),
                s.getGrade() + curve))
            .collect(Collectors.toList());

        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + standardDeviation);
        studentsCurved.forEach(System.out::println);
    }
}
