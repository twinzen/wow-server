package com.wow.server.watchitem;

import com.wow.server.dto.ProductDTO;
import lombok.Data;

@Data
public class WatchItemDTO {
    private Long watchItemId;
    private Long productId;
    private Long userId;
    private ProductDTO product;
}
