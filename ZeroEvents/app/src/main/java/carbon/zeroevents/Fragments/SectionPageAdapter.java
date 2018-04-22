package carbon.zeroevents.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Owner on 02/04/2018.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {

    //Keeping track of the fragments
    private final ArrayList<Fragment> fragmentList = new ArrayList<>();

    //keeping track of fragment titles (name)
    private final ArrayList<String> fragmentTitleList = new ArrayList<>();


    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {

        fragmentList.add(fragment);
        fragmentTitleList.add(title);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
