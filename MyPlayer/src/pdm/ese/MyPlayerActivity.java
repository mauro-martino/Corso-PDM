package pdm.ese;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyPlayerActivity extends Activity {
    /** Called when the activity is first created. */
	MediaPlayer mp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mp = MediaPlayer.create(MyPlayerActivity.this, R.raw.dst);
        Button startButton = (Button)findViewById(R.id.button1);
        startButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		mp.start();
        	}
        });
        Button pauseButton = (Button)findViewById(R.id.button2);
        pauseButton.setOnClickListener(new OnClickListener() {	
        	public void onClick(View v) {
        		mp.pause();
        	}
        });
    }
    public void onDestroy(Bundle savedInstanceState) {
    	mp.release();
    }
}