package com.probie.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.probie.video.R;
import com.probie.video.model.Episode;
import com.probie.video.ui.PlayActivity;

import java.util.List;

/**
 * @author ：probie
 * @date ：Created in 2020/3/11 19:11
 * @description：
 * @modified By：
 * @version: $
 */
public class EpisodeAdapter  extends BaseAdapter {

    private Context context;
    private List<Episode> data;

    public EpisodeAdapter(Context context,List<Episode> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Episode getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_episode,null);
            holder = new ViewHolder();
            holder.textEpisode = convertView.findViewById(R.id.text_episode);

            holder.textEpisode.setText(getItem(position).getEpisode());
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();

            holder.textEpisode.setText(getItem(position).getEpisode());

            convertView.setTag(holder);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo...........
                Intent intent = PlayActivity.createIntent(context);

                intent.putExtra("Play_HREF",getItem(position).getHref());

                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class ViewHolder{

        TextView textEpisode;

    }

}
