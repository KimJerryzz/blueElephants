# ⭐️⭐️⭐️ NOTICE ⭐️⭐️⭐️
### Clone -> 세팅 순서
* pom.xml (run) -> maven 세팅 안되있을 시 아래 사진처럼 세팅한다. (Preferences -> Build Tools -> Maven)
<img width="984" alt="aa" src="https://user-images.githubusercontent.com/88300814/128284336-4aaa8314-ea76-4f05-b99f-c4ed4f33924b.png">

* Maven Build
Maven clean&install 실행

* Application Run
java: cannot find symbol 에러 발생시 ISSUE001 해당 구문 참조해서 해결해야됩니다.

### 서드파티 local repo 접근방법
* 아래 순서대로 필히 진행해야함.
* Intelij -> Terminal
```cli
mvn install:install-file -Dfile=[파일 full경로] -DgroupId=[그룹아이디] -DartifactId=[artifactId] -Dversion=[버전정보] -Dpackaging=[jar] -DgeneratePom=true
```

* 빌드 끝난 후 Terminal
```cli
mvn -U -DskipTest clean compile install
```

### 소스코드 대폭 수정 (21.08.05)

### 기존 인계받은 원본 소스에 문제가 많아 New Project에 릴리즈 업데이트 했습니다.

### 프로젝트 폴더링 구조 변경 작업중 (21.08.06)
#
# 🔥🔥🔥 ISSUE NOTICE 🔥🔥🔥
### ISSUE001. QueryDSL not find Error (21.08.05) 수정
### Project Clone 시 QueryDSL 모듈 설정
* 해결 방안
먼저 아래와 같이 Intellij의 File-Project Structure 메뉴를 보면 다음과 같이 'Source Folders'에 java만 있는 것을 확인해볼 수 있습니다.
![images1](https://github.com/momentjin/study/raw/master/resource/image/querydsl%EC%9D%B8%EC%8B%9D%EB%AC%B8%EC%A0%9C1.png?raw=true)

* 결과

다음과 같이 'source-generated' 폴더를 Sources로 설정하면 IDE가 해당 폴더를 Source로 인식해서 더 이상 오류를 보여주지 않습니다.

![img2](https://github.com/momentjin/study/raw/master/resource/image/querydsl%EC%9D%B8%EC%8B%9D%EB%AC%B8%EC%A0%9C2.png?raw=true)

# 💜💜💜 파일 별 주석처리 가이드 💜💜💜
#
### 각자 아래 주석 형태를 따라 수정일, 수정내역을 간략하게 표기한다.

* JAVA
<img width="441" alt="java" src="https://user-images.githubusercontent.com/88300814/128697526-8a39bafa-e9fa-43f4-84cf-e1ff58e11a9c.png">

* JS
<img width="849" alt="js" src="https://user-images.githubusercontent.com/88300814/128697550-daff5642-6cfa-4b00-893f-8ed7400961fb.png">

* CSS
<img width="569" alt="css" src="https://user-images.githubusercontent.com/88300814/128697570-6a3b4035-a8ce-41e9-ae27-6bb412ba9d0c.png">

test
