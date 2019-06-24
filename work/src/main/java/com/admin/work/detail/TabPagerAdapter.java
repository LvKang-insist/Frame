package com.admin.work.detail;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * FragmentStatePagerAdapter : 他不会保留每一个pager 的状态、
 * 也就是说，当我们的页面销毁后，我们的页面状态也会随之销毁
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURE = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, JSONObject data) {
        super(fm);
        //获取tabs 信息，注意，这里的tabs 是一条信息。
        final JSONArray tabs = data.getJSONArray("tabs");
        final int size = tabs.size();
        for (int i = 0; i < size; i++) {
            final JSONObject eachTab = tabs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            //存储每个图片
            final int pictureSize = pictureUrls.size();
            for (int j = 0; j < pictureSize; j++) {
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLES.add(name);
            PICTURE.add(eachTabPicturesArray);
        }
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return ImageDeleagate.create(PICTURE.get(0));
        }else if (i == 1){
            return ImageDeleagate.create(PICTURE.get(1));
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    /**
     * 每一个tab 上面的文字。
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
