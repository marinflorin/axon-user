package com.peaas.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class UserRequest {

    private String name;
    private String surname;
    private String address;
    private String email;
}