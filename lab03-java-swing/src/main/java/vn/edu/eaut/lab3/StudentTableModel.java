package vn.edu.eaut.lab3;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private final List<Student> students = new ArrayList<>();
    private final String[] columnNames = {"Mã SV", "Họ tên", "Điểm TB", "Xếp loại"};

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return student.getId();
            case 1:
                return student.getFullName();
            case 2:
                return student.getAverageScore();
            case 3:
                return student.getClassification();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addStudent(Student student) {
        students.add(student);
        int row = students.size() - 1;
        fireTableRowsInserted(row, row);
    }

    public void updateStudent(int index, Student student) {
        students.set(index, student);
        fireTableRowsUpdated(index, index);
    }

    public void removeStudent(int index) {
        students.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void clear() {
        int size = students.size();
        if (size > 0) {
            students.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public Student getStudentAt(int index) {
        return students.get(index);
    }

    public boolean containsId(String id) {
        return students.stream().anyMatch(student -> student.getId().equalsIgnoreCase(id));
    }
}
