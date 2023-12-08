document.addEventListener('DOMContentLoaded', function () {
    let page = 0;
    const size = 1; // 한 번에 불러올 게시판의 수
    let isFetching = false; // 현재 데이터를 불러오고 있는지 상태 표시

    const loadMoreBoards = function () {
        if (window.innerHeight + window.scrollY >= document.body.offsetHeight && !isFetching) {
            isFetching = true;
            fetch(`/home/data?page=${page}&size=${size}`)
                .then(response => response.json())
                .then(data => {
                    // 받아온 데이터를 사용하여 게시판 목록에 아이템 추가
                    data.content.forEach(board => {
                        const cardDiv = document.createElement("div");
                        cardDiv.className = "card";
                        document.getElementById("boardContainer").appendChild(cardDiv);

                        const profileDiv = document.createElement("div");
                        profileDiv.className = "profile";
                        cardDiv.appendChild(profileDiv);

                        const profileLink = document.createElement("a");
                        profileLink.href = `/profile/${board.user.username}`;
                        profileDiv.appendChild(profileLink);

                        const usernameH4 = document.createElement("h4");
                        usernameH4.textContent = board.user.username;
                        profileLink.appendChild(usernameH4);

                        const profileImg = document.createElement("img");
                        profileImg.src = "/images/base_profile.png";
                        profileLink.appendChild(profileImg);

                        const postDiv = document.createElement("div");
                        postDiv.className = "post";
                        cardDiv.appendChild(postDiv);

                        const postImg = document.createElement("img");
                        postImg.src = board.imagePath;
                        postDiv.appendChild(postImg);

                        const aboutPostDiv = document.createElement("div");
                        aboutPostDiv.className = "about-post";
                        cardDiv.appendChild(aboutPostDiv);

                        const boardTitleH4 = document.createElement("h4");
                        boardTitleH4.className = "nameCaption";
                        boardTitleH4.textContent = board.title;
                        aboutPostDiv.appendChild(boardTitleH4);

                        const boardContentH5 = document.createElement("h5");
                        aboutPostDiv.appendChild(boardContentH5);

                        const boardContentSpan = document.createElement("span");
                        boardContentSpan.textContent = board.content;
                        boardContentSpan.className = "contentCaption";
                        boardContentH5.appendChild(boardContentSpan);

                        const postTimeH6 = document.createElement("h6");
                        postTimeH6.textContent = board.createdTime;
                        aboutPostDiv.appendChild(postTimeH6);
                    });

                    page++;
                    isFetching = false;
                })
                .catch(error => {
                    console.error('Error:', error);
                    isFetching = false;
                });
        }
    };

    window.addEventListener('scroll', loadMoreBoards);
});
