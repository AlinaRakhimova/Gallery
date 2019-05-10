package rakhimova.ru.instagramclient.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.App;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.model.GlideLoader;
import rakhimova.ru.instagramclient.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    public static final String ID = "id";

    @BindView(R.id.content_photo)
    ImageView photo;

    @BindView(R.id.title)
    TextView title;

    @InjectPresenter
    DetailPresenter presenter;

    @Inject
    GlideLoader glideLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int id = getIntent().getIntExtra(ID, 0);
        presenter.onStart(id);
    }

    @Override
    public void loadPhoto(String url) {
        glideLoader.loadImage(url, photo);
    }

}
