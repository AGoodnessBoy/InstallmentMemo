package ink.moming.installmentmemo.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import ink.moming.installmentmemo.data.InstallmentContract.InstallmentEntry;
import ink.moming.installmentmemo.data.InstallmentContract.BillAccountEntry;

public class InstallmentProvider extends ContentProvider {


    public static final int CODE_INS = 500;
    public static final int CODE_INS_WITH_ID = 501;

    public static final int CODE_BA = 600;
    public static final int CODE_BA_WITH_ID = 601;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private InstallmentDbHelper mDbHelper;

    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = InstallmentContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,InstallmentContract.PATH_INSTALLMENT,CODE_INS);
        matcher.addURI(authority,InstallmentContract.PATH_BILL_ACCOUNT,CODE_BA);
        matcher.addURI(authority,InstallmentContract.PATH_INSTALLMENT,CODE_INS_WITH_ID);
        matcher.addURI(authority,InstallmentContract.PATH_BILL_ACCOUNT,CODE_BA_WITH_ID);

        return matcher;

    }


    @Override
    public boolean onCreate() {
        mDbHelper = new InstallmentDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)){

            case CODE_INS_WITH_ID:{
                String ins_id = uri.getLastPathSegment();

                String[] selectionArguments =
                        new String[]{ins_id};

                cursor = mDbHelper.getReadableDatabase().query(
                        InstallmentEntry.TABLE_NAME,
                        projection,
                        InstallmentEntry._ID+" = ? ",
                        selectionArguments,
                        null,
                        null,sortOrder);
                break;


            }

            case CODE_INS:{
                cursor = mDbHelper.getReadableDatabase().query(
                        InstallmentEntry.TABLE_NAME,
                        projection,selection,selectionArgs, null,null,sortOrder);

                break;
            }

            case CODE_BA:{
                cursor = mDbHelper.getReadableDatabase().query(
                        BillAccountEntry.TABLE_NAME,
                        projection,selection,selectionArgs, null,null,sortOrder);

                break;
            }

            case CODE_BA_WITH_ID:{
                String ba_id = uri.getLastPathSegment();

                String[] selectionArguments =
                        new String[]{ba_id};

                cursor = mDbHelper.getReadableDatabase().query(
                        BillAccountEntry.TABLE_NAME,
                        projection,
                        BillAccountEntry._ID+" = ? ",
                        selectionArguments,
                        null,
                        null,sortOrder);
                break;

            }

            default:
                throw new UnsupportedOperationException();

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        final SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)){
            case CODE_INS:
                long _id_ins = db.insert(InstallmentEntry.TABLE_NAME,null,values);

                if (_id_ins>0){
                    Uri rowUri = InstallmentEntry.buildUriWithId(_id_ins);
                    getContext().getContentResolver().notifyChange(uri,null);
                    return rowUri;
                }
            case CODE_BA:
                long _id_ba = db.insert(BillAccountEntry.TABLE_NAME,null,values);

                if (_id_ba>0){
                    Uri rowUri = InstallmentEntry.buildUriWithId(_id_ba);
                    getContext().getContentResolver().notifyChange(uri,null);
                    return rowUri;
                }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }

    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)){
            case CODE_INS:
                db.beginTransaction();
                int rowsInserted_ins = 0;
                try {
                    for (ContentValues value : values){

                        long _id =
                                db.insert(
                                        InstallmentEntry.TABLE_NAME,
                                        null,
                                        value
                                );
                        if (_id != -1){
                            rowsInserted_ins++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }

                if (rowsInserted_ins > 0){
                    getContext().getContentResolver().notifyChange(uri,null);
                }
                return rowsInserted_ins;
            case CODE_BA:
                db.beginTransaction();
                int rowsInserted_ba = 0;
                try {
                    for (ContentValues value : values){

                        long _id =
                                db.insert(
                                        BillAccountEntry.TABLE_NAME,
                                        null,
                                        value
                                );
                        if (_id != -1){
                            rowsInserted_ba++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }

                if (rowsInserted_ba > 0){
                    getContext().getContentResolver().notifyChange(uri,null);
                }
                return rowsInserted_ba;

            default:
                return super.bulkInsert(uri,values);


        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {


        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)){

            case CODE_INS:
                int numRowsDeleted_ins;
                numRowsDeleted_ins =mDbHelper.getWritableDatabase().delete(
                        InstallmentEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                if (numRowsDeleted_ins !=0 ){
                    getContext().getContentResolver().notifyChange(uri,null);
                }

                return numRowsDeleted_ins;
            case CODE_BA:
                int numRowsDeleted_ba;
                numRowsDeleted_ba =mDbHelper.getWritableDatabase().delete(
                        BillAccountEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                if (numRowsDeleted_ba !=0 ){
                    getContext().getContentResolver().notifyChange(uri,null);
                }

                return numRowsDeleted_ba;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);


        }

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (sUriMatcher.match(uri)){
            case CODE_INS_WITH_ID:
                int updateReturn_ins;
                updateReturn_ins = mDbHelper.getWritableDatabase().update(
                        InstallmentEntry.TABLE_NAME,values,selection,selectionArgs);
                if (updateReturn_ins!=0){
                    getContext().getContentResolver().notifyChange(uri,null);
                }

                return updateReturn_ins;
            case CODE_BA_WITH_ID:
                int updateReturn_ba;
                updateReturn_ba = mDbHelper.getWritableDatabase().update(
                        BillAccountEntry.TABLE_NAME,values,selection,selectionArgs);
                if (updateReturn_ba!=0){
                    getContext().getContentResolver().notifyChange(uri,null);
                }

                return updateReturn_ba;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


    }
}
