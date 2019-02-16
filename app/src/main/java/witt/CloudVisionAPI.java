package witt;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudVisionAPI {

    public String noteImage(TouchEvent te)
    {
        Vision.Builder visionBuilder = new Vision.Builder(
                new NetHttpTransport(),
                new AndroidJsonFactory(),
                null);

        visionBuilder.setVisionRequestInitializer(new VisionRequestInitializer("AIzaSyCP4HbrJwtf9m3xw9BvFW6OnldHWHsSvGs"));
        Vision vision = visionBuilder.build();

        byte[] photoData = te.getImage();

        Image inputImage = new Image();
        //inputImage.encodeContent(photoData);
        String imgStr = new String(Base64.encodeBase64(photoData));
        inputImage.setContent(imgStr);

        Feature desiredFeature = new Feature();
        desiredFeature.setType("FACE_DETECTION");
        desiredFeature.setMaxResults(1);

        AnnotateImageRequest request = new AnnotateImageRequest();
        request.setImage(inputImage);
        request.setFeatures(Arrays.asList(desiredFeature));

        BatchAnnotateImagesRequest batchRequest =
                new BatchAnnotateImagesRequest();

        Log.d("vision", "requests");
        batchRequest.setRequests(Arrays.asList(request));
        try {

            Log.d("vision", "hi?");
            BatchAnnotateImagesResponse batchResponse =
                    vision.images().annotate(batchRequest).setDisableGZipContent(true).execute();

            Log.d("vision", "hello!!");
            if(batchResponse == null)
                Log.d("vision", "wtf?");
            List<AnnotateImageResponse> responses = batchResponse.getResponses();
            if(responses == null)
                Log.d("vision", "null?");
            AnnotateImageResponse response = responses.get(0);
            if(response == null)
                Log.d("vision", "null???");

            if(response.getFaceAnnotations() != null)
                Log.d("vision", "gottem?");
            else
                Log.d("vision", "null??");
            return response.getFaceAnnotations().toString();
        }catch( IOException e)
        {
            // TODO
            Log.d("vision", "fuck..");
            return e.getMessage();
        }
    }

}
