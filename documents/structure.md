# 1. 패키지 
```
band_club
    ├─club
    │  ├─command
    │  ├─event
    │  └─form
    ├─external
    │  ├─feignClient
    │  └─s3
    ├─member
    │  ├─command
    │  ├─event
    │  ├─exception
    │  └─form
    └─policy
```

# 2. 도메인

| 도메인 | 속성 | 타입 | 설명 | 비고 |  
|--------|------|------|------|------|
|Club    |      |      |      |      |
|        |id|Long|Club Id|Primary Key|
|        |image|String|이미지 url||
|        |name|String|이름|NotNull|
|        |description|String|설명||
|        |contactInfo|String|연락처||
|        |status|ClubStatus|상태||
|        |createdAt|Instant|생성일||
|        |closedAt|Instant|폐쇄일||


| ENUM | 값 | 설명 | 비고 |  
|------|----|------|------|
|ClubStatus||      |      |
|          |ACTIVE|운영중||
|          |CLOSED|운영종료||
|          |RECRUITING|회원 모집중||


| 도메인 | 속성 | 타입 | 설명 | 비고 |  
|--------|------|------|------|------|
|Member  |      |      |      |      |
|        |id|Long|Member Id|Primary Key|
|        |club|Club|가입 Club|Foreginer Key, NotNull|
|        |username|String|username|NotNull|
|        |role|Role|권한|NotNull|
|        |ststus|MemberStatus|Member 상태|NotNukk|
|        |name|String|이름||
|        |gender|String|성별|"male" or "female"|
|        |birthYear|Integer|출생년도||
|        |createdAt|Instant|가입일||
|        |terminatedAt|Instant|탈퇴일||



| ENUM | 값 | 설명 | 비고 |  
|------|----|------|------|
|MemberStatus|     |     ||
|            |ACTIVE|활동중||
|            |INACTIVE|휴면||
|            |TERMIATED|탈퇴됨||



| ENUM | 값 | 설명 | 비고 |  
|------|----|------|------|
|Role  |    |      |      |
|      |OWNER|회장|3|
|      |MANAGER|관리자|2|
|      |REGULAR|일반|1|




# 3. 주요 컴포넌트

| 컴포넌트 | 설명 | 비고 |  
|----------|------|------|
|OauthAuthenticator|Oauth 인증 목적 utils||
|UserInfo|Oauth 유저 정보 저장||
|RedisSercie|Redis 접근 목적 utils||
|S3Service|S3 접근 목적 utils||
|JwtUtils|JWT 생성, 인증 목적 utils||
|UserController|User 관련 엔드포인트||
|UserService|User 관련 비즈니스 로직 수행||
|UserRepository|User 관련 DB 접근||




# 4. ERD

![a2](https://github.com/user-attachments/assets/f0cb684b-b475-411c-9414-20cead205c5a)
