package com.callor.book.exec;

import com.callor.book.model.BookDTO;

public class Main_01 {

	public static void main(String[] args) {
		
		// VO, DTO를 생성하고 데이터를 담기
		// 1. 빈(blank) 객체를 생성하고,
		//		필요한 값을 setter를 사용하여 저장하기
		// 		문제 : 필요한 데이터만큼 Setter method를 계속 나열해야 한다.
		BookDTO bookDTO = new BookDTO();
		bookDTO.setTitle("자바야놀자");
		
		// 2. 필드 생성자를 사용하여
		// 		객체를 생성하면서 , 값을 주입하는 방법
		//		문제 : 일부 데이터만 주입하여 객체를 생성할 수 없다.
		// 				일부 데이터를 위하여 별도의 생성자를 또 만들어야 한다.
		//			 	데이터의 주입 순서가 바뀌면 어떤 값이 저장될 지 모른다.
		//				이는 전적으로 개발자 책임으로 귀결된다.
		
		BookDTO bookDTO1 = new BookDTO(
				"자바야놀자", 
				"linke",
				"image", 
				"author", 
				"price", 
				"discount", 
				"publisher", 
				"isbn", 
				"desc",
				"pubdate");
		
		// 3. Builder 패턴 
		//		(lombok이 지원하기 전에는 직접 builder 객체를 만들었으나
		//		lombok이 지원하게 된 이후 간편하게 생성가능)
		// 	객체를 생성(초기화)할때 new 키워드를 사용하지 않고
		//		내부에 만들어져 있는 builder() method를 먼저 호출
		//		builder() method는 객체를 생성하여 return 하는 코드. 
		//	필요한 변수를 setting 할때
		// 		setter method를 사용하지 않고
		// 		변수명() 형태의 method를 사용하여 값을 저장.
		// 	값이 저장되고 생성된 객체를 사용할 수 있도록
		// 		객체인스턴스(bookDTO2)에게 전달하기 위하여
		// 		build() method를 호출한다.
		//
		// 	new 키워드로 생성자를 호출하지 않는다.
		// 	필요한 변수(속성, 멤버변수)만 가지고 있는 객체를 만들 수 있다.
		//	변수 이름을 직접 호출하는 방식으로 값을 저장할 수 있다.
		
		BookDTO bookDTO2 = BookDTO.builder() //클래스이름.builder() 로 시작
							.title("자바야놀자")  // 필요한 데이터를 변수이름이용해 셋팅
							.price("20000")
							.isbn("9760000")
							.build(); // build() method 호출로 끝냄
		
		System.out.println(bookDTO2.toString());
	}
}
