package fanyi.test;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlParseAndPrintExample {
    public static void main(String[] args) {
        String xmlFilePath = "D:\\zed-translation-main\\output.xml"; // 替换为实际的xml文件路径

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new InputStreamReader(new FileInputStream(new File(xmlFilePath))));
            Element root = document.getRootElement();
            List<Element> transElements = root.elements("trans");
            for (Element trans : transElements) {
                Element keyElement = trans.element("key");
                Element valueElement = trans.element("value");
                System.out.println("键: " + keyElement.getText() + ", 值: " + valueElement.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}