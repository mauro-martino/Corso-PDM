package pdm.ese;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawBoard extends View {
    Paint paint = new Paint();
    float puntih[] = new float[44];
    float puntiv[] = new float[44];
    int step = 10;
    int bordo = 20;
    int maxwidth = 40*step+bordo+1;
    int maxheigth = 40*step+bordo+1;

    public DrawBoard(Context context) {
        super(context);
        paint.setColor(Color.WHITE);
        for (int i=0; i <= 40; ){
        	puntih[i]   = bordo;
        	puntih[i+1] = i*step+bordo;
        	puntih[i+2] = maxwidth;
        	puntih[i+3] = i*step+bordo;
        	puntiv[i]   = i*step+bordo;
        	puntiv[i+1] = bordo;
        	puntiv[i+2] = i*step+bordo;
        	puntiv[i+3] = maxheigth;
        	i += 4;
        }
    }
    @Override
    public void onDraw(Canvas canvas) {
            canvas.drawLines(puntih, 0, 44, paint);
            canvas.drawLines(puntiv, 0, 44, paint);
    }
}
