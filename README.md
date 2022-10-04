># 모바일 키오스크
<br/>
모바일 키오스크는 키오스크에서 주문을 위해 기다리는 손님들을 보고 착안한 3인 팀 프로젝트입니다.<br/>
판매자 기능과 소비자 기능으로 나뉘어 있으며 판매자는 상품과 주문을 관리 할 수 있고 소비자는 별도의 앱 설치 없이 QR코드 스캔으로 주문을 할 수 있습니다.<br/><br/><br/><br/>

**목차**
- [사이트 바로가기](#사이트-바로가기)
	- [판매자 페이지](#판매자-페이지)
	- [소비자 페이지](#소비자-페이지)
- [사용 기술](#사용-기술)
- [도메인](#도메인)
- [페이지 설명](#페이지-설명)
	- [판매자 기능](#판매자-기능)
		- [로그인 및 회원가입](#로그인-및-회원가입)
		- [판매자 메인 페이지](#판매자-메인-페이지)
		- [메뉴 관리](#메뉴-관리)
		- [메뉴 관리(상품 추가 및 수정)](#메뉴-관리_상품-추가-및-수정)
		- [주문 관리](#주문-관리)
	- [소비자 기능](#소비자-기능)

<br/><br/><br/><br/>


> # 사이트 바로가기

> ## 판매자 페이지

[판매자 페이지 링크](http://15.164.155.155:8081)

&nbsp;**테스트용 계정**
 | 아이디 | testAccount |
 |--|--|
 | 비밀번호 | testAccount! |

<br/><br/>
> ## 소비자 페이지
[소비자 페이지 링크](http://15.164.155.155:8081/customer/testAccount)

<img src="https://user-images.githubusercontent.com/74872542/193697005-a3a7c979-0648-41ac-98e3-5b236e8a3248.png" width="300" height="300">  QR코드의 경우 휴대폰 카메라앱으로 비추면 접속 가능합니다.

<br/><br/><br/><br/>
> # 사용 기술

- `java` 
- `Spring Boot` `Spring Security` `Spring Data Jpa` 
- `Thymeleaf` `Javascript` 
- `Bootstrap` 
- `Mysql` 
- `AWS ec2` 
<br/><br/><br/><br/>
> # 도메인

![Untitled (26)](https://user-images.githubusercontent.com/74872542/193699657-0b20dcd6-78f2-448c-9ccb-86a76451d31b.png)

- Member 테이블은 판매자 계정을 뜻합니다. 소비자의 경우 일회성 로그인이기 때문에 접속할 때마다 고유성이 보장되는  UUID를 부여해서 구분합니다.
- Member는 Category를 생성한 후 Item을 관리할 수 있습니다.
- 구매자가 Item에서 쇼핑한 후 장바구니에 저장한 아이템들은 OrderItem에 저장됩니다.
- 구매자가 결제 단계를 통과했다면 Order가 생성되고 Member(판매자)는 Order를 확인하고 주문 처리를 할 수 있게 됩니다.
- Order의 경우 주문 번호의 연속성을 보장해서 판매자의 주문 관리 편의성을 위해  @SequenceGenerator로 id 번호를 따로 생성하고 관리하였습니다.
- Item에 OptionGroup을 통해 Option을 추가할 수 있습니다.
<br/><br/><br/><br/>

> # 페이지 설명

> ## 판매자 기능

### 로그인 및 회원가입

![로그인](https://user-images.githubusercontent.com/74872542/193701616-0b143e62-23cb-43e6-9ede-5036de69bdbb.png)

![회원가입](https://user-images.githubusercontent.com/74872542/193707382-bbc79fc0-5b9d-4186-b79d-5bff3d852db1.png)

<br/><br/><br/><br/>

### 판매자 메인 페이지

![판매자 메인](https://user-images.githubusercontent.com/74872542/193707392-6c38b585-ef1a-47c1-879d-3e2696dd67c1.png)

<br/><br/><br/><br/>

### 메뉴 관리

![메뉴 관리](https://user-images.githubusercontent.com/74872542/193707555-c08cab57-db5e-44de-807b-ec02b67f12b3.png)

<br/><br/><br/><br/>

### 메뉴 관리_상품 추가 및 수정

![메뉴 추가](https://user-images.githubusercontent.com/74872542/193707563-ec366cc2-f77f-475d-a4ee-8e99857f7df6.png)
- 상품 사진의 경우 5MB 이하의 크기만 업로드 가능하게 설정하였습니다.
- 데이터베이스의 저장공간을 적게 차지하고 이미지를 빠르게 띄우기 위해 데이터베이스엔 이미지를 찾을 수 있는 식별번호만 저장하고 이미지는 따로 저장하였습니다.

<br/><br/>

![메뉴 확인](https://user-images.githubusercontent.com/74872542/193707569-7fd8845c-f3aa-4f32-87db-df5f6ed14f83.png)

![옵션그룹추가](https://user-images.githubusercontent.com/74872542/193707577-4fe6a0a3-533b-47d4-afc0-b49be52d3f4c.png)

![옵션 추가](https://user-images.githubusercontent.com/74872542/193707578-f21aff87-458b-4df7-ae00-b5f35e244e11.png)

<br/><br/><br/><br/>

### 주문 관리

![주문 관리](https://user-images.githubusercontent.com/74872542/193707917-2e5c0613-6408-4e84-89f7-27ec3f5cf1e9.png)
- 구매자로부터 주문이 들어오면 주문 카드가 표시되고 간단한 주문 정보들을 확인할 수 있습니다.
- 주문 카드는 최근 들어온 카드가 위에 노출되게 배치해서 스크롤을 내려야하는 상황을 최소화하였습니다.
- 수락을 클릭하기 전까진 판매하기 버튼은 비활성화시켜서 사용자의 실수를 최소화하였습니다.
- 수락, 거절, 판매하기를 누를 때마다 Order의 OrderStatus 상태가 변화하며 소비자도 상태를 확인할 수 있습니다.

<br/><br/>

![주문 자세히보기](https://user-images.githubusercontent.com/74872542/193708718-e16f03f1-c5c9-4e47-a37d-3dfb90fa1b4d.png)
- 특정 주문 카드의 자세히 보기가 클릭 될 때마다 자바스크립트로 내부 주문 정보를 교체해주는 방식으로 구현하였습니다.

<br/><br/>

![주문거절](https://user-images.githubusercontent.com/74872542/193708351-ecbb4c3d-a958-4fca-84ae-c1b54cc405db.png)
- 사유를 선택 후 제출하기를 클릭하면 Order 테이블에 거절 사유가 입력되고 소비자는 테이블을 참조하여 거절 사유를 확인할 수 있습니다.

<br/><br/>

![거절사유입력](https://user-images.githubusercontent.com/74872542/193708356-2e97af2b-e069-4793-8bfa-559ab434eafc.png)

<br/><br/><br/><br/>

> ## 소비자 기능

<img width="1013" alt="소비자 사진" src="https://user-images.githubusercontent.com/74872542/193709157-fa880212-d08b-41f3-a7a9-dba9f14a1462.PNG">

- 옵션 변경, 상품 추가 및 삭제 등의 가격 변화가 일어나면 클라이언트 단계에서 가격을 즉각 계산해서 변경합니다.
- 단 최종 결제 전에 서버 단계에서 한 번 더 검증을 시행해서 악의적인 가격 수정을 방지합니다.
- 결제 종류, 식사 종류 등의 라디오 버튼을 활용한 요소는 컨트롤러로 전달될 때 직관성을 고려해서 숫자가 아닌 단어 형태로 전달합니다. 또한 Order 테이블에 저장될 때 선택지가 추가될 경우 순서가 밀릴 수 있기 때문에 Enum 타입은 Ordinal이 아닌 String으로 설정하였습니다.
- 추가 주문하기 버튼을 누르면 세션 uuid가 새로 발급되고 새롭게 주문을 넣을 수 있습니다.
