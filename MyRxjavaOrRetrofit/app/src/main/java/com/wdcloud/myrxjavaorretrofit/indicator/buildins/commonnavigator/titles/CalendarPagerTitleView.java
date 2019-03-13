package com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import com.wdcloud.myrxjavaorretrofit.indicator.buildins.UIUtil;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import com.wdcloud.myrxjavaorretrofit.util.ScreenUtil;


/**
 * 类似今日头条切换效果的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class CalendarPagerTitleView extends View implements IMeasurablePagerTitleView {
    private String mText;
    private String mBottomText;
    private int mTextColor;
    private int bottomTextColor;
    private int mClipColor;
    private boolean mLeftToRight;
    private float mClipPercent;

    private Paint mPaint;

    private Paint mBottomPaint;

    private Paint mBottomLinePaint;

    private Rect mTextBounds = new Rect();
    private float textWidth;
    private float bottomTextWidth;
    private float offsetBottomX;
    private boolean hasBottomLine;
    private int mBottomLineColor;
    private float halfWidth;
    private int bottomLineHeight;
    private int bottomLineTopPadding;


    public CalendarPagerTitleView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        int textSize = UIUtil.dip2px(context, 16);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(textSize);

        bottomLineHeight = ScreenUtil.dp2px(2);

        bottomLineTopPadding = ScreenUtil.dp2px(4);

//        int padding = UIUtil.dip2px(context, 10);
//        setPadding(padding, 0, padding, 0);
    }

    public void setHasBottomLine(boolean hasBottomLine){
        this.hasBottomLine =hasBottomLine;
        if(this.hasBottomLine){
            mBottomLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//            mBottomLinePaint.setColor(Color.parseColor("#FFF23030"));//先在这里写死了。。主要是没时间整这些花花肠子
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        ViewGroup mViewGroup = (ViewGroup) getParent();
        measureTextBounds();
        int pWidth = ScreenUtil.getScreenWidth();
        int padding = (int)(pWidth / 7 - textWidth) / 2;
        setPadding(padding, 0, padding, 0);

        offsetBottomX = (bottomTextWidth - textWidth)/2;

        halfWidth = textWidth/2;

        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = size;
        switch (mode) {
            case MeasureSpec.AT_MOST:
//                int width = mTextBounds.width() + getPaddingLeft() + getPaddingRight();
                int width = (int)textWidth + getPaddingLeft() + getPaddingRight();
                result = Math.min(width, size);
                break;
            case MeasureSpec.UNSPECIFIED:
//                result = mTextBounds.width() + getPaddingLeft() + getPaddingRight();
                result = (int) textWidth + getPaddingLeft() + getPaddingRight();
                break;
            default:
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = size;
        switch (mode) {
            case MeasureSpec.AT_MOST:
                int height = mTextBounds.height() + getPaddingTop() + getPaddingBottom();
                result = Math.min(height, size);
                break;
            case MeasureSpec.UNSPECIFIED:
                result = mTextBounds.height() + getPaddingTop() + getPaddingBottom();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        int x = (getWidth() - mTextBounds.width()) / 2;
        int x = (int)(getWidth() - textWidth) / 2;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int y = (int) ((getHeight() - fontMetrics.bottom - fontMetrics.top) / 2);


        if(!hasBottomLine && mBottomText == null){//普通仅有数字
            // 画底层
            mPaint.setColor(mTextColor);
            canvas.drawText(mText, x, y, mPaint);
        }

        if(mBottomText != null && !hasBottomLine){//今天且今天没直播
            // 画底层
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(ScreenUtil.dp2px(22));
            canvas.drawText(mText, x, y-mTextBounds.height()/2.3f, mPaint);

            mBottomPaint.setColor(bottomTextColor);
//            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
            canvas.drawText(mBottomText, x-offsetBottomX, y+mTextBounds.height()/2.3f, mBottomPaint);

//            canvas.drawCircle(x, y, );
        }

        if(hasBottomLine && mBottomText == null){//（不是今天，且有直播）目前就先这样写了，这里注意hasBottomLine与mBottomText不能同时为真
            // 画底层
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(ScreenUtil.dp2px(22));
//            canvas.drawText(mText, x, y-mTextBounds.height()/2.3f, mPaint);
            canvas.drawText(mText, x, y, mPaint);

            mBottomLinePaint.setColor(mBottomLineColor);
//            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
//            canvas.drawText(mBottomText, x-offsetBottomX, y+mTextBounds.height()/2.3f, mBottomPaint);
//            canvas.drawRoundRect(x, y+mTextBounds.height()/2.3f, x + offsetBottomX, y+mTextBounds.height()/2.6f,10,10,mBottomLinePaint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//下面的api需要21及以上的api支持
                canvas.drawRoundRect(x+halfWidth/3, y + 2*bottomLineTopPadding, x + 5 * halfWidth / 3, y + bottomLineHeight +  2*bottomLineTopPadding, bottomLineHeight / 2, bottomLineHeight / 2, mBottomLinePaint);
            }else{
                canvas.drawRect(x+halfWidth/3 , y +  2*bottomLineTopPadding, x + 5 * halfWidth / 3, y + bottomLineHeight +  2*bottomLineTopPadding, mBottomLinePaint);
            }
        }

        if(hasBottomLine && !TextUtils.isEmpty(mBottomText)){//今天且有直播
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(ScreenUtil.dp2px(22));
            canvas.drawText(mText, x, y-mTextBounds.height()/2.3f, mPaint);

            mBottomPaint.setColor(bottomTextColor);
//            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
            canvas.drawText(mBottomText, x-offsetBottomX, y+mTextBounds.height()/4, mBottomPaint);

            mBottomLinePaint.setColor(mBottomLineColor);
//            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
//            canvas.drawText(mBottomText, x-offsetBottomX, y+mTextBounds.height()/2.3f, mBottomPaint);
//            canvas.drawRoundRect(x, y+mTextBounds.height()/2.3f, x + offsetBottomX, y+mTextBounds.height()/2.6f,10,10,mBottomLinePaint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//下面的api需要21及以上的api支持
                canvas.drawRoundRect(x+halfWidth/3, y + 2*bottomLineTopPadding, x + 5 * halfWidth / 3, y + bottomLineHeight + 2*bottomLineTopPadding, bottomLineHeight / 2, bottomLineHeight / 2, mBottomLinePaint);
            }else{
                canvas.drawRect(x +halfWidth/3, y + 2*bottomLineTopPadding, x + 5 * halfWidth / 3, y + bottomLineHeight + 2*bottomLineTopPadding, mBottomLinePaint);
            }
        }


        // 画clip层
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        if (mLeftToRight) {
            canvas.clipRect(0, 0, getWidth() * mClipPercent, getHeight());
        } else {
            canvas.clipRect(getWidth() * (1 - mClipPercent), 0, getWidth(), getHeight());
        }

        if(!hasBottomLine && mBottomText == null){
            mPaint.setColor(mClipColor);
            canvas.drawText(mText, x, y, mPaint);
        }

        if(mBottomText != null && !hasBottomLine){
            mPaint.setColor(mClipColor);
            mPaint.setTextSize(ScreenUtil.dp2px(22));
            canvas.drawText(mText, x, y - mTextBounds.height() / 2.3f, mPaint);
        }

        if(hasBottomLine && mBottomText == null){
            mPaint.setColor(mClipColor);
            mPaint.setTextSize(ScreenUtil.dp2px(22));
            canvas.drawText(mText, x, y, mPaint);
        }

        if(hasBottomLine && !TextUtils.isEmpty(mBottomText)){
            mPaint.setColor(mClipColor);
            mPaint.setTextSize(ScreenUtil.dp2px(22));
            canvas.drawText(mText, x, y-mTextBounds.height()/2.3f, mPaint);

            mBottomPaint.setColor(bottomTextColor);
//            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
            canvas.drawText(mBottomText, x-offsetBottomX, y+mTextBounds.height()/4, mBottomPaint);

            mBottomLinePaint.setColor(mBottomLineColor);
//            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
//            canvas.drawText(mBottomText, x-offsetBottomX, y+mTextBounds.height()/2.3f, mBottomPaint);
//            canvas.drawRoundRect(x, y+mTextBounds.height()/2.3f, x + offsetBottomX, y+mTextBounds.height()/2.6f,10,10,mBottomLinePaint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//下面的api需要21及以上的api支持
                canvas.drawRoundRect(x +halfWidth/3, y + 2*bottomLineTopPadding, x + 5 * halfWidth / 3, y + bottomLineHeight + 2*bottomLineTopPadding, bottomLineHeight / 2, bottomLineHeight / 2, mBottomLinePaint);
            }else{
                canvas.drawRect(x +halfWidth/3, y + 2*bottomLineTopPadding, x + 5 * halfWidth / 3, y + bottomLineHeight + 2*bottomLineTopPadding, mBottomLinePaint);
            }
        }

        canvas.restore();
    }

    @Override
    public void onSelected(int index, int totalCount) {
    }

    @Override
    public void onDeselected(int index, int totalCount) {
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        mLeftToRight = !leftToRight;
        mClipPercent = 1.0f - leavePercent;
        invalidate();
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        mLeftToRight = leftToRight;
        mClipPercent = enterPercent;
        invalidate();
    }

    private void measureTextBounds() {
//        mPaint.getTextBounds(mText, 0, mText == null ? 0 : mText.length(), mTextBounds);
        mPaint.getTextBounds(mText, 0, mText == null ? 0 : mText.length(), mTextBounds);
        textWidth = mPaint.measureText(mText);

        if(mBottomText != null){
            // 画底层
            bottomTextWidth = mBottomPaint.measureText(mBottomText);
        }

//        if(mBottomText != null){
////            mPaint.setTextSize(ScreenUtil.dp2px(10));
//            setTextSize(ScreenUtil.dp2px(10));
//            bottomTextWidth = mPaint.measureText(mBottomText);
//        }

//        Paint.FontMetrics mFontMetrics = mPaint.getFontMetrics();
//
//                Log.d("Aige", "ascent：" + mFontMetrics.ascent);
//                Log.d("Aige", "top：" + mFontMetrics.top);
//                Log.d("Aige", "leading：" + mFontMetrics.leading);
//                Log.d("Aige", "descent：" + mFontMetrics.descent);
//                Log.d("Aige", "bottom：" + mFontMetrics.bottom);

    }


    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        requestLayout();
    }

    public float getTextSize() {
        return mPaint.getTextSize();
    }

    public void setTextSize(float textSize) {
        mPaint.setTextSize(textSize);
        requestLayout();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
        invalidate();
    }

    public void setBottomTextColor(int bottomTextColor){
        this.bottomTextColor = bottomTextColor;
        invalidate();
    }

    public void setBottomLineColor(int bottomLineColor){
        this.mBottomLineColor = bottomLineColor;
        invalidate();
    }

    public void setBottomText(String bottomText){
        this.mBottomText = bottomText;
        if(this.mBottomText != null) {
            mBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBottomPaint.setTextSize(ScreenUtil.dp2px(10));
        }

        invalidate();
    }

    public int getClipColor() {
        return mClipColor;
    }

    public void setClipColor(int clipColor) {
        mClipColor = clipColor;
        invalidate();
    }

    @Override
    public int getContentLeft() {
//        int contentWidth = mTextBounds.width();
        int contentWidth = (int)textWidth;
        return getLeft() + getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 - contentHeight / 2);
    }

    @Override
    public int getContentRight() {
//        int contentWidth = mTextBounds.width();
        int contentWidth = (int)textWidth;
        return getLeft() + getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 + contentHeight / 2);
    }
}
