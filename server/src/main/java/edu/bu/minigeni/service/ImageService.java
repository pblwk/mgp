package edu.bu.minigeni.service;


import org.deeplearning4j.nn.graph.ComputationGraph;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static edu.bu.minigeni.utility.Utility.*;

/**
 * the Service that handles the business logic of image recognition
 */
@Service
public class ImageService {
    public String recognize(MultipartFile multipartFile) {

        try {

            INDArray ndArray = preprocess(multipartFile); //shape is [batch size, channels, height, width]
            ComputationGraph pretrained = getPretrained(); // get the pretrained model
            INDArray[] prediction = pretrained.output(ndArray); // predict
            return getLabel(prediction); // return the prediction label

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "";
    }

}
