import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class usersgorest {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

}