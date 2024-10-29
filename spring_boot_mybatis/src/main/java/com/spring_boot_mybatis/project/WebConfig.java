package com.spring_boot_mybatis.project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //�쇅遺��뙆�씪 �뿰寃� �꽕�젙 愿��젴 �겢�옒�뒪(�씪諛� �꽕�젙 : application.property �뙆�씪�뿉 �꽕�젙�븷 �닔 �뾾�뒗 �궡�슜�쓣 �봽濡쒓렇�옩�쑝濡� 援ъ꽦)
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//�쇅遺��뙆�씪 �씠誘몄� �궗�슜�븯�룄濡� mapping
		registry.addResourceHandler("/prd_images/**")
		.addResourceLocations("file:///usr/local/project/product_images/"); // 서버 경로
		//.addResourceLocations("file:///C:/springWorkspace/product_images/");
		registry.addResourceHandler("/images/**","/audio/**")
		.addResourceLocations("file:///usr/local/project/upload/"); // 서버 경로
		//.addResourceLocations("file:///C:/springBootWorkspace/upload/");//�븳媛� �젅��寃쎈줈�뿉 �뿬�윭 url 留ㅽ븨 媛��뒫		
	}
	
	

}
