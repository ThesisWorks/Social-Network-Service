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

                        const usernameH4 = document.createElement("h4");
                        usernameH4.textContent = board.user.username;
                        profileDiv.appendChild(usernameH4);

                        const profileImg = document.createElement("img");
                        profileImg.src = "/images/base_profile.png";
                        profileDiv.appendChild(profileImg);

                        const postDiv = document.createElement("div");
                        postDiv.className = "post";
                        cardDiv.appendChild(postDiv);

                        const postImg = document.createElement("img");
                        postImg.src = "https://picsum.photos/400/400";
                        postDiv.appendChild(postImg);

                        const iconsDiv = document.createElement("div");
                        iconsDiv.className = "icons";
                        cardDiv.appendChild(iconsDiv);

                        const likeI = document.createElement("i");
                        likeI.className = "bi bi-heart fa-2x ";
                        iconsDiv.appendChild(likeI);

                        const chatLeftI = document.createElement("i");
                        chatLeftI.className = "bi bi-chat-left fa-2x";
                        iconsDiv.appendChild(chatLeftI);

                        const rightSquareI = document.createElement("i");
                        rightSquareI.className = "bi bi-arrow-up-right-square fa-2x";
                        iconsDiv.appendChild(rightSquareI);

                        const saveI = document.createElement("i");
                        saveI.className = "bi bi-bookmark  fa-2x";
                        iconsDiv.appendChild(saveI);

                        const aboutPostDiv = document.createElement("div");
                        aboutPostDiv.className = "aboutPost";
                        cardDiv.appendChild(aboutPostDiv);

                        const likePeopleH4 = document.createElement("h4");
                        likePeopleH4.textContent = "좋아요 0개";
                        aboutPostDiv.appendChild(likePeopleH4);

                        const likePeopleImg = document.createElement("img");
                        likePeopleImg.src = "/images/base_profile.png";
                        likePeopleImg.width = "22";
                        likePeopleImg.height = "22";
                        likePeopleH4.appendChild(likePeopleImg);

                        const boardTitleH4 = document.createElement("h4");
                        boardTitleH4.className = "nameCaption";
                        boardTitleH4.textContent = board.title;
                        aboutPostDiv.appendChild(boardTitleH4);

                        const boardContentH4 = document.createElement("h4");
                        boardContentH4.className = "nameCaption";
                        aboutPostDiv.appendChild(boardContentH4);

                        const boardContentSpan = document.createElement("span");
                        boardContentSpan.textContent = board.content;
                        boardContentH4.appendChild(boardContentSpan);

                        const commentNumDiv = document.createElement("div");
                        commentNumDiv.style = "margin-top:-20px;";
                        commentNumDiv.textContent = "댓글 0개";
                        aboutPostDiv.appendChild(commentNumDiv);

                        const commentH4 = document.createElement("h4");
                        aboutPostDiv.appendChild(commentH4);

                        const commentProfileImg = document.createElement("img");
                        commentProfileImg.src = "/images/base_profile.png";
                        commentProfileImg.width = "20";
                        commentProfileImg.height = "20";
                        commentH4.appendChild(commentProfileImg);

                        const commentInput = document.createElement("input");
                        commentInput.type = "text";
                        commentInput.placeholder = "add a comment&#128512;&#128516;&#128525;&#128151;";
                        commentInput.style = "margin-top:-10px; margin-left:5px; margin-bottom:5px;";
                        commentH4.appendChild(commentInput);

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
