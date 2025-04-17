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


