package com.wordpress.farhantanvirutshaw.viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position)
                {
                    case 0:
                        return new MondayFragment();
                    case 1:
                        return new TuesdayFragment();
                    default:
                        return new WednesdayFragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position)
                {
                    case 0:
                        return "Monday";
                    case 1:
                        return "Tuesday";
                    default:
                        return "Wednesday";
                }
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);

        TabLayout tabLayout  = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
