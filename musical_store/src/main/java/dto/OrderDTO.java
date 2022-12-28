package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDTO {
    private Long orderID;
    private String productName;
    private Integer productPrice;
}
