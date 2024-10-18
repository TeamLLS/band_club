# API

## ▶클럽 생성
### POST /club
```
header: {  
  Authorization: Bearer ${accessToken value},
}

form-data: {
  name: 모임 이름, (String)
  description: 모임 설명, (String)
  image: 모임 이미지, (MulitPartFile)
  contactInfo: 컨택 정보 (String)
} 
```

### 응답
```
body: {  
  clubId: 생성 클럽 ID (Long)
}
```


## ▶클럽 정보 변경
### PATCH /club
```
header: {  
  Authorization: Bearer ${accessToken value},
}

form-data: {
  clubId: 클럽 ID, (Long)
  name: 모임 이름, (String)
  nameChanged: 이름 변경 여부, (Boolean, true or false)
  description: 모임 설명, (String)
  descriptionChanged: 설명 변경 경부, (Boolean, true or false)
  image: 모임 이미지, (MulitPartFile)
  imageChanged: 이미지 변경 여부, (Boolean, true or false)
  status: 클럽 상태 (ACTIVE or RECRUITING or TERMINATED)
  statusChanged: 상태 변경 여부 (Boolean, true or false)
} 
```

### 응답
```
```

## ▶클럽 정보 조회
### GET /club/{club Id}
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
body: {
  clubId: 클럽 ID, (Long)
  name: 클럽 이름, (String)
  description: 클럽 설명, (String)
   image: 이미지 리소스 url,
   contactInfo: 컨택 정보, (String)
   status: 클럽 상태, (String)
   memberNum: 회원 수 (Integer)
}
```


## ▶클럽 해체
### PATCH /club/{club Id}/close
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
```

## ▶참여 클럽 리스트 조회
### GET /member/club/list?pageNo={페이지 번호}
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
body: {
    list: [
        {
            clubId: 클럽 ID, (Long)
            memberId: 회원 ID, (Long)
            image: 이미지 리소스 URL, 
            name: 클럽 이름, (String)
            clubStatus: 클럽 상태,
            role: 회원 권한, 
            memberStatus: 회원 상태
        },
        ...
    ]
}
```


## ▶회원 등록
### POST /member
```
header: {  
  Authorization: Bearer ${accessToken value},
}

body: {
  clubId: 가입 클럽 ID, (Long)
  username: 가입 대상 username (String)
}
```

### 응답
```
body: {
  memberId: 회원 ID (Long)
}
```



## ▶클럽 회원 조회
### GET /member/{클럽 ID}/list?pageNo={페이지 번호}
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
body: {
    list: [
        {
            memberId: 회원 ID, (Long)
            clubId: 클럽 ID, (Long)
            username: username, (String)
            roleName: 회원 권한, (String)
            name: 회원 이름, (Stirng)
            age: 나이, (Integer)
            gender: 성별, (String)
            statusName: 회원 상태 (String)
        },
        ...
    ]
}
```


## ▶회원 권한 변경
### PATCH /member/role
```
header: {  
  Authorization: Bearer ${accessToken value},
}

body: {
  memberId: 대상 회원 ID, (Long)
  role: 변경 권한 (OWNER(회장) or MANAGER(관리자) or REGULAR(일반))
}
```

### 응답
```
```



## ▶회원 탈퇴
### PATCH /member/{클럽 Id}/withdraw
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
```


## ▶회원 강퇴
### PATCH /member/{회원 Id}/ban
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
```
