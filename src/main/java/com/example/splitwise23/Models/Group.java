package com.example.splitwise23.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "groups_table")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseModel {
    private String groupName;
    @ManyToOne
    private User adminUser;
    @ManyToMany
    private List<User> groupMembers;
    @OneToMany(mappedBy = "group")
    private List<Expense> expenseList;
}
