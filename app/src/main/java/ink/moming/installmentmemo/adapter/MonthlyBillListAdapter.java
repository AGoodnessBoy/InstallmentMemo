package ink.moming.installmentmemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ink.moming.installmentmemo.R;
import ink.moming.installmentmemo.data.FakeData;

public class MonthlyBillListAdapter extends RecyclerView.Adapter<MonthlyBillListAdapter.MonthlyBillListViewHolder> {

    private final Context mContext;
    private List<FakeData.Repaydata> mData;

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
    public void onBindViewHolder(MonthlyBillListViewHolder holder, int position) {
        FakeData.Repaydata currData = mData.get(position);
        holder.mBillName.setText(currData.getName());
        holder.mBillNum.setText(currData.getRepay_number());
        holder.mBillRepayedCount.setText(currData.getRepayed_count());
        holder.mBillTotalCount.setText(currData.getTotal_count());

    }

    @Override
    public int getItemCount() {
        if (null==mData) return 0;
        return mData.size();
    }
    public void swapData(List<FakeData.Repaydata> newData){
        mData = newData;
        notifyDataSetChanged();
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
