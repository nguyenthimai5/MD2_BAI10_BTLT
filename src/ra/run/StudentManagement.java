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
            }
            System.out.print("Su lua chon cua ban: ");
            int choice = Integer.parseInt(scanner.nextLine());
            studentNew.setStudentClass(studentClassList.get(choice - 1));

            studentList.add(studentNew);
        }
    }


    public static void displayStudent() {
        for (Student studentDisplay : studentList) {
            studentDisplay.displayData();
        }
    }

    public static void updateStudent(Scanner scanner) {
        Student student = new Student();
        System.out.println("Nhap vao ma sinh vien can cap nhat");
        scanner.nextLine();
        String updateStudent = scanner.nextLine();
        Boolean exitUpdateStudent = false;
        for (Student studentUpDate : studentList) {
            if (studentUpDate.getStudentName().equals(updateStudent)) {
                studentUpDate.getStudentId();
                System.out.println("Nhap ten sv muon cap nhat:");
                String studentName = scanner.nextLine();
                if (studentName != "" && studentName.length() != 0) {
                    do {
                        if (studentName.trim().length() >= 6 && studentName.trim().length() <= 50) {
                            studentUpDate.setStudentName(studentName);
                            break;
                        } else {
                            System.err.println("Vui long nhap ten sinh vien tu 6 den 50 ky tu");
                        }
                    } while (true);
                }

                System.out.println("Nhap vao tuoi sinh vien muon cap nhat: ");
                int age = Integer.parseInt(scanner.nextLine());
                do {
                    if (age >= 18) {
                        studentUpDate.setAge(age);
                        break;
                    } else {
                        System.err.println("Vui long nhap tuoi sinh vien lon hon hoac bang 18");
                    }
                } while (true);
                System.out.println("Nhap vao gioi tinh sinh vien can cap nhat: ");
                Boolean sex = Boolean.parseBoolean(scanner.nextLine());
                if (sex == true) {
                    sex=Boolean.valueOf("Nam");
                } else {
                    sex=Boolean.valueOf("Nữ");
                }
                studentUpDate.setSex(sex);
                List<Float> listJS = new ArrayList<>();
                Student.inputMark(listJS, scanner);
                student.setListMarkJavaScript(listJS);
                System.out.println("Nhap vao trang thai sinh vien can cap nhat: ");
                Boolean studentStatus = Boolean.parseBoolean(scanner.nextLine());
                if (studentStatus == true) {
                    studentStatus= Boolean.valueOf("Đang học");
                } else {
                    studentStatus= Boolean.valueOf("Đã rời trường");
                }
                studentUpDate.setStudentStatus(studentStatus);
                exitUpdateStudent = true;
                break;
            }

        }
        if (!exitUpdateStudent) {
            System.out.printf("Sv %s nay khong co trong danh sach!", updateStudent);
        }

    }


    public static void diemTrungBinh() {
        for (Student student : studentList) {
            student.callAvgMark();
        }
    }

    public static void xepHangSinhVien() {
        for (Student student : studentList) {
            student.getGPA();
        }

    }

    public static void inputClass(Scanner scanner) {
        System.out.println("Nhap vao so lop can nhap thong tin");
        scanner.nextLine();
        int cnt = scanner.nextInt();
        for (int i = 0; i < cnt; i++) {
            StudentClass studentClass = new StudentClass();
            studentClass.inputData(scanner);
            studentClassList.add(studentClass);
        }
    }

    public static void displayClass() {
        System.out.println("**************THONG TIN SINH VIEN**************");
        for (StudentClass studentClass : studentClassList) {
            studentClass.displayData();
        }
    }


    public static void searchStudent(Scanner scanner) {
        System.out.println("Nhập vào tên SV muốn tìm:");
        scanner.nextLine();
        String studentNameSearch = scanner.nextLine();
        boolean exitStudent = false;
        for (Student student : studentList) {
            if (student.getStudentName().contains(studentNameSearch)) {
                student.displayData();
                exitStudent = true;
            }
        }
        if (!exitStudent) {
            System.out.printf("SV %s khong co trong danh sach", studentNameSearch);
        }
    }

    public static void thongKeStudent() {
        int coutGioi = 0, countKha = 0, countTrungBinh = 0, countYeu = 0;
        for (Student student : studentList) {
            if (student.getAvgMark() >= 9) {
                coutGioi++;
            }
            if (student.getAvgMark() >= 7) {
                countKha++;
            }
            if (student.getAvgMark() >= 5) {
                countTrungBinh++;
            }
            if (student.getAvgMark() > 0) {
                countYeu++;
            }
        }
        System.out.printf("SV GIOI:%d  - SV KHA:%d - SV TRUNG BINH:%d  - SV YEU:%d \n", coutGioi, countKha, countTrungBinh, countYeu);
    }


    public static void passStudent() {
        int passJS = 0, passJC = 0, passJW = 0;
        for (Student studentMark : studentList) {
            for (Float js_mark : studentMark.getListMarkJavaScript()) {
                if (js_mark >= 5) {
                    passJS++;
                }
            }
            for (Float js_markCore : studentMark.getListMarkJavaCore()) {
                if (js_markCore >= 5) {
                    passJC++;
                }
            }
            for (Float js_markWeb : studentMark.getListMarkJavaWeb()) {
                if (js_markWeb >= 5) {
                    passJW++;
                }
            }
        }
        System.out.printf("So SV qua mon JAVSCRIPT:%d  - So SV qua mon JAVACORE:%d  - So SV qua mon JAVAWEB:%d \n", passJS, passJC, passJW);
    }

    public static void updateClass(Scanner scanner) {
        System.out.println("Nhap ten lop can cap nhat: ");
        scanner.nextLine();
        String updateClassNew = scanner.nextLine();
        boolean exitClass = false;
        for (StudentClass studentClass : studentClassList) {
            if (studentClass.getClassName().equals(updateClassNew)) {
                studentClass.getClassId();
                System.out.println("Nhap ten lop muon cap nhat: ");
                String newName = scanner.nextLine();
                if (newName != "" && newName.length() != 0) {
                    studentClass.setClassName(newName);
                }
                System.out.println("Nhap mo ta can cap nhat: ");
                String decriptsion = scanner.nextLine();
                if (decriptsion != "" && decriptsion.length() != 0) {
                    studentClass.setDescriptions(decriptsion);
                }
                System.out.println("Nhap trang thai can cap nhat: ");
                String newStatus = scanner.nextLine();
                if (newStatus != "" && newStatus.length() != 0) {
                    studentClass.setClassStatus(Integer.parseInt(newStatus));
                }
                exitClass = true;
                break;
            }
            if (!exitClass) {
                System.out.printf(" Lop %s khong co trong du lieu\n", updateClassNew);
            }
        }
    }

    public static void searchClass(Scanner scanner) {
        System.out.println("Nhập vào tên lớp học muốn tìm:");
        scanner.nextLine();
        String classNameSearch = scanner.nextLine();
        boolean exitClass = false;
        System.out.println("*************Lop Hoc Ban Muon tim*************");
        for (StudentClass studentClass : studentClassList) {
            if (studentClass.getClassName().contains(classNameSearch)) {
                studentClass.displayData();
                exitClass = true;
            }
        }
        if (!exitClass) {
            System.out.printf("Lop %s khong co trong danh sach!", classNameSearch);
        }

    }


    public static void statusClassOn() {
        System.out.println("------------------------Lop dang hoat dong--------------------");
        int count = 0;
        for (StudentClass classOn : studentClassList) {
            if (classOn.getClassStatus() == 1) {
                classOn.displayData();
                count++;
            }
        }

    }

    public static void xapXepSv() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) o1.callAvgMark() - (int) o2.callAvgMark();
            }
        });
        System.out.println("Da sap xep xong theo diem TB tang dan");
    }


}
