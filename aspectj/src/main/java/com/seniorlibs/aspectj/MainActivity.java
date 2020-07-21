package com.seniorlibs.aspectj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

// 插桩后的MainActivity  javac.debug.compileDebugJavaWithJavac.classes.com.aspectj.MainActivity
//    public class MainActivity extends AppCompatActivity {
//        static {}
//
//        protected void onCreate(Bundle paramBundle) {
//            JoinPoint localJoinPoint = Factory.makeJP(ajc$tjp_0, this, this, paramBundle);
//            try {
//                TraceTagAspectj.aspectOf().before(localJoinPoint);
//                super.onCreate(paramBundle);
//                setContentView(2131296284);
//                TraceTagAspectj.aspectOf().after();
//                return;
//            } catch (Throwable paramBundle) {
//                TraceTagAspectj.aspectOf().after();
//                throw paramBundle;
//            }
//        }
//    }


