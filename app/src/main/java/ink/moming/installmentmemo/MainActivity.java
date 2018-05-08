package ink.moming.installmentmemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import ink.moming.installmentmemo.adapter.BottomNavPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MenuItem mMenuItem;
    private BottomNavigationView mBottomNavigationView;
    private BottomNavPagerAdapter mBottomNavPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView=findViewById(R.id.bottom_navigation_view_main);
        mViewPager = findViewById(R.id.view_pager_main);
        //mBottomNavigationView.setItemIconTintList(getResources().getColorStateList(R.i));
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_item_bill:
                                mViewPager.setCurrentItem(0);
                                break;

                            case R.id.menu_item_plan:
                                mViewPager.setCurrentItem(1);
                                break;

                            case R.id.menu_item_me:
                                mViewPager.setCurrentItem(2);
                                break;
                        }

                        return false;
                    }
                }
        );


        //mBottomNavigationView.setItemIconTintList(null);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (mMenuItem!=null){
                    mMenuItem.setChecked(false);
                }else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);

                mMenuItem.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomNavPagerAdapter = new BottomNavPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mBottomNavPagerAdapter);
    }
}
