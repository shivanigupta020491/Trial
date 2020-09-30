package com.shivani.trial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.react.modules.core.PermissionListener;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.JitsiMeetViewListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements JitsiMeetActivityInterface {

    private FrameLayout frameLayoutSmallScreen;
    private JitsiMeetView view;
    private ImageView fullScreenBtn;
    private RelativeLayout screenLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        launch1(frameLayoutSmallScreen);
       // initilaiseSDK();


    }

    private void initView() {
        frameLayoutSmallScreen = findViewById(R.id.frameLayout);
        screenLayout = findViewById(R.id.linearLayout1);
        fullScreenBtn = findViewById(R.id.fullScreenImgBtn);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void launch1(FrameLayout frameLayout) {
        view = new JitsiMeetView(MainActivity.this);
        System.out.println(">>>>>>> view MainActivity" + "error value");
        JitsiMeetConferenceOptions options = null;
     //  try {
            options = new JitsiMeetConferenceOptions.Builder()
                   //.setServerURL(new URL("https://meet.jit.si/test123"))
                    .setRoom("sfgg")
                    .setSubject("Text")
                    .setAudioMuted(false)
                    .setVideoMuted(false)
                    .setAudioOnly(false)
                    .setWelcomePageEnabled(false)
                    .build();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            System.out.println(">>>>>>> error" + e.toString());
//        }
        // https://meet.jit.si/test123
        //   https://buzz.ipathshala.in/
        view.join(options);
        frameLayout.addView(view);
//
        view.setListener(new JitsiMeetViewListener() {
            @Override
            public void onConferenceJoined(Map<String, Object> map) {
                System.out.println(">>>>>>>>>>>>>>>. joined");
            }

            @Override
            public void onConferenceTerminated(Map<String, Object> map) {
                view.leave();
                view.dispose();
                view = null;
                System.out.println(">>>>>>>>>>>>>>>. terminate");
                finish();
            }

            @Override
            public void onConferenceWillJoin(Map<String, Object> map) {
                System.out.println(">>>>>>>>>>>>>>>. join");
            }
        });

//        frameLayout.addView(view);

    }

//    private void initilaiseSDK() {
//        URL serverURL;
//        try {
//          serverURL = new URL("https://buzz.ipathshala.in/");
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Invalid server URL!");
//        }
//        JitsiMeetConferenceOptions defaultOptions
//                = new JitsiMeetConferenceOptions.Builder()
//                .setServerURL(serverURL)
//                .setWelcomePageEnabled(false)
//                .build();
//        JitsiMeet.setDefaultConferenceOptions(defaultOptions);
//    }


    @Override
    public void requestPermissions(String[] strings, int i, PermissionListener permissionListener) {

    }
}