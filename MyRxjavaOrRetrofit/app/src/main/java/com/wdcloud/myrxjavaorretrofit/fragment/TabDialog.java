package com.wdcloud.myrxjavaorretrofit.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.entity.TabBean;
import com.wdcloud.myrxjavaorretrofit.util.TabLayoutUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umbrella on 2019/1/2.
 */

public class TabDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private List<TabBean> XDlist = new ArrayList<>();
    private List<TabBean> XKlist = new ArrayList<>();
    private List<TabBean> NJlist = new ArrayList<>();
    private List<TabBean> Alldatalist;
    private TabLayout tab;
    private TabBean XDtabBean;
    private TabBean XKtabBean;
    private TabBean NJtabBean;
    private int mSelectXDPosition = 0; //选中 省份 位置
    private int mSelectXKPosition = 0;//选中 城市  位置
    private int mSelectNJPosition = 0;//选中 区县  位置
    private OnXDSureListener onXDSureListener;
    private OnXKSureListener onXkSureListener;
    private OnNJSureListener onNJSureListener;
    private View view;
    private View tabView;
    private View tabView2;
    private TabBeanAdapter tabBeanAdapter;
    private RecyclerView tabrv;
    public TabDialog(Context context) {
        this(context, R.style.quick_option_dialog);
        this.context = context;
    }

    public TabDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }
