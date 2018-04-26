package ink.moming.installmentmemo.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author moming
 */

/**
 * 1.支付宝借呗
 *      1 等额本金 期限 n 日利率 t% 本金 a 借款月份 m 借款 c
 *         月本金 c/n
 * 2.支付宝花呗
 *      期限 n 月还款 a
 * 3.京东白条
 *      期限 n 月还款 a
 * 4.普通信用卡分期
 *      期限 n 月还款 a
 * 5.小米分期
 *      期限 n 月还款 a
 *
 * 提前还款的情况
 *  
 *
 */

public class InstallmentContract {

    public static final String CONTENT_AUTHORITY =
            "ink.moming.installmentmemo.data.InstallmentProvider";

    public static final Uri BASE_CONTENT_URI
            = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_INSTALLMENT= "installment";

    public static final class InstallmentEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_INSTALLMENT)
                .build();

        public static final String TABLE_NAME = "";
        //分期名称
        public static final String COLUMN_INS_NAME = "";
        //分期总数
        public static final String COLUMN_INS_TOTAL_COUNT = "";
        //已还期数
        public static final String COLUMN_INS_REPAYED_COUNT = "";
        //分期单价
        public static final String COLUMN_INS_REPAY_PRICE = "";
        public static final String COLUMN_INS_ACCOUNT = "";

    }
}
