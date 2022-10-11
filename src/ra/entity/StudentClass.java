package ra.entity;

import java.util.Scanner;

public class StudentClass implements IStudentManagement {
    private String classId;
    private String className;
    private String descriptions;
    private int classStatus;

    public StudentClass() {
    }

    public StudentClass(String classId, String className, String descriptions, int classStatus) {
        this.classId = classId;
        this.className = className;
        this.descriptions = descriptions;
        this.classStatus = classStatus;
    }

    public String getClassId(){
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(int classStatus) {
        this.classStatus = classStatus;
    }


    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào mã lớp");
        do {
            this.classId = scanner.nextLine();
       if (this.classId.trim().length()==5){
           if (this.classId.charAt(0)=='J'){
               break;
           }else {
               System.err.println("Vui lòng nhập mã lớp bắt đầu bằng ký tự J");
           }
       }else {
           System.err.println("Vui lòng nhập mã lớp có đúng 5 ký tự");
       }
        }while (true);
        System.out.println("Nhập vào tên lớp");
        do {
            this.className = scanner.nextLine();
            if (this.classId.trim().length()<=10){
                    break;
            }else {
                System.err.println("Vui lòng nhập tên lớp dưới 10 ký tự");
            }
        }while (true);
        System.out.println("Mô tả lớp");
        this.descriptions=scanner.nextLine();
        System.out.println("Nhập vao trạng thái của lớp");
        this.classStatus=Integer.parseInt(scanner.nextLine());
        if (classStatus==1){
            System.out.println("Đang học");
        } else if (this.classStatus==2) {
            System.out.println("Đang tu sửa");
        } else  {
            System.out.println("Đã học xong");
        }
    }

    @Override
    public void displayData() {
        System.out.printf("Mã lớp:%-15s Tên lớp:%-15s Mô tả lớp:%-30s Trang thái:%-15s\n",this.classId,this.className,this.descriptions,this.classStatus);
    }


}

