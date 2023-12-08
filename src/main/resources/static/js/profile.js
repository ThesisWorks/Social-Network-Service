function submitFollowingForm() {
	document.querySelector('#following-form input[name="id"]').value = document.getElementById('followingCount').innerText;
	document.getElementById('following-form').submit();
}

function submitFollowerForm() {
	const followerCount = document.getElementById('followerCount').innerText;
	document.querySelector('#follower-form input[name="id"]').value = followerCount;
	document.getElementById('follower-form').submit();
}
