package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai07MayTinhMini extends JFrame {

    private JTextField txtNumber1;
    private JTextField txtNumber2;
    private JTextField txtResult;
    private JTextArea txtHistory;

    private JButton btnAdd;
    private JButton btnSubtract;
    private JButton btnMultiply;
    private JButton btnDivide;
    private JButton btnClear;

    public Bai07MayTinhMini() {
        setTitle("Bài 07 - Máy tính mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        initEvents();
    }

    // =========================
    // KHỞI TẠO GIAO DIỆN
    // =========================
    private void initComponents() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // -------------------------
        // Số thứ nhất
        // -------------------------
        JLabel lblNumber1 = new JLabel("Số thứ nhất:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(lblNumber1, gbc);

        txtNumber1 = new JTextField();

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        panel.add(txtNumber1, gbc);

        // -------------------------
        // Số thứ hai
        // -------------------------
        JLabel lblNumber2 = new JLabel("Số thứ hai:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblNumber2, gbc);

        txtNumber2 = new JTextField();

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(txtNumber2, gbc);

        // -------------------------
        // Kết quả
        // -------------------------
        JLabel lblResult = new JLabel("Kết quả:");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(lblResult, gbc);

        txtResult = new JTextField();
        txtResult.setEditable(false);
        txtResult.setBackground(Color.WHITE);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(txtResult, gbc);

        // -------------------------
        // Các nút phép tính
        // -------------------------
        btnAdd = new JButton("Cộng");
        btnSubtract = new JButton("Trừ");
        btnMultiply = new JButton("Nhân");
        btnDivide = new JButton("Chia");

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnAdd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnSubtract, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(btnMultiply, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        panel.add(btnDivide, gbc);

        // -------------------------
        // Nút Clear
        // -------------------------
        btnClear = new JButton("Clear");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        panel.add(btnClear, gbc);

        // -------------------------
        // Lịch sử
        // -------------------------
        JLabel lblHistory = new JLabel("Lịch sử phép tính:");

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblHistory, gbc);

        txtHistory = new JTextArea(8, 40);
        txtHistory.setEditable(false);
        txtHistory.setLineWrap(true);
        txtHistory.setWrapStyleWord(true);

        JScrollPane scrollHistory = new JScrollPane(txtHistory);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        panel.add(scrollHistory, gbc);

        add(panel);
    }

    // =========================
    // XỬ LÝ SỰ KIỆN
    // =========================
    private void initEvents() {

        btnAdd.addActionListener(e -> calculate('+'));

        btnSubtract.addActionListener(e -> calculate('-'));

        btnMultiply.addActionListener(e -> calculate('*'));

        btnDivide.addActionListener(e -> calculate('/'));

        btnClear.addActionListener(e -> clearFields());
    }

    // =========================
    // TÍNH TOÁN
    // =========================
    private void calculate(char operator) {

        String s1 = txtNumber1.getText().trim();
        String s2 = txtNumber2.getText().trim();

        // Kiểm tra rỗng
        if (s1.isEmpty() || s2.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập đầy đủ hai số!",
                    "Thông báo lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        double n1;
        double n2;

        // Chuyển chuỗi sang số
        try {

            n1 = Double.parseDouble(s1);
            n2 = Double.parseDouble(s2);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập số hợp lệ!",
                    "Thông báo lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        double result;
        String operationText;

        // Thực hiện phép tính
        switch (operator) {

            case '+':

                result = n1 + n2;

                operationText = s1 + " + " + s2
                        + " = " + formatDouble(result);

                break;

            case '-':

                result = n1 - n2;

                operationText = s1 + " - " + s2
                        + " = " + formatDouble(result);

                break;

            case '*':

                result = n1 * n2;

                operationText = s1 + " * " + s2
                        + " = " + formatDouble(result);

                break;

            case '/':

                // Kiểm tra chia cho 0
                if (n2 == 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Không thể chia cho 0!",
                            "Lỗi chia",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                result = n1 / n2;

                operationText = s1 + " / " + s2
                        + " = " + formatDouble(result);

                break;

            default:
                return;
        }

        // Hiển thị kết quả
        txtResult.setText(formatDouble(result));

        // Thêm vào lịch sử
        txtHistory.append(operationText + "\n");
    }

    // =========================
    // ĐỊNH DẠNG SỐ
    // =========================
    private String formatDouble(double value) {

        // Nếu là số nguyên
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }

        // Nếu là số thực
        return String.format("%.6f", value)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", "");
    }

    // =========================
    // XÓA DỮ LIỆU
    // =========================
    private void clearFields() {

        txtNumber1.setText("");
        txtNumber2.setText("");
        txtResult.setText("");
        txtHistory.setText("");

        txtNumber1.requestFocus();
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Bai07MayTinhMini calculator = new Bai07MayTinhMini();

            calculator.setVisible(true);
        });
    }
}