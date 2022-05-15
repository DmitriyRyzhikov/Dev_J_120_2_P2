
package Dev_J_120;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderWriter {
    //метод читает строку с именем файла из консоли и возвращает ее в метод start класса Starter
    public String readerFromConsole(){
        System.out.println(Titles.TITLE_WELCOM.getTitleStr());
        String fileName = "";
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in, "cp1251")))
           {
            fileName = bf.readLine().trim();
           }
        catch (IOException ex){
            System.out.println("При вводе в консоль что-то пошло не так...");
        }
        return fileName;
    }
    /*метод читает строки из файла и сохраняет их в первичный список list в
      уже обработанном виде. Для увеличения производительности используется
      такая комбинация: BufferedReader(FileReader(file)). Затем полученные строки 
      разбирает на слова Scanner с помощью метода useDelimiter. Пустые строки и 
      отдельностоящие "-" ("-" были оставлены путем исключения их из списка
      разделителей чтобы не грохнуть слова, содержащие дефис) убираем методом
      list.removeIf
    */
    public List<String> readerFromFile(File file) throws IOException{
        file = Objects.requireNonNull(file, "Object File can't be null.");
        if(!file.exists())
            throw new FileNotFoundException("Файл " + file + " не найден");
        if(!file.canRead())
            throw new SecurityException("Файл " + file + " не доступен для чтения");
        List<String> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {     
            while(sc.hasNextLine()) {
            list.add(sc.useDelimiter("[…«»—–©\\h\\p{Space}\\p{Punct}\\p{IsLatin}\\p{Digit}&&[^-]]").next().trim().toLowerCase()); 
            }
        }
        catch (Exception ex)  {   } 
        list.removeIf(x -> (x.equals("") || x.equals("-"))); 
        return list;
    }
    
    //метод записывает данные анализа в два файла в табличном виде. 
    public void writerToFile(Map<String, Integer> map1, Map<String, Integer> map2) throws IOException{
        
        File report1 = new File(Starter.getTextFile().getParent() + "report1.txt");
        File report2 = new File(Starter.getTextFile().getParent() + "report2.txt");
        report1.createNewFile();
        report2.createNewFile();
        if(!report1.canWrite() || !report2.canWrite())
            throw new SecurityException("Файлы отчетов не доступны для записи.");
        try (BufferedWriter pw1 = new BufferedWriter(new FileWriter(report1));
             BufferedWriter pw2 = new BufferedWriter(new FileWriter(report2)))      
            {
            pw1.write(Titles.TITLE_HEADER_1.getTitleStr());
            pw1.write("\n     Файл: " + Starter.getTextFile());
            pw1.write("\n     Количество слов в исходном тексте: " + Starter.getList().size());
            pw1.write(Titles.TITLE_SUBHEADER_1.getTitleStr()); 
            pw1.write(Titles.TITLE_SUBHEADER_2.getTitleStr() + "\n");
            map1.forEach((x, y) -> {
                try {
                    pw1.write(String.format("%30s |    %-4d     |   %f\n", x, y,  y / (double)Starter.getList().size()));
                } catch (IOException ex) {
                    Logger.getLogger(ReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            pw1.write(Titles.TITLE_ENDER.getTitleStr()); 
            
            pw2.write(Titles.TITLE_HEADER_2.getTitleStr());
            pw2.write("\n     Файл: " + Starter.getTextFile());
            pw2.write("\n     Количество слов в исходном тексте: " + Starter.getList().size());
            pw2.write(Titles.TITLE_SUBHEADER_1.getTitleStr()); 
            pw2.write(Titles.TITLE_SUBHEADER_2.getTitleStr() + "\n"); 
            map2.forEach((x, y) -> {
                try {
                    pw2.write(String.format("%30s |    %-4d     |   %f\n", x, y,  y / (double)Starter.getList().size()));
                } catch (IOException ex) {
                    Logger.getLogger(ReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            pw2.write(Titles.TITLE_ENDER.getTitleStr()); 
            }
        
        catch (Exception e) { }
    }
           
}
