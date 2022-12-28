package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private String role;
    private Integer balance;
}
