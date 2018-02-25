package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.CityItemRecyclerViewAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.CityItemActivityPresenter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.WeatherItemPresenter;

/**
 * 城市列表页
 */
public class CityItemActivity extends BaseActivity implements MvpView {

    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private CityItemActivityPresenter mCityItemActivityPresenter = null;

    public CityItemRecyclerViewAdapter mCityItemRecyclerViewAdapter = null;

    @Override
    public int getContentView() {
        return R.layout.activity_city_item;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mCityItemRecyclerViewAdapter = new CityItemRecyclerViewAdapter();
        mRecyclerView.setAdapter(mCityItemRecyclerViewAdapter);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void bind() {
        mCityItemActivityPresenter = new CityItemActivityPresenter();
        mCityItemActivityPresenter.attachView(this);

    }

    @Override
    public void unBind() {
        mCityItemActivityPresenter.detachView(this);
    }

    @Override
    public void onFailed(Throwable e) {

    }
}
