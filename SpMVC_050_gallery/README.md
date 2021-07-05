# spring file upload project
* web client에서 파일을 선택하여 서버에 Upload하기 
* 이미지 게시판, 이미지 갤러리 등을 만들때 사용한다.

## 필요한 Dependency

	<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
	<dependency>
		<groupId>commons-io</groupId>
		<artifactId>commons-io</artifactId>
		<version>2.10.0</version>
	</dependency>
		
	<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
	<dependency>
		<groupId>commons-fileupload</groupId>
 		<artifactId>commons-fileupload</artifactId>
		<version>1.4</version>
	</dependency>
	
	
## root-context에 
	<!-- Root Context: defines shared resources visible to all other web components -->
		
	<!-- file upload와 관련한 bean을 설정하기 -->
	<!-- 
	단위
	0 : 개
	000 : K(Killo) 개
	000,000 : M(Mega) 개
	000,000,000 : G(Giga) 개
	000,000,000,000 : T(Tera) 개
	
	 -->
	<bean id="multipartResolver" 
			class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSizePerFile" value="3000000"/>  <!-- 파일하나당 최대  : 3M -->
		<property name="maxUploadSize" value="350000000"/>  <!--  전체파일의 크기  -->
	</bean>
	
* maxUploadPerFile : 파일 1개의 용량 제한
* maxUploadFile : 전체 파일 용량 제한, 다수의 파일을 올릴때



