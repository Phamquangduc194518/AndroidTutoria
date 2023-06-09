package com.example.binance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.binance.vi.FundingFragment;
import com.example.binance.vi.future.vithe.ViTheAdapter;

import com.example.binance.vi.future.vithe.ViTheBlankFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements FundingFragment.FundingDataListener {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        ViTheBlankFragment viTheBlankFragment = new ViTheBlankFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(viTheBlankFragment, "ViTheBlankFragmentTag");
        transaction.commit();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.trangchu).setChecked(true);
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.thitruong).setChecked(true);
                        break;

                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.giaodich).setChecked(true);
                        break;

                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.futures).setChecked(true);
                        break;

                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.vi).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trangchu:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.thitruong:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.giaodich:
                        viewPager.setCurrentItem(2);
                        break;

                    case R.id.futures:
                        viewPager.setCurrentItem(3);
                        break;

                    case R.id.vi:
                        viewPager.setCurrentItem(4);
                        break;

                }
                return true;
            }
        });
    }


    @Override
    public void onDataSend(String nameCoin, String quantityCoin) {
        ViTheBlankFragment viTheBlankFragment = (ViTheBlankFragment) getSupportFragmentManager().findFragmentByTag("ViTheBlankFragmentTag");
        if (viTheBlankFragment != null) {
            viTheBlankFragment.receivedDataFromFunding(nameCoin, quantityCoin);
        }
    }
}