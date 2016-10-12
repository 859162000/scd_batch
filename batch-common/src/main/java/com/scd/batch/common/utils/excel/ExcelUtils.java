package com.scd.batch.common.utils.excel;

import com.scd.batch.common.utils.enumeration.EnumUtils;
import com.scd.batch.common.utils.enumeration.ValuedEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ExcelUtils {
    private static final DataFormatter DATA_FORMATTER = new DataFormatter();

    /**
     * Read from file.
     * 
     * @param filePath
     * @param startSheet index from 0
     * @param isFirstRowTitle True means the first row in the sheet is a title row.
     * @param resTransformer Row data Transformer, each of the tuple[] is a Excel Cell Object. An anonymous instance
     *            will be recommended when have your own transformer way.
     * @return A list, each element in the list represents as a Row record.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readFromFile(String filePath, int startSheet, boolean isFirstRowTitle,
            RowTransformer resTransformer) throws Exception {
        Assert.isTrue(StringUtils.isNotEmpty(filePath), "filePath must not be empty!");
        Assert.isTrue(startSheet >= 0, "startSheet must not be negative!");
        Assert.notNull(resTransformer, "resultTransformer must not be null!");

        // check file extension [xls, xlsx]
        String fileExtName = StringUtils.substringAfterLast(filePath, ".");
        ExcelType excelType = EnumUtils.parse(ExcelType.class, fileExtName.toUpperCase());
        Assert.notNull(excelType, "Only can read from [xls, xlsx] file.");

        // read from file
        File excelFile = new File(filePath);
        Assert.isTrue(excelFile.exists(), "File:" + filePath + " not exist!");

        // get sheet from workbook
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheetAt(startSheet);

        // handle the sheet by each row and cells in the row.
        int rowIndex = 0;
        List<T> resultList = new ArrayList<>();
        for (Row row : sheet) {
            rowIndex++;

            // Skip the first row if it is the title row.
            if (rowIndex == 1 && isFirstRowTitle) {
                continue;
            }

            List<Cell> cellListInOneRow = new ArrayList<>();
            for (Cell cell : row) {
                cellListInOneRow.add(cell);
            }

            T transfedObj = (T) resTransformer.transformRow(cellListInOneRow.toArray());
            resultList.add(transfedObj);
        }

        return resultList;
    }

    /**
     * Read from file, default with DefaultResultTransformer.INSTANCE
     */
    public static List<List<String>> readFromFile(String filePath, int startSheet, boolean isFirstRowTitle)
            throws Exception {
        return readFromFile(filePath, startSheet, isFirstRowTitle, DefaultRowTransformer.INSTANCE);
    }

    /**
     * Read from file, default with startSheet=0 and DefaultResultTransformer.INSTANCE
     */
    public static List<List<String>> readFromFile(String filePath, boolean isFirstRowTitle) throws Exception {
        return (List<List<String>>) readFromFile(filePath, 0, isFirstRowTitle);
    }

    /**
     * Read the cell value by the cell format style.
     * 
     * @param cell
     * @return
     */
    public static String getStringValue(final Cell cell) {
        String result = "";

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
            case Cell.CELL_TYPE_NUMERIC:
            case Cell.CELL_TYPE_BOOLEAN:
                result = DATA_FORMATTER.formatCellValue(cell);
                break;

            // skip these type of cell
            case Cell.CELL_TYPE_ERROR:
            case Cell.CELL_TYPE_FORMULA:
            case Cell.CELL_TYPE_BLANK:
                break;

            default:
                // NOPE
                break;
        }

        return result;
    }

    /**
     * DefaultRowTransformer. Transformer to {@code List<String>}
     */
    private static class DefaultRowTransformer implements RowTransformer {
        public static final DefaultRowTransformer INSTANCE = new DefaultRowTransformer();

        /**
         * Hidden constructor
         */
        private DefaultRowTransformer() {
        }

        @Override
        public List<String> transformRow(Object[] cells) {
            List<String> eachRow = new ArrayList<>();
            for (Object cellObj : cells) {
                Cell cell = (Cell) cellObj;
                eachRow.add(getStringValue(cell));
            }

            return eachRow;
        }
    }

    /**
     * ExcelType enum: XLS(Excel2003), XLSX(Excel2007 or later)
     */
    private enum ExcelType implements ValuedEnum<String> {
        XLS("XLS"),   // Type for Excel2003
        XLSX("XLSX"); // Type for Excel2007 or later
        
        /**
         * the bound file extension
         */
        public final String fileExt;

        ExcelType(String fileExt) {
            this.fileExt = fileExt;
        }

        @Override
        public String value() {
            return this.fileExt;
        }
    }

}
