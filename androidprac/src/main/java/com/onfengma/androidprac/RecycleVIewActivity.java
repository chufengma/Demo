package com.onfengma.androidprac;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class RecycleVIewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ColorDrawable toolBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
//        recyclerView = (RecyclerView) findViewById(R.id.recycler);
//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(new DemoAdapter());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolBarColor = new ColorDrawable(Color.TRANSPARENT);
        getSupportActionBar().setBackgroundDrawable(toolBarColor);

        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler2);
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView2.setAdapter(new DemoAdapter());

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
        Palette palette = Palette.from(bitmap).generate();
        getSupportActionBar().setBackgroundDrawable(toolBarColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycle_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
            cardView.setRadius(13);
            cardView.setCardElevation(20);
            ViewHolder viewHolder = new ViewHolder(cardView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewHolder.imageView.getLayoutParams();
            lp.height = new Random().nextInt(getResources().getDisplayMetrics().widthPixels / 2);
            viewHolder.imageView.setLayoutParams(lp);
        }

        @Override
        public int getItemCount() {
            return 14;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image);
            }

        }

    }
}
