package com.onfengma.androidprac.views.tichText;


import java.util.List;

public interface OnImageClickListener {
    /**
     * 图片被点击后的回调方法
     *
     * @param imageUrls 本篇富文本内容里的全部图片
     * @param position  点击处图片在imageUrls中的位置
     */
    void imageClicked(List<String> imageUrls, int position);
}