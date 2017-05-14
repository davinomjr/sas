package com.example.davino.sas.view;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.davino.sas.R;

/**
 * Created by Davino on 27/05/2015.
 */
public class DrawerItemClickListener extends Activity implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {

       DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setItemChecked(position, true);
        setTitle(Home.menuitems[position]);
        mDrawerLayout.closeDrawer(mDrawerList);


    }
}