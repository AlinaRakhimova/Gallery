package rakhimova.ru.instagramclient.ui.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    @BindView(R.id.content_photo)
    ImageView photo;

    @BindView(R.id.title)
    TextView title;

    @InjectPresenter
    DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter.onStart();
    }

    @Override
    public void loadPhoto() {
        String titleString = getIntent().getStringExtra("title");
        int url = getIntent().getIntExtra("url", 1);
        photo.setImageResource(url);
        title.setText(titleString);
    }

}
