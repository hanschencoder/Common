/*
 * Copyright 2016 Hans Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package site.hanschen.common.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseActivity extends AppCompatActivity {

    protected Context        mContext;
    private   Handler        mMainHandler;
    private   MaterialDialog mWaitingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    private boolean isWaitingDialogShowing() {
        return mWaitingDialog != null && mWaitingDialog.isShowing();
    }

    public void showWaitingDialog() {
        if (!isWaitingDialogShowing()) {
            mWaitingDialog = new MaterialDialog.Builder(mContext).progress(true, 0).progressIndeterminateStyle(true).build();
            mWaitingDialog.setCancelable(false);
            mWaitingDialog.show();
        }
    }

    public void dismissWaitingDialog() {
        if (isWaitingDialogShowing()) {
            mWaitingDialog.dismiss();
        }
    }

    protected Handler getMainHandler() {
        if (mMainHandler == null) {
            mMainHandler = new Handler(Looper.getMainLooper());
        }
        return mMainHandler;
    }
}
