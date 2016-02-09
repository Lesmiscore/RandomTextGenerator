package com.nao20010128nao.RTG;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import android.text.*;

public class MainActivity extends Activity
{
	EditText result,length;
	Button start;
	BufferedReader br=null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		start=(Button)findViewById(R.id.start);
		result=(EditText)findViewById(R.id.result);
		length=(EditText)findViewById(R.id.length);
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("/dev/urandom")),8000);
			br.read(new char[10]);
		} catch (IOException e) {
			
		}
		start.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				result.setText("");
				int len=Integer.parseInt(length.getText().toString());
				char[] arr=new char[len];
				try {
					int r=br.read(arr);
					if(r>0){
						br.read(arr,r,arr.length-r);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						if (br != null) {
							br.close();
						}
					} catch (IOException e) {
						
					}
				}
				result.setText(arr,0,arr.length);
				((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setText(result.getText());
				start.setOnClickListener(this);
			}
		});
    }
}
