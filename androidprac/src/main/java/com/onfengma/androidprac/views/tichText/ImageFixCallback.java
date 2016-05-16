package com.onfengma.androidprac.views.tichText;



public interface ImageFixCallback {
    /**
     * 修复图片尺寸的方法
     *
     * @param holder     ImageHolder对象
     * @param imageReady 图片是否已经加载完毕
     */
    void onFix(ImageHolder holder, boolean imageReady);
}