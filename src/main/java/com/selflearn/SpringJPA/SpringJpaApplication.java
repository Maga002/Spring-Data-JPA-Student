package com.selflearn.SpringJPA;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

//    private static void generateRandomStudent(StudentRepository studentRepository) {
//        Faker faker = new Faker();
//        for (int i = 0; i < 20; i++) {
//            String firstName = faker.name().firstName();
//            String lastName = faker.name().lastName();
//            String email = String.format("%s.%s@gmail.com", firstName, lastName).toLowerCase();
//            Student student = new Student(
//                    firstName, lastName, email, faker.number().numberBetween(17, 40)
//            );
//            studentRepository.save(student);
//        }
//    }

//    private static void paginationStudent(StudentRepository studentRepository) {
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        Page<Student> page = studentRepository.findAll(pageRequest);
//        System.out.println(page);
//    }
//
//    private static void sortingStudent(StudentRepository studentRepository) {
//        studentRepository.findAll(Sort.by("firstName").descending().and(Sort.by("age").ascending()))
//                .forEach(student ->
//                        System.out.println(student.getFirstName() + " " + student.getAge()));
//    }

    @Bean
    CommandLineRunner commandLineRunner(StudentIdCardRepository studentIdCardRepository, StudentRepository studentRepository) {
        return args -> {
//            sortingStudent(studentRepository);        // Sorting SQL Table
//            paginationStudent(studentRepository);    // Pagination the data with 5 entities for each page
//            generateRandomStudent(studentRepository);  // Generating Random Student to table


            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Integer age = faker.number().numberBetween(17, 40);
            Student student = new Student(firstName, lastName, email, age);
            Long fakeCard = faker.number().randomNumber(15, false);

            student.addBook(new Book("Call of the Wild", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("Think and grow rich", LocalDateTime.now().minusDays(10)));
            student.addBook(new Book("Clean code", LocalDateTime.now().minusDays(20)));
            StudentIdCard studentIdCard = new StudentIdCard(fakeCard.toString(), student);

            student.setStudentIdCard(studentIdCard);

            student.addEnrolment(new Enrolment(new EnrolmentId(1L, 1L), student, new Course("Spring Data JPA", "Java"), LocalDateTime.now().minusDays(18)));
            student.addEnrolment(new Enrolment(new EnrolmentId(1L, 2L), student, new Course("ASP .NET Core", "C#"), LocalDateTime.now().minusDays(30)));
//
//
//            student.enrolToCourse(new Course("Spring Data JPA","Java"));
//            student.enrolToCourse(new Course("ASP .NET CORE","C#"));

            studentRepository.save(student);

            studentRepository.findById(1L)
                    .ifPresentOrElse(s -> {
                        System.out.println("fetch books lazy...");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> System.out.println(s.getFirstName() + " borrowed " + book.getBookName()));
                    }, () -> System.out.println("Error happen!"));
        };
    }
}
