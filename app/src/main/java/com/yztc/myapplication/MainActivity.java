package com.yztc.myapplication;



import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yztc.fragment.News;
import com.yztc.fragment.Video;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private Map<String,Fragment> datas=new HashMap<>();
   private NavigationView navigationMenu;
   private SlidingMenu slidingMenu;
    private android.support.v4.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationMenu= (NavigationView) findViewById(R.id.navigation);
        slidingMenu= (SlidingMenu) findViewById(R.id.slidingMenu);
        navigationMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news:
                        FragmentTransaction transaction=fragmentManager.beginTransaction();

                        Fragment news=datas.get("news");
                          transaction.replace(R.id.my_container,news);
                        transaction.commit();
                        slidingMenu.showContent();
                        break;
                    case R.id.video:
                        FragmentTransaction transaction1=fragmentManager.beginTransaction();
                        Fragment video =datas.get("video");
                        transaction1.replace(R.id.my_container,video);
                        transaction1.commit();
                        slidingMenu.showContent();
                     break;
                }
               return true;
            }
        });

        init();
    }

   public void init(){
     fragmentManager =getSupportFragmentManager();
       FragmentTransaction transaction=fragmentManager.beginTransaction();
        Fragment video=new Video();
        Fragment  news=new News();
        datas.put("video",video);
        datas.put("news",news);
        transaction.add(R.id.my_container,video);
        transaction.commit();
   }
}
