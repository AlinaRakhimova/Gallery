package rakhimova.ru.instagramclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;

public class PhotoFragment extends Fragment {

    private static final String EMPTY_STRING = "";

    @BindView(R.id.login)
    TextInputLayout login;

    @BindView(R.id.password)
    TextInputLayout password;

    @BindView(R.id.ok)
    MaterialButton ok;

    public PhotoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLogin = String.valueOf(login.getEditText().getText());
                if (userLogin.equals(EMPTY_STRING)) {
                    login.getEditText().setError("Введите логин");
                } else {
                    String userPassword = String.valueOf(password.getEditText().getText());
                    if (userPassword.equals(EMPTY_STRING)) {
                        password.getEditText().setError("Введите пароль");
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
