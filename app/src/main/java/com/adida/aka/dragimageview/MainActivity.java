package com.adida.aka.dragimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imgPic;
    TextView txtToado;
    int mode = 0;
    int DRAG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgPic = (ImageView) findViewById(R.id.imgPic);
        txtToado = (TextView) findViewById(R.id.txtToado);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, 250);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin  = 50;
        imgPic.setLayoutParams(layoutParams);
        imgPic.setOnTouchListener(new View.OnTouchListener() {
            RelativeLayout.LayoutParams params;
            float dx = 0, dy = 0, x = 0, y =0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ImageView imageView = (ImageView) view;
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                        dx = motionEvent.getRawX() - params.leftMargin;
                        dy = motionEvent.getRawY() - params.topMargin;
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(mode == DRAG){
                            x = motionEvent.getRawX();
                            y = motionEvent.getRawY();

                            params.leftMargin = (int) (x - dx);
                            params.topMargin  = (int) (y - dy);

//                            params.rightMargin  = 0;
//                            params.bottomMargin = 0;
                            params.rightMargin = params.leftMargin + (params.width*5);
                            params.bottomMargin = params.topMargin + (params.height*10);

                            imageView.setLayoutParams(params);
                            txtToado.setText("leftMargin = "+  params.leftMargin + "| rightMargin = " +  params.rightMargin);
                        }
                }
                return true;
            }
        });
    }
}
