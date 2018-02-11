package com.panyz.panframe.networkDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.panyz.corelib.mvp.BaseView;
import com.panyz.panframe.R;

public class HomeActivity extends AppCompatActivity implements BaseView<HomeBean> {

    private Button btn;
    private TextView tvContent;
    private LinearLayout loadView;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePresenter(this, this);
        initViews();
    }

    private void initViews() {
        btn = (Button) findViewById(R.id.button);
        tvContent = (TextView) findViewById(R.id.tv_content);
        loadView = (LinearLayout) findViewById(R.id.load_view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData();
                presenter.loadDataGet();
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
    public void onSuccess(HomeBean data) {
        tvContent.setText(data.toString());
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
