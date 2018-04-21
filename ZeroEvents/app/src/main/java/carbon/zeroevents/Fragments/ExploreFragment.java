package carbon.zeroevents.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import carbon.zeroevents.R;
import carbon.zeroevents.Tabs.SortedTab;
import carbon.zeroevents.Tabs.AllTab;
import carbon.zeroevents.Tabs.OutSoonTab;
import carbon.zeroevents.Tabs.TabsPagerAdapter;

/**
 * Created by Owner on 03/04/2018.
 */

public class ExploreFragment extends Fragment {

    private ViewPager viewPager;

    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.explore_tab,container,false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new AllTab(), "All Movies");
        adapter.addFragment(new SortedTab(), "Sorted Movies Asc.");
        adapter.addFragment(new OutSoonTab(), "Coming Out Soon");

        viewPager.setAdapter(adapter);
    }
}