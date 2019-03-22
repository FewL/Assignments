package com.tank.newsreader;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private Handler handler = new Handler();
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button6);
        textView = (TextView) findViewById(R.id.textView4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String query =editText.getText().toString();
                //final String query = "我爱你\n你爱我吗";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String resultJson = new TransApi().getTransResult(query, "auto", "en");
                        //拿到结果，对结果进行解析。
                        Gson gson = new Gson();
                        TranslateResult translateResult = gson.fromJson(resultJson, TranslateResult.class);
                        final List<TranslateResult.TransResultBean> trans_result = translateResult.getTrans_result();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(trans_result!=null){
                                    String dst = "";
                                    for (TranslateResult.TransResultBean s : trans_result
                                    ) {
                                        dst = dst + "\n" + s.getDst();
                                    }

                                    textView.setText(dst);
                                }
                                else
                                {
                                    textView.setText("调用失败！");
                                }
                            }
                        });

                    }
                }).start();
            }
        });

    }
}
