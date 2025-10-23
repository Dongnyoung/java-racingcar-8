# java-racingcar-precourse

## 기능구현목록

---

**클래스 단위로 기능 구현**

### 시작/안내 멘트 클래스 (Banner)

### 입력클래스 (InputView)

### 입력값 처리 클래스 (InputParser)
- ","를 기준으로 나누기
- StringBuilder배열 만들기
- 시도할 횟수를 문자열->정수로 변환

### 참가자 유효성 검사클래스 (ParticipantValidator)
- 유효성(5자 이하여야함)검사 통과하면 " : " 이거 붙여줌 (참가자확정이라는 뜻으로 해야겟다)
  멘트

### 참가자 결정 클래스 (ParticipantRegistry)
- 참가자이름 옆에 " : "를 붙여주면 참가자로 확정

### 전진 클래스 (Movement)
- 횟수만큼 비즈니스 로직 실행
- randomNumber가 4보다 크면 전진

### 진행상황을 보여주는 클래스 (ProgressView)
- StringBuilder 출력

### 전진횟수 체크 클래스 (MoveCounter)
- 참가자들의 전진 횟수 체크

### 전진횟수 저장 클래스 (FinalMoveRecord)
- 해쉬맵초기화
- 해쉬맵에 참가자들의 전진 횟수 저장

### 가장 많이 전진한 횟수 결정 클래스 (MaxMoveFinder)
- 가장 많이 전진한 횟수 체크

### 우승자 결정 클래스 (WinnerDecider)

- 우승자 결정 (가장많이 전진한 횟수를 기반으로)

### 우승자 출력 클래스 (WinnerView)
- 우승자StringBuilder깨끗하게 처리

- 우승자 출력  (String[] 이용)
- 우승자 출력 멘트

