package com.spring_boot_mybatis.project.file;

import java.io.File;//�꽌踰� �뙆�씪 �떆�뒪�뀥�뿉 ���옣�븯怨� 李얘린 �쐞�빐 媛앹껜 import
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile; //multipart/form-data ���엯 ���옣 媛앹껜

@Controller
public class FileUploadController {
	
	//file upload form �슂泥��뿉 ���븳 泥섎━
	@RequestMapping("/fileUploadForm")
	public String viewUploadform() {
		return "upload/fileUploadForm"; //jsp�럹�씠吏� 諛섑솚 -> �뙆�씪�쟾�넚 �슂泥��씠 �빐�떦 jsp�럹�씠吏��뿉�꽌 諛쒖깮�븯寃� �맖
	}
	
	//�뙆�씪�쟾�넚 �슂泥� 泥섎━ 硫붿냼�뱶
	//(1)1媛� �뙆�씪 �뾽濡쒕뱶 : �뙆�씪紐� 以묐났 諛⑹��븯湲� �쐞�빐 �뙆�씪紐� 蹂�寃�(UUID�몴以��꽌�떇臾몄옄瑜� �뙆�씪紐� �븵�뿉 異붽��빐�꽌 �뙆�씪紐� 蹂�寃�)
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("uploadFile") MultipartFile file, Model model) throws IOException {
		//1.�뙆�씪 ���옣 寃쎈줈 蹂��닔 (臾몄옄�뿴�삎 蹂��닔) : �꽌踰꾩륫�뿉�꽌 �궗�슜�븷 ���옣�냼�쓽 二쇱냼(�쁽�옱 而댄벂�꽣�쓽 �뙆�씪�떆�뒪�뀥 c�뱶�씪�씠釉뚯뿉 ���옣�븷 �삁�젙)
		//寃쎈줈 留덉�留됱뿉 / �엳�뼱�빞 �븿
		//String uploadPath="C:/springBootWorkspace/upload/";  //寃쎈줈�뒗 /濡� �걹�굹�빞 �븿(�뙆�씪紐낆� �뮘�뿉 異붽��븿)
		String uploadPath = "/usr/local/project/upload/"; // 서버 경로
		
		//2.�썝蹂� �뙆�씪 �씠由� ���옣(蹂�寃쎈릺�뒗 �뙆�씪紐낆뿉 �썝�뙆�씪紐낆쓣 �룷�븿�떆�궎湲� �쐞�빐 蹂��닔�뿉 ���옣)
		String originalFileName = file.getOriginalFilename();//�쟾�넚�맂 �뙆�씪紐낆쓣 諛섑솚
		
		//3.�뙆�씪紐� 以묐났 �뵾�븯湲� �쐞�빐 UUID �깮�꽦 �썑 �뙆�씪�떆�뒪�뀥�뿉 ���옣�븷 �씠由� �깮�꽦
		UUID uuid = UUID.randomUUID(); //8-4-4-4-12�쓽 5媛쒖쓽 臾몄옄洹몃９�쓣 �븯�씠�뵂�쑝濡� 援щ텇�빐�꽌 �깮�꽦�빐 以�
		String savedFileName = uuid.toString() + "_" + originalFileName; //蹂�寃쎈맂 �뙆�씪紐낆쑝濡� ���옣�냼�뿉 ���옣
		
		//4.�뙆�씪媛앹껜 �깮�꽦(�쁽�옱 �떆�뒪�뀥�뿉 ���옣�븯湲곗쐞�븳 �쁽�옱 �떆�뒪�뀥 �궗�슜 �뙆�씪 媛앹껜 : �쁽�옱 �떆�뒪�뀥�뿉 醫낆냽�릺�뒗 �뙆�씪 媛앹껜媛� �깮�꽦)
		//�뙆�씪 媛앹껜 �깮�꽦�옄�뿉�뒗 ���옣寃쎈줈���뙆�씪紐낆쓣 留ㅺ컻蹂��닔濡� �쟾�떖
		File sendFile = new File(uploadPath + savedFileName);
		
		//5.���옣�냼(sendFile)濡� �뙆�씪誘명꽣濡� �쟾�넚�맂�뙆�씪(file)�쓣 �쟾�넚
		file.transferTo(sendFile); //�겢�씪�씠�뼵�듃媛� �쟾�떖�븳 �뙆�씪�쓣 �쁽�옱 �떆�뒪�뀥�뿉 ���옣
		
		//�겢�씪�씠�뼵�듃�뿉寃� �뙆�씪 ���옣 寃곌낵 �쟾�넚 : ���옣 �뙆�씪紐낆쓣 view�럹�씠吏�濡� �쟾�떖
		model.addAttribute("originalFileName",originalFileName);
		return "upload/fileUploadResultView"; //view �럹�씠吏� 寃곗젙
	}
	
	//(2) �뿬�윭媛쒖쓽 �뙆�씪 �뾽濡쒕뱶
	//Multi-pard/Formdata �삎�떇�쓽 �뙆�씪 : MultipartFile 媛앹껜蹂��닔�뿉 ���옣 媛��뒫
	//�뿬�윭媛쒖쓽 Multi-pard/Formdata媛� �쟾�넚 �릺誘�濡� MultipartFile�쓣 �썝�냼濡� 媛뽯뒗 ArralyList�뿉 ���옣�븷 �닔 �엳�쓬
	@RequestMapping("/fileUploadMultiple")
	public String fileUploadMultiple(@RequestParam("uploadFileMulti") ArrayList<MultipartFile> files,
										Model model) throws IOException {
		//1.�뙆�씪 ���옣寃쎈줈 �꽕�젙
		String uploadPath="C:/springBootWorkspace/upload/";  //寃쎈줈�뒗 /濡� �걹�굹�빞 �븿(�뙆�씪紐낆� �뮘�뿉 異붽��븿)
		
		//1-1. �뿬�윭媛쒖쓽 �뙆�씪 �씠由� ���옣�븷 由ъ뒪�듃蹂��닔 �깮�꽦 : 寃곌낵 �럹�씠吏��뿉 異쒕젰�븷�닔 �엳寃� ���옣
		ArrayList<String> originalFileNameList = new ArrayList<String>();
		
		//�뿬�윭媛� �뙆�씪 吏묓빀�쓣 �닚�쉶�븯硫댁꽌 媛� �뙆�씪�쓽 �젙蹂� 異붿텧
		for (MultipartFile file :files) {
			//file�뿉�뒗 �쟾�넚�맂 �뙆�씪 1媛쒖쓽 �젙蹂닿� ���옣�릺�뼱 �엳�쓬
			//2.�썝蹂� �뙆�씪 �씠由� ���옣(蹂�寃쎈릺�뒗 �뙆�씪紐낆뿉 �썝�뙆�씪紐낆쓣 �룷�븿�떆�궎湲� �쐞�빐 蹂��닔�뿉 ���옣)
			String originalFileName = file.getOriginalFilename();//�쟾�넚�맂 �뙆�씪紐낆쓣 諛섑솚
			//�썝蹂명뙆�씪 �씠由꾩쓣 由ъ뒪�듃�뿉 ���옣
			originalFileNameList.add(originalFileName);
			//3.�뙆�씪紐� 以묐났 �뵾�븯湲� �쐞�빐 UUID �깮�꽦 �썑 �뙆�씪�떆�뒪�뀥�뿉 ���옣�븷 �씠由� �깮�꽦
			UUID uuid = UUID.randomUUID(); //8-4-4-4-12�쓽 5媛쒖쓽 臾몄옄洹몃９�쓣 �븯�씠�뵂�쑝濡� 援щ텇�빐�꽌 �깮�꽦�빐 以�
			String savedFileName = uuid.toString() + "_" + originalFileName; //蹂�寃쎈맂 �뙆�씪紐낆쑝濡� ���옣�냼�뿉 ���옣
			//4.�뙆�씪媛앹껜 �깮�꽦(�쁽�옱 �떆�뒪�뀥�뿉 ���옣�븯湲곗쐞�븳 �쁽�옱 �떆�뒪�뀥 �궗�슜 �뙆�씪 媛앹껜 : �쁽�옱 �떆�뒪�뀥�뿉 醫낆냽�릺�뒗 �뙆�씪 媛앹껜媛� �깮�꽦)
			//�뙆�씪 媛앹껜 �깮�꽦�옄�뿉�뒗 ���옣寃쎈줈���뙆�씪紐낆쓣 留ㅺ컻蹂��닔濡� �쟾�떖
			File sendFile = new File(uploadPath + savedFileName);
			//5.���옣�냼濡� �쟾�넚
			file.transferTo(sendFile);//1媛� �뙆�씪�씠 ���옣�냼�뿉 ���옣
		}//�쟾�떖�맂 �뙆�씪 �뿬�윭媛쒕�� �븳媛쒖뵫 ���옣�냼�뿉 ���옣�븯�뒗 肄붾뱶
		
		model.addAttribute("originalFileNameList",originalFileNameList);
		return "upload/fileUploadMultipleResultVew";
	}
	
	//3. �뙆�씪紐� 蹂�寃� �뾾�씠 upload
	///fileOriginalNameUplode�슂泥��뿉 ���빆 泥섎━ 硫붿냼�뱶
	@RequestMapping("/fileOriginalNameUplode")
	public String fileOriginalNameUplode(@RequestParam("uploadFileOrigin") MultipartFile file,
										 Model model) throws IOException{
			//1. �뙆�씪 ���옣 寃쎈줈 �꽕�젙 : �뵒�젆�꽣由� �깮�꽦 �썑 ���옣
			String uploadPath = "C:/springBootWorkspace/upload/product_image/";
			
			//2. �썝蹂� �뙆�씪 �씠由� ���옣
			String originFileName = file.getOriginalFilename();
			
			//3. �뙆�씪 媛앹껜 �깮�꽦
			File sendFile = new File(uploadPath + originFileName);
			
			//4. ���옣�냼濡� �쟾�넚
			file.transferTo(sendFile);
			
			model.addAttribute("originalFileName",originFileName);
		
		
		return "upload/fileUploadResultView";
	}
	
	//�씠誘몄� �뙆�씪 �뾽濡쒕뱶 �뤌 �슂泥�
	@RequestMapping("/imageFileUploadForm")
	public String imageFileUploadForm() {
		return "upload/imageFileUpload";
	}
	
	//upload �뤃�뜑�뿉 �씠誘몄� �뙆�씪 �뾽濡쒕뱶
	//ajax媛� �슂泥� -> 臾몄옄�뿴 �뜲�씠�꽣 諛섑솚(view �럹�씠吏� 諛섑솚 �븘�떂)
	@ResponseBody
	@RequestMapping("/imageFileUpload")
	public String imageFileUpload(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		
		String uploadPath = "c:/springBootWorkspace/upload/";
		
		String originFileName = file.getOriginalFilename();
		
		File sendFile = new File(uploadPath + originFileName);
		
		file.transferTo(sendFile);
		
		String result ="success";
		return result;
	}
	
	//�씠誘몄� �뙆�씪 �뾽濡쒕뱶 �뤌 �슂泥�
	@RequestMapping("/audioFileUploadForm")
	public String audioFileUploadForm() {
		return "upload/audioFileUpload";
	}
	
	//upload �뤃�뜑�뿉 �씠誘몄� �뙆�씪 �뾽濡쒕뱶
	//ajax媛� �슂泥� -> 臾몄옄�뿴 �뜲�씠�꽣 諛섑솚(view �럹�씠吏� 諛섑솚 �븘�떂)
	@ResponseBody
	@RequestMapping("/audioFileUpload")
	public String audioFileUpload(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		
		String uploadPath = "c:/springBootWorkspace/upload/";
		
		String originFileName = file.getOriginalFilename();
		
		File sendFile = new File(uploadPath + originFileName);
		
		file.transferTo(sendFile);
		
		String result ="success";
		return result;
	}	
}

























