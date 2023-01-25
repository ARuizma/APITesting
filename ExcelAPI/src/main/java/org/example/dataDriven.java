package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {

    //Identify Testcases excel file column
    //once column is identified scan to identify purchase row
    //pull purchase data and feed it into test

    public ArrayList<String> getData(String testcasename) throws IOException {
        ArrayList<String> arr = new ArrayList<String>();
        FileInputStream file = new FileInputStream("C:\\Users\\ARuizMarin\\Documents\\Udemy\\ApiTesting\\ApiTest.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheets = workbook.getNumberOfSheets();
        for(int i=0;i<sheets;i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstrow = rows.next();
                Iterator<Cell> cells = firstrow.cellIterator();
                int k=0;
                int column=0;
                while(cells.hasNext()){
                    Cell value = cells.next();
                    if(value.getStringCellValue().equalsIgnoreCase("Testcases")){
                        column=k;
                        break;
                    }
                    k++;
                }
                while(rows.hasNext()){
                    Row r=rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)){
                        Iterator<Cell> rowcell= r.cellIterator();
                        while (rowcell.hasNext()){
                            Cell c = rowcell.next();
                            if(c.getCellType()== CellType.STRING) {
                                arr.add(c.getStringCellValue());
                            } else{
                                arr.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {

    }
}