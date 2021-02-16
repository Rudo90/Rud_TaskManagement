package models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private int id;
    private String name;
    private String surname;
    private String type;
    private String email;
    private String password;


}
