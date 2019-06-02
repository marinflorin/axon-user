package com.peaas.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserCommand implements Serializable {

    @TargetAggregateIdentifier
    @NotNull
    @Size(min = 1, max = 36)
    private String userId;
    private String name;
    private String surname;
    private String address;
    private String email;
}
