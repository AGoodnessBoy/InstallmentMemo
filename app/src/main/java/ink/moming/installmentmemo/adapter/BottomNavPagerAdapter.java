package ink.moming.installmentmemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ink.moming.installmentmemo.fragment.AccountFragment;
import ink.moming.installmentmemo.fragment.MonthlyBillFragment;
import ink.moming.installmentmemo.fragment.RepaymentPlanFragment;

public class BottomNavPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    public BottomNavPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(new MonthlyBillFragment());
        mFragments.add(new RepaymentPlanFragment());
        mFragments.add(new AccountFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
