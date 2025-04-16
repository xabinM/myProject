되는거 : <button type="button" th:onclick="'location.href=\'/products/\' + ' + ${product.id} + ' + \'/edit\''">수정하기</button>
되는거 : <button type="button" th:onclick="|location.href='/products/' + ${product.id} + '/edit'|">수정하기</button>
되는거 : <button type="button" th:onclick="|location.href='@{/products/{id}/edit(id=${product.id})}'|">수정하기</button>


안됨 :     <button type="button" onclick="window.location='/products/' + [[${product.id}]] + '/edit'">수정하기</button>
안됨 :     <button type="button" onclick="location.href='/products/' + [[${product.id}]] + '/edit'">수정하기</button>
안됨 :     <button type="button" onclick="location.href='@{/products/{id}/edit(id=${product.id})}'">수정하기</button>
안됨 :     <button type="button" th:href="@{/products/{id}/edit(id=${product.id})}">수정하기</button>
안됨 :     <button type="button" th:action="@{/products/{id}/edit(id=${product.id})}">수정하기</button>

오늘 할일 순서
1. 각 페이지 로그아웃 버튼
2. 상품 페이지, 내 상품 페이지 구분 짓고 수정 삭제 버튼 옮기기
3. Spring Security

메인 페이지에서 상품 목록을 보인다.
기존의 상품 페이지 이동은 myProducts 로 바꾼다.
현재 css 적용 안되어있음.
