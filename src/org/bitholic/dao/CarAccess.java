package org.bitholic.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.bitholic.utils.HibernateUtil;
import org.hibernate.*;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bitholic on 16/7/6.
 */
public class CarAccess {
    public static Session session;
    public static Gson gson;

    public static void main(String[] args){
        String s = "{\"licensePlate\":\"皖S0008\",\"trademark\":\"东风客车\",\"seat\":\"30\",\"registerDate\":\"\",\"insuranceDate\":\"\",\"vehicleLicense\":\"\",\"state\":\"0\"}";
        String t = "{\"licensePlate\":\"1\",\"trademark\":\"\",\"seat\":\"\",\"registerDate\":\"\",\"insuranceDate\":\"\",\"vehicleLicense\":\"\",\"state\":1}";
        //System.out.println(deleteCar("fefe"));
        //System.out.println(searchCar("drivingLicense","2014211972",0,100));
        //exportCars();
        System.out.print(getCars());

    }

    public static String getCar(String licenseNumber){
        session = HibernateUtil.getSession();
        Query query = session.createQuery("from Car where licensePlate=?");
        query.setString(0,licenseNumber);
        Car car = (Car)query.uniqueResult();
        gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(car);

    }

    //搜索车辆信息
    public static String searchCar(String option, String value, Integer offset, Integer limit) {
        String output;
        session = HibernateUtil.getSession();
        Query query,query1;
        try {
            if (option.equals("trademark")) {
                query = session.createQuery("from Car where trademark=?");
                query.setString(0, value);
                query1 = session.createQuery("select count(*) from Car where trademark=?");
                query1.setString(0, value);
            } else if (option.equals("licensePlate")) {
                query = session.createQuery("from Car where licensePlate=?");
                query.setString(0, value);
                query1 = session.createQuery("select count(*) from Car where licensePlate=?");
                query1.setString(0, value);
            } else if (option.equals("seat")) {
                query = session.createQuery("from Car where seat=?");
                query.setString(0, value);
                query1 = session.createQuery("select count(*) from Car where seat=?");
                query1.setString(0, value);
            } else if (option.equals("vehicleLicense")) {
                query = session.createQuery("from Car where vehicleLicense=?");
                query.setString(0, value);
                query1 = session.createQuery("select count(*) from Car where vehicleLicense=?");
                query1.setString(0, value);
            } else {
                query = null;
                query1 = null;
            }

            Transaction transaction = session.beginTransaction();
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<Car> cars = query.list();
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).setId(offset + i + 1);
            }
            Long sum = (Long) query1.uniqueResult();
            transaction.commit();

