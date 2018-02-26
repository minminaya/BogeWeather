package cn.minminaya.bogeweather.mvp.function_main.adapter;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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


//    Button yesButton;
//    String text;

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


            holder.mLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    handleClickEvent(position);
                    return false;
                }
            });


        }
    }

    /**
     * 处理点击事件
     */
    private void handleClickEvent(final int position) {
        final AlertDialog build = new AlertDialog.Builder(mContext).create();
        //自定义布局
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.layout_alertdialog_city, null);
        //把自定义的布局设置到dialog中，注意，布局设置一定要在show之前。从第二个参数分别填充内容与边框之间左、上、右、下、的像素
        build.setView(view1, 0, 0, 0, 0);
        //一定要先show出来再设置dialog的参数，不然就不会改变dialog的大小了
//            build.show();
        build.show();
        //得到当前显示设备的宽度，单位是像素
        int width = mContext.getWindowManager().getDefaultDisplay().getWidth();
        //得到这个dialog界面的参数对象
        WindowManager.LayoutParams params = build.getWindow().getAttributes();
        //设置dialog的界面宽度
        params.width = width - (width / 6);
        //设置dialog高度为包裹内容
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的重心
        params.gravity = Gravity.CENTER;
        //dialog.getWindow().setLayout(width-(width/6), LayoutParams.WRAP_CONTENT);
        //用这个方法设置dialog大小也可以，但是这个方法不能设置重心之类的参数，推荐用Attributes设置
        //最后把这个参数对象设置进去，即与dialog绑定
        build.getWindow().setAttributes(params);
        Button yesButton = (Button) view1.findViewById(R.id.ids_btn_city_yes);
        Button cancelButton = (Button) view1.findViewById(R.id.ids_btn_city_cancel);
        final EditText editText = (EditText) view1.findViewById(R.id.ids_edit_city_input);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C.cityTemp = editText.getText().toString();
                build.dismiss();

                if (!C.cityTemp.equals("")) {
                    //只有不为空才执行逻辑操作
                    C.CityNameConstant.citys.set(position, C.cityTemp);

                    notifyDataSetChanged();

                    //发到ManActivity
                    EventBus.getDefault().post(C.FORM_CITYITEMACTITVY_TO＿MAIN_ACTIVITY);

                    mContext.onBackPressed();
                    EventBus.getDefault().post(position);//设置当前显示的fragment
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onBackPressed();
            }
        });
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
