package ninja.ibtehaz.fishersmarket;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;

import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Awareness";
                case 1:
                    return "Fishers Market - Login";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);


            /**
             * Initiate LISTVIEW - Expandable
             *
             */
            android.widget.ExpandableListAdapter listAdapter;
            ExpandableListView expListView;
            List<String> listDataHeader = null;
            HashMap<String, List<String>> listDataChild = null;
//            expListView = (ExpandableListView)findViewById();
//
//            prepareListData();
//            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//            expListView.setAdapter(listAdapter);
            /**
             * END
             */

            /**
             * Intiiazting the Layouts for each of the Views
             */

            final LinearLayout page1 = (LinearLayout)rootView.findViewById(R.id.awareness_linear_layout);

            final LinearLayout page2 = (LinearLayout)rootView.findViewById(R.id.market_login);

            final LinearLayout page3 = (LinearLayout)rootView.findViewById(R.id.market_newAccount);

            Button btn = (Button)rootView.findViewById(R.id.button_awareness);

            final Intent i = new Intent(getContext(), forbidden_Lan.class);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(i);
                }
            });


            /**
             * Market Login Layout Activation
             */

            Button newAccount_login = (Button)rootView.findViewById(R.id.newAccount_login);


            /**
             * Market Layout Activation
             */

            Button login_newAccount = (Button)rootView.findViewById(R.id.Login_newAccount);



            /**
             * getArguments().getInt(ARG_SECTION_NUMBER) == 1 means the first tab and thats how you control the tabbed ELEMENTS
             *
             */
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1 )
            {
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.GONE);
            }
            else
            {
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
            }


            /**
             * Test Code Can Be DELETED -- TODO
             */
            newAccount_login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    page1.setVisibility(View.GONE);
                    page2.setVisibility(View.GONE);
                    page3.setVisibility(View.VISIBLE);

                }
            });

            login_newAccount.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    page1.setVisibility(View.GONE);
                    page2.setVisibility(View.VISIBLE);
                    page3.setVisibility(View.GONE);

                }
            });


            return rootView;
        }
    }
}
