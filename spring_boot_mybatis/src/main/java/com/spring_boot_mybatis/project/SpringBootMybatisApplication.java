package com.spring_boot_mybatis.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//�봽濡쒖젥�듃�뒗 springboot 洹쒖튃�쓣 �뵲瑜대뒗 �봽濡쒓렇�옩�쑝濡� �벑濡�
@SpringBootApplication
@ComponentScan(basePackages= {"com.spring_boot_mybatis.project"})
@MapperScan(basePackages= {"com.spring_boot_mybatis.project"})

//DB 연결정보 : 프로퍼티 사용
//로컬 경로 / 서버 경로
@PropertySources({
	@PropertySource(value={"file: c:/springBootWorkspace/webservice /configure.properties", 
       			"file:/usr/local/project/properties/configure.properties", }, 
		       ignoreResourceNotFound=true)
}) 

public class SpringBootMybatisApplication {

	//website �떆�옉 吏��젏
	public static void main(String[] args) {
		//�꽌踰� �떆�옉
		//�뒪�봽留곷��듃�뒗 諛섎뱶�떆 main() �룷�븿�븯�뒗 java �뙆�씪�씠 �엳�뼱�빞 �븿
		SpringApplication.run(SpringBootMybatisApplication.class, args);
	}

}
