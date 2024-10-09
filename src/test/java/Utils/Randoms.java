package Utils;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Randoms {

    public static final Logger logger = LogManager.getLogger();
    public static Gson gson = new Gson();
    public static SoftAssert softAssert = new SoftAssert();

    static Faker faker = new Faker();

    public static String FIRSTNAME = faker.name().firstName();

    public static String LASTNAME = faker.name().lastName();

    static LocalDate currentDate = LocalDate.now();

    static LocalDate pastDate = currentDate.minusYears(2);

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM_dd");

    public static String CHECK_IN_DATE = pastDate.format(formatter);

    static LocalDate futureDate = currentDate.plusYears(2);

    public static String CHECK_OUT_DATE = futureDate.format(formatter);

    public static String ADDITIONAL_NEEDS = faker.lorem().sentence();

    public static Integer TOTAL_PRICE = new Random().nextInt(10,500);



    @Test
    public void nameReturn()
    {
        System.out.println(FIRSTNAME);
        System.out.println(LASTNAME);
        System.out.println(CHECK_IN_DATE);
        System.out.println(CHECK_OUT_DATE);
        System.out.println(TOTAL_PRICE);

    }

}
