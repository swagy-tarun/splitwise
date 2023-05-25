package com.demo.splitwise.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "name")
@Entity
public class AppUser {
    private @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_SEQ")
    @SequenceGenerator(
            name = "APP_USER_SEQ",
            allocationSize = 1
    )
    Long id;
    private String name;
}
