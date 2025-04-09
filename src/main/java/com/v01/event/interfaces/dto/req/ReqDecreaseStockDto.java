package com.v01.event.interfaces.dto.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqDecreaseStockDto {

    private Long productId;
    private Long quantity;



}
