import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MasterBase {
    public String content;
    public MasterBase(){

    }

    public void readFileCOntent(String filePath){
        try{
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JSONArray ListofAllCars(){
        String filepath = System.getProperty("user.dir")+ File.separator+"Cars.txt";
        readFileCOntent(filepath);
        JSONObject listcars= new JSONObject(content);
        JSONArray cars = new JSONArray();
        cars = (JSONArray) listcars.get("Car");
        return cars;
    }
    public String printResults(Map<String,Integer> RentalCar, int rentalPrice){
        int cheapcar = 0;
        for(String Key: RentalCar.keySet()){
            if(RentalCar.get(Key).equals(rentalPrice)){
                System.out.println("Car Details: "+Key+" ,Final Price: "+RentalCar.get(Key));
                cheapcar++;
            }
        }
        return String.valueOf(cheapcar);
    }
    public void FindCar(String color, String Make) throws IOException{
        JSONArray cars = ListofAllCars();
        int count = 0;
        System.out.println("Requested car Details");
        System.out.println("==============================================");
        for(int i=0; i<cars.length();i++){
            JSONObject car = (JSONObject) cars.get(i);
            if(car.get("make").toString().equals(Make)){
                JSONObject meta = (JSONObject) car.get("metadata");
                if(meta.get("Color").toString().equals(color)){
                    System.out.println(car.get("make").toString()+ ",Model-"+car.get("model").toString());
                    System.out.println("Detail of the car: "+meta.get("Notes").toString());
                    System.out.println("VIN Number: "+car.get("vin").toString());
                    System.out.println("==============================================");
                    count++;
                }
            }
        }
        System.out.println("Number of cars found in the search criteria: "+count);
    }
    public void LowestRental(Boolean Discount )throws IOException{
        JSONArray cars = ListofAllCars();
        int rentalPrice=0;
        int count=0;
        Map<String,Integer> RentalCar = new HashMap<String,Integer>();
        System.out.println("Requested car details");
        System.out.println("==============================================");
        for(int i=0; i<cars.length();i++){
            JSONObject car = (JSONObject) cars.get(i);
            JSONObject rent = (JSONObject) car.get("perdayrent");
            int price = (Integer)rent.get("Price");
            int discount= (Integer)rent.get("Discount");
            if(!Discount){
                if(i==0 || rentalPrice >=price){}
                rentalPrice=price;
                String carDetails= car.get("make").toString()+", "+car.get("model").toString()+"\nVIN Number: "+car.get("vin".toString());
                RentalCar.put(carDetails,rentalPrice);
            }else{
                {
                    if(i==0 || rentalPrice >=price-discount){}
                    rentalPrice=price-discount;
                    String carDetails= car.get("make").toString()+", "+car.get("model").toString()+"\nVIN Number: "+car.get("vin".toString())+"\nRental Price: "+rent.get("Price");
                    RentalCar.put(carDetails,rentalPrice);
                }
            }
            count++;
        }
        System.out.println("Lowest Priced Car"+ rentalPrice);
        System.out.println("Number of Cars found in search criteria: "+ printResults(RentalCar,rentalPrice)+" among "+count+" cars");
    }
    public  void HighRevenueGeneratingCar(){
        JSONArray cars= ListofAllCars();
        Double profit = 0.00;
        int count=0;
        Map<String,Double> RentalCar = new HashMap<String, Double>();
        System.out.println("Requested Car Details");
        System.out.println("==============================================");
        for (int i=0;i<cars.length();i++){
            JSONObject car = (JSONObject) cars.get(i);
            JSONObject metrics= (JSONObject) car.get("metrics");
            Double yomaintenancecost= (Double) metrics.get("yoymaintenancecost");
            Double depreciation = (Double)metrics.get("depreciation");
            if(i==0 || profit>=yomaintenancecost+depreciation){
                profit= yomaintenancecost+depreciation;
                String carDetails= car.get("make").toString()+", "+car.get("model").toString()+"\nVIN Number: "+car.get("vin").toString();
                RentalCar.put(carDetails,profit);
            }
            count++;
        }
        int cheapcar=0;
        for(String Key: RentalCar.keySet()){
            if(RentalCar.get(Key).equals(profit)){
                System.out.println("Car Details: "+Key+" , Lowest Maintainence Cost: "+RentalCar.get(Key));
                cheapcar++;
            }
        }
        System.out.println("Number of Cars found in the search Criteria: "+cheapcar+" among "+count+" cars");
    }
}
