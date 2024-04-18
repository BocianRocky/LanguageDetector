import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<LanguageWeight> languageWeights = loadTrainingData();
        ArrayList<OneFile> testFiles = loadTestData();
        performClassification(testFiles, languageWeights);
        printClassificationResults(testFiles);
        inputLanguage(languageWeights);
    }

    private static ArrayList<LanguageWeight> loadTrainingData() {
        String parentDirectoryPath = "Languages_training";
        DataReader trainingDataReader = new DataReader(parentDirectoryPath);
        trainingDataReader.readMainDirectrory();
        ArrayList<OneFile> allFilesInVec = trainingDataReader.getLanguages();
        ArrayList<LanguageWeight> languageWeights = new ArrayList<>();
        for (String lang : trainingDataReader.getLang()) {
            languageWeights.add(new LanguageWeight(lang));
        }
        for (LanguageWeight languageWeight : languageWeights) {
            languageWeight.setWeights(allFilesInVec);
        }
        return languageWeights;
    }

    private static ArrayList<OneFile> loadTestData() {
        String testDirectoryPath = "Languages_test";
        DataReader testDataReader = new DataReader(testDirectoryPath);
        testDataReader.readMainDirectrory();
        return testDataReader.getAllVect();
    }

    private static void performClassification(ArrayList<OneFile> testFiles, ArrayList<LanguageWeight> languageWeights) {
        for (OneFile file : testFiles) {
            file.checkCorrectness(languageWeights);
        }
    }

    private static void printClassificationResults(ArrayList<OneFile> testFiles) {
        System.out.println("Prawidlowo zaklasyfikowane przyklady: "+OneFile.count +"/"+testFiles.size());
        System.out.println("\"Dokladnosc eksperymentu: " + ((double)OneFile.count/testFiles.size())*100+"%");
        System.out.println("\n\n");
    }

    private static void inputLanguage(ArrayList<LanguageWeight> languageWeights) {
        while(true) {
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Wpisz tekst:");
            StringBuilder sb = new StringBuilder();
            String line;
            while(!(line= scanner.nextLine()).isEmpty()){
                sb.append(line);
            }
            String text=sb.toString();
            if(text.equals("koniec")){
                System.out.println("Koniec");
                break;
            }
            OneFile input = new OneFile();
            input.countLettersInLine(text);
            System.out.println("\n\n");
            System.out.println("Language: "+input.whichLanguage(languageWeights));
        }

    }
}
