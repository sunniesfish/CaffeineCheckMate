			    // 이메일 인증 성공 시
			    newWindow.onload = function() {
			        // 부모 창의 HTML 요소 업데이트
			        const container = document.querySelector('.container');
			        container.innerHTML = '<h1>인증성공</h1>';
			        container.style.color = 'green'; // 성공 메시지 색상 변경
			        
			        // 세션 속성 업데이트
			        sessionStorage.setItem('authCodeVerified', 'true');
			        return true; // 트루 값 반환
			    }

			    // 이메일 인증 실패 시
			    newWindow.onerror = function() {
			        // 부모 창의 HTML 요소 업데이트
			        const container = document.querySelector('.container');
			        container.innerHTML = '<h1>인증실패</h1>';
			        container.style.color = 'red'; // 실패 메시지 색상 변경
			        
			        // 세션 속성 업데이트
			        sessionStorage.setItem('authCodeVerified', 'false');
			        return false; // 폴스 값 반환
			    }