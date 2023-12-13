package com.example.splitwise23.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Objects;

@Entity(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    private String PhoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
