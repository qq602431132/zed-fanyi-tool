package fanyi.util;

import fanyi.FanYi;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.swing.SwingUtilities;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileOperator {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 查询文件中是否包含某个字符串
     *
     * @param filePath 文件路径
     * @param target 要查找的目标字符串
     * @return true表示包含，false表示不包含
     */
    public boolean containsString(String filePath, String target) {
        System.out.println("containsString(String filePath:" + filePath + ", String target:" + target + ")");
        lock.readLock().lock();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(target)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 查询文件中是否包含某个字符串，返回所有包含该字符串的行号和行内容
     *
     * @param filePath 文件路径
     * @param target 要查找的目标字符串
     * @return 包含目标字符串的行信息列表，每个元素是包含行号和行内容的数组，第一个元素为行号（从1开始），第二个元素为行内容
     */
    public List<String[]> findStringLines(String filePath, String target) {
        System.out.println("findStringLines(String filePath:" + filePath + ", String target:" + target + ")");
        List<String[]> result = new ArrayList<>();
        lock.readLock().lock();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            int lineNumber = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(target)) {
                    result.add(new String[]{String.valueOf(lineNumber), line});
                }
                lineNumber++;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 替换文件中指定行号和匹配字符串为替换字符串
     *
     * @param filePath 文件路径
     * @param lineNumber 要替换的行号（从1开始）
     * @param target 要替换的目标字符串
     * @param replacement 替换的字符串
     */
    public void replaceStringInLine(String filePath, int lineNumber, String target, String replacement) {
        SwingUtilities.invokeLater(() -> FanYi.jTextArea2.append("转换开始" + filePath + ", int lineNumber:" + lineNumber 
                + " 查找字符:" + target + " 替换字符:" + replacement + "\n"));
//        System.out.println("转换开始(String filePath:" + filePath + ", int lineNumber:" + lineNumber + ",\n String target:" + target + ", String replacement:" + replacement + ")");
        lock.writeLock().lock();
        List<String> newLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            int currentLineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String nl = null;
                if (currentLineNumber == lineNumber) {
                    nl = line.replace(target.trim(), replacement.trim());
                    String r = "原始:" + line + "\n" + "结束:" + nl+"\n转换完成";
                    SwingUtilities.invokeLater(() -> FanYi.jTextArea2.append(r+"\n"));
//                    System.out.println(r);
                } else {
                    nl = line;
                }
                newLines.add(nl);
                currentLineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
            for (String line : newLines) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 更新XLS文件 SheetNum Replace LastTime
     */
    public void replaceTotleXLS(Workbook workbook, String SheetNum, String Replace, String LastTime) {
        //获取第一个sheet
        Sheet firstSheet = workbook.getSheetAt(0);
        if (firstSheet != null) {
            for (Row dataRow : firstSheet) {
                String pipeiStr = dataRow.getCell(0).getStringCellValue();
                if (pipeiStr.equals("SheetNum")) {
                    dataRow.getCell(1).setCellValue(SheetNum);
                } else if (pipeiStr.equals("Replace")) {
                    dataRow.getCell(1).setCellValue(Replace);
                } else if (pipeiStr.equals("LastTime")) {
                    dataRow.getCell(1).setCellValue(LastTime);
                } else {
                    continue;
                }
            }
        }
    }

    public boolean checkStringInLine(String filePath, int lineNumber, String target) {
        boolean result=false;
        SwingUtilities.invokeLater(() -> FanYi.jTextArea2.append("开始检验" + filePath + ", int lineNumber:" + lineNumber 
                + " 查找字符:" + target + "\n"));
        lock.writeLock().lock();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            int currentLineNumber = 1;
            while ((line = reader.readLine()) != null) {
                boolean nl = false;
                if (currentLineNumber == lineNumber) {
                    //找到行之后,检验是否存在字符.
                    nl = line.contains(target.trim());
                    result = nl;
                    String r = "原始:" + line + "\n" + "是否存在字符:" + nl+"";
                    SwingUtilities.invokeLater(() -> FanYi.jTextArea2.append(r+"\n"));
                } 
                currentLineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
