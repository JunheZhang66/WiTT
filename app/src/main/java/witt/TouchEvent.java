package witt;

import android.graphics.Bitmap;

import com.google.ar.sceneform.ArSceneView;

public class TouchEvent {

    private Bitmap image;
    private float x;
    private float y;
    private float screenX;
    private float screenY;

    public TouchEvent(Bitmap image, float x, float y, float screenX, float screenY) {
        this.image = image;
        this.screenX = screenX;
        this.screenY = screenY;
        this.x = x;
        this.y = y;
    }

    public Bitmap getImage() {
        return image;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getScreenX() {
        return screenX;
    }

    public float getScreenY() {
        return screenY;
    }
}
