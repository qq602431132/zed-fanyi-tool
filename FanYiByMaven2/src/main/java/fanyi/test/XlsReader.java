package fanyi.test;




import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class XlsReader {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("output.xls");  // 读取刚才生成的output.xls文件，确保文件名及路径正确
            Workbook workbook = new HSSFWorkbook(inputStream);

            // 读取第一个工作表（基础信息工作表）
            Sheet firstSheet = workbook.getSheet("基础信息");
            if (firstSheet!= null) {
                for (Row row : firstSheet) {
                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t");
                                break;
                            default:
                                System.out.print(cell + "\t");
                        }
                    }
                    System.out.println();
                }
            }

            // 读取其他工作表（每个MZhengZe对应工作表）
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 1; i < numberOfSheets; i++) {  // 从索引1开始，跳过第一个工作表（基础信息工作表）
                Sheet sheet = workbook.getSheetAt(i);
                System.out.println("工作表名称: " + sheet.getSheetName());
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t");
                                break;
                            default:
                                System.out.print(cell + "\t");
                        }
                    }
                    System.out.println();
                }
            }

            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取Excel文件出现错误：" + e.getMessage());
        }
    }
}
