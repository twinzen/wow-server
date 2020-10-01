package com.wow.server.dto;

import lombok.Data;

@Data
public class WatchItemDTO {
    private Long watchItemId;
    private Long productId;
    private Long userId;
    private ProductDTO product;
}
