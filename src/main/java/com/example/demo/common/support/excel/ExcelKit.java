package com.example.demo.common.support.excel;

import com.example.demo.common.support.StrKit;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangsp
 * date 2021-06-21
 */
public class ExcelKit<T> {
    Class<T> clazz;

    //是否默认第一个sheet
    @Setter
    private static boolean DEF_SHEET_FIRST = true;

    public static String xlsX = ".xlsx";
    public static String xls = ".xls";

    public ExcelKit(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 读取exl数据
     *
     * @param sheetName 表名
     * @param pathExl   excel表位置
     */
    public List<T> importExcel(String sheetName, String pathExl){
        if(StrKit.notBlank(pathExl)){
            try (FileInputStream fileInputStream = new FileInputStream(pathExl)) {
                if (pathExl.endsWith(xlsX)) {
                    return importExcel(sheetName, new XSSFWorkbook(fileInputStream));
                } else if(pathExl.endsWith(xls)){
                    return importExcel(sheetName, new HSSFWorkbook(fileInputStream));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
    public List<T> importExcel(List<String> sheetNames, Workbook workbook) throws Exception {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < sheetNames.size(); i++) {
            List<T> list1 = importExcel(sheetNames.get(i), workbook);
            if (list1.size() > 0) {
                list.addAll(list1);
            }
        }
        return list;
    }

    /**
     * 读取excel 生成对象数据集合
     * @param sheetName
     * @param workbook
     * @return
     * @throws Exception
     */
    public List<T> importExcel(String sheetName, Workbook workbook) throws Exception {
        int maxCol = 0;
        List<T> list = new ArrayList<T>();
        try {
            Sheet sheet = getSheet(sheetName, workbook);
            if (sheet == null) {
                return list;
            }
            int rows = sheet.getPhysicalNumberOfRows();
            // 有数据时才处理
            if (rows > 0) {
                // 得到类的所有field.
                List<Field> allFields = getMappedFiled(clazz, null);
                // 定义一个map用于存放列的序号和field.
                Map<Integer, Field> fieldsMap = new HashMap<>();
                for (Field field : allFields) {
                    // 将有注解的field存放到map中.
                    if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                        ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                        // 获得列号
                        int col = getExcelCol(attr.column());
                        maxCol = Math.max(col, maxCol);
                        // 设置类的私有字段属性可访问.
                        field.setAccessible(true);
                        fieldsMap.put(col, field);
                    }
                }
                // 从第2行开始取数据,默认第一行是表头.
                for (int i = 1; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    //取得总列数 遍历.
                    int cellNum = maxCol;
                    T entity = null;
                    for (int j = 0; j < cellNum; j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        CellType cellType = cell.getCellType();
                        String c = "";
                        if (cellType == CellType.NUMERIC) {
                            c = String.valueOf(cell.getNumericCellValue());
                        } else if (cellType == CellType.BOOLEAN) {
                            c = String.valueOf(cell.getBooleanCellValue());
                        } else {
                            c = cell.getStringCellValue();
                        }
                        if (c == null || c.equals("")) {
                            continue;
                        }
                        entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                        // System.out.println(cells[j].getContents());
                        Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                        if (field == null) {
                            continue;
                        }
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (String.class == fieldType) {
                            field.set(entity, c);
                        } else if ((Integer.TYPE == fieldType)
                                || (Integer.class == fieldType)) {
                            field.set(entity, Integer.parseInt(c));
                        } else if ((Long.TYPE == fieldType)
                                || (Long.class == fieldType)) {
                            field.set(entity, Long.valueOf(c));
                        } else if ((Float.TYPE == fieldType)
                                || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(c));
                        } else if ((Short.TYPE == fieldType)
                                || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(c));
                        } else if ((Double.TYPE == fieldType)
                                || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(c));
                        } else if (Character.TYPE == fieldType && (c != null) && (c.length() > 0)) {
                            field.set(entity, Character.valueOf(c.charAt(0)));
                        }
                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * excel内容记录数 小于13W条
     * @param list 数据源
     * @param sheetName 表格名称
     * @param output 输出流
     * @return
     */
    public boolean exportExcel(List<T> list, String sheetName, OutputStream output) {
        // 产生工作表对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            List<Field> fields = getMappedFiled(clazz, null);
            HSSFSheet sheet = createSheet(workbook, sheetName);
            HSSFCellStyle style = createHSSFCellStyle(workbook);
            sheet = setSheetColumn(fields, sheet, style);
            int endNo = list.size();
            HSSFCell cell = null;
            HSSFRow row = null;
            // 写入各条记录,每条记录对应excel表中的一行
            for (int i = 0; i < endNo; i++) {
                //也可以根据size 取模 然后按照模值创建sheet 但是数据量过大回导致内存占用过高，建议开发降低数据量。
                if(i == 65000){
                    sheet = createSheet(workbook, sheetName);
                    sheet = setSheetColumn(fields, sheet, style);
                }
                row = sheet.createRow(i +1);
                // 得到导出对象.
                T vo = list.get(i);
                for (int k = 0; k < fields.size(); k++) {
                    try {
                        // 获得field.
                        Field field = fields.get(k);
                        // 设置实体类私有属性可访问
                        field.setAccessible(true);
                        ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                        // 创建cell
                        cell = row.createCell(getExcelCol(attr.column()));
                        cell.setCellType(CellType.STRING);
                        // 如果数据存在就填入,不存在填入空格.
                        if(attr.isAutoIncrement()){
                            cell.setCellValue(i+1);
                            continue;
                        }
                        cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            output.flush();
            workbook.write(output);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static Map<Integer, Field> getFieldOrderMap(List<Field> fields, int maxCol){
        Map<Integer, Field> fieldsMap = new HashMap<>();
        for (Field field : fields) {
            // 将有注解的field存放到map中.
            if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                // 获得列号
                int col = getExcelCol(attr.column());
                maxCol = Math.max(col, maxCol);
                // 设置类的私有字段属性可访问.
                field.setAccessible(true);
                fieldsMap.put(col, field);
            }
        }
        return fieldsMap;
    }

    public static Sheet getSheet(String sheetName, Workbook workbook){
        Sheet sheet = null;
        if (StrKit.notBlank(sheetName)) {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = workbook.getSheet(sheetName);
        }
        // 如果传入的sheet名不存在则默认指向第1个sheet.
        if (DEF_SHEET_FIRST && sheet == null) {
            sheet = workbook.getSheetAt(0);
        }
        return sheet;
    }

    /**
     * 创建sheet
     * @param workbook
     * @param sheetName
     * @return
     */
    public static HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName){
        HSSFSheet sheet = workbook.createSheet(sheetName);
        return sheet;
    }

    /**
     * 创建sheet样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createHSSFCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
        style.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
        // 字体水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    /**
     * 设置shett标题列名
     * @param fields
     * @param sheet
     * @param style
     * @return
     */
    public static HSSFSheet setSheetColumn(List<Field> fields, HSSFSheet sheet, HSSFCellStyle style){
        // 产生单元格
        HSSFCell cell = null;
        HSSFRow row = sheet.createRow(0);
        // 写入各个字段的列头名称
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
            // 获得列号
            int col = getExcelCol(attr.column());
            // 创建列
            cell = row.createCell(col);
            // 设置列中写入内容为String类型
            cell.setCellType(CellType.STRING);
            // 写入列名
            cell.setCellValue(attr.name());
            // 如果设置了提示信息则鼠标放上去提示.
            if (!attr.prompt().trim().equals("")) {
                // 这里默认设了2-101列提示.
                setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);
            }
            // 如果设置了combo属性则本列只能选择不能输入
            if (attr.combo().length > 0) {
                // 这里默认设了2-101列只能选择不能输入.
                setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);
            }
            cell.setCellStyle(style);
        }
        return sheet;
    }

    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,
                                          String promptContent, int firstRow, int endRow, int firstCol,
                                          int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint
                .createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(
                regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
                                              String[] textlist, int firstRow, int endRow, int firstCol,
                                              int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }

    /**
     * 得到实体类所有通过注解映射了数据表的字段
     *
     * 包含父类
     */
    private List<Field> getMappedFiled(Class clazz, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<Field>();
        }
        // 得到所有定义字段
        Field[] allFields = clazz.getDeclaredFields();
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                fields.add(field);
            }
        }
        if (clazz.getSuperclass() != null
                && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), fields);
        }
        return fields;
    }

}
