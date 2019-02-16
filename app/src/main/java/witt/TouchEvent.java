package witt;

import com.google.ar.sceneform.ArSceneView;

public class TouchEvent {

    private int id;
    private byte[] image;
    private ArSceneView view;
    private float x;
    private float y;

    public TouchEvent(int id, byte[] image, ArSceneView view, float x, float y) {
        this.id = id;
        this.image = image;
        this.view = view;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public ArSceneView getView() {
        return view;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
