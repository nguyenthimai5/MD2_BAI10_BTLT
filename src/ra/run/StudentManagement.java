package ra.run;

import ra.entity.IStudentManagement;
import ra.entity.Student;
import ra.entity.StudentClass;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Objects.equals;

public class StudentManagement {
    static List<StudentClass> studentClassList = new ArrayList<>();
    static List<Student> studentList = new ArrayList<>();
    static int lenghtStudent = 0;
    static int lenghtClass = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************QUAN LY HOC VIEN****************");
            System.out.println("1.Quản lý lớp");
            System.out.println("2.Quảm lý sinh viên");
            System.out.println("3.Thoát");
            System.out.println("Nhập sự lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    StudentManagement.getClassManagement(scanner);
                    break;
                case 2:
                    StudentManagement.getStudentManagenment(scanner);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-3!");
            }
        } while (true);

    }

    public static void getClassManagement(Scanner scanner) {
        boolean exit = true;
        do {
            System.out.println("*******************QUẢN LÝ LỚP HỌC*******************");
            System.out.println("1.Thêm lớp học mới");
            System.out.println("2.Cập nhật thông tin lớp học");
            System.out.println("3.Hiển thị thông tin lớp học");
            System.out.println("4.Thống kê các lớp học đang hoạt động");
            System.out.println("5.Tìm kiếm lớp học theo tên");
            System.out.println("6.Thoát");
            System.out.println("Vui lòng nhập sự lựa chọn của bạn: ");
            int chon = scanner.nextInt();
            switch (chon) {
                case 1:
                    StudentManagement.inputClass(scanner);
                    break;
                case 2:
                    StudentManagement.updateClass(scanner);
                    break;
                case 3:
                    StudentManagement.displayClass();
                    break;
                case 4:
                    StudentManagement.statusClassOn();
                    break;
                case 5:
                    StudentManagement.searchClass(scanner);
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui long nhap tu 1-6!");
            }
        } while (exit);
    }

    public static void getStudentManagenment(Scanner scanner) {
        boolean exit2 = true;
        do {
            System.out.println("********************QUẢN LÝ SINH VIÊN******************");
            System.out.println("1.Thêm mới sinh viên");
            System.out.println("2.Cập nhật thông tin sinh viên");
            System.out.println("3.Hiện thị thông tin sinh viên");
            System.out.println("4.Tính điểm trung bình");
            System.out.println("5.Xếp loại sinh viên");
            System.out.println("6.Sắp xếp sinh viên theo điểm trung bình tăng dần");
            System.out.println("7.Tìm kiếm sinh viên theo tên");
            System.out.println("8. Thống kê số sinh viên đạt loại giỏi, khá, trung bình và yếu");
            System.out.println("9. Thống kê các sinh viên Pass qua các môn học");
            System.out.println("10.Thoát");
            int nhap = scanner.nextInt();
            switch (nhap) {
                case 1:
                    StudentManagement.inputListStudent(scanner);
                    break;
                case 2:
                    StudentManagement.updateStudent(scanner);
                    break;
                case 3:
                    StudentManagement.displayStudent();
                    break;
                case 4:
                    StudentManagement.diemTrungBinh();
                    break;
                case 5:
                    StudentManagement.xepHangSinhVien();
                    break;
                case 6:
                    StudentManagement.xapXepSv();
                    break;
                case 7:
                    StudentManagement.searchStudent(scanner);
                    break;
                case 8:
                    StudentManagement.thongKeStudent();
                    break;
                case 9:
                    StudentManagement.passStudent();
                    break;
                case 10:
                    exit2 = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-10!");
            }

        } while (exit2);
    }

    public static void inputListStudent(Scanner scanner) {
        System.out.println("Nhap vao so sinh vien can nhap thong tin: ");
        scanner.nextLine();
        int cnt = scanner.nextInt();
        for (int i = 0; i < cnt; i++) {
            Student studentNew = new Student();
            studentNew.inputData(scanner);
            System.out.println("Chon lop hoc cho sinh vien:");
            int index = 1;
            for (StudentClass stClass : studentClassList) {
                System.out.printf("%d.%s\n", index, stClass.getClassName());
                index++;
                lenghtStudent++;
            }
            System.out.print("Su lua chon cua ban: ");
            int choice = Integer.parseInt(scanner.nextLine());
            studentNew.setStudentClass(studentClassList.get(choice - 1));

            studentList.add(studentNew);
        }
    }


    public static void displayStudent() {
        for (int i = 0; i < lenghtStudent; i++) {
            studentList.get(i).displayData();
        }
    }

    public static void updateStudent(Scanner sc) {
        System.out.println("Nhap vao ma sinh vien can cap nhat");
        String updateStudent = sc.nextLine();
        sc.nextLine();
        Student student = new Student();
        if (updateStudent == student.getStudentId()) {
            student.inputData(sc);
        }


    }

    public static void diemTrungBinh() {

        for (int i = 0; i < lenghtStudent; i++) {
            studentList.get(i).callAvgMark();
        }
    }

    public static void xepHangSinhVien() {
        for (int i = 0; i < lenghtStudent; i++) {
            studentList.get(i).getGPA();
        }
    }

    public static void inputClass(Scanner scanner) {
        System.out.println("Nhap vao so lop can nhap");
        scanner.nextLine();
        int cnt = scanner.nextInt();
        for (int i = 0; i < cnt; i++) {
            StudentClass studentClass = new StudentClass();
            studentClass.inputData(scanner);
            studentClassList.add(studentClass);
            lenghtClass++;
        }
    }

    public static void displayClass() {
        for (int i = 0; i < lenghtClass; i++) {
            studentClassList.get(i).displayData();
        }
    }

