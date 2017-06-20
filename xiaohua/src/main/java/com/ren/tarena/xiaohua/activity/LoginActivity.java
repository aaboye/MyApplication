package com.ren.tarena.xiaohua.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.manager.HttpManager;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    EditText edit_LoginName;
    EditText edit_Password;
    EditText edit_Code;
    ImageView imageView_Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialUI();
        //异步加载验证码
        asyncLoadCode();



    }

    private void asyncLoadCode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap codeBitmap =
                        HttpManager.loadCodeHttpGet();
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = codeBitmap;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void initialUI() {
        edit_Code = (EditText)
                findViewById(R.id.editText_Login_Code);
        edit_LoginName = (EditText)
                findViewById(R.id.editText_Login_LoginName);
        edit_Password = (EditText)
                findViewById(R.id.editText_Login_Password);
        imageView_Code = (ImageView)
                findViewById(R.id.imageView_Code);
    }
    public void loadCode(View view) {
        asyncLoadCode();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what == 1) {
                Bitmap bitmap = (Bitmap) msg.obj;
                imageView_Code.setImageBitmap(bitmap);
            } else if (what == 2) {
                //用户登录的处理结果
                Boolean flag = (Boolean) msg.obj;
                if (flag) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();

                }
            }
        }
    };
    public void login(View view){
        String loginName=edit_LoginName.getText().toString();
        String password=edit_Password.getText().toString();
        String code=edit_Code.getText().toString();
        //发送异步的登录请求
        asyncLogin(loginName,password,code);
    }

    private void asyncLogin(
            final String loginName,
            final String password,
            final String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag= HttpManager.loginHttpPost(loginName,password,code);
                Message msg=handler.obtainMessage();
                msg.what=2;
                msg.obj=flag;
                handler.sendMessage(msg);
            }
        }).start();
    }

    public void regist(View view){
        startActivity(new Intent(LoginActivity.this,RegistActivity.class));
    }


}

