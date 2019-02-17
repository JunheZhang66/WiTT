package witt;

import android.graphics.Bitmap;

import com.google.ar.sceneform.ArSceneView;

public class TouchEvent {

    private int id
    private Bitmap image;
    private ArSceneView view;
    private float x;
    private float y;
    private float screenX;
    private float screenY;

    public TouchEvent(int id, Bitmap image, ArSceneView view, float x, float y) {
        this.id = id;
        this.image = image;
        this.screenX = screenX;
        this.screenY = screenY;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return id;
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
