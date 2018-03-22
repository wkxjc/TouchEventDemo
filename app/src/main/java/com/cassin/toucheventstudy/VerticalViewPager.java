package com.cassin.toucheventstudy;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        super(context);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();
        event.setLocation((event.getY() / height) * width, (event.getX() / width) * height);
        return event;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)));
    }

    private float yDown = 0;//记录手指按下时的y坐标值
    private boolean isDown = false;//记录是否是在ScrollView顶部下拉

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Activity activity = (Activity) getContext();
        ScrollView scrollView = activity.findViewById(R.id.sv_detail);
        if(scrollView.getScrollY() == 0){//ScrollView在顶部时判断手势
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    isDown = false;//初始化isDown
                    yDown = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if((event.getY() - yDown) > 0){
                        isDown = true;//如果手指移动时的y坐标值大于手指按下时的坐标值，表示在下拉
                    }
                    break;
            }
            if(isDown){
                return true;//如果是在ScrollView顶部下拉，ViewPager拦截点击事件
            }else{
                return super.onInterceptTouchEvent(swapTouchEvent(MotionEvent.obtain(event)));
            }
        }
        return super.onInterceptTouchEvent(swapTouchEvent(MotionEvent.obtain(event)));
    }
}

