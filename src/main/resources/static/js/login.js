$(document).on('click', '#login-btn', function(){

    let id = $('#login-id').val().trim();
    let pw = $('#login-pw').val().trim();

    if(id == ''){
        alert('아이디를 입력하세요.');
        return;
    }
    if(pw == ''){
        alert('비밀번호를 입력하세요.');
        return;
    }

    $.ajax({
        url: '/tmap/login', // 서버 URL
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({userId: id , userPw :pw}),
        success: function(response) {

            switch (response){

                case '404': alert('아이디 / 비밀번호를 다시 입력해 주세요.'); break;

                case 'anonymous': alert('존재하지 않는 아이디.'); break;

                case 'wrongPw': alert('비밀번호가 틀렸습니다.'); break;

                case 'err': alert('서버 오류입니다.'); break;

                case 'login': alert('로그인 성공.'); break;

            }

        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
            alert('서버 에러입니다. 잠시 후 다시 시도해주세요.');
        }
    });

});