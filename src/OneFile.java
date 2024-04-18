import java.util.ArrayList;

public class OneFile {
    private final String country;
    private final int []tableLetters;

    public static int count=0;

    public OneFile(){
        this.country=null;
        this.tableLetters=new int[26];
    }
    public OneFile(String country){
        this.country=country;
        this.tableLetters=new int[26];
    }

    public String getCountry() {
        return country;
    }

    public int[] getTableLetters() {
        return tableLetters;
    }

    public void countLettersInLine(String line){
        line=line.toUpperCase();
        for (int i = 0; i < line.length(); i++) {
            char letter=line.charAt(i);
            if(letter>='A' && letter<='Z'){
                int num=letter-'A';
                tableLetters[num]++;
            }
        }
    }

    public void checkCorrectness(ArrayList<LanguageWeight> langWeights) {
        for (LanguageWeight a : langWeights) {
            double x=active(a);
            if (country != null && country.equals(a.getNameLang()) && x==1) {
                count++;
            }
        }
    }

    public String whichLanguage(ArrayList<LanguageWeight> langWeights) {
        for (LanguageWeight a : langWeights) {
            double x=active(a);
            if (x==1) {
                return a.getNameLang();
            }
        }
        throw new RuntimeException("nie ma takiego jezyka");
    }

    private double active(LanguageWeight weight) {
        double x=0;
        double[] weights=weight.getWeights();
        int[] tableLetters=getTableLetters();
        for (int i = 0; i < weights.length; i++) {
            x+=(double)tableLetters[i]*weights[i];
        }
        x+=weight.getTheta()*(-1);
        return (x>=0)?1:0;
    }







}
