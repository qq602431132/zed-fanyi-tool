package fanyi.pojo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author uu
 */
public class MFanYi {

    public String getBaseDirPath() {
        return BaseDirPath;
    }

    public void setBaseDirPath(String BaseDirPath) {
        this.BaseDirPath = BaseDirPath;
    }

    public String getFileExtension() {
        return FileExtensions;
    }

    public void setFileExtension(String FileExtension) {
        this.FileExtensions = FileExtension;
    }

    private String BaseDirPath;
    private String FileExtensions;
    private List<MZhengZe> ZhengZes;
    private Map<String,Vector<MReplace>> RepMap;

    public List<MZhengZe> getZhengZes() {
        return ZhengZes;
    }

    public void setZhengZes(List<MZhengZe> ZhengZes) {
        this.ZhengZes = ZhengZes;
    }

    public Map<String, Vector<MReplace>> getRepMap() {
        return RepMap;
    }

    public void setRepMap(Map<String, Vector<MReplace>> RepMap) {
        this.RepMap = RepMap;
    }


    public String getFileExtensions() {
        return FileExtensions;
    }

    public void setFileExtensions(String FileExtensions) {
        this.FileExtensions = FileExtensions;
    }

    public void saveToXML(File xmlFilePath, MFanYi mfy) {
        try {
            
            // 创建XML文档对象
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("Root");
            Element bdpElement = root.addElement("BaseDirPath");
            bdpElement.addCDATA(mfy.getBaseDirPath());
            Element fesElement = root.addElement("FileExtensions");
            fesElement.addCDATA(mfy.getFileExtension());
            Element zeElement = root.addElement("ZhengZes");
            //添加正则表格
            List<MZhengZe> dataVector = mfy.getZhengZes();
            Map<String,Vector<MReplace>> mrep = mfy.getRepMap();
            // 获取表格数据行并添加到XML
            for (MZhengZe rowData : dataVector) {
                Element oneRow = zeElement.addElement("MZhengZe");
                oneRow.addElement("ZhengZeStr").addCDATA(rowData.getZhengZeStr());
                oneRow.addElement("ReplaceStr").addCDATA(rowData.getReplaceStr());
                oneRow.addElement("FilesStr").addCDATA(rowData.getFilesStr());
                oneRow.addElement("TagNameStr").addCDATA(rowData.getTagNameStr());
                Element repsElement = oneRow.addElement("Replaces");
                Vector<MReplace> mreps = mrep.get(rowData.getZhengZeStr());
                if(mreps!=null&&mreps.size()>0){
                    for (MReplace rep : mreps) {
                    Element oneRep = repsElement.addElement("MReplace");
                    oneRep.addElement("FilePathStr").addCDATA(rep.getFilePathStr());
                    oneRep.addElement("LineNumStr").addCDATA(rep.getLineNumStr());
                    oneRep.addElement("FanYiStr").addCDATA(rep.getFanYiStr());
                    oneRep.addElement("StatusStr").addCDATA(rep.getStatusStr());
                }
                }
                
            }
            // 设置XML输出格式并写入文件
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter(xmlFilePath), format);
            writer.write(document);
            writer.close();
            System.out.println("XML file has been generated successfully.");
            JOptionPane.showMessageDialog(null, "XML file has been generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
