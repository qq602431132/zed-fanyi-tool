package fanyi.test;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileReplaceExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("D:\\Personal\\Documents\\1\\ss\\b2.rs"); // 要操作的文件路径，这里假设文件在项目根目录下名为input.txt，可根据实际情况修改
        String targetString = "Dart"; // 要匹配的字符串
        String replacementString = "打他"; // 用于替换的字符串

        List<String> lines;
        try {
            // 按UTF-8编码读取文件的所有行到List中
            lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<String> newLines = new ArrayList<>();
        for (String line : lines) {
            // 替换每行中匹配的字符串
            String replacedLine = line.replace(targetString, replacementString);
            newLines.add(replacedLine);
        }

        try {
            // 将替换后的内容以UTF-8编码写回原文件（会覆盖原文件内容）
            Files.write(filePath, newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