//    @Override
//    public int compare(Student o1, Student o2) {
//        return (int) o1.callAvgMark() - (int) o2.callAvgMark();
//    }

    public static void searchStudent(Scanner scanner) {
        System.out.println("Nhập vào tên sv muốn tìm:");
        scanner.nextLine();
        String studentNameSearch = scanner.nextLine();
        Student student = new Student();
        for (int i = 0; i < lenghtStudent; i++) {
            if (student.getStudentName().startsWith(studentNameSearch)) {
                studentList.get(i).displayData();
            }
        }
    }

    public static void thongKeStudent() {
        Student student = new Student();
        for (int i = 0; i < lenghtStudent; i++) {
            if (student.getGpa() == "Gioi") {
                studentList.get(i).displayData();
            }
            if (student.getGpa() == "Kha") {
                studentList.get(i).displayData();
            }
            if (student.getGpa() == "Trung binh") {
                studentList.get(i).displayData();
            }
            if (student.getGpa() == "Yeu") {
                studentList.get(i).displayData();
            }
        }
    }

    public static void passStudent() {
        Student student = new Student();
        for (int i = 0; i < lenghtStudent; i++) {
            for (Float js_mark : student.getListMarkJavaScript()) {
                if (js_mark >= 5) {
                    studentList.get(i).displayData();
                }
            }
            for (Float js_markCore : student.getListMarkJavaCore()) {
                if (js_markCore >= 5) {
                    studentList.get(i).displayData();
                }

            }
            for (Float js_markWeb : student.getListMarkJavaWeb()) {
                if (js_markWeb >= 5) {
                    studentList.get(i).displayData();
                }
            }
        }
    }

    public static void updateClass(Scanner scanner) {
        StudentClass studentClass = new StudentClass();
        System.out.println("Nhap ten lop can cap nhat: ");
        scanner.nextLine();

        String updateClassNew = scanner.nextLine();
        for (int i = 0; i < studentClassList.size(); i++) {
            if (studentClass.getClassId().equals(updateClassNew)) {
                studentClass.inputData(scanner);
            } else {
                System.err.println("Ma sv khong co trong du lieu!");
            }
        }
    }

    public static void searchClass(Scanner scanner) {
        System.out.println("Nhập vào tên lớp học muốn tìm:");
        scanner.nextLine();
        String classNameSearch = scanner.nextLine();
        scanner.nextLine();
        StudentClass studentClass = new StudentClass();
        for (int i = 0; i < lenghtStudent; i++) {
            if (studentClass.getClassName().startsWith(classNameSearch)) {
                studentClassList.get(i).displayData();
            }
        }
    }

    public static void statusClassOn() {
        StudentClass studentClass = new StudentClass();
        for (int i = 0; i < lenghtClass; i++) {
            if (studentClass.getClassStatus() == 1) {
                studentClassList.get(i).displayData();
            }
        }

    }

    public static void xapXepSv() {
        for (int i = 0; i < lenghtStudent - 1; i++) {
            for (int j = i + 1; j < lenghtStudent; j++) {
                if (studentList.get(i).callAvgMark() > studentList.get(j).callAvgMark()) {
                    studentList.set(i, studentList.get(j));
                }
            }
        }


    }
}