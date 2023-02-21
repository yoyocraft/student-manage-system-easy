package GUITest;

import service.userService;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class LoginView extends JFrame {

    private JPanel contentPane;
    private JTextField usernameText;
    private JTextField passwordText;
    private userService userservice = new userService();


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginView frame = new LoginView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public LoginView() {
        setTitle("欢迎登录");
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


        //跳转注册
        JButton registerBtn = new JButton("注册");
        registerBtn.addActionListener(e -> {

            RegisterView view = new RegisterView();
            view.setVisible(true);
            dispose();

        });
        registerBtn.setBounds(210, 150, 74, 23);
        contentPane.add(registerBtn);



        //登录
        JButton LoginBtn = new JButton("登录");
        LoginBtn.addActionListener(e -> {
            stulogin();
        });
        LoginBtn.setBounds(115, 150, 74, 23);
        contentPane.add(LoginBtn);



    }

//登录逻辑判断
    public void stulogin() {
        //调用服务层逻辑验证账号密码
        if (userservice.login(usernameText.getText(), passwordText.getText())) {
            JOptionPane.showMessageDialog(null, "登录成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
            //关闭当前界面
            dispose();
            //登录成功跳出新界面
            UserListView view = new UserListView();
            view.setVisible(true);
        } else if (usernameText.getText().isEmpty() && passwordText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else if (usernameText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else if (passwordText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误！\n请重新输入", "提示消息", JOptionPane.ERROR_MESSAGE);
            usernameText.setText("");
            passwordText.setText("");
        }
    }

}