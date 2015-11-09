package com.onfengma.androidprac;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, RecyclerViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
        // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
        //getWindow().setExitTransition(new Explode());
        // getWindow().setEnterTransition(new Explode());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recycler.setAdapter(new DemoAdapter());
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    class DemoViewHolder extends RecyclerView.ViewHolder {

        View header;
        TextView textView;

        public DemoViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            header = itemView.findViewById(R.id.header);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

    }

    class DemoAdapter extends RecyclerView.Adapter<DemoViewHolder> implements View.OnClickListener {

        Random random = new Random(255);

        @Override
        public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.recycler_view_layout_item, parent, false);
            view.setOnClickListener(this);
            return new DemoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DemoViewHolder holder, int position) {
            int a = random.nextInt();
            int r = random.nextInt();
            int g = random.nextInt();
            int b = random.nextInt();
            holder.header.setBackgroundColor(Color.argb(a, r, g ,b));
            holder.textView.setText(System.currentTimeMillis() + ":" + position);
        }

        @Override
        public int getItemCount() {
            return 35;
        }

        @Override
        public void onClick(View v) {
            DemoViewHolder holder = (DemoViewHolder) v.getTag();
            // share element transition about activity
//            Intent intent = new Intent(RecyclerViewActivity.this, ItemDetailActivity.class);
//            Pair<View, String> p1 = Pair.create(holder.header, "trans_header");
//            Pair<View, String> p2 = Pair.create((View)holder.textView, "trans_text");
//            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(RecyclerViewActivity.this, p1, p2);
//            startActivity(intent, optionsCompat.toBundle());

            // share element transition about fragment
            DemoFragment demoFragment = new DemoFragment();
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.addTransition(new ChangeTransform());
            transitionSet.addTransition(new ChangeBounds());
            demoFragment.setSharedElementEnterTransition(transitionSet);
            demoFragment.setSharedElementReturnTransition(transitionSet);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, demoFragment)
                    .addToBackStack("transaction")
                    .addSharedElement(holder.header, "trans_header")
                    .addSharedElement(holder.textView, "trans_text");
            // Apply the transaction
            ft.commit();

        }
    }

    public static class DemoFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_item_detail, container, false);
        }
    }
}
