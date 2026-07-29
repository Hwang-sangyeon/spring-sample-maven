# spring-sample-maven

IntelliJ 테스트용 Spring Boot 3.3.4 + Maven 샘플 프로젝트입니다.

## 요구 사항
- JDK 17 이상
- IntelliJ IDEA (Community 또는 Ultimate)
- Maven은 별도 설치 불필요 (IntelliJ 내장 Maven 사용 가능)

## IntelliJ에서 열기
1. IntelliJ IDEA 실행 → `Open` → 이 폴더(`spring-sample-maven`) 선택
2. `pom.xml`이 인식되면 자동으로 Maven 프로젝트로 임포트됨 (우측 하단 알림에서 "Load Maven Project" 클릭, 또는 자동 진행)
3. 의존성 다운로드가 끝나면 `SampleApplication.java` 실행 (▶ 버튼 클릭 또는 `Shift+F10`)

## 커맨드라인에서 실행 (선택)
```bash
mvn clean package
mvn spring-boot:run
```

## 동작 확인
애플리케이션 실행 후 브라우저 또는 curl로 확인:
```bash
curl http://localhost:8080/
curl http://localhost:8080/hello
curl http://localhost:8080/hello?name=Sample
curl http://localhost:8080/actuator/health
```

## 테스트 실행
```bash
mvn test
```
또는 IntelliJ에서 `HelloControllerTest.java` 파일 열고 클래스명 옆 ▶ 버튼 클릭

## 프로젝트 구조
```
spring-sample-maven/
├── pom.xml
├── src/main/java/com/sample/
│   ├── SampleApplication.java   ← 메인 클래스
│   └── HelloController.java     ← REST API
├── src/main/resources/
│   └── application.yml
└── src/test/java/com/sample/
    └── HelloControllerTest.java
```

## 포트
기본 포트: **8080** (Gradle 버전과 동시 실행 시 충돌 없도록 Gradle은 8081 사용)
