package edu.bu.minigeni.service;


import org.deeplearning4j.nn.graph.ComputationGraph;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static edu.bu.minigeni.utility.Utility2.*;


@Service
public class ImageService {
    public String recognize(MultipartFile multipartFile){

        try {

            INDArray ndArray=preprocess(multipartFile); //channels, height, width
            ComputationGraph pretrained = getPretrained();
            INDArray[] prediction=pretrained.output(ndArray);
            return getLabel(prediction);

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return "";
    }

}
