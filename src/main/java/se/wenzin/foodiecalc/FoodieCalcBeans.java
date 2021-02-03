package se.wenzin.foodiecalc;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodieCalcBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
