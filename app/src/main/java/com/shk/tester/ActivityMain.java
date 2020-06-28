package com.shk.tester;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.linkto.cocosbcxinvoker.dapp_client.listener.CocosListener;
import com.linkto.cocosbcxinvoker.dapp_client.manage.DpInvokerManager;
import com.linkto.cocosbcxinvoker.dapp_client.model.Authorize;

public class ActivityMain extends Activity {
    private static final int REQUEST_CODE_PERMISSION = 1;

    private TextView mHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHint = findViewById(R.id.tv_hint);

        findViewById(R.id.btn_test_a).setOnClickListener((view) -> {
            Authorize authorize = new Authorize();
            authorize.setDappName("a b c+d+"); // dapp name
            authorize.setDappIcon("https://logoicon.png");// dapp icon
            authorize.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");// action ID (uuid)
            authorize.setExpired(1537157808L); // Expired time
            authorize.setDesc("desc content"); // Description
            DpInvokerManager.getInstance().authorize(this, authorize, new CocosListener() {
                @Override
                public void onSuccess(String data) {
                }

                @Override
                public void onError(String data) {
                }

                @Override
                public void onCancel(String data) {
                }
            });
        });

        findViewById(R.id.btn_test_b).setOnClickListener((view) -> {
        });

        findViewById(R.id.btn_test_c).setOnClickListener((view) -> {
        });

        findViewById(R.id.btn_clear).setOnClickListener((view) -> {
            clearHint();
        });
    }

    protected void appendHint(String hint) {
        String text = mHint.getText().toString();
        if (text.equals("")) {
            mHint.setText(hint);
        } else {
            mHint.setText(String.format("%s\n%s", text, hint));
        }
    }

    protected void clearHint() {
        mHint.setText("");
    }

    protected void setHint(String hint) {
        mHint.setText(hint);
    }
}