## RestAPI test(swagger 3.0)
http://sphabucks.xyz/swagger-ui.html

## 💻 ENV

* Java 17 이상
* Spring
  * Project - Gradle-Groovy
  * Language - Java
  * Spring Boot - 3.0.2
  * Packaging - Jar
  * Dependencies
    * Spring Web
    * Spring Security
    * Lombok
    * Spring Data JPA
    * MySQL Driver
* Swagger
  * springdoc-openapi-starter-webmvc-ui:2.0.3

## 👨‍💻 백엔드
* 주영민
  * 결제 API(Iamport) 적용
  * 검색 및 필터링 API 적용
* 정승훈
  * Spring Security 적용 및 커스터마이징
* 김지욱
  * CI를 위한 Dockerfile 및 git action workflow yaml파일 제작
  * dockerhub와 GCP(Google Cloud Platform)을 이용한 서버 배포
  * SecurityConfig에 corsConfiguration을 추가하여 CORS에러 처리

## 👇 디렉토리 구조
<details>
<summary>src</summary>
<div>

```
└─sphabucks  
  ├─domain
  │  ├─carts  
  │  ├─event  
  │  ├─paying  
  │  ├─productimage  
  │  ├─products  
  │  ├─purchaseHistory  
  │  ├─shipping  
  │  ├─tag  
  │  └─users  
  └─global  
     ├─auth  
     ├─config  
     ├─email  
     ├─exception  
     ├─paging  
     ├─responseEntity  
     └─utility  
```
</div>
</details>

## ✨ 핵심 기능

1. 로그인/회원가입
   * 회원 가입 시 Redis 서버와 JavaMailSender를 이용하여 사용자인증을 구현하였습니다. 
   * 로그인이 완료되었을 때 JWT를 생성하여 엑세스토큰으로 사용합니다.
2. 장바구니
   * 한 상품 당 최대 5개까지만 담을 수 있습니다.
   * 단일 삭제, 선택 삭제, 전체 삭제 등 다양한 기능을 구현하였습니다.
3. 배송지 관리
   * 등록된 배송지가 없을 경우 처음 등록하는 배송지가 기본 배송지가 됩니다.
   * 새로운 기본 배송지를 등록할 경우 기존의 기본 배송지가 취소됩니다.
   * 기본 배송지가 첫 번째로 표시되며 이후로는 최근에 수정된 순으로 표시됩니다.
4. 상품 추천
   * 추천 MD, 베스트 등 추천 상품 정보를 반환합니다.
5. 검색
   * 키워드 검색이 가능합니다.
   * 검색 결과별 필터링 메뉴를 다르게 설정하였습니다.