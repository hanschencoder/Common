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

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
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

    protected void showFragment(@IdRes int containerViewId, Class<? extends Fragment> clz) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        try {
            Fragment f;
            if ((f = fm.findFragmentByTag(clz.getName())) == null) {
                f = clz.newInstance();
                ft.add(containerViewId, f, clz.getName());
            }
            ft.show(f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showFragment(@IdRes int containerViewId,
                                Class<? extends Fragment> clz,
                                @AnimatorRes int enterAnim,
                                @AnimatorRes int exitAnim) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(enterAnim, exitAnim);
        try {
            Fragment f;
            if ((f = fm.findFragmentByTag(clz.getName())) == null) {
                f = clz.newInstance();
                ft.add(containerViewId, f, clz.getName());
            }
            ft.show(f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addFragment(@IdRes int containerViewId, Class<? extends Fragment> clz) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        try {
            Fragment f = clz.newInstance();
            ft.add(containerViewId, f, clz.getName()).show(f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addFragment(@IdRes int containerViewId,
                               Class<? extends Fragment> clz,
                               @AnimatorRes int enterAnim,
                               @AnimatorRes int exitAnim) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(enterAnim, exitAnim);
        try {
            Fragment f = clz.newInstance();
            ft.add(containerViewId, f, clz.getName()).show(f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void replaceFragment(@IdRes int containerViewId, Class<? extends Fragment> clz) {
        replaceFragment(containerViewId, clz, null);
    }

    protected void replaceFragment(@IdRes int containerViewId, Class<? extends Fragment> clz, Bundle args) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        try {
            Fragment f;
            if ((f = fm.findFragmentByTag(clz.getName())) == null) {
                f = clz.newInstance();
            }
            if (args != null) {
                f.setArguments(args);
            }
            ft.replace(containerViewId, f, clz.getName()).show(f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   Class<? extends Fragment> clz,
                                   @AnimatorRes int enterAnim,
                                   @AnimatorRes int exitAnim) {
        replaceFragment(containerViewId, clz, null, enterAnim, exitAnim);
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   Class<? extends Fragment> clz,
                                   Bundle args,
                                   @AnimatorRes int enterAnim,
                                   @AnimatorRes int exitAnim) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(enterAnim, exitAnim);
        try {
            Fragment f;
            if ((f = fm.findFragmentByTag(clz.getName())) == null) {
                f = clz.newInstance();
            }
            if (args != null) {
                f.setArguments(args);
            }
            ft.replace(containerViewId, f, clz.getName()).show(f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void removeFragment(@IdRes int containerViewId) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentById(containerViewId);
        if (f != null) {
            ft.remove(f).commit();
        }
    }

    protected void removeFragment(Class<? extends Fragment> clz) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentByTag(clz.getName());
        if (f != null) {
            ft.remove(f).commit();
        }
    }

    protected void hideFragment(@IdRes int containerViewId) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentById(containerViewId);
        if (f != null) {
            ft.hide(f).commit();
        }
    }

    protected void hideFragment(Class<? extends Fragment> clz) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentByTag(clz.getName());
        if (f != null) {
            ft.hide(f).commit();
        }
    }

    protected Fragment getFragmentById(int containerViewId) {
        FragmentManager fm = getFragmentManager();
        return fm.findFragmentById(containerViewId);
    }
}
