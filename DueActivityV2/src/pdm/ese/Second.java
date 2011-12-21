package pdm.ese;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Second extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        String id = "Metodo On Create";
		Log.d("Metodo", id);
        
        TextView label = (TextView) findViewById(R.id.textView1);
        String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox");
        label.setText(iltestoricevuto);
	}
	public void onStart() {
		super.onStart();
		String id = "Metodo On Start";
		Log.d("Metodo", id);
	}
	public void onReStart() {
		super.onRestart();
		String id = "Metodo On Restart";
		Log.d("Metodo", id);
		
	}
	public void onResume() {
		super.onResume();
		String id = "Metodo On Resume";
		Log.d("Metodo", id);
		
	}
	public void onPause() {
		super.onPause();
		String id = "Metodo On Pause";
		Log.d("Metodo", id);
		
	}
	public void onStop() {
		super.onStop();
		String id = "Metodo On Stop";
		Log.d("Metodo", id);
	
	}
	public void onDestroy() {
		super.onDestroy();
		String id = "Metodo On Destroy";
		Log.d("Metodo", id);
	
	}

}
