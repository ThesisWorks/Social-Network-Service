/* 드롭다운 메뉴를 토글하는 함수 */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// 메뉴 바깥을 클릭하면 드롭다운 메뉴가 닫히도록 함
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}
