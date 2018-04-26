package ink.moming.installmentmemo.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author moming
 */

public class BillAccountContract {
    public static final String CONTENT_AUTHORITY =
            "ink.moming.installmentmemo.data.BillAccountProvider";

    public static final Uri BASE_CONTENT_URI
            = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_BILL_ACCOUNT= "bill_account";

    public static final class BillAccountEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_BILL_ACCOUNT)
                .build();

        public static final String TABLE_NAME = PATH_BILL_ACCOUNT;

        //分期账户名称
        public static final String COLUMN_BA_NAME="";
        //分期账户额度
        public static final String COLUMN_BA_LIMIT="";
        //分期账户已使用额度
        public static final String COLUMN_BA_USED="";
    }
}
