
package Dev_J_120;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Analyzer {
    /*метод из первичного списка list, содержащего все русские слова файла,  
     формирует сначала множество set, что позволит убрать все дубликаты слов. 
     После каждое слово из set сравнивается со словами из первичного списка list,
     в процессе чего ведется подсчет по каждому совпадению. В итоге формируется 
     TreeMap, в которой лежат пары: слово-количество слов. В данном случае ключ - слова.
     Так как это TreeMap, попутно получаем сортировку ключей - слов по алфавиту.     
    */  
    public Map<String, Integer> primaryAnalyzer(List<String> list){
        Map<String, Integer> report1 = new TreeMap<>();
        Set<String> set = new HashSet<>(list);
        set.forEach(x -> {
        int count = 0;
        for(String s : list) {
            if(x.equals(s))
               count++; }
        report1.put(x, count);
            });
        return report1;
    } 
    /*
    Метод формирует еще одну TreeMap с данным для отчета №2. Здесь сортировка
    выполняется по значению - абсолютной частоте слов в тексте в убывающем порядке.
    При совпадении значений, сортировка выполняется автоматически по ключам - словам
    в алфавитном порядке. Используется метод sortByValues с переопрделенным компаратором.
    */    
    public Map<String, Integer> secondaryAnalyzer(List<String> list){
            Map<String, Integer> report2 = sortByValues(primaryAnalyzer(list));
        return report2;    
    }
    
    public static Map<String, Integer> sortByValues(Map<String, Integer> map){
        
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = map.get(o2).compareTo(map.get(o1));
                if(result == 0)
                   return 1;
                else
                   return result;
            }       
        };
        Map<String, Integer> map1 = new TreeMap<>(comparator); 
        map1.putAll(map);
        return map1;
    }
    //метод для печати отчетов в консоль. 
    public void printReports(){
        String t1 = Titles.TITLE_HEADER_1.getTitleStr();
        String t2 = "\n    Файл: " + Starter.getTextFile();
        String t3 = "\n    Количество слов в исходном тексте: " + Starter.getList().size();
        String t4 = Titles.TITLE_SUBHEADER_1.getTitleStr();
        String t5 = Titles.TITLE_SUBHEADER_2.getTitleStr();
        System.out.println(t1+t2+t3+t4+t5);
        Starter.getReport1().forEach((x, y) -> System.out.printf("%30s |    %-4d     |   %f\n", x, y,  y / (double)Starter.getList().size()));
        System.out.println(Titles.TITLE_ENDER.getTitleStr());       
        String t6 = Titles.TITLE_HEADER_2.getTitleStr();
        System.out.println(t6+t2+t3+t4+t5);
        Starter.getReport2().forEach((x, y) -> System.out.printf("%30s |    %-4d    |   %f\n", x, y,  y / (double)Starter.getList().size()));
        System.out.println(Titles.TITLE_ENDER.getTitleStr());
        
    } 
}
