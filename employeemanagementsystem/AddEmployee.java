package employeemanagementsystem;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);
    JTextField tname, taddress, tphone, taddhar, temail, tsalary, tdesignation;
    JLabel tempid;
    JDateChooser tdob;

    JButton add, back;
    JComboBox<String> Boxeducation;

    AddEmployee() {
        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 150, 30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(name);

        tname = new JTextField();
        tname.setBounds(200, 150, 150, 30);
        tname.setBackground(new Color(177, 252, 197));
        add(tname);

        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(400, 150, 150, 30);
        dob.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(600, 150, 150, 30);
        tdob.setBackground(new Color(177, 252, 197));
        add(tdob);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(50, 200, 150, 30);
        salary.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(200, 200, 150, 30);
        tsalary.setBackground(new Color(177, 252, 197));
        add(tsalary);

        JLabel address = new JLabel("Address");
        address.setBounds(400, 200, 150, 30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(600, 200, 150, 30);
        taddress.setBackground(new Color(177, 252, 197));
        add(taddress);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(50, 250, 150, 30);
        phone.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(200, 250, 150, 30);
        tphone.setBackground(new Color(177, 252, 197));
        add(tphone);

        JLabel email = new JLabel("Email");
        email.setBounds(400, 250, 150, 30);
        email.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(email);

        temail = new JTextField();
        temail.setBounds(600, 250, 150, 30);
        temail.setBackground(new Color(177, 252, 197));
        add(temail);

        JLabel education = new JLabel("Highest Education");
        education.setBounds(50, 300, 150, 30);
        education.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(education);

        String items[] = { "BBA", "BS", "B.Tech", "BCA", "BA", "BSC", "B.COM", "MBA", "MA", "MTech", "MSC", "PHD" };
        Boxeducation = new JComboBox<>(items);
        Boxeducation.setBackground(new Color(177, 252, 197));
        Boxeducation.setBounds(200, 300, 150, 30);
        add(Boxeducation);

        JLabel addhar = new JLabel("Addhar Number");
        addhar.setBounds(400, 300, 150, 30);
        addhar.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(addhar);

        taddhar = new JTextField();
        taddhar.setBounds(600, 300, 150, 30);
        taddhar.setBackground(new Color(177, 252, 197));
        add(taddhar);

        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(50, 350, 150, 30);
        empid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(empid);

        tempid = new JLabel("" + number);
        tempid.setBounds(200, 350, 150, 30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        tempid.setForeground(Color.RED);
        add(tempid);

        JLabel designation = new JLabel("Designation");
        designation.setBounds(400, 350, 150, 30);
        designation.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(600, 350, 150, 30);
        tdesignation.setBackground(new Color(177, 252, 197));
        add(tdesignation);

        add = new JButton("ADD");
        add.setBounds(450, 550, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(250, 550, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLayout(null);
        setLocation(195, 4);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tname.getText();
            Date date = tdob.getDate();
            String dob = null;
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dob = sdf.format(date);
            }
            String salary = tsalary.getText();
            String address = taddress.getText();
            String addhar = taddhar.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = (String) Boxeducation.getSelectedItem();
            String designation = tdesignation.getText();
            String empID = tempid.getText();

            try (conn c = new conn()) {
                String query = "INSERT INTO employee (empID, fname, name, dob, salary, address, addhar, phone, email, education, designation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = c.prepareStatement(query);
                pstmt.setString(1, empID);
                pstmt.setString(2, name);
                pstmt.setString(3, name);
                pstmt.setString(4, dob);
                pstmt.setString(5, salary);
                pstmt.setString(6, address);
                pstmt.setString(7, addhar);
                pstmt.setString(8, phone);
                pstmt.setString(9, email);
                pstmt.setString(10, education);
                pstmt.setString(11, designation);

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Employee Added Successfully!");

                new AttendanceManagement(empID);
                this.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding employee: " + ex.getMessage());
            }
        } else if (e.getSource() == back) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
