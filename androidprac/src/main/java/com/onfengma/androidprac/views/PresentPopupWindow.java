package com.onfengma.androidprac.views;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayout;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.onfengma.androidprac.R;
import com.onfengma.androidprac.utils.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chuyifeng on 2015/10/27.
 */
public class PresentPopupWindow extends Dialog {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    public PresentPopupWindow(Context context) {
        super(context, R.style.PresentDialog);
        init(context);
    }

    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.present_popup_layout, null);
        ButterKnife.bind(this, view);
        // params
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM | Gravity.START;
        getWindow().setAttributes(lp);
        setContentView(view);

        SparseArray<ArrayList<Present>> presentsList = new SparseArray<>();
        ArrayList<Present> presents = null;
        for(int i= 0; i< 15;i++) {
            if (i % 8 == 0) {
                presents = new ArrayList();
                presentsList.append(i/8, presents);
            }
            Present present = new Present();
            present.setName("Fengma" + i);
            presents.add(present);
        }
        viewPager.setAdapter(new PresentPagerAdapter(presentsList));
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.35f;
        lp.width = getContext().getResources().getDisplayMetrics().widthPixels;
        getWindow().setAttributes(lp);
    }

    private class PresentPagerAdapter extends PagerAdapter {

        private SparseArray<ArrayList<Present>> presentsList;

        public PresentPagerAdapter(SparseArray<ArrayList<Present>> presentsList) {
            this.presentsList = presentsList;
        }

        @Override
        public int getCount() {
            return presentsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            GridLayout view = (GridLayout) inflater.inflate(R.layout.present_layout, container, false);
            ArrayList<Present> presents = presentsList.get(position);

            for(int i = 0; i< presents.size() ;i++) {
                View item = inflater.inflate(R.layout.present_item, container, false);
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                ((TextView)(item.findViewById(R.id.text))).setText(presents.get(i).getName());
                view.addView(item ,lp);
            }
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            container.addView(view, lp);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    private class Present {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private View floagView;

    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            if (floagView == null) {
                floagView = view;
            } else {
                if (floagView == view) {
                    Logger.i("position:--------:" + position);
                }
            }

            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}
