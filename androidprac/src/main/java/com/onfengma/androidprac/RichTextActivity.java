package com.onfengma.androidprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.onfengma.androidprac.views.tichText.OnImageClickListener;
import com.onfengma.androidprac.views.tichText.RichText;

import java.util.List;

public class RichTextActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text);

        textView = (TextView) findViewById(R.id.text);
        String html = "SSSSSS<img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3483188741,1097192419&fm=58\" " +
                "width=\"80\" " +
                "height=\"150\">SFFDFFDF<br>" +
                "<img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=4102287482,798839369&fm=58\" \"width=\"200\" \"height=\"400\" >";
        RichText.from(html).autoFix(false).imageClick(new OnImageClickListener() {
            @Override
            public void imageClicked(List<String> imageUrls, int position) {
                System.out.println("------------" + imageUrls.get(position));
            }
        }).into(textView);
    }
}
