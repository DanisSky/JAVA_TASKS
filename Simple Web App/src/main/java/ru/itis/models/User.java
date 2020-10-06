package ru.itis.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Builder
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String pass;

}
