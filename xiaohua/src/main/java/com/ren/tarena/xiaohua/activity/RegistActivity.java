package com.ren.tarena.xiaohua.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.entity.User;
import com.ren.tarena.xiaohua.fragment.BaseFragment;
import com.ren.tarena.xiaohua.manager.HttpManager;

public class RegistActivity extends AppCompatActivity {
    private EditText editText_loginName;
    private EditText editText_password;
    private EditText editText_okPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initialUI();
    }

    private void initialUI() {
        editText_loginName = (EditText) findViewById(R.id.edittext_regist_num);
        editText_password = (EditText) findViewById(R.id.edittext_regist_pwd);
        editText_okPassword = (EditText) findViewById(R.id.edittext_regist_password);
    }

    public void regist(View view) {
        String loginName = editText_loginName.getText().toString();
        String password = editText_password.getText().toString();
        String okPassword = editText_okPassword.getText().toString();

        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(password)
                ) {
            Toast.makeText(RegistActivity.this, "请填写完整的注册信息", Toast.LENGTH_SHORT).show();
            return;
        }
        //如果密码和确认密码不一致则返回方法调用
        if (!password.equals(okPassword)) {
            Toast.makeText(RegistActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        //把提交的注册用户信息注册成一个用户对象
        User user = new User(-1, loginName, password);
        //发送注册请求
        asyncRegister(user);

    }

    private void asyncRegister(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = HttpManager.registHttpPost(user);
                //将返回结果发送回主线程
                //从消息池中获得一个消息对象
                Message msg = handler.obtainMessage();
                msg.obj = flag;
                handler.sendMessage(msg);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Boolean flag = (Boolean) msg.obj;
            if (flag) {
                Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                //清空输入框内容
                cleanContent();
                startActivity(new Intent(RegistActivity.this,LoginActivity.class));
            } else {
                Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void cleanContent() {
        editText_loginName.setText("");
        editText_okPassword.setText("");
        editText_password.setText("");
    }

}
