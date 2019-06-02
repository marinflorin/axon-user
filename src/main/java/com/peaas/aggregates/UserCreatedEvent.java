package com.peaas.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreatedEvent {

    private String userId;
    private String name;
    private String surname;
    private String address;
    private String email;
}
