document.getElementById('editableTitle').addEventListener('click', function() {
    this.removeAttribute('readonly'); // readonly 속성을 제거하여 편집 가능하게 함
});

// 외부를 클릭하면 편집을 종료하고 readonly 속성을 다시 설정
document.addEventListener('click', function(event) {
    var titleField = document.getElementById('editableTitle');
    if (event.target !== titleField) {
        titleField.setAttribute('readonly', true);
    }
});
