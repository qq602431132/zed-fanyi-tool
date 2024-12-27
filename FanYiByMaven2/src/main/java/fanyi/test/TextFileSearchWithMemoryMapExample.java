package fanyi.test;



import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileSearchWithMemoryMapExample {
    public static boolean containsString(String filePath, String targetString) {
        try (RandomAccessFile file = new RandomAccessFile(new File(filePath), "r");
             FileChannel channel = file.getChannel()) {
            long fileSize = channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
            String content = StandardCharsets.UTF_8.decode(buffer).toString();
            System.out.println("count"+content);
            Pattern pattern = Pattern.compile(targetString);
            Matcher matcher = pattern.matcher(content);
            return matcher.find();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String filePath = "D:\\zed-translation-main\\zed-trans\\zed-src\\crates\\activity_indicator\\src\\activity_indicator.rs"; // 替换为实际的文本文件路径
        String targetString = "点击以重新启动并更新"; // 替换为实际要查找的字符串内容
        boolean result = containsString(filePath, targetString);
        if (result) {
            System.out.println("文件中包含该字符串。");
        } else {
            System.out.println("文件中不包含该字符串。");
        }
    }
}
