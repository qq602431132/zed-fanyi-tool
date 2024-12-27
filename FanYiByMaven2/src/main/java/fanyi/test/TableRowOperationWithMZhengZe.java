package fanyi.test;



import fanyi.pojo.MZhengZe;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TableRowOperationWithMZhengZe {
    private static Vector<MZhengZe> zhengZes = new Vector<>();
    private static JTable table;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 创建窗口
            JFrame frame = new JFrame("Table Row Operations with MZhengZe Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // 初始化示例数据（可按需修改）
            initData();

            // 定义列名
            Vector<String> columnNames = new Vector<>();
            columnNames.add("标签");
            columnNames.add("匹配字符串");
            columnNames.add("替换字符串");
            columnNames.add("匹配到文件");

            // 根据MZhengZe对象集合创建表格数据向量
            Vector<Vector<Object>> data = new Vector<>();
            for (MZhengZe mZhengZe : zhengZes) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(mZhengZe.getTagNameStr());
                rowData.add(mZhengZe.getZhengZeStr());
                rowData.add(mZhengZe.getReplaceStr());
                rowData.add(mZhengZe.getFilesStr());
                data.add(rowData);
            }

            // 创建表格模型
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            // 创建JTable
            table = new JTable(tableModel);

            // 创建用于显示行数信息的标签
            JLabel rowInfoLabel = new JLabel();
            updateRowInfoLabel();

            // 创建按钮面板
            JPanel buttonPanel = new JPanel();

            // 创建增加行按钮及添加事件监听器
            JButton addRowButton = new JButton("增加行");
            addRowButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MZhengZe newMZhengZe = new MZhengZe();
                    newMZhengZe.setTagNameStr("新标签");
                    newMZhengZe.setZhengZeStr("新匹配字符串");
                    newMZhengZe.setReplaceStr("新替换字符串");
                    newMZhengZe.setFilesStr("新文件.txt");

                    zhengZes.add(newMZhengZe);

                    Vector<Object> newRowData = new Vector<>();
                    newRowData.add(newMZhengZe.getTagNameStr());
                    newRowData.add(newMZhengZe.getZhengZeStr());
                    newRowData.add(newMZhengZe.getReplaceStr());
                    newRowData.add(newMZhengZe.getFilesStr());

                    tableModel.addRow(newRowData);
                    updateRowInfoLabel();
                }
            });
            buttonPanel.add(addRowButton);

            // 创建删除行按钮及添加事件监听器
            JButton deleteRowButton = new JButton("删除行");
            deleteRowButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] selectedRows = table.getSelectedRows();
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
                }
            });
            buttonPanel.add(deleteRowButton);

            // 创建上移多行按钮及添加事件监听器
            JButton moveUpButton = new JButton("上移多行");
            moveUpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] selectedRows = table.getSelectedRows();
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
                }
            });
            buttonPanel.add(moveUpButton);

            // 创建下移多行按钮及添加事件监听器
            JButton moveDownButton = new JButton("下移多行");
            moveDownButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] selectedRows = table.getSelectedRows();
                    if (selectedRows.length > 0) {
                        java.util.Arrays.sort(selectedRows);
                        for (int i = selectedRows.length - 1; i >= 0; i--) {
                            int row = selectedRows[i];
                            if (row < table.getRowCount() - 1) {
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
                }
            });
            buttonPanel.add(moveDownButton);

            // 将表格添加到滚动面板
            JScrollPane scrollPane = new JScrollPane(table);

            // 将行数信息标签和按钮面板添加到另一个面板，方便布局
            JPanel infoAndButtonPanel = new JPanel(new BorderLayout());
            infoAndButtonPanel.add(rowInfoLabel, BorderLayout.WEST);
            infoAndButtonPanel.add(buttonPanel, BorderLayout.EAST);

            // 将滚动面板和 infoAndButtonPanel添加到窗口
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(infoAndButtonPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        });
    }

    private static void initData() {
        MZhengZe mZhengZe1 = new MZhengZe();
        mZhengZe1.setTagNameStr("Tag1");
        mZhengZe1.setZhengZeStr("Pattern1");
        mZhengZe1.setReplaceStr("Replace1");
        mZhengZe1.setFilesStr("File1.txt");
        zhengZes.add(mZhengZe1);

        MZhengZe mZhengZe2 = new MZhengZe();
        mZhengZe2.setTagNameStr("Tag2");
        mZhengZe2.setZhengZeStr("Pattern2");
        mZhengZe2.setReplaceStr("Replace2");
        mZhengZe2.setFilesStr("File2.txt");
        zhengZes.add(mZhengZe2);
    }

    private static void updateRowInfoLabel() {
        int selectedRowCount = table.getSelectedRowCount();
        int totalRowCount = table.getRowCount();
        String rowInfo = "选择的行数/总行数: " + selectedRowCount + "/" + totalRowCount;
//        ((JLabel) table.getParent().getParent().getComponent(1).getComponent(0)).setText(rowInfo);
    }
}