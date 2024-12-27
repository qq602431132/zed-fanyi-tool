package fanyi;

import com.formdev.flatlaf.FlatDarkLaf;
import fanyi.pojo.MFanYi;
import fanyi.pojo.MReplace;
import fanyi.pojo.MZhengZe;
import fanyi.util.FileOperator;
import java.awt.Color;

import java.awt.Component;
import java.awt.Desktop;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Yy
 */
public class FanYi extends javax.swing.JFrame {

    File titleFile;
    MFanYi mFanYi;
    FileOperator fo;
    List<File> srcFiles;
    final Vector<MZhengZe> zhengZes;
    final Map<String, Vector<MReplace>> repMap;
    SAXReader reader = new SAXReader();

    public FanYi() {
        titleFile = new File(".");
        fo = new FileOperator();
        zhengZes = new Vector<>();
        repMap = new HashMap<>();
        mFanYi = new MFanYi();
        mFanYi.setZhengZes(zhengZes);
        mFanYi.setRepMap(repMap);
        srcFiles = new ArrayList<>();
        initComponents();
        initTableH(jTable1);
        initTableH(jTable2);
        initButtons(jPanel1);
        initButtons(jPanel2);
        setFocusable(true);
        loadSrcFiles();
        //表格1 内容发生变化时，对应的Vector<MZhengZe> zhengZes进行修改
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                DefaultTableModel dtm = (DefaultTableModel) e.getSource();
                int type = e.getType();
                int row = e.getFirstRow();
                int column = e.getColumn();
                System.out.println("type" + type + "row" + row + "column" + column);
                if (type == TableModelEvent.UPDATE) {
                    if (column != -1) {
                        String value = String.valueOf(dtm.getValueAt(row, column));
                        System.out.println("单元格 (" + row + ", " + column + ") 的值被修改==>" + dtm.getValueAt(row, column));
                        MZhengZe zz = zhengZes.get(row);
                        switch (column) {
                            case 0:
                                zz.setZhengZeStr(value);
                                break;
                            case 1:
                                zz.setReplaceStr(value);
                                break;
                            case 2:
                                zz.setFilesStr(value);
                                break;
                            case 3:
                                zz.setTagNameStr(value);
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                }
//                else if (type == TableModelEvent.INSERT) {
//                    System.out.println("在 " + row + " 行插入了新数据");
//                } 
//                else if (type == TableModelEvent.DELETE) {
//                    System.out.println("删除了 " + row + " 行的数据");
//                    zhengZes.remove(row);
//                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jToolBar3 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jToolBar5 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jSplitPane2.setDividerSize(10);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setResizeWeight(0.8);

        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 539));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jSplitPane1.setDividerLocation(500);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(1000, 103));
        jSplitPane1.setOneTouchExpandable(true);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "匹配字符串", "替换字符串", "匹配到的文件", "标签"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jToolBar2.setRollover(true);

        jButton4.setText("+");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jToolBar2.add(jTextField1);

