package cn.minminaya.bogeweather.mvp.function_main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.function_main.activity.CityItemActivity;

/**
 * Created by Niwa on 2018/2/26.
 */

public class CityItemRecyclerViewAdapter extends RecyclerView.Adapter<CityItemRecyclerViewAdapter.ViewHolder1> {

    private CityItemActivity mContext;

    public CityItemRecyclerViewAdapter(CityItemActivity context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_city, parent, false);
        ViewHolder1 holder1 = new ViewHolder1(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, final int position) {
        int size = C.CityNameConstant.citys.size();

        if (size > 0) {

            holder.tvCityName.setText(C.CityNameConstant.citys.get(position));
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.onBackPressed();
                    EventBus.getDefault().post(position);//设置当前显示的fragment
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_name)
        TextView tvCityName;
        @BindView(R.id.ids_btn_delete)
        Button deleteButton;

        @BindView(R.id.ids_layout_item)
        LinearLayout mLinearLayout;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
