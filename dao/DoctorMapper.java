import static com.xxx.util.Constant.DOCTOR_FILENAME;
import static com.xxx.util.Constant.SPLIT_CHARACTER;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.xxx.xxx_interface.dao.IFileOperation;
import com.xxx.po.Doctor;

/**
 * 医生的mapper
 */
public class DoctorMapper implements IFileOperation<Doctor> {
    
    private static DoctorMapper mapper = new DoctorMapper();
    
    private DoctorMapper() {}
    
    /**
     * 获取单例
     * @return
     */
    public static DoctorMapper getInstance() {
        return mapper;
    }

    /**
     * 增
     */
    @Override
    public void add(Doctor object) {
        if (object != null) { 
            List<Doctor> doctorList = getAll();
            for (Doctor doctor : doctorList) {
                if (doctor.getDoctorId().equals(object.getDoctorId())) {
                    return;
                }
            }
            doctorList.add(object);
            writeDepartment(doctorList);
        }
        
    }

    /**
     * 删
     */
    @Override
    public void delete(Doctor object) {
        List<Doctor> doctorList = getAll();
        Doctor deleteObj = null;
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorId().equals(object.getDoctorId())) {
                deleteObj = doctor;
                break;
            }
        }
        if (deleteObj != null) {
            doctorList.remove(deleteObj);
        }
        writeDepartment(doctorList);
    }

    /**
     * 获取所有医生的列表
     */
    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctorList = new ArrayList<>();        
        try (Scanner scanner = new Scanner(new FileReader(DOCTOR_FILENAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                doctorList.add(generateDoctor(line));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return doctorList;
    }

    /**
     * 改
     */
    @Override
    public void update(Doctor object) {
        List<Doctor> doctorList = getAll();
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorId().equals(object.getDoctorId())) {
                doctor = object;
                break;
            }
        }
        writeDepartment(doctorList);
    }
    
    /**
     * 完成修改后的写入
     * @param departmentList
     */
    private void writeDepartment(List<Doctor> departmentList) {
        try (BufferedWriter tempWriter = new BufferedWriter(new FileWriter(DOCTOR_FILENAME));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DOCTOR_FILENAME, true))) {  
            tempWriter.write("");
            for (Doctor doctor : departmentList) {
                String line = doctor.getDoctorId() + SPLIT_CHARACTER + doctor.getUserName() + SPLIT_CHARACTER
                        + doctor.getPassword() + SPLIT_CHARACTER + doctor.getName() + SPLIT_CHARACTER
                        + doctor.getDepartment() + SPLIT_CHARACTER + doctor.getTitle() + SPLIT_CHARACTER
                        + doctor.getIsOnline()+ "\n";
                bufferedWriter.write(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    /**
     * 根据医生的属性生成医生对象
     * @param line
     * @return
     */
    private Doctor generateDoctor(String line) {
        String[] strList = line.split(SPLIT_CHARACTER);
        int id = Integer.parseInt(strList[0]);
        String userName = strList[1];
        String password = strList[2];
        String name = strList[3];
        String department = strList[4];
        String title = strList[5];
        boolean isOnline = Boolean.parseBoolean(strList[6]);
        Doctor doctor = new Doctor(id, userName, password, name, department, title, isOnline);
        return doctor;
    }
    
    /**
     * 获取下一个ID
     * @return
     */
    public Integer getNextId() {
        List<Doctor> doctorList = getAll();
        int length = doctorList.size(); 
        return ++length;
    }
    
    /**
     * 根据姓名获取医生
     * @param name
     * @return
     */
    public Doctor getElementByName(String name) {
        if (name == null) {
            return null;
        }
        List<Doctor> doctorList = getAll();
        for (Doctor doctor : doctorList) {
            if (name.equals(doctor.getName())) {
                return doctor;
            }
        }
        return null;
    }

}
