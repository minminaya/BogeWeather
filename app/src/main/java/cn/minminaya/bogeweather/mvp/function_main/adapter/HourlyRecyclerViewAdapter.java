package cn.minminaya.bogeweather.mvp.function_main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.minminaya.bogeweather.App;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.data.http.model.HourlyBean;

/**
 * 小时模块的RecyclerView的adapter
 * Created by Niwa on 2018/1/26.
 */

public class HourlyRecyclerViewAdapter extends RecyclerView.Adapter<HourlyRecyclerViewAdapter.ViewHolder1> {


    private List<HourlyBean> hourlyBeans = new ArrayList<>();

    public HourlyRecyclerViewAdapter() {
    }

    public List<HourlyBean> getHourlyBeans() {
        return hourlyBeans;
    }

    public void setHourlyBeans(List<HourlyBean> hourlyBeans) {
        this.hourlyBeans = hourlyBeans;
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item_hourly, parent, false);
        ViewHolder1 holder1 = new ViewHolder1(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        if (hourlyBeans.size() > 0) {
            int imgNum = Integer.parseInt(hourlyBeans.get(position).getImg());
            int imgNumRes;
            switch (imgNum) {
                case 99:
                    imgNumRes = R.mipmap.icon_weather_99;
                    break;
                case 301:
                    imgNumRes = R.mipmap.icon_weather_301;
                    break;
                case 302:
                    imgNumRes = R.mipmap.icon_weather_302;
                    break;
                default:
                    imgNumRes = C.icon_pics[imgNum];
                    break;
            }
            holder.mImgHourly.setImageResource(imgNumRes);
            holder.mTvHourlyTemporary.setText(hourlyBeans.get(position).getTemp()+"℃");
            holder.mTvHourlyTime.setText(hourlyBeans.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return hourlyBeans.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.ids_tv_hourly_temporary)
        TextView mTvHourlyTemporary;
        @BindView(R.id.ids_img_hourly)
        ImageView mImgHourly;
        @BindView(R.id.ids_tv_hourly_time)
        TextView mTvHourlyTime;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
