package ru.rakhimova.instagramclient.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.instagramclient.R;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.model.GlideLoader;
import ru.rakhimova.instagramclient.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    public static final String ID = "id";

    @BindView(R.id.content_photo)
    ImageView photo;

    @BindView(R.id.title)
    TextView titleTV;

    @InjectPresenter
    DetailPresenter presenter;

    @Inject
    GlideLoader glideLoader;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_photo);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
        App.getAppComponent().inject(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int id = getIntent().getIntExtra(ID, 0);
        presenter.onStart(id);
    }

    @Override
    public void loadPhoto(String title, String url) {
        titleTV.setText(title);
        glideLoader.loadImage(url, photo);
    }

    @Override
    public void showToast(String message) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show(); //FIXME Реализовать отображение toast
        Log.d("Libraries7", message);
    }

}
