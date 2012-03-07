package com.mr;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public abstract class MMActivity extends Activity implements OnClickListener{
    private LinearLayout mContent;
    
    protected void onCreate(Bundle savedInstanceState, int resource) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.base_activity);
        
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        
        mContent = (LinearLayout) findViewById(R.id.view);
        
        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(resource, mContent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_ok){
            ok();
        }else if(v.getId()==R.id.btn_cancel){
            cancel();
        }
    }
    
    abstract protected void ok();
    abstract protected void cancel();
    
}