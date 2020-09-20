package FileWorker;

import Rating.RatingPair;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class FileWorker {
    //"C:\Users\oleyn\IdeaProjects\tetr\rating.txt", "rating.txt"
    static File ratingFile;
    static String path;
    static String fileName;
    static LinkedList<RatingPair> ratingList;

    static public void readOrCreateFile(){
        ratingFile = new File(path,fileName);
        if(!ratingFile.isFile()){
            try {
                ratingFile.createNewFile();
                FileWriter fw = new FileWriter(ratingFile);
                for(int i = 0; i < 5; i++){
                    fw.append('0');
                    fw.append(" Username ");
                    fw.append('\n');
                }
                fw.flush();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        try{
            FileReader fr = new FileReader(ratingFile);
            Scanner scan = new Scanner(fr);
            Integer score = 0;
            String name;
            String[] substr;
            while(scan.hasNextLine()){
                RatingPair pair = new RatingPair();
                substr = scan.nextLine().split("\\s+");
                score = Integer.parseInt(substr[0]);
                name = substr[1];
                pair.setData(score, name);
                ratingList.add(pair);
            }
        }catch(IOException | NumberFormatException e){
            throw new RuntimeException(e);
        }
    }

    static public void setPathAndFileName(String path, String fileName){
        //ratingFile = new File(path, fileName);
        FileWorker.path = path;
        FileWorker.fileName = fileName;
    }

    static public LinkedList<RatingPair> getRatingList(){
        ratingList = new LinkedList<RatingPair>();
        readOrCreateFile();
        return ratingList;
    }

    static public void setRewriteRatingFile(LinkedList<RatingPair> ratingList){
        try {
            PrintWriter pw = new PrintWriter(ratingFile);
            for(int i = 0; i < 5; i++) {
                pw.print(Integer.toString(ratingList.get(i).getScore()) + ' '
                        + ratingList.get(i).getUsername() +'\n');
            }
            pw.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
