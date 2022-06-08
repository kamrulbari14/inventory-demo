package com.inventory.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isActive;
}
