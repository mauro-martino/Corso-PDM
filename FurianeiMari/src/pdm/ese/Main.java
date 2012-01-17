package pdm.ese;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button bottone = new Button(this);
        bottone.setText("prova");

        boolean giocatore[][] = new boolean[10][10];
        
        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
        layoutparams.weight = 3.0f;
        LinearLayout layout = new LinearLayout(this);
        DrawBoard drawBoard = new DrawBoard(this);
        drawBoard.setBackgroundColor(Color.BLUE);
        layout.addView(drawBoard,layoutparams);
        LinearLayout layout2 = new LinearLayout(this);
        layout2.addView(bottone);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(5.0f);
        mainLayout.addView(layout,layoutparams);
        layoutparams.weight = 2.0f;
        mainLayout.addView(layout2,layoutparams);
        setContentView(mainLayout);
    }
}