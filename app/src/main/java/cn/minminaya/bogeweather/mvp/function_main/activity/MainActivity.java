package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MvpView {

    private MainPresenter mainPresenter = new MainPresenter();

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void bind() {
        mainPresenter.attachView(this);
        mainPresenter.test();
    }

    @Override
    public void unBind() {
        mainPresenter.detachView(this);
    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        Toast.makeText(this, "visible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(Throwable e) {

    }
}
