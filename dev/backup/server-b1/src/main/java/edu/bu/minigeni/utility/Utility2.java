package edu.bu.minigeni.utility;


import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.zoo.PretrainedType;
import org.deeplearning4j.zoo.model.SqueezeNet;
import org.deeplearning4j.zoo.util.imagenet.ImageNetLabels;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;


public class Utility2 {
    static ComputationGraph pretrained =null;

    public static INDArray preprocess(MultipartFile multipartFile){

        int TARGET_SIZE=224;

        try {
            File tempFile = File.createTempFile("temp", "jpg");
            multipartFile.transferTo(tempFile);
            BufferedImage image = ImageIO.read(tempFile);


            File outputfile = new File("image.jpg");
            ImageIO.write(image, "jpg", outputfile);

            BufferedImage resizedImage=resizeImage(image,TARGET_SIZE,TARGET_SIZE);

            ImageIO.write(resizedImage, "jpg", outputfile);

            INDArray ndArray=bufferedImageToINDArray(resizedImage);
            assert ndArray != null;
            ndArray=ndArray.reshape(1,3,TARGET_SIZE,TARGET_SIZE);

            return ndArray;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }







    public static ComputationGraph getPretrained() {

        if (pretrained !=null){
            return pretrained;
        }else{
            SqueezeNet squeezeNet = SqueezeNet.builder().build();
            try {
                pretrained = squeezeNet.initPretrained(PretrainedType.IMAGENET);

            }catch(
                    IOException exception) {
                System.err.print(exception.getMessage());
            }

            return pretrained;
        }



    }

    public static String getLabel(INDArray[]prediction){
        try {
            ImageNetLabels imageNetLabels = new ImageNetLabels();
            float max=-1f;
            int indexForMax=-1;
            for(int i=0;i<1000;i++){
                if (prediction[0].toFloatVector()[i]>max){
//                    System.out.println(prediction[0].toFloatVector()[i]);
                    max=prediction[0].toFloatVector()[i];
                    indexForMax=i;
                }
            }
//            System.out.println(indexForMax);

            return imageNetLabels.getLabel(indexForMax);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "label";
    }


        public static INDArray bufferedImageToINDArray(BufferedImage image){


        try {
            INDArray ndArray = Nd4j.zeros( 3, image.getHeight(), image.getWidth());

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = image.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    ndArray.putScalar(new int[] {0,y,x},red);
                    int green = color.getGreen();
                    ndArray.putScalar(new int[] {1,y,x},green);
                    int blue = color.getBlue();
                    ndArray.putScalar(new int[] {2,y,x},blue);
                }
            }

            return ndArray;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

//    public static INDArray multipartFileToINDArray(MultipartFile multipartFile){
//
//
//        try {
//            File tempFile = File.createTempFile("temp", "temp");
//            multipartFile.transferTo(tempFile);
//            BufferedImage image = ImageIO.read(tempFile);
//            INDArray ndArray = Nd4j.zeros( 3, image.getHeight(), image.getWidth());
//
//            for (int y = 0; y < image.getHeight(); y++) {
//                for (int x = 0; x < image.getWidth(); x++) {
//                    //Retrieving contents of a pixel
//                    int pixel = image.getRGB(x, y);
//                    //Creating a Color object from pixel value
//                    Color color = new Color(pixel, true);
//                    //Retrieving the R G B values
//                    int red = color.getRed();
//                    ndArray.putScalar(new int[] {0,y,x},red);
//                    int green = color.getGreen();
//                    ndArray.putScalar(new int[] {1,y,x},green);
//                    int blue = color.getBlue();
//                    ndArray.putScalar(new int[] {2,y,x},blue);
//                }
//            }
//
//            return ndArray;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }

}
