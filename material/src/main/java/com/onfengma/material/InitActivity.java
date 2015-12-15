package com.onfengma.material;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InitActivity extends AppCompatActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.content)
    CoordinatorLayout content;
    @Bind(R.id.coll)
    CollapsingToolbarLayout coll;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);
        coll.setTitle("Material !!!");
        recycler.setAdapter(new MyAdapter());
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) imageView.getLayoutParams();
        lp.setBehavior(new MyBehavier());
        imageView.setLayoutParams(lp);

        CoordinatorLayout.LayoutParams lp2 = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        lp2.setBehavior(new MyBehavier2());
        fab.setLayoutParams(lp2);
    }

    class MyBehavier extends CoordinatorLayout.Behavior<View> {

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
            return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        }

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
            float tanxY = (child.getTranslationY() + dy) * 1.0f;
            child.setTranslationY(tanxY);
        }
    }

    class MyBehavier2 extends CoordinatorLayout.Behavior<View> {

        boolean doing;
        boolean hidedoing;

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
            return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        }

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
            if (dy > 0) {
                if (doing) {
                    return;
                }
                doing = true;
                ViewCompat.animate(child).translationY(child.getContext().getResources().getDisplayMetrics().heightPixels)
                        .setInterpolator(new AccelerateInterpolator()).setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        doing = false;
                    }
                })
                        .start();
            } else if (dy < 0) {
                if (hidedoing) {
                    return;
                }
                hidedoing = true;
                ViewCompat.animate(child).translationY(0).setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        hidedoing = false;
                    }
                }).setInterpolator(new DecelerateInterpolator())
                        .start();
            }
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private static final int TYPE_NORMAL = 1;
        private static final int TYPE_HEADER = 2;

        @Override
        public int getItemViewType(int position) {
            return TYPE_NORMAL;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_recycler_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageView.setImageResource(R.drawable.bg_4);
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
