package com.hooli.luggage_app.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hooli.luggage_app.R;
import com.hooli.luggage_app.fragment.HomeFragment;
import com.hooli.luggage_app.fragment.PersonFragment;
import com.hooli.luggage_app.fragment.TravelAssistantFragment;
import com.hooli.luggage_app.fragment.TravelNoteFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationViewEx bnve;
    HomeFragment homeFragment;
    PersonFragment personFragment;
    TravelAssistantFragment travelAssistantFragment;
    TravelNoteFragment travelNoteFragment;
    List<Fragment> fragments = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bnve.setCurrentItem(0);
        //初始化home_fragment
        showFragment(homeFragment, HomeFragment.class, getSupportFragmentManager().beginTransaction());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();//重置到默认不选中图片
            switch (item.getItemId()) {
                case R.id.work_box:
                    //在这里替换图标
                    item.setIcon(R.mipmap.main_page_table_work_box_press);
                    showFragment(homeFragment, HomeFragment.class, getSupportFragmentManager().beginTransaction());
                    break;
                case R.id.table_travelassistant:
                    item.setIcon(R.mipmap.main_page_table_travelassistant_press);
                    showFragment(travelAssistantFragment, TravelAssistantFragment.class, getSupportFragmentManager().beginTransaction());
                    break;
                case R.id.table_find:
                    item.setIcon(R.mipmap.main_page_table_find_press);
                    showFragment(travelNoteFragment, TravelNoteFragment.class, getSupportFragmentManager().beginTransaction());
                    break;
                case R.id.table_myself:
                    item.setIcon(R.mipmap.main_page_table_myself_press);
                    showFragment(personFragment, PersonFragment.class, getSupportFragmentManager().beginTransaction());
                    return true;
            }
            return false;
        }
    };
    private void resetToDefaultIcon() {
        MenuItem home =  bnve.getMenu().findItem(R.id.work_box);
        home.setIcon(R.mipmap.main_page_table_work_box_normal);
        MenuItem table_travelassistant =  bnve.getMenu().findItem(R.id.table_travelassistant);
        table_travelassistant.setIcon(R.mipmap.main_page_table_travelassistant_normal);
        MenuItem table_find =  bnve.getMenu().findItem(R.id.table_find);
        table_find.setIcon(R.mipmap.main_page_table_find_normal);
        MenuItem table_myself =  bnve.getMenu().findItem(R.id.table_myself);
        table_myself.setIcon(R.mipmap.main_page_table_myself_normal);
    }

    //展示fragment
    private void showFragment(Fragment fragment, Class<? extends Fragment> fclass, FragmentTransaction transaction) {
        if (fragment == null) {
            try {
                Constructor c = fclass.getConstructor();
                fragment = (Fragment) c.newInstance();
                String className = fragment.getClass().getSimpleName();
                switch (className) {
                    case "HomeFragment":
                        homeFragment = (HomeFragment) fragment;
                        break;
                    case "TravelNoteFragment":
                        travelNoteFragment = (TravelNoteFragment) fragment;
                        break;
                    case "TravelAssistantFragment":
                        travelAssistantFragment = (TravelAssistantFragment) fragment;
                        break;
                    case "PersonFragment":
                        personFragment = (PersonFragment) fragment;
                        break;
                }
                transaction.add(R.id.frame_4_replace, fragment);
                fragments.add(fragment);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            hideAllFragment(transaction);
            transaction.show(fragment);
        }
        transaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction transaction) {
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }



}
