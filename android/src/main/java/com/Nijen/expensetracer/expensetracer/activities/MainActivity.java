package com.Nijen.expensetracer.expensetracer.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ImageButton;

import com.Nijen.expensetracer.expensetracer.fragments.ReportFragment;
import com.github.Nijen.expensetracer.R;
import com.Nijen.expensetracer.expensetracer.fragments.CategoryFragment;
import com.Nijen.expensetracer.expensetracer.fragments.TodayFragment;

public class MainActivity extends BaseFragmentActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private ImageButton todayButton;
    private ImageButton reportButton;
    private ImageButton categoryButton;
    private ImageButton settingButton;

    @Override
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        todayButton = (ImageButton) findViewById(R.id.today_button);
        reportButton = (ImageButton) findViewById(R.id.report_button);
        categoryButton = (ImageButton) findViewById(R.id.category_button);
        settingButton = (ImageButton) findViewById(R.id.setting_button);

        todayButton.setOnClickListener(v -> selectItem(R.id.nav_today));
        reportButton.setOnClickListener(v -> selectItem(R.id.nav_report));
        categoryButton.setOnClickListener(v -> selectItem(R.id.nav_categories));
        settingButton.setOnClickListener(v -> selectItem(R.id.nav_settings));

        selectItem(R.id.nav_today);

    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (!(currentFragment instanceof TodayFragment)) {
            loadTodayFragment();
        } else {
            // If current fragment is TodayFragment then exit
            super.onBackPressed();
        }
    }


    private void selectItem(int id) {
        switch (id) {
            case R.id.nav_report:
                loadFragment(ReportFragment.class, id, getResources().getString(R.string.nav_report));
                setSelectedColor(id);
                break;
            case R.id.nav_categories:
                loadFragment(CategoryFragment.class, id, getResources().getString(R.string.nav_categories));
                setSelectedColor(id);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            default:
                setSelectedColor(id);
                loadFragment(TodayFragment.class, id, getResources().getString(R.string.nav_today));
        }
    }

    private void setSelectedColor(@IdRes int id) {
        switch (id) {
            case R.id.nav_today:
                todayButton.setBackgroundColor(getResources().getColor(R.color.white));
                todayButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                reportButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                reportButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                categoryButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                categoryButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                break;

            case R.id.nav_report:
                todayButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                todayButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                reportButton.setBackgroundColor(getResources().getColor(R.color.white));
                reportButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                categoryButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                categoryButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                break;
            case R.id.nav_categories:
                todayButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                todayButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                reportButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                reportButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                categoryButton.setBackgroundColor(getResources().getColor(R.color.white));
                categoryButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                break;
            default:
                break;
        }
    }


    private void loadFragment(Class fragmentClass, @IdRes int navDrawerCheckedItemId,
                              CharSequence toolbarTitle) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        insertFragment(fragment);
        // Set action bar title
        setTitle(toolbarTitle);
    }

    private void loadTodayFragment() {
        loadFragment(TodayFragment.class, R.id.nav_today,
                getResources().getString(R.string.nav_today));
    }
}

