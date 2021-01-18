package se.wenzin.foodiecalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages= {"se.wenzin.foodiecalc"})
public class FoodieCalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieCalcApplication.class, args);
	}

}
