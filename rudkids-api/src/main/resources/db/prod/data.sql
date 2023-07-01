insert into tbl_user(user_id, email, name, phone_number, deleted, path, url, role_type)
select X'0dda3b177a0c4c6888724af6f5425035', 'xorud6761@gmail.com', '김태경', '01012345678', false, 'path', 'http://k.kakaocdn.net/dn/bPPLA2/btshAzroPsi/aheqqZR70QtzADLKLWk0v1/img_640x640.jpg', 'ADMIN'
from dual
where not exists (select * from tbl_user where user_id = X'0dda3b177a0c4c6888724af6f5425035');