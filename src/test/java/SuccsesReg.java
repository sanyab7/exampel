import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SuccsesReg {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
