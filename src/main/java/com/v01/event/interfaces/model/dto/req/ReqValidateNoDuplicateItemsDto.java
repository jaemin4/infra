package com.v01.event.interfaces.model.dto.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqValidateNoDuplicateItemsDto {
    private Long productId;
    private Long quantity;
}
