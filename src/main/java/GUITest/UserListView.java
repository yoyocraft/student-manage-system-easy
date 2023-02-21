package GUITest;


import pojo.Student;
import service.studentService;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class UserListView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField sidText;

    private studentService service = new studentService();


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserListView frame = new UserListView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public UserListView() {

        setTitle("学生信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 337);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 564, 232);
        contentPane.add(scrollPane);

        Object[] columns = {"学号", "名字", "年龄", "专业", "班级", "偏爱学科"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        //加载学生数据
        load(null);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("学号");
        lblNewLabel.setBounds(10, 10, 42, 15);
        contentPane.add(lblNewLabel);

        sidText = new JTextField();
        sidText.setBounds(44, 8, 115, 21);
        contentPane.add(sidText);
        sidText.setColumns(10);

        //查看按钮
        JButton searchBtn = new JButton("查看");
        searchBtn.addActionListener(e -> load(sidText.getText()));

        searchBtn.setBounds(300, 8, 63, 23);
        contentPane.add(searchBtn);

        //搜索学生
        JButton searchBtn1 = new JButton("搜索");
        searchBtn1.addActionListener(e -> search(Integer.parseInt(sidText.getText())));

        searchBtn1.setBounds(169, 8, 63, 23);
        contentPane.add(searchBtn1);

        //添加按钮
        JButton addBtn = new JButton("添加");
        addBtn.addActionListener(e -> {
            AddView view = new AddView();
            view.setVisible(true);
        });
        addBtn.setBounds(365, 8, 63, 23);

        contentPane.add(addBtn);

        //修改按钮
        JButton updateBtn = new JButton("修改");
        updateBtn.addActionListener(e -> {
            // 获取选中行
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.valueOf(table.getValueAt(row, 0).toString());
            UpdateView view = new UpdateView(id);
            view.setVisible(true);

        });
        updateBtn.setBounds(438, 8, 63, 23);

        //删除按钮
        JButton deleteBtn = new JButton("删除");
        deleteBtn.addActionListener(e -> {
            // 获取选中行
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int result = JOptionPane.showConfirmDialog(contentPane, "确认删除该学生吗？", "提示",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                int sid = Integer.valueOf(table.getValueAt(row, 0).toString());
                //删除数据库中的记录
                service.delete(sid);
                JOptionPane.showMessageDialog(contentPane, "删除成功!");
                load(null);
            }
        });
        deleteBtn.setBounds(511, 8, 63, 23);
        contentPane.add(deleteBtn);
        contentPane.add(updateBtn);
    }

    // 加载全部数据
    public void load(String sid) {
        List<Student> list = service.selectAll();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        // 填充数据
        for (Student item : list) {
            String[] arr = new String[6];
            arr[0] = item.getSid() + "";
            arr[1] = item.getName();
            arr[2] = String.valueOf(item.getAge());
            arr[3] = item.getMajor();
            arr[4] = item.getGrade();
            arr[5] = item.getloveSubj();
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }


    public void search(int sid) {
        //通过学号查找单个学生
        Student student = service.selectBySid(sid);
        if (student != null) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);// 清除原有行

            String[] arr = new String[6];

            arr[0] = student.getSid() + "";
            arr[1] = student.getName();
            arr[2] = String.valueOf(student.getAge());
            arr[3] = student.getMajor();
            arr[4] = student.getGrade();
            arr[5] = student.getloveSubj();
            // 添加数据到表格
            tableModel.addRow(arr);
        } else {
            JOptionPane.showMessageDialog(contentPane, "不存在该学生", "系统提示", JOptionPane.WARNING_MESSAGE);
        }
    }


}