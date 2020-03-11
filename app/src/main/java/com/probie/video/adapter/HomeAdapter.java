package com.probie.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.probie.video.R;
import com.probie.video.model.Img;

import java.util.List;

/**
 * @author ：probie
 * @date ：Created in 2020/3/9 12:52
 * @description：
 * @modified By：
 * @version: $
 */
public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<Img> data;

    public HomeAdapter(Context context, List<Img> data){
        super();
        this.context = context;
        this.data = data ;

    }

    @Override
    public int getCount() {
        //返回data中数据的数量
        return data.size();
    }

    @Override
    public Img getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid,null);
            holder = new ViewHolder();
            holder.imgview = convertView.findViewById(R.id.item_image);
            holder.title = convertView.findViewById(R.id.item_title);
            holder .episode = convertView.findViewById(R.id.item_episode);
            Uri uri = Uri.parse(getItem(position).getSrc());
            holder.imgview.setImageURI(uri);
            holder.title.setText(getItem(position).getTitle());
            holder.episode.setText(getItem(position).getEpisode());
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            Uri uri = Uri.parse(getItem(position).getSrc());
            holder.imgview.setImageURI(uri);
            holder.title.setText(getItem(position).getTitle());
            holder.episode.setText(getItem(position).getEpisode());
            convertView.setTag(holder);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo...............
            }
        });


        return convertView;
    }



    class ViewHolder{
        SimpleDraweeView imgview;
        TextView title;
        TextView episode;

    }

}
