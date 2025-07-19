package seu.bankmanagementsystem.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customers {
    private int id;
    private String username;
    private double balance;
}
