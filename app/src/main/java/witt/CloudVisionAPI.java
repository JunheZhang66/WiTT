package witt;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;

//import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.Vision.Builder;
//import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder;

import com.google.cloud.vision.v1.AnnotateImageResponse;
//import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
//import com.google.cloud.vision.v1.EntityAnnotation;

import com.google.cloud.vision.v1.Feature.Type;
//import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudVisionAPI {
    TouchEvent te;
    public CloudVisionAPI(TouchEvent te)
    {
        this.te = te;
    }
    public String noteImage()
    {
        Vision.Builder visionBuilder = new Vision.Builder(
                new NetHttpTransport(),
                new AndroidJsonFactory(),
                null);

        visionBuilder.setVisionRequestInitializer(new VisionRequestInitializer("AIzaSyCP4HbrJwtf9m3xw9BvFW6OnldHWHsSvGs"));
        Vision vision = visionBuilder.build();

        byte[] photoData = te.getImage();

        Image inputImage = new Image();
        inputImage.encodeContent(photoData);

        Feature desiredFeature = new Feature();
        desiredFeature.setType("LABEL_DETECTION");

        AnnotateImageRequest request = new AnnotateImageRequest();
        request.setImage(inputImage);
        request.setFeatures(Arrays.asList(desiredFeature));

        BatchAnnotateImagesRequest batchRequest =
                new BatchAnnotateImagesRequest();

        batchRequest.setRequests(Arrays.asList(request));
        try {
            BatchAnnotateImagesResponse batchResponse =
                    vision.images().annotate(batchRequest).execute();

            List<EntityAnnotation> labels = batchResponse.getResponses().get(0).getLabelAnnotations();

            return labels.get(0).getDescription();
        }catch( IOException e)
        {
            // TODO
            return null;
        }


    }

}
