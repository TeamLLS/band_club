# ▶동아리 관리 서비스: MyZone
![1734664390048](https://github.com/user-attachments/assets/4cff46a7-13a2-410c-a65c-7a3ce7e287ab)

```
목적: 동아리 관리 편의 제공 목적 어플리케이션
      관리 정보, 활동 통계를 모든 회원들에게 투명하게 제공
```

# ▶목차 
- [기술](#기술)
- [시스템 구성](#시스템-구성)
- [DB 구성](#DB-구성)
- [서비스 개요](#서비스-개요)
- [성능 테스트](#성능-테스트)
- [주요 로직](#주요-로직)
- [API](#API)


# ▶기술
```
- jdk 17: 개발 환경

- spring boot 3.3.x: 개발 프레임 워크

- mysql: 서버 db

- kafka: 메시징 미들웨어 

- aws ec2, docker: 서버 배포 

- oauth2 kakao login: 외부 로그인 연동  

- redis: 인증 토큰 저장소

- aws api gateway: api 엔드포인트

- aws lambda: 토큰 인증 필터 

- aws elb: 경로 라우팅 

- aws s3 bucket: 이미지 저장소
```

# ▶시스템 구성
![l5](https://github.com/user-attachments/assets/d75e3e2c-9008-40be-ab35-7671e3d14114)

---------------------------------------------------------------------------------------------

![l3](https://github.com/user-attachments/assets/08042ac0-bc15-45bf-b23f-6c3c856a85aa)
![l4](https://github.com/user-attachments/assets/385be931-ad18-4852-9ba8-61150942416d)

# ▶DB 구성
| 서버 | 위치 |
|------|------|
| User 서버 | https://github.com/TeamLLS/band_user/blob/master/documents/structure.md |
| Club 서버 | https://github.com/TeamLLS/band_club/blob/master/documents/structure.md |
| Acrivity 서버 | https://github.com/TeamLLS/band_activity/blob/master/documents/strucutre.md |
| Budget 서버 | https://github.com/TeamLLS/band_budget/blob/master/documents/structure.md |
| Community 서버 | https://github.com/TeamLLS/band_community/blob/master/documents/structure.md |
| Data 서버 | https://github.com/TeamLLS/band_data/blob/master/documents/structure.md |


# ▶서비스 개요 
## 0. 기능 요약
![l1](https://github.com/user-attachments/assets/138596d8-4d9a-4aab-87e9-ac56282a5e73)

## 1. 로그인 & 프로필 & 모임 관리
![c1](https://github.com/user-attachments/assets/227f2626-dc59-4ed0-9eb2-9290865b605e)

```
- 카카오 기반 로그인
- 프로필 조회 및 변경
- 모임 생성 및 조회 
```

## 2. 모임 내 활동 관리
![c2](https://github.com/user-attachments/assets/cabcfe08-40e9-4305-bca5-ee496c149ebf)
```
- 모임 내 활동 생성 및 조회
- 참가 여부 변경 및 참가자 표시
```

## 3. 회원 관리 / 게시글 작성
![c3](https://github.com/user-attachments/assets/996a4ee8-2f00-4b25-98ff-a60acddad110)
```
- 회원 조회 및 추가
- 공지 작성 및 조회
- 댓글 및 답글 작성
```

## 4. 예산 및 장부 관리 
![c4](https://github.com/user-attachments/assets/603fd0c9-31f8-489f-94f5-42a58738981f)
```
- 예산 변경 사항 등록
- 특정 시점의 예산 상태 및 내역 조회
- 장부 생성 및 조회
- 납부 대상 회원 등록 및 납부 상태 변경
```

## 5. 활동 기반 모임 통계  
![c5](https://github.com/user-attachments/assets/46bc6f51-c74b-4570-83b8-0a3151b2cec0)
```
- 월 단위 회원 증감 통계
- 월 단위 활동량 통계
- 월 단위 예산 통계 
```

## 6. 회원 성실도 평가 
![c6](https://github.com/user-attachments/assets/656d5935-c3bd-4522-9a68-b284d2e73be2)
```
- 회원 활동 기반 (활동 참가율, 회비 납부율, 마지막 활동 시간 등) 점수 산출 및 순위 정렬
- 회원별 최근 3개월 통계 합산
```

# ▶성능 테스트
![n3](https://github.com/user-attachments/assets/1b8c3e67-a6ab-48a6-b0ce-c364909990bf)
![n1](https://github.com/user-attachments/assets/585e577c-29e2-4651-8262-c01195f6bfb3)
![n2](https://github.com/user-attachments/assets/f09111a8-d5e5-4e16-9756-a5e1acd8d0d6)

# ▶주요 로직

## ▷로그인
![bb0](https://github.com/user-attachments/assets/df402ed4-66fc-4d30-b45c-fb2c62eca507)
```
api gate way를 이용해 단일 진입점과 통합 인증 처리 제공
redis를 이용해 access token을 저장하고 이를 조회하여 접근자 인증을 처리, 인증 정보 반환
실제 elb로 요청이 인계될 때 헤더에 접근자 인증 정보 추가
```

## ▷특정 시점 조회
![d3](https://github.com/user-attachments/assets/bafef5c6-bc3a-4cf8-a99f-26d7e87b9147)
```
db 데이터가 갱신될 때 이벤트와 스냅샷을 저장 
최신 데이터 조회 시에 리드 모델을 반환
이전 데이터 조회 시에 그 이전 최신 스냅샷과 그 이후 이벤트들을 합산하여 반환 
```

## ▷서버 간 통신
![bb2](https://github.com/user-attachments/assets/97b62b88-2f2d-477f-a2e0-c81f263711df)
```
데이터 변경이 필요한 요청의 경우 club 서버에서 접근자의 회원 검증 후, 회원 정보와 함께 요청을 mq로 전달
대상 서버는 mq로 부터 받은 요청을 처리 후 생성된 이벤트를 db에 저장 후 mq로 전달
data 서버는 mq로 부터 받은 이벤트를 카운팅하여 합산 데이터를 저장, 후에 통계 데이터 조회시 반환
인증이 필요없는 get 요청의 경우 조회 데이터가 있는 서버로 바로 라우팅  
```


# ▶API
| 서버 | 위치 |
|------|------|
| User 서버 | https://github.com/TeamLLS/band_user/blob/master/documents/api.md |
| Club 서버 | https://github.com/TeamLLS/band_club/blob/master/documents/api.md |
| Acrivity 서버 | https://github.com/TeamLLS/band_activity/blob/master/documents/api.md |
| Budget 서버 | https://github.com/TeamLLS/band_budget/blob/master/documents/api.md |
| Community 서버 | https://github.com/TeamLLS/band_community/blob/master/documents/api.md |
| Data 서버 | https://github.com/TeamLLS/band_data/blob/master/documents/api.md |


