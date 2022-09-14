package tw.edu.pu.s1082827.mfgt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button2); //search
        btn1.setOnClickListener(new View.OnClickListener(){  //点击按钮监听
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,search.class); //切换窗口
                startActivity(i);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button4); //chat
        btn2.setOnClickListener(new View.OnClickListener(){  //点击按钮监听
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,chat.class); //切换窗口
                startActivity(i);
            }
        });

        Button btn3 = (Button) findViewById(R.id.button3); //account
        btn3.setOnClickListener(new View.OnClickListener(){  //点击按钮监听
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,account.class); //切换窗口
                startActivity(i);
            }
        });
    }

}
