package fanyi.pojo;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author uu
 */
public class MZhengZe {

    public String getTagNameStr() {
        return TagNameStr;
    }

    public void setTagNameStr(String TagNameStr) {
        this.TagNameStr = TagNameStr;
    }

    public String getZhengZeStr() {
        return ZhengZeStr;
    }

    public void setZhengZeStr(String ZhengZeStr) {
        this.ZhengZeStr = ZhengZeStr;
    }

    public String getReplaceStr() {
        return ReplaceStr;
    }

    public void setReplaceStr(String ReplaceStr) {
        this.ReplaceStr = ReplaceStr;
    }

    public String getFilesStr() {
        return FilesStr;
    }

    public void setFilesStr(String FilesStr) {
        this.FilesStr = FilesStr;
    }
    //标签
    String TagNameStr;
    //匹配字符串
    String ZhengZeStr;
    //替换字符串
    String ReplaceStr;
    //匹配到文件
    String FilesStr;
  
}
