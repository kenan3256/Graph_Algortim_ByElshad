package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class NodeReader {
    public static int[][] readNodesFromExcel(String filePath) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(file);

            List<Element> preparationForGraph = new ArrayList<>();

            Sheet sheet = workbook.getSheetAt(0);  // Data ilk vereqdedi
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int sourceNodeNum = (int) row.getCell(0).getNumericCellValue();
                int targetNodeNum = (int) row.getCell(1).getNumericCellValue();
                int weight = (int) row.getCell(2).getNumericCellValue();
                Element elem = new Element(sourceNodeNum-1, targetNodeNum-1, weight);
                preparationForGraph.add(elem);
            }

            workbook.close();

            Map<Integer, List<Element>> groupped = preparationForGraph.stream().collect(Collectors.groupingBy(elem -> elem.source));
            Set<Map.Entry<Integer,  List<Element>>> entrySet =  groupped.entrySet();

            int [][] result = new int[entrySet.size()][entrySet.size()];

            for(Map.Entry<Integer,  List<Element>> entry: entrySet){
                    Integer source = entry.getKey();
                    List<Element> elementList = entry.getValue();
                    for(Element elem : elementList){
                          result[source][elem.target] = elem.weight;
                    }

            }


            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static class Element{
        private Integer source;
        private Integer target;
        private Integer weight;

        public Element(Integer source, Integer target, Integer weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }


    }
}
