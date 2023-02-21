package GUITest;

import pojo.Student;
import service.studentService;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class UpdateView extends JFrame {

    private JPanel contentPane;
    private JTextField sidText;
    private JTextField nameText;
    private JTextField ageText;
    private JTextField majorText;
    private JTextField gradeText;
    private JTextField loveSubjText;

    private studentService service = new studentService();


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateView frame = new UpdateView(1);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public UpdateView(final int sid) {
        setTitle("学生编辑");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 450);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("学号：");
        lblNewLabel.setBounds(112, 50, 43, 15);
        contentPane.add(lblNewLabel);

        sidText = new JTextField();
        sidText.setBounds(151, 47, 160, 21);
        contentPane.add(sidText);
        sidText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setBounds(112, 93, 43, 15);
        contentPane.add(lblNewLabel_1);

        nameText = new JTextField();
        nameText.setBounds(151, 90, 160, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("年龄：");
        lblNewLabel_2.setBounds(112, 134, 43, 15);
        contentPane.add(lblNewLabel_2);

        ageText = new JTextField();
        ageText.setBounds(151, 130, 160, 21);
        contentPane.add(ageText);
        ageText.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("专业：");
        lblNewLabel_3.setBounds(112, 177, 43, 15);
        contentPane.add(lblNewLabel_3);

        majorText = new JTextField();
        majorText.setBounds(151, 177, 160, 21);
        contentPane.add(majorText);
        majorText.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("班级：");
        lblNewLabel_4.setBounds(111, 220, 43, 15);
        contentPane.add(lblNewLabel_4);

        gradeText = new JTextField();
        gradeText.setBounds(151, 220, 160, 21);
        contentPane.add(gradeText);
        gradeText.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("偏爱学科：");
        lblNewLabel_5.setBounds(90, 263, 70, 15);
        contentPane.add(lblNewLabel_5);

        loveSubjText = new JTextField();
        loveSubjText.setBounds(151, 263, 160, 21);
        contentPane.add(loveSubjText);
        loveSubjText.setColumns(10);

        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(e -> {

            String sid1 = sidText.getText();
            String name = nameText.getText();
            String age = ageText.getText();
            String major = majorText.getText();
            String grade = gradeText.getText();
            String loveSubj = loveSubjText.getText();
            if (sid1 == null || "".equals(sid1)) {
                JOptionPane.showMessageDialog(contentPane, "请输入学号", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (name == null || "".equals(name)) {
                JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (age == null || "".equals(age)) {
                JOptionPane.showMessageDialog(contentPane, "请输入年龄", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (major == null || "".equals(major)) {
                JOptionPane.showMessageDialog(contentPane, "请输入专业", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (grade == null || "".equals(grade)) {
                JOptionPane.showMessageDialog(contentPane, "请输入年纪", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (loveSubj == null || "".equals(loveSubj)) {
                JOptionPane.showMessageDialog(contentPane, "请输入偏爱学科", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Student student = new Student();
            student.setSid(Integer.parseInt(sid1));
            student.setName(name);
            student.setAge(Integer.parseInt(age));
            student.setMajor(major);
            student.setGrade(grade);
            student.setloveSubj(loveSubj);
            //修改并保存重新录入的数据
            service.update(student);
            dispose();
            JOptionPane.showMessageDialog(contentPane, "修改成功，点击查询刷新数据!");

        });
        saveBtn.setBounds(151, 300, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(e -> dispose());
        cancelBtn.setBounds(237, 300, 74, 23);
        contentPane.add(cancelBtn);

        //数据回显
        Student student = service.selectBySid(sid);
        sidText.setText(String.valueOf(student.getSid()));
        nameText.setText(student.getName());
        ageText.setText(String.valueOf(student.getAge()));
        majorText.setText(student.getMajor());
        gradeText.setText(student.getGrade());
        loveSubjText.setText(student.getloveSubj());
    }

}
