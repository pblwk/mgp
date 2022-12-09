//package edu.bu.minigeni.utility;
//
//import org.bytedeco.javacpp.opencv_core;
//import org.deeplearning4j.nn.graph.ComputationGraph;
//import org.deeplearning4j.zoo.PretrainedType;
//import org.deeplearning4j.zoo.model.SqueezeNet;
//import org.deeplearning4j.zoo.util.imagenet.ImageNetLabels;
//import org.nd4j.linalg.api.ndarray.INDArray;
//
//import org.nd4j.linalg.factory.Nd4j;
//import org.opencv.core.*;
//
//import org.opencv.imgproc.Imgproc;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.io.File;
//import java.io.IOException;
//
//public class Utility {
//    static ComputationGraph pretrained =null;
//
//    public static INDArray preprocess(MultipartFile multipartFile){
//
//        int TARGET_SIZE=224;
//
//        try {
//            File tempFile = File.createTempFile("temp", "jpg");
//            multipartFile.transferTo(tempFile);
//            BufferedImage image = ImageIO.read(tempFile);
//
//
//            File outputfile = new File("image.jpg");
//            ImageIO.write(image, "jpg", outputfile);
//
//            Mat mat=bufferedImageToMat(image);
//            Mat resized=new Mat();
//            Imgproc.resize(mat,resized,new Size(256,256));
//
//            Rect rectCrop = new Rect(16,16,TARGET_SIZE,TARGET_SIZE);
//            Mat cropped= resized.submat(rectCrop);
////            System.out.println(cropped.height());
//
////            Mat normalized=new Mat();
////            Core.normalize(cropped,normalized,0.0,1.0,Core.NORM_MINMAX,CvType.CV_32FC1);
//
//            Mat normalized=cropped;
//
//            INDArray ndArray = Nd4j.zeros(1, 3, TARGET_SIZE, TARGET_SIZE);
//            for (int row=0;row<normalized.height();row++){
//                for (int col=0;col<normalized.width();col++){
//                    double[] pixel=normalized.get(row,col);
////                    pixel[0]=(pixel[0]-0.485)/ 0.229;
////                    pixel[1]=(pixel[1]-0.456)/ 0.224;
////                    pixel[2]=(pixel[2]-0.406)/ 0.225;
//                    ndArray.putScalar(new int[]{0,0,row,col},pixel[0]);
//                    ndArray.putScalar(new int[]{0,1,row,col},pixel[1]);
//                    ndArray.putScalar(new int[]{0,2,row,col},pixel[2]);
//                    normalized.put(row,col,pixel);
//                }
//            }
//            return ndArray;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
////
//
//
//    public static Mat bufferedImageToMat(BufferedImage bi) {
//        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
//        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
//        mat.put(0, 0, data);
//        return mat;
//    }
//
//
//
//    public static ComputationGraph getPretrained() {
//
//        if (pretrained !=null){
//            return pretrained;
//        }else{
//            SqueezeNet squeezeNet = SqueezeNet.builder().build();
//            try {
//                pretrained = squeezeNet.initPretrained(PretrainedType.IMAGENET);
//
//            }catch(
//                    IOException exception) {
//                System.err.print(exception.getMessage());
//            }
//
//            return pretrained;
//        }
//
//
//
//    }
//
//    public static String getLabel(INDArray[]prediction){
//        try {
//            ImageNetLabels imageNetLabels = new ImageNetLabels();
//            float max=-1f;
//            int indexForMax=-1;
//            for(int i=0;i<1000;i++){
//                if (prediction[0].toFloatVector()[i]>max){
////                    System.out.println(prediction[0].toFloatVector()[i]);
//                    max=prediction[0].toFloatVector()[i];
//                    indexForMax=i;
//                }
//            }
////            System.out.println(indexForMax);
//
//            return imageNetLabels.getLabel(indexForMax);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return "label";
//    }
//
////    public static INDArray multipartFileToINDArray(MultipartFile multipartFile){
////
////
////        try {
////            File tempFile = File.createTempFile("temp", "temp");
////            multipartFile.transferTo(tempFile);
////            BufferedImage image = ImageIO.read(tempFile);
////            INDArray ndArray = Nd4j.zeros( 3, image.getHeight(), image.getWidth());
////
////            for (int y = 0; y < image.getHeight(); y++) {
////                for (int x = 0; x < image.getWidth(); x++) {
////                    //Retrieving contents of a pixel
////                    int pixel = image.getRGB(x, y);
////                    //Creating a Color object from pixel value
////                    Color color = new Color(pixel, true);
////                    //Retrieving the R G B values
////                    int red = color.getRed();
////                    ndArray.putScalar(new int[] {0,y,x},red);
////                    int green = color.getGreen();
////                    ndArray.putScalar(new int[] {1,y,x},green);
////                    int blue = color.getBlue();
////                    ndArray.putScalar(new int[] {2,y,x},blue);
////                }
////            }
////
////            return ndArray;
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////
////        return null;
////
////    }
//
//}
