package com.panyz.panframe.mvpDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.panyz.corelib.base.BaseBean;
import com.panyz.corelib.mvp.BaseView;
import com.panyz.panframe.R;

public class MainActivity extends AppCompatActivity implements BaseView<BaseBean> {

    private EditText etAccount, etPwd;
    private Button btnLogin;
    private LinearLayout loadView;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new MainPresenter(this, this);
    }

    private void initViews() {
        etAccount = (EditText) findViewById(R.id.et_account);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        loadView = (LinearLayout) findViewById(R.id.load_view);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(etAccount.getText().toString(), etPwd.getText().toString());
            }
        });

    }

    @Override
    public void startLoadView() {
        loadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadView() {
        loadView.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(BaseBean data) {
        Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
