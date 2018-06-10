package ink.moming.installmentmemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ink.moming.installmentmemo.R;

import ink.moming.installmentmemo.data.InstallmentContract.InstallmentEntry;
import ink.moming.installmentmemo.data.InstallmentContract.BillAccountEntry;
import ink.moming.installmentmemo.ui.StickyRecyclerView;

public class MonthlyBillListAdapter extends StickyRecyclerView.StickyAdapter<MonthlyBillListAdapter.MonthlyBillListViewHolder> {

    private final Context mContext;
    private Cursor mData;

    public MonthlyBillListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MonthlyBillListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.module_recycle_item_monthly_bill;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layout,parent,false);
        final MonthlyBillListViewHolder viewHolder =new MonthlyBillListViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mData.moveToPosition(position);
        MonthlyBillListViewHolder mHolder = (MonthlyBillListAdapter.MonthlyBillListViewHolder)holder;

        mHolder.mBillName.setText(mData.getString(mData.getColumnIndex(InstallmentEntry.COLUMN_INS_NAME)));
        mHolder.mBillNum.setText(mData.getString(mData.getColumnIndex(InstallmentEntry.COLUMN_INS_REPAY_NUMBER)));
        mHolder.mBillRepayedCount.setText(mData.getString(mData.getColumnIndex(InstallmentEntry.COLUMN_INS_REPAYED_COUNT)));
        mHolder.mBillTotalCount.setText(mData.getColumnIndex(InstallmentEntry.COLUMN_INS_TOTAL_COUNT));

    }


    @Override
    public int getItemCount() {
        if (null==mData) return 0;
        return mData.getCount();
    }
    public void swapData(Cursor newData){
        mData = newData;
        notifyDataSetChanged();
    }

    @Override
    public String getItemViewTitle(int position) {
        mData.moveToPosition(position);
        return mData.getString(mData.getColumnIndex(BillAccountEntry.COLUMN_BA_NAME));
    }


    public class MonthlyBillListViewHolder extends RecyclerView.ViewHolder{

        public final TextView mBillName;
        public final TextView mBillNum;
        public final TextView mBillTotalCount;
        public final TextView mBillRepayedCount;


        public MonthlyBillListViewHolder(View itemView) {
            super(itemView);

            mBillName = itemView.findViewById(R.id.tv_bill_name);
            mBillNum = itemView.findViewById(R.id.tv_bill_num);
            mBillTotalCount = itemView.findViewById(R.id.tv_bill_total);
            mBillRepayedCount = itemView.findViewById(R.id.tv_bill_rest);
        }
    }
}
