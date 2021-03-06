package com.ms.mythemelibrary.lib;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ms.mythemelibrary.lib.setter.DrawerLayoutStatusBarBackgroundSetter;
import com.ms.mythemelibrary.lib.setter.NavigationViewItemColor;
import com.ms.mythemelibrary.lib.setter.TextColorSetter;
import com.ms.mythemelibrary.lib.setter.ToolbarPopuThemeSetter;
import com.ms.mythemelibrary.lib.setter.ViewBackgroundColorSetter;
import com.ms.mythemelibrary.lib.setter.ViewBackgroundDrawableSetter;
import com.ms.mythemelibrary.lib.setter.ViewSetter;

import java.util.HashSet;
import java.util.Set;

/**
 * 主题切换控制类
 *
 * @author mrsimple
 */
public final class Colorful {
    /**
     * Colorful Builder
     */
    Builder mBuilder;

    /**
     * private constructor
     *
     * @param builder
     */
    private Colorful(Builder builder) {
        mBuilder = builder;
    }

    /**
     * 设置新的主题
     *
     * @param newTheme
     */
    public void setTheme(int newTheme) {
        mBuilder.setTheme(newTheme);
    }

    /**
     * 构建Colorful的Builder对象
     *
     * @author mrsimple
     */
    public static class Builder {
        /**
         * 存储了视图和属性资源id的关系表
         */
        Set<ViewSetter> mElements = new HashSet<ViewSetter>();
        /**
         * 目标Activity
         */
        Activity mActivity;

        /**
         * @param activity
         */
        public Builder(Activity activity) {
            mActivity = activity;
        }

        /**
         * @param fragment
         */
        public Builder(Fragment fragment) {
            mActivity = fragment.getActivity();
        }

        private View findViewById(int viewId) {
            return mActivity.findViewById(viewId);
        }

        /**
         * 将View id与存储该view背景色的属性进行绑定
         *
         * @param viewId  控件id
         * @param colorId 颜色属性id
         * @return
         */
        public Builder backgroundColor(int viewId, int colorId) {
            mElements.add(new ViewBackgroundColorSetter(findViewById(viewId),
                    colorId));
            return this;
        }

        public Builder backgroundColor(View targetView, int colorId) {
            mElements.add(new ViewBackgroundColorSetter(
                    targetView,
                    colorId)
            );
            return this;
        }

        /**
         * 将View id与存储该view背景Drawable的属性进行绑定
         *
         * @param viewId     控件id
         * @param drawableId Drawable属性id
         * @return
         */
        public Builder backgroundDrawable(int viewId, int drawableId) {
            mElements.add(new ViewBackgroundDrawableSetter(
                    findViewById(viewId), drawableId));
            return this;
        }

        /**
         * 将View 与存储该view背景Drawable的属性进行绑定
         *
         * @param targetView 控件
         * @param drawableId Drawable属性id
         * @return
         */
        public Builder backgroundDrawable(View targetView, int drawableId) {
            mElements.add(new ViewBackgroundDrawableSetter(
                    targetView, drawableId)
            );
            return this;
        }

        /**
         * 将TextView id与存储该TextView文本颜色的属性进行绑定
         *
         * @param viewId  TextView或者TextView子类控件的id
         * @param colorId 颜色属性id
         * @return
         */
        public Builder textColor(int viewId, int colorId) {
            TextView textView = (TextView) findViewById(viewId);
            mElements.add(new TextColorSetter(textView, colorId));
            return this;
        }
        public Builder textColor(TextView textView, int colorId) {
            mElements.add(new TextColorSetter(textView, colorId));
            return this;
        }


        public Builder navigationViewItemColor(int viewId, int colorId) {
            NavigationView navigationView = (NavigationView) findViewById(viewId);
            mElements.add(new NavigationViewItemColor(navigationView, colorId));
            return this;
        }

        /**
         * 设置 Toolbar PopuTheme
         *
         * @param viewId
         * @param popuThemeId
         * @return
         */
        public Builder toolbarPopuThemeSetter(int viewId, int popuThemeId) {

            Toolbar toolbar = (Toolbar) findViewById(viewId);
            mElements.add(new ToolbarPopuThemeSetter(toolbar, popuThemeId));
            return this;
        }

        /**
         * drawerLayout 状态栏颜色
         *
         * @param viewId
         * @param popuThemeId
         * @return
         */
        public Builder drawerLayoutStatusBarBackgroundSetter(int viewId, int popuThemeId) {

            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(viewId);
            mElements.add(new DrawerLayoutStatusBarBackgroundSetter(mDrawerLayout, popuThemeId));
            return this;
        }


        /**
         * 用户手动构造并且添加Setter
         *
         * @param setter 用户自定义的Setter
         * @return
         */
        public Builder setter(ViewSetter setter) {
            mElements.add(setter);
            return this;
        }

        /**
         * 设置新的主题
         *
         * @param newTheme
         */
        protected void setTheme(int newTheme) {
            mActivity.setTheme(newTheme);
            makeChange(newTheme);
        }

        /**
         * 修改各个视图绑定的属性
         */
        private void makeChange(int themeId) {
            Theme curTheme = mActivity.getTheme();
            for (ViewSetter setter : mElements) {
                setter.setValue(curTheme, themeId);
            }
        }

        /**
         * 创建Colorful对象
         *
         * @return
         */
        public Colorful create() {
            return new Colorful(this);
        }
    }
}
