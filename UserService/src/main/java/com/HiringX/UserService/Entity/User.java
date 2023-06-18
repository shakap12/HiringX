package com.HiringX.UserService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User ID")
    private Long userId;

    @Column(name = "User Name")
    @NotNull(message = "User Name should not be null")
    @NotBlank(message = "User Name should not be blank")
    private String userName;

    @Column(name = "User Email")
    @Email(message = "Enter correct email ID")
    private String userEmail;


    @Column(name = "User's Skill 1")
    @NotNull(message = "This field cannot be empty")
    private String skill1;

    @Column(name = "User's Skill 2")
    @NotNull(message = "This field cannot be empty")
    private String skill2;

    @Transient
    private List<UserJobMapping> appliedJobs=new ArrayList<>();
}

