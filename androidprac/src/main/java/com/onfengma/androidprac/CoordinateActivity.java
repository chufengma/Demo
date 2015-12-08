package com.onfengma.androidprac;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoordinateActivity extends AppCompatActivity {

    @Bind(R.id.normal)
    TextView normal;
    @Bind(R.id.sacle)
    TextView sacle;
    @Bind(R.id.transition)
    TextView transition;
    @Bind(R.id.rotate)
    TextView rotate;
    @Bind(R.id.scroll)
    TextView scroll;
    @Bind(R.id.recyler)
    RecyclerView recyclerView;

    private String[] testCases = new String[]{"Normal", "Scale", "Transition", "Rotate", "Scroll"};
    private TextView[] viewCases;

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        ButterKnife.bind(this);

        initRecyler();

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewCases = new TextView[]{normal, sacle, transition, rotate, scroll};
                normal.setText(getDesc(normal));
                getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private void initRecyler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MyAdapter());
    }

    public void onClick(View view) {
        Toast.makeText(this, "OnClick !!!", Toast.LENGTH_SHORT).show();
    }

    private static String getDesc(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("top:" + view.getTop() + ",left:" + view.getLeft() + ",right" + view.getRight() + ",buttom" + view.getBottom() + "\n");
        sb.append("x:" + view.getX() + ",y:" + view.getY() + "\n");
        sb.append("width:" + view.getWidth() + ",height:" + view.getHeight() + "\n");
        sb.append("scaleX:" + view.getScaleX() + ",scaleY:" + view.getScaleY() + "\n");
        sb.append("transX:" + view.getTranslationX() + ",transY:" + view.getTranslationY() + ",transZ" + view.getTranslationZ() +  " \n");
        sb.append("rotationX:" + view.getRotationX() + ",rotationY:" + view.getRotationY() + ",rotate:" + view.getRotation() + "\n");
        sb.append("scrollX:" + view.getScrollX() + ",scrollY:" + view.getScrollY() + "\n");
        return sb.toString();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Button button = new Button(parent.getContext());
            MyViewHolder myViewHolder = new MyViewHolder(button);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.button.setText(testCases[position]);
            holder.button.setTag(position);
            holder.button.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return testCases.length;
        }

        @Override
        public void onClick(View v) {
            final int index = (int) v.getTag();
            viewCases[currentIndex].setVisibility(View.GONE);
            viewCases[index].setVisibility(View.VISIBLE);
            viewCases[index].setText(getDesc(viewCases[index]));

            if (viewCases[index] == rotate) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewCases[index], "rotation", 0, 45);
                objectAnimator.setDuration(5000);
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        viewCases[index].setText(getDesc(viewCases[index]));
                    }
                });
                objectAnimator.start();
            }

            currentIndex = index;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        Button button;

        public MyViewHolder(View itemView) {
            super(itemView);
            button = (Button)itemView;
        }

    }

}
