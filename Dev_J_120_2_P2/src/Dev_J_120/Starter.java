
package Dev_J_120;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


class Starter {
    
    private static File textFile;
    private static List<String> list;
    private static Map<String, Integer> report1;
    private static Map<String, Integer> report2;

    public static File getTextFile() {
        return textFile; }
    
    public static List<String> getList() {
        return list; }

    public static Map<String, Integer> getReport1() {
        return report1; }

    public static Map<String, Integer> getReport2() {
        return report2; }
    //метод последовательно запускает все методы, необходимые для анализа текста      
    public static void start() throws IOException{
        
            ReaderWriter readerWriter = new ReaderWriter();
            File tempFile = new File(readerWriter.readerFromConsole());
            if(tempFile.isFile()) { 
            System.out.println(Titles.TITLE_CONFIRM.getTitleStr());
            textFile = tempFile; }
            else {
                System.out.println(Titles.TITLE_WRONG_URL.getTitleStr());
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
                }
                throw new IllegalArgumentException("Некорректный URL "); }
            
            list = readerWriter.readerFromFile(textFile);
            Analyzer analyzer = new Analyzer();
            report1 = analyzer.primaryAnalyzer(list);
            report2 = analyzer.secondaryAnalyzer(list);
            readerWriter.writerToFile(report1, report2); 
            analyzer.printReports();
    }
               
}
