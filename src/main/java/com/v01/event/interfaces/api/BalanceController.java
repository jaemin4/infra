package com.v01.event.interfaces.api;

import com.v01.event.application.BalanceFrontService;
import com.v01.event.interfaces.model.dto.res.ResCompleteChargeDto;
import com.v01.event.interfaces.model.param.CompleteChargeParam;
import com.v01.event.interfaces.model.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceFrontService balanceFrontService;

    @PostMapping(value = "/charge")
    public ResponseEntity<ApiResponse<ResCompleteChargeDto>> charge(CompleteChargeParam param) {
        ResCompleteChargeDto resDto = balanceFrontService.completeCharge(param);
        ApiResponse<ResCompleteChargeDto> response = new ApiResponse<>("충전이 완료되었습니다.", resDto);

        return ResponseEntity.ok(response);
    }


}
