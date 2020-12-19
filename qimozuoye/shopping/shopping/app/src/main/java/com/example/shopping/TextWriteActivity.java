package com.example.shopping;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping.util.DateUtil;
import com.example.shopping.util.FileUtil;

public class TextWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText good_name;
    private EditText good_eva;
    private String mPath;
    private TextView tv_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_write);

        good_name= findViewById(R.id.good_name);
        good_eva= findViewById(R.id.good_eva);

        tv_path = findViewById(R.id.tv_path);
        findViewById(R.id.btn_save).setOnClickListener(this);

        // 获取当前App的私有存储目录
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = good_name.getText().toString();
            String age = good_eva.getText().toString();

            if (TextUtils.isEmpty(name)) {
                showToast("请先填写商品名称");
                return;
            } else if (TextUtils.isEmpty(age)) {
                return;
            }
            String content = "";
            content = String.format("%s　商品名称：%s\n", content, good_name);
            content = String.format("%s　评价：%s\n", content,good_eva);

            content = String.format("%s　注册时间：%s\n", content, DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));

            // 检查SD卡状态,存储媒体已经挂载，并且挂载点可读/写。
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String file_path = mPath + DateUtil.getNowDateTime("") + ".txt";
                // 把文本字符串保存为文本文件
                FileUtil.saveText(file_path, content);

                showToast("数据已写入SD卡文件");

            } else {
                showToast("未发现已挂载的SD卡，请检查");
            }
        }
    }
    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}



