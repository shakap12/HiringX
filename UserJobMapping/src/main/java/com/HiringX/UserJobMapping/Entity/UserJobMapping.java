package com.HiringX.UserJobMapping.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User Job Mapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJobMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long mappingId;

    @Column(name = "Job ID")
    private Long userJobId;
    @Column(name = "User ID")
    private Long userId;
}
