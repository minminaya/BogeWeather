package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.data.http.NetWorkForRestApi;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.CityItemRecyclerViewAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.CityItemActivityPresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 城市列表页
 */
public class CityItemActivity extends BaseActivity implements MvpView {

    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.btn_add_city_fragment)
    Button mAddCityFragmentButton;

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
        mCityItemRecyclerViewAdapter = new CityItemRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mCityItemRecyclerViewAdapter);
    }

    @Override
    public void setListeners() {
        mAddCityFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = mEditText.getText().toString();
                if (!text.equals("")) {
                    mCityItemActivityPresenter.handleCityEditText(text);
                }
            }
        });
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
