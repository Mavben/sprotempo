package com.example.demo.book.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

// DTO는 클라이언트의 HTTP 파라미터를 담는 컨테이너 객체로 사용됩니다.
// 책을 사용할 때 사용할 객체 정의

@Getter    
@Setter    
public class BookCreateDTO {    
  @NonNull    
  private String title;    

  @NonNull    
  private Integer price;

public Object getTitle() {
	// TODO Auto-generated method stub
	return null;
}    
}  