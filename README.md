# parking
 
打包
mvn clean package
啟動
java -jar \tarage\parking-0.0.1-SNAPSHOT.jar


系統使用SpringBtoot 2.24版本,資料庫Mysql,框架使用mybatis

程式使用spring boot schedule產生request，每15秒查詢停車位狀況，每10秒會有0 ~ 2台車停車，每15秒會有0 ~ 2台車離開

![image](https://github.com/nwahs0s/parking/blob/master/pic/%E8%9E%A2%E5%B9%95%E5%BF%AB%E7%85%A7%202020-02-20%20%E4%B8%8B%E5%8D%8810.20.17.png)

停車狀況紀錄包含停車開始時間、離開時間、停車費用
![image](https://github.com/nwahs0s/parking/blob/master/pic/%E8%9E%A2%E5%B9%95%E5%BF%AB%E7%85%A7%202020-02-20%20%E4%B8%8B%E5%8D%8810.40.52.png)

停車位狀態紀錄，已停車時狀態為1
![image](https://github.com/nwahs0s/parking/blob/master/pic/%E8%9E%A2%E5%B9%95%E5%BF%AB%E7%85%A7%202020-02-20%20%E4%B8%8B%E5%8D%8810.41.06.png)
