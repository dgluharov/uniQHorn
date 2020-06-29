# UniQHorn

This is the repository for the internal Quality House project - Uniqhorn(a timesheet management system).

## Installation
1. Install IntelliJ Idea(Community edition) from https://www.jetbrains.com/idea/download/#section=windows
2. Install MySql from https://dev.mysql.com/downloads/installer/  
    *Note: Use the first package (the smaller one)  
    1. Use these credentials when creating DataBase:  **Username** - root; **Password** - root  
        *Note: The local instance of DB should be run on 3306 port   
    2. After installation, open **MySQL Workbench**, enter into DataBase using credentials(**Username** - root; **Password** - root )   
        1. On the bottom of ***Navigator*** panel click on **Schemas** tab  
        2. Right click in ***Navigator*** panel and click **Create Schema...**  using name ***uniqhorn*** 
3. Clone or copy project link from: https://github.com/dgluharov/uniQHorn
4. Download and install **Java  13** from https://www.oracle.com/java/technologies/javase-jdk13-downloads.html     
5. Import project in IntelliJ Idea
6. Go to File>Settings>Plugins  
    1. Search for Lombok and install it
7. In the top Toolbar click on the **Add/Edit Configuration** dropdown  
    1. The button **+** is in the top left corner... click it
    2. Select ***Maven*** and choose appropriate name for configuration  
    3. In **Command line** input field press ***Ctrl+Space*** and choose **spring-boot:run** from dropdown
8. Run project