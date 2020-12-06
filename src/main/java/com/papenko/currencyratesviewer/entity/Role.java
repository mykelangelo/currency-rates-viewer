package com.papenko.currencyratesviewer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {
    private static final long serialVersionUID = 3586904337840005142L;
    @Id
    @NotBlank
    @Column(unique = true)
    String name;

    @NotNull
    Boolean privileged;
}
