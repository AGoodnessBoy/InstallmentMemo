package ink.moming.installmentmemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ink.moming.installmentmemo.R;
import ink.moming.installmentmemo.adapter.MonthlyBillListAdapter;
import ink.moming.installmentmemo.data.FakeData;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyBillFragment extends Fragment {

    private RecyclerView mRecycleList;
    private MonthlyBillListAdapter mAdapter;


    public MonthlyBillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_bill, container, false);
        mRecycleList = view.findViewById(R.id.rc_monthly_bill_list);
        mAdapter = new MonthlyBillListAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycleList.setLayoutManager(linearLayoutManager);
        mRecycleList.setAdapter(mAdapter);
        FakeData fakeData = new FakeData();
        List<FakeData.Repaydata> repaydata = fakeData.getData();
        mAdapter.swapData(repaydata);

        return view;
    }

}
