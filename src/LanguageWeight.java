import java.util.ArrayList;
import java.util.Arrays;

public class LanguageWeight {
    private final double[]weights;
    private final String nameLang;

    private double theta=0;
    private static final double alfa=0.2;

    public String getNameLang() {
        return nameLang;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getTheta() {
        return theta;
    }

    public LanguageWeight(String nameLang) {
        this.nameLang = nameLang;
        this.weights = new double[26];
        Arrays.fill(weights,0.3);
    }
    public void setWeights(ArrayList<OneFile> allFilesInVec){
        double d;
        boolean flag=true;
        while(flag){
            flag=false;
            for(OneFile oneFile : allFilesInVec){
                d=(oneFile.getCountry().equals(nameLang))?1.0:0.0;
                double y=activationFunction(weights, oneFile.getTableLetters(), theta);
                if(d-y!=0.0){
                    flag=true;
                    boolean secflag=true;
                    while(secflag) {
                        for (int i = 0; i < weights.length; i++) {
                            weights[i] += (d - y) * alfa * oneFile.getTableLetters()[i];
                        }
                        theta+=(d-y)*alfa*(-1);
                        double u=activationFunction(weights, oneFile.getTableLetters(), theta);
                        if (d - u == 0.0) {
                            secflag=false;
                        }
                    }
                }
            }
        }
    }
    public static double activationFunction(double[] weights, int[] tableLetters, double theta) {
        double x=0.0;
        for (int i = 0; i < weights.length; i++) {
            x+=weights[i]*tableLetters[i];
        }
        x+=theta*(-1);
        return (x>=0)?1.0:0.0;
    }

}

