package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageHandler{
    BufferedImage image;

    public void load(String path){
        try {
            this.image = ImageIO.read(new File(path));
            System.out.println("Image loaded.");
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void write(String path){
        try {
            if(ImageIO.write(this.image, "jpg", new File(path))){
                System.out.println("Image saved.");
            }
            else{
                System.err.println("Image could not have been saved.");
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void increaseBrightness(int num){
        num = num & 0xff;

        //System.out.println(String.format("0x%08X", n));
        for(int i = 0; i< this.image.getWidth(); i++){
            for(int j = 0; j< this.image.getHeight(); j++){

                int b = (this.image.getRGB(i,j) & 0x0000ff) + num;
                if(b > 0x0000ff) b = 0x0000ff;
                int g = (this.image.getRGB(i,j) & 0x00ff00) + (num << 8);
                if(g > 0x00ff00) g = 0x00ff00;
                int r = (this.image.getRGB(i,j) & 0xff0000) + (num << 16);
                if(r > 0xff0000) r = 0xff0000;

                int rgb = 0xff000000 | r | g | b;

                //System.out.println(String.format("0x%08X", this.image.getRGB(i,j))+" --> "+String.format("0x%08X", rgb));
                this.image.setRGB(i,j,rgb);
            }
        }
        //System.out.println("Increased brightness.");
    }

    private class increaseBrightnessThread extends Thread{

        int startLine;
        int numLines;
        int num;
        BufferedImage image;

        public increaseBrightnessThread(int startLine, int linesPerThread, int num, BufferedImage image){
            this.startLine = startLine;
            this.numLines = linesPerThread;
            this.num = num;
            this.image = image;
        }

        @Override
        public void run() {

            num = num & 0xff;

                for (int i = 0; i < this.image.getWidth(); i++) {
                    try {
                        for (int j = startLine; j < startLine + numLines; j++) {

                            int b = (this.image.getRGB(i, j) & 0x0000ff) + num;
                            if (b > 0x0000ff) b = 0x0000ff;
                            int g = (this.image.getRGB(i, j) & 0x00ff00) + (num << 8);
                            if (g > 0x00ff00) g = 0x00ff00;
                            int r = (this.image.getRGB(i, j) & 0xff0000) + (num << 16);
                            if (r > 0xff0000) r = 0xff0000;

                            int rgb = 0xff000000 | r | g | b;

                            this.image.setRGB(i, j, rgb);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                        break;
                    }
            }
            //System.out.println("Increased brightness.");
        }
    }

    public void threadedIncreaseBrightness(int num){
        int cores = Runtime.getRuntime().availableProcessors();
        int linesAssigned = 0;
        int linesPerThread = Math.round((float)image.getHeight() / cores);
        Thread[] threads = new Thread[cores];
        for(int i = 0 ; i < cores; i++) {
            if(i == cores-1){
                linesPerThread = this.image.getHeight() - linesAssigned;
            }
            threads[i] = new increaseBrightnessThread(linesAssigned,linesPerThread,num,this.image);
            threads[i].start();
            linesAssigned += linesPerThread;
        }

        for(Thread thread : threads){
            try {
                thread.join();
            }
            catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void pooledIncreaseBrightness(int num){
        Thread[] pool = new Thread[this.image.getHeight()];

        for(int i = 0; i< this.image.getHeight(); i++){
            pool[i] = new increaseBrightnessThread(i,1,num,this.image);
            pool[i].start();
        }
        for(Thread thread: pool){
            try {
                thread.join();
            }
            catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void pooledIncreaseWithExecutorService(int num){
        ExecutorService executorService = Executors.newFixedThreadPool(this.image.getHeight());

        for(int i = 0 ; i < this.image.getHeight(); i++){
            executorService.execute(new increaseBrightnessThread(i,1,num,this.image));
        }
        executorService.shutdown();

        try{
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch(InterruptedException e){
            System.err.println(e.getMessage());
        }
    }


    private class histogramThread extends Thread{
        private final int[] histogram;
        private String channel;
        private int startLine;
        private int numLines;
        private BufferedImage image;


        public histogramThread(int[] histogram, String channel, int startLine, int numLines, BufferedImage image){
            this.histogram = histogram;
            this.channel = channel;
            this.startLine = startLine;
            this.numLines = numLines;
            this.image = image;
        }

        @Override
        public void run(){
            int channelMask;
            switch(channel){
                case "blue":
                    channelMask = 0x0000ff;
                    break;
                case "green":
                    channelMask = 0x00ff00;
                    break;
                case "red":
                    channelMask = 0xff0000;
                    break;
                default:
                    System.err.println("Malformed channel");
                    return;
            }

            int rgb = 0;
            for(int i = 0; i < this.image.getWidth(); i++) {
                for (int j = this.startLine; j < startLine + numLines; j++) {
                    rgb = this.image.getRGB(i,j);
                    rgb = rgb & channelMask;
                    if(channel.equals("green")) rgb = rgb >> 8;
                    else if(channel.equals("red")) rgb = rgb >> 16;
                    synchronized (histogram) {
                        histogram[rgb]++;
                    }
                }
            }
            System.out.println(Arrays.toString(histogram));
        }
    }

    public void blueChannelHistogram(){
        int cores = Runtime.getRuntime().availableProcessors();
//        int cores = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        int[] histogram = new int[255];

        int linesAssigned = 0;
        int linesPerThread = Math.round((float)image.getHeight() / cores);
        for(int i = 0 ; i < cores; i++) {
            if (i == cores - 1) {
                linesPerThread = this.image.getHeight() - linesAssigned;
            }
            executorService.execute(new histogramThread(histogram, "blue", linesAssigned, linesPerThread, this.image));
            linesAssigned += linesPerThread;
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch(InterruptedException e){
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.stream(histogram).max());
    }
}
