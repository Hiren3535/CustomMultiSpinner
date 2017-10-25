package com.hiren.custommultispinner;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hiren on 25/10/17.
 */

public class CustomAdapter {

    ArrayList<Integer> posSel = new ArrayList<Integer>();
    private Context mContext;
    String finallanguage;
    private static boolean[] thumbnailsselection;
    private static int Max_Select;
    int countselection = 0;
    private Dialog dialog;
    private ListView listView;
    private String titlemessage;
    private TextView textView;
    private ArrayList<String> items = new ArrayList<String>();

    public CustomAdapter(Context mContext, String title, ArrayList<String> items, TextView text,int max) {
        this.mContext = mContext;
        this.titlemessage = title;
        this.items = items;
        this.textView = text;
        Max_Select = max;
        SetDialog();
    }

    private void SetDialog() {
        thumbnailsselection = new boolean[items.size()];
        dialog = new Dialog(mContext);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.row_selectchoice);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        listView = (ListView) dialog.findViewById(R.id.list_selcetion);
        final TextView listtitle = (TextView) dialog.findViewById(R.id.tv_popup_title);
        final TextView submit = (TextView) dialog.findViewById(R.id.tv_submit_list);
        listView.setDivider(mContext.getResources().getDrawable(R.drawable.gradient));
        listView.setDividerHeight(1);
        if(!titlemessage.isEmpty()){
            listtitle.setText(titlemessage);
        }
        listView.setAdapter( new CustomAdapter.ImageAdapter(mContext,items,titlemessage));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posSel.clear();
                boolean noSelect = false;
                StringBuilder sb = new StringBuilder();
                StringBuilder sbpos = new StringBuilder();
                for (int i = 0; i < thumbnailsselection.length; ++i) {
                    if (thumbnailsselection[i]) {
                        if (noSelect) {
                            sb.append(",");
                        }
                        noSelect = true;
                        sb.append(items.get(i));
                        Log.e("abctext", sb.toString());
                    }
                }
                textView.setText(sb.toString());
                for (int i = 0; i < thumbnailsselection.length; i++) {
                    if (thumbnailsselection[i]) {
                        noSelect = true;
                        posSel.add(i + 1);
                        sbpos.append(i + 1 + ",");
                        finallanguage = sbpos.substring(0, sbpos.length() - 1);
                        System.out.println(sb.toString());
                        Log.e("sel pos thu-->", "" + i + "__");
                    }
                }

                if (!noSelect) {
                    Log.d("string", sbpos.toString() + "_" + finallanguage);
                    dialog.dismiss();
                    //Toast.makeText(mContext, "Please Select Item!",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("stringf", sbpos.toString() + "_" + finallanguage);
                    dialog.dismiss();
                    //Toast.makeText(mContext,"Selected Items:" + posSel.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static class ImageAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        ArrayList<Integer> posSel = new ArrayList<Integer>();
        private Context mContext;
        String finallanguage;
        int countselection = 0;
        private ArrayList<String> items = new ArrayList<String>();

        public ImageAdapter(Context context, ArrayList<String> itemslist, String title) {
            this.mContext = context;
            this.items = itemslist;
        }

        public int getCount() {
            return items.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final CustomAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new CustomAdapter.ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
                holder.textview = (TextView) convertView.findViewById(R.id.textView_popup);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBox_popup);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.checkbox.setId(position);
            holder.textview.setId(position);
            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    CheckBox cb = (CheckBox) v;
                    int id = cb.getId();
                    if (thumbnailsselection[id]) {
                        cb.setChecked(false);
                        thumbnailsselection[id] = false;
                        countselection--;
                        Log.e("padi", thumbnailsselection[id] + "__" + countselection);
                    } else {
                        try {
                            if (countselection >= Max_Select) {
                                cb.setChecked(false);
                                thumbnailsselection[id] = false;
                                Log.e("padiys", thumbnailsselection[id] + "__" + countselection);
                                Toast.makeText(mContext, "You are Not Able to select more than "+Max_Select+" choice", Toast.LENGTH_SHORT).show();
                            } else {
                                cb.setChecked(true);
                                thumbnailsselection[id] = true;
                                countselection++;
                                Log.e("padiy", thumbnailsselection[id] + "__" + countselection);
                            }
                        }catch (Exception e){
                            Log.e("error in count",e+"");
                        }
                    }
                }
            });
            holder.textview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    int id = v.getId();
                }
            });
            holder.textview.setText(items.get(position));
            holder.checkbox.setChecked(thumbnailsselection[position]);
            holder.id = position;
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView textview;
        CheckBox checkbox;
        int id;
    }

}
