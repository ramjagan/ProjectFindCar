import java.io.IOException;
import org.junit.Test;
import java.io.*;

public class FindCars extends MasterBase {

    public FindCars(){
        super();
    }
    public static void main(String args[]) throws IOException {
        FindCars blueTesla = new FindCars();
        blueTesla.FindCar("Blue","Tesla");
    }

    @Test
    public void FindBlueTesla() throws IOException{
        System.out.println("==============================================");
        System.out.println("Test- Find BLue Tesla");
        System.out.println("==============================================");
        FindCar("Blue", "Tesla");
    }
    @Test
    public void LowestRentalCar() throws IOException{
        System.out.println("==============================================");
        System.out.println("Test- Lowest Rental Car");
        System.out.println("==============================================");
        Boolean Discount = Boolean.FALSE;
        LowestRental(Discount);
    }
    @Test
    public void LowestRentalCarWithDiscount() throws IOException{
        System.out.println("==============================================");
        System.out.println("Test- Lowest Rental Car with Discount");
        System.out.println("==============================================");
        Boolean Discount = Boolean.TRUE;
        LowestRental(Discount);
    }
    @Test
    public void HighRevenueGeneratingCars() throws IOException{
        System.out.println("==============================================");
        System.out.println("Test- High Reveneue Generating Car");
        System.out.println("==============================================");
        HighRevenueGeneratingCar();
    }

}