//
//    protected TabDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(context, R.layout.tablayout_dialog, null);
        setFindView(view);
        setContentView(view);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.horizontalMargin = 0;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    private void setFindView(View view) {
         Alldatalist= new ArrayList<>();
        tab = view.findViewById(R.id.tab);
        new TabLayoutUtil().reflex(tab);
        tabrv = view.findViewById(R.id.tab_rv);
        TextView title_text = view.findViewById(R.id.title_text);
        ImageView close_dialog = view.findViewById(R.id.close_dialog);
        close_dialog.setOnClickListener(this);
        tab.addTab(tab.newTab().setText("学校"));
        tab.addTab(tab.newTab().setText(""));
        tab.addTab(tab.newTab().setText(""));
        LinearLayoutManager tablistManager = new LinearLayoutManager(context);
        tablistManager.setOrientation(LinearLayoutManager.VERTICAL);
        tabrv.setLayoutManager(tablistManager);
        LinearLayout tabStrip = (LinearLayout) tab.getChildAt(0);//没有数据的设置为不可点击，颜色边灰色
        tabView = tabStrip.getChildAt(1);
        tabView2 = tabStrip.getChildAt(2);
        tabView.setClickable(false);
        tabView2.setClickable(false);
        tabBeanAdapter = new TabBeanAdapter();
        tabrv.setAdapter(tabBeanAdapter);
        Alldatalist.addAll(XDlist);
        tab.setOnTabSelectedListener(tabSelectedListener);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
      class TabBeanAdapter extends RecyclerView.Adapter<TabBeanAdapter.ViewHolder>{

          @Override
          public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
              View view= LayoutInflater.from(context).inflate(R.layout.tabrvitem_layout,parent,false);
              ViewHolder viewHolder=new ViewHolder(view);
              return viewHolder;
          }

          @Override
          public void onBindViewHolder(ViewHolder holder, int position) {
              Boolean select = Alldatalist.get(position).getSelect();
              String tabname = Alldatalist.get(position).getTabname();
              if(select==true)
              {
                  holder.mTitle.setTextColor(Color.parseColor("#7EC0EE"));
                  holder.mTitle.setChecked(true);
              }
              else
              {
                  holder.mTitle.setTextColor(Color.parseColor("#8a000000"));
                  holder.mTitle.setChecked(false);
              }
              holder.mTitle.setText(tabname+"    ");
              holder.mTitle.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      int tabSelectPosition = tab.getSelectedTabPosition();
                      switch (tabSelectPosition)
                      {
                          case 0:
                              XKlist.clear();
                              XDtabBean = Alldatalist.get(position);
                              onXDSureListener.onXDSureClick(XDtabBean.getTabname(),XDtabBean.getId());
                              XKtabBean=null;
                              NJtabBean=null;
                              mSelectXKPosition=0;
                              mSelectNJPosition=0;
                              for (int i = 0; i <Alldatalist.size(); i++) {
                                  Alldatalist.get(i).setSelect(false);
                              }
                              XDtabBean.setSelect(true);
                              tab.getTabAt(1).setText("年级");
                              tab.getTabAt(2).setText("");
                              tab.getTabAt(0).setText(XDtabBean.getTabname());
                              tabView.setClickable(true);
                              mSelectXDPosition = position;
                              break;
                          case 1:
                              NJlist.clear();
                              XKtabBean = Alldatalist.get(position);
                              onXkSureListener.onXKSureClick(XKtabBean.getTabname(),XKtabBean.getId());
                              NJtabBean=null;
                              mSelectNJPosition=0;
                              for (int i = 0; i <Alldatalist.size(); i++) {
                                  Alldatalist.get(i).setSelect(false);
                              }
                              XKtabBean.setSelect(true);
                              tab.getTabAt(2).setText("班级");
                              tab.getTabAt(1).setText(XKtabBean.getTabname());
//                              tab.getTabAt(2).select();
                              tabView2.setClickable(true);
                              mSelectXKPosition = position;
                              break;
                          case 2:
                              tabView.setClickable(true);
                              tabView2.setClickable(true);
                              NJtabBean = Alldatalist.get(position);
                              for (int i = 0; i <Alldatalist.size(); i++) {
                                  Alldatalist.get(i).setSelect(false);
                              }
                              NJtabBean.setSelect(true);
                              tab.getTabAt(2).setText(NJtabBean.getTabname());
                              notifyDataSetChanged();
                              mSelectNJPosition = position;
                              onNJSureListener.onNJSureClick(NJtabBean.getTabname(),NJtabBean.getId());
                              dismiss();
                              break;
                      }
                  }
              });
          }

          @Override
          public int getItemCount() {
              return Alldatalist.size();
          }

          class ViewHolder extends RecyclerView.ViewHolder {
              CheckedTextView mTitle;

              ViewHolder(View itemView) {
                  super(itemView);
                  mTitle = (CheckedTextView) itemView.findViewById(R.id.tab_rv_text);
              }
          }
      }

    /**
     * 点确定回调这个接口
     */
    public interface OnXDSureListener {
        void onXDSureClick(String XD, String ID);
    }
    public interface OnXKSureListener {
        void onXKSureClick(String XK, String ID);
    }
    public interface OnNJSureListener {
        void onNJSureClick(String NJ, String ID);
    }
    public void setOnXDSureListener(OnXDSureListener listener) {
        this.onXDSureListener = listener;
    }
    public void setOnXKSureListener(OnXKSureListener listener) {
        this.onXkSureListener = listener;
    }
    public void setOnNJSureListener(OnNJSureListener listener) {
        this.onNJSureListener = listener;
    }
    public void setXDList(List<TabBean> xdList)
    {
        this.XDlist=xdList;
    }
    public void setXKList(List<TabBean> xkList)
    {
        this.XKlist=xkList;
        tab.getTabAt(1).select();
    }
    public void setNJList(List<TabBean> njList)
    {
        this.NJlist=njList;
        tab.getTabAt(2).select();
    }
    TabLayout.OnTabSelectedListener tabSelectedListener=new TabLayout.OnTabSelectedListener()
    {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Alldatalist.clear();
            int position = tab.getPosition();
            switch (position)
            {
                case 0:
                    Alldatalist.addAll(XDlist);
                    tabBeanAdapter.notifyDataSetChanged();
                    tabrv.smoothScrollToPosition(mSelectXDPosition);
                    break;
                case 1:
                    Alldatalist.addAll(XKlist);
                    tabBeanAdapter.notifyDataSetChanged();
                    tabrv.smoothScrollToPosition(mSelectXKPosition);
                    break;
                case 2:
                    Alldatalist.addAll(NJlist);
                    tabBeanAdapter.notifyDataSetChanged();
                    tabrv.smoothScrollToPosition(mSelectNJPosition);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
