package com.contactmanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_numbers", uniqueConstraints = {
    @UniqueConstraint(columnNames = "number")
})
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Contact contact;
}
