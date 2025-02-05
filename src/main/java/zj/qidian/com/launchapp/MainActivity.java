package zj.qidian.com.launchapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar = null;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==007){

                //进入登陆界面;
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 不显示系统的标题栏，保证windowBackground和界面activity_main的大小一样，显示在屏幕不会有错位;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initActionBar();
        doDelay();
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.hide();

        }

    }

    private void doDelay(){
       //此处开辟新的线程，避免在主线程中因更新UI而导致ANR异常;
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                    Message message = new Message();
                    message.what = 007;
                    handler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

}
