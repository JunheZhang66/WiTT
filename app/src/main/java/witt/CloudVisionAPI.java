package witt;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CloudVisionAPI {

    Vision vision;

    public CloudVisionAPI() {
        Vision.Builder visionBuilder = new Vision.Builder(
                new NetHttpTransport(),
                new AndroidJsonFactory(),
                null);

        visionBuilder.setVisionRequestInitializer(new VisionRequestInitializer("AIzaSyCP4HbrJwtf9m3xw9BvFW6OnldHWHsSvGs"));
        vision = visionBuilder.build();
    }

    public String processImage(TouchEvent te)
    {
        Bitmap photo = resizeImage(te);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] photoData = stream.toByteArray();

        Image inputImage = new Image();
        String imgStr = new String(Base64.encodeBase64(photoData));
        inputImage.setContent(imgStr);

        Feature desiredFeature = new Feature();
        desiredFeature.setType("LABEL_DETECTION");
        desiredFeature.setMaxResults(1);

        AnnotateImageRequest request = new AnnotateImageRequest();
        request.setImage(inputImage);
        request.setFeatures(Arrays.asList(desiredFeature));

        BatchAnnotateImagesRequest batchRequest =
                new BatchAnnotateImagesRequest();

        batchRequest.setRequests(Arrays.asList(request));
        try {
            Log.d("vision", "hi?");
            BatchAnnotateImagesResponse batchResponse =
                    vision.images().annotate(batchRequest).setDisableGZipContent(true).execute();
            Log.d("vision", "hello!!");
            List<AnnotateImageResponse> responses = batchResponse.getResponses();
            AnnotateImageResponse response = responses.get(0);
            EntityAnnotation label = response.getLabelAnnotations().get(0);
            return label.getDescription();
        } catch(IOException e) {
            // TODO
            return e.getMessage();
        }
    }

    private Bitmap resizeImage(TouchEvent te) {
        Bitmap orig = te.getImage();
        float x = te.getX();
        float y = te.getY();
        float maxX = te.getScreenX();
        float maxY = te.getScreenY();

        float halfWidth = Math.min(x, maxX - x);
        float halfHeight = Math.min(y, maxY - y);

        int startX = Math.round(x - halfWidth);
        int startY = Math.round(y - halfHeight);

        Bitmap modified = Bitmap.createBitmap(orig, startX, startY,
                Math.min(Math.round(halfWidth*2), orig.getWidth()-startX),
                Math.min(Math.round(halfHeight*2), orig.getHeight()-startY));
        return Bitmap.createScaledBitmap(modified, 300, 300, false);
    }

}
