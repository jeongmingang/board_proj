-- 계정 권한 부여
grant all 
	on board.* 
	to 'user_gradle_erp'@'localhost'
identified by 'rootroot';


-- file 권한(backup, load) --root만 권한 부여 가능
grant File
  on *.*
 to 'user_gradle_erp'@'localhost';







