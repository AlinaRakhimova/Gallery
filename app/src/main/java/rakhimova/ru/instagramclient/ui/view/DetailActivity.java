package rakhimova.ru.instagramclient.ui.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.model.GlideLoader;
import rakhimova.ru.instagramclient.ui.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    public static final String URL = "url";

    @BindView(R.id.content_photo)
    ImageView photo;

    @BindView(R.id.title)
    TextView title;

    @InjectPresenter
    DetailPresenter presenter;

    GlideLoader glideLoader = new GlideLoader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter.onStart();
    }

    @Override
    public void loadPhoto() {
        String url = getIntent().getStringExtra(URL);
        glideLoader.loadImage(url, photo);
    }

}