            CarJson carJson = new CarJson(1,sum, cars);
            gson = new GsonBuilder().serializeNulls().create();
            output = gson.toJson(carJson);
        }catch (Exception e){
            output = "{\"state\":2, \"info\":\"发生错误!\"}";
        }
        return output;
    }

    //所有车辆
    public static String getCars(){
        String output;
        session = HibernateUtil.getSession();
        Query query1, query2;

        try {
            query1 = session.createQuery("select count(*) from Car");
            query2 = session.createQuery("from Car");
            //获取总条数
            Transaction transaction = session.beginTransaction();
            Long sum = (Long) query1.uniqueResult();

            List<Car> cars = query2.list();
            transaction.commit();

            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).setId(i + 1);
            }
            CarJson carJson = new CarJson(1, sum, cars);
            gson = new GsonBuilder().serializeNulls().create();
            output = gson.toJson(carJson);
        } catch (Exception e) {
            output = "{\"state\":2, \"info\":\"发生内部错误!\"}";
        }
        return output;
    }

    //所有车辆的分页
    public static String getCars(int type, Integer offset, Integer limit) {
        String output;
        session = HibernateUtil.getSession();
        Query query1, query2;

        try {
            if (type == 1) {
                query1 = session.createQuery("select count(*) from Car");
                query2 = session.createQuery("from Car");
            } else {
                query1 = session.createQuery("select count(*) from Car where state=0");
                query2 = session.createQuery("from Car where state=0");
            }
            //获取总条数
            Transaction transaction = session.beginTransaction();
            Long sum = (Long) query1.uniqueResult();

            query2.setFirstResult(offset);
            query2.setMaxResults(limit);
            List<Car> cars = query2.list();
            transaction.commit();
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).setId(offset + i + 1);
            }
            CarJson carJson = new CarJson(1, sum, cars);
            gson = new GsonBuilder().serializeNulls().create();
            output = gson.toJson(carJson);
        } catch (Exception e) {
            output = "{\"state\":2, \"info\":\"发生内部错误!\"}";
        }
        return output;
    }

    //添加车辆
    public static String addCar(String jsonInput){
        String output = null;
        Gson gson = new Gson();
        try{
            Car car = gson.fromJson(jsonInput, Car.class);
            //检查车是否在库中
            String licensePlate = car.getLicensePlate();
            Session session1 = HibernateUtil.getSession();
            Query query = session1.createQuery("from Car where licensePlate=?");
            query.setString(0,licensePlate);
            Car car1 = (Car)query.uniqueResult();

            if(car1 == null) {
                //Car car = new Car("皖S0001",30,"东风客车","2016-07-01","2017-07-01","2014211981",0);
                if(car.getRegisterDate().equals("")){
                    car.setRegisterDate(null);
                }
                if(car.getInsuranceDate().equals("")){
                    car.setInsuranceDate(null);
                }
                if(car.getVehicleLicense().equals("")){
                    car.setVehicleLicense(null);
                }
                session = HibernateUtil.getSession();
                Transaction transaction = session.beginTransaction();
                session.save(car);
                transaction.commit();
                output = "{\"state\":1, \"info\":\"添加成功!\"}";
            }else{
                output = "{\"state\":2, \"info\":\"车辆已在数据库中,请勿重新添加!\"}";
            }
        }catch(HibernateException e){
            output = "{\"state\":3, \"info\":\"保存失败!请检查数据格式是否正确或联系数据库管理员.\"}";
        }catch(JsonSyntaxException e){
            output = "{\"state\":4, \"info\":\"格式错误!\"}";
        }catch(Exception e){
            output = "{\"state\":5, \"info\":\"未知错误!请联系管理员.\"}";
        }
        return output;
    }

    public static String deleteCar(String licensePlate){
        String output;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery("from Car where licensePlate=?");
            query.setString(0,licensePlate);
            Car car = (Car)query.uniqueResult();
            Transaction transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
            output = "{\"state\":1, \"info\":\"删除成功!\"}";
        }catch(Exception e){
            e.printStackTrace();
            output = "{\"state\":2, \"info\":\"删除失败!\"}";
        }
        return output;
    }
    /*
    public static void exportCars(String path){
        session = HibernateUtil.getSession();
        Query query = session.createQuery("from Car");
        List<Car> cars = query.list();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("所有车辆");
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("序号");
        cell = row.createCell(1);
        cell.setCellValue("车牌号");
        cell = row.createCell(2);
        cell.setCellValue("品牌");
        cell = row.createCell(3);
        cell.setCellValue("座位数");
        cell = row.createCell(4);
        cell.setCellValue("注册日期");
        cell = row.createCell(5);
        cell.setCellValue("保险日期");
        cell = row.createCell(6);
        cell.setCellValue("驾驶证");
        cell = row.createCell(7);
        cell.setCellValue("行驶证");
        cell = row.createCell(8);
        cell.setCellValue("状态");

        for(int i = 0; i < cars.size(); i++){
            row = sheet.createRow(i+1);

            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(cars.get(i).getLicenseNumber());
            row.createCell(2).setCellValue(cars.get(i).getTrademark());
            row.createCell(3).setCellValue(cars.get(i).getSeatNumber());
            row.createCell(4).setCellValue(cars.get(i).getRegisterDate());
            row.createCell(5).setCellValue(cars.get(i).getInsuranceDate());
            row.createCell(6).setCellValue(cars.get(i).getDrivingLicense());
            row.createCell(7).setCellValue(cars.get(i).getVehicleLicense());
            row.createCell(8).setCellValue(cars.get(i).getState());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try{
            wb.write(os);
        }catch(IOException e){
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();
        File file = new File(path);
        OutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   */
}
