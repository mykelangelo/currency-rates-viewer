package com.papenko.currencyratesviewer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    private static final long serialVersionUID = 872799842354444029L;
    @Id
    @NotBlank
    @Column(unique = true)
    @Size(min = 6, max = 319) //i@i.ua is the shortest possible email, and there can't be 320 symbols in a valid email
    String email;

    @NotBlank
    @Size(min = 59, max = 60)
    @ToString.Exclude
    String hash;

    @Valid
    @ManyToOne
    @JoinColumn(name = "role_name")
    Role role;
}
