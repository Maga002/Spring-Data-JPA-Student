package com.selflearn.SpringJPA;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "book")
@Entity(name = "Book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_id_sequence", sequenceName = "book_id_sequence")
    @GeneratedValue(generator = "book_id_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
    private String bookName;
    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createAt;

    public Book(String bookName, LocalDateTime createAt) {
        this.bookName = bookName;
        this.createAt = createAt;
    }

    public Book() {
    }

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "student_book_fk")
     )
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    public void  setStudent(Student student){
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", createAt=" + createAt +
                ", student=" + student +
                '}';
    }
}
