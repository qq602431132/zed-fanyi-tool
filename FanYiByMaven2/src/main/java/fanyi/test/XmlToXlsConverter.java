package fanyi.test;

 
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class XmlToXlsConverter {
    public static void main(String[] args) {
        try {
            // 解析XML文件
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("input.xml"));  // 这里假设XML文件名为input.xml，你可替换为实际文件名及路径

            // 创建Excel工作簿（.xls格式）
            Workbook workbook = new HSSFWorkbook();

            // 处理BaseDirPath和FileExtensions，存入第一个工作表
            Element root = document.getDocumentElement();
            Element baseDirPathElement = (Element) root.getElementsByTagName("BaseDirPath").item(0);
            Element fileExtensionsElement = (Element) root.getElementsByTagName("FileExtensions").item(0);

            Sheet firstSheet = workbook.createSheet("基础信息");
            Row headerRow = firstSheet.createRow(0);
            headerRow.createCell(0).setCellValue("BaseDirPath");
            headerRow.createCell(1).setCellValue("FileExtensions");

            Row dataRow = firstSheet.createRow(1);
            dataRow.createCell(0).setCellValue(baseDirPathElement.getTextContent());
            dataRow.createCell(1).setCellValue(fileExtensionsElement.getTextContent());

            // 处理ZhengZes下的每个子项，每个子项作为一个单独工作表
            NodeList zhengZesList = root.getElementsByTagName("MZhengZe");
            for (int i = 0; i < zhengZesList.getLength(); i++) {
                Element mZhengZeElement = (Element) zhengZesList.item(i);
                Sheet sheet = workbook.createSheet("MZhengZe_" + (i + 1));

                // 获取并写入各个字段作为表头
                String[] headers = {"ZhengZeStr", "ReplaceStr", "FilesStr", "TagNameStr", "FilePathStr", "LineNumStr", "FanYiStr", "StatusStr"};
                Row headerRowForSubSheet = sheet.createRow(0);
                for (int j = 0; j < headers.length; j++) {
                    headerRowForSubSheet.createCell(j).setCellValue(headers[j]);
                }

                // 获取对应子项中的各个字段值并写入数据行
                Element zhengZeStrElement = (Element) mZhengZeElement.getElementsByTagName("ZhengZeStr").item(0);
                Element replaceStrElement = (Element) mZhengZeElement.getElementsByTagName("ReplaceStr").item(0);
                Element filesStrElement = (Element) mZhengZeElement.getElementsByTagName("FilesStr").item(0);
                Element tagNameStrElement = (Element) mZhengZeElement.getElementsByTagName("TagNameStr").item(0);
                NodeList replacesList = mZhengZeElement.getElementsByTagName("MReplace");
                for (int k = 0; k < replacesList.getLength(); k++) {
                    Element replaceElement = (Element) replacesList.item(k);
                    Row dataRowForSubSheet = sheet.createRow(k + 1);
                    dataRowForSubSheet.createCell(0).setCellValue(zhengZeStrElement.getTextContent());
                    dataRowForSubSheet.createCell(1).setCellValue(replaceStrElement.getTextContent());
                    dataRowForSubSheet.createCell(2).setCellValue(filesStrElement.getTextContent());
                    dataRowForSubSheet.createCell(3).setCellValue(tagNameStrElement.getTextContent());

                    Element filePathStrElement = (Element) replaceElement.getElementsByTagName("FilePathStr").item(0);
                    Element lineNumStrElement = (Element) replaceElement.getElementsByTagName("LineNumStr").item(0);
                    Element fanYiStrElement = (Element) replaceElement.getElementsByTagName("FanYiStr").item(0);
                    Element statusStrElement = (Element) replaceElement.getElementsByTagName("StatusStr").item(0);

                    dataRowForSubSheet.createCell(4).setCellValue(filePathStrElement.getTextContent());
                    dataRowForSubSheet.createCell(5).setCellValue(lineNumStrElement.getTextContent());
                    dataRowForSubSheet.createCell(6).setCellValue(fanYiStrElement.getTextContent());
                    dataRowForSubSheet.createCell(7).setCellValue(statusStrElement.getTextContent());
                }
            }

            // 保存Excel文件
            FileOutputStream outputStream = new FileOutputStream("output.xls");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            System.out.println("转换成功，已生成Excel文件。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转换过程出现错误：" + e.getMessage());
        }
    }
}
