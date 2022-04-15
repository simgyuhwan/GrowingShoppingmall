# GrowingShoppingmall

설명 : 초기 도메인 구성부터 각 도메인에 필요한 요구사항들을 정리하고 이를 객체지향에 맞게 구현해볼 예정입니다. 쇼핑몰을 구현하면서 요구사항들을 하나 둘 추가해보면서 개발할 예정이며 REST API로 구현할 것입니다. 주기적으로 도메인과 코드를 리팩토링하며 수정하여 점진적으로 발전 시켜보려 합니다..
기본적인 구현이 완료되면 AWS와 젠킨스 등을 사용하여 자동 배포를 구현 예정

## 도메인 구성
 회원 - 주문 - 상품 - 배송 
 
## 도메인 요구사항 정리
* 회원(Member)
  1) JWT 구현
  2) 회원 가입할 수 있다.
  3) ID는 이메일을 사용
  4) 비밀번호는 최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력
  5) 닉네임 사용(중복 X)
  6) 회원의 등급은 회원, 운영자이다.
  7) 운영자만 상품을 등록할 수 있다.
  8) 회원 정보는 주소, 핸드폰 번호, ID, PW가 있다.




