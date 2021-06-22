package com.GMS.representative.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.GMS.representative.fragments.NeedScanFragment;
import com.GMS.representative.fragments.VerifiedFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainFragmentAdapter  extends FragmentStateAdapter {

    ArrayList<Fragment> lstFragments = new ArrayList<>();
    ArrayList<String> lstStrings = new ArrayList<>();

    public MainFragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0 :
                return new NeedScanFragment();
            case 1 :
                return new VerifiedFragment();

        }
       return new NeedScanFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
