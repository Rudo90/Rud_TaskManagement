package models;


import lombok.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Task {

    private int id;
    private String title;
    private String description;
    private TaskStatusEnum status;
    private Date deadline;
    private String email;
}
