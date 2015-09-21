package com.onfengma.androidprac.MVP;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.onfengma.androidprac.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVPDemoActivity extends AppCompatActivity implements ILoginView {

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.result)
    TextView result;

    ProgressDialog progressDialog;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpdemo);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.login)
    void onLoginClick() {
        presenter.login(name.getText().toString(), password.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mvpdemo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess(String msg) {
        result.setText(msg);
    }

    @Override
    public void onFailed(String reason) {
        result.setText(reason);
    }
}
