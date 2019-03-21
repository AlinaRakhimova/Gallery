package rakhimova.ru.instagramclient.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import rakhimova.ru.instagramclient.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_sign_in:
                loadFragment(AuthorizationFragment.newInstance());
                return true;
            case R.id.navigation_gallery:
                loadFragment(GalleryFragment.newInstance());
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_content, AuthorizationFragment.newInstance())
                    .commit();
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                .replace(R.id.fl_content, fragment)
                .commit();
    }
}
