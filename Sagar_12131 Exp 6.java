/**
Question 1: Write a program to sort a list of Employee objects (name, age, salary) using lambda expressions.
*/
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", salary=" + salary + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter the number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Enter name of employee " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter age of employee " + (i + 1) + ": ");
            int age = scanner.nextInt();
            System.out.print("Enter salary of employee " + (i + 1) + ": ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            employees.add(new Employee(name, age, salary));
        }

        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("Sorted by name: " + employees);

        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("Sorted by age: " + employees);

        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));
        System.out.println("Sorted by salary: " + employees);
    }
}

/**
Question 2:Create a program to use lambda expressions and stream operations to filter students scoring above 75%, sort them by marks, and display their names.
*/
import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter marks of student " + (i + 1) + ": ");
            int marks = scanner.nextInt();
            scanner.nextLine();

            students.add(new Student(name, marks));
        }

        List<String> filteredSortedNames = students.stream()
            .filter(student -> student.getMarks() > 75)
            .sorted(Comparator.comparingInt(Student::getMarks))
            .map(Student::getName)
            .collect(Collectors.toList());

        System.out.println("Students scoring above 75%, sorted by marks:");
        filteredSortedNames.forEach(System.out::println);
    }
}


/**
Question 3:Write a Java program to process a large dataset of products using streams. Perform operations such as grouping products by category,
finding the most expensive product in each category, and calculating the average price of all products
*/

import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
    public String toString() {
        return name + " (" + category + "): $" + price;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numProducts; i++) {
            System.out.print("Enter name of product " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter category of product " + (i + 1) + ": ");
            String category = scanner.nextLine();
            System.out.print("Enter price of product " + (i + 1) + ": ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            products.add(new Product(name, category, price));
        }

        Map<String, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));

        double averagePrice = products.stream()
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);

        System.out.println("Products by category:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        System.out.println("Most expensive product in each category:");
        mostExpensiveByCategory.forEach((category, product) -> {
            product.ifPresent(p -> System.out.println(category + ": " + p));
        });

        System.out.println("Average price of all products: $" + averagePrice);
    }
}



