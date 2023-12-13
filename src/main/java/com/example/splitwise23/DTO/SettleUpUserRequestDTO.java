package com.example.splitwise23.DTO;

import com.example.splitwise23.Models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SettleUpUserRequestDTO {
    private Long userId;
}
