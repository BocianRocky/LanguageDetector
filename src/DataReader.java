import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    private final File parentDirectory;
    private final ArrayList<OneFile> allVect;
    private final ArrayList<String>languages;

    public DataReader(String parentDirectoryPath) {
        this.parentDirectory = new File(parentDirectoryPath);
        this.allVect = new ArrayList<>();
        this.languages=new ArrayList<>();
    }
    public ArrayList<String> getLang() {
        return languages;
    }

    public ArrayList<OneFile> getAllVect() {
        return allVect;
    }

    public ArrayList<OneFile> getLanguages() {
        return allVect;
    }

    public void readMainDirectrory() {
        if (parentDirectory.isDirectory()) {
            File[] katalogi = parentDirectory.listFiles(File::isDirectory);
            if (katalogi != null && katalogi.length > 0) {
                for (File katalog : katalogi) {
                    languages.add(katalog.getName());
                    File[] listaPlikow = katalog.listFiles(File::isFile);
                    if (listaPlikow != null) {
                        for (File file : listaPlikow) {
                            OneFile languageFile = new OneFile(katalog.getName());
                            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    languageFile.countLettersInLine(line);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException("blad podczas czytania pliku");
                            }
                            allVect.add(languageFile);
                        }
                    }

                }
            } else {
                throw new RuntimeException("Brak katalogow z jezykami");
            }
        } else {
            throw new RuntimeException("nie ma takiego katalogu");
        }

    }

}
