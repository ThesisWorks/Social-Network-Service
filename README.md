## 기능 목록
- [ ] 로그인 기능 (선택사항)
	- [x] 중복되지 않은 id로 로그인 할 수 있다.
	- [ ] 비번 -> 솔트 해싱기법 -> Sptin Security
	- [ ] 외부 서비스(카카오 로그인, 네이버 로그인)

- [x] 회원 가입 기능
  - [x] 중복되지 않은 id로 회원가입 할 수 있다.

- [x] 로그아웃 할 수 있다.

- [x] 팔로우 기능
	- [x] 유저가 다른 유저를 팔로우 할 수 이다.
	- [x] 팔로우, 팔로워 구분

- [ ] 검색 기능
	- [ ] 사용자 이름을 검색할 수 있다.
	- [ ] 사용자 프로필로 이동한다.

- [ ] 알림 기능(선택 사항)
	- [ ] 팔로우 당할때 알림을 받는 기능
	- [ ] 좋아요 알림 기능(선택 사항)
	- [ ] 댓글 알림 기능(선택 사항)

- [ ] 프로필 기능
	- [x] 그 유저의 정보를 출력한다.
		- [x] id(username)
		- [ ] 프로필사진(선택사항)
		- [x] 소개
		- [ ] 팔로우, 팔로워 목록
		- [ ] 작성한 게시글
	- [ ] 팔로우 버튼을 누를 수 있다.
	- [ ] 언팔로우 버튼을 누를 수 있다.

- [x] 텍스트 게시글을 작성하는 기능
	- [x] 사용자가 작성한 게시글을 받는다.
	- [x] 게시글을 DB에 저장한다.
	- [ ] 선택사항
		- [ ] 사진 - [ ]> 이미지 소스를 링크 생성(호스팅)
		- [ ] 사진 파일을 직접 받아서 저장

- [x] 텍스트 게시글을 수정하는 기능
	- [x] 사용자가 작성한 게시글을 수정한다.
	- [x] 수정 내역을 받아서 DB에 저장한다.

- [x] 특정 게시글을 보는 기능
  - [x] 특정 게시글의 제목과 내용을 볼 수 있다.

- [x] 텍스트 게시글을 삭제하는 기능
	- [x] 버튼을 누르면 게시글이 삭제된다.

- [ ] 게시글에 좋아요를 누르는 기능
	- [ ] 게시글에 좋아요 버튼을 누르면 좋아요 숫자가 1 올라간다.
	- [ ] 한번 더 누르면 좋아요가 취소된다.

- [ ] 게시글에 댓글을 다는 기능(선택사항)
	- [ ] 게시글에 댓글을 달면 이 내역을 DB에 저장한다.

- [ ] 게시글 공유 기능(선택사항)

- [ ] 홈화면 기능
	- [ ] 팔로우 중인 유저들의 게시글을 보여준다.
	- [ ] 시간 순으로 출력한다.
	- [ ] 드래그로 게시글을 넘기거나, 페이지로 넘긴다.
	- [ ] 게시글에는 다음 정보가 들어간다.
		- [ ] 게시글 내용
		- [ ] 작성자 이름
		- [ ] 작성자 프로필
