package com.HiringX.JobsService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "JOB_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    @NotNull(message = "This field cannot be empty")
    @NotBlank(message = "Title cannot be blank")
    @Length(min=1,max=20,message = "Enter valid title")
    private String jobTitle;

    @NotNull(message = "This field cannot be null")
    @Length(min=1,max=15,message = "Enter valid skill")
    private String jobRequiredSkill1;

    @NotNull(message = "This field cannot be null")
    @Length(min=1,max=15,message = "Enter valid skill")
    private String jobRequiredSkill2;

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    @Length(min=1,max=20,message = "Enter a valid company name")
    private String jobCompany;

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    @Length(min=1,max = 15,message = "Enter a valid location")
    private String jobLocation;
}
