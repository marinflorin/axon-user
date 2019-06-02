package com.peaas.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileAggregate {

    @Id
    @AggregateIdentifier
    @Column(name = "id", length = 36)
    private String userId;
    private String name;
    private String surname;
    private String address;
    private String email;

    @CommandHandler
    public UserProfileAggregate(CreateUserCommand command, MetaData metaData) {
        apply(new UserCreatedEvent(command.getUserId(), command.getName(), command.getSurname(), command.getAddress(), command.getEmail()));
    }

    @EventHandler
    public void handle(UserCreatedEvent event, MetaData metaData, @Timestamp Instant eventTimestamp) {
        this.userId = event.getUserId();
        this.name = event.getName();
        this.surname = event.getSurname();
        this.address = event.getAddress();
        this.email = event.getEmail();
    }
}
