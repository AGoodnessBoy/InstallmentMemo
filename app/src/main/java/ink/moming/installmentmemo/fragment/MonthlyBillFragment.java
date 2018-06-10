package ink.moming.installmentmemo.fragment;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import ink.moming.installmentmemo.R;
import ink.moming.installmentmemo.adapter.MonthlyBillListAdapter;
import ink.moming.installmentmemo.data.FakeData;
import ink.moming.installmentmemo.data.InstallmentContract;
import ink.moming.installmentmemo.ui.StickyRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyBillFragment extends Fragment  {

    public static final String TAG = MonthlyBillFragment.class.getSimpleName();
    public static final int ID_MB_LOADER=204;
    public static final String[] MB_PROJECTION = {
            InstallmentContract.InstallmentEntry.COLUMN_INS_NAME,
            InstallmentContract.InstallmentEntry.COLUMN_INS_REPAYED_COUNT,
            InstallmentContract.InstallmentEntry.COLUMN_INS_TOTAL_COUNT,
            InstallmentContract.InstallmentEntry.COLUMN_INS_REPAYED_COUNT,
            InstallmentContract.BillAccountEntry.COLUMN_BA_NAME,
            InstallmentContract.BillAccountEntry.COLUMN_BA_REPAYMENT_DATE,
    };

    private StickyRecyclerView mRecycleList;
    private MonthlyBillListAdapter mAdapter;
    private Button mInitializationButton;



    public MonthlyBillFragment() {
        // Required empty public constructor
    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderMBCallbacks(final Context context){
        return new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                switch (id){
                    case ID_MB_LOADER:
                        //启动加载状态
                        mRecycleList.setVisibility(View.INVISIBLE);
                        Uri uri = InstallmentContract.InstallmentEntry.CONTENET_WITH_BA_URI;
                        return new CursorLoader(context,uri,null,null,null,null);
                    default:
                        throw new RuntimeException("Loader Not Implemented: " + id);


                }
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                boolean cursorHasValidData = false;
                if (data!=null && data.moveToFirst()){
                    cursorHasValidData = true;
                }
                if (!cursorHasValidData){
                    //新建数据
                    return;
                }
                mAdapter.swapData(data);

            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                mAdapter.swapData(null);
            }
        };
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_bill, container, false);
        mRecycleList = view.findViewById(R.id.rc_monthly_bill_list);
        mInitializationButton = view.findViewById(R.id.btn_initialization_bill_account);
        mAdapter = new MonthlyBillListAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycleList.setLayoutManager(linearLayoutManager);
        mRecycleList.setAdapter(mAdapter);
        return view;
    }

}
