package com.selflearn.SpringJPA;

import jakarta.persistence.*;

@Table(name = "student_id_card", uniqueConstraints = @UniqueConstraint(name = "card_number_unique", columnNames = "card_number"))
@Entity(name = "StudentIdCard")
public class StudentIdCard {
    @Id
    @SequenceGenerator(name = "student_card_id", sequenceName = "student_card_id")
    @GeneratedValue(generator = "student_card_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "card_number", nullable = false, columnDefinition = "TEXT",length = 15)
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_card_student_id_fk "))
    private Student student;

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
