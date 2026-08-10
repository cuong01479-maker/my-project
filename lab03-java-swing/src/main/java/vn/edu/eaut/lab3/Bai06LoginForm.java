package vn.edu.eaut.lab3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Bai06LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRole;
    private JCheckBox chkShowPassword;
    private JButton btnLogin;

    public Bai06LoginForm() {
        setTitle("Bài 06 - Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsername = new JLabel("Tài khoản:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPassword, gbc);

        chkShowPassword = new JCheckBox("Hiển thị mật khẩu");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(chkShowPassword, gbc);

        JLabel lblRole = new JLabel("Vai trò:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblRole, gbc);

        cbRole = new JComboBox<>(new String[] {"Admin", "User"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(cbRole, gbc);

        btnLogin = new JButton("Đăng nhập");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnLogin, gbc);

        add(panel);

        chkShowPassword.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chkShowPassword.isSelected()) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    txtPassword.setEchoChar('*');
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String role = (String) cbRole.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đầy đủ tài khoản và mật khẩu.",
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean valid = false;

        if ("admin".equals(username) && "123456".equals(password) && "Admin".equals(role)) {
            valid = true;
        } else if ("user".equals(username) && "123456".equals(password) && "User".equals(role)) {
            valid = true;
        }

        if (valid) {
            JOptionPane.showMessageDialog(this,
                    "Chào mừng " + username + "! Bạn đã đăng nhập với vai trò " + role + ".",
                    "Đăng nhập thành công",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message;
            if (!"123456".equals(password)) {
                message = "Mật khẩu không đúng. Vui lòng thử lại.";
            } else if (!"admin".equals(username) && !"user".equals(username)) {
                message = "Tài khoản không tồn tại. Vui lòng kiểm tra lại.";
            } else {
                message = "Vai trò không hợp lệ cho tài khoản này. Vui lòng chọn đúng vai trò.";
            }
            JOptionPane.showMessageDialog(this,
                    message,
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Bai06LoginForm frame = new Bai06LoginForm();
                frame.setVisible(true);
            }
        });
    }
}

