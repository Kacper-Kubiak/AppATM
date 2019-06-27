package pl.pwr.s230473.appatm;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/*
    * Icons made by Freepik from www.flaticon.com is licensed by CC 3.0 BY
 */
public class MainActivity extends AppCompatActivity {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.ic_action_dollar_symbol,
            R.drawable.ic_action_dollar_sign_and_piles_of_coins,
            R.drawable.ic_action_dollar_symbol
    };

    private String[] tabName = {
            "Wymiana",
            "Kursy",
            "TEST"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExchangeFragment(), tabName[0]);
        adapter.addFragment(new CurrencyFragment(), tabName[1]);
        adapter.addFragment(new Tab3Fragment(), tabName[2]);
        viewPager.setAdapter(adapter);
    }
}
