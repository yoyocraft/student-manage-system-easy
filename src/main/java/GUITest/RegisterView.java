package GUITest;

import pojo.User;
import service.userService;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterView extends JFrame {

    private JPanel contentPane;
    private JTextField usernameText;
    private JTextField passwordText;
    private userService userservice = new userService();


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegisterView frame = new RegisterView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public RegisterView() {
        setTitle("欢迎注册");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 380, 250);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("账号：");
        lblNewLabel.setBounds(92, 50, 50, 15);
        contentPane.add(lblNewLabel);

        usernameText = new JTextField();
        usernameText.setBounds(131, 47, 160, 21);
        contentPane.add(usernameText);
        usernameText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("密码：");
        lblNewLabel_1.setBounds(92, 93, 43, 15);
        contentPane.add(lblNewLabel_1);

        passwordText = new JTextField();
        passwordText.setBounds(131, 90, 160, 21);
        contentPane.add(passwordText);
        passwordText.setColumns(10);


        //注册
        JButton registerBtn = new JButton("注册");
        registerBtn.addActionListener(e -> {

            String username = usernameText.getText();
            String password = passwordText.getText();

            if (username == null || "".equals(username)) {
                JOptionPane.showMessageDialog(contentPane, "请输入账号", "系统提示", JOptionPane.WARNING_MESSAGE);
            } else if (password == null || "".equals(password)) {
                JOptionPane.showMessageDialog(contentPane, "请输入密码", "系统提示", JOptionPane.WARNING_MESSAGE);
           //检查用户名是否重复
            } else if (userservice.check(username)) {
                if (username.isEmpty() && password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    //调用方法在数据库中存入数据
                    userservice.regiser(user);
                    JOptionPane.showMessageDialog(null, "注册成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    LoginView view = new LoginView();
                    view.setVisible(true);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(contentPane, "用户名已存在", "系统提示", JOptionPane.WARNING_MESSAGE);
                usernameText.setText("");
                passwordText.setText("");
            }


        });
        registerBtn.setBounds(115, 150, 74, 23);
        contentPane.add(registerBtn);


        //重置
        JButton LoginBtn = new JButton("重置");
        LoginBtn.addActionListener(e -> {
            usernameText.setText("");
            passwordText.setText("");
        });
        LoginBtn.setBounds(210, 150, 74, 23);
        contentPane.add(LoginBtn);


        //返回
        JButton returnBtn = new JButton("返回");
        returnBtn.addActionListener(e -> {
            LoginView view = new LoginView();
            view.setVisible(true);
            dispose();
        });
        returnBtn.setBounds(160, 180, 74, 23);
        contentPane.add(returnBtn);


    }


}
