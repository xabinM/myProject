되는거 : <button type="button" th:onclick="'location.href=\'/products/\' + ' + ${product.id} + ' + \'/edit\''">수정하기</button>
되는거 : <button type="button" th:onclick="|location.href='/products/' + ${product.id} + '/edit'|">수정하기</button>
되는거 : <button type="button" th:onclick="|location.href='@{/products/{id}/edit(id=${product.id})}'|">수정하기</button>


안됨 :     <button type="button" onclick="window.location='/products/' + [[${product.id}]] + '/edit'">수정하기</button>
안됨 :     <button type="button" onclick="location.href='/products/' + [[${product.id}]] + '/edit'">수정하기</button>
안됨 :     <button type="button" onclick="location.href='@{/products/{id}/edit(id=${product.id})}'">수정하기</button>
안됨 :     <button type="button" th:href="@{/products/{id}/edit(id=${product.id})}">수정하기</button>
안됨 :     <button type="button" th:action="@{/products/{id}/edit(id=${product.id})}">수정하기</button>

오늘 할일 순서
3. Spring Security

기록

- 로그인을 한 상태에서 신규 접속 기능(로그인, 회원가입 등) 페이지에 접근을 하려는 것과,
  로그인을 하지 않은 상태에서 권한이 없는 페이지에 접근을 하려하는것 둘을 구분 지었다.
  첫 번째 경우는 그냥 메인 페이지로 리다이렉트 시켰고, 
  두 번째 경우는 인터셉터를 통해서 컨트롤러에 넘어오기 전 로그인 페이지로의 강제 이동 처리를 했다. 
  이 때, 접속하려고 했던 페이지의 URL을 담아 로그인 성공시 그 URL로 이동하게끔 하였다.

- Profile (header 드롭다운) : 여기서 뒤로가기로 로그인 전으로 돌아갔을때 로그인멤버의 session 처리가 안되고 있음. 수정해야함.
- 뒤로 가기로 접근할 때, 최신화가 안된다. 상품 삭제 후 메인페이지로 뒤로가기로 접근할 때, 삭제가 안됨, 새로고침하면 됨. 이것도 수정 필
