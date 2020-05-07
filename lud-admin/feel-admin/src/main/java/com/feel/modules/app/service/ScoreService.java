package com.feel.modules.app.service;

import com.feel.modules.app.entity.FUserScoreEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zz
 * @Description:
 * @Date: 8:01 PM 8/25/19
 * @Modified By
 */
public interface ScoreService {

    Boolean updateScore(FUserScoreEntity userScore, BigDecimal operateScore, int operate_type, int scoreType, int status, String remark);

    Boolean updateScore(FUserScoreEntity userScore, BigDecimal operateScore, int operate_type, int scoreType, int status, String remark , Date backTime);

    Boolean updateScore(FUserScoreEntity userScore, BigDecimal operateScore, int operate_type, int scoreType, int status, String remark , Date backTime , Integer productId , Integer orderId);

}
