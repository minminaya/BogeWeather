package cn.minminaya.bogeweather.mvp.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Niwa on 2017/5/21.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        setListeners();
        bind();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unBind();
    }

    /**
     * --次序0--
     * 返回layout的id
     */
    public abstract int getContentView();

    /**
     * --次序1--
     * 初始化view
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 设置监听器，在initView()之后
     * --次序2--
     */
    public abstract void setListeners();

    /**
     * --次序3--
     * 绑定
     */
    public abstract void bind();

    public abstract void unBind();
}