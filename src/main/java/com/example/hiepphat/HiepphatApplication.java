package com.example.hiepphat;
import com.jayway.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class HiepphatApplication extends SpringBootServletInitializer {

	public static void main(String[] args)
	{
	//	getEmployees();
	SpringApplication.run(HiepphatApplication.class,args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(HiepphatApplication.class);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
						.allowedHeaders("*");
			}
		};
	}
	private static void getEmployees()
	{
		final String uri = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query=tomato&pageSize=1&pageNumber=1";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		JSONObject jsonObject=new JSONObject(result);
		JSONArray array=jsonObject.getJSONArray("foods");
		for(int i=0;i<array.length();i++){
			JSONObject raw=array.getJSONObject(i);
			for(int j=0;j<raw.length();j++){
				JSONArray array2=raw.getJSONArray("foodNutrients");
				for(int m=0;m<array2.length();m++){
					JSONObject jsonObject1=array2.getJSONObject(m);
					String nutrion=jsonObject1.getString("nutrientName");
					if(nutrion.contains("Protein")){

					break;
					}
				}
			}

		}




	}
}
