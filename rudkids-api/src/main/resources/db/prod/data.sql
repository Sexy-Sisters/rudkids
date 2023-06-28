insert into tbl_user(user_id, age, email, gender, name, phone_number, deleted, path, url, role_type, social_type)
select X'0dda3b177a0c4c6888724af6f5425035', 18, 'xorud6761@gmail.com', 'MALE', '김태경', '01012345678', false, 'path', 'http://k.kakaocdn.net/dn/bPPLA2/btshAzroPsi/aheqqZR70QtzADLKLWk0v1/img_640x640.jpg', 'ADMIN', 'KAKAO'
from dual
where not exists (select * from tbl_user where user_id = X'0dda3b177a0c4c6888724af6f5425035');