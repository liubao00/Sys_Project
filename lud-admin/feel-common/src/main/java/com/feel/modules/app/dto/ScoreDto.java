package com.feel.modules.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zz
 * @Description:
 * @Date: 12:25 AM 9/16/19
 * @Modified By
 */
@Data
@NoArgsConstructor
public class ScoreDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "userId不能为空")
    @NotBlank
    private Integer userId;

    @NotBlank
    @NotNull(message = "score不能为空")
    private BigDecimal score;

    @NotBlank
    @NotNull(message = "type不能为空")
    private Integer scoreType;

}
