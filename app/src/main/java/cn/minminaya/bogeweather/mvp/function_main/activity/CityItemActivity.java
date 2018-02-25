package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.CityItemRecyclerViewAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.CityItemActivityPresenter;

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
                String text = mEditText.getText().toString();
                if (!text.equals("")) {
                    //只有不为空才执行逻辑操作
                    C.CityNameConstant.citys.set(4, text);

                    mCityItemRecyclerViewAdapter.notifyDataSetChanged();

                    //发到ManActivity
                    EventBus.getDefault().post(C.FORM_CITYITEMACTITVY_TO＿MAIN_ACTIVITY);

                    onBackPressed();
                    EventBus.getDefault().post(4);//设置当前显示的fragment
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
