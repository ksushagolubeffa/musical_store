package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnonymousComment {
    private Long id;
    private String text;
    private Long productID;
}
