package com.HiringX.UserService.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJobMapping {

    private Long mappingId;
    private Long userJobId;
    private Long userId;
}