        jButton5.setText("-");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jButton10.setText("↑");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton10);

        jButton8.setText("↓");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton8);

        jPanel1.add(jToolBar2, java.awt.BorderLayout.NORTH);

        jToolBar4.setRollover(true);

        jLabel3.setText("0/0");
        jToolBar4.add(jLabel3);
        jToolBar4.add(jProgressBar1);

        jLabel5.setText("0/0");
        jToolBar4.add(jLabel5);

        jPanel1.add(jToolBar4, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "文件路径", "行号", "翻译", "状态"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jToolBar3.setRollover(true);

        jButton6.setIcon(new ImageIcon(this.getClass().getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton6);
        jToolBar3.add(jTextField2);

        jPanel2.add(jToolBar3, java.awt.BorderLayout.NORTH);

        jToolBar5.setRollover(true);

        jLabel4.setText("0/0");
        jToolBar5.add(jLabel4);
        jToolBar5.add(jProgressBar2);

        jLabel6.setText("0/0");
        jToolBar5.add(jLabel6);

        jPanel2.add(jToolBar5, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setRightComponent(jPanel2);

        jPanel3.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jToolBar1.setRollover(true);

        jButton7.setText("粘贴正则表达式");
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton3.setText("导入工程");
        jButton3.setEnabled(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator2);

        jTextField3.setText("D:\\test\\zed-0.166.1");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jTextField3);

        jButton1.setText("...");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jLabel7.setText("后缀名：");
        jToolBar1.add(jLabel7);

        jTextField4.setText("*.rs");
        jToolBar1.add(jTextField4);

        jLabel2.setText("文件数");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel2);
        jToolBar1.add(jSeparator3);

        jButton2.setText("保存工程");
        jButton2.setEnabled(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator4);

        jButton9.setText("直接翻译");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton11.setText("从工程文件翻译");
        jButton11.setEnabled(false);
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton12.setText("从XLS文件翻译");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

        jButton13.setText("校验XLS");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);

        jPanel3.add(jToolBar1, java.awt.BorderLayout.NORTH);

        jSplitPane2.setLeftComponent(jPanel3);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jSplitPane2.setRightComponent(jScrollPane4);

        getContentPane().add(jSplitPane2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 用来指定源代码路径 结果 srcFiles = listMatchedFiles(selectedDirectory, regex);
     * jLabel2.setText("文件数：" + srcFiles.size());
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        srcFiles.clear();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            jTextField3.setText(selectedDirectory.getAbsolutePath());
            System.out.println("检索目录==>" + selectedDirectory.getAbsolutePath());
            String regex = convertToRegex(jTextField4.getText().trim());
            //获得所有源码文件
            srcFiles = listMatchedFiles(selectedDirectory, regex);
            jLabel2.setText("文件数：" + srcFiles.size());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * 加载工程文件
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fileChooser = new JFileChooser(titleFile.getParent());
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().trim().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "xml Files (*.xml)";
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File ProFile = fileChooser.getSelectedFile();
            titleFile = ProFile;
            this.setTitle(titleFile.getAbsolutePath());
            //加载工程文件
            loadProjectFile(ProFile, "");
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    /**
     * 单个正则查询匹配文件
     */
    private Vector<MReplace> CheckOneRow(DefaultTableModel tableModel, int index, DefaultTableModel tableModel2, String col2Data, Vector<MReplace> reps) {
        Vector<MReplace> rev = new Vector<>();
        clearTable(jTable2);
        // 正则的名字
        String data1 = tableModel.getValueAt(index, 0).toString();
        Map<File, String> containsFiles = new HashMap<>();
        //把文件名#行号#行内容写入第3列。
        StringBuffer data3 = new StringBuffer();
        int fileNumber = 0;//检索过程到达第几个文件
        for (File srcf : srcFiles) {//遍历所有源码文件
            fileNumber++;
            if (containsString(srcf.getAbsolutePath(), data1)) {
                containsFiles.put(srcf, "begin");
                //遍历源码文件，将匹配的行显示出来。
                try {
                    FileInputStream fis = new FileInputStream(srcf);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    BufferedReader reader = new BufferedReader(isr);
                    String line;
                    int lineNumber = 0;
                    while ((line = reader.readLine()) != null) {
                        lineNumber++;
                        if (line.contains(data1)) {
                            String status = "false";//默认未处理
                            for (MReplace re : reps) {
                                if (srcf.getAbsolutePath().equals(re.getFilePathStr())
                                        && String.valueOf(lineNumber).equals(re.getLineNumStr())) {
                                    //找到了
                                    status = re.getStatusStr();
                                }
                            }
                            MReplace re = new MReplace();
                            re.setFilePathStr(String.valueOf(srcf.getAbsolutePath()));
                            re.setLineNumStr(String.valueOf(lineNumber));
                            re.setFanYiStr(String.valueOf(line.trim() + " ==> "
                                    + line.trim().replace(String.valueOf(data1), String.valueOf(col2Data))));
                            re.setStatusStr(String.valueOf(status + ""));
                            //文件,行号,翻译,状态.
                            SwingUtilities.invokeLater(() -> tableModel2.addRow(new String[]{re.getFilePathStr(), re.getLineNumStr(), re.getFanYiStr(), re.getStatusStr()}));
                            data3.append(srcf.getAbsolutePath().replace(jTextField3.getText().trim(), "") + ":" + re.getLineNumStr() + ";");
                            rev.add(re);
                            SwingUtilities.invokeLater(() -> jLabel4.setText(jTable2.getSelectedRowCount() + "/" + String.valueOf(jTable2.getRowCount())));
                        }
                    }
                    reader.close();
                    isr.close();
                    fis.close();
                    //完成
                    containsFiles.put(srcf, "end");
                    int numtmp = 0;
                    for (Map.Entry<File, String> entry : containsFiles.entrySet()) {
                        if ("end".equals(entry.getValue())) {
                            numtmp++;
                        }
                    }
                    int progress = (int) ((numtmp / (double) containsFiles.size()) * 100);
                    SwingUtilities.invokeLater(() -> jProgressBar2.setValue(progress));
                    jLabel6.setText(numtmp + "/" + String.valueOf(containsFiles.size()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int progress = (int) ((fileNumber / (double) srcFiles.size()) * 100);
            SwingUtilities.invokeLater(() -> jProgressBar1.setValue(progress));
            jLabel5.setText(String.valueOf(fileNumber) + "/" + String.valueOf(srcFiles.size()));
        }
        System.out.println("共检索文件==>" + fileNumber);
        SwingUtilities.invokeLater(() -> tableModel.setValueAt((data3.lastIndexOf(":") != -1 ? data3.substring(0, data3.lastIndexOf(";")) : ""), index, 2));
        return rev;
    }

    /**
     * 检查文件是否存在匹配项
     */
    public boolean containsString(String filePath, String targetString) {
        System.out.println("努力找文件==>" + filePath);
        return fo.containsString(filePath, targetString);
    }
    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        loadProjectFile(null, jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        loadSrcFiles();
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * 表格1匹配字符串
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        if (col == 2) {
            DefaultTableModel tableModel2 = (DefaultTableModel) jTable2.getModel();
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            int index = jTable1.getSelectedRow();
            String col1Data = (String) tableModel.getValueAt(index, 0);
            if (!col1Data.trim().isEmpty()) {
                MZhengZe zz = zhengZes.get(row);
                String col2Data = (String) tableModel.getValueAt(index, 1);
                Vector<MReplace> mReplaces = repMap.get(zz.getZhengZeStr());
                if (mReplaces == null) {
                    //如果不存在
                    Vector<MReplace> t = new Vector<MReplace>();
                    mReplaces = t;
                }
                Vector<MReplace> checkOneRow = CheckOneRow(tableModel, index, tableModel2, col2Data, mReplaces);
                repMap.put(zz.getZhengZeStr(), checkOneRow);
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int[] selectedRows = jTable1.getSelectedRows();
        if (selectedRows.length > 0) {
            List<Integer> selectedRowsList = new ArrayList<>();
            for (int row : selectedRows) {
                selectedRowsList.add(row);
            }
            // 倒序删除，避免索引混乱
            for (int i = selectedRowsList.size() - 1; i >= 0; i--) {
                int index = selectedRowsList.get(i);
                zhengZes.remove(index);
                tableModel.removeRow(index);
            }
            updateRowInfoLabel();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * table1添加空行。
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        MZhengZe newMZhengZe = new MZhengZe();
        newMZhengZe.setZhengZeStr("");
        newMZhengZe.setReplaceStr("");
        newMZhengZe.setFilesStr("");
        newMZhengZe.setTagNameStr("");
        // 使用insertElementAt添加新行数据到zhengZes
        zhengZes.insertElementAt(newMZhengZe, zhengZes.size());
        Vector<Object> newRowData = new Vector<>();
        newRowData.add(newMZhengZe.getZhengZeStr());
        newRowData.add(newMZhengZe.getReplaceStr());
        newRowData.add(newMZhengZe.getFilesStr());
        newRowData.add(newMZhengZe.getTagNameStr());
        // 使用表格模型的addRow方法添加行数据到表格
        tableModel.addRow(newRowData);
        updateRowInfoLabel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // 创建一个文本区域，用于输入多行文本
        JTextArea textArea = new JTextArea();  // 5行20列，可根据实际需求调整
        // 将文本区域放入滚动面板，方便在内容较多时滚动查看
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 800));
        // 使用JOptionPane来弹出对话框，显示包含文本区域的面板
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "请输入多行文本", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String multiLineText = textArea.getText();
            System.out.println("输入的多行文本内容如下：");
            System.out.println(multiLineText);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("llllllllllllll");
        }
    }//GEN-LAST:event_formKeyPressed

    /**
     * 表1发生点击
     */
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        jLabel3.setText(jTable1.getSelectedRowCount() + "/" + String.valueOf(jTable1.getRowCount()));
    }//GEN-LAST:event_jTable1MouseReleased

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        jLabel4.setText(jTable2.getSelectedRowCount() + "/" + String.valueOf(jTable2.getRowCount()));
    }//GEN-LAST:event_jTable2MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //1.执行ctrl+s保存所有翻译特殊项目.
        if (!jTextField3.getText().trim().isEmpty()) {
            mFanYi.setBaseDirPath(jTextField3.getText().trim());
            // 创建文件选择器，设置只显示XML文件类型
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML文件 (*.xml)", "xml");
            fileChooser.setFileFilter(xmlFilter);
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getAbsolutePath().endsWith(".xml")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".xml");
                }
                mFanYi.saveToXML(selectedFile, mFanYi);
                JOptionPane.showMessageDialog(this, "XML file has been generated successfully.");
            }

        } else {
            JOptionPane.showMessageDialog(this, "请填写源码路径.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        Vector<MReplace> res = repMap.get(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0));
        int row = jTable2.rowAtPoint(evt.getPoint());
        int col = jTable2.columnAtPoint(evt.getPoint());
        DefaultTableModel tableModel2 = (DefaultTableModel) jTable2.getModel();
        if (col == 3) {
            int index = jTable2.getSelectedRow();
            MReplace mr = res.get(index);
            boolean col1Data = Boolean.valueOf((String) tableModel2.getValueAt(index, 3));
            SwingUtilities.invokeLater(() -> tableModel2.setValueAt(!col1Data + "", index, 3));
            mr.setStatusStr(String.valueOf(!col1Data));
            res.set(index, mr);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    /**
     * 翻译操作事件
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //开始翻译 收集所有要翻译的项目.
        int numtmp = 0;
        for (Map.Entry<String, Vector<MReplace>> entry : repMap.entrySet()) {
            Vector<MReplace> mrs = entry.getValue();
            for (MReplace mr : mrs) {
                if (mr.getStatusStr().equals("true")) {
                    String[] ss = mr.getFanYiStr().split("==>");
                    int li = Integer.valueOf(mr.getLineNumStr().trim());
                    String targstr = ss[0];
                    String repstr = ss[1];
                    System.out.println("targstr" + targstr
                            + "\nrepstr:" + repstr);
                    fo.replaceStringInLine(mr.getFilePathStr(), li, targstr, repstr);
                    System.out.println("修改完成");
                }
            }
        }
        JOptionPane.showMessageDialog(this, "修改完成.");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //打开文件所在目录
        int row = jTable2.getSelectedRow();
        if (row >= 0) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable2.getModel();
            String filePath = (String) tableModel.getValueAt(row, 0);
            File file = new File(filePath);
            File parentDirectory = file.getParentFile();
            if (parentDirectory != null) {
                try {
                    Desktop.getDesktop().open(parentDirectory);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "无法打开文件所在目录: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int[] selectedRows = jTable1.getSelectedRows();
        if (selectedRows.length > 0) {
            java.util.Arrays.sort(selectedRows);
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int row = selectedRows[i];
                if (row < jTable1.getRowCount() - 1) {
                    // 在zhengZes中交换数据
                    MZhengZe currentMZhengZe = zhengZes.get(row);
                    MZhengZe nextMZhengZe = zhengZes.get(row + 1);
                    zhengZes.set(row + 1, currentMZhengZe);
                    zhengZes.set(row, nextMZhengZe);

                    // 在表格模型中交换行数据
                    Vector<Vector<Object>> dataVector = tableModel.getDataVector();
                    Vector<Object> currentRowData = dataVector.get(row);
                    Vector<Object> nextRowData = dataVector.get(row + 1);
                    dataVector.set(row + 1, currentRowData);
                    dataVector.set(row, nextRowData);
                }
            }
            tableModel.fireTableDataChanged();
            updateRowInfoLabel();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int[] selectedRows = jTable1.getSelectedRows();
        if (selectedRows.length > 0) {
            // 对选中行索引进行排序，确保按顺序操作
            java.util.Arrays.sort(selectedRows);
            for (int row : selectedRows) {
                if (row > 0) {
                    // 在zhengZes中交换数据
                    MZhengZe currentMZhengZe = zhengZes.get(row);
                    MZhengZe prevMZhengZe = zhengZes.get(row - 1);
                    zhengZes.set(row - 1, currentMZhengZe);
                    zhengZes.set(row, prevMZhengZe);
                    // 在表格模型中交换行数据
                    Vector<Vector<Object>> dataVector = tableModel.getDataVector();
                    Vector<Object> currentRowData = dataVector.get(row);
                    Vector<Object> prevRowData = dataVector.get(row - 1);
                    dataVector.set(row - 1, currentRowData);
                    dataVector.set(row, prevRowData);
                }
            }
            tableModel.fireTableDataChanged();
            updateRowInfoLabel();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().trim().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "xml Files (*.xml)";
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File ProFile = fileChooser.getSelectedFile();
            //翻译工程文件
            fanYiProjectFile(ProFile);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        jTable2.setFocusable(false);
        jTable2.setFocusable(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().trim().endsWith(".xls");
            }

            @Override
            public String getDescription() {
                return "xls Files (*.xls)";
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File xlsFile = fileChooser.getSelectedFile();
            try {
                FileInputStream inputStream = new FileInputStream(xlsFile);  // 读取刚才生成的output.xls文件，确保文件名及路径正确
                Workbook workbook = new HSSFWorkbook(inputStream);
                Map<String, MZhengZe> yingShe = new HashMap();
                String baseDirPath = "";
                //获取第一个sheet
                Sheet firstSheet = workbook.getSheetAt(0);
                if (firstSheet != null) {
                    Row r1 = firstSheet.getRow(1);
                    baseDirPath = r1.getCell(1).getStringCellValue();
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
                    for (Row row : sheet) {
                        //整合到Map里面 行唯一比较安全,正则唯一,有可能多个正则匹配到同一行,导致该行被多次修改.多次修改肯定不行.
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        if (row.getLastCellNum() != 4) {
                            System.out.println("er:" + sheet.getSheetName() + ":" + row.getRowNum() + ":" + row.getLastCellNum());
                        }
                        String pipeiStr = row.getCell(0).getStringCellValue();
                        String tihuanStr = row.getCell(1).getStringCellValue();
                        String[] filePaths = row.getCell(2).getStringCellValue().split(";");
                        for (String fp : filePaths) {
                            MZhengZe zz = new MZhengZe();
                            zz.setZhengZeStr(pipeiStr);
                            zz.setReplaceStr(tihuanStr);
                            zz.setFilesStr(fp);
                            if (row.getLastCellNum() == 4) {
                                String tagStr = row.getCell(3).getStringCellValue();
                                zz.setTagNameStr(tagStr);
                            }
                            if (yingShe.keySet().contains(fp)) {
                                MZhengZe oze = yingShe.get(fp);
                                System.out.println("发现冲突:" + sheet.getSheetName() + ":" + row.getRowNum() + "\n"
                                        + "当前 " + zz.getFilesStr() + zz.getZhengZeStr() + " -- "
                                        + "对比 " + oze.getFilesStr() + oze.getZhengZeStr());

                            } else {
                                yingShe.put(fp, zz);
                            }
                        }
                    }
                }
                workbook.close();
                inputStream.close();
                for (Map.Entry<String, MZhengZe> entry : yingShe.entrySet()) {
                    String[] key = entry.getKey().split(":");
                    MZhengZe value = entry.getValue();
                    String filePathStr = baseDirPath + key[0];
                    int lineNum = Integer.valueOf(key[1]);
                    String targstr = value.getZhengZeStr();
                    String repstr = value.getReplaceStr();
                    fo.replaceStringInLine(filePathStr, lineNum, targstr, repstr);
                }
                JOptionPane.showMessageDialog(this, "修改完成.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "读取Excel文件出现错误：" + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().trim().endsWith(".xls");
            }

            @Override
            public String getDescription() {
                return "xls Files (*.xls)";
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File xlsFile = fileChooser.getSelectedFile();
            try {
                FileInputStream inputStream = new FileInputStream(xlsFile);  // 读取刚才生成的output.xls文件，确保文件名及路径正确
                Workbook workbook = new HSSFWorkbook(inputStream);
                Map<String, MZhengZe> yingShe = new HashMap();
                Map<String, MZhengZe> errorMap = new HashMap();
                String baseDirPath = "";
                //获取第一个sheet
                Sheet firstSheet = workbook.getSheetAt(0);
                if (firstSheet != null) {
                    Row r1 = firstSheet.getRow(1);
                    baseDirPath = r1.getCell(1).getStringCellValue();
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
                    for (Row row : sheet) {
                        //整合到Map里面 行唯一比较安全,正则唯一,有可能多个正则匹配到同一行,导致该行被多次修改.多次修改肯定不行.
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        if (row.getLastCellNum() != 4) {
                            System.out.println("er:" + sheet.getSheetName() + ":" + row.getRowNum() + ":" + row.getLastCellNum());
                        }
                        String pipeiStr = row.getCell(0).getStringCellValue();
                        String tihuanStr = row.getCell(1).getStringCellValue();
                        String[] filePaths = row.getCell(2).getStringCellValue().split(";");
                        for (String fp : filePaths) {
                            MZhengZe zz = new MZhengZe();
                            zz.setZhengZeStr(pipeiStr);
                            zz.setReplaceStr(tihuanStr);
                            zz.setFilesStr(fp);
                            if (row.getLastCellNum() >= 4) {
                                String tagStr = row.getCell(3).getStringCellValue();
                                zz.setTagNameStr(tagStr);
                            }
                            if (yingShe.keySet().contains(fp)) {
                                MZhengZe oze = yingShe.get(fp);
                                System.out.println("发现冲突:" + sheet.getSheetName() + ":" + row.getRowNum() + "\n"
                                        + "当前 " + zz.getFilesStr() + zz.getZhengZeStr() + " -- "
                                        + "对比 " + oze.getFilesStr() + oze.getZhengZeStr());

                            } else {
                                yingShe.put(fp, zz);
                            }
                        }
                    }
                }
                workbook.close();
                inputStream.close();
                //以上都是读取XLS文件,不用管
                for (Map.Entry<String, MZhengZe> entry : yingShe.entrySet()) {
                    String[] key = entry.getKey().split(":");
                    MZhengZe value = entry.getValue();
                    String filePathStr = baseDirPath + key[0];
                    int lineNum = Integer.valueOf(key[1]);
                    String targstr = value.getZhengZeStr();
                    String repstr = value.getReplaceStr();
                    boolean bflag = fo.checkStringInLine(filePathStr, lineNum, targstr);
                    if (!bflag) {
                        errorMap.put(entry.getKey(), entry.getValue());
                    }
                }
                //自动修正
                clearTable(jTable1);
                clearTable(jTable2);
                for (Map.Entry<String, MZhengZe> entry : errorMap.entrySet()) {
                    String[] key = entry.getKey().split(":");
                    MZhengZe zz = entry.getValue();
                    String filePathStr = baseDirPath + key[0];
                    int lineNum = Integer.valueOf(key[1]);
                    System.out.println(key + "\t" + zz.getZhengZeStr() + "\t" + zz.getReplaceStr() + "\t" + zz.getTagNameStr());
                    DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                    rePickOneRow(tableModel, zz, filePathStr, lineNum);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "读取Excel文件出现错误：" + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * 获得所有源码文件
     */
    public List<File> listMatchedFiles(File directory, String regex) {
        List<File> result = new ArrayList<>();
        File[] files = directory.listFiles();
        if (!".*\\.()$".equals(regex)) {
            Pattern pattern = Pattern.compile(regex);
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        result.addAll(listMatchedFiles(file, regex));
                    } else {
                        Matcher matcher = pattern.matcher(file.getName());
                        if (matcher.matches()) {
                            result.add(file);
                        }
                    }
                }
            }
        } else {
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        result.addAll(listMatchedFiles(file, regex));
                    } else {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }

    //  ".*\\.(java|rs)$"
    public String convertToRegex(String fileExtensions) {
        String[] extensions = fileExtensions.trim().split(",");
        StringBuilder regexBuilder = new StringBuilder(".*\\.(");
        for (int i = 0; i < extensions.length; i++) {
            String ext = extensions[i].trim().replace("*.", "");
            if (i > 0) {
                regexBuilder.append("|");
            }
            regexBuilder.append(ext);
        }
        regexBuilder.append(")$");
        return regexBuilder.toString();
    }

    public void clearTable(JTable table) {
        if (table != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            if (model != null) {
                table.setFocusable(false);
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                table.setFocusable(true);
            }
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FanYi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    // End of variables declaration//GEN-END:variables

    /**
     * 加载工程文件
     */
    private void loadProjectFile(File selectedFile, String searchText) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel tableMode2 = (DefaultTableModel) jTable2.getModel();
        try {
            if (selectedFile != null) {
                // 先清除表格1数据
                clearTable(jTable1);
                clearTable(jTable2);
                // 清楚现有数据
                zhengZes.clear();
                repMap.clear();
                // 加载工程文件
                Document document = reader.read(new InputStreamReader(new FileInputStream(selectedFile)));
                Element root = document.getRootElement();
                List<Element> MZhengZeElements = root.element("ZhengZes").elements("MZhengZe");
                for (Element mZhengZe : MZhengZeElements) {
                    //单个正则
                    Element zhengZeStrElement = mZhengZe.element("ZhengZeStr");
                    Element replaceStrElement = mZhengZe.element("ReplaceStr");
                    Element filesStrElement = mZhengZe.element("FilesStr");
                    Element tagNameStrElement = mZhengZe.element("TagNameStr");
                    MZhengZe newMZhengZe = new MZhengZe();
                    newMZhengZe.setTagNameStr(tagNameStrElement.getText().trim());
                    newMZhengZe.setZhengZeStr(zhengZeStrElement.getText().trim());
                    newMZhengZe.setReplaceStr(replaceStrElement.getText().trim());
                    newMZhengZe.setFilesStr(filesStrElement.getText().trim());
                    // 使用insertElementAt添加新行数据到zhengZes
                    zhengZes.insertElementAt(newMZhengZe, zhengZes.size());
                    Vector<Object> newRowData = new Vector<>();
                    newRowData.add(newMZhengZe.getZhengZeStr());
                    newRowData.add(newMZhengZe.getReplaceStr());
                    newRowData.add(newMZhengZe.getFilesStr());
                    newRowData.add(newMZhengZe.getTagNameStr());
                    // 使用表格模型的addRow方法添加行数据到表格
                    tableModel.addRow(newRowData);// 每个正则对应表格一行
                    updateRowInfoLabel();
                    // 获得一个正则的所有翻译列表
                    List<Element> MReplaceElements = mZhengZe.element("Replaces").elements("MReplace");
                    Vector<MReplace> value = new Vector<>();
                    for (Element mReplace : MReplaceElements) {
                        // 表格2里面的一行,不需要放到表格2里而是要放到当前数据里.
                        // 当前数据中,repMap是用来存储表格2的. Map<String,Vector<MReplace>> 
                        MReplace mmr = new MReplace();
                        mmr.setFilePathStr(String.valueOf(mReplace.element("FilePathStr").getText().trim()));
                        mmr.setLineNumStr(String.valueOf(mReplace.element("LineNumStr").getText().trim()));
                        mmr.setFanYiStr(String.valueOf(mReplace.element("FanYiStr").getText().trim()));
                        mmr.setStatusStr(String.valueOf(mReplace.element("StatusStr").getText().trim()));
                        value.add(mmr);
                    }
                    repMap.put(newMZhengZe.getZhengZeStr(), value);
                }
                jTable1.putClientProperty("TABLE_PROPERTIES", selectedFile);
                loadProjectFile(null, jTextField1.getText().trim());
            } else {
                if (!"".equals(searchText)) {
                    // 创建TableRowSorter用于排序和筛选（这里主要用于筛选隐藏行）
                    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                    jTable1.setRowSorter(sorter);
                    // 根据输入的检索字符串构建行筛选器，进行模糊匹配筛选
                    sorter.setRowFilter(RowFilter.regexFilter(".*" + searchText + ".*", 0, 1, 2));
                } else {
                    System.out.println("空检索");
                    jTable1.setRowSorter(null);
//                    jTable1.setModel(tableModel);
//                    jTable1.updateUI();
//                    initTableH(jTable1);
//                    initTableH(jTable2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initTableH(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        // 设置表格线显示
        table.setShowGrid(true);
        table.setGridColor(Color.GRAY);
        // 设置行高
        table.setRowHeight(30);
        // 获取表格的列模型
        TableColumnModel columnModel = table.getColumnModel();
        if (table == jTable1) {
            // 设置第三列的宽度为500像素
            columnModel.getColumn(2).setPreferredWidth(400);
            columnModel.getColumn(1).setPreferredWidth(200);
            columnModel.getColumn(0).setPreferredWidth(200);
        } else if (table == jTable2) {
            // 设置第三列的宽度为500像素
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(2).setPreferredWidth(300);
            columnModel.getColumn(1).setPreferredWidth(50);
            columnModel.getColumn(0).setPreferredWidth(150);
        }
        for (int ci = 0; ci < columnModel.getColumnCount(); ci++) {
            TableColumn thirdColumn = columnModel.getColumn(ci);
            thirdColumn.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JComponent component = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    component.setToolTipText(String.valueOf(value));
                    return component;
                }
            });
        }
    }

    private void initButtons(JComponent c) {
        for (Component component : c.getComponents()) {
            if (component instanceof JButton) {
                component.setPreferredSize(new Dimension(component.getPreferredSize().width, 50));
            } else if (component instanceof JTextField) {
                component.setPreferredSize(new Dimension(component.getPreferredSize().width, 50));
            } else if (component instanceof JLabel) {
                component.setPreferredSize(new Dimension(component.getPreferredSize().width, 50));
            }
        }

    }

    /**
     * 获得所有源码文件
     */
    private void loadSrcFiles() {
        //初始化数据,如果 源码路径和后缀名不为空,加载文件数量
        if ((!jTextField3.getText().trim().isEmpty()) && (!jTextField4.getText().trim().isEmpty())) {
            mFanYi.setBaseDirPath(jTextField3.getText().trim());
            mFanYi.setFileExtension(jTextField4.getText().trim());
            srcFiles.clear();
            File selectedDirectory = new File(jTextField3.getText());
            jTextField3.setText(selectedDirectory.getAbsolutePath());
            System.out.println("检索目录==>" + selectedDirectory.getAbsolutePath());
            String regex = convertToRegex(jTextField4.getText().trim());
            //获得所有源码文件
            srcFiles = listMatchedFiles(selectedDirectory, regex);
            jLabel2.setText("文件数：" + srcFiles.size());
        }
    }

    private void updateRowInfoLabel() {
        SwingUtilities.invokeLater(() -> jLabel3.setText(jTable1.getSelectedRowCount() + "/" + String.valueOf(jTable1.getRowCount())));
    }

    private void fanYiProjectFile(File ProFile) {
        try {
            if (ProFile != null) {
                SAXReader reader = new SAXReader();
                // 加载工程文件
                Document document = reader.read(new InputStreamReader(new FileInputStream(ProFile)));
                Element root = document.getRootElement();
                List<Element> MZhengZeElements = root.element("ZhengZes").elements("MZhengZe");
                for (Element mZhengZe : MZhengZeElements) {
                    //单个正则
                    Element zhengZeStrElement = mZhengZe.element("ZhengZeStr");
                    Element replaceStrElement = mZhengZe.element("ReplaceStr");
                    Element filesStrElement = mZhengZe.element("FilesStr");
                    Element tagNameStrElement = mZhengZe.element("TagNameStr");
                    MZhengZe newMZhengZe = new MZhengZe();
                    newMZhengZe.setTagNameStr(tagNameStrElement.getText().trim());
                    newMZhengZe.setZhengZeStr(zhengZeStrElement.getText().trim());
                    newMZhengZe.setReplaceStr(replaceStrElement.getText().trim());
                    newMZhengZe.setFilesStr(filesStrElement.getText().trim());
                    // 获得一个正则的所有翻译列表
                    List<Element> MReplaceElements = mZhengZe.element("Replaces").elements("MReplace");

                    for (Element mReplace : MReplaceElements) {
                        // 表格2里面的一行,不需要放到表格2里而是要放到当前数据里.
                        // 当前数据中,repMap是用来存储表格2的. Map<String,Vector<MReplace>> 
                        MReplace mmr = new MReplace();
                        mmr.setFilePathStr(String.valueOf(mReplace.element("FilePathStr").getText().trim()));
                        mmr.setLineNumStr(String.valueOf(mReplace.element("LineNumStr").getText().trim()));
                        mmr.setFanYiStr(String.valueOf(mReplace.element("FanYiStr").getText().trim()));
                        mmr.setStatusStr(String.valueOf(mReplace.element("StatusStr").getText().trim()));
                        if (mmr.getStatusStr().equals("true")) {
                            String[] ss = mmr.getFanYiStr().split("==>");
                            int li = Integer.valueOf(mmr.getLineNumStr().trim());
                            String targstr = ss[0];
                            String repstr = ss[1];
                            System.out.println("targstr" + targstr
                                    + "\nrepstr:" + repstr);
                            fo.replaceStringInLine(mmr.getFilePathStr(), li, targstr, repstr);
                            System.out.println("修改完成");
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "修改完成.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rePickOneRow(DefaultTableModel tableModel, MZhengZe zz, String srcf, int lineNum) {
        String data1 = zz.getZhengZeStr();// 正则的名字
        Map<String, String> containsFiles = new HashMap<>();//包含的文件
        StringBuffer data3 = new StringBuffer();//把文件名#行号#行内容写入第3列。
        int fileNumber = 0;//检索过程到达第几个文件
        fileNumber++;
        if (containsString(srcf, data1)) {
            containsFiles.put(srcf, "begin");
            try {
                FileInputStream fis = new FileInputStream(srcf);//遍历源码文件，将匹配的行显示出来。
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader reader = new BufferedReader(isr);
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (line.contains(data1)) {
                        MReplace re = new MReplace();
                        re.setFilePathStr(String.valueOf(srcf));
                        re.setLineNumStr(String.valueOf(lineNumber));
                        data3.append(srcf.replace(jTextField3.getText().trim(), "") + ":" + re.getLineNumStr() + ";");
                    }
                }
                reader.close();
                isr.close();
                fis.close();
                //完成
                containsFiles.put(srcf, "end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("共检索文件==>" + fileNumber);
        zz.setFilesStr(data3.substring(0, data3.lastIndexOf(";")));
        Vector<Object> newRowData = new Vector<>();
        newRowData.add(zz.getZhengZeStr());
        newRowData.add(zz.getReplaceStr());
        newRowData.add(zz.getFilesStr());
        newRowData.add(srcf + ":" + lineNum);
        // 使用表格模型的addRow方法添加行数据到表格
        tableModel.addRow(newRowData);
        updateRowInfoLabel();
    }
}
