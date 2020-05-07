package com.feel.common.config;

/**
 * @Author: zz
 * @Description:
 * @Date: 2:35 AM 8/29/19
 * @Modified By
 */
public class ScoreStatus {

    /*排单-余额冻结中*/
    public static final int FREEZING_IN_ORDER_USABLE    = 1;

    /*排单-收益冻结中*/
    public static final int FREEZING_IN_ORDER_EARNING   = 2;

    /*提现-释放冻结中*/
    public static final int FREEZING_IN_MENTION_RELEASE = 3;

    /*提现-余额冻结中*/
    public static final int FREEZING_IN_MENTION_USABLE  = 4;

    /*排单-矿池收益冻结中*/
    public static final int FREEZING_IN_POOL            = 5;

    /*排单-推荐奖励冻结中*/
    public static final int FREEZING_IN_ORDER_RECOMMEND = 6;

    /*提现-收益冻结中*/
    public static final int FREEZING_IN_MENTION_EARNING = 7;

    /*拍单-AB区间收益冻结中*/
    public static final int FREEZING_IN_ORDER_AB        = 8;



    /*排单-余额冻结结束*/
    public static final int SUCC_IN_ORDER_USABLE        = 11;

    /*排单-收益成功*/
    public static final int SUCC_IN_ORDER_EARNING       = 12;

    /*提现-释放冻结成功*/
    public static final int SUCC_IN_MENTION_RELEASE     = 13;

    /*提现-成功*/
    public static final int SUCC_IN_MENTION_USABLE      = 14;

    /*排单-矿池收益成功*/
    public static final int SUCC_IN_POOL                = 15;

    /*排单-推荐奖励成功*/
    public static final int SUCC_IN_ORDER_RECOMMEND     = 16;

    /*提现-收益冻结结束*/
    public static final int SUCC_IN_MENTION_EARNING     = 17;

    /*AB区间-收益*/
    public static final int SUCC_IN_ORDER_AB            = 18;



    /*冲币-成功*/
    public static final int SUCC_IN_CHARGE_USABLE       = 20;

    /*拨币-成功*/
    public static final int SUCC_IN_UPDATE_USABLE       = 21;




}
