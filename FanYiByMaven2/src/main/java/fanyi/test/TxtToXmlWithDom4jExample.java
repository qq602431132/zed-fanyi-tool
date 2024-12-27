package fanyi.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class TxtToXmlWithDom4jExample {
    public static void main(String[] args) {
        String txtFilePath = "D:\\zed-translation-main\\input.txt"; // 替换为实际的txt文件路径
        String xmlFilePath = "D:\\zed-translation-main\\output.xml"; // 替换为实际的xml文件路径

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(txtFilePath)), StandardCharsets.UTF_8))) {
            List<String[]> keyValuePairs = new ArrayList<>();
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String keys = parts[0].trim();
                    keys = keys.substring(1, keys.length()-1);
                    String vs= parts[1].trim();
                    vs = vs.substring(1,vs.length()-2);
                    keyValuePairs.add(new String[]{keys,vs});
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }

            // 创建XML文档对象
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("Root");

            // 遍历键值对列表，将键值对添加到XML文档中
            for (String[] pair : keyValuePairs) {
                Element trans = root.addElement("trans");
                Element key = trans.addElement("key");
                key.addCDATA(pair[0]);
                Element value = trans.addElement("value");
                value.addCDATA(pair[1]);
            }

            // 设置XML输出格式并写入文件
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter(new File(xmlFilePath)), format);
            writer.write(document);
            writer.close();

            System.out.println("XML file has been generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}