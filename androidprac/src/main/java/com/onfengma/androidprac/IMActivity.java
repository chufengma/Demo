package com.onfengma.androidprac;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;
import com.onfengma.androidprac.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class IMActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DemoAdapter demoAdapter;

    List<String> messages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        demoAdapter = new DemoAdapter();
        recyclerView.setAdapter(demoAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                Logger.i(" last item is : " + lastItem + "," + (demoAdapter.getItemCount() - 1));
                if (lastItem == demoAdapter.getItemCount() - 1) {
                    loadNextMessages();
                }
            }
        });

        for (int i=0;i<20;i++) {
            messages.add("This is message type 1: " + i);
        }
        demoAdapter.addDate(messages);

        layoutManager.setReverseLayout(true);
    }

    private void loadNextMessages() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++) {
                    messages.add("This is message type LOADMORE: " + i);
                }
                demoAdapter.addDate(messages);
            }
        }, 3000);
    }

    public static int TYPE_LOADING = 1;
    public static int TYPE_TEXT = 2;

    class DemoLinearLayoutManager extends LinearLayoutManager {

        public DemoLinearLayoutManager(Context context) {
            super(context);
        }
    }

    class DemoAdapter extends RecyclerView.Adapter {

        private List<String> messages = new ArrayList<>();

        private void addDate(List<String> messages) {
            this.messages.clear();
            if (messages != null) {
                this.messages.addAll(messages);
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == TYPE_LOADING ? new DemoViewHolder(parent.getContext()) : new TextViewHolder(parent.getContext());
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TextViewHolder) {
                ((TextViewHolder) holder).setText(messages.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return messages.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            return messages.size() >= 10 && position == getItemCount() - 1 ? TYPE_LOADING : TYPE_TEXT;
        }
    }

    class DemoViewHolder extends RecyclerView.ViewHolder {
        public DemoViewHolder(Context context) {
            super(View.inflate(context, R.layout.im_loading_item, null));
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public TextViewHolder(Context context) {
            super(View.inflate(context, R.layout.im_text_item, null));
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        public void setText(String text) {
            this.textView.setText(text);
        }
    }

}
