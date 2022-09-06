package com.example.demo.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.io.FilenameUtils.indexOfExtension;
import  org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author U6065105 -Pravat Clarivate
 * This is utility class which will help convert xls and xlsx file to csv files
 */
public class DocConvertorXLSXToCSV {
    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String CVS_SEPERATOR_CHAR = ",";

    private static final Logger log = LogManager.getLogger();

    public static void conversionOfXlsxToCSVIndexes(String inputFile, String outputFile, List<Integer> sheetIndexes) throws Exception {

        // If input parameters are null throw exception?
        if (inputFile == null || outputFile == null || sheetIndexes == null) {
            throw new Exception("Invalid parameters provided");
        }

        // Convert list of sheetIndexes into list of sheet names
        List<String> sheetNames = new ArrayList<>(sheetIndexes.size());

        try (
                FileInputStream fis = new FileInputStream(inputFile);
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
        ) {

            log.info("The input name" + fis);
            Workbook workbook = null;
            String ext = FilenameUtils.getExtension(inputFile.toString());
            if (ext.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (ext.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            for (Integer index : sheetIndexes) {
                sheetNames.add(workbook.getSheetName(index));
            }
            conversionOfXlsxToCSV(workbook, writer, sheetNames);

        }

    }

    public static void conversionOfXlsxToCSVNames(String inputFile, String outputFile, List<String> sheetNames) throws Exception {
        // If input parameters are null throw exception?
        if (inputFile == null || outputFile == null || sheetNames == null) {
            throw new Exception("Invalid parameters provided");
        }

        try (
                FileInputStream fis = new FileInputStream(inputFile);
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))
        ) {
            log.info("The input name" + fis);
            Workbook workbook = null;
            String ext = FilenameUtils.getExtension(inputFile.toString());
            if (ext.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (ext.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            conversionOfXlsxToCSV(workbook, writer, sheetNames);

        }

    }

    private static void conversionOfXlsxToCSV(Workbook workbook, BufferedWriter writer, List<String> sheetNames) throws Exception {
        for (String sheetName : sheetNames) {
            log.debug("Reading sheet data for {}", sheetName);
            Sheet sheet = workbook.getSheet(sheetName);
            readSheetData(sheet, writer);
        }
    }

    /**
     * populate string buffer with data read from sheet
     *
     * @param sheet
     * @param writer
     * @throws Exception
     */
    private static void readSheetData(Sheet sheet, BufferedWriter writer) throws Exception {
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            readRow(rowIterator.next(), writer);
            writer.append('\n');
        }
    }

    // TODO: comment function
    private static String getCustomNumericValue(Cell myCell) throws Exception {
        String cellData = "";
        if (DateUtil.isCellDateFormatted(myCell)) {
            cellData += new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(myCell.getDateCellValue());
        } else {
            cellData += new BigDecimal(myCell.getNumericCellValue());
        }
        return cellData;
    }

    /**
     * Puplate string buffer with data read from row
     *
     * @param row
     * @param writer
     * @throws Exception
     */
    private static void readRow(Row row, BufferedWriter writer) throws Exception {
        // For each row, iterate through each columns
        Iterator<Cell> cellIterator = row.cellIterator();
        for (int cn = 0; cn < row.getLastCellNum(); cn++) {
            Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            log.debug("Adding cell: {}", getCellString(cell));
            writer.append(getCellString(cell));

            // Add separator between columns if it is not last column
            if(cn != row.getLastCellNum()-1){
                writer.append(CVS_SEPERATOR_CHAR);
            }
        }
    }

    private static String getCellString(Cell cell) throws Exception {
        switch (cell.getCellType()) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return getCustomNumericValue(cell);
            case STRING:
                return "\"" + cell.getStringCellValue().replaceAll("\"", "\"\"") + "\"";
            case FORMULA:
                return String.valueOf(cell.getNumericCellValue());
            case BLANK:
                return " ";
            default:
                return cell.getStringCellValue();
        }
    }


        /**
         * This will check the file name with its extension and remove the Extension
         *
         * @param filename
         * @return
         */
        public static String removeExtension ( final String filename){
            int index = indexOfExtension(filename); //used the String.lastIndexOf() method
            if (index == 0) {
                return filename;
            } else {
                return filename.substring(0, index);
            }
        }
    }