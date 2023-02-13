package com.naseyun.computer.myrefrigerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class TabPagerAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FreshBasketFragment freshBasketFragment;
    private ExpirationBasketFragment expirationBasketFragment;

//    public TabPagerAdapter(@NonNull Fragment fragment) {
//        super(fragment);
//    }

    public TabPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragment(int position) {
        fragments.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        Fragment currentFragment = new Fragment();
//        switch(position) {
//            case 0:
////                freshBasketFragment = new FreshBasketFragment();
////                addFragment(freshBasketFragment);
//                currentFragment = fragments.get(0);
//                break;
//            case 1:
////                expirationBasketFragment = new ExpirationBasketFragment();
////                addFragment(expirationBasketFragment);
//                currentFragment = fragments.get(1);
//                break;
//            default:
//                return null;
//        }
//        //return fragments.get(position);
//        return currentFragment;
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
