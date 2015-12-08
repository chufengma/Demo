package com.onfengma.androidprac;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.onfengma.androidprac.utils.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DrawerLayoutActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.recyler)
    RecyclerView recyler;
    @Bind(R.id.my_awesome_toolbar)
    Toolbar myAwesomeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);
        setSupportActionBar(myAwesomeToolbar);
//
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        recyler.setAdapter(new MyAdapter());
        recyler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyler.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int first = lm.findFirstCompletelyVisibleItemPosition();
                if (dy < 0) {
                    showToolBar();
                }
                if (dy > 0) {
                    hideToolBar();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        // drawerLayout.setStatusBarBackgroundColor(Color.TRANSPARENT);
        // drawerLayout.setStatusBarBackgroundColor(Color.TRANSPARENT);
        // 注意setStatusBarBackgroundColor方法需要你将fitsSystemWindows设置为true才会生效
        // DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // drawerLayout.setStatusBarBackgroundColor(Color.TRANSPARENT);
    }

    private void hideToolBar() {
//        if (myAwesomeToolbar.getVisibility() == View.GONE) {
//            return;
//        }
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(myAwesomeToolbar, "alpha", 1f, 0f).setDuration(500);
        // ValueAnimator ani2 = ValueAnimator.ofInt(0, ViewUtils.dipToPixels(80, getResources().getDisplayMetrics()));
//        ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) myAwesomeToolbar.getLayoutParams();
//                lp.topMargin = -1 * (int) animation.getAnimatedValue();
//            }
//        });
//        ani1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                myAwesomeToolbar.setVisibility(View.GONE);
//            }
//        });
        animatorSet.play(ani1);
        animatorSet.start();
    }

    private void showToolBar() {
//        if (myAwesomeToolbar.getVisibility() == View.VISIBLE) {
//            return;
//        }
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(myAwesomeToolbar, "alpha", 0f, 1f).setDuration(500);
//        ani1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                myAwesomeToolbar.setVisibility(View.VISIBLE);
//            }
//        });
        // ValueAnimator ani2 = ValueAnimator.ofInt(ViewUtils.dipToPixels(80, getResources().getDisplayMetrics()), 0);
//        ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) myAwesomeToolbar.getLayoutParams();
//                lp.topMargin = -1 * (int) animation.getAnimatedValue();
//            }
//        });
        animatorSet.play(ani1);
        animatorSet.start();
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_recycler_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageView.setImageResource(position % 3 == 0 ? R.drawable.bg_1 : position % 2 == 0 ? R.drawable.bg_2 : R.drawable.bg_3);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
