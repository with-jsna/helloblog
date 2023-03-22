# Kakao bank

> 과제: 블로그 검색 서비스

## 다운로드 링크

https://drive.google.com/drive/folders/1qkc-O2nQCCDcNdgPZYN6V9Mtn4DyohdP?usp=sharing

## Run Project

```bash
$ ./gradlew bootRun
```

## Spec

- Kotlin 1.8.10
- Jdk 17
  - 스프링3을 사용하기 위해 사용
- Spring Boot 3.0.4
- Spring Webflux
  - WebClient를 이용해 외부 통신을 위해 사용
- Spring Boot Configuration Processor
  - properties 설정을 위해 사용 (환경에 따라 변경 가능)
- MapStruct
  - 객체간의 변환하기 위해 사용
- netty-resolver-dns-native-macos
  - OSX M1 개발환경 오류 해결을 위해 사용


- Spring data JPA
- QueryDSL
  - 동적쿼리를 작성하기 위해 사용
- H2
  - Database

- JUnit5
  - 테스트코드 작성을 위해 사용
- Kotest
  - Kotlin 다운 테스트코드 작성을 위해 사용
- Mockk
  - 유닛 테스트를 작성할 때 dummy 객체를 작성하기 위해 사용

## API 명세서

### 블로그 검색

```
GET /api/blog?keyword=카뱅 HTTP/1.1
Host: localhost:8080
```

**Request Parameters**

| Parameter  | Optional | Description |
|------------|----------|-------------|
| keyword    | true     | 검색어         |
| sort       | false    | 정렬          |
| pageNumber | false    | 페이지 번호      |
| pageSize   | false    | 페이지 크기      |

**Response Fields**

| Path              | Type   | Description |
|-------------------|--------|-------------|
| blogType          | String | 블로그 타입      |
| result            | Array  | 검색 결과       |
| result[].blogName | String | 블로그 이름      |
| result[].title    | String | 제목          |
| result[].contents | String | 내용          |
| result[].postDate | String | 게시일시        |
| result[].link     | String | 링크          |

**Response Example**

```json
{
  "data": {
    "blogType": "KAKAO",
    "result": [
      {
        "blogName": "지수의 개발노트",
        "title": "[<b>카뱅</b>]",
        "contents": "<b>카뱅가는길</b>",
        "postDate": "2023-03-15T20:00:18.000+09:00",
        "link": "https://flame.tistory.com/1"
      }
    ],
    "pageNumber": 1,
    "pageSize": 10,
    "totalCount": 77777
  }
}
```

### 인기 검색어 목록

```
GET /api/popular/top10 HTTP/1.1
Host: localhost:8080
```

**Response Fields**

| Path  | Type   | Description |
|-------|--------|-------------|
| word  | String | 검색어         |
| count | NUMBER | 횟수          |

**Response Example**

```json
{
  "data": [
    {
      "word": "카뱅",
      "count": 7777777
    },
    {
      "word": "카카오뱅크",
      "count": 777777777
    },
    {
      "word": "국민은행",
      "count": 4
    }
  ]
}
```
