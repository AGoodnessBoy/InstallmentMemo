package ink.moming.installmentmemo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ink.moming.installmentmemo.data.InstallmentContract.InstallmentEntry;
import ink.moming.installmentmemo.data.InstallmentContract.BillAccountEntry;

public class InstallmentDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "table_ins.db";

    private static final int DATABASE_VERSION = 1;

    public InstallmentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //table ba
        final String SQL_CREAT_BA_TABLE =
                "CREATE TABLE IF NOT EXISTS " + BillAccountEntry.TABLE_NAME + " ("+
                        BillAccountEntry._ID+
                        " INTEGER PRIMARY KEY AUTOINCREMENT , "+
                        BillAccountEntry.COLUMN_BA_NAME+
                        " TEXT NOT NULL , "+
                        BillAccountEntry.COLUMN_BA_LIMIT+
                        " REAL NOT NULL , "+
                        BillAccountEntry.COLUMN_BA_USED+
                        " REAL NOT NULL , "+
                        BillAccountEntry.COLUMN_BA_REPAYMENT_DATE+
                        " TEXT NOT NULL , "+
                        BillAccountEntry.COLUMN_BA_REMARK+
                        " TEXT NOT NULL , "+
                        " UNIQUE ("+ BillAccountEntry.COLUMN_BA_NAME+
                        ") ON CONFLICT REPLACE);";



        //table ins
        final String SQL_CREAT_INS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + InstallmentEntry.TABLE_NAME + " ("+
                        InstallmentEntry._ID+
                        " INTEGER PRIMARY KEY AUTOINCREMENT , "+
                        InstallmentEntry.COLUMN_INS_NAME+
                        " TEXT NOT NULL , "+
                        InstallmentEntry.COLUMN_INS_TOTAL_COUNT+
                        " INTEGER NOT NULL , "+
                        InstallmentEntry.COLUMN_INS_REPAYED_COUNT+
                        " INTEGER NOT NULL , "+
                        InstallmentEntry.COLUMN_INS_REPAY_NUMBER+
                        " REAL NOT NULL , "+
                        InstallmentEntry.COLUMN_INS_REMARK+
                        " TEXT NOT NULL , "+
                        InstallmentEntry.COLUMN_INS_ACCOUNT+
                        " INTEGER NOT NULL , "+
                        " FOREIGN KEY ( "+InstallmentEntry.COLUMN_INS_ACCOUNT +") " +
                        " REFERENCES "+BillAccountEntry.TABLE_NAME +
                        " ("+BillAccountEntry._ID+")," +
                        " UNIQUE ("+ InstallmentEntry.COLUMN_INS_NAME+
                        ") ON CONFLICT REPLACE) ";

        db.execSQL(SQL_CREAT_BA_TABLE);
        db.execSQL(SQL_CREAT_INS_TABLE);








    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "
                + InstallmentEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "
                + BillAccountEntry.TABLE_NAME);

        onCreate(db);
    }
}
