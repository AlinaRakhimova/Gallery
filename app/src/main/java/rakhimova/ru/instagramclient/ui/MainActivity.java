package rakhimova.ru.instagramclient.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.adapter.ViewPagerAdapter;
import rakhimova.ru.instagramclient.ui.transformation.CubeOutDepthTransformation;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GalleryFragment.newInstance(), "Gallery");
        adapter.addFragment(FavoriteFragment.newInstance(), "Favorite");
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new CubeOutDepthTransformation());
        tabLayout.setupWithViewPager(viewPager);
    }

}
