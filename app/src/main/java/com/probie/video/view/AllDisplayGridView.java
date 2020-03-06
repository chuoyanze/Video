package com.probie.video.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 14:42
 * @description：
 * @modified By：
 * @version: $
 */
public class AllDisplayGridView extends GridView {

    public AllDisplayGridView(Context context) {
        super(context);
    }

    public AllDisplayGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AllDisplayGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
