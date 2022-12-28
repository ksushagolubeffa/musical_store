package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private byte[] image;
    private String imageName;
    private Integer price;
}
