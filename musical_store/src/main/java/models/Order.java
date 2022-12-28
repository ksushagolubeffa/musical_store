package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private Long id;
    private Long userID;
    private Long productID;
    private boolean isExecuted;
}
