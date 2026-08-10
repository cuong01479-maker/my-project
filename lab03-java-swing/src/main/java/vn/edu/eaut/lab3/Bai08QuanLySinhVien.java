package vn.edu.eaut.lab3;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai08QuanLySinhVien extends JFrame {
    private final JTextField txtId = new JTextField(15);
    private final JTextField txtFullName = new JTextField(20);
    private final JTextField txtAverage = new JTextField(10);
    private final JButton btnAdd = new JButton("Thêm");
    private final JButton btnEdit = new JButton("Sửa");
    private final JButton btnDelete = new JButton("Xóa");
    private final JButton btnClear = new JButton("Làm mới");
    private final StudentTableModel tableModel = new StudentTableModel();
    private final JTable table = new JTable(tableModel);

    public Bai08QuanLySinhVien() {
        setTitle("Bài 08 - Quản lý sinh viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 460);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Mã sinh viên:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtFullName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Điểm trung bình:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtAverage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên"));
        add(scrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEdit();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedStudent();
                }
            }
        });
    }

    private void onAdd() {
        String id = txtId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String averageText = txtAverage.getText().trim();

        if (id.isEmpty() || fullName.isEmpty() || averageText.isEmpty()) {
            showError("Vui lòng nhập đầy đủ mã sinh viên, họ tên và điểm trung bình.");
            return;
        }

        if (tableModel.containsId(id)) {
            showError("Mã sinh viên đã tồn tại. Vui lòng nhập mã khác.");
            return;
        }

        double average;
        try {
            average = Double.parseDouble(averageText);
        } catch (NumberFormatException ex) {
            showError("Điểm trung bình không hợp lệ. Vui lòng nhập số.");
            return;
        }

        if (average < 0 || average > 10) {
            showError("Điểm trung bình phải nằm trong khoảng 0 đến 10.");
            return;
        }

        Student student = new Student(id, fullName, average);
        tableModel.addStudent(student);
        clearForm();
    }

    private void onEdit() {
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex < 0) {
            showError("Vui lòng chọn một sinh viên để sửa.");
            return;
        }

        String id = txtId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String averageText = txtAverage.getText().trim();

        if (id.isEmpty() || fullName.isEmpty() || averageText.isEmpty()) {
            showError("Vui lòng nhập đầy đủ mã sinh viên, họ tên và điểm trung bình.");
            return;
        }

        double average;
        try {
            average = Double.parseDouble(averageText);
        } catch (NumberFormatException ex) {
            showError("Điểm trung bình không hợp lệ. Vui lòng nhập số.");
            return;
        }

        if (average < 0 || average > 10) {
            showError("Điểm trung bình phải nằm trong khoảng 0 đến 10.");
            return;
        }

        Student current = tableModel.getStudentAt(selectedIndex);
        if (!current.getId().equalsIgnoreCase(id) && tableModel.containsId(id)) {
            showError("Mã sinh viên đã tồn tại. Vui lòng chọn mã khác.");
            return;
        }

        Student updated = new Student(id, fullName, average);
        tableModel.updateStudent(selectedIndex, updated);
        table.clearSelection();
        clearForm();
    }

    private void onDelete() {
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex < 0) {
            showError("Vui lòng chọn một sinh viên để xóa.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa sinh viên này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            tableModel.removeStudent(selectedIndex);
            clearForm();
        }
    }

    private void loadSelectedStudent() {
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex >= 0) {
            Student student = tableModel.getStudentAt(selectedIndex);
            txtId.setText(student.getId());
            txtFullName.setText(student.getFullName());
            txtAverage.setText(String.valueOf(student.getAverageScore()));
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtFullName.setText("");
        txtAverage.setText("");
        table.clearSelection();
        txtId.requestFocus();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Bai08QuanLySinhVien().setVisible(true);
            }
        });
    }
}

