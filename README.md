# cnpm
1.Cài đặt các tools cần thiết  
  - Cài đặt git   
    - Link hướng dẫn:https://www.youtube.com/watch?v=4xqVv2lTo40 
    - Link cài đặt:https://git-scm.com/   
  - Cài đặt Intellij idea 
    - Link hướng dẫn:https://www.youtube.com/watch?v=-5kIt83ldk8 
    - Link jdk:https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html 
    - Link Intellij idea:https://www.jetbrains.com/idea/download/#section=windows 
  - Cài đặt MsSql 
    - Link hướng dẫn:https://youtu.be/O2yhVbvfwWs 
    - Link sql Server:https://www.microsoft.com/en-us/sql-server/sql-server-downloads       
2.Setup project
  - Download project
    - Chọn thư mục bất kỳ:
        - Chuột phải chọn Git Bash Here
        - Gõ lần lượt từng câu lệnh:
            - git init
            - git clone https://github.com/AnHaiTrinh/cnpm.git
  - Mở sql manager
    - Tạo database mới:đặt tên se07,port mặc định 1433
    - Run file se07.sql trong thư mục database
  - Mở file pom.xml bằng Intellij idea 
    - Kết nối với database theo từng bước sau:
    ![image](https://user-images.githubusercontent.com/94188910/213843015-26253429-eaa7-4d12-a324-ef216ba2d651.png)
      - Chọn Edit Configurations..
      - Chọn Add new Configurations
      - Thiết lập Working Directory:Đường dẫn đến project
      - Thiết lập Environment Variables:USER=tên user MsSQL;MSSQL_PWD=mật khẩu MsSQL;SERVER_NAME=LOCALHOST;DATABASE_NAME=se07;PORT_NUMBER=1433;ENCRYPT=FALSE
  Ví dụ
  ![image](https://user-images.githubusercontent.com/94188910/213843001-1fa6c5bf-ec14-4fe7-9220-cb534ca3d5fa.png)
  - Chạy file main để sử dụng phần mềm
